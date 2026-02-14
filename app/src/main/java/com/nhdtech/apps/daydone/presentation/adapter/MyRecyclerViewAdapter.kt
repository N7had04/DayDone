package com.nhdtech.apps.daydone.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.databinding.ListItemBinding

class MyRecyclerViewAdapter(val taskList: List<Task>): RecyclerView.Adapter<MyViewHolder>() {

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
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task) {
        binding.tvTaskTitle.text = task.title
        binding.tvTaskDescription.text = task.description
        binding.tvTaskDeadline.text = task.deadline.toString()
    }
}