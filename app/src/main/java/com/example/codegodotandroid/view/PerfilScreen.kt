package com.example.codegodotandroid.view

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.codegodotandroid.R
import com.example.codegodotandroid.model.Usuario
import com.example.codegodotandroid.navigation.GodotColors
import com.example.codegodotandroid.repository.UsuarioRepository
import com.example.codegodotandroid.viewmodel.PerfilUiState
import com.example.codegodotandroid.viewmodel.PerfilViewModel
import com.example.codegodotandroid.viewmodel.PerfilViewModelFactory
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.delay
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel = viewModel(
        factory = PerfilViewModelFactory(
            UsuarioRepository(LocalContext.current)
        )
    ),
    onRequireLogin: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = GodotColors.Background
    ) {
        when (val state = uiState) {
            is PerfilUiState.Loading -> {
                LoadingContent()
            }
            is PerfilUiState.Success -> {
                PerfilContent(
                    usuario = state.usuario,
                    onFotoActualizada = { uri ->
                        viewModel.actualizarFotoPerfil(uri)
                    }
                )
            }
            is PerfilUiState.Error -> {
                ErrorContent(mensaje = state.mensaje)
            }
            is PerfilUiState.NoAutenticado -> {
                NoAutenticadoContent(onRequireLogin = onRequireLogin)
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = GodotColors.Primary)
    }
}

@Composable
fun NoAutenticadoContent(onRequireLogin: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onRequireLogin()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = GodotColors.Surface
            )
        ) {
            Text(
                text = "Debes iniciar sesión para ver tu perfil.",
                modifier = Modifier.padding(20.dp),
                color = GodotColors.OnBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ErrorContent(mensaje: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = GodotColors.Surface
            )
        ) {
            Text(
                text = mensaje,
                modifier = Modifier.padding(20.dp),
                color = Color(0xFFEF4444),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PerfilContent(
    usuario: Usuario,
    onFotoActualizada: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    var mostrarDialogoFoto by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = GodotColors.Surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título
                Text(
                    text = "Mi Perfil",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = GodotColors.OnBackground
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Foto de perfil con opción de cambiar
                FotoPerfilClickable(
                    fotoUrl = usuario.fotoUrl,
                    onClick = { mostrarDialogoFoto = true }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Texto de ayuda
                Text(
                    text = "Toca la foto para cambiarla",
                    fontSize = 12.sp,
                    color = GodotColors.OnBackground.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Campos de información
                CampoPerfilRow(
                    label1 = "Nombre Completo",
                    value1 = usuario.nombreCompleto(),
                    label2 = "Usuario",
                    value2 = usuario.usuario ?: ""
                )

                Spacer(modifier = Modifier.height(16.dp))

                CampoPerfilRow(
                    label1 = "Correo Electrónico",
                    value1 = usuario.correo ?: "",
                    label2 = "Teléfono",
                    value2 = usuario.telefono ?: "No especificado"
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Mensaje informativo
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = GodotColors.Primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "ℹ️",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(end = 8.dp, top = 2.dp)
                        )
                        Text(
                            text = "Esta es tu información de perfil. Próximamente podrás editar tus datos.",
                            fontSize = 14.sp,
                            color = GodotColors.OnBackground,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }

    // Diálogo para seleccionar cámara o galería
    if (mostrarDialogoFoto) {
        SelectorImagenDialog(
            onDismiss = { mostrarDialogoFoto = false },
            onImagenSeleccionada = { uri ->
                onFotoActualizada(uri)
                mostrarDialogoFoto = false
            }
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SelectorImagenDialog(
    onDismiss: () -> Unit,
    onImagenSeleccionada: (String) -> Unit
) {
    val context = LocalContext.current

    // Estados de permisos
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val storagePermissionState = rememberPermissionState(
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        }
    )

    // URI temporal para la foto de cámara
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher para la cámara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            onImagenSeleccionada(photoUri.toString())
        }
    }

    // Launcher para la galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            onImagenSeleccionada(it.toString())
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Seleccionar foto",
                color = GodotColors.OnBackground
            )
        },
        text = {
            Column {
                Text(
                    text = "¿Cómo quieres seleccionar tu foto de perfil?",
                    color = GodotColors.OnBackground.copy(alpha = 0.8f)
                )
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Botón Cámara
                OutlinedButton(
                    onClick = {
                        if (cameraPermissionState.status.isGranted) {
                            photoUri = crearArchivoTemporal(context)
                            photoUri?.let { cameraLauncher.launch(it) }
                        } else {
                            cameraPermissionState.launchPermissionRequest()
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = GodotColors.Primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Cámara",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara")
                }

                // Botón Galería
                OutlinedButton(
                    onClick = {
                        if (storagePermissionState.status.isGranted) {
                            galleryLauncher.launch("image/*")
                        } else {
                            storagePermissionState.launchPermissionRequest()
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = GodotColors.Primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoLibrary,
                        contentDescription = "Galería",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = GodotColors.OnBackground.copy(alpha = 0.6f))
            }
        },
        containerColor = GodotColors.Surface
    )

    // Diálogos de permisos denegados
    when {
        cameraPermissionState.status.shouldShowRationale -> {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Permiso de cámara requerido") },
                text = { Text("Esta app necesita acceso a la cámara para tomar fotos de perfil.") },
                confirmButton = {
                    TextButton(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                        Text("Conceder permiso")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                }
            )
        }
        storagePermissionState.status.shouldShowRationale -> {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Permiso de almacenamiento requerido") },
                text = { Text("Esta app necesita acceso al almacenamiento para seleccionar fotos.") },
                confirmButton = {
                    TextButton(onClick = { storagePermissionState.launchPermissionRequest() }) {
                        Text("Conceder permiso")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

fun crearArchivoTemporal(context: Context): Uri {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir = context.getExternalFilesDir(null)
    val file = File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    )
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}

@Composable
fun FotoPerfilClickable(fotoUrl: String?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .border(3.dp, GodotColors.Primary, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (!fotoUrl.isNullOrBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(fotoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_foreground)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto placeholder",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Overlay con ícono de cámara
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .size(40.dp),
            shape = CircleShape,
            color = GodotColors.Primary
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Cambiar foto",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun CampoPerfilRow(
    label1: String,
    value1: String,
    label2: String,
    value2: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CampoPerfil(
            label = label1,
            value = value1,
            modifier = Modifier.weight(1f)
        )
        CampoPerfil(
            label = label2,
            value = value2,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CampoPerfil(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = GodotColors.OnBackground.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = GodotColors.OnBackground,
                disabledBorderColor = GodotColors.Primary.copy(alpha = 0.5f),
                disabledContainerColor = GodotColors.Background
            ),
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilContentPreview() {
    val usuarioEjemplo = Usuario(
        nombre = "Demian",
        apellidoPaterno = "Pulgar",
        apellidoMaterno = "González",
        usuario = "demianpulgar",
        correo = "demo@example.com",
        telefono = "+56 9 0000 0000",
        fotoUrl = null
    )
    MaterialTheme {
        Surface(color = GodotColors.Background) {
            PerfilContent(usuario = usuarioEjemplo, onFotoActualizada = {})
        }
    }
}
