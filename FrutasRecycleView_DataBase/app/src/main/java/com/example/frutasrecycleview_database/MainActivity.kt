package com.example.frutasrecycleview_database

import Adaptadores.MiAdaptador
import Auxiliar.Conexion
import Modelo.AlmacenDeFrutas
import Modelo.FactoriaListaFrutas
import Modelo.Fruta
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frutasrecycleview_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var miRecyclerView : RecyclerView

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var contextoPrincipal: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var listado:ArrayList<Fruta> = Conexion.obtenerFrutas(this)

        miRecyclerView = binding.listaFrutas as RecyclerView
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        var miAdaptador = MiAdaptador(listado, this)
        miRecyclerView.adapter = miAdaptador

        contextoPrincipal = this
    }

    fun addFruta(view: View) {
        if (binding.edID.text.toString().trim().isEmpty() || binding.edNombre.text.toString().trim().isEmpty()
            || binding.edImgen.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Campos en blanco", Toast.LENGTH_SHORT).show()
        }
        else {
            var frut: Fruta = Fruta(
                binding.edID.getText().toString(),
                binding.edNombre.getText().toString(),
                binding.edImgen.getText().toString()
            )
            var codigo= Conexion.addFruta(this, frut)
            binding.edID.setText("")
            binding.edNombre.setText("")
            binding.edImgen.setText("")
            binding.edID.requestFocus()
            //la L es por ser un Long lo que trae codigo.
            if(codigo!=-1L) {
                Toast.makeText(this, "Fruta insertada", Toast.LENGTH_SHORT).show()
                listarFrutas(view)
            }
            else
                Toast.makeText(this, "Ya existe ese id. Fruta no insertada", Toast.LENGTH_SHORT).show()
        }
    }

    fun delFruta(view: View) {
        var cant = Conexion.delFruta(this, binding.edID.text.toString())
        binding.edID.setText("")
        binding.edNombre.setText("")
        binding.edImgen.setText("")
        if (cant == 1) {
            Toast.makeText(this, "Se borró la fruta con ese ID", Toast.LENGTH_SHORT).show()
            listarFrutas(view)
        }
        else
            Toast.makeText(this, "No existe una fruta con ese ID", Toast.LENGTH_SHORT).show()

    }

    fun modFruta(view: View) {
        if (binding.edID.text.toString().trim().isEmpty() || binding.edNombre.text.toString().trim().isEmpty()
            || binding.edImgen.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Campos en blanco", Toast.LENGTH_SHORT).show()
        }
        else {
            var frut: Fruta = Fruta(
                binding.edID.getText().toString(),
                binding.edNombre.getText().toString(),
                binding.edImgen.getText().toString()
            )
            var cant = Conexion.modFruta(this, binding.edID.text.toString(), frut)
            if (cant == 1)
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe una persona con ese DNI", Toast.LENGTH_SHORT).show()
        }
        listarFrutas(view)
    }

    fun buscarFruta(view: View) {
        var f:Fruta? = null
        f = Conexion.buscarFruta(this, binding.edID.text.toString())
        if (f!=null) {
            binding.edNombre.setText(f.nombre)
            binding.edImgen.setText(f.imagen)
        } else {
            Toast.makeText(this, "No existe una fruta con ese ID", Toast.LENGTH_SHORT).show()
        }

    }

    fun listarFrutas(view: View) {
        var listado:ArrayList<Fruta> = Conexion.obtenerFrutas(this)

        if (listado.size == 0){
            Toast.makeText(this, "No existen más datos", Toast.LENGTH_SHORT).show()
        }

        miRecyclerView = binding.listaFrutas as RecyclerView
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        var miAdaptador = MiAdaptador(listado, this)
        miRecyclerView.adapter = miAdaptador

        contextoPrincipal = this
    }
}