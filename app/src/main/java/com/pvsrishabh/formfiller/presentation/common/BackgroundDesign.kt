package com.pvsrishabh.formfiller.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun BackgroundDesign(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Draw the top curved shape
        drawPath(
            path = Path().apply {
                moveTo(0f, 0f)
                cubicTo(
                    0f, 0f,
                    canvasWidth * 0.4f, -canvasHeight * 0.2f,
                    canvasWidth, 0f
                )
                lineTo(canvasWidth, canvasHeight * 0.4f)
                quadraticBezierTo(
                    canvasWidth * 0.8f, canvasHeight * 0.6f,
                    canvasWidth * 0.45f, canvasHeight * 0.6f
                )
                quadraticBezierTo(
                    canvasWidth * 0.2f, canvasHeight * 0.6f,
                    0f, canvasHeight * 0.7f
                )
                close()
            },
            color = Color(0xFF3F51B5)
        )

        // Draw the bottom curved shape
        drawPath(
            path = Path().apply {
                moveTo(0f, canvasHeight)
                quadraticBezierTo(
                    canvasWidth * 0.2f, canvasHeight * 0.8f,
                    canvasWidth * 0.5f, canvasHeight * 0.8f
                )
                quadraticBezierTo(
                    canvasWidth * 0.8f, canvasHeight * 0.8f,
                    canvasWidth, canvasHeight * 0.6f
                )
                lineTo(canvasWidth, canvasHeight)
                close()
            },
            color = Color(0xFF00ACC1)
        )
    }
}