package com.example.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ITaskService
import com.example.todolist.database.TaskED
import com.example.todolist.database.TaskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskHelper: ViewModel() {
    private val taskService: ITaskService = TaskService.getInstance()

    fun addTask(taskED: TaskED) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.addTask(taskED)
        }
    }

    fun deleteTask(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.deleteTaskById(id)
        }
    }

    fun updateTask(task: TaskED) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.updateTask(task)
        }
    }


    @Volatile var task: TaskED? = null

    fun getTaskById(id: String): TaskED? {
        viewModelScope.launch(Dispatchers.IO) {
            task = taskService.findById(id)
        }
        return task
    }
}