package com.basebox.resumeapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import com.basebox.resumeapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.list_resumes -> {
                findNavController().navigate(R.id.action_settingsFragment_to_resumeListFragment)
                return true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.action_settingsFragment_self)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}