package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.holder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentHolderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.navigationcontrols.TabButtonClickListener
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products.FabEventListener

class HolderFragment : Fragment(), TabButtonClickListener, FabEventListener {

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
//        Log.d("TAG", "1. " + findNavController().toString())
//        Log.d("TAG", "1. " + childFragmentManager.fragments[0].toString())
        childFragmentManager.fragments[0].findNavController().navigate(action)
    }

    override fun onFabClick() {
        Log.d("TAG", "onFabClick()")
        val action: NavDirections = ActionOnlyNavDirections(R.id.action_holderFragment_to_productCreationFragment)
        Log.d("TAG", action.toString())
        Log.d("TAG", childFragmentManager.fragments[0].toString())
        Log.d("TAG", parentFragmentManager.fragments.toString())
        Log.d("TAG", parentFragmentManager.fragments[0].toString())

        //parentFragmentManager.fragments[0].findNavController().navigate(action)


//        Log.d("TAG", "parentFragment " + parentFragment.toString())
        //this.findNavController().navigate(action)
    //findNavController().navigate(action)

//        val action: NavDirections = ActionOnlyNavDirections(R.id.action_holderFragment_to_productCreationFragment)
//        parentFragment?.findNavController()?.navigate(action)
    }
}