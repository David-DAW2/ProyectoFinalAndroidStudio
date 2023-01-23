package com.example.proyectohealthdiary.ui.mismedicinas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectohealthdiary.databinding.FragmentMismedicinasBinding

class MisMedicinasFragment : Fragment() {

private var _binding: FragmentMismedicinasBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val misMedicinasViewModel =
            ViewModelProvider(this).get(MisMedicinasViewModel::class.java)

    _binding = FragmentMismedicinasBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textDashboard
    misMedicinasViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}