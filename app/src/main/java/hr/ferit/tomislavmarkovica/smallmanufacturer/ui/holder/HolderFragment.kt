package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.holder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentHolderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.listing.ContactsFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.navigationcontrols.TabButtonClickListener
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.details.OrderEventListener
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.listing.OrdersFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.listing.ProductsFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.listing.ProductLongPressListener


class HolderFragment : Fragment(), TabButtonClickListener, ProductLongPressListener, OrderEventListener {

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
        binding.toolbar.settings.setOnClickListener { showSettingsFragment() }
        return binding.root
    }

    private fun getFragmentForButtonClicked(position: Int) : Fragment {
        return when (position) {
                0 -> {
                ProductsFragment()
            }
                1 -> {
                OrdersFragment()
            }
                else -> {
                ContactsFragment()
            }
        }
    }

    override fun onTabButtonClick(position: Int) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, getFragmentForButtonClicked(position))
        fragmentTransaction.commit()
    }

    private fun createBundle(key: String, value: Long): Bundle {
        val bundle = Bundle()
        bundle.putLong(key, value)
        return bundle
    }

    override fun onProductLongPress(id: Long?) {
        findNavController().navigate(R.id.action_holderFragment_to_productFeaturesEditFragment, createBundle("productId", id!!))
    }

    override fun onOrderLongPress(id: Long?) {
        findNavController().navigate(R.id.action_holderFragment_to_orderDetailsFragment, createBundle("orderId", id!!))
    }

    private fun showSettingsFragment() {
        findNavController().navigate(R.id.action_holderFragment_to_settingsFragment)
    }
}