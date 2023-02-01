package com.example.proyectohealthdiary.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectohealthdiary.ui.Medicina


class DetailViewModel(medicina: com.example.proyectohealthdiary.ui.server.Medicina): ViewModel() {
    private val _medicina = MutableLiveData(medicina)
    val medicina: MutableLiveData<com.example.proyectohealthdiary.ui.server.Medicina> get() = _medicina
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val medicina: com.example.proyectohealthdiary.ui.server.Medicina): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(medicina) as T
    }

}