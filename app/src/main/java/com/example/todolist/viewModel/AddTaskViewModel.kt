package com.example.todolist.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ITaskService
import com.example.todolist.database.TaskED
import com.example.todolist.database.TaskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTaskViewModel: ViewModel() {
    private val taskService: ITaskService = TaskService.getInstance()

    fun addTask(taskED: TaskED) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.addTask(taskED)
        }
    }

}
