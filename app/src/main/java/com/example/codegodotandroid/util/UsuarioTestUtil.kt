package com.example.codegodotandroid.util

import android.content.Context
import com.example.codegodotandroid.model.Usuario
import com.example.codegodotandroid.repository.UsuarioRepository

/**
 * Utilidad para crear un usuario de prueba
 * Úsalo solo para desarrollo/testing
 */
object UsuarioTestUtil {

    fun crearUsuarioPrueba(context: Context) {
        val usuarioPrueba = Usuario(
            nombre = "Demian",
            apellidoPaterno = "Pulgar",
            apellidoMaterno = "González",
            usuario = "demianpulgar",
            correo = "demo@codegodot.com",
            telefono = "+56 9 1234 5678",
            fotoUrl = null // Puedes agregar una URL de imagen si lo deseas
        )

        val repository = UsuarioRepository(context)
        repository.guardarUsuarioLogeado(usuarioPrueba)
    }

    fun cerrarSesion(context: Context) {
        val repository = UsuarioRepository(context)
        repository.cerrarSesion()
    }
}

