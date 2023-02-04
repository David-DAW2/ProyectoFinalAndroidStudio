package com.example.proyectohealthdiary.ui.detail

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentDetailBinding
import com.example.proyectohealthdiary.ui.loadUrl
import com.example.proyectohealthdiary.ui.server.Medicina
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DetailFragment : Fragment(R.layout.fragment_detail){
    private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Medicina>(EXTRA_MEDICINA)!!)
    }
    companion object{
        const val EXTRA_MEDICINA = "DetailActivity:Medicina"
    }

    //private val viewModel: DetailViewModel by viewModels()
    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)


        viewModel.medicina.observe(viewLifecycleOwner){ medicina ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = medicina.nombre

            binding.imagen.loadUrl(medicina.foto)
            binding.contenido.text = medicina.nombre
            bindingDetail(binding.autor, medicina)
            val email = FirebaseAuth.getInstance().currentUser?.email
            Toast.makeText(context,email.toString(),Toast.LENGTH_SHORT).show()
            binding.aAdir.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("¿Desea añadir este medicamento a sus medicinas?")
                    .setPositiveButton("Ok",
                        DialogInterface.OnClickListener { dialog, id ->
                            binding.aAdir.setImageDrawable(requireContext().getDrawable(R.drawable.ic_drugs))
                            db?.collection(email!!)?.document(medicina.nombre.toString())?.set(hashMapOf(
                                "Nombre" to medicina.nombre,
                                "Formato" to medicina.nomFormaFarmaceutica,
                                "Laboratorio" to medicina.nombreLab,
                                "foto" to medicina.foto,
                            ))
                        })
                    .setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                builder.create()
                builder.show()
            }


        }



    }

    private fun bindingDetail(detalles: TextView, medicina: Medicina) {
        detalles.text = buildSpannedString {
            bold { append("Laboratorio: ") }
            appendLine(medicina.nombreLab)
            append()
            bold { append("Formato: ") }
            appendLine(medicina.nomFormaFarmaceutica)
            append()
            bold { append("Receta: ") }
            if (medicina.receta==true){
                appendLine("Necesaria receta")

            }else{
                appendLine("Necesaria receta")

            }
        }
    }

}