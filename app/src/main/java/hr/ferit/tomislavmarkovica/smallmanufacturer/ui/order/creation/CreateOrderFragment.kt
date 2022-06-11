package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.creation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentCreateOrderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateOrderFragment: Fragment() {

//    private val viewModelOrders: OrdersViewModel = get<OrdersViewModel>()
    private val viewModelSaveOrder: SaveOrderViewModel by viewModel()

//    private lateinit var sharedViewModel: SharedViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val viewModelContacts: ContactsViewModel by viewModel()
    private val viewModelProducts: ProductsViewModel by viewModel()

    private lateinit var binding: FragmentCreateOrderBinding

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
        //sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.buttonJoinContact.setOnClickListener { showSelectContactFragment() }
        binding.buttonJoinProduct.setOnClickListener { showSelectProductFragment() }
        binding.buttonSaveOrder.setOnClickListener { createOrder() }
        bindContact()
        bindProduct()

        Log.d("TAG", "--------------------------------------------")
        Log.d("TAG", this.toString())

        return binding.root
    }

    private fun bindProduct() {
        sharedViewModel.product.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.layoutItemProduct.textViewProductName.text = it.name
                binding.layoutItemProduct.textViewProductDescription.text = it.description
                binding.layoutItemProduct.imageViewProductImage.setImageBitmap(it.photo)
            }
            else {
                binding.layoutItemProduct.textViewProductName.text = ""
                binding.layoutItemProduct.textViewProductDescription.text = ""
                binding.layoutItemProduct.imageViewProductImage.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.factory_256) as Bitmap)
            }
        }
    }

    private fun bindContact() {
        sharedViewModel.contact.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.layoutItemContact.textViewFirstName.text = it.firstName
                binding.layoutItemContact.textViewLastName.text = it.lastName
                binding.layoutItemContact.textViewEmail.text = it.email
                binding.layoutItemContact.textViewPhoneNumber.text = it.phoneNumber
                binding.layoutItemContact.textViewAddress.text = it.address
            }
            else {
                binding.layoutItemContact.textViewFirstName.text = ""
                binding.layoutItemContact.textViewLastName.text = ""
                binding.layoutItemContact.textViewEmail.text = ""
                binding.layoutItemContact.textViewPhoneNumber.text = ""
                binding.layoutItemContact.textViewAddress.text = ""
            }
        }
    }

    private fun showSelectContactFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createOrderFragment_to_selectContactFragment)
    }

    private fun showSelectProductFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createOrderFragment_to_selectProductFragment)
    }

    private fun getDateFromDatePicker(picker: DatePicker): String {
        return picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year.toString()
    }

    private fun createOrder() {
        if (sharedViewModel.areProductAndContactSelected() == false) return

        val contactId = sharedViewModel.contact.value!!.id
        val productId = sharedViewModel.product.value!!.id

//        val dateFormat = SimpleDateFormat("dd MM yyyy", Locale.GERMANY)
        val orderDate = getDateFromDatePicker(binding.datePickerOrderDate)
        val deliveryDate = getDateFromDatePicker(binding.datePickerDeliveryDate)

        Log.d("TAG", ":: " + orderDate)
        Log.d("TAG", ":: " + deliveryDate)

        Log.d("TAG", orderDate)

        val order = Order(id = 0, orderDate = orderDate, deliveryDate = deliveryDate, contactId = contactId, productId = productId)

        Log.d("TAG", "ORDER: " + order.toString())

        val savingReuslt = viewModelSaveOrder.save(order)

        Log.d("TAG", "savingReuslt = " + savingReuslt.toString())


        Toast.makeText(context, "Order created", Toast.LENGTH_SHORT).show()



        // TODO pri spremanju order-a treba izbrisati order i product u sharedViewModelu
        sharedViewModel.setSelectedProduct(null)
        sharedViewModel.setSelectedContact(null)

        findNavController().navigateUp()
    }
}