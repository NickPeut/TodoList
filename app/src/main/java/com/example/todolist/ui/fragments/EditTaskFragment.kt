package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.database.TaskED
import com.example.todolist.tasks.Task
import com.example.todolist.viewModel.TaskHelper

class EditTaskFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }


    private lateinit var taskHelper: TaskHelper
    private lateinit var etName: EditText
    private lateinit var tvNameMove: TextView
    private lateinit var etDescription: EditText
    private var task: Task? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: EditTaskFragmentArgs by navArgs()
        super.onViewCreated(view, savedInstanceState)
        tvNameMove = view.findViewById(R.id.fragment_add_task__tv_name_move)
        tvNameMove.text = args.nameMove
        etName = view.findViewById(R.id.fragment_add_task__et_name)
        etDescription = view.findViewById(R.id.fragment_add_task__et_description)
        task = args.task
        if (args.nameMove == getString(R.string.update)) {
            etName.setText(task?.name)
            etDescription.setText(task?.description)
        }
        taskHelper = ViewModelProvider(this).get(TaskHelper::class.java)
        val btnSaveTask: ImageButton = view.findViewById(R.id.fragment_add_task__btn_save)

        btnSaveTask.setOnClickListener(this::onClickAddTask)
    }


    private fun onClickAddTask(view: View) {
        val name = etName.text.toString()
        val description = etDescription.text.toString()
        if (name == "") {
            getToastAboutFillAllFields().show()
            return
        }
        val taskED: TaskED
        if (task == null) {
            val id = name.hashCode().toString()
            if (taskHelper.getTaskById(id) == null) {
                taskED = TaskED(
                    id = id,
                    name = name,
                    description = description
                )
            } else {
                getToastAboutFillAllFields()
                return
            }
        } else {
            taskED = TaskED(
                id = task!!.id,
                name = name,
                description = description
            )
        }
        if (task == null) {
            taskHelper.addTask(taskED)
            findNavController().navigate(R.id.nav_graph__list_of_tasks_fragment)
        } else {
            taskHelper.updateTask(taskED)
            val action = EditTaskFragmentDirections.actionNavGrafAddTaskFragmentToNavGrafTaskFragment(
                Task(taskED.id, taskED.name, taskED.description)
            )
            findNavController().navigate(action)
        }

    }

    private fun getToastAboutFillAllFields(): Toast {
        return Toast.makeText(
            requireContext(),
            getString(R.string.fill_name),
            Toast.LENGTH_SHORT
        )
    }
}