package com.example.menu_multijuegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.menu_multijuegos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarPrincipal.title = "    Mi aplicación DAM"
        binding.toolbarPrincipal.subtitle = "     Principal"
        binding.toolbarPrincipal.setLogo(R.drawable.ic_logo)

        //aquí simplemente inflo la toolBaar, pero aún no hay opciones ni botón home.
        setSupportActionBar(binding.toolbarPrincipal)

        //en las siguientes líneas hago que aaprezca el botón de atrás (home) y genero su evento
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarPrincipal.setNavigationOnClickListener {
            Toast.makeText(this,"Pulsado el retroceso", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mnOp1 -> {
                if(!binding.edtUsuario.text.isEmpty()){
                    irATresEnRaya()
                }else{
                    Toast.makeText(this,"Mete un usuario", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.mnOp2 -> {
                if(!binding.edtUsuario.text.isEmpty()){
                    irASimonDice()
                }else{
                    Toast.makeText(this,"Mete un usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private val irATresEnRaya: () -> Unit = {
        val miIntent = Intent(this, TresEnRaya::class.java)
        miIntent.putExtra("usuario",binding.edtUsuario.text.toString())
        startActivity(miIntent)
    }

    private val irASimonDice: () -> Unit = {
        val miIntent = Intent(this, SimonDice::class.java)
        miIntent.putExtra("usuario",binding.edtUsuario.text.toString())
        startActivity(miIntent)
    }
}