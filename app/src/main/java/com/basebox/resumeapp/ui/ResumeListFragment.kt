package com.basebox.resumeapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.basebox.resumeapp.R
import com.basebox.resumeapp.data.model.Resume
import com.basebox.resumeapp.databinding.FragmentResumeListBinding
import com.basebox.resumeapp.ui.adapter.ResumeAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResumeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResumeListFragment : Fragment() {
    private var _binding: FragmentResumeListBinding? = null
    private lateinit var viewModel: ResumeViewModel
    private val binding get() = _binding!!
    val recyclerView = _binding!!.recycler
    val adapter = ResumeAdapter()
    private val newResumeRequestCode = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResumeListBinding.inflate(inflater, container, false)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(this).get(ResumeViewModel::class.java)

            viewModel.getALlResumes.observe(viewLifecycleOwner) { it ->
                // Update the cached copy of the words in the adapter.
                it?.let {
                    adapter.submitList(it)
                }

            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newResumeRequestCode && resultCode == Activity.RESULT_OK) {
            val skill = data?.getStringExtra(CreateResumeFragment.EXTRA_RESUME_SKILL)
            val phone = data?.getStringExtra(CreateResumeFragment.EXTRA_RESUME_PHONE)
            val email = data?.getStringExtra(CreateResumeFragment.EXTRA_RESUME_EMAIL)
            val exp = data?.getStringExtra(CreateResumeFragment.EXTRA_RESUME_EXP)
            val qual = data?.getStringExtra(CreateResumeFragment.EXTRA_RESUME_QUAL)
            val title = data?.getStringExtra(CreateResumeFragment.EXTRA_RESUME_TITLE)
            val name = data?.getStringExtra(CreateResumeFragment.EXTRA_RESUME_NAME)

                lifecycleScope.launchWhenCreated {
                    val resume = Resume(0, title!!, name!!, 0, phone!!, exp!!,
                        qual!!, "", "", skill!! )
                    with(viewModel) {

                        createResume(resume)
                    }
                }


        } else {
            Toast.makeText(
                requireContext().applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    companion object {

    }
}