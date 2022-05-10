package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.productfeaturesedit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductFeaturesEditBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFeaturesEditFragment: Fragment() {

    private lateinit var binding: FragmentProductFeaturesEditBinding
    private val viewModel: ProductsViewModel by viewModel()

//    private val args: ProductFeaturesEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductFeaturesEditBinding.inflate(
            inflater,
            container,
            false
        )
        bindView()
        //binding.buttonAddFeature.setOnClickListener {  }
        return binding.root
    }

    private fun bindView() {
        viewModel.products.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                val product = viewModel.getProductById(1)
                binding.textViewProductName.text = product?.name
                binding.textViewProductDescription.text = product?.description
                Log.d("TAG", product.toString())

                updateData()
            }
        }
    }

    private fun updateData() {
//        viewModel.products.value?.let { adapter.setTasks(it) }


    }
}