package com.example.barcodescanner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.barcodescanner.ui.theme.BarCodeScannerTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BarCodeScannerTheme {

                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // Pass any permission you want while launching
                    requestLocationPermission(this)

                }

                NavigationScreen(mainActivity = this)
            }
        }
    }

    fun requestLocationPermission(mainActivity: MainActivity) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            1
        )
    }
}


