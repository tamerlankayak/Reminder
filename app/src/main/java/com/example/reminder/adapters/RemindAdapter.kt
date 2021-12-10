package com.example.reminder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.entity.Remind

class RemindAdapter(private val onClick: (Remind) -> Unit) :
    ListAdapter<Remind, RemindAdapter.RemindViewHolder>(RemindDiffCallback) {


    class RemindViewHolder(itemView: View, val onClick: (Remind) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.textview_title)
        private var subject: TextView = itemView.findViewById(R.id.textview_subject)
        private var currentContracts: Remind? = null

        init {
            itemView.setOnClickListener {
                currentContracts?.let {
                    onClick(it)
                }
            }
        }

        fun bind(contracts: Remind) {
            currentContracts = contracts
            title.text = contracts.title
            subject.text = contracts.subject
        }
    }

    object RemindDiffCallback : DiffUtil.ItemCallback<Remind>() {
        override fun areItemsTheSame(oldItem: Remind, newItem: Remind): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Remind, newItem: Remind): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_remind, parent, false)
        return RemindViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: RemindViewHolder, position: Int) {
        val reminds = getItem(position)
        holder.bind(reminds)
    }
}

