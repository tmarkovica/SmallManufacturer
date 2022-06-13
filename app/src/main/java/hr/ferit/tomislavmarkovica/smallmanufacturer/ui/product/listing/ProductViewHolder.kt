package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.listing

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemProductBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(product: Product) {
        val binding = ItemProductBinding.bind(itemView)
        binding.textViewProductName.text = product.name
        binding.textViewProductDescription.text = product.description
        binding.imageViewProductImage.setImageBitmap(product.photo)
    }

    fun highlightItem() {
        val binding = ItemProductBinding.bind(itemView)
        binding.root.setBackgroundColor(Color.GREEN)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    fun defaultItemColor() {
        val binding = ItemProductBinding.bind(itemView)
        binding.root.setBackgroundColor(binding.root.context.getColor(R.color.item_color))
    }
}