import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MiMedicinaFragment : Fragment(R.layout.fragment_mismedicinas) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MiMedicinaAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mismedicinas, container, false)
        recyclerView = view.findViewById(R.id.recyclerMedicina)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter =MiMedicinaAdapter { medicina -> }
        recyclerView.adapter = adapter
        return view
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
