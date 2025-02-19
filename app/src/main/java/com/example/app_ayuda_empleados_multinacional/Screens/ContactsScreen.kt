package com.example.ayudaempleadosmultinacional.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_ayuda_empleados_multinacional.R
import com.example.ayudaempleadosmultinacional.ViewModels.ContactInfo
import com.example.ayudaempleadosmultinacional.ViewModels.ContactsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(viewModel: ContactsViewModel = viewModel()) {
    // Estados para manejar la selección de ciudad y servicio
    var selectedCity by remember { mutableStateOf(viewModel.cities.first()) }
    var selectedService by remember { mutableStateOf(viewModel.services.first()) }
    var expandedCity by remember { mutableStateOf(false) }
    var expandedService by remember { mutableStateOf(false) }

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
                expanded = expandedCity,
                onExpandedChange = { expandedCity = !expandedCity },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                TextField(
                    value = selectedCity,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Ciudad") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCity) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedCity,
                    onDismissRequest = { expandedCity = false }
                ) {
                    viewModel.cities.forEach { city ->
                        DropdownMenuItem(
                            text = { Text(city) },
                            onClick = {
                                selectedCity = city
                                expandedCity = false
                            }
                        )
                    }
                }
            }

            // Menú desplegable para seleccionar el servicio
            ExposedDropdownMenuBox(
                expanded = expandedService,
                onExpandedChange = { expandedService = !expandedService },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                TextField(
                    value = selectedService,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Servicio") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedService) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedService,
                    onDismissRequest = { expandedService = false }
                ) {
                    viewModel.services.forEach { service ->
                        DropdownMenuItem(
                            text = { Text(service) },
                            onClick = {
                                selectedService = service
                                expandedService = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la información de contacto para la ciudad y servicio seleccionados
        viewModel.getContactInfo(selectedCity)?.let { contactInfo ->
            ContactInfoDisplay(
                city = selectedCity,
                service = selectedService,
                contactInfo = contactInfo,
                phoneNumber = viewModel.getPhoneForService(contactInfo, selectedService)
            )
        }
    }
}

// El resto del código permanece igual


@Composable
fun ContactInfoDisplay(city: String, service: String, contactInfo: ContactInfo, phoneNumber: String) {
    // Columna que contiene la información de contacto
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        // Box que contiene el mapa y el número de teléfono superpuesto
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            // Imagen del mapa de la ciudad
            Image(
                painter = painterResource(id = R.drawable.termometro), // Reemplazar con la imagen del mapa correspondiente
                contentDescription = "Mapa de $city",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Número de teléfono superpuesto en el mapa
            Text(
                text = phoneNumber,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Nombre de la ciudad y país
        Text(
            text = "${contactInfo.city}, ${contactInfo.country}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Si el servicio seleccionado es "Oficina", muestra los detalles de contacto
        if (service == "Oficina") {
            Text("Contacto: ${contactInfo.contactName}", fontSize = 14.sp)
            Text("Teléfono: ${contactInfo.contactPhone}", fontSize = 14.sp)
            Text("Email: ${contactInfo.contactEmail}", fontSize = 14.sp)
        }
    }
}
