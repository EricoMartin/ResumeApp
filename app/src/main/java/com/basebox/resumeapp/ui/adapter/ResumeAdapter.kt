package com.basebox.resumeapp.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.basebox.resumeapp.R
import com.basebox.resumeapp.data.model.Resume
import com.basebox.resumeapp.data.repo.DefaultRepository
import com.basebox.resumeapp.data.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResumeAdapter: ListAdapter<Resume, ResumeAdapter.ResumeViewHolder>(ResumeComparator()) {
//    private lateinit var current: Resume

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeViewHolder {
        return ResumeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ResumeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title, current.name)
        holder.itemView.setOnClickListener {
                val bundle: Bundle?  = Bundle()
            bundle?.putParcelable("RESUME", current!!)
            Navigation.createNavigateOnClickListener(R.id.action_resumeListFragment_to_resumeFragment, bundle).onClick(holder.itemView)
            }
        }


    class ResumeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val jobTitle: TextView = itemView.findViewById(R.id.textView13)
        private val username: TextView = itemView.findViewById(R.id.textView14)

        fun bind(title: String?, name: String) {
            jobTitle.text = title
            username.text = name
            }


        companion object {
            fun create(parent: ViewGroup): ResumeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.resume_list_item, parent, false)
                return ResumeViewHolder(view)
            }
        }
    }

    class ResumeComparator : DiffUtil.ItemCallback<Resume>() {
        override fun areItemsTheSame(oldItem: Resume, newItem: Resume): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Resume, newItem: Resume): Boolean {
            return oldItem.id == newItem.id
        }
    }
}