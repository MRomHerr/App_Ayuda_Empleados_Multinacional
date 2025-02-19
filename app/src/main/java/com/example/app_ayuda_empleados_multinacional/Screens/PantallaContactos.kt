package com.example.ayudaempleadosmultinacional.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_ayuda_empleados_multinacional.R
import com.example.ayudaempleadosmultinacional.ViewModels.InfoContacto
import com.example.ayudaempleadosmultinacional.ViewModels.ContactosViewModel

/**
 * Composable principal que muestra la pantalla de contactos.
 * @param modeloVista ViewModel que contiene la lógica de negocio y datos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaContactos(modeloVista: ContactosViewModel = viewModel()) {
    // Estados para manejar las selecciones y expansiones de los menús desplegables
    var ciudadSeleccionada by remember { mutableStateOf(modeloVista.ciudades.first()) }
    var servicioSeleccionado by remember { mutableStateOf(modeloVista.servicios.first()) }
    var expandidoCiudad by remember { mutableStateOf(false) }
    var expandidoServicio by remember { mutableStateOf(false) }

    // Columna principal que contiene todos los elementos de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Título de la pantalla
        Text(
            "Teléfonos de ayuda y contactos",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Fila que contiene los menús desplegables de ciudad y servicio
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Menú desplegable para seleccionar la ciudad
            ExposedDropdownMenuBox(
                expanded = expandidoCiudad,
                onExpandedChange = { expandidoCiudad = !expandidoCiudad },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                TextField(
                    value = ciudadSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Ciudad") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoCiudad) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandidoCiudad,
                    onDismissRequest = { expandidoCiudad = false }
                ) {
                    modeloVista.ciudades.forEach { ciudad ->
                        DropdownMenuItem(
                            text = { Text(ciudad) },
                            onClick = {
                                ciudadSeleccionada = ciudad
                                expandidoCiudad = false
                            }
                        )
                    }
                }
            }

            // Menú desplegable para seleccionar el servicio
            ExposedDropdownMenuBox(
                expanded = expandidoServicio,
                onExpandedChange = { expandidoServicio = !expandidoServicio },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                TextField(
                    value = servicioSeleccionado,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Servicio") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoServicio) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandidoServicio,
                    onDismissRequest = { expandidoServicio = false }
                ) {
                    modeloVista.servicios.forEach { servicio ->
                        DropdownMenuItem(
                            text = { Text(servicio) },
                            onClick = {
                                servicioSeleccionado = servicio
                                expandidoServicio = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la información de contacto para la ciudad y servicio seleccionados
        modeloVista.obtenerInfoContacto(ciudadSeleccionada)?.let { infoContacto ->
            MostrarInfoContacto(
                ciudad = ciudadSeleccionada,
                servicio = servicioSeleccionado,
                infoContacto = infoContacto,
                numeroTelefono = modeloVista.obtenerTelefonoParaServicio(infoContacto, servicioSeleccionado)
            )
        }
    }
}

/**
 * Composable que muestra la información detallada de contacto para una ciudad y servicio específicos.
 * @param ciudad Nombre de la ciudad seleccionada.
 * @param servicio Tipo de servicio seleccionado.
 * @param infoContacto Objeto que contiene toda la información de contacto.
 * @param numeroTelefono Número de teléfono específico para el servicio seleccionado.
 */
@Composable
fun MostrarInfoContacto(ciudad: String, servicio: String, infoContacto: InfoContacto, numeroTelefono: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        // Contenedor para la imagen del mapa y el número de teléfono
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            // Imagen del mapa de la ciudad
            Image(
                painter = painterResource(id = infoContacto.imagen),
                contentDescription = "Mapa de ${infoContacto.ciudad}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Número de teléfono superpuesto en el mapa
            Text(
                text = numeroTelefono,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black, // Color del texto cambiado a negro
                modifier = Modifier.padding(8.dp)
            )
        }

        // Nombre de la ciudad y país
        Text(
            text = "${infoContacto.ciudad}, ${infoContacto.pais}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Información adicional si el servicio seleccionado es "Oficina"
        if (servicio == "Oficina") {
            Text("Contacto: ${infoContacto.nombreContacto}", fontSize = 14.sp)
            Text("Teléfono: ${infoContacto.telefonoContacto}", fontSize = 14.sp)
            Text("Email: ${infoContacto.emailContacto}", fontSize = 14.sp)
        }
    }
}
