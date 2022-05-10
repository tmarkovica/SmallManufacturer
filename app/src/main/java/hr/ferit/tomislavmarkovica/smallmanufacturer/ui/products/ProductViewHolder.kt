package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemProductBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(product: Product) {
        val binding = ItemProductBinding.bind(itemView)
        binding.textViewProductName.text = product.name
        binding.textViewProductDescription.text = product.description
    }
}