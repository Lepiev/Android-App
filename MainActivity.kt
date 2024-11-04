package com.example.test14

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import android.icu.util.Calendar
import android.os.Bundle
import androidx.compose.material3.Scaffold
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.mutableIntSetOf
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.example.test14.ui.theme.Test14Theme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import org.w3c.dom.Text
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test14Theme (darkTheme = false){
                MainScreen()
            }

        }
    }
}

data class Tasks(var message:String, var YorN:Boolean = false)


@Composable
fun MainScreen(){

    var listOftasks = remember { mutableListOf<String>() }

    Scaffold (
        //нижняя панель
        bottomBar = {
            Row (modifier = Modifier.fillMaxWidth().height(60.dp)){
                Box(modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp)) {  }
                Box(modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp)) {  }
                Box(modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp)) {  }
            }
        },

        //кнопка
        floatingActionButton = {
            Button(onClick = {}, modifier = Modifier.size(80.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                //плюсик
                Box(contentAlignment = Alignment.Center){
                    //горизонтальная линия
                    Box(modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.White)
                    )
                    //вертикальная линия
                    Box(modifier = Modifier
                        .padding(20.dp)
                        .fillMaxHeight()
                        .width(12.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.White)
                    )

                }

            }
        }


    ){
        Box(modifier = Modifier.padding(it)){



        }
    }

}


@Composable
fun AddNewTasks(takenText:String = "Без ничего"){
    Box(
        modifier = Modifier.padding(22.dp)
    ) {
        Text(
            text = takenText,
            fontSize = 28.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HelloPreview() {
    MainScreen()
}

