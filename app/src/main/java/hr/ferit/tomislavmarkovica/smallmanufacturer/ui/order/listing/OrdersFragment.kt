package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.listing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentOrdersBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.OrdersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrdersFragment : Fragment() {

    private val viewModel: OrdersViewModel by viewModel()

    private lateinit var binding: FragmentOrdersBinding

    private lateinit var adapter: OrderAdapter

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
        binding.floatingActionButtonAddOrder.setOnClickListener { showCreateOrderFragment() }
        bindView()
        setupRecyclerView()
        return binding.root
    }

    private fun showCreateOrderFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_holderFragment_to_createOrderFragment)
    }

    private fun updateData() {

        // TODO ubaciti order i product u adapter
        viewModel.orders.value?.let {
            adapter.setOrders(it)
            adapter.setContacts(viewModel.findConnectedContacts(it))
            adapter.setProducts(viewModel.findConnectedProducts(it))
        }
        adapter.notifyData()
    }

    private fun bindView() {
        viewModel.orders.observe(viewLifecycleOwner) {
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
        adapter = OrderAdapter()
//        adapter.listener = this
        binding.recyclerView.adapter = adapter
    }

}