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
fun HomeScreenBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Draw the background gradient
        val gradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB3E5FC), // Light Blue
                Color(0xFF1565C0), // Navy Blue
            ),
            start = Offset(0f, 0f),
            end = Offset(canvasWidth, canvasHeight)
        )
        drawRect(
            brush = gradient,
            size = Size(canvasWidth, canvasHeight)
        )

        // Draw the diagonal lines
        val linePath = Path().apply {
            moveTo(0f, 0f)
            for (i in 0..canvasHeight.toInt() step 40) {
                val x = (canvasWidth * i / canvasHeight).toFloat()
                val y = i.toFloat()
                lineTo(x, y)
            }
        }
        drawPath(
            path = linePath,
            color = Color.White.copy(alpha = 0.3f), // Semi-transparent white
            style = Stroke(width = 2f)
        )

        // Draw the circular shape
        drawCircle(
            color = Color.White.copy(alpha = 0.2f), // Semi-transparent white
            radius = canvasWidth * 0.25f,
            center = Offset(canvasWidth * 0.7f, canvasHeight * 0.25f)
        )
    }
}
