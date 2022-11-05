package com.basebox.resumeapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.basebox.resumeapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [CreateResumeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateResumeFragment : Fragment() {

    private lateinit var nameView: EditText
    private lateinit var titleView: EditText
    private lateinit var emailView: EditText
    private lateinit var phoneView: EditText
    private lateinit var experience: EditText
    private lateinit var skills: EditText
    private lateinit var qualification: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        nameView = requireView().findViewById(R.id.editTextTextPersonName)
        titleView = requireView().findViewById(R.id.editTextTextPersonName2)
        emailView = requireView().findViewById(R.id.editTextTextEmailAddress)
        phoneView = requireView().findViewById(R.id.editTextPhone)
        experience = requireView().findViewById(R.id.editTextTextPersonName3)
        skills = requireView().findViewById(R.id.editTextTextPersonName4)
        qualification = requireView().findViewById(R.id.editTextTextMultiLine2)

        val button = requireView().findViewById<Button>(R.id.button)
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

                resumeIntent.putExtra(EXTRA_RESUME_NAME, name)
                resumeIntent.putExtra(EXTRA_RESUME_TITLE, title)
                resumeIntent.putExtra(EXTRA_RESUME_EMAIL, email)
                resumeIntent.putExtra(EXTRA_RESUME_PHONE, phone)
                resumeIntent.putExtra(EXTRA_RESUME_EXP, exp)
                resumeIntent.putExtra(EXTRA_RESUME_SKILL, skill)
                resumeIntent.putExtra(EXTRA_RESUME_QUAL, qual)
                requireActivity().setResult(Activity.RESULT_OK, resumeIntent)
            }
            requireActivity().finish()
        }
        return inflater.inflate(R.layout.fragment_create_resume, container, false)
    }

    companion object {
        const val EXTRA_RESUME_NAME = "com.example.android.wordlistsql.RESUME"
        const val EXTRA_RESUME_TITLE = "com.example.android.wordlistsql.TITLE"
        const val EXTRA_RESUME_EMAIL = "com.example.android.wordlistsql.EMAIL"
        const val EXTRA_RESUME_PHONE = "com.example.android.wordlistsql.PHONE"
        const val EXTRA_RESUME_EXP = "com.example.android.wordlistsql.EXP"
        const val EXTRA_RESUME_SKILL= "com.example.android.wordlistsql.SKILL"
        const val EXTRA_RESUME_QUAL = "com.example.android.wordlistsql.RQUAL"
    }
}