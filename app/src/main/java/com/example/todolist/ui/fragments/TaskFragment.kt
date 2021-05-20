package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.tasks.Task
import com.example.todolist.viewModel.TaskHelper

class TaskFragment : Fragment() {
    private lateinit var btnUpdateTask: ImageButton
    private lateinit var btnDeleteTask: ImageButton
    private lateinit var taskHelper: TaskHelper
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var task: Task
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: TaskFragmentArgs by navArgs()
        tvName = view.findViewById(R.id.fragment_task__tv_task_name)
        tvDescription = view.findViewById(R.id.fragment_task__tv_task_description)
        task = args.Task
        tvName.text = task.name
        tvDescription.text = task.description
        btnUpdateTask = view.findViewById(R.id.fragment_task__btn_update)
        btnDeleteTask = view.findViewById(R.id.fragment_task__btn_delete)
        taskHelper = ViewModelProvider(this).get(TaskHelper::class.java)
        btnUpdateTask.setOnClickListener(this::onClickUpdateTask)
        btnDeleteTask.setOnClickListener(this::onClickDeleteTask)
    }

    private fun onClickUpdateTask(view: View) {
        val action = TaskFragmentDirections.actionNavGrafTaskFragmentToNavGrafAddTaskFragment(task, getString(R.string.update))
        findNavController().navigate(action)
    }

    private fun onClickDeleteTask(view: View) {
        taskHelper.deleteTask(task.id)
        findNavController().navigate(R.id.nav_graph__list_of_tasks_fragment)
    }
}
