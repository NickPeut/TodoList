package com.example.todolist.tasks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
        val name: String?,
        val description: String?
): Parcelable