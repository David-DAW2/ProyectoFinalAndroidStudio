package com.example.proyectohealthdiary.ui.medicamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentMedicamentosBinding
import com.example.proyectohealthdiary.ui.Medicina

class MedicamentosFragment : Fragment() {

private var _binding: FragmentMedicamentosBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

    private lateinit var adapterMed: MedicamentosAdapter
    private lateinit var recyclerView: RecyclerView
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val medicamentosViewModel =
            ViewModelProvider(this).get(MedicamentosViewModel::class.java)

    _binding = FragmentMedicamentosBinding.inflate(inflater, container, false)
    val root: View = binding.root


    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val layoutManager=LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.recyclerMedicamentos)
        recyclerView.layoutManager =layoutManager
        recyclerView.setHasFixedSize(true)
        adapterMed = MedicamentosAdapter(getMedicinas())
        recyclerView.adapter=adapterMed
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            ))
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
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}