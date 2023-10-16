package com.example.formulario2_julian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.formulario2_julian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegistrar.setOnClickListener {
            if (binding.edtNombre.text.isEmpty()||binding.edtApellido.text.isEmpty()||
                binding.edtDNI.text.isEmpty()||binding.edtGmail.text.isEmpty()||
                binding.edtContrasenia.text.isEmpty()||binding.edtComnfirmarContrasenia.text.isEmpty()){

                Toast.makeText(this,"Te falta rellenar algun campo", Toast.LENGTH_SHORT).show()
            }else{
                if(!(binding.edtContrasenia.text.toString().length >= 6) || !binding.edtComnfirmarContrasenia.text.equals(binding.edtComnfirmarContrasenia.text)){
                    Toast.makeText(this,"Es muy corta o no coincide la contraseña con la confirmación", Toast.LENGTH_SHORT).show()
                }else{
                    IrAConfirmation()
                }
            }
        }
    }

    private fun IrAConfirmation() {
        var miIntent: Intent = Intent(this,activity_confirmation::class.java)
        miIntent.putExtra("nombre",binding.edtNombre.text.toString())
        miIntent.putExtra("apellido",binding.edtApellido.text.toString())
        miIntent.putExtra("dni",binding.edtDNI.text.toString())
        miIntent.putExtra("gmail",binding.edtGmail.text.toString())
        miIntent.putExtra("contrasenia",binding.edtContrasenia.text.toString())
        startActivity(miIntent)
    }
}