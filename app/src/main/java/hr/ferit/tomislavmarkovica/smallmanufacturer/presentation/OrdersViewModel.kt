package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.contactrepo.ContactRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class OrdersViewModel(
    private val orderRepository: OrderRepository,
    private val contactRepository: ContactRepository,
    private val productRepository: ProductRepository
): ViewModel() {

    var orders: LiveData<List<Order>> = orderRepository.getAllOrders()

    fun getOrderById(id: Long): Order? {
        return orderRepository.getOrderById(id)
    }

    fun findConnectedContacts(orders: List<Order>): List<Contact> {
        val tempList = mutableListOf<Contact>()
        for(o in orders)
            tempList.add(contactRepository.getContactById(o.contactId)!!)
        return tempList
    }

    fun findConnectedProducts(orders: List<Order>): List<Product> {
        val tempList = mutableListOf<Product>()
        for(o in orders)
            tempList.add(productRepository.getProductById(o.productId)!!)
        return tempList
    }
}