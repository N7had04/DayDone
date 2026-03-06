package com.nhdtech.apps.daydone.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.databinding.ListItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyRecyclerViewAdapter(private val onDeleteClick: (Task) -> Unit): RecyclerView.Adapter<MyViewHolder>() {

    private var taskList: List<Task> = emptyList()

    fun submitList(newList: List<Task>) {
        taskList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.bind(taskList[position], onDeleteClick)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        task: Task,
        onDeleteClick: (Task) -> Unit
    ) {
        binding.tvTaskTitle.text = task.title
        binding.tvTaskDescription.text = task.description
        val format = SimpleDateFormat(
            "dd MMM yyyy, HH:mm",
            Locale.getDefault()
        )
        binding.tvTaskDeadline.text = format.format(Date(task.deadline))

        val currentTime = System.currentTimeMillis()
        if (task.deadline < currentTime) {
            binding.tvOverdue.visibility = View.VISIBLE
        } else {
            binding.tvOverdue.visibility = View.GONE
        }

        binding.checkBox.setOnCheckedChangeListener(null)
        binding.checkBox.isChecked = false
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onDeleteClick(task)
            }
        }
    }
}