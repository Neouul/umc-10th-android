package com.neouul.umc10android.example.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.neouul.umc10android.example.component.ui.theme.UMC10thExampeleComponentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UMC10thExampeleComponentTheme {
                MainScreen()
            }
        }
    }
}
