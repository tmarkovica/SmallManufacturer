package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order

interface OrderRepository {

    fun save(order: Order): Long

    fun delete(order: Order)

    fun getOrderById(id: Long): Order?

    fun getAllOrders(): LiveData<List<Order>>
}