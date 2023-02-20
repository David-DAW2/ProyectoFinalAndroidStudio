package com.example.proyectohealthdiary.ui.mismedicinas.medicinaDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina

class DetailMedicinasViewModel(medicina: MiMedicina): ViewModel() {
    val _medicina = MutableLiveData(medicina)
    val medicina: MutableLiveData<MiMedicina> get() = _medicina

}

@Suppress("UNCHECKED_CAST")
class DetailMedicinasViewModelFactory(private val medicina: MiMedicina): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailMedicinasViewModel(medicina) as T
    }

}