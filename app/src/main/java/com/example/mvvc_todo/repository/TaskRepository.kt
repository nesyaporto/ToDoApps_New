package com.example.mvvc_todo.repository

import androidx.lifecycle.LiveData
import com.example.mvvc_todo.db.TaskDao
import com.example.mvvc_todo.db.TaskEntry

class TaskRepository (val taskDao: TaskDao) {
    suspend fun insertData(taskEntry: TaskEntry) = taskDao.insert(taskEntry)

    fun getAllTask():LiveData<List<TaskEntry>> = taskDao.getAllTask()

}