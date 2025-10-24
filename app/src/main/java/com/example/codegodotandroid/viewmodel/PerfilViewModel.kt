package com.example.codegodotandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.codegodotandroid.model.Usuario
import com.example.codegodotandroid.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class PerfilUiState {
    object Loading : PerfilUiState()
    data class Success(val usuario: Usuario) : PerfilUiState()
    data class Error(val mensaje: String) : PerfilUiState()
    object NoAutenticado : PerfilUiState()
}

class PerfilViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PerfilUiState>(PerfilUiState.Loading)
    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    init {
        cargarPerfil()
    }

    fun cargarPerfil() {
        viewModelScope.launch {
            _uiState.value = PerfilUiState.Loading

            try {
                val usuario = repository.obtenerUsuarioLogeado()

                if (usuario != null) {
                    _uiState.value = PerfilUiState.Success(usuario)
                } else {
                    _uiState.value = PerfilUiState.NoAutenticado
                }
            } catch (e: Exception) {
                _uiState.value = PerfilUiState.Error("Error al cargar el perfil: ${e.message}")
            }
        }
    }

    fun actualizarFotoPerfil(fotoUri: String) {
        viewModelScope.launch {
            try {
                val estadoActual = _uiState.value
                if (estadoActual is PerfilUiState.Success) {
                    val usuarioActualizado = estadoActual.usuario.copy(fotoUrl = fotoUri)
                    repository.guardarUsuarioLogeado(usuarioActualizado)
                    _uiState.value = PerfilUiState.Success(usuarioActualizado)
                }
            } catch (e: Exception) {
                _uiState.value = PerfilUiState.Error("Error al actualizar la foto: ${e.message}")
            }
        }
    }

    fun cerrarSesion() {
        repository.cerrarSesion()
        _uiState.value = PerfilUiState.NoAutenticado
    }
}

// Factory para crear el ViewModel con dependencias
class PerfilViewModelFactory(
    private val repository: UsuarioRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PerfilViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
