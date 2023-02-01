package com.example.proyectohealthdiary.ui.medicamentos

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentMedicamentosBinding
import com.example.proyectohealthdiary.ui.Medicina
import com.example.proyectohealthdiary.ui.detail.DetailFragment

class MedicamentosFragment : Fragment(R.layout.fragment_medicamentos) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMedicamentosBinding.bind(view).apply {
            recyclerMedicamentos.adapter = MedicamentosAdapter(getMedicinas()){ medicina ->
                navigateTo(medicina)
            }
        }

    }

    private fun navigateTo(medicina: Medicina) {
        findNavController().navigate(
            R.id.action_navigation_medicamentos_to_detailFragment2,
            bundleOf(DetailFragment.EXTRA_MEDICINA to medicina)
        )

    }

}



    fun getMedicinas(): List<Medicina> {
        val listaMedicamentos= listOf<Medicina>(
            Medicina("ibuprofeno", "5gr","https://picsum.photos/seed/picsum/150/150"),
            Medicina("Paracetamol", "10gr","https://picsum.photos/seed/picsum/150/150"),
            Medicina("Dalsin", "50ml","https://picsum.photos/seed/picsum/150/150"),
            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),
            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),

            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),
            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),

            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),

            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),

            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),

            Medicina("Oxycontin", "40gr","https://picsum.photos/seed/picsum/150/150"),


            )
        return listaMedicamentos
    }

