import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.ElementoMedBinding
import com.example.proyectohealthdiary.databinding.FragmentMisMedicinasBinding
import com.example.proyectohealthdiary.databinding.ItemMedicinaBinding
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina
import com.example.proyectohealthdiary.ui.server.Medicina
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_medicina.view.*

class MiMedicinaAdapter(val listener: (MiMedicina) -> Unit) : RecyclerView.Adapter<MiMedicinaAdapter.ViewHolder>() {

    var listamedicinas = mutableListOf<MiMedicina>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicina, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicina = listamedicinas[position]
        holder.bind(medicina)
        holder.itemView.setOnClickListener {
            listener(medicina)
        }
        holder.itemView.eliminar.setOnClickListener {
            eliminarMedicina(medicina)
        }
    }

    override fun getItemCount(): Int {
        return listamedicinas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMedicinaBinding.bind(itemView)
        fun bind(medicina: MiMedicina){
            val fotoMed= binding.fotoMed
            val nombreMed= binding.medicinaNombre
            val formatoMed= binding.medicinaFormato
            val laboratorioMed= binding.medicinaLaboratorio


            val nombreSinEspacios = medicina.Nombre?.substringBefore(" ") ?: ""

            nombreMed.text = nombreSinEspacios
            formatoMed.text=medicina.Formato

            Glide.with(fotoMed.context).load(medicina.foto).into(fotoMed)

        }
    }
    private fun eliminarMedicina(medicina: MiMedicina) {
        val db = FirebaseFirestore.getInstance()
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email

        if (currentUserEmail != null) {

            val documentRef = medicina.Nombre?.let { db.collection(currentUserEmail).document(it) }
            if (documentRef != null) {
                documentRef.delete()
                    .addOnSuccessListener {
                        Log.d(TAG, "Documento eliminado correctamente.")
                        listamedicinas.remove(medicina)
                        notifyDataSetChanged()
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error al eliminar el documento", e)
                    }
            }
        }
    }

}
