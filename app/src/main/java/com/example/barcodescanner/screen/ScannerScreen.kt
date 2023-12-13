package com.example.barcodescanner.screen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.barcodescanner.CameraPreview
import com.example.barcodescanner.DestinationScreen
import com.example.barcodescanner.MainActivity
import com.example.barcodescanner.MainScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder

@Composable
fun ScannerScreen(navController: NavHostController, mainActivity: MainActivity) {
    var code by remember {
        mutableStateOf("")
    }
    var hasReadCode by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

//    var hasCamPermission by remember {
//        mutableStateOf(
//            ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.CAMERA
//            ) == PackageManager.PERMISSION_GRANTED
//        )
//    }
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { granted ->
//            hasCamPermission = granted
//        }
//    )
//    LaunchedEffect(key1 = true) {
//
//
//            launcher.launch(Manifest.permission.CAMERA)
//
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (true) {
            if (hasReadCode) {
                val encodedData = URLEncoder.encode(code, "UTF-8")
                navController.navigate(DestinationScreen.MainScreenDest.route)
//                navController.navigate(
//                    route = DestinationScreen.MainScreenDest.getFullRoute(
//                        1,
//                        encodedData
//                    )
//                )

//                BackHandler {
//                    restartApp(mainActivity)
//                }
            } else {

                CameraPreview() { result ->
//                    code = result
//                    hasReadCode = true
                    Log.d("result qr ", result)
                    val encodedData = URLEncoder.encode(result, "UTF-8")

                    navController.navigate(
                        route = DestinationScreen.MainScreenDest.getFullRoute(
                            1,
                            encodedData
                        )
                    )

                }
            }
        }
    }

}

private fun restartApp(mainActivity: MainActivity) {

    val intent = Intent(mainActivity, MainActivity::class.java)
    mainActivity.startActivity(intent)
    mainActivity.finishAffinity()
}
