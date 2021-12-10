package com.example.reminder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.reminder.databinding.FragmentRemindDetailsBinding
import com.example.reminder.viewmodels.RemindViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemindDetailsFragment : Fragment() {
    private var _binding: FragmentRemindDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var remindViewModel: RemindViewModel
    private val args: RemindDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRemindDetailsBinding.inflate(layoutInflater, container, false)
        remindViewModel = ViewModelProvider(this).get(RemindViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            initRemindDetail(args.remindId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun initRemindDetail(id: Long) {
        remindViewModel.getRemindById(id).observe(viewLifecycleOwner, Observer {
            binding.textviewTitle.text = it.title
            binding.textViewSubject.text = it.subject
        })

    }
}