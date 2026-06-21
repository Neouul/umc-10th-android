package com.neouul.umc10android.week10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.neouul.umc10android.week10.core.routing.NavigationRoot
import com.neouul.umc10android.week10.ui.theme.UMC10thweek10ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UMC10thweek10ComposeTheme {
                NavigationRoot()
            }
        }
    }
}