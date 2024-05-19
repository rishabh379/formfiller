package com.pvsrishabh.formfiller.presentation.feature

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.ai.sample.feature.chat.ChatRoute
import com.pvsrishabh.formfiller.presentation.feature.form_filler.FormFillerScreen
import com.pvsrishabh.formfiller.presentation.feature.form_filler.FormFillerViewModel
import com.pvsrishabh.formfiller.presentation.feature.multimodal.PhotoReasoningRoute
import com.pvsrishabh.formfiller.presentation.feature.text.SummarizeRoute
import com.pvsrishabh.formfiller.presentation.home.HomeViewModel

@Composable
fun GenAiOptScreen(
    onGoClick: (String) -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(onItemClicked = { routeId ->
                navController.navigate(routeId)
            })
        }
        composable("form_filler"){
            val viewModel = viewModel<FormFillerViewModel>()
            FormFillerScreen(
                state = viewModel.state.value,
                onValueChange = {
                    viewModel.updateText(
                        it
                    )
                },
                onGoClick = { onGoClick(it) }
            )
        }
        composable("summarize") {
            SummarizeRoute()
        }
        composable("photo_reasoning") {
            PhotoReasoningRoute()
        }
        composable("chat") {
            ChatRoute()
        }
    }
}