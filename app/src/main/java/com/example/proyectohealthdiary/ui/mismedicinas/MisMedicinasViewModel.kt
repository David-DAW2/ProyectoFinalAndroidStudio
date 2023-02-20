package com.example.proyectohealthdiary.ui.mismedicinas

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MisMedicinasViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
    private val collectionRef = currentUserEmail?.let { db.collection(it) }

    private val _medicinas = MutableLiveData<List<MiMedicina>>()
    val medicinas: LiveData<List<MiMedicina>>
        get() = _medicinas

    fun loadMedicinas() {
        collectionRef?.get()
            ?.addOnSuccessListener { querySnapshot ->
                val medicinas = mutableListOf<MiMedicina>()
                for (document in querySnapshot.documents) {
                    val medicina = document.toObject(MiMedicina::class.java)
                    if (medicina != null) {
                        medicinas.add(medicina)
                    }
                }
                _medicinas.value = medicinas
            }
            ?.addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    companion object {
        private const val TAG = "MisMedicinasViewModel"
    }
}