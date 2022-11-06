package com.basebox.resumeapp.ui

import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.basebox.resumeapp.R
import com.basebox.resumeapp.data.model.Resume
import com.basebox.resumeapp.databinding.FragmentCreateResumeBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [CreateResumeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CreateResumeFragment : Fragment() {

    private lateinit var nameView: EditText
    private lateinit var titleView: EditText
    private lateinit var emailView: EditText
    private lateinit var phoneView: EditText
    private lateinit var experience: EditText
    private lateinit var skills: EditText
    private lateinit var qualification: EditText
    private lateinit var linkedIn: EditText
    private lateinit var  github: EditText
    private var _binding: FragmentCreateResumeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ResumeViewModel by viewModels()
    private lateinit var selectedImageUri: Uri
    private val sharedPrefFile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateResumeBinding.inflate(inflater, container, false)

        nameView = _binding!!.editTextTextPersonName
        titleView = _binding!!.editTextTextPersonName2
        emailView = _binding!!.editTextTextEmailAddress
        phoneView = _binding!!.editTextPhone
        experience = _binding!!.editTextTextPersonName3
        skills = _binding!!.editTextTextPersonName4
        qualification = _binding!!.editTextTextMultiLine2
        linkedIn = _binding!!.editTextTextMultiLine
        github =_binding!!.editTextTextMultiLine3

        val button = _binding!!.button
        button.setOnClickListener {
            val resumeIntent = Intent()
            if (TextUtils.isEmpty(nameView.text) || TextUtils.isEmpty(titleView.text)) {
                requireActivity().setResult(Activity.RESULT_CANCELED, resumeIntent)
            } else {
                val name = nameView.text.toString()
                val title = titleView.text.toString()
                val email = emailView.text.toString()
                val phone= phoneView.text.toString()
                val exp = experience.text.toString()
                val skill = skills.text.toString()
                val qual = qualification.text.toString()
                val image = selectedImageUri.toString()
                val link = linkedIn.text.toString()
                val git = github.text.toString()
                if (image != ""){
                    binding.textView4.visibility = View.VISIBLE
                }
                val sharedPreferences: SharedPreferences =
                    requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

                val resume = Resume(0, title, name, email, image, phone, exp, qual, link, git, skill)
                lifecycleScope.launchWhenCreated {
                    viewModel.createResume(resume)
                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                    editor.putInt("id_key",resume.id)
                    editor.putString("name_key",name)
                    editor.apply()
                    editor.commit()
                }
                val directions = CreateResumeFragmentDirections.actionCreateResumeFragmentToResumeFragment(
              resume.name
          )
                findNavController().navigate(directions)

            }
            //findNavController().navigate(R.id.action_createResumeFragment_to_resumeListFragment)
        }

        binding.textView3.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "select a picture"),
                IMAGE_CODE
            )
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === IMAGE_CODE) {
            if (resultCode === RESULT_OK){
                selectedImageUri = data?.data!!
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.list_resumes -> {
                findNavController().navigate(R.id.action_createResumeFragment_to_resumeListFragment)
                return true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.action_createResumeFragment_to_settingsFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_RESUME_NAME = "com.example.android.wordlistsql.RESUME"
        const val EXTRA_RESUME_TITLE = "com.example.android.wordlistsql.TITLE"
        const val EXTRA_RESUME_EMAIL = "com.example.android.wordlistsql.EMAIL"
        const val EXTRA_RESUME_PHONE = "com.example.android.wordlistsql.PHONE"
        const val EXTRA_RESUME_EXP = "com.example.android.wordlistsql.EXP"
        const val EXTRA_RESUME_SKILL= "com.example.android.wordlistsql.SKILL"
        const val EXTRA_RESUME_QUAL = "com.example.android.wordlistsql.RQUAL"
        const val IMAGE_CODE = 1
    }
}