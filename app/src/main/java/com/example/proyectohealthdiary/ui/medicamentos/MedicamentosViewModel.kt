package com.example.proyectohealthdiary.ui.medicamentos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicamentosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Tsdddddddddddddddddddddddddd"
    }
    val text: LiveData<String> = _text
}