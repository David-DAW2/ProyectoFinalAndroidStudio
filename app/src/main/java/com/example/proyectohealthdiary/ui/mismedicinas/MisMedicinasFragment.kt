package com.example.proyectohealthdiary.ui.mismedicinas

import MiMedicinaAdapter
import android.app.TaskStackBuilder.create
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.ui.mismedicinas.medicinaDetail.medicinaDetailFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class MisMedicinasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MiMedicinaAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mis_medicinas, container, false)
        recyclerView = view.findViewById(R.id.recyclerMedicina)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter =MiMedicinaAdapter { medicina -> navigateTo(medicina)}
        recyclerView.adapter = adapter



        return view


    }
    fun navigateTo(medicina: MiMedicina) {
        parentFragmentManager.commit {

            replace(R.id.fotoMed, medicinaDetailFragment.create(medicina))
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onStart() {
        super.onStart()
        val db = FirebaseFirestore.getInstance()
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
        val collectionRef = currentUserEmail?.let { db.collection(it) }
        if (collectionRef != null) {
            collectionRef.get()
                .addOnSuccessListener { querySnapshot ->
                    val medicinas = mutableListOf<MiMedicina>()
                    for (document in querySnapshot.documents) {
                        val medicina = document.toObject(MiMedicina::class.java)
                        if (medicina != null) {
                            medicinas.add(medicina)
                        }
                    }
                    adapter.listamedicinas = medicinas
                    adapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }
        }
    }

    companion object {
        private const val TAG = "MiMedicinaFragment"
    }


}