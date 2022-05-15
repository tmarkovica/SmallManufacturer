package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.featuresedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductFeaturesEditBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeatureProductRelationViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.featureadapter.FeatureAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFeaturesEditFragment: Fragment() {

    private lateinit var binding: FragmentProductFeaturesEditBinding
    private val viewModelProduct: ProductsViewModel by viewModel()
    private val viewModelFeatureProduct: FeatureProductRelationViewModel by viewModel()//FeaturesViewModel by viewModel()
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

    private fun getProductIdFromNavigationBundle(): Long {
        return arguments?.getLong("productId") ?: 0
    }

    private fun getProduct(): Product? {
        return viewModelProduct.getProductById(getProductIdFromNavigationBundle())
    }

    private fun setFeaturesObserver() {
        viewModelFeatureProduct.features.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                updateData()
            }
        }
    }

    private fun bindView() {
        val product = getProduct() ?: return
        viewModelFeatureProduct.setProductId(product.id)

        binding.textViewProductName.text = product.name
        binding.textViewProductDescription.text = product.description
        binding.imageViewProductImage.setImageBitmap(product.photo)

        setFeaturesObserver()
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
        viewModelFeatureProduct.features.value?.let { adapter.setFeatures(it) }
    }

    private fun getFeatureFromTextInput() : Feature? {
        val feature = binding.editTextProductFeature.text.toString()
        return if (feature == "") null
        else return Feature(0, feature)
    }

    private fun addFeature() {
        viewModelFeatureProduct.saveFeature(getFeatureFromTextInput() ?: return)
        binding.editTextProductFeature.text.clear()
    }
}