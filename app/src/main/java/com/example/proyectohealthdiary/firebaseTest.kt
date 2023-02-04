package com.example.proyectohealthdiary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class firebaseTest : AppCompatActivity() {
    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_test)

    }


// Add a new document with a generated ID

}