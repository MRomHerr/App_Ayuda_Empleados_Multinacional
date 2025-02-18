package com.example.ayudaempleadosmultinacional.Screens

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

        // Mostrar la imagen de la temperatura
        TemperatureImage(celsius = temperature.toInt())

        // Mostrar la temperatura actual
        DisplayTemperature(celsius = temperature.toInt(), isCelsius = isCelsius)

        // Botón para alternar entre Celsius y Fahrenheit
        TemperatureUnit(isCelsius = isCelsius, onToggle = viewModel::toggleTemperatureUnit)

        // Slider para ajustar la temperatura
        TemperatureSlider(value = temperature, onValueChange = viewModel::updateTemperature)

        // Botón para guardar la temperatura
        TemperatureButton(onSave = viewModel::saveTemperature)

        // Mostrar la lista de temperaturas guardadas
        TemperatureList(temperatureList = temperatureList)

    }
}

@Composable
fun TemperatureUnit(isCelsius: Boolean, onToggle: () -> Unit) {
    Button(onClick = onToggle, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1D126))) {
        Text(if (isCelsius) "Mostrar en Fahrenheit" else "Mostrar en Celsius")
    }
}

@Composable
fun TemperatureSlider(value: Float, onValueChange: (Float) -> Unit) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = -30f..55f,
        steps = 84
    )
}

@Composable
fun DisplayTemperature(celsius: Int, isCelsius: Boolean) {
    val fahrenheit = (celsius * 9 / 5) + 32
    Text(text = if (isCelsius) "$celsius ºC" else "$fahrenheit ºF", fontSize = 32.sp)
}

@Composable
fun TemperatureImage(celsius: Int) {
    Image(
        painter = painterResource(id = R.drawable.termometro), // Replace with your image resource
        contentDescription = "imagen termometro",
        modifier = Modifier.size(100.dp)
    )
}

@Composable
fun TemperatureButton(onSave: () -> Unit) {
    Button(
        onClick = onSave,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1D126))
    ) {
        Text("Guardar Temperatura")
    }
}

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

fun getTemperatureIcon(celsius: Int): Int {
    return when {
        celsius <= 12 -> R.drawable.frio
        celsius in 13..25 -> R.drawable.temperatura_media
        else -> R.drawable.caliente
    }
}
