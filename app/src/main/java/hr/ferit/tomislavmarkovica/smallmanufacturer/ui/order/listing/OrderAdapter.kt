package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.details.OrderEventListener

class OrderAdapter : RecyclerView.Adapter<OrderViewHolder>() {

    private val orders = mutableListOf<Order>()
    private val products = mutableListOf<Product>()
    private val contacts = mutableListOf<Contact>()

    var listener: OrderEventListener? = null

    fun setOrders(orders: List<Order>) {
        this.orders.clear()
        this.orders.addAll(orders)
    }

    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
    }

    fun setContacts(contacts: List<Contact>) {
        this.contacts.clear()
        this.contacts.addAll(contacts)
    }

    fun notifyData() {
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bindOrder(order)
        holder.bindProduct(products[position])
        holder.bindContact(contacts[position])

        listener?.let {
            holder.itemView.setOnLongClickListener {
                listener?.onOrderLongPress(order.id)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount(): Int = orders.count()
}