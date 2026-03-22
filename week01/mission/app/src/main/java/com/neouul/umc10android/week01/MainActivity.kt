package com.neouul.umc10android.week01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.neouul.umc10android.week01.databinding.ActivityMainBinding
import com.neouul.umc10android.week01.ui.AppColors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivHappy.setOnClickListener {
            resetColor()
            binding.tvHappy.setTextColor(AppColors.happyText)
        }

        binding.ivExciting.setOnClickListener {
            resetColor()
            binding.tvExciting.setTextColor(AppColors.excitingText)

            binding.ivSoso.setOnClickListener {
                resetColor()
                binding.tvSoso.setTextColor(AppColors.sosoText)
            }

            binding.ivUnrest.setOnClickListener {
                resetColor()
                binding.tvUnrest.setTextColor(AppColors.unrestText)
            }

            binding.ivAngry.setOnClickListener {
                resetColor()
                binding.tvAngry.setTextColor(AppColors.angryText)
            }
        }
    }

    private fun resetColor() {
        binding.tvHappy.setTextColor(AppColors.black)
        binding.tvExciting.setTextColor(AppColors.black)
        binding.tvSoso.setTextColor(AppColors.black)
        binding.tvUnrest.setTextColor(AppColors.black)
        binding.tvAngry.setTextColor(AppColors.black)
    }
}