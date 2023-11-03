package com.example.frutasrecycleview_database

import Modelo.Fruta
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.frutasrecycleview_database.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var f = intent.getSerializableExtra("obj") as Fruta

        binding.imgFruta2.setImageResource(R.drawable.coco)

        binding.txtFruta.text = f.nombre.toString()

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }
}