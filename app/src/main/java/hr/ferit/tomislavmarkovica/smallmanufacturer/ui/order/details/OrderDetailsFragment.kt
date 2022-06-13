package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentDetailsOrderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ContactsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.OrderedFeatureViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.OrdersViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.featureadapter.FeatureAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsOrderBinding

    private lateinit var adapter: FeatureAdapter

    private val viewModelOrder: OrdersViewModel by viewModel()
    private val viewModelProduct: ProductsViewModel by viewModel()
    private val viewModelContact: ContactsViewModel by viewModel()
    private val viewModelOrderedFeature: OrderedFeatureViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsOrderBinding.inflate(
            inflater,
            container,
            false
        )
        bindView()
        setupRecyclerView()
        return binding.root
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

    private fun bindContact(contactId: Long) {
        val contact: Contact = viewModelContact.getContactById(contactId) ?: return
        binding.layoutItemContact.textViewFirstName.text = contact.firstName
        binding.layoutItemContact.textViewLastName.text = contact.lastName
        binding.layoutItemContact.textViewEmail.text = contact.email
        binding.layoutItemContact.textViewPhoneNumber.text = contact.phoneNumber
        binding.layoutItemContact.textViewAddress.text = contact.address
    }

    private fun bindProduct(productId: Long) {
        val product = viewModelProduct.getProductById(productId) ?: return

        binding.textViewProductName.text = product.name
        binding.textViewProductDescription.text = product.description
        binding.imageViewProductImage.setImageBitmap(product.photo)
    }

    private fun bindOrder(order: Order) {
        binding.textViewOrderDate.text = order.orderDate
        binding.textViewDeliveryDate.text = order.deliveryDate
    }

    private fun bindView() {
        setFeaturesObserver()
        val order = getOrder() ?: return
        viewModelOrderedFeature.findFeaturesForProductInOrderWithId(order.id)
        bindOrder(order)
        bindContact(order.contactId)
        bindProduct(order.productId)
    }

    private fun updateData() {
        viewModelOrderedFeature.features.value?.let { adapter.setFeatures(it) }
    }

    private fun setFeaturesObserver() {
        viewModelOrderedFeature.features.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                updateData()
            }
        }
    }

    private fun getOrderIdFromNavigationBundle(): Long {
        return arguments?.getLong("orderId") ?: 0
    }

    private fun getOrder(): Order? {
        return viewModelOrder.getOrderById(getOrderIdFromNavigationBundle())
    }
}