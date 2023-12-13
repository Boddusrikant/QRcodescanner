package com.example.barcodescanner

import android.content.pm.PackageManager
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.Manifest.permission
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import android.Manifest
import androidx.core.app.ActivityCompat


@Composable
fun SplashScreen(navController: NavController) {

    val scaleAnimation: Animatable<Float, AnimationVector1D> = remember { Animatable(initialValue = 0f) }

    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        navController = navController,
        durationMillisAnimation = 1500,
        delayScreen = 3000L
    )

    DesignSplashScreen(
        imagePainter = painterResource(id = R.drawable.barcode_scanner),
        scaleAnimation = scaleAnimation
    )
}

@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    navController: NavController,
    durationMillisAnimation: Int,
    delayScreen: Long
) {
    val context= LocalContext.current
    val authHelper = FirebaseAuth.getInstance()
    val isLoggedIn by remember { derivedStateOf { authHelper.getCurrentUser() != null } }

    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 0.5F,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )

        delay(timeMillis = delayScreen)


        if (isLoggedIn) {
            navController.navigate(DestinationScreen.ScannerScreenDest.route) {
                popUpTo(route = DestinationScreen.SplashScreenDest.route) {
                    inclusive = true
                }
            }
        } else {

            navController.navigate(route = DestinationScreen.LoginScreenDest.route) {
                popUpTo(route = DestinationScreen.SplashScreenDest.route) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun DesignSplashScreen(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
    scaleAnimation: Animatable<Float, AnimationVector1D>
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(228, 228, 228, 255),
                        Color(227, 218, 230, 255),
                        Color(224, 225, 228, 255),
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = imagePainter,
                contentDescription = "Qr Splash Screen",
                modifier = modifier
                    .size(400.dp)
                    .scale(scale = scaleAnimation.value),
            )

        }
    }
}