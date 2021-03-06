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
import com.example.todolist.helpers.TaskHelper
import com.example.todolist.helpers.showCheck

class TaskFragment : Fragment() {
    private lateinit var btnUpdateTask: ImageButton
    private lateinit var btnDeleteTask: ImageButton
    private lateinit var btnCheck: ImageButton
    private lateinit var taskHelper: TaskHelper
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var task: Task
    private lateinit var btnBack: ImageButton

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
        btnUpdateTask = view.findViewById(R.id.fragment_task__btn_update)
        btnDeleteTask = view.findViewById(R.id.fragment_task__btn_delete)
        btnCheck = view.findViewById(R.id.fragment_task__ib_check)
        btnBack = view.findViewById(R.id.fragment_task__btn_back)

        task = args.Task
        tvName.text = task.name
        tvDescription.text = task.description
        btnCheck.setImageResource(showCheck(task.isDone))

        taskHelper = ViewModelProvider(this).get(TaskHelper::class.java)
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.nav_graph__list_of_tasks_fragment)
        }
        //btnCheck.setOnClickListener(this::onClickCheck)
        btnUpdateTask.setOnClickListener(this::onClickUpdateTask)
        btnDeleteTask.setOnClickListener(this::onClickDeleteTask)
    }

    /*private fun onClickCheck(view: View) {
        task.isDone = !task.isDone
        btnCheck.setImageResource(showCheck(task.isDone))
    }
    */

    private fun onClickUpdateTask(view: View) {
        val action = TaskFragmentDirections.actionNavGrafTaskFragmentToNavGrafAddTaskFragment(task, getString(R.string.update))
        findNavController().navigate(action)
    }

    private fun onClickDeleteTask(view: View) {
        taskHelper.deleteTask(task.id)
        findNavController().navigate(R.id.nav_graph__list_of_tasks_fragment)
    }
}
