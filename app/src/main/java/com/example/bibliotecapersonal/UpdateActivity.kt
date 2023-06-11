package com.example.bibliotecapersonal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bibliotecapersonal.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referenceCodigo = binding.referenceNombre.text.toString()
            val updateNombre = binding.updateNombre.text.toString()
            val updateCalificacion = binding.updateCalificacion.text.toString()
            val updateTipo = binding.updateTipo.text.toString()

            updateData(referenceCodigo,updateNombre,updateCalificacion,updateTipo)
        }
    }

    private fun updateData(codigo: String, nombre: String, calificacion: String, tipo: String) {

        database = FirebaseDatabase.getInstance().getReference("Biblioteca Personal")

        val user = mapOf<String,String>(
            "nombre" to nombre,
            "calificacion" to calificacion,
            "tipo" to tipo
        )

        database.child(codigo).updateChildren(user).addOnSuccessListener {

            binding.referenceNombre.text.clear()
            binding.updateNombre.text.clear()
            binding.updateCalificacion.text.clear()
            binding.updateTipo.text.clear()
            Toast.makeText(this,"Actualizado Exitosamente",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{
            Toast.makeText(this,"Error de Actualizacion",Toast.LENGTH_SHORT).show()
        }}
}