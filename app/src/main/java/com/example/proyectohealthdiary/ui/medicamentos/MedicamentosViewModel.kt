package com.example.proyectohealthdiary.ui.medicamentos

import androidx.lifecycle.*
import com.example.proyectohealthdiary.ui.server.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MedicamentosViewModel (apiKey: String): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)


            var result = RemoteConnection.service.getMed(apiKey)

            val meds = result.resultados?.map {
                Medicina(
                    it.nombre,
                    it.receta,
                    it.formaFarmaceutica.nombre,
                    it.labtitular,
                    it.fotos[0].url,
                    it.dosis
                )
            }

            _state.value = _state.value?.copy(loading = false, medicinas = meds)


        }
    }

    fun navigateTo(medicina: Medicina) {
        _state.value = _state.value?.copy(navigateTo = medicina)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val medicinas: List<Medicina>? = null,
        val navigateTo: Medicina? = null
    )
}
class MedicamentosViewModelFactory(private val apiKey: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MedicamentosViewModel(apiKey) as T
    }
}