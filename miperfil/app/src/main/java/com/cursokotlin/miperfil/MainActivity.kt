
package com.cursokotlin.miperfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().apply {
            userAgentValue = "MiPerfilAndroid/1.0 (com.cursokotlin.miperfil)"
        }

        setContent {
            MaterialTheme {
                MiPerfilApp()
            }
        }
    }
}

@Composable
fun MiPerfilApp() {

    var mostrarMenu by remember { mutableStateOf(false) }

    val morado = Color(0xFF6750A4)
    val rojo = Color(0xFFE53935)
    val panel = Color.Black.copy(alpha = 0.62f)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // FONDO COMPLETO
        Image(
            painter = painterResource(R.drawable.fondo),
            contentDescription = "Fondo de pantalla",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // CONTENIDO PRINCIPAL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 110.dp)
        ) {

            // PORTADA
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(205.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.Black.copy(alpha = 0.22f))
                )

                Text(
                    text = "MI PERFIL",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 22.dp),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                // LINEA A LA DERECHA DE LA FOTO
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 134.dp, top = 145.dp)
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.White)
                )

                // FOTO DE PERFIL
                Image(
                    painter = painterResource(R.drawable.perfil),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 22.dp, top = 90.dp)
                        .size(92.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            // NOMBRE Y CORREO
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(panel)
                    .padding(14.dp)
            ) {

                Text(
                    text = "Daniela Doñu Bolteada",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "donudaniela250@gmail.com",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ABOUT Y LENGUAJES
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(panel)
                    .padding(14.dp)
            ) {

                Text(
                    text = "",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Hola, soy estudiante de la UPP y me interesa la tecnología, " +
                            "la programación . " +
                            "Soy tecnico en programación y me gusta aprender más para mejorar.",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 21.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                // LENGUAJES
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    val lenguajes = listOf(
                        "HTML",
                        "CSS",
                        "Java",
                        "Kotlin",
                        "C++",
                        "PHP",
                        "JavaScript",
                        "SQL",
                        "Python",
                        "Laravel"
                    )

                    lenguajes.forEach { lenguaje ->

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 10.dp
                                )
                        ) {

                            Text(
                                text = lenguaje,
                                color = morado,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // UBICACION Y MAPA
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(panel)
                    .padding(14.dp)
            ) {

                Text(
                    text = "Mi ubicación",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    MapaOpenStreetMap(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // NAVBAR INFERIOR
        if (mostrarMenu) {

            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 60.dp
                    ),
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text("Inicio", color = morado)
                    Text("Perfil", color = morado)
                    Text("About", color = morado)
                    Text("Lenguajes", color = morado)
                    Text("Mapa", color = morado)
                }
            }
        }

        // BOTON PARA MOSTRAR Y OCULTAR NAVBAR
        FloatingActionButton(
            onClick = {
                mostrarMenu = !mostrarMenu
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(42.dp),
            containerColor = rojo,
            contentColor = Color.White,
            shape = CircleShape
        ) {

            Text(
                text = if (mostrarMenu) "−" else "+",
                fontSize = 25.sp
            )
        }
    }
}

// MAPA REAL CON OPENSTREETMAP Y OSMdroid
@Composable
fun MapaOpenStreetMap(
    modifier: Modifier = Modifier
) {

    if (LocalInspectionMode.current) {

        Box(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE8E6D9)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("📍", fontSize = 35.sp)

                Text(
                    text = "Pachuca de Soto, Hidalgo",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Vista previa del mapa",
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
            }
        }

    } else {

        AndroidView(
            modifier = modifier,

            factory = { context ->

                MapView(context).apply {

                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)

                    val ubicacion = GeoPoint(20.1011, -98.7591)

                    controller.setZoom(15.0)
                    controller.setCenter(ubicacion)

                    val marcador = Marker(this).apply {
                        position = ubicacion
                        title = "Mi ubicación - Pachuca"

                        setAnchor(
                            Marker.ANCHOR_CENTER,
                            Marker.ANCHOR_BOTTOM
                        )
                    }

                    overlays.add(marcador)
                }
            },

            update = { mapa ->
                mapa.invalidate()
            }
        )
    }
}

// VISTA PREVIA
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun VistaPrevia() {
    MiPerfilApp()
}
