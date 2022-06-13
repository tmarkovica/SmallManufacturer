package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.OrderDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order

class OrderRepositoryImplementation(
    private val orderDao: OrderDao
) : OrderRepository {

    override fun save(order: Order): Long = orderDao.save(order)

    override fun delete(order: Order) = orderDao.delete(order)

    override fun getOrderById(id: Long): Order? = orderDao.getOrderById(id)
    override fun getAllOrders(): LiveData<List<Order>> = orderDao.getAllOrders()
}