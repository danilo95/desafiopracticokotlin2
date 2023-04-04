package com.example.desafiopractico2.Repository

import androidx.lifecycle.MutableLiveData
import com.example.desafiopractico2.Models.Historial
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistorialRepository {
    var firebaseAuth = FirebaseAuth.getInstance()

    //usar el PATCH EXACTO de como aparece en la database
    private val databaseReference =
        FirebaseDatabase.getInstance().getReference("historial").orderByChild("musuarioEmail")
            .equalTo(firebaseAuth.currentUser?.email)

    //validar que solo exista una instancia
    @Volatile
    private var INSTANCE: HistorialRepository? = null

    fun getInstance(): HistorialRepository {
        return INSTANCE ?: synchronized(this) {
            val instance = HistorialRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadMedicinas(historialList: MutableLiveData<List<Historial>>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _allHistorial:
                            List<Historial> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Historial::class.java)!!
                    }
                    historialList.postValue(_allHistorial)
                } catch (e: Exception) {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}