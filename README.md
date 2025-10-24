# CodeGodot Android - Aplicación Móvil

Aplicación Android que replica la funcionalidad del proyecto web CodeGodot React.

## Arquitectura

Este proyecto utiliza **MVVM (Model-View-ViewModel)** con Jetpack Compose:

### Estructura de Carpetas

```
app/src/main/java/com/example/codegodotandroid/
├── model/
│   └── Usuario.kt              # Modelo de datos del usuario
├── repository/
│   └── UsuarioRepository.kt    # Acceso a datos (SharedPreferences)
├── viewmodel/
│   └── PerfilViewModel.kt      # Lógica de negocio
├── view/
│   ├── InicioScreen.kt         # Pantalla de inicio (Hero + secciones)
│   └── PerfilScreen.kt         # Pantalla de perfil de usuario
├── navigation/
│   ├── Screen.kt               # Definición de rutas
│   └── MainNavigation.kt       # Sistema de navegación con barra inferior
├── util/
│   └── UsuarioTestUtil.kt      # Utilidad para testing
└── MainActivity.kt             # Actividad principal
```

## Pantallas Implementadas

### ✅ Pantalla de Inicio
- **Hero Section** con fondo azul (#0B63A8) y texto "Copiar y pegar"
- **Sección "¿Qué entregamos?"** con lista de características
- **Sección "¿Qué es Godot?"** con información del motor
- **Sección "Herramientas"** con 3 tarjetas (Rápido, Gratis, Para todo mundo)
- Diseño fiel al proyecto React original
- Scroll vertical para todas las secciones

### ✅ Pantalla de Perfil
- Foto de perfil circular con borde azul
- Información del usuario en campos de texto deshabilitados
- Diseño responsive con campos en dos columnas
- Mensaje informativo
- Arquitectura MVVM completa

### ✅ Sistema de Navegación
- **Barra de navegación inferior** con 2 opciones:
  - 🏠 Inicio
  - 👤 Perfil
- Cambio fluido entre pantallas
- Iconos Material Design
- Colores corporativos de CodeGodot

## Dependencias

El proyecto utiliza las siguientes librerías:

- **Jetpack Compose**: UI moderna y declarativa
- **Material Design 3**: Componentes visuales
- **ViewModel Compose**: Manejo del estado y ciclo de vida
- **Coil**: Carga de imágenes desde URL
- **Gson**: Serialización/deserialización de JSON
- **Material Icons**: Iconos de navegación

## Pasos para ejecutar

1. **Eliminar carpeta duplicada** (si aún no lo has hecho):
   - Elimina la carpeta: `app/src/main/java/com/example/codegotandroid` (sin la segunda 'o')
   - Solo debe existir: `app/src/main/java/com/example/codegodotandroid`

2. **Sincronizar Gradle**: 
   - Abre el proyecto en Android Studio
   - Haz clic en "Sync Now" en la barra superior
   - Espera a que descargue todas las dependencias

3. **Ejecutar la aplicación**:
   - Haz clic en el botón "Run" (▶️ verde)
   - La app se abrirá en la pantalla de Inicio
   - Usa la barra inferior para navegar entre Inicio y Perfil

4. **Usuario de prueba**:
   - Se crea automáticamente al abrir la app
   - Datos: Demian Pulgar González / demianpulgar

## Características implementadas

✅ Pantalla de Inicio completa (replica del proyecto React)
✅ Pantalla de Perfil con diseño mejorado
✅ Navegación con barra inferior (Bottom Navigation)
✅ Arquitectura MVVM completa
✅ Manejo de estados (Loading, Success, Error, NoAutenticado)
✅ Almacenamiento local con SharedPreferences
✅ Diseño responsive para móvil
✅ Colores corporativos (#0B63A8)

## Próximas funcionalidades

- [ ] Agregar imágenes reales (Logo.png y Godot.png)
- [ ] Pantalla de Login
- [ ] Pantalla de Comunidad
- [ ] Edición de perfil
- [ ] Integración con API backend
- [ ] Subir foto de perfil

## Notas para agregar imágenes

Para agregar las imágenes Logo.png y Godot.png:

1. Copia tus imágenes a: `app/src/main/res/drawable/`
2. Renómbralas a minúsculas: `logo.png` y `godot.png`
3. En el código, reemplaza `R.drawable.ic_launcher_foreground` por:
   - `R.drawable.logo` (para el logo de CodeGodot)
   - `R.drawable.godot` (para el logo de Godot Engine)

## Tecnologías

- Kotlin
- Jetpack Compose
- Material Design 3
- MVVM Architecture
- Coroutines & Flow
- SharedPreferences para almacenamiento local
- Navigation Component (Bottom Navigation)
