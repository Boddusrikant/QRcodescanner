package com.example.barcodescanner

const val ID_KEY = "ID_key"
const val TEXT_KEY = "TEXT_key"
sealed class DestinationScreen(val route: String) {
    object SplashScreenDest : DestinationScreen(route = "splash_screen")
    object MainScreenDest : DestinationScreen(route = "main_screen"+ "/{$ID_KEY}" + "/{$TEXT_KEY}"){
        fun getFullRoute(id: Int, text: String): String {
            return "main_screen" + "/$id" + "/$text"
        }
    }
    object ScannerScreenDest : DestinationScreen(route = "scanner_screen")
    object LoginScreenDest : DestinationScreen(route = "login_screen")
    object SignupScreenDest : DestinationScreen(route = "signup_screen")
}