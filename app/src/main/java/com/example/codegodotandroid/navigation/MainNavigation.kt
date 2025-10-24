package com.example.codegodotandroid.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codegodotandroid.view.InicioScreen
import com.example.codegodotandroid.view.PerfilScreen
import kotlinx.coroutines.launch

// Colores estilo Godot (azules oscuros)
object GodotColors {
    val Primary = Color(0xFF478CBF)           // Azul Godot principal
    val PrimaryDark = Color(0xFF2D5A7B)       // Azul oscuro
    val Background = Color(0xFF1F2937)        // Gris azulado oscuro
    val Surface = Color(0xFF374151)           // Superficie oscura
    val OnPrimary = Color.White               // Texto sobre azul
    val OnBackground = Color(0xFFF3F4F6)      // Texto sobre fondo oscuro
}

data class DrawerMenuItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    var selectedScreen by remember { mutableStateOf(Screen.Inicio.route) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItems = listOf(
        DrawerMenuItem(
            route = Screen.Inicio.route,
            icon = Icons.Default.Home,
            label = "Inicio"
        ),
        DrawerMenuItem(
            route = Screen.Perfil.route,
            icon = Icons.Default.Person,
            label = "Perfil"
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = GodotColors.Surface
            ) {
                // Header del Drawer
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GodotColors.Primary)
                        .padding(24.dp)
                ) {
                    Column {
                        Text(
                            text = "CodeGodot",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Menú de navegación",
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Items del menú
                menuItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = if (selectedScreen == item.route)
                                    GodotColors.Primary
                                else
                                    GodotColors.OnBackground.copy(alpha = 0.6f)
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                color = if (selectedScreen == item.route)
                                    GodotColors.Primary
                                else
                                    GodotColors.OnBackground.copy(alpha = 0.8f)
                            )
                        },
                        selected = selectedScreen == item.route,
                        onClick = {
                            selectedScreen = item.route
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = GodotColors.Primary.copy(alpha = 0.2f),
                            unselectedContainerColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = when (selectedScreen) {
                                Screen.Inicio.route -> "CodeGodot"
                                Screen.Perfil.route -> "Mi Perfil"
                                else -> "CodeGodot"
                            },
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = GodotColors.Primary
                    )
                )
            },
            containerColor = GodotColors.Background
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (selectedScreen) {
                    Screen.Inicio.route -> {
                        InicioScreen(
                            onNavigateToComunidad = {
                                // TODO: Navegar a Comunidad cuando esté implementada
                            }
                        )
                    }
                    Screen.Perfil.route -> {
                        PerfilScreen(
                            onRequireLogin = {
                                // TODO: Navegar a Login cuando esté implementada
                            }
                        )
                    }
                }
            }
        }
    }
}
