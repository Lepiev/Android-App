package com.example.test14

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test14.data.TaskEntity



@Composable
fun MainScreenUI(
    tasks: List<TaskEntity>,
    onAddTask: () -> Unit,
    onTaskClick: (Int) -> Unit
) {
    Scaffold(
        containerColor = Color.White,

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
                    ) { }
                }
            }
        },
        // Плавающая кнопка добавления задачи
        floatingActionButton = {
            Button(
                onClick = onAddTask,
                modifier = Modifier
                    .size(85.dp)
                    .offset(x = -(10).dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                // Плюсик
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить задачу",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(tasks) { taskEntity ->
                    TaskItemUI(task = taskEntity, onClick = { onTaskClick(taskEntity.id) })
                }
            }
        }
    }
}

@Composable
fun TaskItemUI(task: TaskEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = task.title,
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = task.message,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun MainScreen(navController: NavController, taskViewModel: TaskViewModel) {
    val tasks: List<TaskEntity> = taskViewModel.allTasks.observeAsState(initial = emptyList()).value

    MainScreenUI(
        tasks = tasks,
        onAddTask = { navController.navigate("taskEditor") },
        onTaskClick = { taskId ->
            navController.navigate("task_detail/$taskId")
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun MainScreenUIPreview() {
//    // Примерные данные
//    val sampleTasks = listOf(
//        TaskEntity(id = 1, title = "Первая задача", message = "Описание первой задачи."),
//        TaskEntity(id = 2, title = "Вторая задача", message = "Описание второй задачи."),
//        TaskEntity(id = 3, title = "Третья задача", message = "Описание третьей задачи.")
//    )
//
//    // Обёртываем в тему для правильного отображения
//
//            MainScreenUI(
//                tasks = sampleTasks,
//                onAddTask = { /* Действие при добавлении задачи */ },
//                onTaskClick = { /* Действие при нажатии на задачу */ }
//            )
}
