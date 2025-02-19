package com.example.ayudaempleadosmultinacional.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_ayuda_empleados_multinacional.R
import com.example.ayudaempleadosmultinacional.ViewModels.TemperaturaViewModel

/**
 * Pantalla principal para la funcionalidad de temperatura.
 * Muestra la temperatura actual, permite cambiar entre Celsius y Fahrenheit,
 * ajustar la temperatura y guardar temperaturas.
 */
@Composable
fun PantallaTemperatura(modeloVista: TemperaturaViewModel) {
    val temperatura by modeloVista.temperatura.collectAsState()
    val esCelsius by modeloVista.esCelsius.collectAsState()
    val listaTemperaturas by modeloVista.listaTemperaturas.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Conversión de Temperatura", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        ImagenTemperatura(celsius = temperatura.toInt())
        MostrarTemperatura(celsius = temperatura.toInt(), esCelsius = esCelsius)
        UnidadTemperatura(esCelsius = esCelsius, alCambiar = modeloVista::cambiarUnidadTemperatura)
        DeslizadorTemperatura(valor = temperatura, alCambiarValor = modeloVista::actualizarTemperatura)
        BotonTemperatura(alGuardar = modeloVista::guardarTemperatura)
        ListaTemperaturas(listaTemperaturas = listaTemperaturas)
    }
}

/**
 * Botón para cambiar entre unidades de temperatura (Celsius/Fahrenheit).
 */
@Composable
fun UnidadTemperatura(esCelsius: Boolean, alCambiar: () -> Unit) {
    Button(onClick = alCambiar, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1D126))) {
        Text(if (esCelsius) "Mostrar en Fahrenheit" else "Mostrar en Celsius")
    }
}

/**
 * Deslizador para ajustar la temperatura.
 */
@Composable
fun DeslizadorTemperatura(valor: Float, alCambiarValor: (Float) -> Unit) {
    Slider(
        value = valor,
        onValueChange = alCambiarValor,
        valueRange = -30f..55f,
        steps = 84
    )
}

/**
 * Muestra la temperatura actual en la unidad seleccionada.
 */
@Composable
fun MostrarTemperatura(celsius: Int, esCelsius: Boolean) {
    val fahrenheit = (celsius * 9 / 5) + 32
    Text(text = if (esCelsius) "$celsius ºC" else "$fahrenheit ºF", fontSize = 32.sp)
}

/**
 * Muestra la imagen del termómetro.
 */
@Composable
fun ImagenTemperatura(celsius: Int) {
    Image(
        painter = painterResource(id = R.drawable.termometro),
        contentDescription = "imagen termometro",
        modifier = Modifier.size(100.dp)
    )
}

/**
 * Botón para guardar la temperatura actual.
 */
@Composable
fun BotonTemperatura(alGuardar: () -> Unit) {
    Button(
        onClick = alGuardar,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1D126))
    ) {
        Text("Guardar Temperatura")
    }
}

/**
 * Lista de temperaturas guardadas.
 */
@Composable
fun ListaTemperaturas(listaTemperaturas: List<Pair<Int, Int>>) {
    LazyColumn {
        items(listaTemperaturas) { (celsius, fahrenheit) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = obtenerIconoTemperatura(celsius)),
                    contentDescription = "Icono temperatura",
                    modifier = Modifier.size(70.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("$celsius ºC - $fahrenheit ºF", fontSize = 18.sp)
            }
        }
    }
}

/**
 * Determina el icono a mostrar basado en la temperatura.
 */
fun obtenerIconoTemperatura(celsius: Int): Int {
    return when {
        celsius <= 12 -> R.drawable.frio
        celsius in 13..25 -> R.drawable.temperatura_media
        else -> R.drawable.caliente
    }
}
