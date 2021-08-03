package com.aransafp.subar.ui.settings

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.aransafp.subar.R
import com.aransafp.subar.notification.DailyReminder

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, SettingsFragment())
            .commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)

            val notificationPreference =
                findPreference<SwitchPreference>(getString(R.string.key_notification))

            notificationPreference?.setOnPreferenceChangeListener { _, newValue ->
                val dailyReminder = DailyReminder()
                if (newValue == true) {
                    //start alarm manager
                    dailyReminder.setAlarm(context as Context)
                    showToast("Notification on")
                } else {
                    //cancel alarm manager
                    dailyReminder.cancelAlarm(context as Context)
                    showToast("Notification off")

                }

                true
            }

        }

        private fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

    }


}