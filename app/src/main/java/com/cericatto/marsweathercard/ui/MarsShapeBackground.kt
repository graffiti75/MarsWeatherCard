package com.cericatto.marsweathercard.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview

fun MarsShapeBackground(
	color: Color = Color.White
): Brush {
	return object : ShaderBrush() {
		override fun createShader(size: Size): Shader {
			val path = createHexagonPath(size)
			val bitmap = ImageBitmap(size.width.toInt(), size.height.toInt())
			val canvas = androidx.compose.ui.graphics.Canvas(bitmap)

			// Fill the hexagonal path with the desired color
			canvas.drawPath(path, Paint().apply { this.color = color })
			return ImageShader(bitmap, TileMode.Clamp)
		}
	}
}

private fun createHexagonPath(size: Size): Path {
	val width = size.width
	val height = size.height
	val path = Path()

	val quarterWidth = width / 10
	val threeQuarterWidth = 9 * quarterWidth
	val quarterHeight = height / 10

	path.moveTo(0f, 0f)
	path.lineTo(threeQuarterWidth, 0f)
	path.lineTo(width, quarterHeight)
	path.lineTo(width, height)
	path.lineTo(0f, height)

	path.close()

	return path
}

@Preview
@Composable
private fun MarsShapeBackgroundPreview() {
	MarsShapeBackground()
}