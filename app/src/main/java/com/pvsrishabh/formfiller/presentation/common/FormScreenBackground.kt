package com.pvsrishabh.formfiller.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.sin

@Composable
fun FormScreenBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Draw the background gradient
        val gradient = Brush.radialGradient(
            colors = listOf(
                Color(0xFFFFEB3B), // Yellow
                Color(0xFFFFC107), // Amber
                Color(0xFFFF9800), // Orange
            ),
            center = Offset(canvasWidth * 0.5f, canvasHeight * 0.3f),
            radius = canvasWidth * 0.8f
        )
        drawRect(
            brush = gradient,
            size = Size(canvasWidth, canvasHeight)
        )

        // Draw the wavy lines
        val wavePath = Path().apply {
            moveTo(0f, canvasHeight * 0.7f)
            for (i in 0..canvasWidth.toInt() step 50) {
                val x = i.toFloat()
                val y = canvasHeight * 0.7f + (sin(x / 100) * 80).toFloat()
                lineTo(x, y)
            }
        }
        drawPath(
            path = wavePath,
            color = Color.White.copy(alpha = 0.4f), // Semi-transparent white
            style = Stroke(width = 6f)
        )

        // Draw the circular shapes
        drawCircle(
            color = Color.White.copy(alpha = 0.2f), // Semi-transparent white
            radius = canvasWidth * 0.2f,
            center = Offset(canvasWidth * 0.25f, canvasHeight * 0.2f)
        )
        drawCircle(
            color = Color.White.copy(alpha = 0.2f), // Semi-transparent white
            radius = canvasWidth * 0.15f,
            center = Offset(canvasWidth * 0.75f, canvasHeight * 0.8f)
        )
    }
}