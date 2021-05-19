package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.TaskED
import com.example.todolist.viewModel.AddTaskViewModel

class AddTaskFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }


    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var etName: EditText
    private lateinit var etDescription: EditText


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etName = view.findViewById(R.id.fragment_add_task__et_name)
        etDescription = view.findViewById(R.id.fragment_add_task__et_description)

        addTaskViewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
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

        val taskED = TaskED(
            id = name,
            name = name,
            description = description
        )
        //TODO()
        addTaskViewModel.addTask(taskED)

        findNavController().navigate(R.id.nav_graph__list_of_tasks_fragment)

    }

    private fun getToastAboutFillAllFields(): Toast {
        return Toast.makeText(
            requireContext(),
            getString(R.string.fill_name),
            Toast.LENGTH_SHORT
        )
    }
}