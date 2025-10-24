package com.example.codegodotandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.codegodotandroid.ui.theme.CodegodotAndroidTheme
import com.example.codegodotandroid.navigation.MainNavigation
import com.example.codegodotandroid.util.UsuarioTestUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Para testing: crear un usuario de prueba automáticamente
        UsuarioTestUtil.crearUsuarioPrueba(this)

        enableEdgeToEdge()
        setContent {
            CodegodotAndroidTheme {
                MainNavigation()
            }
        }
    }
}