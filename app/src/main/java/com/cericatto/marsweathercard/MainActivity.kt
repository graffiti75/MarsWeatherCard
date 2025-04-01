package com.cericatto.marsweathercard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.cericatto.marsweathercard.ui.theme.MarsWeatherCardTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MarsWeatherCardTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					MarsWeatherCardRoot(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}