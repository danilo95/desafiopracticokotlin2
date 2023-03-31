package com.example.desafiopractico2.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiopractico2.Repository.MedicamentosRepository

class MedicamentosViewModel:ViewModel() {

    private val repository: MedicamentosRepository
    private val _allMedicinas=MutableLiveData<List<Medicamentos>>()
    val allMedicinas: LiveData<List<Medicamentos>> = _allMedicinas

    init {

        //iniciamos nuestro repositorio e instanciamos la funcion para cargar
        repository=MedicamentosRepository().getInstance()
        repository.loadMedicinas(_allMedicinas)
    }

}