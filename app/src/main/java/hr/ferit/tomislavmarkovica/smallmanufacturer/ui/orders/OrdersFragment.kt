package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentOrdersBinding

class OrdersFragment: Fragment() {

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
        return binding.root
    }
}