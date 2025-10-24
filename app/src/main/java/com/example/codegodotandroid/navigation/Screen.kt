package com.example.codegodotandroid.navigation

sealed class Screen(val route: String) {
    object Inicio : Screen("inicio")
    object Perfil : Screen("perfil")
    object Comunidad : Screen("comunidad")
}

