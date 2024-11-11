package com.example.test14

import android.content.SharedPreferences.Editor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test14.data.TaskEntity

@Composable
fun EditorScreenUI(
    title: String,
    onTitleChange: (String) -> Unit,
    message: String,
    onMessageChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,

        // Нижняя панель (можно оставить пустой или добавить элементы по необходимости)
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(5.dp)
                    ) { }
                }
            }
        },
        floatingActionButton = {
            Button(
                onClick = onSave,

                modifier = Modifier

                    .offset(x = -(10).dp)
                    .size(85.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить задачу",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TextField(
                    textStyle = TextStyle(fontSize =  28.sp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    placeholder = {Text("Введите заголовок")},
                    value = title,
                    onValueChange = onTitleChange,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )

                Box(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color.Black),
                    )

                TextField(
                    textStyle = TextStyle(fontSize =  20.sp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    placeholder = {Text("Введите описание")},
                    value = message,
                    onValueChange = onMessageChange,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                )
            }

        }
    }
}

// экран создания задачи
@Composable
fun EditorScreen(navController: NavController, taskViewModel: TaskViewModel){
    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    EditorScreenUI(
        title = title,
        onTitleChange = { title = it },
        message = message,
        onMessageChange = { message = it },
        onSave = {
            if (title.isNotBlank()) {
                val newTask = TaskEntity(
                    title = title,
                    message = message
                )
                taskViewModel.insert(newTask)
                navController.navigate("main") {
                    popUpTo("main") { inclusive = false }
                }
            }
            else{
                navController.navigate("main") {
                    popUpTo("main") { inclusive = false }
                }
            }
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun EditorScreenPreview() {
    EditorScreenUI(
        title = "Пример заголовка",
        onTitleChange = {},
        message = "Пример описания задачи. Здесь можно добавить подробности.",
        onMessageChange = {},
        onSave = {}
    )
}