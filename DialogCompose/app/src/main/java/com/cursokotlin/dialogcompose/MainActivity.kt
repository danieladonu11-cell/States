package com.cursokotlin.dialogcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cursokotlin.dialogcompose.ui.theme.DialogComposeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogComposeTheme {
                Scaffold( modifier = Modifier.fillMaxSize()
                ) {
                    var show by rememberSaveable {mutableSetOf(false) }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Button(onClick = { }){
                            Text(text = "Dialogo")
                        }
                    }
                    MyDialog()
                }
            }
        }
    }
}
@Composable
fun MyDialog(show: Boolean){
    if (show) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {}) {
                    Text(text = "ConfirmButton")
                }
            },
            dismissButton = {
                TextButton(onClick = {}) {
                    Text(text = "ConfirmButton")
                }
            },
            title = { Text(text = "Daniela Doñu") },
            text = { Text(text = "Body de dialogo") })
    }
}










@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DialogComposeTheme {
        Greeting()
    }
}

@Composable
fun Greeting() {
    TODO("Not yet implemented")
}