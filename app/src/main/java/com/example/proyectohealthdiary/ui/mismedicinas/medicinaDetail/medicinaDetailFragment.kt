package com.example.proyectohealthdiary.ui.mismedicinas.medicinaDetail

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentDetailBinding
import com.example.proyectohealthdiary.databinding.FragmentMedicinaDetailBinding
import com.example.proyectohealthdiary.ui.detail.DetailFragment
import com.example.proyectohealthdiary.ui.detail.DetailViewModel
import com.example.proyectohealthdiary.ui.detail.DetailViewModelFactory
import com.example.proyectohealthdiary.ui.loadUrl
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina
import com.example.proyectohealthdiary.ui.server.Medicina
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.E


class medicinaDetailFragment : Fragment(R.layout.fragment_medicina_detail) {
    private val viewModel: DetailMedicinasViewModel by viewModels {
        DetailMedicinasViewModelFactory(arguments?.getParcelable<MiMedicina>(medicinaDetailFragment.EXTRA_MiMedicina)!!)
    }

    companion object {
        const val EXTRA_MiMedicina = "DetailActivity:MiMedicina"

        fun create(medicina: MiMedicina): medicinaDetailFragment =
            medicinaDetailFragment().apply {
                arguments = bundleOf(EXTRA_MiMedicina to medicina)
            }
    }

    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMedicinaDetailBinding.bind(view)


        viewModel.medicina.observe(viewLifecycleOwner) { medicina ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = medicina.Nombre

            binding.imagen.loadUrl(medicina.foto)
            binding.contenido.text = medicina.Nombre
            bindingDetail(binding.autor, medicina)
            val email = FirebaseAuth.getInstance().currentUser?.email
            Toast.makeText(context, email.toString(), Toast.LENGTH_SHORT).show()
          /*  binding.eliminar.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("¿Desea eliminar este medicamento?")
                    .setPositiveButton("Sí") { dialog, id ->
                        medicina.Nombre?.let { it1 ->
                            db?.collection(email!!)?.document(it1)?.delete()
                                ?.addOnSuccessListener {
                                    Toast.makeText(
                                        requireContext(),
                                        "Medicamento eliminado exitosamente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Regresar al fragmento anterior
                                    requireActivity().supportFragmentManager.popBackStack()
                                }
                                ?.addOnFailureListener {
                                    Toast.makeText(
                                        requireContext(),
                                        "Error eliminando medicamento",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // User cancelled the dialog
                    }
                builder.create()
                builder.show()


            }*/


        }


    }

    private fun bindingDetail(detalles: TextView, medicina: MiMedicina) {
        detalles.text = buildSpannedString {
            bold { append("Laboratorio: ") }
            appendLine(medicina.Nombre)
            append()
            bold { append("Formato: ") }
            appendLine(medicina.Formato)
            append()
            bold { append("Laboratorio: ") }

            appendLine(medicina.Laboratorio)
        }
    }
}

