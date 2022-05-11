package hr.ferit.tomislavmarkovica.smallmanufacturer.product.featuresedit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductFeaturesEditBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeaturesViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.product.featureadapter.FeatureAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFeaturesEditFragment: Fragment() {

    private lateinit var binding: FragmentProductFeaturesEditBinding
    private val viewModelProduct: ProductsViewModel by viewModel()
    private val viewModelFeatures: FeaturesViewModel by viewModel()
    private lateinit var adapter: FeatureAdapter

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
        setupRecyclerView()
        binding.buttonAddFeature.setOnClickListener { addFeature() }

        return binding.root
    }

    private fun bindView() {
        viewModelFeatures.features.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                val productId = arguments?.getLong("productId")
                Log.d("TAG", "Navigation argument: $productId")
                val product = viewModelProduct.getProductById(productId)
                binding.textViewProductName.text = product?.name
                binding.textViewProductDescription.text = product?.description
                binding.imageViewProductImage.setImageBitmap(product?.photo)
                updateData()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFeatures.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = FeatureAdapter()
        binding.recyclerViewFeatures.adapter = adapter
    }

    private fun updateData() {
        viewModelFeatures.features.value?.let { adapter.setFeatures(it) }
    }

    private fun getFeatureFromTextInput() : Feature? {
        val feature = binding.editTextProductFeature.text.toString()
        return if (feature == "") null
        else return Feature(0, feature)
    }

    private fun addFeature() {
        viewModelFeatures.saveFeature(getFeatureFromTextInput() ?: return)
        binding.editTextProductFeature.text.clear()
    }
}