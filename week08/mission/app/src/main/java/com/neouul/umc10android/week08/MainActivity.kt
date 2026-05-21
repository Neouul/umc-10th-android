package com.neouul.umc10android.week08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.neouul.umc10android.week08.core.routing.NavigationRoot
import com.neouul.umc10android.week08.ui.theme.UMC10thweek08ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UMC10thweek08ComposeTheme {
                NavigationRoot()
            }
        }
    }
}