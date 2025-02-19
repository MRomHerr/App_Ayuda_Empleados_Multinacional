package com.example.ayudaempleadosmultinacional.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_ayuda_empleados_multinacional.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class CityInfo(
    val id: Int,
    val city: String,
    val country: String,
    val timeZone: String
)

val cities = listOf(
    CityInfo(1, "Madrid", "España", "Europe/Madrid"),
    CityInfo(2, "París", "Francia", "Europe/Paris"),
    CityInfo(3, "Londres", "Reino Unido", "Europe/London"),
    CityInfo(4, "Porto Alegre", "Brasil", "America/Sao_Paulo"),
    CityInfo(5, "Acapulco", "México", "America/Mexico_City"),
    CityInfo(6, "Vancouver", "Canadá", "America/Vancouver"),
    CityInfo(7, "Houston", "Estados Unidos", "America/Chicago"),
    CityInfo(8, "Casablanca", "Marruecos", "Africa/Casablanca"),
    CityInfo(9, "Osaka", "Japón", "Asia/Tokyo"),
    CityInfo(10, "Melbourne", "Australia", "Australia/Melbourne"),
    CityInfo(11, "Ankara", "Turquía", "Europe/Istanbul"),
    CityInfo(12, "Dubai", "Emiratos Árabes Unidos", "Asia/Dubai")
)

class HoursViewModel : ViewModel() {
    private val _selectedCity = MutableStateFlow(cities[0])
    val selectedCity = _selectedCity.asStateFlow()

    private val _currentTime = MutableStateFlow(ZonedDateTime.now(ZoneId.of(cities[0].timeZone)))
    val currentTime = _currentTime.asStateFlow()

    fun updateSelectedCity(city: CityInfo) {
        _selectedCity.value = city
        _currentTime.value = ZonedDateTime.now(ZoneId.of(city.timeZone))
    }

    fun updateTime(hour: Int, minute: Int) {
        _currentTime.value = _currentTime.value.withHour(hour).withMinute(minute)
    }
}

@Composable
fun HoursScreen(viewModel: HoursViewModel = viewModel()) {
    val selectedCity by viewModel.selectedCity.collectAsState()
    val currentTime by viewModel.currentTime.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Horas en las Ciudades", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        CitySelector(cities, selectedCity) { viewModel.updateSelectedCity(it) }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.termometro),
            contentDescription = "Plano de ${selectedCity.city}",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("${selectedCity.city}, ${selectedCity.country}",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally))

        Text(
            currentTime.format(DateTimeFormatter.ofPattern("HH:mm")),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TimeInput(currentTime) { hour, minute -> viewModel.updateTime(hour, minute) }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(cities) { city ->
                if (city != selectedCity) {
                    CityTimeDisplay(city, currentTime)
                }
            }
        }
    }
}

@Composable
fun CitySelector(cities: List<CityInfo>, selectedCity: CityInfo, onCitySelected: (CityInfo) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(cities) { city ->
            Button(
                onClick = { onCitySelected(city) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD1D126),
                    contentColor = Color.Black
                )
            ) {
                Text(city.city)
            }
        }
    }
}


@Composable
fun TimeInput(currentTime: ZonedDateTime, onTimeChanged: (Int, Int) -> Unit) {
    var hours by remember { mutableStateOf(currentTime.hour) }
    var minutes by remember { mutableStateOf(currentTime.minute) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Hora: ")
        IconButton(onClick = { hours = (hours - 1 + 24) % 24; onTimeChanged(hours, minutes) }) {
            Text("-")
        }
        Text(hours.toString().padStart(2, '0'))
        IconButton(onClick = { hours = (hours + 1) % 24; onTimeChanged(hours, minutes) }) {
            Text("+")
        }
        Text(":")
        IconButton(onClick = { minutes = (minutes - 1 + 60) % 60; onTimeChanged(hours, minutes) }) {
            Text("-")
        }
        Text(minutes.toString().padStart(2, '0'))
        IconButton(onClick = { minutes = (minutes + 1) % 60; onTimeChanged(hours, minutes) }) {
            Text("+")
        }
    }
}



@Composable
fun CityTimeDisplay(city: CityInfo, referenceTime: ZonedDateTime) {
    val cityTime = referenceTime.withZoneSameInstant(ZoneId.of(city.timeZone))
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.termometro),
            contentDescription = "Plano de ${city.city}",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(city.city, fontWeight = FontWeight.Bold)
            Text(city.country)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            cityTime.format(DateTimeFormatter.ofPattern("HH:mm")),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

