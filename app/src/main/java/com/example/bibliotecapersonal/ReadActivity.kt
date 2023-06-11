package com.example.bibliotecapersonal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bibliotecapersonal.databinding.ActivityReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchCodigo : String = binding.searchCodigo.text.toString()
            if  (searchCodigo.isNotEmpty()){
                readData(searchCodigo)
            }else{
                Toast.makeText(this,"Por Favor Ingrese el Codigo de Barras",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(codigo: String) {
        database = FirebaseDatabase.getInstance().getReference("Biblioteca Personal")
        database.child(codigo).get().addOnSuccessListener {

            if (it.exists()){

                val nombre = it.child("nombre").value
                val calificacion = it.child("calificacion").value
                val tipo = it.child("tipo").value
                Toast.makeText(this,"Registro Encontrado",Toast.LENGTH_SHORT).show()
                binding.searchCodigo.text.clear()
                binding.readNombre.text = nombre.toString()
                binding.readCalificacion.text = calificacion.toString()
                binding.readTipo.text = tipo.toString()
            }else{
                Toast.makeText(this,"Codigo no Existe",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Algo Salio Mal",Toast.LENGTH_SHORT).show()
        }
    }
}