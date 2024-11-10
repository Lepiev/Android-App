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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.mutableIntSetOf
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test14Theme (darkTheme = false){

                val navController = rememberNavController()
                val taskViewModel: TaskViewModel = viewModel()

                NavHost(navController = navController, startDestination = "main",
                    enterTransition = { EnterTransition.None},
                    exitTransition = { ExitTransition.None}

                ) {

                    composable("main"){
                        MainScreen(navController, taskViewModel)
                    }
                    composable("taskEditor") {
                        EditorScreen(navController, taskViewModel)
                    }
                    composable("task_detail/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")
                        TaskDetailScreen(taskId, navController, taskViewModel)
                    }

                }
            }

        }
    }
}

data class Task(val title: String, val message:String, val id: String)

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun getTaskById(id: String): Task? {
        return _tasks.find { it.id == id }
    }
}


@Composable
fun MainScreen(navController: NavController, taskViewModel: TaskViewModel){


    Scaffold (
        //нижняя панель
        bottomBar = {
            Row (modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)){
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(5.dp)) {  }
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(5.dp)) {  }
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(5.dp)) {  }
            }
        },

        //кнопка
        floatingActionButton = {
            Button(onClick = {
                navController.navigate("taskEditor")
            }, modifier = Modifier.size(80.dp),
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
            Column {
                for (tmpTask in taskViewModel.tasks) {
                    Text (text = tmpTask.title, fontSize = 28.sp,
                        modifier = Modifier
                            .clickable (onClick = {
                                navController.navigate("task_detail/${tmpTask.id}") {
                                    popUpTo("main") { inclusive = false }
                                }
                            }))
                }
            }
        }
    }

}


// экран создания задачи
@Composable
fun EditorScreen(navController: NavController, taskViewModel: TaskViewModel){
    Scaffold (
        //нижняя панель
        bottomBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)) {
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(5.dp)) { }
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(5.dp)) { }
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(5.dp)) { }
            }
        },

    ){
        Box(modifier = Modifier.fillMaxSize()) {
            var title by remember { mutableStateOf("") }
            var savetext by remember { mutableStateOf("") }
            Column(modifier = Modifier.padding(it)) {

                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Заголовок") }
                )
                TextField(
                    value = savetext,
                    onValueChange = { savetext = it }
                )

            }

            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        val newTask = Task(
                            title = title,
                            message = savetext,
                            id = System.currentTimeMillis().toString()
                        )
                        taskViewModel.addTask(newTask)
                        navController.navigate("main")
                    }

                },
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp), //убирает оганичение области
                modifier = Modifier.align(Alignment.BottomEnd).padding(vertical = 70.dp).padding(horizontal = 20.dp).size(80.dp)

            ) {
                // Контейнер для стрелки
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(modifier = Modifier
                        .align(Alignment.Center)

                        .size(50.dp, 10.dp)
                        .background(Color.White)

                    )
                    Box(modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(10.dp, 28.dp)
                        .rotate(-45f)
                        .size(30.dp, 10.dp)
                        .background(Color.White)

                    )
                    Box(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset((10.dp), -(28).dp)
                        .rotate(45f)
                        .size(30.dp, 10.dp)
                        .background(Color.White)

                    )
                }
            }
        }
    }
}

// экран при открытии инфы о задаче
@Composable
fun TaskDetailScreen(taskId: String?, navController: NavController, taskViewModel: TaskViewModel) {
    val task = taskViewModel.getTaskById(taskId ?: "")
    if (task != null) {

        Scaffold (
            //нижняя панель
            bottomBar = {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)){
                    Box(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(5.dp)) {  }
                    Box(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(5.dp)) {  }
                    Box(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(5.dp)) {  }
                }
            },

            floatingActionButton = {
                Button(onClick = {
                    navController.popBackStack()
                }, modifier = Modifier.size(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    //что-то нарисовать
                    Box(contentAlignment = Alignment.Center){

                    }

                }
            }
        ){
            Column (modifier = Modifier.padding(it)){
                    Text(text = task.title, fontSize = 30.sp, modifier = Modifier
                        .align(Alignment.CenterHorizontally), textAlign = TextAlign.Center)

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = task.message, fontSize = 24.sp)
            }
        }
    }


    else {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = "Задача не найдена", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Назад")
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HelloPreview() {
        // Создаём NavController для превью
        val navController = rememberNavController()

        // Создаём экземпляр TaskViewModel и добавляем пример задачи
        val taskViewModel = TaskViewModel().apply {
            addTask(Task(title = "Пример задачи очеь большой задачи", message = "Это пример описания задачи.", id = "1"))
        }

        // Отображаем TaskDetailScreen с примерными данными
        TaskDetailScreen(
            taskId = "1", // Идентификатор существующей задачи
            navController = navController,
            taskViewModel = taskViewModel
        )

}
