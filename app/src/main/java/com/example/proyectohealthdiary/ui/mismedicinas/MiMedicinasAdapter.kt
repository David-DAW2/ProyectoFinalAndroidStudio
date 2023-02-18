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


            nombreMed.text = medicina.nombre
            formatoMed.text=medicina.Formato

            Glide.with(fotoMed.context).load(medicina.foto).into(fotoMed)
        }
    }
}
