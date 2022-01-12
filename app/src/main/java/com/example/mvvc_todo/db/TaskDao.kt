package com.example.mvvc_todo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(taskEntry: TaskEntry)

    @Delete
    suspend fun delete(taskEntry: TaskEntry)

    @Update
    suspend fun update(taskEntry: TaskEntry)

    @Query("SELECT * FROM daftar_todo ORDER BY waktu DESC")
    fun getAllTask(): LiveData<List<TaskEntry>>

}