package com.example.desafiopractico2.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiopractico2.Repository.HistorialRepository

class HistorialViewModel : ViewModel() {

    private val repository: HistorialRepository
    private val _allHistorial = MutableLiveData<List<Historial>>()
    val allHistorial: LiveData<List<Historial>> = _allHistorial

    init {

        //iniciamos nuestro repositorio e instanciamos la funcion para cargar
        repository = HistorialRepository().getInstance()
        repository.loadMedicinas(_allHistorial)
    }

}