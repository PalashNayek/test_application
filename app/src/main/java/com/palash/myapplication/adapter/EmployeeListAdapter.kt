package com.palash.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.palash.myapplication.databinding.EmpItemBinding
import com.palash.myapplication.models.response.Data

class EmployeeListAdapter : ListAdapter<Data, EmployeeListAdapter.EmployeeViewHolder>(DiffUtilCallback()) {

    inner class EmployeeViewHolder(private val binding: EmpItemBinding) : ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.txtName.text = item.employee_name
            binding.txtSalary.text = item.employee_salary.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = EmpItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}