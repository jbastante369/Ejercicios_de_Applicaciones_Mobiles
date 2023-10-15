package com.example.encuesta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.example.encuesta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listaEncuestas : List<Encuesta> = listOf()
        var nombre : String
        var sistema_operativo : String
        var especialidades : List<String> = listOf()

        binding.btnValidar.setOnClickListener {
            if (binding.edtNombre.text.isEmpty() && !binding.swAnonimo.isChecked){
                Toast.makeText(this,"Te falta rellenar el nombre o ponerlo como anonimo", Toast.LENGTH_SHORT).show()
            }else if(!binding.rdbMac.isChecked && !binding.rdbWindows.isChecked && !binding.rdbLinux.isChecked){
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

                binding.sbHoras.setOnSeekBarChangeListener(object:
                    SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        var progreso = binding.sbHoras.progress
                        binding.txtNumHoras.setText(progreso.toInt())
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        var progreso = binding.sbHoras.progress
                        binding.txtNumHoras.setText(progreso.toInt())
                    }
                })

                var encuesta = Encuesta(nombre,sistema_operativo, especialidades,0)
                listaEncuestas += encuesta

                binding.edtNombre.setText("")
                binding.swAnonimo.isChecked = false
                binding.rdbMac.isChecked = false
                binding.rdbWindows.isChecked = false
                binding.rdbLinux.isChecked = false
                binding.chbDAM.isChecked = false
                binding.chbASIR.isChecked = false
                binding.chbDAW.isChecked = false
            }
        }

        binding.btnCuantas.setOnClickListener{
            Toast.makeText(this,"Hay "+ listaEncuestas.size + " encuestas", Toast.LENGTH_SHORT).show()
        }

        binding.btnResumen.setOnClickListener {
            var resumen : String = ""
            for (i in listaEncuestas){
                resumen += i.nombre +" "+ i.sistema_operativo + " " + i.especialidades + " " + i.horas_estudio + "\n"
            }
            binding.txtResumen.setText(resumen)
        }
    }
}