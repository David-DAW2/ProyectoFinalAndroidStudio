package com.example.proyectohealthdiary.ui.medicamentos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.ui.Medicina

class MedicamentosAdapter(private val listaMedicinas: List<Medicina>): RecyclerView.Adapter<MedicamentosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentosViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return MedicamentosViewHolder(layoutInflater.inflate(R.layout.elemento_med,parent,false))
    }

    override fun onBindViewHolder(holder: MedicamentosViewHolder, position: Int) {

        val item =listaMedicinas[position]
        holder.render(item)

    }

    override fun getItemCount(): Int = listaMedicinas.size
}