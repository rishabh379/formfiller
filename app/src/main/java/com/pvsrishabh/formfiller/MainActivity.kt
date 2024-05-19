package com.pvsrishabh.formfiller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.pvsrishabh.formfiller.presentation.home.HomeNav
import com.pvsrishabh.formfiller.presentation.navgraph.Route
import com.pvsrishabh.formfiller.presentation.onboarding.OnBoardingScreen
import com.pvsrishabh.formfiller.presentation.onboarding.OnBoardingViewModel
import com.pvsrishabh.formfiller.presentation.sign_in.GoogleAuthUiClient
import com.pvsrishabh.formfiller.presentation.sign_in.SignInScreen
import com.pvsrishabh.formfiller.presentation.sign_in.SignInViewModel
import com.pvsrishabh.formfiller.ui.theme.FormFillerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashCondition
            }
        }

        setContent {
            FormFillerTheme {

                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = mainViewModel.startDestination
                    ) {
                        navigation(
                            route = Route.AppStartNavigation.route,
                            startDestination = Route.OnBoardingScreen.route
                        ){
                            composable(
                                route = Route.OnBoardingScreen.route
                            ) {
                                val viewModel: OnBoardingViewModel = hiltViewModel()
                                OnBoardingScreen(
                                    event = viewModel::onEvent
                                )
                            }
                        }

                        navigation(
                            route = Route.FormNavigation.route,
                            startDestination = if(Firebase.auth.currentUser != null){
                                Route.HomeScreen.route
                            }else{
                                Route.SignInScreen.route
                            }
                        ) {
                            composable(Route.SignInScreen.route) {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                LaunchedEffect(key1 = Unit) {
                                    if (googleAuthUiClient.getSignedInUser() != null) {
                                        navController.navigate(Route.HomeScreen.route)
                                    }
                                }

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult = googleAuthUiClient.signInWithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )

                                LaunchedEffect(key1 = state.isSignInSuccessful) {
                                    if (state.isSignInSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign in successful",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.navigate(Route.HomeScreen.route)
                                        viewModel.resetState()
                                    }
                                }

                                SignInScreen(
                                    state = state,
                                    onSignInClick = {
                                        lifecycleScope.launch {
                                            val signInIntentSender = googleAuthUiClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    },
                                    onTextClick = {
                                        navController.navigate(Route.HomeScreen.route) {
                                            // Pop up to the SignInScreen when navigating to the HomeScreen
                                            popUpTo(Route.SignInScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                )
                            }
                            composable(Route.HomeScreen.route) {
                                HomeNav()
                            }
                        }
                    }
                }
            }
        }
    }
}