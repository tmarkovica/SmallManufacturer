package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductsBinding

class ProductsFragment: Fragment() {

    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
}