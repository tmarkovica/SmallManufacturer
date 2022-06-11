package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.listing

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemOrderBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class OrderViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindOrder(order: Order) {
        val binding = ItemOrderBinding.bind(itemView)
        binding.textViewOrderDate.text = order.orderDate
        binding.textViewDeliveryDate.text = order.deliveryDate
    }

    fun bindProduct(product: Product) {
        val binding = ItemOrderBinding.bind(itemView)
        binding.layoutItemProduct.textViewProductName.text = product.name
        binding.layoutItemProduct.textViewProductDescription.text = product.description
        binding.layoutItemProduct.imageViewProductImage.setImageBitmap(product.photo)
    }

    fun bindContact(contact: Contact) {
        val binding = ItemOrderBinding.bind(itemView)
        binding.layoutItemContact.textViewFirstName.text = contact.firstName
        binding.layoutItemContact.textViewLastName.text = contact.lastName
        binding.layoutItemContact.textViewEmail.text = contact.email
        binding.layoutItemContact.textViewPhoneNumber.text = contact.phoneNumber
        binding.layoutItemContact.textViewAddress.text = contact.address
    }
}