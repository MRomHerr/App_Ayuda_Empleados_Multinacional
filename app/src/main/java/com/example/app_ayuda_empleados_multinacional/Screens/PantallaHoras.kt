package com.example.ayudaempleadosmultinacional.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ayudaempleadosmultinacional.ViewModels.HorasViewModel
import com.example.ayudaempleadosmultinacional.ViewModels.Pais
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Pantalla principal para mostrar las horas en diferentes ciudades del mundo.
 */
@Composable
fun PantallaHoras(viewModel: HorasViewModel = viewModel()) {
    // Observar los estados del ViewModel
    val paises by viewModel.paises.collectAsState()
    val paisSeleccionado by viewModel.paisSeleccionado.collectAsState()
    val horaReferencia by viewModel.horaReferencia.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Horas en las Ciudades", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Selector de ciudades (países)
        SelectorCiudades(paises, paisSeleccionado) { viewModel.actualizarPaisSeleccionado(it) }

        Spacer(modifier = Modifier.height(16.dp))

        paisSeleccionado?.let { pais ->
            Image(
                painter = painterResource(id = pais.imagen),
                contentDescription = "Plano de ${pais.ciudad}",
                modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("${pais.ciudad}, ${pais.pais}",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally))

            Text(
                horaReferencia.format(DateTimeFormatter.ofPattern("HH:mm")),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input para cambiar la hora de referencia del país seleccionado
            EntradaHora(horaReferencia) { hora, minuto -> viewModel.actualizarHoraReferencia(hora, minuto) }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(paises) { pais ->
                    if (pais != paisSeleccionado) {
                        MostrarHoraPais(pais, viewModel) // Muestra la hora local calculada para otros países.
                    }
                }
            }
        }
    }
}

/**
 * Composable para seleccionar una ciudad (país).
 */
@Composable
fun SelectorCiudades(paises: List<Pais>, paisSeleccionado: Pais?, onPaisSeleccionado: (Pais) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(paises) { pais ->
            Button(
                onClick = { onPaisSeleccionado(pais) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD1D126),
                    contentColor = Color.Black
                )
            ) {
                Text(pais.ciudad)
            }
        }
    }
}

/**
 * Composable para ajustar la hora y los minutos de referencia.
 */
@Composable
fun EntradaHora(horaActual: LocalTime, onHoraCambiada: (Int, Int) -> Unit) {
    var horas by remember { mutableStateOf(horaActual.hour) }
    var minutos by remember { mutableStateOf(horaActual.minute) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Hora: ")
        IconButton(onClick = { horas = (horas - 1 + 24) % 24; onHoraCambiada(horas, minutos) }) {
            Text("-")
        }
        Text(horas.toString().padStart(2, '0'))
        IconButton(onClick = { horas = (horas + 1) % 24; onHoraCambiada(horas, minutos) }) {
            Text("+")
        }
        Text(":")
        IconButton(onClick = { minutos = (minutos - 1 + 60) % 60; onHoraCambiada(horas, minutos) }) {
            Text("-")
        }
        Text(minutos.toString().padStart(2, '0'))
        IconButton(onClick = { minutos = (minutos + 1) % 60; onHoraCambiada(horas, minutos) }) {
            Text("+")
        }
    }
}

/**
 * Composable para mostrar la hora local calculada para un país específico.
 */
@Composable
fun MostrarHoraPais(pais: Pais, viewModel: HorasViewModel) {
    val horaLocal = viewModel.obtenerHoraLocal(pais)
    val formateadorHora = DateTimeFormatter.ofPattern("HH:mm")

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = pais.imagen),
            contentDescription = "Plano de ${pais.ciudad}",
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(pais.ciudad, fontWeight = FontWeight.Bold)
            Text(pais.pais)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            horaLocal.format(formateadorHora),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
