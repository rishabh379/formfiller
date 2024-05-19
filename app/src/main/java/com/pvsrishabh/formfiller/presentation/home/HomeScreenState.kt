package com.pvsrishabh.formfiller.presentation.home

import com.google.firebase.auth.FirebaseUser

data class HomeScreenState(
    val user: FirebaseUser? = null,
    val text: String = ""
)