package com.pvsrishabh.formfiller.presentation.form

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pvsrishabh.formfiller.R
import com.pvsrishabh.formfiller.presentation.nav_drawer.CustomDrawer
import com.pvsrishabh.formfiller.presentation.nav_drawer.CustomDrawerState
import com.pvsrishabh.formfiller.presentation.nav_drawer.MenuItem
import com.pvsrishabh.formfiller.presentation.nav_drawer.isOpened
import com.pvsrishabh.formfiller.presentation.nav_drawer.opposite
import com.pvsrishabh.formfiller.ui.theme.FormFillerTheme
import com.pvsrishabh.formfiller.util.coloredShadow
import kotlin.math.roundToInt

@Composable
fun FormScreen(text: String) {

    var drawerState by remember { mutableStateOf(CustomDrawerState.Closed) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 4.5).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f,
        label = "Animated Scale"
    )

    val viewModel = viewModel<FormViewModel>()
    LaunchedEffect(text) {
        // Call getMenuItemsFromUrl from ViewModel
        viewModel.getMenuItemsFromUrl(text)
    }
    // Collect the menu items as state
    val menuItems by viewModel.menuItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = CustomDrawerState.Closed
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
//        FormScreenBackground()
        CustomDrawer(
            items = menuItems,
            onCloseClick = { drawerState = CustomDrawerState.Closed },
            isLoading = isLoading
        )
        MainContent(
            modifier = Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                .coloredShadow(
                    color = Color.Black,
                    alpha = 0.1f,
                    shadowRadius = 50.dp
                ),
            drawerState = drawerState,
            onDrawerClick = { drawerState = it },
            url = text
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    drawerState: CustomDrawerState,
    onDrawerClick: (CustomDrawerState) -> Unit,
    url: String
) {
    Scaffold(
        modifier = modifier
            .clickable(enabled = drawerState == CustomDrawerState.Opened) {
                onDrawerClick(CustomDrawerState.Closed)
            },
        topBar = {

            CustomTopBar(url = url, onClick = { onDrawerClick(drawerState.opposite()) })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            contentAlignment = Alignment.Center
        ) {
            WebViewPage(url = url)
        }
    }
}

@Preview
@Composable
fun FormScreenPreview(){
    FormFillerTheme {
        FormScreen(text = "https://www.google.com")
    }
}