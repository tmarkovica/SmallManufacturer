package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.navigationcontrols

import android.os.Bundle
import android.util.Log
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

        Log.d("TAG", "${this.activity}")


        return binding.root
    }

    private fun setTabNavigation() {
        var tabLayoutMediator = binding.tabLayout
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
    // https://material.io/components/tabs/android#fixed-tabs
    // https://developer.android.com/jetpack/androidx/releases/navigation
    // https://www.youtube.com/watch?v=4gUeyNkGE3g
    // https://clopez27.com/blog/android-use-fragmentcontainerview-for-navigation   -> skroz na kraju!
}
