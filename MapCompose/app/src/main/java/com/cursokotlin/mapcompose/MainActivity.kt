
package com.cursokotlin.mapcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cursokotlin.mapcompose.ui.theme.MapComposeTheme
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapType.HYBRID
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import kotlinx.serialization.descriptors.StructureKind

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MapComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MyGoogleMaps()
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun MyGoogleMaps() {
    val marker= LatLng(28.270833, (-16.63916).toDouble())
    val properties by remember { mutableStateOf(MapProperties(mapType = HYBRID)) }
    val uiSettings by remember { MapUiSettings(zoomControlsEnabled = false ) }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings =
    ){
        Marker(position = marker, title = "EL TEIDE", snippet = "Mapa de android studio")
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MapComposeTheme {
        Greeting("Android")
    }
}
