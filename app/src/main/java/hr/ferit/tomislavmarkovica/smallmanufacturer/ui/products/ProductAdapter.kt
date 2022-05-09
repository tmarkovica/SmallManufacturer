package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemProductBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class ProductAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private val products = mutableListOf<Product>()
    var listener: OnProductEventListener? = null

    fun setTasks(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        listener?.let {
            holder.itemView.setOnClickListener { listener?.onProductClick(product.id) }
            //holder.itemView.setOnLongClickListener { listener?.onProductLongPress(product.id) }
        }
    }

    override fun getItemCount(): Int = products.count()
}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(product: Product) {
        val binding = ItemProductBinding.bind(itemView)
        binding.textViewProductName.text = product.name
        binding.textViewProductDescription.text = product.description
    }
}