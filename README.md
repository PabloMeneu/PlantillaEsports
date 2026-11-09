# Team Titans · Plantilla de eSports 2.0

Aplicación Android para gestionar la plantilla de un equipo de eSports. Permite **añadir**, **editar** y **eliminar** jugadores, y la lista se actualiza sola en pantalla.

**Módulo:** Programación Multimedia y Dispositivos Móviles (2.º DAM)
**Tecnologías:** Kotlin · Jetpack Compose · Material 3 · Android Studio

---

## Qué hace la app

- Muestra la plantilla inicial de **5 jugadores**, cada uno con foto, nickname, nombre, rol, edad y nivel.
- **Añadir:** un formulario con nombre, apodo, rol, edad y nivel. Comprueba los datos y, si falta algo, muestra un mensaje de error en amarillo. Los jugadores nuevos llevan una silueta como foto.
- **Editar:** el botón *Editar* carga los datos del jugador en el formulario. Se pueden **guardar** los cambios (se conserva su id y su foto) o **cancelar**.
- **Eliminar:** el botón *Eliminar* abre un diálogo de confirmación antes de borrar.
- Los campos del formulario no se borran al **girar el móvil** (`rememberSaveable`).

---

## La receta: cómo está montada la pantalla

Siempre en este orden:

1. **La clase** (`data class Jugador`), arriba del todo, fuera de todo. Es el molde: dice qué datos tiene un jugador.
2. **La lista** de jugadores, dentro de `PantallaPlantilla`:
   ```kotlin
   val jugadores = remember { mutableStateListOf(Jugador(...), Jugador(...)) }
   ```
3. **Las variables del formulario y del estado:**
   - Campos de texto → `rememberSaveable { mutableStateOf("") }`
   - `error`, `siguienteId`, `jugadorAEliminar`, `idEnEdicion` → `remember { ... }`
4. **La `LazyColumn`:**
   - `item { }` → el formulario, los botones y el mensaje de error (una sola vez).
   - `items(jugadores, key = { it.id }) { jugador -> }` → una tarjeta por jugador.
5. **El `AlertDialog`**, fuera de la `LazyColumn`, que solo se ve cuando `jugadorAEliminar` no es `null`.
6. **La tarjeta** en su propia función: `TarjetaJugador(jugador, onEditar, onEliminar)`.

---

## Lo que he aprendido

### Paréntesis y llaves
- `( )` → **configuración**: cómo es el elemento (`modifier`, colores, `onClick`...).
- `{ }` → **contenido**: qué hay dentro (otros elementos, el texto de un botón).
- Entre paréntesis, coma después de cada cosa (también vale después de la última). Entre llaves, sin comas.

### Crear una variable o usarla
- **Con `val` / `var` delante**, la variable **se crea** en ese momento.
- **Sin `val`**, ya existía y solo se le da un valor nuevo (`error = ""`).
- Una variable vive dentro de las llaves donde se crea (**ámbito**). Las que se crean dentro de un `onClick` solo existen durante ese clic.

### `remember` frente a `rememberSaveable`
| | `remember` | `rememberSaveable` |
|---|---|---|
| Se conserva al redibujar la pantalla | ✅ | ✅ |
| Se conserva al girar el móvil | ❌ | ✅ |

La lista **no** puede ser `rememberSaveable`. Al cerrar la app, la lista vuelve a los 5 jugadores iniciales: para guardarla haría falta una base de datos.

### Estado: la pantalla se redibuja sola
No hace falta "volver" a ninguna pantalla. Se cambia una variable de estado y Compose redibuja:
- `idEnEdicion = null` → modo **añadir**. Con un id → modo **editar** (el botón dice "Guardar cambios" y aparece "Cancelar").
- `jugadorAEliminar = null` → no hay diálogo. Con un jugador → sale el diálogo.

### Funciones que avisan
La tarjeta no puede borrar ni editar, porque la lista está fuera. Recibe **acciones** y solo avisa de que se ha pulsado:
```kotlin
fun TarjetaJugador(jugador: Jugador, onEditar: () -> Unit, onEliminar: () -> Unit)
```
Una acción siempre va entre llaves: `onEliminar = { jugadorAEliminar = jugador }`.

### Validar
- `trim()` quita los espacios del principio y del final.
- `isBlank()` comprueba si un texto está vacío.
- `toIntOrNull()` convierte texto a número, o da `null` si no se puede ("abc").
- Cadena `if / else if / else`: se comprueba en orden y solo se añade en el `else` final. Mejor comprobar los campos en el mismo orden en que salen en pantalla.

### Editar un jugador de la lista
```kotlin
val indice = jugadores.indexOfFirst { it.id == idEnEdicion }   // posición del jugador
jugadores[indice] = jugadores[indice].copy(nombre = ..., nivel = ...)   // copia cambiando solo esos datos
```
`copy` mantiene lo que no se le indica, así que el **id** y la **imagen** se conservan.

### Textos con variables
- `"$edad años"` → una variable sola.
- `"${jugador.edad} años"` → si hay un punto, con llaves.

### Colores
`Color.White`, `Black`, `Red`, `Yellow`, `Green`, `Blue`, `Gray`, `LightGray`, `DarkGray`, `Cyan`, `Magenta`, `Transparent`.
Cualquier otro: `Color(0xFF` + código hexadecimal + `)`, como en Figma.

---

## Atajos de Android Studio

| Atajo | Para qué sirve |
|---|---|
| **Alt + Enter** | Arreglar lo que sale en rojo, sobre todo añadir el import que falta |
| **Code → Reformat Code** (Ctrl + Alt + L) | Ordenar las sangrías para ver qué está dentro de qué |
| **Mayús + F6** | Cambiar un nombre en todos los sitios a la vez |
| **Ctrl + Z** / **Ctrl + Mayús + Z** | Deshacer / rehacer |
| **Escape** | Cerrar una sugerencia o un aviso sin aceptarlo |
| **Ctrl + clic** sobre un nombre | Ir a donde está definido |
| **Ctrl + /** | Comentar o descomentar una línea |
| **Ctrl + P** (dentro de los paréntesis) | Ver los parámetros que acepta una función |
| **Ctrl + Espacio** | Ver las sugerencias de autocompletado |
| **Ctrl + F** | Buscar en el archivo |

**Trucos:**
- Con el cursor justo después de una `{` o un `(`, Android Studio marca su pareja.
- Escribir primero el esqueleto vacío (`Button() { }`, `if () { } else { }`) y rellenarlo después.
- Una variable en **gris** significa que no se usa en ningún sitio.
- Si el emulador se cuelga: *Device Manager → ⋮ → Cold Boot Now*.
- Si sale "Module not specified": *Edit Configurations → Module → …app*.

---

## Cómo ejecutarla

1. Clonar el repositorio y abrirlo con Android Studio.
2. Esperar a que termine la sincronización de Gradle.
3. Ejecutar con ▶ en un emulador o en un móvil.
