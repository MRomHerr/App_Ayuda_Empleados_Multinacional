// ViewModel
package com.example.ayudaempleadosmultinacional.ViewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel para manejar la lógica de negocio relacionada con la temperatura.
 */
class TemperatureViewModel : ViewModel() {
    private val _temperature = MutableStateFlow(0f)
    val temperature = _temperature.asStateFlow()

    private val _isCelsius = MutableStateFlow(true)
    val isCelsius = _isCelsius.asStateFlow()

    private val _temperatureList = MutableStateFlow<List<Pair<Int, Int>>>(emptyList())
    val temperatureList = _temperatureList.asStateFlow()

    /**
     * Actualiza la temperatura actual.
     */
    fun updateTemperature(value: Float) {
        _temperature.value = value
    }

    /**
     * Cambia entre Celsius y Fahrenheit.
     */
    fun toggleTemperatureUnit() {
        _isCelsius.value = !_isCelsius.value
    }

    /**
     * Guarda la temperatura actual en la lista.
     */
    fun saveTemperature() {
        val celsius = _temperature.value.toInt()
        val fahrenheit = celsiusToFahrenheit(celsius)
        val newList = _temperatureList.value.toMutableList()
        if (newList.size >= 50) newList.removeFirst()
        newList.add(celsius to fahrenheit)
        _temperatureList.value = newList
    }

    /**
     * Convierte Celsius a Fahrenheit.
     */
    private fun celsiusToFahrenheit(celsius: Int): Int {
        return (celsius * 9 / 5) + 32
    }
}