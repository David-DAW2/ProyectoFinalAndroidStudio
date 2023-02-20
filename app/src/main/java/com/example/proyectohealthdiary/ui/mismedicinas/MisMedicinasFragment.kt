package com.example.proyectohealthdiary.ui.mismedicinas

import MiMedicinaAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.ui.mismedicinas.medicinaDetail.medicinaDetailFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MisMedicinasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MiMedicinaAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private val viewModel: MisMedicinasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mis_medicinas, container, false)
        recyclerView = view.findViewById(R.id.recyclerMedicina)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter =MiMedicinaAdapter { medicina -> navigateTo(medicina)}
        recyclerView.adapter = adapter

        return view
    }

    fun navigateTo(medicina: MiMedicina) {
        findNavController().navigate(
            R.id.action_navigation_misMedicinas_to_medicinaDetailFragment,
            bundleOf(medicinaDetailFragment.EXTRA_MiMedicina to medicina)
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMedicinas()
        viewModel.medicinas.observe(this) { medicinas ->
            adapter.listamedicinas = medicinas as MutableList<MiMedicina>
            adapter.notifyDataSetChanged()
        }
    }
}