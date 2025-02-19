package com.example.ayudaempleadosmultinacional.ViewModels

import androidx.lifecycle.ViewModel
import com.example.app_ayuda_empleados_multinacional.R

data class InfoContacto(
    val ciudad: String,
    val pais: String,
    val imagen: Int,
    val emergencias: String,
    val policia: String,
    val bomberos: String,
    val oficinaTurismo: String,
    val ayuntamiento: String,
    val servicioTaxi: String,
    val nombreOficina: String,
    val telefonoOficina: String,
    val nombreContacto: String,
    val telefonoContacto: String,
    val emailContacto: String
)

class ContactosViewModel : ViewModel() {
    private val datosContacto = listOf(
        InfoContacto(
            ciudad = "Madrid", pais = "España", imagen = R.drawable.espana,
            emergencias = "112", policia = "091", bomberos = "080",
            oficinaTurismo = "+34 914 201 314", ayuntamiento = "+34 915 883 300",
            servicioTaxi = "+34 915 474 700", nombreOficina = "Oficina Madrid",
            telefonoOficina = "+34 913 423 600", nombreContacto = "Antonio Avellaneda",
            telefonoContacto = "+34 913 423 634", emailContacto = "aavellaneda@splatnot.com"
        ),
        InfoContacto(
            ciudad = "París", pais = "Francia", imagen = R.drawable.francia,
            emergencias = "112", policia = "17", bomberos = "18",
            oficinaTurismo = "+33 1 49 52 42 63", ayuntamiento = "+33 1 42 76 60 00",
            servicioTaxi = "+33 1 45 30 30 30", nombreOficina = "Oficina París",
            telefonoOficina = "+33 1 45 26 20 30", nombreContacto = "François Merlin",
            telefonoContacto = "+33 1 45 26 22 46", emailContacto = "fmerlin@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Londres", pais = "Reino Unido", imagen = R.drawable.reino_unido,
            emergencias = "999", policia = "101", bomberos = "999",
            oficinaTurismo = "+44 20 7344 1000", ayuntamiento = "+44 20 7983 4000",
            servicioTaxi = "+44 20 7272 0272", nombreOficina = "Oficina Londres",
            telefonoOficina = "+44 20 2536 0200", nombreContacto = "Sarah Louise Taylor",
            telefonoContacto = "+44 20 2536 0232", emailContacto = "staylor@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Porto Alegre", pais = "Brasil", imagen = R.drawable.brasil,
            emergencias = "190 (Policía), 193 (Bomberos)", policia = "190", bomberos = "193",
            oficinaTurismo = "+55 51 3289 4285", ayuntamiento = "+55 51 3289 1027",
            servicioTaxi = "+55 51 3211 1188", nombreOficina = "Oficina Porto Alegre",
            telefonoOficina = "+55 51 5644 1000", nombreContacto = "Maria Fernanda Oliveira Costa",
            telefonoContacto = "+55 51 5644 1688", emailContacto = "mfoliveira@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Acapulco", pais = "México", imagen = R.drawable.mexico,
            emergencias = "911", policia = "911", bomberos = "911",
            oficinaTurismo = "+52 744 482 2855", ayuntamiento = "+52 744 482 1400",
            servicioTaxi = "+52 744 485 1073", nombreOficina = "Oficina Acapulco",
            telefonoOficina = "+52 744 779 1000", nombreContacto = "Antonio Avellaneda",
            telefonoContacto = "+52 744 779 1948", emailContacto = "aavellaneda@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Vancouver", pais = "Canadá", imagen = R.drawable.canada,
            emergencias = "911", policia = "911", bomberos = "911",
            oficinaTurismo = "+1 604 482 2888", ayuntamiento = "+1 604 873 7000",
            servicioTaxi = "+1 604 681 1111", nombreOficina = "Oficina Vancouver",
            telefonoOficina = "+34 913 423 600", nombreContacto = "David Miller",
            telefonoContacto = "+34 913 423 634", emailContacto = "dmiller@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Houston", pais = "Estados Unidos", imagen = R.drawable.estados_unidos,
            emergencias = "911", policia = "713 884 3131", bomberos = "911",
            oficinaTurismo = "+1 713 437 5240", ayuntamiento = "+1 713 247 1000",
            servicioTaxi = "+1 713 236 1111", nombreOficina = "Oficina Houston",
            telefonoOficina = "+1 713 555 1222", nombreContacto = "Robinson Hill",
            telefonoContacto = "+1 713 555 1291", emailContacto = "rhill@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Casablanca", pais = "Marruecos", imagen = R.drawable.marruecos,
            emergencias = "19 (Policía), 15 (Bomberos)", policia = "19", bomberos = "15",
            oficinaTurismo = "+212 522 225 410", ayuntamiento = "+212 522 235 157",
            servicioTaxi = "+212 522 252 014", nombreOficina = "Oficina Casablanca",
            telefonoOficina = "+212 522 449 000", nombreContacto = "Ahmed Ben Youssef El Fassi",
            telefonoContacto = "+212 522 449 644", emailContacto = "abenyoussef@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Osaka", pais = "Japón", imagen = R.drawable.japon,
            emergencias = "110 (Policía), 119 (Bomberos y Ambulancias)", policia = "110", bomberos = "119",
            oficinaTurismo = "+81 6 6345 3301", ayuntamiento = "+81 6 6208 7171",
            servicioTaxi = "+81 6 6345 1234", nombreOficina = "Oficina Osaka",
            telefonoOficina = "+81 6 4882 6600", nombreContacto = "Takahashi Hiroshi",
            telefonoContacto = "+81 6 4882 6632", emailContacto = "thiroshi@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Melbourne", pais = "Australia", imagen = R.drawable.australia,
            emergencias = "000", policia = "000", bomberos = "000",
            oficinaTurismo = "+61 3 9658 9658", ayuntamiento = "+61 3 9658 9658",
            servicioTaxi = "+61 3 8413 7300", nombreOficina = "Oficina Melbourne",
            telefonoOficina = "+61 3 9974 9600", nombreContacto = "Emily Johnson",
            telefonoContacto = "+61 3 9974 9677", emailContacto = "ejohnson@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Ankara", pais = "Turquía", imagen = R.drawable.turquia,
            emergencias = "112", policia = "155", bomberos = "110",
            oficinaTurismo = "+90 312 310 13 55", ayuntamiento = "+90 312 507 10 00",
            servicioTaxi = "+90 312 444 75 47", nombreOficina = "Oficina Ankara",
            telefonoOficina = "+90 312 822 70 00", nombreContacto = "Elif Demir",
            telefonoContacto = "+90 312 822 70 94", emailContacto = "edemir@splatnot.com"
        ),
        InfoContacto(
            ciudad = "Dubai", pais = "Emiratos Árabes Unidos", imagen = R.drawable.emiratos,
            emergencias = "999", policia = "999", bomberos = "997",
            oficinaTurismo = "+971 4 201 5555", ayuntamiento = "+971 4 406 5555",
            servicioTaxi = "+971 4 208 0808", nombreOficina = "Oficina Dubai",
            telefonoOficina = "+971 4 495 7000", nombreContacto = "Khalid Al Maktoum",
            telefonoContacto = "+971 4 495 7556", emailContacto = "kalmaktoum@splatnot.com"
        )
    )

    val ciudades = datosContacto.map { it.ciudad }
    val servicios = listOf("Emergencias", "Policía", "Bomberos", "Oficina de Información y Turismo", "Ayuntamiento", "Servicio de Taxi", "Oficina")

    fun obtenerInfoContacto(ciudad: String): InfoContacto? {
        return datosContacto.find { it.ciudad == ciudad }
    }

    fun obtenerTelefonoParaServicio(infoContacto: InfoContacto, servicio: String): String {
        return when (servicio) {
            "Emergencias" -> infoContacto.emergencias
            "Policía" -> infoContacto.policia
            "Bomberos" -> infoContacto.bomberos
            "Oficina de Información y Turismo" -> infoContacto.oficinaTurismo
            "Ayuntamiento" -> infoContacto.ayuntamiento
            "Servicio de Taxi" -> infoContacto.servicioTaxi
            "Oficina" -> infoContacto.telefonoOficina
            else -> ""
        }
    }
}
