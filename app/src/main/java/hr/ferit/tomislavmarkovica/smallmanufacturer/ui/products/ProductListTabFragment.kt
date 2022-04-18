package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductsBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListTabFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding

    private val viewModel: ProductsViewModel by viewModel()

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
        bindView()
        return binding.root
    }

    private fun bindView() {
        binding.btn.setOnClickListener { viewModel.saveSomething() }
        binding.btn2.setOnClickListener { binding.tv.text = viewModel.getAllProductsInString() }

        viewModel.products.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {

            }
        }
    }
}