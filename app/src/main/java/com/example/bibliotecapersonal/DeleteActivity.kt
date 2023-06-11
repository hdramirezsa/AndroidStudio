package com.example.bibliotecapersonal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bibliotecapersonal.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val codigo = binding.deleteCodigo.text.toString()
            if (codigo.isNotEmpty())
                deleteData(codigo)
            else
                Toast.makeText(this, "Por Favor Ingrese el Codigo ISBN", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteData(codigo: String){
        database = FirebaseDatabase.getInstance().getReference("Biblioteca Personal")
        database.child(codigo).removeValue().addOnSuccessListener {
            binding.deleteCodigo.text.clear()
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "No Fue Posible Eliminar", Toast.LENGTH_SHORT).show()
        }
    }
}