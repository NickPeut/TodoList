package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ITaskService
import com.example.todolist.database.TaskED
import com.example.todolist.database.TaskService
import com.example.todolist.tasks.Task
import com.example.todolist.tasks.TasksAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListOfTasksFragment : Fragment(), TasksAdapter.ListItemClickListener {
    private val personService: ITaskService = TaskService.getInstance()
    private lateinit var listOfTask:List<Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvList: RecyclerView = view.findViewById(R.id.fragment_list_of_task__rv_list)
        var buttonAdd :ImageButton = view.findViewById(R.id.fragment_list_of_task__btn_add)
        val adapter = TasksAdapter(emptyList(), this)
        GlobalScope.launch(Dispatchers.Main) {
            val people = async(Dispatchers.IO) {
                personService.findAll()
            }

            listOfTask = toTaskView(people.await())
            adapter.setData(listOfTask)
        }
        rvList.layoutManager = LinearLayoutManager(this.context)
        rvList.adapter = adapter
        buttonAdd.setOnClickListener() {
            val action =
                ListOfTasksFragmentDirections.actionNavGraphListOfTasksFragmentToNavGrafAddTaskFragment(null,getString(R.string.new_task))
            findNavController().navigate(action)
        }
    }


    override fun onListItemClick(clickedItemIndex: Int) {
        val task: Task = listOfTask[clickedItemIndex]
        val action =
            ListOfTasksFragmentDirections.actionNavGraphListOfTasksFragmentToTaskFragment(task)
        findNavController().navigate(action)
    }

    private fun toTaskView(people: List<TaskED>): List<Task> {
        return people.map {
            Task.Builder.createBuilder()
                .id(it.id)
                .name(it.name!!)
                .description(it.description!!)
                .build()
        }
    }
}
