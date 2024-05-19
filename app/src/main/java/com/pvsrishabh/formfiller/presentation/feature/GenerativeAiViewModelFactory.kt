package com.pvsrishabh.formfiller.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.pvsrishabh.formfiller.presentation.feature.chat.ChatViewModel
import com.pvsrishabh.formfiller.presentation.feature.multimodal.PhotoReasoningViewModel
import com.pvsrishabh.formfiller.presentation.feature.text.SummarizeViewModel
import com.pvsrishabh.formfiller.util.Constants.API_KEY
import com.pvsrishabh.formfiller.util.Constants.TEXT_IMAGE_MODEL
import com.pvsrishabh.formfiller.util.Constants.TEXT_MODEL

val GenerativeViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        viewModelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val config = generationConfig {
            temperature = 0.7f
        }

        return with(viewModelClass) {
            when {
                isAssignableFrom(SummarizeViewModel::class.java) -> {
                    // Initialize a GenerativeModel with the `gemini-pro` AI model
                    // for text generation
                    val generativeModel = GenerativeModel(
                        modelName = TEXT_MODEL,
                        apiKey = API_KEY,
                        generationConfig = config
                    )
                    SummarizeViewModel(generativeModel)
                }

                isAssignableFrom(PhotoReasoningViewModel::class.java) -> {
                    // Initialize a GenerativeModel with the `gemini-pro-vision` AI model
                    // for multimodal text generation
                    val generativeModel = GenerativeModel(
                        modelName = TEXT_IMAGE_MODEL,
                        apiKey = API_KEY,
                        generationConfig = config
                    )
                    PhotoReasoningViewModel(generativeModel)
                }

                isAssignableFrom(ChatViewModel::class.java) -> {
                    // Initialize a GenerativeModel with the `gemini-pro` AI model for chat
                    val generativeModel = GenerativeModel(
                        modelName = TEXT_MODEL,
                        apiKey = API_KEY,
                        generationConfig = config
                    )
                    ChatViewModel(generativeModel)
                }

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${viewModelClass.name}")
            }
        } as T
    }
}
