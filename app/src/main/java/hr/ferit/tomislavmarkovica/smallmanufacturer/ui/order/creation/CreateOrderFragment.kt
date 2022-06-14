package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.creation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentCreateOrderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeatureProductRelationViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.OrderedFeatureViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.OrdersViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.SharedCotactAndProductViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.creation.FeatureEventListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter

class CreateOrderFragment : Fragment(), FeatureEventListener {

    private val viewModelSaveOrder: OrdersViewModel by viewModel()

    private val sharedContactAndProductViewModel: SharedCotactAndProductViewModel by activityViewModels()

    private val viewModelFeatureProductRelation: FeatureProductRelationViewModel by viewModel()

    private val viewModelOrderedFeature: OrderedFeatureViewModel by viewModel()

    private lateinit var binding: FragmentCreateOrderBinding

    private lateinit var adapter: FeatureSelectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateOrderBinding.inflate(
            inflater,
            container,
            false
        )
        binding.buttonJoinContact.setOnClickListener { showSelectContactFragment() }
        binding.buttonJoinProduct.setOnClickListener { showSelectProductFragment() }
        binding.buttonSaveOrder.setOnClickListener { createOrder() }
        bindContact()
        bindProduct()
        bindFeatures()
        setupRecyclerView()
        return binding.root
    }

    private fun bindProduct() {
        sharedContactAndProductViewModel.product.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.layoutItemProduct.textViewProductName.text = it.name
                binding.layoutItemProduct.textViewProductDescription.text = it.description
                binding.layoutItemProduct.imageViewProductImage.setImageBitmap(it.photo)

                viewModelFeatureProductRelation.setProductId(it.id)
            } else {
                binding.layoutItemProduct.textViewProductName.text = ""
                binding.layoutItemProduct.textViewProductDescription.text = ""
                binding.layoutItemProduct.imageViewProductImage.setImageBitmap(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.factory_256
                    ) as Bitmap
                )
            }
        }
    }

    private fun bindContact() {
        sharedContactAndProductViewModel.contact.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.layoutItemContact.textViewFirstName.text = it.firstName
                binding.layoutItemContact.textViewLastName.text = it.lastName
                binding.layoutItemContact.textViewEmail.text = it.email
                binding.layoutItemContact.textViewPhoneNumber.text = it.phoneNumber
                binding.layoutItemContact.textViewAddress.text = it.address
            } else {
                binding.layoutItemContact.textViewFirstName.text = ""
                binding.layoutItemContact.textViewLastName.text = ""
                binding.layoutItemContact.textViewEmail.text = ""
                binding.layoutItemContact.textViewPhoneNumber.text = ""
                binding.layoutItemContact.textViewAddress.text = ""
            }
        }
    }

    private fun bindFeatures() {
        viewModelFeatureProductRelation.features.observe(viewLifecycleOwner) {
            updateFeaturesData()
        }
    }

    private fun updateFeaturesData() {
        viewModelFeatureProductRelation.features.value?.let { adapter.setFeatures(it) }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFeatures.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = FeatureSelectAdapter()
        adapter.listener = this
        binding.recyclerViewFeatures.adapter = adapter
    }

    private fun showSelectContactFragment() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_createOrderFragment_to_selectContactFragment)
    }

    private fun showSelectProductFragment() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_createOrderFragment_to_selectProductFragment)
    }

    private fun getDateFromDatePicker(picker: DatePicker): String {
        val current =
            picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year.toString()
        val formatter = DateTimeFormatter.ofPattern("dd/M/yyyy")
        return current.format(formatter)
    }

    private fun saveCheckedFeatures(orderId: Long, productId: Long) {
        val featureIds = adapter.getCheckedFeaturesId()
        viewModelOrderedFeature.saveCheckedFeatures(productId, featureIds, orderId)
    }

    private fun areProductAndContactSelected(): Boolean {
        return sharedContactAndProductViewModel.isContactSelected() && sharedContactAndProductViewModel.isProductSelected()
    }

    private fun createOrder() {
        if (!areProductAndContactSelected()) {
            Toast.makeText(context, "Contact or product are not added", Toast.LENGTH_SHORT).show()
            return
        }

        val contactId = sharedContactAndProductViewModel.contact.value!!.id
        val productId = sharedContactAndProductViewModel.product.value!!.id

        val orderDate = getDateFromDatePicker(binding.datePickerOrderDate)
        val deliveryDate = getDateFromDatePicker(binding.datePickerDeliveryDate)


        val order = Order(
            id = 0,
            orderDate = orderDate,
            deliveryDate = deliveryDate,
            contactId = contactId,
            productId = productId
        )
        val orderId = viewModelSaveOrder.save(order)
        saveCheckedFeatures(orderId, productId)

        Toast.makeText(context, "Order created", Toast.LENGTH_SHORT).show()

        sharedContactAndProductViewModel.setSelectedProduct(null)
        sharedContactAndProductViewModel.setSelectedContact(null)
        findNavController().navigateUp()
    }

    override fun onFeatureClick(feature: Feature) {

    }
}