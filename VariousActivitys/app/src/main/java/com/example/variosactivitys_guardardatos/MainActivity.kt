package com.example.variosactivitys_guardardatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.variosactivitys_guardardatos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.boton.setOnClickListener {
            IrAVentana2()
        }
    }

    private fun IrAVentana2() {
        var miIntent: Intent = Intent(this,Ventana2::class.java)
        //miIntent.putExtra("nombre",binding.cajaNombre.text.toString())
        //miIntent.putExtra("edad",binding.cajaEdad.text.toString())
        startActivity(miIntent)
    }
}

