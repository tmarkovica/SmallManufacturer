package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentProductsBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListTabFragment : Fragment(), OnProductEventListener {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModel()
    private lateinit var adapter: ProductAdapter
    private lateinit var listener: FabEventListener

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
        setupRecyclerView()
        binding.floatingActionButtonAddProduct.setOnClickListener { showProductCreationFragment() }
        listener = parentFragment as FabEventListener
        return binding.root
    }

    private fun showProductCreationFragment() {
        listener.onFabClick()
    }

    private fun bindView() {
        viewModel.products.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                updateData()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ProductAdapter()
        adapter.listener = this
        binding.recyclerViewProducts.adapter = adapter
    }

//    override fun onProductLongPress(id: Long?) {
//        return true
//    }

    override fun onProductClick(id: Long?) {
        Log.d("TAG", "ProductID pressed: $id")
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        viewModel.products.value?.let { adapter.setTasks(it) }
    }
}