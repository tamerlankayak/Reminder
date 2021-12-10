package com.example.reminder.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.reminder.R
import com.example.reminder.databinding.FragmentAddRemindBinding
import com.example.reminder.entity.Remind
import com.example.reminder.viewmodels.RemindViewModel


class AddRemindFragment : Fragment() {

    private var _binding: FragmentAddRemindBinding? = null
    private val binding get() = _binding!!

   private lateinit var remindViewModel: RemindViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddRemindBinding.inflate(layoutInflater, container, false)


        remindViewModel = ViewModelProvider(this).get(RemindViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {

            insertDataToDatabase()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun insertDataToDatabase() {
        val title = binding.editextTitle.text.toString()
        val subject = binding.editextSubject.text.toString()

        if (inputCheck(title, subject)) {
            val remind = Remind(0, title, subject)
            remindViewModel.addRemind(remind)
            Toast.makeText(requireContext(), "Xatırlama əlavə edildi", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addRemindFragment_to_remindListFragment)
        }else{
            Toast.makeText(requireContext(), "Zəhmət olmasa bütün sahələri doldurun", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, subject: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(subject))
    }
}