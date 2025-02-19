package com.example.ayudaempleadosmultinacional.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ayudaempleadosmultinacional.Screens.CityInfo
import com.example.ayudaempleadosmultinacional.Screens.cities
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlinx.coroutines.delay

class HoursViewModel : ViewModel() {
    // MutableStateFlow para la ciudad seleccionada, inicializada con la primera ciudad de la lista
    private val _selectedCity = MutableStateFlow(cities[0])
    // StateFlow público para observar la ciudad seleccionada
    val selectedCity = _selectedCity.asStateFlow()

    // MutableStateFlow para la hora actual, inicializada con la hora de la primera ciudad
    private val _currentTime = MutableStateFlow(ZonedDateTime.now(ZoneId.of(cities[0].timeZone)))
    // StateFlow público para observar la hora actual
    val currentTime = _currentTime.asStateFlow()

    init {
        // Inicia la actualización periódica del tiempo al crear el ViewModel
        updateTimePeriodically()
    }

    // Función para actualizar la ciudad seleccionada
    fun updateSelectedCity(city: CityInfo) {
        _selectedCity.value = city
        // Actualiza la hora actual al cambiar de ciudad
        _currentTime.value = ZonedDateTime.now(ZoneId.of(city.timeZone))
    }

    // Función privada para actualizar el tiempo periódicamente
    private fun updateTimePeriodically() {
        viewModelScope.launch {
            while (true) {
                delay(60000) // Espera 1 minuto
                // Actualiza la hora actual con la zona horaria de la ciudad seleccionada
                _currentTime.value = ZonedDateTime.now(ZoneId.of(_selectedCity.value.timeZone))
            }
        }
    }
}
