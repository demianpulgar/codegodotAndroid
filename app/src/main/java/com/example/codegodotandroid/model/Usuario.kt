package com.example.codegodotandroid.model

data class Usuario(
    val nombre: String? = null,
    val apellidoPaterno: String? = null,
    val apellidoMaterno: String? = null,
    val usuario: String? = null,
    val correo: String? = null,
    val telefono: String? = null,
    val fotoUrl: String? = null
) {
    fun nombreCompleto(): String {
        val partes = listOfNotNull(nombre, apellidoPaterno, apellidoMaterno)
        return partes.joinToString(" ").ifBlank { "Sin nombre" }
    }
}

