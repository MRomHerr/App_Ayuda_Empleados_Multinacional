package com.example.ayudaempleadosmultinacional.ViewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.app_ayuda_empleados_multinacional.R
import java.time.LocalTime

/**
 * ViewModel para manejar la lógica de negocio relacionada con las horas en diferentes países.
 */
data class Pais(
    val ciudad: String,      // Nombre de la ciudad
    val pais: String,        // Nombre del país
    val imagen: Int,         // Recurso de imagen para la ciudad
    val diferencia: Int      // Diferencia horaria respecto a la hora de referencia
)

class HorasViewModel : ViewModel() {

    // Lista de países disponibles
    private val _paises = MutableStateFlow<List<Pais>>(emptyList())
    val paises = _paises.asStateFlow()

    // País actualmente seleccionado por el usuario
    private val _paisSeleccionado = MutableStateFlow<Pais?>(null)
    val paisSeleccionado = _paisSeleccionado.asStateFlow()

    // Hora de referencia seleccionada por el usuario (inicializada a 00:00)
    private val _horaReferencia = MutableStateFlow(LocalTime.of(0, 0))
    val horaReferencia = _horaReferencia.asStateFlow()

    /**
     * Inicializa el ViewModel cargando la lista de países y seleccionando el primero como predeterminado.
     */
    init {
        actualizarPaises()
        _paisSeleccionado.value = _paises.value.firstOrNull()
    }

    /**
     * Carga la lista de países con sus respectivas diferencias horarias e imágenes.
     */
    private fun actualizarPaises() {
        val nuevaLista = listOf(
            Pais("Madrid", "España", R.drawable.espana, 0),
            Pais("París", "Francia", R.drawable.francia, 0),
            Pais("Londres", "Reino Unido", R.drawable.reino_unido, -1),
            Pais("Porto Alegre", "Brasil", R.drawable.brasil, -4),
            Pais("Acapulco", "México", R.drawable.mexico, -7),
            Pais("Vancouver", "Canadá", R.drawable.canada, -9),
            Pais("Houston", "Estados Unidos", R.drawable.estados_unidos, -7),
            Pais("Casablanca", "Marruecos", R.drawable.marruecos, 0),
            Pais("Osaka", "Japón", R.drawable.japon, +8),
            Pais("Melbourne", "Australia", R.drawable.australia, +10),
            Pais("Ankara", "Turquía", R.drawable.turquia, +2),
            Pais("Dubai", "Emiratos Árabes Unidos", R.drawable.emiratos, +3)
        )
        _paises.value = nuevaLista
    }

    /**
     * Actualiza el país seleccionado por el usuario.
     *
     * @param pais El país seleccionado.
     */
    fun actualizarPaisSeleccionado(pais: Pais) {
        _paisSeleccionado.value = pais
    }

    /**
     * Actualiza la hora de referencia seleccionada por el usuario.
     *
     * @param hora La nueva hora seleccionada.
     * @param minuto Los nuevos minutos seleccionados.
     */
    fun actualizarHoraReferencia(hora: Int, minuto: Int) {
        _horaReferencia.value = LocalTime.of(hora, minuto)
    }

    /**
     * Calcula la hora local para un país basado en la hora de referencia y su diferencia horaria.
     *
     * @param pais El país para el que se desea calcular la hora local.
     * @return La hora local calculada.
     */
    fun obtenerHoraLocal(pais: Pais): LocalTime {
        val diferenciaHoras = pais.diferencia - (_paisSeleccionado.value?.diferencia ?: 0)
        return _horaReferencia.value.plusHours(diferenciaHoras.toLong())
    }
}
