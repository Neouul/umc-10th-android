package com.neouul.umc10android.week01

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.neouul.umc10android.week01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivHappy.setOnClickListener {
            resetColor()
            binding.tvHappy.setTextColor("#FFEFB6".toColorInt())
        }

        binding.ivExciting.setOnClickListener {
            resetColor()
            binding.tvExciting.setTextColor("#CEE7F5".toColorInt())
        }

        binding.ivSoso.setOnClickListener {
            resetColor()
            binding.tvSoso.setTextColor("#BEC3ED".toColorInt())
        }

        binding.ivUnrest.setOnClickListener {
            resetColor()
            binding.tvUnrest.setTextColor("#B1D3B9".toColorInt())
        }

        binding.ivAngry.setOnClickListener {
            resetColor()
            binding.tvAngry.setTextColor("#EB8B8B".toColorInt())
        }
    }

    private fun resetColor() {
        binding.tvHappy.setTextColor(Color.BLACK)
        binding.tvExciting.setTextColor(Color.BLACK)
        binding.tvSoso.setTextColor(Color.BLACK)
        binding.tvUnrest.setTextColor(Color.BLACK)
        binding.tvAngry.setTextColor(Color.BLACK)
    }
}