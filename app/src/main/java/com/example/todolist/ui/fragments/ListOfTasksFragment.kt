package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.tasks.Task
import com.example.todolist.tasks.TasksAdapter

class ListOfTasksFragment : Fragment(), TasksAdapter.ListItemClickListener {

    private val listOfTask = generateTestTasksList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvList: RecyclerView = view.findViewById(R.id.fragment_list_of_people__rv_list)
        val adapter = TasksAdapter(listOfTask, this)
        rvList.layoutManager = LinearLayoutManager(this.context)
        rvList.adapter = adapter
    }


    override fun onListItemClick(clickedItemIndex: Int) {
        val task: Task = listOfTask[clickedItemIndex]
        val action = ListOfTasksFragmentDirections.actionNavGraphListOfTasksFragmentToTaskFragment(task)
        findNavController().navigate(action) //R.id.nav_graf_task_fragment
    }

}

private fun generateTestTasksList(): List<Task> {
    return listOf(
            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),
            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),
            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),
            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),
            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),
            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),

            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),

            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),

            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),

            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),

            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            ),
            Task(
                    "first task",
                    "description of first task"
            ),
            Task(
                    "second task",
                    "description of first task"
            )
    )
}
