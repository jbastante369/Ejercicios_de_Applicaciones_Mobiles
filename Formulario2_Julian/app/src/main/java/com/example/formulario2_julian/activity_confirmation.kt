package com.example.formulario2_julian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formulario2_julian.databinding.ActivityMainBinding

class activity_confirmation : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre = intent.getStringExtra("nombre")
        var apellido = intent.getStringExtra("apellido")
        var dni = intent.getStringExtra("dni")
        var gmail = intent.getStringExtra("gmail")
        var contrasenia = intent.getStringExtra("contrasenia")
        var usuario: Usuario = Usuario(nombre,apellido,dni,gmail,contrasenia)

        binding.txtNombre2.setText("Nombre: "+ nombre)
        binding.txtApellido2.setText("Nombre: "+ nombre)
        binding.txtDNI2.setText("Nombre: "+ nombre)
        binding.txtGmail2.setText("Nombre: "+ nombre)
        binding.txtContrasenia2.setText("Nombre: "+ nombre)

        Almacen_De_Usuarios.aniadirUsuario(usuario)

        var cadena: String = ""
        var i:Int = 1
        for (u in Almacen_De_Usuarios.usuarios){
            cadena+=" "+i+". "+u.nombre+" "+u.apellido+" "+u.dni+" "+u.gmail+" "+u.contrasenia + "\n"
            i++
        }

        binding.boton.setOnClickListener{
            finish()
        }
    }
}