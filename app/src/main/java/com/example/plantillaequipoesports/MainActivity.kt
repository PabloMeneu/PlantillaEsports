package com.example.plantillaequipoesports

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantillaequipoesports.ui.theme.PlantillaEquipoESportsTheme


data class Jugador(
    val id: Int,
    val nombre: String,
    val nickname: String,
    val rol: String,
    val edad: Int,
    val nivel: Int,
    @DrawableRes val imagen: Int,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantillaEquipoESportsTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    PantallaPlantilla()

                }
            }

        }

    }
}

@Composable
fun PantallaPlantilla(vm: PlantillaViewModel = PlantillaViewModel()) {
val jugadores = vm.jugadores
    /* val jugadores = remember {
       mutableStateListOf<Jugador>(
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
            ),
        )
    }*/
    var nombre by rememberSaveable { mutableStateOf("") }
    var nickname by rememberSaveable { mutableStateOf("") }
    var rol by rememberSaveable { mutableStateOf("") }
    var edadTexto by rememberSaveable { mutableStateOf("") }
    var nivelTexto by rememberSaveable { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
   // var siguienteId by remember { mutableStateOf(6) }
    var jugadorAEliminar by remember { mutableStateOf<Jugador?>(null) }
    var idEnEdicion by rememberSaveable { mutableStateOf<Int?>(null) }
    fun limpiarFormulario(){
        nombre = ""
        nickname = ""
        rol = ""
        edadTexto = ""
        nivelTexto = ""
        error = ""
        idEnEdicion = null
    }
    val coloresCampo = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.Yellow,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.Yellow,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.Yellow,
        cursorColor = Color.Black,
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Red)
            .padding(horizontal = 20.dp)
            .navigationBarsPadding()
    ) {
        Text(
            text = "TEAM TITANS",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            letterSpacing = 3.sp

        )
        LazyColumn {
            item {
                Column {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = coloresCampo,
                    )
                    OutlinedTextField(
                        value = nickname,
                        onValueChange = { nickname = it },
                        label = { Text("Apodo") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = coloresCampo,
                    )

                    OutlinedTextField(
                        value = rol,
                        onValueChange = { rol = it },
                        label = { Text("Rol") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = coloresCampo,
                    )

                    OutlinedTextField(
                        value = edadTexto,
                        onValueChange = { edadTexto = it },
                        label = { Text("Edad") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = coloresCampo,
                    )

                    OutlinedTextField(
                        value = nivelTexto,
                        onValueChange = { nivelTexto = it },
                        label = { Text("Nivel") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = coloresCampo,
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = {
                                val mensaje = vm.guardarJugador(
                                    idEnEdicion = idEnEdicion,
                                    nombre = nombre,
                                    nickname = nickname,
                                    rol = rol,
                                    edadTexto = edadTexto,
                                    nivelTexto = nivelTexto
                                )

                                if (mensaje == null) {
                                    limpiarFormulario()
                                } else {
                                    error = mensaje
                                }
                            }
                        ) {
                            Text(if (idEnEdicion == null) "Añadir jugador" else "Guardar cambios")
                        }
                       /*Button(onClick = {
                           val nombreLimpio = nombre.trim()
                            val edad = edadTexto.toIntOrNull()
                            val nivel = nivelTexto.toIntOrNull()
                            val imagen = R.drawable.baseline_person_24
                            if (nombreLimpio.isBlank()) {
                                error = "Escribe el nombre."
                            } else if (nickname.isBlank()) {
                                error = "Escribe tu apodo."
                            } else if (rol.isBlank()) {
                                error = "escribe tu rol en el equipo."
                            } else if (edad == null) {
                                error = "Escribe una edad numérica entera."
                            } else if (edad !in 1..99) {
                                error = "La edad debe de estar entre 1 y 99."
                            } else if (nivel == null) {
                                error = "Escribe tu nivel."

                            } else {
                                if (idEnEdicion == null) {
                                    val nuevo = Jugador(
                                        siguienteId,
                                        nombreLimpio,
                                        nickname,
                                        rol,
                                        edad,
                                        nivel,
                                        imagen
                                    )
                                    jugadores.add(nuevo)
                                    siguienteId++
                                } else {
                                    val indice = jugadores.indexOfFirst { it.id == idEnEdicion }
                                    jugadores[indice] = jugadores[indice].copy(
                                        nombre = nombreLimpio,
                                        nickname = nickname,
                                        rol = rol,
                                        edad = edad,
                                        nivel = nivel
                                    )
                                    idEnEdicion = null
                                }


                                nombre = ""
                                nickname = ""
                                rol = ""
                                edadTexto = ""
                                nivelTexto = ""
                                error = ""
                            }
                        }) {
                            Text(if (idEnEdicion == null) "Añadir jugador" else "Guardar cambios")
                        }*/
                        if (idEnEdicion != null) {
                            Button(onClick = { limpiarFormulario() }) {
                                Text("Cancelar")
                            }
                        /*if (idEnEdicion != null) {
                            Button(onClick = {
                                idEnEdicion = null
                                nombre = ""
                                nickname = ""
                                rol = ""
                                edadTexto = ""
                                nivelTexto = ""
                                error = ""
                            }) {
                                Text("Cancelar")
                            }*/
                        }
                    }

                    if (error.isNotEmpty()) {
                        Text(error, color = Color.Yellow)
                    }
                }
            }




            items(jugadores, key = { it.id }) { jugador ->
                TarjetaJugador(jugador, onEditar = {

                    idEnEdicion = jugador.id
                    nombre = jugador.nombre.trim()
                    nickname = jugador.nickname.trim()
                    rol = jugador.rol
                    edadTexto = jugador.edad.toString()
                    nivelTexto = jugador.nivel.toString()
                    error = ""

                }, onEliminar = { jugadorAEliminar = jugador })
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    thickness = 1.5.dp,
                    color = Color.Yellow
                )
            }
        }
        val pendiente = jugadorAEliminar
        if (pendiente != null) {
            AlertDialog(
                onDismissRequest = {
                    jugadorAEliminar = null
                },
                title = { Text("Eliminar jugador") },
                text = { Text("¿Estás seguro de que quieres eliminar a ${pendiente.nickname}?") },
                confirmButton = {
                    TextButton(onClick = {
                        vm.eliminarJugador(pendiente.id)
                        if (idEnEdicion == pendiente.id) {
                            limpiarFormulario()
                        }
                        //jugadores.remove(pendiente)
                        jugadorAEliminar = null
                    }) { Text("Eliminar") }
                },
                dismissButton = {
                    TextButton(onClick = { jugadorAEliminar = null }) { Text("Cancelar") }
                }
            )
        }
    }
}


@Composable
fun TarjetaJugador(
    jugador: Jugador, onEditar: () -> Unit, onEliminar: () -> Unit,

    ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = jugador.imagen),
            contentDescription = "Foto de ${jugador.nickname}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape)
                .background(Color.Red),
        )

        Spacer(modifier = Modifier.width(20.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = jugador.nickname,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = jugador.nombre,
                color = Color.White,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = jugador.rol,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${jugador.edad} años",
                color = Color(0xFF8B949E),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Nivel: ${jugador.nivel}",
                color = Color(0xFF3FB950),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold

            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onEditar) {
                    Text("Editar")
                }

                Button(onClick = onEliminar) {
                    Text("Eliminar")
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun TarjetaJugadorPreview() {
    PlantillaEquipoESportsTheme {
        Surface(color = Color(0xFF0D1117)) {
            TarjetaJugador(
                jugador = Jugador(
                    id = 1,
                    nombre = "Alejandro Portillo",
                    nickname = "AlexPro",
                    rol = "Atacante",
                    edad = 21,
                    nivel = 87,
                    imagen = R.drawable.jugador1

                ),
                onEditar = { },
                onEliminar = { },
            )
        }
    }
}

