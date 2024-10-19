package com.example.test14

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.test14.ui.theme.Test14Theme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun Hello(content: @Composable () -> Unit) {content()}

fun getTime() : String{
    val calendar = Calendar.getInstance()
    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)
    return "$hours:$minutes"
}

@Preview(showSystemUi = true)
@Composable
fun HelloPreview() {

    val pad = PaddingValues(top = 100.dp, start = 10.dp, end = 13.dp, bottom = 200.dp)
    Text(
        "Hello",
        fontSize=28.sp,
        modifier = Modifier.size(width=300.dp, height=200.dp)
            .background(color= Color.LightGray)
            .offset(x=10.dp, y=50.dp)
    )
}
