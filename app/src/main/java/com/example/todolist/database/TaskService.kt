package com.example.todolist.database

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class TaskService private constructor() : ITaskService {
    private val db: FirebaseFirestore = Firebase.firestore
    private val tasksCollection: CollectionReference =
        db.collection(Collections.TASKS.collectionName)

    companion object {
        private var INSTANCE: ITaskService? = null

        fun getInstance(): ITaskService {
            if (INSTANCE == null) {
                INSTANCE = TaskService()
            }
            return INSTANCE!!
        }
    }

    override suspend fun findById(id: String): TaskED? {
        val personRef = tasksCollection.document(id)

        return suspendCancellableCoroutine { coroutine ->
            personRef.get()
                .addOnSuccessListener {
                    coroutine.resume(it.toObject(TaskED::class.java))
                }
                .addOnFailureListener {
                    coroutine.resumeWithException(
                        RuntimeException("Filed fetching task with id: $id", it)
                    )
                }
        }
    }

    override suspend fun findAll(): List<TaskED> {
        return suspendCancellableCoroutine { coroutine ->
            tasksCollection.get()
                .addOnSuccessListener {
                    coroutine.resume(it.toObjects(TaskED::class.java))
                }.addOnFailureListener {
                    coroutine.resumeWithException(
                        RuntimeException("Filed fetching tasks list", it)
                    )
                }
        }
    }

    override suspend fun addTask(task: TaskED): TaskED {
        return suspendCancellableCoroutine { coroutine ->
            tasksCollection.document(task.id)
                .set(tasksToMap(task))
                .addOnFailureListener {
                    coroutine.resumeWithException(
                        RuntimeException(
                            "Failure adding person with id: ${task.id}",
                            it
                        )
                    )
                }
                .addOnSuccessListener {
                    Log.i(
                        this::class.java.name,
                        "Successful adding person with email: ${task.id}"
                    )

                    coroutine.resume(task)
                }
        }
    }

    override suspend fun updateTask(task: TaskED): TaskED {
        val personRef = tasksCollection.document(task.id)

        return suspendCancellableCoroutine { coroutine ->
            personRef.update(tasksToMap(task))
                .addOnFailureListener {
                    coroutine.resumeWithException(
                        RuntimeException(
                            "Failure updating person with id: ${task.id}",
                            it
                        )
                    )
                }
                .addOnSuccessListener {
                    coroutine.resume(task)
                }
        }
    }

    override suspend fun deleteTaskById(id: String) {
        val personRef = tasksCollection.document(id)

        return suspendCancellableCoroutine { coroutine ->
            personRef.delete()
                .addOnSuccessListener {
                    coroutine.resume(Unit)
                }
                .addOnFailureListener {
                    coroutine.resumeWithException(
                        RuntimeException(
                            "Failure deleting person with email: $id",
                            it
                        )
                    )
                }
        }
    }

    private fun tasksToMap(task: TaskED): Map<String, Any?> {
        return mapOf(
            "id" to task.id,
            "name" to task.name,
            "description" to task.description
        )
    }
}