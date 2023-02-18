package com.example.proyectohealthdiary.ui.mismedicinas.medicinaDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.FragmentMedicinaDetailBinding
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina
import kotlin.math.E

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [medicinaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class medicinaDetailFragment : Fragment() {

    companion object{
        const val EXTRA_MiMedicina = "DetailActivity:MiMedicina"

        fun create(medicina: MiMedicina): medicinaDetailFragment =
            medicinaDetailFragment().apply {
                arguments = bundleOf(EXTRA_MiMedicina to medicina)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMedicinaDetailBinding.bind(view)

        val medicina = arguments?.getParcelable<MiMedicina>(EXTRA_MiMedicina)

        if (medicina != null) {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = medicina.nombre
            Glide.with(binding.imagen)
                .load(medicina.foto)
                .into(binding.imagen)
        }
    }

}