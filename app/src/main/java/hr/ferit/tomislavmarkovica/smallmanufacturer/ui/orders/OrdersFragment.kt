package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(
            inflater,
            container,
            false
        )
        binding.floatingActionButtonAddOrder.setOnClickListener { showCreateOrderFragment() }
        return binding.root
    }

    private fun showCreateOrderFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_holderFragment_to_createOrderFragment)
    }


}