package com.example.mvvc_todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvc_todo.db.TaskDatabase
import com.example.mvvc_todo.db.TaskEntry
import com.example.mvvc_todo.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository : TaskRepository

    val getAllTask : LiveData<List<TaskEntry>>

    init {
        repository = TaskRepository(taskDao)
        getAllTask= repository.getAllTask()

    }

    fun insertData(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(taskEntry)
        }
    }

    fun onTaskSelected(taskEntry: TaskEntry){

    }

    fun onTaskCheckedChanged(taskEntry: TaskEntry, isChecked:Boolean)=viewModelScope.launch {
        taskDao.update(taskEntry.copy(complete = isChecked))
    }
}