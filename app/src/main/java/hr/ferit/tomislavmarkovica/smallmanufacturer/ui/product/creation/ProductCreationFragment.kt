package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.creation

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductCreationBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeaturesViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.featureadapter.FeatureAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class ProductCreationFragment: Fragment() {

    private lateinit var binding: FragmentProductCreationBinding
    private val viewModelProducts: ProductsViewModel by viewModel()
    private val viewModelFeatures: FeaturesViewModel by viewModel()
    private lateinit var adapter: FeatureAdapter

    private lateinit var imageBitmap: Bitmap

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
        binding.imageViewProductImage.setOnClickListener { addProductPhoto() }
        binding.imageViewProductImage.setOnLongClickListener { removePhoto() }
        getDefaultProductImageFromResources()
        bindView()
        setupRecyclerView()
        return binding.root
    }

    private fun getDefaultProductImageFromResources() {
        imageBitmap = BitmapFactory.decodeResource(resources, R.drawable.factory_256) as Bitmap
    }

    private fun setImageToImageView() {
        binding.imageViewProductImage.setImageBitmap(imageBitmap)
    }

    private fun removePhoto(): Boolean {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Yes"
                ) { _, _ ->
                        // User clicked OK button
                        getDefaultProductImageFromResources()
                        setImageToImageView()
                    }
                setNegativeButton("No"
                ) { _, _ ->
                    // User cancelled the dialog
                }
                setTitle("Do you want to delete image")
            }
            builder.create()
        }
        alertDialog?.show()
        return true
    }

    private fun updateData() {
        viewModelFeatures.features.value?.let { adapter.setFeatures(it) }
    }

    private fun getProductFromInput() : Product? {
        val name = binding.editTextProductName.text.toString()
        val description = binding.editTextProductDescription.text.toString()
        return if (name == "") null
            else
                Product(0, name, description, imageBitmap)
    }

    private fun getFeatureFromTextInput() : Feature? {
        val feature = binding.editTextProductFeature.text.toString()
        return if (feature == "") null
            else return Feature(0, feature)
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

    private fun bindView() {
        viewModelFeatures.features.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                updateData()
            }
        }
    }

    private fun saveProduct() {
        val productId = viewModelProducts.saveProduct(getProductFromInput() ?: return)
        viewModelFeatures.save(productId)

        Toast.makeText(context, "New product added", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).navigate(R.id.action_productCreationFragment_to_holderFragment)
    }

    private fun addFeature() {
        viewModelFeatures.addFeature(getFeatureFromTextInput() ?: return)
        binding.editTextProductFeature.text.clear()
    }

    private fun addProductPhoto() {
        dispatchTakePictureIntent()
    }

    // Camera
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            setImageToImageView()
        }
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }
}