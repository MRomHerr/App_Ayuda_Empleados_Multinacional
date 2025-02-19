// ViewModel
package com.example.ayudaempleadosmultinacional.ViewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel para manejar la lógica de negocio relacionada con la temperatura.
 */
class TemperaturaViewModel : ViewModel() {
    private val _temperatura = MutableStateFlow(0f)
    val temperatura = _temperatura.asStateFlow()

    private val _esCelsius = MutableStateFlow(true)
    val esCelsius = _esCelsius.asStateFlow()

    private val _listaTemperaturas = MutableStateFlow<List<Pair<Int, Int>>>(emptyList())
    val listaTemperaturas = _listaTemperaturas.asStateFlow()

    /**
     * Actualiza la temperatura actual.
     */
    fun actualizarTemperatura(valor: Float) {
        _temperatura.value = valor
    }

    /**
     * Cambia entre Celsius y Fahrenheit.
     */
    fun cambiarUnidadTemperatura() {
        _esCelsius.value = !_esCelsius.value
    }

    /**
     * Guarda la temperatura actual en la lista.
     */
    fun guardarTemperatura() {
        val celsius = _temperatura.value.toInt()
        val fahrenheit = celsiusAFahrenheit(celsius)
        val nuevaLista = _listaTemperaturas.value.toMutableList()
        if (nuevaLista.size >= 50) nuevaLista.removeFirst()
        nuevaLista.add(celsius to fahrenheit)
        _listaTemperaturas.value = nuevaLista
    }

    /**
     * Convierte Celsius a Fahrenheit.
     */
    private fun celsiusAFahrenheit(celsius: Int): Int {
        return (celsius * 9 / 5) + 32
    }
}
