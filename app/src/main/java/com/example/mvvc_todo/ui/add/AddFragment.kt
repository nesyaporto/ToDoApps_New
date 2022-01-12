package com.example.mvvc_todo.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvc_todo.R
import com.example.mvvc_todo.databinding.FragmentAddBinding
import com.example.mvvc_todo.db.TaskEntry
import com.example.mvvc_todo.viewmodel.TaskViewModel


class AddFragment : Fragment() {

private val viewModel: TaskViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddBinding.inflate(inflater)

        val myAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.kategori)

        )
        binding.apply {
            spinner.adapter = myAdapter
            btAdd.setOnClickListener {
                if (TextUtils.isEmpty(etEnterTask.text)) {
                    Toast.makeText(
                        requireContext(),
                        "Please Enter the Title of the Task",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener

                }

                val judul_str = etEnterTask.text.toString()
                val kategorispin = spinner.selectedItemPosition
                val taskEntry = TaskEntry(
                    0,
                    judul_str,
                    kategorispin,
                    System.currentTimeMillis()

                )
    viewModel.insertData(taskEntry)
                Toast.makeText(requireContext(),"Successfully Added!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_taskFragment)


            }
        }
    return binding.root
    }

}