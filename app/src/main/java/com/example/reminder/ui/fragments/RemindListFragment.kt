package com.example.reminder.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminder.R
import com.example.reminder.adapters.RemindAdapter
import com.example.reminder.databinding.FragmentRemindListBinding
import com.example.reminder.entity.Remind
import com.example.reminder.viewmodels.RemindViewModel

class RemindListFragment : Fragment() {

    private var _binding: FragmentRemindListBinding? = null
    private val binding get() = _binding!!
    private lateinit var remindViewModel: RemindViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRemindListBinding.inflate(inflater, container, false)
        val view = binding.root
        remindViewModel = ViewModelProvider(this).get(RemindViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRemindList()

        binding.floatBtnAddNewRemind.setOnClickListener {
            findNavController().navigate(R.id.action_remindListFragment_to_addRemindFragment)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initRemindList() {
        var recyclerView = binding.recyclewviewRemindList
        var adapter = RemindAdapter { reminds ->
            val action = RemindListFragmentDirections.actionRemindListFragmentToRemindDetailsFragment(reminds.id)
            Navigation.findNavController(binding.root).navigate(action)            }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        remindViewModel.readAllData.observe(viewLifecycleOwner, Observer { remind ->
            adapter.submitList(remind)
            for (item in remind){
                Log.e("Db data", item.title.toString() )
            }
        })
    }

}




