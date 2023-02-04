package com.example.proyectohealthdiary.ui.login

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.proyectohealthdiary.MainActivity
import com.example.proyectohealthdiary.ProviderType
import com.example.proyectohealthdiary.R
import com.example.proyectohealthdiary.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var email=binding.txtEmail
        var pass=binding.txtPass
        var buttonReg=binding.linkToRegister
        var botonLog=binding.btnLogin


         buttonReg.setOnClickListener{
             if (email.text.isNotEmpty()&&pass.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),pass.text.toString()).addOnCompleteListener(){

                    if (it.isSuccessful){
                        mensajeApproved("Registrado con exito")
                        irAMain(it.result?.user?.email?:"",ProviderType.BASIC)
                    }else{
                        mensajeError("No se ha podido registrar o ya existe")

                    }
                }
             }

         }
        botonLog.setOnClickListener{
            if (email.text.isNotEmpty()&&pass.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),pass.text.toString()).addOnCompleteListener(){

                    if (it.isSuccessful){
                        mensajeApproved("Logeado con exito")
                        irAMain(it.result?.user?.email?:"",ProviderType.BASIC)
                    }else{
                        mensajeError("Error al acceder")

                    }
                }
            }

        }



}
    private fun irAMain(email:String, provider: ProviderType){
        val intentMain=Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(intentMain)
    }

    private fun mensajeError(textoMen:String) {
        val mensaje= AlertDialog.Builder(this)
        mensaje.setTitle("Error")
        mensaje.setMessage(textoMen)
        mensaje.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=mensaje.create()
        dialog.show()
    }
    private fun mensajeApproved(textoMen:String){
        val mensaje= AlertDialog.Builder(this)
        mensaje.setTitle("Done")
        mensaje.setMessage(textoMen)
        mensaje.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=mensaje.create()
        dialog.show()
    }


}



