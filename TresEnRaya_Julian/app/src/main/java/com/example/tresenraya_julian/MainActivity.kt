package com.example.tresenraya_julian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.tresenraya_julian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var jugador: Int = 0
        var turno: Int = 0
        var terminado: Boolean = false
        var tablero: Array<Array<Int>> = Array(3) { Array(3) { 0 } }
        val botones = arrayOf(
            arrayOf(binding.img00,binding.img01,binding.img02),
            arrayOf(binding.img10,binding.img11,binding.img12),
            arrayOf(binding.img20,binding.img21,binding.img22),
        )


        for (y in 0 until botones.size){
            for (x in 0 until botones[0].size){
                botones[y][x].setOnClickListener {
                    if (turno % 2 == 0) {
                        botones[y][x].setImageResource(R.drawable.ic_green_circle)
                        tablero[y][x] = 1
                        jugador = 1
                    } else {
                        botones[y][x].setImageResource(R.drawable.ic_orange_circle)
                        tablero[y][x] = 2
                        jugador = 2
                    }
                    botones[y][x].isClickable = false
                    turno++
                    if (esGanador(tablero,jugador) != 0) {
                        if (jugador == 1) {
                            Toast.makeText(this, "¡¡Verde gana!!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "¡¡Naranja gana!!", Toast.LENGTH_SHORT).show()
                        }
                        for (y in 0 until botones.size) {
                            for (x in 0 until botones[0].size) {
                                botones[y][x].isClickable = false
                            }
                        }
                        binding.btnReseteo.isVisible = true
                    }
                    if(turno > 8){
                        Toast.makeText(this, "¡¡Empate!!", Toast.LENGTH_SHORT).show()
                        binding.btnReseteo.isVisible = true
                    }
                }
            }
        }

        binding.btnReseteo.setOnClickListener{
            for (y in 0 until botones.size) {
                for (x in 0 until botones[0].size) {
                    botones[y][x].isClickable = true
                    botones[y][x].setImageResource(R.drawable.ic_circle_default)
                }
            }
            binding.btnReseteo.isVisible = false
            tablero = Array(3) { Array(3) { 0 } }
            turno = 0
        }
    }

    fun esGanador(tablero:Array<Array<Int>>,jugador:Int): Int{
        var ganador: Int = 0
        if (ganador == 0){
            ganador = filas(tablero,jugador)
        }
        if (ganador == 0){
            ganador = columnas(tablero,jugador)
        }
        if (ganador == 0){
            ganador = diagonales(tablero,jugador)
        }
        return ganador
    }

    fun diagonales(tablero:Array<Array<Int>>,jugador:Int): Int{
        var k: Int = 0
        var x: Int = 0
        for(y in tablero.size - 1 .. 0){
            if(tablero[y][x]==jugador){
                k++;
            }
            if(k==3){
                return jugador
            }
            x++
        }
        x = 0
        for(y in 0 until tablero.size){
            if(tablero[y][x]==jugador){
                k++;
            }
            if(k==3){
                return jugador
            }
            x++
        }
        return 0
    }

    fun filas(tablero:Array<Array<Int>>,jugador:Int): Int{
        var k: Int = 0
        for (y in 0 until tablero.size){
            for (x in 0 until tablero[0].size){
                if(tablero[y][x]==jugador){
                    k++
                }
                if(k==3){
                   return jugador
                }
            }
            k = 0
        }

        return 0
    }

    fun columnas(tablero:Array<Array<Int>>,jugador:Int): Int{
        var k: Int = 0
        for (x in 0 until tablero.size){
            for (y in 0 until tablero[0].size){
                if(tablero[y][x]==jugador){
                    k++
                }
                if(k==3){
                    return jugador
                }
            }
            k = 0
        }

        return 0
    }

    /*fun partidaAcabada(){
        for (y in 0 until botones.size){
            for (x in 0 until botones[0].size){
                botones[y][x].isClickable = false
            }
        }
    }*/
}