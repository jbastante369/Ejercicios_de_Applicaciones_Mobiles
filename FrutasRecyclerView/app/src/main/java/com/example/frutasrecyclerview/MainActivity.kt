package com.example.frutasrecyclerview

import Adaptadores.MiAdaptador
import Modelo.AlmacenDeFrutas
import Modelo.FactoriaListaFrutas
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frutasrecyclerview.databinding.ActivityMainBinding

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
        AlmacenDeFrutas.frutas = FactoriaListaFrutas.generaLista(12)
        Log.e("JBS", AlmacenDeFrutas.frutas.toString())

        miRecyclerView = binding.listaFrutas as RecyclerView
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        var miAdaptador = MiAdaptador(AlmacenDeFrutas.frutas, this)
        miRecyclerView.adapter = miAdaptador

        contextoPrincipal = this
    }
}