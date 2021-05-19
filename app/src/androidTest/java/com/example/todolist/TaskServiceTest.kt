package com.example.todolist

import com.example.todolist.database.ITaskService
import com.example.todolist.database.TaskED
import com.example.todolist.database.TaskService
import kotlinx.coroutines.runBlocking


import org.junit.Test

import org.junit.Assert.*

class ApartmentServiceTest {
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
            val apartment = createTestTask()

            val addedPerson = taskService.addTask(apartment)
            taskService.deleteTaskById(addedPerson.id)

            val gotTask = taskService.findById(addedPerson.id)

            assertNull(gotTask)
        }
    }

    @Test
    fun updatingShouldBeSuccessful() {
        runBlocking {
            val task = createTestTask()
            val updatedTask = createUpdatedApartment(task)

            val addedTask = taskService.addTask(task)
            taskService.updateTask(updatedTask)
            val gotTask = taskService.findById(task.id)

            assertEquals(gotTask, updatedTask)

            taskService.deleteTaskById(addedTask.id)
        }
    }

    private fun createTestTask(): TaskED {
        return TaskED.Builder.createBuilder()
            .id("1")
            .name("cat")
            .description("meow")
            .build()
    }

    private fun createUpdatedApartment(oldTask: TaskED): TaskED {
        return TaskED.Builder.createBuilder()
            .id(oldTask.id)
            .name(oldTask.name!!)
            .description(oldTask.description!!)
            .build()
    }
}
