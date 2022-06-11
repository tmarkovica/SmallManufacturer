package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemContactBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(contact: Contact) {
        val binding = ItemContactBinding.bind(itemView)
        binding.textViewFirstName.text = contact.firstName
        binding.textViewLastName.text = contact.lastName
        binding.textViewEmail.text = contact.email
        binding.textViewPhoneNumber.text = contact.phoneNumber
        binding.textViewAddress.text = contact.address
    }

    fun highlightItem() {
        val binding = ItemContactBinding.bind(itemView)
        binding.root.setBackgroundColor(Color.GREEN)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    fun defaultItemColor() {
        val binding = ItemContactBinding.bind(itemView)
        binding.root.setBackgroundColor(binding.root.context.getColor(R.color.item_color))
//        binding.root.setBackgroundColor(R.color.item_color)
    }
}