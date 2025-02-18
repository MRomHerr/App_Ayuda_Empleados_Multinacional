package com.example.ayudaempleadosmultinacional.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_ayuda_empleados_multinacional.R

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun HoursScreen() {
    val cities = listOf(
        "Madrid", "París", "Londres", "Porto Alegre",
        "Acapulco", "Vancouver", "Houston", "Casablanca",
        "Osaka", "Melbourne", "Ankara", "Dubai"
    )
    var selectedCity by remember { mutableStateOf(cities.first()) }
    var selectedTime by remember { mutableStateOf("12:00") } // Default time

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Horas en las distintas ciudades", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de ciudad de referencia
        Text("Selecciona la ciudad de referencia:")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            cities.forEach { city ->
                Button(onClick = { selectedCity = city }) {
                    Text(city)
                }
            }
        }

        // Selector de hora (Simple TextField para demostración)
        OutlinedTextField(
            value = selectedTime,
            onValueChange = { selectedTime = it },
            label = { Text("Introduce la hora (HH:MM)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la hora en cada ciudad
        cities.forEach { city ->
            CityTimeDisplay(city = city, selectedCity = selectedCity, selectedTime = selectedTime)
        }
    }
}

@Composable
fun CityTimeDisplay(city: String, selectedCity: String, selectedTime: String) {
    val mapImage = when (city) {
        "Madrid" -> R.drawable.logo_empresa
        "París" -> R.drawable.logo_empresa
        "Londres" -> R.drawable.logo_empresa
        "Porto Alegre" -> R.drawable.logo_empresa
        "Acapulco" -> R.drawable.logo_empresa
        "Vancouver" -> R.drawable.logo_empresa
        "Houston" -> R.drawable.logo_empresa
        "Casablanca" -> R.drawable.logo_empresa
        "Osaka" -> R.drawable.logo_empresa
        "Melbourne" -> R.drawable.logo_empresa
        "Ankara" -> R.drawable.logo_empresa
        "Dubai" -> R.drawable.logo_empresa
        else -> R.drawable.logo_empresa // Default map
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Mapa del país
        Image(
            painter = painterResource(id = mapImage),
            contentDescription = "Mapa de $city",
            modifier = Modifier.size(100.dp)
        )

        // Nombre de la ciudad y hora (Placeholder)
        Column {
            Text(city)
            val cityTimeZone = when (city) {
                "Madrid" -> "Europe/Madrid"
                "París" -> "Europe/Paris"
                "Londres" -> "Europe/London"
                "Porto Alegre" -> "America/Sao_Paulo"
                "Acapulco" -> "America/Mexico_City"
                "Vancouver" -> "America/Vancouver"
                "Houston" -> "America/Chicago"
                "Casablanca" -> "Africa/Casablanca"
                "Osaka" -> "Asia/Tokyo"
                "Melbourne" -> "Australia/Melbourne"
                "Ankara" -> "Europe/Istanbul"
                "Dubai" -> "Asia/Dubai"
                else -> "Europe/Madrid"
            }

            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
            val currentTime = ZonedDateTime.now(ZoneId.of(cityTimeZone)).format(timeFormatter)
            Text("Hora: $currentTime")
        }
    }
}
