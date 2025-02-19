// MainActivity
package com.example.ayudaempleadosmultinacional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ayudaempleadosmultinacional.Screens.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ayudaempleadosmultinacional.ViewModels.TemperaturaViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app_ayuda_empleados_multinacional.R
import com.example.app_ayuda_empleados_multinacional.ui.theme.App_Ayuda_Empleados_MultinacionalTheme
import com.example.ayudaempleadosmultinacional.Pantallas.PantallaContactos
import com.example.ayudaempleadosmultinacional.Pantallas.PantallaTemperatura

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_Ayuda_Empleados_MultinacionalTheme {
                MainApp()
            }
        }
    }
}

/**
 * Define los elementos de la barra de navegación inferior.
 */
sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Temperatura : BottomNavItem("temperatura", R.drawable.termometro2, "Temperatura")
    object Horas : BottomNavItem("horas", R.drawable.reloj, "Horas")
    object Contactos : BottomNavItem("contactos", R.drawable.telefono, "Contactos")
}

/**
 * Composable principal que define la estructura de la aplicación.
 */
@Composable
fun MainApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController) }
    ) { innerPadding ->
        AppContent(navController, innerPadding)
    }
}

/**
 * Barra superior de la aplicación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    SmallTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5DC))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_empresa),
                    contentDescription = "Logo de la Empresa",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Acción de ajustes */ }) {
                Icon(Icons.Filled.Settings, contentDescription = "Ajustes", tint = Color.White)
            }
            IconButton(onClick = { /* Acción de login */ }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Login", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFD12926))
    )
}

/**
 * Barra de navegación inferior de la aplicación.
 */
@Composable
fun AppBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Temperatura,
        BottomNavItem.Horas,
        BottomNavItem.Contactos
    )
    NavigationBar(containerColor = Color(0xFF525237)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(item.label, color = Color.White) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFD1D126),
                    unselectedIconColor = Color.White
                )
            )
        }
    }
}

/**
 * Contenido principal de la aplicación, maneja la navegación entre pantallas.
 */
@Composable
fun AppContent(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController, startDestination = BottomNavItem.Temperatura.route, Modifier.padding(innerPadding)) {
        composable(BottomNavItem.Temperatura.route) {
            val temperatureViewModel: TemperaturaViewModel = viewModel()
            PantallaTemperatura(temperatureViewModel)
        }
        composable(BottomNavItem.Horas.route) {
            PantallaHoras()
        }
        composable(BottomNavItem.Contactos.route) {
            PantallaContactos()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App_Ayuda_Empleados_MultinacionalTheme {
        MainApp()
    }
}