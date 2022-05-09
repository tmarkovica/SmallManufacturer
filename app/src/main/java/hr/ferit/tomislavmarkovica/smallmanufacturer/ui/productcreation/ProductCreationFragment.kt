package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.productcreation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductCreationBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductsBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeaturesViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products.OnProductEventListener
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products.ProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductCreationFragment: Fragment(), FeatureEventListener {

    private lateinit var binding: FragmentProductCreationBinding
    private val viewModelProducts: ProductsViewModel by viewModel()
    private val viewModelFeatures: FeaturesViewModel by viewModel()
    private lateinit var adapter: FeatureAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCreationBinding.inflate(
            inflater,
            container,
            false
        )
        binding.buttonSaveProduct.setOnClickListener { saveProduct() }
        binding.buttonAddFeature.setOnClickListener { addFeature() }
        bindView()
        setupRecyclerView()
        return binding.root
    }

    private fun saveProduct() {
        val name = binding.editTextProductName.text.toString()
        val description = binding.editTextProductDescription.text.toString()

        if (name == "" || description == "")
            return;

        var product = Product(0, name, description)
        viewModelProducts.saveProduct(product)
        Toast.makeText(context, "New product added", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).navigate(R.id.action_productCreationFragment_to_holderFragment)
    }

    private fun addFeature() {
        val feature = Feature(0, binding.editTextProductFeature.text.toString())
        viewModelFeatures.saveFeature(0, feature)
    }

    override fun onFeatureClick(feature: Feature) {
        Log.d("TAG", "Feature pressed: $feature")
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFeatures.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = FeatureAdapter()
        adapter.listener = this
        binding.recyclerViewFeatures.adapter = adapter
    }

    private fun updateData() {
        //viewModel.products.value?.let { adapter.setFeatures(it) }
        viewModelFeatures.features.value?.let { adapter.setFeatures(it) }
    }

    private fun bindView() {
        viewModelFeatures.features.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                updateData()
            }
        }
    }
}