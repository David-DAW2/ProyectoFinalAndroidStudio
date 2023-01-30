package com.example.proyectohealthdiary.ui.medicamentos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.ui.Medicina

class MedicamentosViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val fotoMed= view.findViewById<ImageView>(R.id.fotoMed)
    val nombreMed= view.findViewById<TextView>(R.id.nombreMed)
    val cantidadMed= view.findViewById<TextView>(R.id.cantidadMed)


    fun render(medicina: Medicina){
        nombreMed.text=medicina.nombre
        cantidadMed.text=medicina.miligramos
        Glide.with(fotoMed.context).load(medicina.foto).into(fotoMed)
    }
}