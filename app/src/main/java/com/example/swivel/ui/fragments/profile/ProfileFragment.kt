package com.example.swivel.ui.fragments.profile

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.swivel.R

class ProfileFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        val sharedPreferences = preferenceScreen.sharedPreferences
        val count = preferenceScreen.preferenceCount
        for (i in 0 until count) {
            val preference = preferenceScreen.getPreference(i)
            setPreferenceSummery(preference, sharedPreferences.getString(preference.key, "")!!)
        }
    }

    private fun setPreferenceSummery(preference: Preference, value: Any) {
        val stringValue = value.toString()
        if (preference is ListPreference) {
            val prefIndex = preference.findIndexOfValue(stringValue)
            if (prefIndex >= 0) {
                preference.summary = preference.entries[prefIndex]
            }
        } else {
            preference.summary = stringValue
        }
    }
}