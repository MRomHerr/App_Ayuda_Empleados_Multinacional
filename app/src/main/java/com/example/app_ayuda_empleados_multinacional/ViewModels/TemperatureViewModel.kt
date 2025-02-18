package com.example.ayudaempleadosmultinacional.ViewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TemperatureViewModel : ViewModel() {
    private val _temperature = MutableStateFlow(0f)
    val temperature = _temperature.asStateFlow()

    private val _isCelsius = MutableStateFlow(true)
    val isCelsius = _isCelsius.asStateFlow()

    private val _temperatureList = MutableStateFlow<List<Pair<Int, Int>>>(emptyList())
    val temperatureList = _temperatureList.asStateFlow()

    fun updateTemperature(value: Float) {
        _temperature.value = value
    }

    fun toggleTemperatureUnit() {
        _isCelsius.value = !_isCelsius.value
    }

    fun saveTemperature() {
        val celsius = _temperature.value.toInt()
        val fahrenheit = celsiusToFahrenheit(celsius)
        val newList = _temperatureList.value.toMutableList()
        if (newList.size >= 50) newList.removeFirst()
        newList.add(celsius to fahrenheit)
        _temperatureList.value = newList
    }

    private fun celsiusToFahrenheit(celsius: Int): Int {
        return (celsius * 9 / 5) + 32
    }
}
