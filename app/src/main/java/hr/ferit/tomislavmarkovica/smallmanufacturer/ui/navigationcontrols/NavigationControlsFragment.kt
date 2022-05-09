package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.navigationcontrols

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentNavigationControlsBinding


class NavigationControlsFragment : Fragment() {

    private lateinit var listener: TabButtonClickListener

    private lateinit var binding: FragmentNavigationControlsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationControlsBinding.inflate(
            inflater,
            container,
            false
        )
        listener = parentFragment as TabButtonClickListener
        setTabNavigation()
        return binding.root
    }

    private fun setTabNavigation() {
        val tabLayoutMediator = binding.tabLayout
        tabLayoutMediator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { listener.onTabButtonClick(it) }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }
}
