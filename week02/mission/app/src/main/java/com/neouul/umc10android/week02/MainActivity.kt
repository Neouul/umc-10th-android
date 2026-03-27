package com.neouul.umc10android.week02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.neouul.umc10android.week02.databinding.ActivityMainBinding
import com.neouul.umc10android.week02.presentation.fragment.CartFragment
import com.neouul.umc10android.week02.presentation.fragment.HomeFragment
import com.neouul.umc10android.week02.presentation.fragment.ProfileFragment
import com.neouul.umc10android.week02.presentation.fragment.ShopFragment
import com.neouul.umc10android.week02.presentation.fragment.WishFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 홈 화면에서 시작
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, HomeFragment())
            .commit()

        // BottomNavigationView를 눌렀을 때 Fragment 변경하기
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId){

                // 홈 화면
                R.id.home_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, HomeFragment())
                        .commit()
                    true
                }

                // 구매하기 화면
                R.id.shop_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, ShopFragment())
                        .commit()
                    true
                }

                // 위시리스트 화면
                R.id.wish_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, WishFragment())
                        .commit()
                    true
                }

                // 장바구니 화면
                R.id.cart_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, CartFragment())
                        .commit()
                    true
                }

                // 마이페이지 화면
                R.id.profile_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}