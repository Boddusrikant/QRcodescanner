package com.example.barcodescanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.barcodescanner.ui.theme.BarCodeScannerTheme
import com.example.studentmanagement.HomeScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BarCodeScannerTheme {

                val splashDuration = 2000L // 2 seconds
                var showSplash by remember { mutableStateOf(true) }
                LaunchedEffect(Unit) {
                    delay(splashDuration)
                    showSplash = false
                }
                if (showSplash) {
                    SplashScreen()

                } else {
                    HomeScreen( )
                }
            }
        }
    }
}

