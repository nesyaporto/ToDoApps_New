package com.example.mvvc_todo.ui.task



import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvc_todo.databinding.CategoriesLayoutBinding
import com.example.mvvc_todo.databinding.RowLayoutBinding
import com.example.mvvc_todo.db.TaskEntry

class TaskAdapter(private val listener: OnItemClickListener) : ListAdapter<TaskEntry, TaskAdapter.ViewHolder>(TaskDiffCallback){

    companion object TaskDiffCallback : DiffUtil.ItemCallback<TaskEntry>(){
        override fun areItemsTheSame(oldItem: TaskEntry, newItem: TaskEntry) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskEntry, newItem: TaskEntry) = oldItem == newItem
    }

    inner class ViewHolder(val binding:RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        init{
            binding.apply {
                root.setOnClickListener{
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        val taskEntry = getItem(position)
                        listener.onItemClick(taskEntry)
                    }
                }
                itemTodo.setOnClickListener{
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        val taskEntry = getItem(position)
                        listener.onCheckBoxClick(taskEntry, itemTodo.isChecked)
                    }
                }
            }
        }

        fun bind(taskEntry: TaskEntry) {
            binding.apply {
                itemTodo.isChecked = taskEntry.complete
                tvJudul.text = taskEntry.judul
                tvJudul.paint.isStrikeThruText = taskEntry.complete
            }
            binding.taskEntry = taskEntry
            binding.executePendingBindings()

        }
    }

    interface OnItemClickListener{
        fun onItemClick(taskEntry: TaskEntry)
        fun onCheckBoxClick(taskEntry: TaskEntry, isChecked:Boolean)
    }

    class KategoriViewHolder(val binding:CategoriesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskEntry: TaskEntry) {
            binding.taskEntry = taskEntry
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val current = getItem(position)
        holder.bind(current)

        }
    }

