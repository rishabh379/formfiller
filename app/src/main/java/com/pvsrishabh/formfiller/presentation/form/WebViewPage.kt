package com.pvsrishabh.formfiller.presentation.form

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pvsrishabh.formfiller.R

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPage(
    url: String
) {

    // dialog screen

    val loaderDialogScreen = remember {
        mutableStateOf(false)
    }
    if(loaderDialogScreen.value){
        Dialog(
            onDismissRequest = { loaderDialogScreen.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false) // experimental
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center){
                Text(text = "Loading...", color = colorResource(id = R.color.body))
            }
        }
    }

    // android view

    var backEnabled by remember {
        mutableStateOf(false)
    }
    var webView: WebView? = null

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            webViewClient = WebViewClient()

            // to enable js
            settings.javaScriptEnabled = true

            webViewClient = object: WebViewClient(){
                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                    // show dialog
                    loaderDialogScreen.value = true
                    backEnabled = view.canGoBack()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    //hide dialog
                    loaderDialogScreen.value = false
                }
            }

            loadUrl(url)
            webView = this
        }
    }, update = {
        webView = it
    })

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}