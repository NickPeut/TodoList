package com.example.todolist.tasks


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.TaskED
import com.example.todolist.ui.fragments.EditTaskFragmentDirections
import com.example.todolist.viewModel.TaskHelper

class TasksAdapter(
    private var tasks: List<Task>,
    listener: ListItemClickListener
) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    private val mOnClickListener: ListItemClickListener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TasksViewHolder(view)
    }

    fun setData(listOfTasks: List<Task>) {
        this.tasks = listOfTasks
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    interface ListItemClickListener {
        fun onListItemClick(clickedItemIndex: Int)
        fun onCheckItemClick(clickedItemIndex: Int)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cardView: CardView = itemView.findViewById(R.id.task_item__card_view)
        private var tvName: TextView = itemView.findViewById(R.id.task_item__task_name)
        private var isDone: ImageButton = itemView.findViewById(R.id.task_item__ib_check)
        fun getCardView(): CardView = cardView

        fun bind(task: Task) {
            tvName.text = task.name
            if (task.isDone) {
                isDone.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
            } else {
                isDone.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
            }
            isDone.setOnClickListener(
                View.OnClickListener {
                    val clickedPosition = adapterPosition
                    mOnClickListener.onCheckItemClick(clickedPosition)
                    task.isDone = !task.isDone
                    if (task.isDone) {
                        isDone.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                    } else {
                        isDone.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
                    }
                }
            )
            cardView.setOnClickListener(
                View.OnClickListener {
                    val clickedPosition = adapterPosition
                    mOnClickListener.onListItemClick(clickedPosition)
                }
            )
        }
    }
}
