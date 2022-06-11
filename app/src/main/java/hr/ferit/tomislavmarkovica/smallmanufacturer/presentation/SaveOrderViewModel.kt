package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order

class SaveOrderViewModel(
    private val orderRepository: OrderRepository
): ViewModel() {

    fun save(order: Order): Long {
        return orderRepository.save(order)
    }
}