package com.example.ayudaempleadosmultinacional.Screens

import android.os.Build
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
import com.example.ayudaempleadosmultinacional.ViewModels.TemperatureViewModel

/**
 * Pantalla principal para la funcionalidad de temperatura.
 * Muestra la temperatura actual, permite cambiar entre Celsius y Fahrenheit,
 * ajustar la temperatura y guardar temperaturas.
 */
@Composable
fun TemperatureScreen(viewModel: TemperatureViewModel) {
    val temperature by viewModel.temperature.collectAsState()
    val isCelsius by viewModel.isCelsius.collectAsState()
    val temperatureList by viewModel.temperatureList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Conversión de Temperatura", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TemperatureImage(celsius = temperature.toInt())
        DisplayTemperature(celsius = temperature.toInt(), isCelsius = isCelsius)
        TemperatureUnit(isCelsius = isCelsius, onToggle = viewModel::toggleTemperatureUnit)
        TemperatureSlider(value = temperature, onValueChange = viewModel::updateTemperature)
        TemperatureButton(onSave = viewModel::saveTemperature)
        TemperatureList(temperatureList = temperatureList)
    }
}

/**
 * Botón para cambiar entre unidades de temperatura (Celsius/Fahrenheit).
 */
@Composable
fun TemperatureUnit(isCelsius: Boolean, onToggle: () -> Unit) {
    Button(onClick = onToggle, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1D126))) {
        Text(if (isCelsius) "Mostrar en Fahrenheit" else "Mostrar en Celsius")
    }
}

/**
 * Slider para ajustar la temperatura.
 */
@Composable
fun TemperatureSlider(value: Float, onValueChange: (Float) -> Unit) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = -30f..55f,
        steps = 84
    )
}

/**
 * Muestra la temperatura actual en la unidad seleccionada.
 */
@Composable
fun DisplayTemperature(celsius: Int, isCelsius: Boolean) {
    val fahrenheit = (celsius * 9 / 5) + 32
    Text(text = if (isCelsius) "$celsius ºC" else "$fahrenheit ºF", fontSize = 32.sp)
}

/**
 * Muestra la imagen del termómetro.
 */
@Composable
fun TemperatureImage(celsius: Int) {
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
fun TemperatureButton(onSave: () -> Unit) {
    Button(
        onClick = onSave,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1D126))
    ) {
        Text("Guardar Temperatura")
    }
}

/**
 * Lista de temperaturas guardadas.
 */
@Composable
fun TemperatureList(temperatureList: List<Pair<Int, Int>>) {
    LazyColumn {
        items(temperatureList) { (celsius, fahrenheit) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = getTemperatureIcon(celsius)),
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
fun getTemperatureIcon(celsius: Int): Int {
    return when {
        celsius <= 12 -> R.drawable.frio
        celsius in 13..25 -> R.drawable.temperatura_media
        else -> R.drawable.caliente
    }
}