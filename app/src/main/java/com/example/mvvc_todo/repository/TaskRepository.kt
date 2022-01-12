package com.example.mvvc_todo.repository

import androidx.lifecycle.LiveData
import com.example.mvvc_todo.db.TaskDao
import com.example.mvvc_todo.db.TaskEntry

class TaskRepository (val taskDao: TaskDao) {
    suspend fun insertData(taskEntry: TaskEntry) = taskDao.insert(taskEntry)
    suspend fun updateData(taskEntry: TaskEntry) = taskDao.update(taskEntry)
    suspend fun deleteData(taskEntry: TaskEntry) = taskDao.delete(taskEntry)

    fun getAllTask():LiveData<List<TaskEntry>> = taskDao.getAllTask()

}