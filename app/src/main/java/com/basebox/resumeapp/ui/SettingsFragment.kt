package com.basebox.resumeapp.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.basebox.resumeapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}