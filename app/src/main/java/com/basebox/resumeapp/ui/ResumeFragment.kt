package com.basebox.resumeapp.ui

import android.content.Context.MODE_PRIVATE
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.basebox.resumeapp.R
import com.basebox.resumeapp.data.model.Resume
import com.basebox.resumeapp.databinding.FragmentResumeBinding
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint
class ResumeFragment : Fragment() {

    private val viewModel: ResumeViewModel by viewModels()
    private lateinit var fab: FloatingActionButton
    private var _binding: FragmentResumeBinding? = null
    private val binding get() = _binding!!
    private val sharedPrefFile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPref = requireContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        val editor = sharedPref.edit()
        _binding = FragmentResumeBinding.inflate(inflater, container, false)
        fab = _binding!!.floatingActionButton
        fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_resumeFragment_to_createResumeFragment)
        }

        val bundle = arguments
        if (bundle != null) {
            Log.e("ResumeFragment", "Fragment received information")
            val args = ResumeFragmentArgs.fromBundle(bundle!!)

//            lifecycleScope.launchWhenCreated {
//                val username = sharedPref.getString("name_key",
//                    null)
//                val user = viewModel.showResume(username!!)
//
//                    _binding!!.textView.text = user.name
//                    _binding!!.textView2.text = user.title
//                    _binding!!.textView8.text = user.email
//                    _binding!!.textView9.text = user.phone
//                    _binding!!.textView5.text = user.experience
//                    _binding!!.textView6.text = user.qualification
//                    _binding!!.textView7.text = user.skills
//                    _binding!!.textView10.text = user.linkedin
//                    _binding!!.textView11.text = user.github
//
//                    val uri = Uri.parse(user.image)
//                    Glide.with(requireContext())
//                        .load(File(uri.path!!))
//                        .centerCrop()
//                        .into(_binding!!.imageView)
//                }
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
                findNavController().navigate(R.id.action_resumeFragment_to_resumeListFragment)
                return true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.action_resumeFragment_to_settingsFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}