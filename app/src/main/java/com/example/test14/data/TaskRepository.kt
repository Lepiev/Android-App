package com.example.test14.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<TaskEntity>> = taskDao.getAllTasks()

    fun getTaskById(taskId: Int): Flow<TaskEntity?> {
        return taskDao.getTaskById(taskId)
    }

    suspend fun insert(task: TaskEntity) {
        taskDao.insert(task)
    }

    suspend fun delete(task: TaskEntity) {
        taskDao.delete(task)
    }

    suspend fun update(task: TaskEntity) {
        taskDao.update(task)
    }
}