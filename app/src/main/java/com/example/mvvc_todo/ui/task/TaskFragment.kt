package com.example.mvvc_todo.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvc_todo.R
import com.example.mvvc_todo.databinding.FragmentTaskBinding
import com.example.mvvc_todo.db.TaskEntry
import com.example.mvvc_todo.viewmodel.TaskViewModel


class TaskFragment : Fragment(R.layout.fragment_task), TaskAdapter.OnItemClickListener {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter
    private lateinit var adapter2: KategoriAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTaskBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = TaskAdapter(this)
        adapter2 = KategoriAdapter()
        viewModel.getAllTask.observe(viewLifecycleOwner){
            adapter.submitList(it)
            adapter2.submitList(it)
        }

        binding.apply {

            binding.recyclerView.adapter = adapter
            binding.rvKategori.adapter = adapter2

            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_taskFragment_to_addFragment)

            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onItemClick(taskEntry: TaskEntry) {
        viewModel.onTaskSelected(taskEntry)
    }

    override fun onCheckBoxClick(taskEntry: TaskEntry, isChecked: Boolean) {
        viewModel.onTaskCheckedChanged(taskEntry, isChecked)
    }


}