package com.appfer.movieapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appfer.movieapp.R
import com.appfer.movieapp.databinding.ActivityLoginBinding
import com.appfer.movieapp.databinding.ActivityRegistroBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonreg.setOnClickListener{
            val email = binding.txtcorreoreg.editText?.text.toString()
            val password = binding.txtpasswordreg.editText?.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Ingrese los datos", Toast.LENGTH_SHORT).show()
            }else{
                binding.progressBar.visibility = android.view.View.VISIBLE
              registronuevo(email,password)
            }
        }
    }

    fun registronuevo(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                binding.progressBar.visibility = android.view.View.GONE


                if (task.isSuccessful) {
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener { email ->
                            if (email.isSuccessful) {
                                Toast.makeText(this, "Registro exitoso. Verifica tu correo.", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(this, "Error al enviar el correo de verificación.", Toast.LENGTH_SHORT).show()
                            }

                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                } else {
                    // En caso de error al registrar al usuario
                    Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
