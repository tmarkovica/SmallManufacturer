package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.holder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentHolderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contacts.ContactsFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.navigationcontrols.TabButtonClickListener
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.orders.OrdersFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products.ProductListTabFragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products.ProductLongPressListener


class HolderFragment : Fragment(), TabButtonClickListener, ProductLongPressListener {

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

    private fun getFragmentForButtonClicked(position: Int) : Fragment {
        return when (position) {
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
    }

    override fun onTabButtonClick(position: Int) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, getFragmentForButtonClicked(position))
        fragmentTransaction.commit()
    }

    override fun onProductLongPress(id: Long?) {
        val bundle = Bundle()
        bundle.putLong("productId", id ?: return)
        findNavController().navigate(R.id.action_holderFragment_to_productFeaturesEditFragment, bundle)
    }
}