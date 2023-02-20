import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectohealthdiary.databinding.ItemStockBinding
import com.example.proyectohealthdiary.ui.mismedicinas.MiMedicina
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MiStockAdapter(val listener: (MiMedicina) -> Unit) : RecyclerView.Adapter<MiStockAdapter.ViewHolder>() {

    var listamedicinas = mutableListOf<MiMedicina>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.example.proyectohealthdiary.R.layout.item_stock, parent, false)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemStockBinding.bind(itemView)

        fun bind(medicina: MiMedicina) {
            val fotoMed = binding.fotoMed
            val nombreMed = binding.medicinaNombre
            val stockSpinner = binding.medicinaStock

            val nombreSinEspacios = medicina.Nombre?.substringBefore(" ") ?: ""

            nombreMed.text = nombreSinEspacios

            Glide.with(fotoMed.context).load(medicina.foto).into(fotoMed)

            val adapter = ArrayAdapter.createFromResource(
                itemView.context,
                com.example.proyectohealthdiary.R.array.opciones_stock,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            stockSpinner.adapter = adapter

            // Update the background color of the item when a new option is selected in the spinner
            stockSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val stockOption = parent?.getItemAtPosition(position).toString()

                    setStockColor(stockOption, itemView, medicina)

                    // Update the stock value of the medicine

                    // Save the stock value to Firestore
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }
            }

            // Set the selected item in the spinner to the current stock value of the medicine
            medicina.stock?.let {
                val position = adapter.getPosition(it)
                stockSpinner.setSelection(position)
                setStockColor(it, itemView, medicina)
            }
        }

        private fun setStockColor(stockOption: String?, itemView: View, medicina: MiMedicina) {
            when (stockOption) {
                "Stock" -> {
                    val db = FirebaseFirestore.getInstance()
                    val email = FirebaseAuth.getInstance().currentUser?.email
                    val medicinaRef = email?.let { db.collection(it) }
                    if (medicinaRef != null) {
                        medicinaRef.document("${medicina.Nombre}")
                            .update(mapOf("stock" to stockOption))
                    }
                    medicina.stock = stockOption
                    itemView.setBackgroundColor(Color.GREEN)
                }
                "Poco stock" -> {
                    val db = FirebaseFirestore.getInstance()
                    val email = FirebaseAuth.getInstance().currentUser?.email
                    val medicinaRef = email?.let { db.collection(it) }
                    if (medicinaRef != null) {
                        medicinaRef.document("${medicina.Nombre}")
                            .update(mapOf("stock" to stockOption))
                    }
                    medicina.stock = stockOption
                    itemView.setBackgroundColor(Color.YELLOW)
                }
                "Sin stock" -> {
                    val db = FirebaseFirestore.getInstance()
                    val email = FirebaseAuth.getInstance().currentUser?.email
                    val medicinaRef = email?.let { db.collection(it) }
                    if (medicinaRef != null) {
                        medicinaRef.document("${medicina.Nombre}")
                            .update(mapOf("stock" to stockOption))
                    }
                    medicina.stock = stockOption
                    itemView.setBackgroundColor(Color.RED)
                }
                else -> {
                    itemView.setBackgroundColor(Color.WHITE)
                }
            }


        }
    }
}