package com.example.proyectohealthdiary.ui.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentDetailBinding
import com.example.proyectohealthdiary.ui.Medicina
import com.example.proyectohealthdiary.ui.loadUrl

class DetailFragment : Fragment(R.layout.fragment_detail){

    companion object{
        const val EXTRA_MEDICINA = "DetailActivity:Medicina"
        fun create(medicina: Medicina): DetailFragment =
            DetailFragment().apply {
                arguments = bundleOf(EXTRA_MEDICINA to medicina)
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val medicina = arguments?.getParcelable<Medicina>(EXTRA_MEDICINA)

        if (medicina != null) {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = medicina.nombre
            binding.imagen.loadUrl(medicina.foto)
        }

        binding.aAdir.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("¿Añadir a favoritos?")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        binding.aAdir.setImageDrawable(requireContext().getDrawable(R.drawable.ic_drugs))
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