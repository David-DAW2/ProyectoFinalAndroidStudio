import android.R
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectohealthdiary.databinding.ItemStockBinding
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_medicina.view.*

class MiStockAdapter(val listener: (MiMedicina) -> Unit) : RecyclerView.Adapter<MiStockAdapter.ViewHolder>() {

    var listamedicinas = mutableListOf<MiMedicina>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.proyectohealthdiary.R.layout.item_stock, parent, false)
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
        private val binding = ItemStockBinding.bind(itemView)
        fun bind(medicina: MiMedicina){
            val fotoMed= binding.fotoMed
            val nombreMed= binding.medicinaNombre
            val stockSpinner = binding.medicinaStock

            val nombreSinEspacios = medicina.Nombre?.substringBefore(" ") ?: ""

            nombreMed.text = nombreSinEspacios

            Glide.with(fotoMed.context).load(medicina.foto).into(fotoMed)

            setStockColor(medicina.color, itemView)

            val adapter = ArrayAdapter.createFromResource(
                itemView.context,
                com.example.proyectohealthdiary.R.array.opciones_stock,
                R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            stockSpinner.adapter = adapter

            // Update the background color of the item when a new option is selected in the spinner
            stockSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    setStockColor(parent?.getItemAtPosition(position).toString(), itemView)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }
            }
        }


        private fun setStockColor(stockOption: String?, itemView: View) {
            when (stockOption) {
                "Stock" -> itemView.setBackgroundColor(Color.GREEN)
                "Poco stock" -> itemView.setBackgroundColor(Color.YELLOW)
                "Sin stock" -> itemView.setBackgroundColor(Color.RED)
                else -> itemView.setBackgroundColor(Color.WHITE)
            }
        }
    }
}