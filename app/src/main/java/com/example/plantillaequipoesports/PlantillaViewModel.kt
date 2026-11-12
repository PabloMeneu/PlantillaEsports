package com.example.plantillaequipoesports

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PlantillaViewModel : ViewModel() {

    private val _jugadores = mutableStateListOf<Jugador>(
        Jugador(
            id = 1,
            nombre = "Alejandro Portillo",
            nickname = "AlexPro",
            rol = "Atacante",
            edad = 21,
            nivel = 87,
            imagen = R.drawable.jugador1
        ),
        Jugador(
            id = 2,
            nombre = "Marta Sanchez Trillo",
            nickname = "MartaGG",
            rol = "Soporte",
            edad = 20,
            nivel = 84,
            imagen = R.drawable.jugador3
        ),
        Jugador(
            id = 3,
            nombre = "Xavi Colom Rives",
            nickname = "ShadowX",
            rol = "Defensor",
            edad = 24,
            nivel = 91,
            imagen = R.drawable.jugador2
        ),
        Jugador(
            id = 4,
            nombre = "Daniel Rodriguez Tovar",
            nickname = "Destroyer",
            rol = "Atacante",
            edad = 23,
            nivel = 89,
            imagen = R.drawable.jugador4
        ),
        Jugador(
            id = 5,
            nombre = "Dorotea Barceló Mir",
            nickname = "Dory",
            rol = "Atacante",
            edad = 23,
            nivel = 90,
            imagen = R.drawable.jugador5
        )
    )

    val jugadores: List<Jugador>
        get() = _jugadores

    private var siguienteId = 6

    fun guardarJugador(
        idEnEdicion: Int?,
        nombre: String,
        nickname: String,
        rol: String,
        edadTexto: String,
        nivelTexto: String
    ): String? {
        val nombreLimpio = nombre.trim()
        val edad = edadTexto.toIntOrNull()
        val nivel = nivelTexto.toIntOrNull()

        when {
            nombreLimpio.isBlank() -> return "Escribe el nombre."
            nickname.isBlank() -> return "Escribe tu apodo."
            rol.isBlank() -> return "Escribe tu rol en el equipo."
            edad == null -> return "Escribe una edad numérica entera."
            edad !in 1..99 -> return "La edad debe de estar entre 1 y 99."
            nivel == null -> return "Escribe tu nivel."
        }

        if (idEnEdicion == null) {
            _jugadores.add(
                Jugador(
                    id = siguienteId,
                    nombre = nombreLimpio,
                    nickname = nickname,
                    rol = rol,
                    edad = edad,
                    nivel = nivel,
                    imagen = R.drawable.baseline_person_24
                )
            )
            siguienteId++
        } else {
            val indice = _jugadores.indexOfFirst { it.id == idEnEdicion }
            if (indice == -1) return "El jugador que intentas editar ya no está en la plantilla."

            _jugadores[indice] = _jugadores[indice].copy(
                nombre = nombreLimpio,
                nickname = nickname,
                rol = rol,
                edad = edad,
                nivel = nivel
            )
        }

        return null
    }

    fun eliminarJugador(id: Int) {
        _jugadores.removeAll { it.id == id }
    }
}