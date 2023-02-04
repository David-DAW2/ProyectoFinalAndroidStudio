package com.example.proyectohealthdiary.ui.medicamentos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentMedicamentosBinding
import com.example.proyectohealthdiary.ui.server.Medicina
import com.example.proyectohealthdiary.ui.detail.DetailFragment

class MedicamentosFragment : Fragment(R.layout.fragment_medicamentos) {
    private val viewModel: MedicamentosViewModel by viewModels{ MedicamentosViewModelFactory(getString(R.string.api_key))}
    private lateinit var binding: FragmentMedicamentosBinding
    private val adapter = MedicamentosAdapter(){ medicina -> viewModel.navigateTo(medicina)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMedicamentosBinding.bind(view).apply {
            recyclerMedicamentos.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            state.medicinas?.let {
                adapter.listamedicinas = it
                adapter.notifyDataSetChanged()
            }


            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_navigation_medicamentos_to_detailFragment2,
                    bundleOf(DetailFragment.EXTRA_MEDICINA to it)
                )
                viewModel.onNavigateDone()
            }
        }

    }



}




