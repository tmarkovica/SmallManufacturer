package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.holder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentHolderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.navigationcontrols.TabButtonClickListener

class HolderFragment : Fragment(), TabButtonClickListener {

    private lateinit var binding: FragmentHolderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHolderBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onTabButtonClick(position: Int) {
        Log.d("TAG", "HolderFragment: Tab selected + $position")

        val action: NavDirections = when (position) {
            0 -> {
                ActionOnlyNavDirections(R.id.to_productsFragment)
            }
            1 -> {
                ActionOnlyNavDirections(R.id.to_contactsFragment)
            }
            else -> {
                ActionOnlyNavDirections(R.id.to_ordersFragment)
            }
        }
        childFragmentManager.fragments[0].findNavController().navigate(action)
    }
}