package com.example.proyectohealthdiary

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectohealthdiary.databinding.ActivityMainBinding
import com.example.proyectohealthdiary.ui.detail.DetailFragment
import com.example.proyectohealthdiary.ui.detail.DetailViewModel
import com.example.proyectohealthdiary.ui.detail.DetailViewModelFactory
import com.example.proyectohealthdiary.ui.medicamentos.MedicamentosAdapter
import com.example.proyectohealthdiary.ui.medicinasProvider
import com.example.proyectohealthdiary.ui.server.Medicina
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType {
    BASIC
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val bundle = intent.extras
        var email=bundle?.getString("email")



        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_medicamentos, R.id.navigation_miStock, R.id.navigation_misMedicinas
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        db?.collection("users")?.document("email")?.set(user)

    }

    val user = hashMapOf(
        "first" to "Ada",
        "last" to "junka",
        "born" to 1815
    )
}