package com.example.test14

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test14.data.TaskEntity

@Composable
fun TaskDetailScreenUI(
    title: String,
    onTitleChange: (String) -> Unit,
    message: String,
    onMessageChange: (String) -> Unit,
    onBack: () -> Unit
) {
    Scaffold (
        containerColor = Color.White, // Устанавливаем фон Scaffold
        // Нижняя панель
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
                            .background(Color.White) // Фон каждой секции нижней панели
                    )
                }
            }
        },
        // Плавающая кнопка "Назад"
        floatingActionButton = {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .size(85.dp)
                    .offset(x = (-10).dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                // Контейнер для стрелки
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(40.dp, 9.dp)
                            .background(Color.White)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(17.dp, 32.dp)
                            .rotate(-45f)
                            .size(25.dp, 8.dp)
                            .background(Color.White)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(17.dp, (-32).dp)
                            .rotate(45f)
                            .size(25.dp, 8.dp)
                            .background(Color.White)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Поле ввода заголовка с placeholder
            TextField(
                textStyle = TextStyle(fontSize = 28.sp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                placeholder = { Text("Введите заголовок") },
                value = title,
                onValueChange = onTitleChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Box(modifier = Modifier.padding(horizontal = 16.dp).height(2.dp).fillMaxWidth().background(Color.Black),)

            // Поле ввода описания с placeholder
            TextField(
                textStyle = TextStyle(fontSize = 24.sp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                placeholder = { Text("Введите описание задачи") },
                value = message,
                onValueChange = onMessageChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}


@Composable
fun TaskDetailScreen(taskId: String?, navController: NavController, taskViewModel: TaskViewModel = viewModel()) {
    val id = taskId?.toIntOrNull() ?: return
    val task by taskViewModel.getTaskById(id).observeAsState()

    if(task != null) {
        // Управление состоянием полей ввода
        var title by remember { mutableStateOf(task!!.title) }
        var message by remember { mutableStateOf(task!!.message) }

        // Обработчики изменений полей ввода
        val onTitleChange: (String) -> Unit = { newTitle ->
            title = newTitle
            taskViewModel.updateTask(task!!.copy(title = newTitle))
        }

        val onMessageChange: (String) -> Unit = { newMessage ->
            message = newMessage
            taskViewModel.updateTask(task!!.copy(message = newMessage))
        }

        TaskDetailScreenUI(
            title = title,
            onTitleChange = onTitleChange,
            message = message,
            onMessageChange = onMessageChange,
            onBack = { navController.popBackStack() }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TaskDetailScreenUIPreview() {

        val sampleTask = TaskEntity(
            id = 1,
            title = "Пример задачи",
            message = "Это пример описания задачи. Здесь можно добавить подробности."
        )
        TaskDetailScreenUI(
            title = sampleTask.title,
            onTitleChange = {},
            message = sampleTask.message,
            onMessageChange = {},
            onBack = {}
        )

}