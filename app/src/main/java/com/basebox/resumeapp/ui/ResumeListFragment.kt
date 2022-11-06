package com.basebox.resumeapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.basebox.resumeapp.R
import com.basebox.resumeapp.databinding.FragmentResumeListBinding
import com.basebox.resumeapp.ui.adapter.ResumeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResumeListFragment : Fragment() {

    private var _binding: FragmentResumeListBinding? = null
    private val viewModel: ResumeViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResumeListBinding.inflate(inflater, container, false)
        val adapter = ResumeAdapter()
        val recyclerView = _binding!!.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getALlResumes.observe(viewLifecycleOwner) { it ->
            // Update the cached copy of the Resumes in the adapter.
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.getALlResumes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                _binding!!.linearCompat.visibility = View.VISIBLE
                _binding!!.recycler.visibility = View.GONE
            }
        }

        binding.newResume.setOnClickListener {
            findNavController().navigate(R.id.action_resumeListFragment_to_createResumeFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.list_resumes -> {
                findNavController().navigate(R.id.action_resumeListFragment_self)
                return true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.action_resumeListFragment_to_settingsFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}