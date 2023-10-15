package com.example.encuesta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.encuesta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        var listaEncuestas : List<Encuesta> = listOf()
        var nombre : String
        var sistema_operativo : String
        var especialidades : List<String> = listOf()

        binding.btnValidar.setOnClickListener {
            if (binding.edtNombre.text.isBlank() && binding.swAnonimo.isActivated!!){
                Toast.makeText(this,"Te falta rellenar el nombre o ponerlo como anonimo", Toast.LENGTH_SHORT).show()
            }else if(binding.rdbMac.isChecked!! && binding.rdbWindows.isChecked!! && binding.rdbLinux.isChecked!!){
                Toast.makeText(this,"Elija un sistema operativo", Toast.LENGTH_SHORT).show()
            }else{
                if(binding.swAnonimo.isChecked){
                    nombre = "Animo"
                }else{
                    nombre = binding.edtNombre.text.toString()
                }

                if(binding.rdbMac.isChecked){
                    sistema_operativo = "Mac"
                }else if (binding.rdbWindows.isChecked){
                    sistema_operativo = "Windows"
                }else{
                    sistema_operativo = "Linux"
                }

                if (binding.chbDAM.isChecked){
                    especialidades += "DAM"
                }
                if (binding.chbASIR.isChecked){
                    especialidades += "ASIR"
                }
                if (binding.chbDAW.isChecked){
                    especialidades += "DAW"
                }

                var encuesta = Encuesta(nombre,sistema_operativo, especialidades,0)
                listaEncuestas += encuesta
            }
        }

        binding.btnCuantas.setOnClickListener{
            Toast.makeText(this,"Hay "+ listaEncuestas.size + " encuestas", Toast.LENGTH_SHORT).show()
        }

        binding.btnResumen.setOnClickListener {
            var resumen : String = ""
            for (i in listaEncuestas){
                resumen += i.toString() + "\n"
            }
            binding.txtResumen.setText(resumen)
        }
    }
}