package com.example.codegodotandroid.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.codegodotandroid.model.Usuario
import com.google.gson.Gson

class UsuarioRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("CodeGodotPrefs", Context.MODE_PRIVATE)

    private val gson = Gson()

    companion object {
        private const val KEY_USUARIO_LOGEADO = "usuarioLogeado"
    }

    /**
     * Obtiene el usuario logueado desde SharedPreferences
     */
    fun obtenerUsuarioLogeado(): Usuario? {
        val usuarioJson = sharedPreferences.getString(KEY_USUARIO_LOGEADO, null)
        return if (usuarioJson != null) {
            try {
                gson.fromJson(usuarioJson, Usuario::class.java)
            } catch (_: Exception) {
                null
            }
        } else {
            null
        }
    }

    /**
     * Guarda el usuario logueado en SharedPreferences
     */
    fun guardarUsuarioLogeado(usuario: Usuario) {
        val usuarioJson = gson.toJson(usuario)
        sharedPreferences.edit {
            putString(KEY_USUARIO_LOGEADO, usuarioJson)
        }
    }

    /**
     * Elimina el usuario logueado (logout)
     */
    fun cerrarSesion() {
        sharedPreferences.edit {
            remove(KEY_USUARIO_LOGEADO)
        }
    }

    /**
     * Verifica si hay un usuario logueado
     */
    fun hayUsuarioLogeado(): Boolean {
        return sharedPreferences.contains(KEY_USUARIO_LOGEADO)
    }
}
