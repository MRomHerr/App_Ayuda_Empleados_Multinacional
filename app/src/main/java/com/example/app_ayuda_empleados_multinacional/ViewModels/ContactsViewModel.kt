package com.example.ayudaempleadosmultinacional.ViewModels

import androidx.lifecycle.ViewModel

data class ContactInfo(
    val city: String,
    val country: String,
    val emergencies: String,
    val police: String,
    val firemen: String,
    val touristOffice: String,
    val cityHall: String,
    val taxiService: String,
    val officeName: String,
    val officePhone: String,
    val contactName: String,
    val contactPhone: String,
    val contactEmail: String
)

class ContactsViewModel : ViewModel() {
    private val contactData = listOf(
        ContactInfo(
            city = "Madrid", country = "España",
            emergencies = "112", police = "091", firemen = "080",
            touristOffice = "+34 914 201 314", cityHall = "+34 915 883 300",
            taxiService = "+34 915 474 700", officeName = "Oficina Madrid",
            officePhone = "+34 913 423 600", contactName = "Antonio Avellaneda",
            contactPhone = "+34 913 423 634", contactEmail = "aavellaneda@splatnot.com"
        ),
        ContactInfo(
            city = "París", country = "Francia",
            emergencies = "112", police = "17", firemen = "18",
            touristOffice = "+33 1 49 52 42 63", cityHall = "+33 1 42 76 60 00",
            taxiService = "+33 1 45 30 30 30", officeName = "Oficina París",
            officePhone = "+33 1 45 26 20 30", contactName = "François Merlin",
            contactPhone = "+33 1 45 26 22 46", contactEmail = "fmerlin@splatnot.com"
        ),
        ContactInfo(
            city = "Londres", country = "Reino Unido",
            emergencies = "999", police = "101", firemen = "999",
            touristOffice = "+44 20 7344 1000", cityHall = "+44 20 7983 4000",
            taxiService = "+44 20 7272 0272", officeName = "Oficina Londres",
            officePhone = "+44 20 2536 0200", contactName = "Sarah Louise Taylor",
            contactPhone = "+44 20 2536 0232", contactEmail = "staylor@splatnot.com"
        ),
        ContactInfo(
            city = "Porto Alegre", country = "Brasil",
            emergencies = "190 (Policía), 193 (Bomberos)", police = "190", firemen = "193",
            touristOffice = "+55 51 3289 4285", cityHall = "+55 51 3289 1027",
            taxiService = "+55 51 3211 1188", officeName = "Oficina Porto Alegre",
            officePhone = "+55 51 5644 1000", contactName = "Maria Fernanda Oliveira Costa",
            contactPhone = "+55 51 5644 1688", contactEmail = "mfoliveira@splatnot.com"
        ),
        ContactInfo(
            city = "Acapulco", country = "México",
            emergencies = "911", police = "911", firemen = "911",
            touristOffice = "+52 744 482 2855", cityHall = "+52 744 482 1400",
            taxiService = "+52 744 485 1073", officeName = "Oficina Acapulco",
            officePhone = "+52 744 779 1000", contactName = "Antonio Avellaneda",
            contactPhone = "+52 744 779 1948", contactEmail = "aavellaneda@splatnot.com"
        ),
        ContactInfo(
            city = "Vancouver", country = "Canadá",
            emergencies = "911", police = "911", firemen = "911",
            touristOffice = "+1 604 482 2888", cityHall = "+1 604 873 7000",
            taxiService = "+1 604 681 1111", officeName = "Oficina Vancouver",
            officePhone = "+34 913 423 600", contactName = "David Miller",
            contactPhone = "+34 913 423 634", contactEmail = "dmiller@splatnot.com"
        ),
        ContactInfo(
            city = "Houston", country = "Estados Unidos",
            emergencies = "911", police = "713 884 3131", firemen = "911",
            touristOffice = "+1 713 437 5240", cityHall = "+1 713 247 1000",
            taxiService = "+1 713 236 1111", officeName = "Oficina Houston",
            officePhone = "+1 713 555 1222", contactName = "Robinson Hill",
            contactPhone = "+1 713 555 1291", contactEmail = "rhill@splatnot.com"
        ),
        ContactInfo(
            city = "Casablanca", country = "Marruecos",
            emergencies = "19 (Policía), 15 (Bomberos)", police = "19", firemen = "15",
            touristOffice = "+212 522 225 410", cityHall = "+212 522 235 157",
            taxiService = "+212 522 252 014", officeName = "Oficina Casablanca",
            officePhone = "+212 522 449 000", contactName = "Ahmed Ben Youssef El Fassi",
            contactPhone = "+212 522 449 644", contactEmail = "abenyoussef@splatnot.com"
        ),
        ContactInfo(
            city = "Osaka", country = "Japón",
            emergencies = "110 (Policía), 119 (Bomberos y Ambulancias)", police = "110", firemen = "119",
            touristOffice = "+81 6 6345 3301", cityHall = "+81 6 6208 7171",
            taxiService = "+81 6 6345 1234", officeName = "Oficina Osaka",
            officePhone = "+81 6 4882 6600", contactName = "Takahashi Hiroshi",
            contactPhone = "+81 6 4882 6632", contactEmail = "thiroshi@splatnot.com"
        ),
        ContactInfo(
            city = "Melbourne", country = "Australia",
            emergencies = "000", police = "000", firemen = "000",
            touristOffice = "+61 3 9658 9658", cityHall = "+61 3 9658 9658",
            taxiService = "+61 3 8413 7300", officeName = "Oficina Melbourne",
            officePhone = "+61 3 9974 9600", contactName = "Emily Johnson",
            contactPhone = "+61 3 9974 9677", contactEmail = "ejohnson@splatnot.com"
        ),
        ContactInfo(
            city = "Ankara", country = "Turquía",
            emergencies = "112", police = "155", firemen = "110",
            touristOffice = "+90 312 310 13 55", cityHall = "+90 312 507 10 00",
            taxiService = "+90 312 444 75 47", officeName = "Oficina Ankara",
            officePhone = "+90 312 822 70 00", contactName = "Elif Demir",
            contactPhone = "+90 312 822 70 94", contactEmail = "edemir@splatnot.com"
        ),
        ContactInfo(
            city = "Dubai", country = "Emiratos Árabes Unidos",
            emergencies = "999", police = "999", firemen = "997",
            touristOffice = "+971 4 201 5555", cityHall = "+971 4 406 5555",
            taxiService = "+971 4 208 0808", officeName = "Oficina Dubai",
            officePhone = "+971 4 495 7000", contactName = "Khalid Al Maktoum",
            contactPhone = "+971 4 495 7556", contactEmail = "kalmaktoum@splatnot.com"
        )
    )

    val cities = contactData.map { it.city }
    val services = listOf("Emergencias", "Policía", "Bomberos", "Oficina de Información y Turismo", "Ayuntamiento", "Servicio de Taxi", "Oficina")

    fun getContactInfo(city: String): ContactInfo? {
        return contactData.find { it.city == city }
    }

    fun getPhoneForService(contactInfo: ContactInfo, service: String): String {
        return when (service) {
            "Emergencias" -> contactInfo.emergencies
            "Policía" -> contactInfo.police
            "Bomberos" -> contactInfo.firemen
            "Oficina de Información y Turismo" -> contactInfo.touristOffice
            "Ayuntamiento" -> contactInfo.cityHall
            "Servicio de Taxi" -> contactInfo.taxiService
            "Oficina" -> contactInfo.officePhone
            else -> ""
        }
    }
}
