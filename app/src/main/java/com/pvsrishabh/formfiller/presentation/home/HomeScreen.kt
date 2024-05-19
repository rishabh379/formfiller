package com.pvsrishabh.formfiller.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.pvsrishabh.formfiller.R
import com.pvsrishabh.formfiller.presentation.common.HomeScreenBackground
import com.pvsrishabh.formfiller.presentation.feature.GenAiOptScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onClick: (String) -> Unit
) {
    val currUser = state.user
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currUser != null) "Hello, ${currUser.displayName}" else "Welcome to Form Filler",
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.display_small),
                    )
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent) // Set the background color to transparent
                    .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
            ) {
                HomeScreenBackground()
                GenAiOptScreen(
                    onGoClick = {
                        onClick(it)
                    })
            }
        }
    )
}