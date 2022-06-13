package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentSelectProductBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.SharedCotactAndProductViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.listing.ProductEventListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectProductFragment: Fragment(), ProductEventListener {

    private lateinit var binding: FragmentSelectProductBinding

    private val sharedCotactAndProductViewModel: SharedCotactAndProductViewModel by activityViewModels()

    private val viewModel: ProductsViewModel by viewModel()

    private lateinit var adapter: ProductSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectProductBinding.inflate(
            inflater,
            container,
            false
        )
        bindView()
        setupRecyclerView()
        binding.buttonConfirm.setOnClickListener { confirmSelectedContact() }
        return binding.root
    }

    private fun updateData() {
        viewModel.products.value?.let { adapter.setProducts(it) }
    }

    private fun bindView() {
        viewModel.products.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                updateData()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ProductSelectionAdapter()
        adapter.listener = this
        binding.recyclerView.adapter = adapter
    }

    override fun onProductClick(id: Long?) {
    }

    override fun onProductLongPress(id: Long?) {
    }

    private fun confirmSelectedContact() {
        val product: Product = adapter.getSelectedProduct() ?: return
        Toast.makeText(context, "Product selected", Toast.LENGTH_SHORT).show()
        sharedCotactAndProductViewModel.setSelectedProduct(product)
        findNavController().navigateUp()
    }
}