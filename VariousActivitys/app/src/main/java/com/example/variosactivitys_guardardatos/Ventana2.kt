package com.example.variosactivitys_guardardatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.variosactivitys_guardardatos.databinding.ActivityMainBinding
import modelo.Almacen_personas
import modelo.Persona

class Ventana2 : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre = intent.getStringExtra("nombre")
        var edad = intent.getStringExtra("edad")
        var persona: Persona = Persona(nombre,edad)

        binding.cajaNombre.setText(nombre)
        binding.cajaEdad.setText(edad)

        Almacen_personas.aniadirPersona(persona)

        var cadena: String = ""
        var i:Int = 1
        for (p in Almacen_personas.personas){
            cadena+=" "+i+". "+p.nombre+" "+p.edad + "\n"
            i++
        }
        
        binding.boton.setOnClickListener{
            finish()
        }
    }
}
