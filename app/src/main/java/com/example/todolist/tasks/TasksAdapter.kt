package com.example.todolist.tasks


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

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
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cardView: CardView = itemView.findViewById(R.id.task_item__card_view)
        private var tvName: TextView = itemView.findViewById(R.id.task_item__task_name)

        fun getCardView(): CardView = cardView

        fun bind(task: Task) {
            tvName.text = task.name
            cardView.setOnClickListener(
                    View.OnClickListener {
                        val clickedPosition = adapterPosition
                        mOnClickListener.onListItemClick(clickedPosition)
                    }
            )
        }
    }
}
