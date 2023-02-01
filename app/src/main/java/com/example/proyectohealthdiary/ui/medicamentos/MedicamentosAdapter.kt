package com.example.proyectohealthdiary.ui.medicamentos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.ElementoMedBinding
import com.example.proyectohealthdiary.ui.Medicina
import com.example.proyectohealthdiary.ui.inflate


class MedicamentosAdapter(val listamedicinas: List<Medicina>, val listener: (Medicina) -> Unit):
    RecyclerView.Adapter<MedicamentosAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = parent.inflate(R.layout.elemento_med, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.elemento_med, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicina = listamedicinas[position]
        holder.bind(medicina)

        holder.itemView.setOnClickListener {
            listener(medicina)
        }

    }

    override fun getItemCount(): Int = listamedicinas.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ElementoMedBinding.bind(view)
        fun bind(medicina: Medicina){
            val fotoMed= binding.fotoMed
            val nombreMed= binding.nombreMed
            val cantidadMed= binding.cantidadMed


            nombreMed.text=medicina.nombre
            cantidadMed.text=medicina.miligramos
            Glide.with(fotoMed.context).load(medicina.foto).into(fotoMed)

        }
    }
}