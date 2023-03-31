package com.example.desafiopractico2.Repository

import androidx.lifecycle.MutableLiveData
import com.example.desafiopractico2.Models.Medicamentos
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MedicamentosRepository {

    //usar el PATCH EXACTO de como aparece en la database
    private val databaseReference = FirebaseDatabase.getInstance().getReference("medicinas")

    //validar que solo exista una instancia
    @Volatile
    private var INSTANCE: MedicamentosRepository? = null

    fun getInstance(): MedicamentosRepository {
        return INSTANCE ?: synchronized(this) {
            val instance = MedicamentosRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadMedicinas(medicamentosList: MutableLiveData<List<Medicamentos>>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _medicamentosList:
                            List<Medicamentos> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Medicamentos::class.java)!!
                    }
                    medicamentosList.postValue(_medicamentosList)
                } catch (e: Exception) {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}