package com.example.imagenesyaudio

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imagenesyaudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mediaplayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaplayer = MediaPlayer.create(this,R.raw.circo)
        binding.ibtnAzul.setOnClickListener {
            binding.iMando.setImageResource(R.drawable.ic_mandob)
            mediaplayer.start()
        }

        binding.ibtnVerde.setOnClickListener {
            binding.iMando.setImageResource(R.drawable.ic_mando)
            mediaplayer.stop()
            mediaplayer = MediaPlayer.create(this,R.raw.circo)
        }
    }
}