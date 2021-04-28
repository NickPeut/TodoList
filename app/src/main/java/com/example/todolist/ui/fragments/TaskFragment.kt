package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.todolist.R

class TaskFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tvName: TextView = view.findViewById(R.id.fragment_task__tv_name)
        val tvDescription: TextView = view.findViewById(R.id.fragment_task__tv_description)
        super.onViewCreated(view, savedInstanceState)
        val args: TaskFragmentArgs by navArgs()
        val task = args.Task
        tvName.text = task.name
        tvDescription.text = task.description
    }
}


