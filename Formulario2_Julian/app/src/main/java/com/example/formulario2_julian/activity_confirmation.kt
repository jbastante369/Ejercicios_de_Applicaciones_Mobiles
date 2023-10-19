package com.example.formulario2_julian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formulario2_julian.databinding.ActivityConfirmationBinding
import com.example.formulario2_julian.databinding.ActivityMainBinding

class activity_confirmation : AppCompatActivity() {
    lateinit var binding: ActivityConfirmationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre = intent.getStringExtra("nombre")
        var apellido = intent.getStringExtra("apellido")
        var dni = intent.getStringExtra("dni")
        var gmail = intent.getStringExtra("gmail")
        var contrasenia = intent.getStringExtra("contrasenia")
        var usuario: Usuario = Usuario(nombre,apellido,dni,gmail,contrasenia)

        binding.txtNombre2.setText("Nombre: "+ nombre)
        binding.txtApellido2.setText("Apellido: "+ apellido)
        binding.txtDNI2.setText("DNI: "+ dni)
        binding.txtGmail2.setText("Gmail: "+ gmail)
        binding.txtContrasenia2.setText("Contraseña: "+ contrasenia)

        Almacen_De_Usuarios.aniadirUsuario(usuario)

        var cadena: String = ""
        var i:Int = 1
        for (u in Almacen_De_Usuarios.usuarios){
            cadena+=" "+i+". "+u.nombre+" "+u.apellido+" "+u.dni+" "+u.gmail+" "+u.contrasenia + "\n"
            i++
        }

        binding.multiUsuarios.setText(cadena)

        binding.boton.setOnClickListener{
            finish()
        }
    }
}