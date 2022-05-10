package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.holder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentHolderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contacts.ContactsFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.navigationcontrols.TabButtonClickListener
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.orders.OrdersFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products.ProductListTabFragment


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
        val fragment: Fragment = when (position) {
            0 -> {
                ProductListTabFragment()
            }
            1 -> {
                OrdersFragment()
            }
            else -> {
                ContactsFragment()
            }
        }
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}