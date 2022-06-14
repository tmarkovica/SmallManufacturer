package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hr.ferit.tomislavmarkovica.smallmanufacturer.PreferenceManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.SmallManufacturer.Companion.application
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentSettingsBinding
import org.koin.android.ext.android.getKoin

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(
            inflater,
            container,
            false
        )
        preferenceManager = application.getKoin().get()
        binding.buttonSave.setOnClickListener { saveSettings() }
        displaySetSettings()
        return binding.root
    }

    private fun saveSettings() {
        preferenceManager.allowNotifications(binding.checkBoxAllowNotifications.isChecked)
        findNavController().navigate(R.id.action_settingsFragment_to_holderFragment)
    }

    private fun displaySetSettings() {
        binding.checkBoxAllowNotifications.isChecked =
            preferenceManager.retrieveNotificationsAllowance()
    }
}