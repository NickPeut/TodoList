package com.example.todolist.database

interface ITaskService {
    suspend fun findById(id: String): TaskED?
    suspend fun findAll(): List<TaskED>
    suspend fun addTask(task: TaskED): TaskED
    suspend fun updateTask(task: TaskED): TaskED
    suspend fun deleteTaskById(id: String)
}