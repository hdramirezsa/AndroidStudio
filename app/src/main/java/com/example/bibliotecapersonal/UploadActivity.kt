package com.example.bibliotecapersonal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bibliotecapersonal.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {

            val nombre = binding.uploadNombre.text.toString()
            val calificacion = binding.uploadCalificacion.text.toString()
            val tipo = binding.uploadTipo.text.toString()
            val codigo = binding.uploadCodigo.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Biblioteca Personal")
            val users = UserData(nombre,calificacion,tipo,codigo)
            database.child(codigo).setValue(users).addOnSuccessListener {

                binding.uploadNombre.text.clear()
                binding.uploadCalificacion.text.clear()
                binding.uploadTipo.text.clear()
                binding.uploadCodigo.text.clear()

                Toast.makeText(this,"Registro Ingresado",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }

    }
}