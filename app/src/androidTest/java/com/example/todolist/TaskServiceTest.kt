package com.example.todolist

import com.example.todolist.database.ITaskService
import com.example.todolist.database.TaskED
import com.example.todolist.database.TaskService
import kotlinx.coroutines.runBlocking


import org.junit.Test

import org.junit.Assert.*

class TaskServiceTest {
    private val taskService: ITaskService = TaskService.getInstance()

    @Test
    fun taskShouldBeAddedSuccessful() {
        runBlocking {
            val task = createTestTask()

            val addedTask = taskService.addTask(task)
            val gotTask = taskService.findById(addedTask.id)

            assertNotNull(gotTask)
            assertEquals(addedTask, gotTask)

            taskService.deleteTaskById(addedTask.id)
        }
    }

    @Test
    fun deletingShouldBeSuccessful() {
        runBlocking {
            val task = createTestTask()

            val addedTask = taskService.addTask(task)
            taskService.deleteTaskById(addedTask.id)

            val gotTask = taskService.findById(addedTask.id)

            assertNull(gotTask)
        }
    }

    @Test
    fun updatingShouldBeSuccessful() {
        runBlocking {
            val task = createTestTask()
            val updatedTask = createUpdatedTask(task)

            val addedTask = taskService.addTask(task)
            taskService.updateTask(updatedTask)

            val gotTask = taskService.findById(task.id)

            assertEquals(gotTask, updatedTask)

            taskService.deleteTaskById(addedTask.id)
        }
    }

    @Test
    fun showAllTasks() {
        runBlocking {
            val listTasks = taskService.findAll()
            println(listTasks)
        }
    }


    private fun createTestTask(): TaskED {
        return TaskED.Builder.createBuilder()
            .id("1")
            .name("cat")
            .description("meow")
            .isDone(true)
            .build()
    }

    private fun createUpdatedTask(oldTask: TaskED): TaskED {
        return TaskED.Builder.createBuilder()
            .id(oldTask.id)
            .name(oldTask.name!!)
            .description("new description")
            .isDone(!oldTask.isDone)
            .build()
    }
}
