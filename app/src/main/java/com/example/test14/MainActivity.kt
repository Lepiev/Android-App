package com.example.test14


import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import android.os.Bundle
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.filled.ArrowBack
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme


import androidx.compose.foundation.clickable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.test14.ui.theme.Test14Theme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.test14.data.AppDatabase
import com.example.test14.data.TaskEntity
import com.example.test14.data.TaskRepository


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test14Theme(darkTheme = false) {
                val navController = rememberNavController()
                val taskViewModel: TaskViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "main",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    composable("main") {
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

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    val allTasks: LiveData<List<TaskEntity>>

    init {
        val taskDao = AppDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks.asLiveData()
    }

    fun insert(task: TaskEntity) = viewModelScope.launch {
        repository.insert(task)
    }

    fun getTaskById(taskId: Int): LiveData<TaskEntity?> {
        return repository.getTaskById(taskId).asLiveData()
    }

    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: TaskEntity) = viewModelScope.launch {
        repository.delete(task)
    }
}


@Preview(showSystemUi = true)
@Composable
fun HelloPreview() {


}