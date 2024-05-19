package com.pvsrishabh.formfiller.presentation.navgraph

sealed class Route(
    val route: String
){
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route(route = "homeScreen")

//    object SearchScreen : Route(route = "searchScreen")
//    object BookmarkScreen : Route(route = "bookmarkScreen")
//    object DetailsScreen : Route(route = "detailsScreen")

    object SignInScreen : Route(route = "signInScreen")
    object FormScreen : Route(route = "formScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")
    object FormNavigation : Route(route = "formNavigation")

    object FormNavigatorScreen : Route(route = "formNavigatorScreen")

}