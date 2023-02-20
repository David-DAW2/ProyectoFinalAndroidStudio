package com.example.proyectohealthdiary.ui.mistock

import MiMedicinaAdapter
import MiStockAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentMistockBinding
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina
import com.example.proyectohealthdiary.ui.mismedicinas.MisMedicinasViewModel
import com.example.proyectohealthdiary.ui.mismedicinas.medicinaDetail.medicinaDetailFragment

class MiStockFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MiStockAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private val viewModel: MiStockViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mistock, container, false)
        recyclerView = view.findViewById(R.id.recyclerStock)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter =MiStockAdapter { medicina -> }
        recyclerView.adapter = adapter

        return view
    }



    override fun onStart() {
        super.onStart()
        viewModel.loadMedicinas()
        viewModel.medicinas.observe(this) { medicinas ->
            adapter.listamedicinas = medicinas as MutableList<MiMedicina>
            adapter.notifyDataSetChanged()

            for (medicina in medicinas) {
                val stock = medicina.stock
                Log.d("MiStockFragment", "Stock for ${medicina.Nombre}: $stock")
            }
        }
    }
}