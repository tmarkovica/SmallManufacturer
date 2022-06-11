package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.selection

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.listing.ProductEventListener
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.listing.ProductViewHolder

class ProductSelectionAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private val products = mutableListOf<Product>()
    var listener: ProductEventListener? = null

    private var selectedItemViewHolder: ProductViewHolder? = null
    private var selectedProduct: Product? = null

    fun setProducts(products: List<Product>) {
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

        if (listener == null) return

        listener?.let {
            holder.itemView.setOnClickListener {
                highlightProduct(holder)
                selectedProduct = product

                listener?.onProductClick(product.id)
            }
            holder.itemView.setOnLongClickListener {
                listener?.onProductLongPress(product.id)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount(): Int = products.count()

    @SuppressLint("NewApi")
    private fun highlightProduct(holder: ProductViewHolder) {
        selectedItemViewHolder?.defaultItemColor()
        holder.highlightItem()
        selectedItemViewHolder = holder
    }

    fun getSelectedProduct(): Product? {
        return selectedProduct
    }
}