package com.example.barcodescanner

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, id: String?, text: String?) {
    val context = LocalContext.current
    var decodeTxt by remember { mutableStateOf("") }
    if (text != null) {
        val qrCodeStr = URLDecoder.decode(text, "UTF-8")
        decodeTxt = qrCodeStr
    }

    Column(

    ) {
        TopAppBar(
            title = {
                Text(text = "QR Code ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            },
            navigationIcon = {
                IconButton(onClick = {

                    navController.navigate(route = DestinationScreen.ScannerScreenDest.route) {
                        popUpTo(route = DestinationScreen.MainScreenDest.route) {
                            inclusive = true
                        }
                    }

                }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.White)

                }
            },

            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFF565899),
                titleContentColor = Color.White,
            )
        )


        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Qr Code Data",
                style = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            decodeTxt?.let {
                BasicTextField(
                    value = it,
                    onValueChange = { newText -> decodeTxt = newText },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // Handle keyboard done action
                        }
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5F5))
                        .padding(16.dp)
                )
            }

            Row (horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()

            ) {
                Button(onClick = {
                    val myClipboard =
                        ContextCompat.getSystemService(context, ClipboardManager::class.java)
                    val myClip: ClipData = ClipData.newPlainText("Label", decodeTxt)
                    if (myClipboard != null) {
                        myClipboard.setPrimaryClip(myClip)
                    }


                }
                    ,
                    colors = ButtonDefaults.buttonColors(Color(0xFF03A9F4))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_content_copy_24),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "COPY TEXT")

                }

                Button(onClick = {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, decodeTxt);
                    context.startActivity(Intent.createChooser(shareIntent, "Send to"))
                }
                    ,   colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_share_24),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "SHARE TEXT")

                }
            }

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PrvButton() {
    Row (horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {



        },
            colors = ButtonDefaults.buttonColors(Color(0xFF03A9F4))

        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_content_copy_24),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "COPY TEXT")

        }

        Button(onClick = {

        }
,   colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71))
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_share_24),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "SHARE TEXT")

        }
    }
}

