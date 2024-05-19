package com.pvsrishabh.formfiller.presentation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pvsrishabh.formfiller.presentation.feature.form_filler.FormFillerViewModel
import com.pvsrishabh.formfiller.presentation.form.FormScreen
import com.pvsrishabh.formfiller.presentation.navgraph.Route

@Composable
fun HomeNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.route
    ) {
        composable(route = Route.HomeScreen.route) {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(
                state = viewModel.state.value,
                onClick = { navigate(navController, it) }
            )
        }

        composable(route = Route.FormScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("text")
                ?.let { text ->
                    FormScreen(text = text)
                }
        }
    }
}

private fun navigate(navController: NavController, text: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("text", text)
    navController.navigate(
        route = Route.FormScreen.route
    )
}