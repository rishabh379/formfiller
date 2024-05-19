package com.pvsrishabh.formfiller.presentation.form

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.pvsrishabh.formfiller.presentation.nav_drawer.MenuItem
import com.pvsrishabh.formfiller.util.Constants
import com.pvsrishabh.formfiller.util.Constants.API_KEY
import com.pvsrishabh.formfiller.util.Constants.TEXT_MODEL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class FormViewModel: ViewModel() {
    val text = ""

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun getMenuItemsFromUrl(url: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedMenuItems = mutableListOf<MenuItem>()

            try {
                val connection = withContext(Dispatchers.IO) { Jsoup.connect(url).get() }
                val htmlContent = connection.html()
                val doc = Jsoup.parse(htmlContent)
                val inputs = findAllInputs("div", "geS5n", doc)
                inputs.forEach {
                    fetchedMenuItems.add(MenuItem(genResponse(it)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            _menuItems.value = fetchedMenuItems
            _isLoading.value = false
        }
    }

    // Function to find question options in a div tag
    private fun findQuestionOptions(divTag: org.jsoup.nodes.Element): String {
        // Finding the text question in the given div
        val question = divTag.selectFirst("span.M7eMe")?.text() ?: ""

        // Finding all the single choice options for the given question
        val optS = divTag.select("span.aDTYNe.snByac.OvPDhc.OIC90c")
        val optM = divTag.select("span.aDTYNe.snByac.n5vBHf.OIC90c")
        val options = mutableListOf<String>()
        var optChr = 'a'
        if (optS.isNotEmpty()) {
            for (option in optS) {
                options.add("$optChr: ${option.text()}")
                optChr++
            }
        } else if (optM.isNotEmpty()) {
            for (option in optM) {
                options.add("$optChr: ${option.text()}")
                optChr++
            }
        }

        var totalQuestion = question
        options.forEach { opt -> totalQuestion += "  $opt" }
        return totalQuestion
    }

    // Function to find all inputs in a given tag with a specific class
    private fun findAllInputs(tag: String, cls: String, doc: org.jsoup.nodes.Document): List<String> {
        val inputs = mutableListOf<String>()
        val allDivTags = doc.select("$tag.$cls")
        for (div in allDivTags) {
            inputs.add(findQuestionOptions(div))
        }
        return inputs
    }

    private val config = generationConfig {
        temperature = 0.7f
    }

    private val generativeModel = GenerativeModel(
        modelName = TEXT_MODEL,
        apiKey = API_KEY,
        generationConfig = config
    )

    private suspend fun genResponse(inputText: String): String {
        val prompt = "Give the answers for the following: $inputText"
        val outputContent = StringBuilder()
        try {
            generativeModel.generateContentStream(prompt)
                .collect { response ->
                    outputContent.append(response.text)
                }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, e.localizedMessage ?: "")
        }
        return outputContent.toString()
    }
}