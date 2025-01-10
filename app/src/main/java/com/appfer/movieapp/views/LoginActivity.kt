package com.appfer.movieapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appfer.movieapp.R
import com.appfer.movieapp.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonlogin.setOnClickListener {
            val email1 = binding.txtcorreo.editText?.text.toString()
            val contra = binding.txtcontra.editText?.text.toString()
            if (email1.isNotEmpty() && contra.isNotEmpty()){
                loginusuario(email1,contra)
            }else{
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtcuenta.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }
    }

    fun loginusuario(email1: String, contra: String) {
        auth.signInWithEmailAndPassword(email1, contra)
            .addOnCompleteListener { task ->
                if (task.isSuccessful ) {
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Verifica tu correo", Toast.LENGTH_SHORT).show()
                        auth.signOut()
                    }
                } else {
                    Toast.makeText(this, "Verifica primero tu correo", Toast.LENGTH_SHORT).show()

                }
            }
    }
}



