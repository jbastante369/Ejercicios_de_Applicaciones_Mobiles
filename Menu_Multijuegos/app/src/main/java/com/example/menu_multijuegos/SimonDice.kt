package com.example.menu_multijuegos

import Auxiliar.Conexion
import Modelo.Usuario
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.menu_multijuegos.databinding.ActivitySimonDiceBinding
import kotlin.random.Random

class SimonDice : AppCompatActivity() {
    lateinit var binding: ActivitySimonDiceBinding

    private var nivel = 1
    private var nivelusuario = 1
    private var secuencia = mutableListOf<Int>()
    private var indiceUsuario = 0
    private var completado = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimonDiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var textViews = arrayOf(binding.btnRojo,
            binding.btnAmarillo,
            binding.btnAzul,
            binding.btnVerde)

        var colorEncendido = arrayOf("#FF0000",
            "#FFE600",
            "#0022FF",
            "#11FF00")

        var colorApagado = arrayOf("#A50000",
            "#887B00",
            "#000E6A",
            "#087800")
        var usuario = intent.getStringExtra("usuario")
        var u = Conexion.buscarUsuario(this, usuario.toString().trim())


        if (u == null){
            var u:Usuario = Usuario(usuario.toString(),1)
            Conexion.addUsuario(this,u)
            Toast.makeText(this,"Nuevo Usuario: "+ u.nombre, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Bienvenido de vuelta "+ u.nombre, Toast.LENGTH_SHORT).show()
            nivelusuario = u.nivel
        }
        binding.txtRecord.setText("Record: $nivelusuario")

        binding.tbSimonDice.title = "    Simon dice"
        binding.tbSimonDice.subtitle = "     "+ usuario.toString()
        binding.tbSimonDice.setLogo(R.drawable.ic_logo)

        setSupportActionBar(binding.tbSimonDice)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tbSimonDice.setNavigationOnClickListener {
            Toast.makeText(this,"Pulsado el retroceso", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnIniciarSecuencia.setOnClickListener {
            desactivarClicks()
            indiceUsuario = 0
            if (completado){
                secuencia.add(Random.nextInt(0, textViews.size))
                completado = false
            }

            val temporizador = object : CountDownTimer((nivel*2 *800).toLong(),800) {
                var currentIndex = 0
                var cont = 0
                override fun onTick(millisUntilFinished: Long) {
                    if (currentIndex < secuencia.size) {
                        if (cont == 0){
                            textViews[secuencia[currentIndex]].setBackgroundColor(Color.parseColor(colorEncendido[secuencia[currentIndex]]))
                            cont = 1
                        }else{
                            textViews[secuencia[currentIndex]].setBackgroundColor(Color.parseColor(colorApagado[secuencia[currentIndex]]))
                            currentIndex++
                            cont = 0
                        }

                    }
                }

                override fun onFinish() {
                    habilitarClicsDelUsuario(u)
                }
            }
            temporizador.start()
        }

        binding.btnResetear.setOnClickListener {
            secuencia = mutableListOf<Int>()
            completado = true
            nivel = 1
            binding.txtNivel.setText("Nivel $nivel")
        }

    }

    private fun habilitarClicsDelUsuario(u:Usuario?) {
        var textViews = arrayOf(binding.btnRojo,
            binding.btnAmarillo,
            binding.btnAzul,
            binding.btnVerde)

        activarClicks()

        for (textView in textViews) {
            textView.setOnClickListener {
                var index = textViews.indexOf(textView)
                if (index == secuencia[indiceUsuario]) {

                    indiceUsuario++
                    if (indiceUsuario == secuencia.size) {

                        Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show()
                        nivel++
                        binding.txtNivel.setText("Nivel $nivel")
                        desactivarClicks()
                        completado = true
                        if (nivel > nivelusuario){
                            if (u != null) {
                                u.nivel = nivel
                                Conexion.modUsuario(this,u.nombre,u)
                                nivelusuario = nivel
                                binding.txtRecord.setText("Record: $nivelusuario")
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "¡Incorrecto! Inténtalo de nuevo.", Toast.LENGTH_SHORT).show()
                    indiceUsuario = 0
                }
            }
        }
    }

    private fun desactivarClicks() {
        var textViews = arrayOf(binding.btnRojo,
            binding.btnAmarillo,
            binding.btnAzul,
            binding.btnVerde)

        for (textView in textViews) {
            textView.isClickable = false
        }
    }

    private fun activarClicks() {
        var textViews = arrayOf(binding.btnRojo,
            binding.btnAmarillo,
            binding.btnAzul,
            binding.btnVerde)

        for (textView in textViews) {
            textView.isClickable = true
        }
    }
}