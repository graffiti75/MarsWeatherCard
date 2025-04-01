package com.cericatto.marsweathercard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cericatto.marsweathercard.ui.MarsShapeBackground

enum class MarsType {
	OLYMPUS,
	STORM
}

@Composable
fun MarsWeatherCardRoot(
	modifier: Modifier
) {
	Box(
		contentAlignment = Alignment.BottomStart,
		modifier = modifier
			.fillMaxSize()
			.background(Color(0xFF14171E))
	) {
		Image(
			painter = painterResource(id = R.drawable.mars_surface),
			contentDescription = "Mars image"
		)
		Box(
			contentAlignment = Alignment.Center,
			modifier = modifier
				.wrapContentWidth()
				.fillMaxHeight()
				.padding(horizontal = 60.dp)
		) {
			MarsWeatherCard(modifier = modifier)
		}
	}
}

@Composable
fun MarsWeatherCard(
	modifier: Modifier
) {
	val configuration = LocalConfiguration.current
	val screenHeight = (configuration.screenHeightDp * 0.5).dp
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.Start,
		modifier = modifier
			.height(screenHeight)
			.wrapContentWidth()
			.background(MarsShapeBackground())
			.padding(8.dp)
	) {
		IconText(
			type = MarsType.OLYMPUS,
			modifier = Modifier.padding(bottom = 100.dp)
		)
		IconText(
			type = MarsType.STORM
		)
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			TextUppercase(modifier = Modifier)
			TemperatureColumn(modifier = Modifier)
		}
		MarsCard(
			modifier = Modifier
		)
	}
}

@Composable
private fun IconText(
	modifier: Modifier = Modifier,
	type: MarsType
) {
	val icon = when (type) {
		MarsType.OLYMPUS -> R.drawable.pin
		MarsType.STORM -> R.drawable.wind
	}
	val text = when (type) {
		MarsType.OLYMPUS -> "Olympus Mons"
		MarsType.STORM -> "Dust Storm"
	}
	val textColor = when (type) {
		MarsType.OLYMPUS -> Color(0xFF9E83C5)
		MarsType.STORM -> Color(0xFFCD533C)
	}
	val contentDescription = when (type) {
		MarsType.OLYMPUS -> "Pin icon"
		MarsType.STORM -> "Wind icon"
	}
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start,
		modifier = modifier.fillMaxWidth()
	) {
		Icon(
			painter = painterResource(icon),
			contentDescription = contentDescription,
			modifier = Modifier.size(15.dp),
			tint = textColor
		)
		Text(
			text = text,
			style = TextStyle(
				color = textColor
			),
			modifier = Modifier.padding(start = 10.dp)
		)
	}
}

@Composable
private fun RowScope.TextUppercase(
	modifier: Modifier = Modifier,
	temperature: String = "-63"
) {
	Row(
		verticalAlignment = Alignment.Top,
		horizontalArrangement = Arrangement.Start,
		modifier = modifier
			.fillMaxWidth()
			.weight(1f)
	) {
		Text(
			text = temperature,
			style = TextStyle(
				fontSize = 50.sp,
				fontWeight = FontWeight.Medium,
				color = Color(0xFF14171E)
			),
			modifier = Modifier.padding(start = 10.dp)
		)
		Text(
			text = "ºC",
			style = TextStyle(
				fontSize = 20.sp,
				color = Color(0xFF14171E)
			),
			modifier = Modifier.padding(start = 5.dp, top = 5.dp)
		)
	}
}

@Composable
private fun RowScope.TemperatureColumn(
	modifier: Modifier = Modifier,
	high: String = "H:-52ºC",
	low: String = "H:-73ºC"
) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.End,
		modifier = modifier
			.fillMaxWidth()
			.weight(1f)
			.padding(end = 15.dp)
	) {
		Text(
			text = high,
			style = TextStyle(
				fontSize = 12.sp,
				color = Color(0xFF14171E)
			),
			modifier = Modifier.padding(start = 10.dp)
		)
		Text(
			text = low,
			style = TextStyle(
				fontSize = 12.sp,
				color = Color(0xFF14171E)
			),
			modifier = Modifier.padding(start = 10.dp, top = 5.dp)
		)
	}
}

@Composable
private fun MarsCard(
	modifier: Modifier = Modifier
) {
	val textList = listOf(
		"Wind speed" to "27Km/h NW",
		"Pressure" to "600 Pa",
		"UV Radiation" to "0.5 mSv/day",
		"Martian date" to "914 Sol",
	)
	LazyVerticalGrid(
		columns = GridCells.Fixed(2), // Sets 2 columns
		modifier = Modifier
			.padding(top = 20.dp)
			.fillMaxSize()
			.padding(3.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp),
		horizontalArrangement = Arrangement.spacedBy(10.dp)
	) {
		items(textList) { (title, subtitle) ->
			MarsSmallCard(
				title = title,
				subtitle = subtitle
			)
		}
	}
}

@Composable
private fun MarsSmallCard(
	modifier: Modifier = Modifier,
	title: String = "Wind speed",
	subtitle: String = "27Km/h NW"
) {
	Column(
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.Start,
		modifier = modifier
			.fillMaxWidth()
			.background(Color(0xFFF9E8E5))
			.padding(8.dp)
	) {
		Text(
			text = title,
			style = TextStyle(
				fontSize = 13.sp,
				fontWeight = FontWeight.Medium,
				color = Color(0xFFCD533C)
			)
		)
		Text(
			text = subtitle,
			style = TextStyle(
				fontSize = 16.sp,
				fontWeight = FontWeight.Medium,
				color = Color(0xFF14171E)
			),
			modifier = Modifier.padding(top = 5.dp)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun MarsWeatherCardRootPreview() {
	MarsWeatherCardRoot(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun MarsWeatherCardPreview() {
	MarsWeatherCard(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun MarsSmallCardPreview() {
	MarsSmallCard(modifier = Modifier)
}