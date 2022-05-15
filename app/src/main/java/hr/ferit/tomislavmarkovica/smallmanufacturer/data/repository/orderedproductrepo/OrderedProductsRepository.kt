package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedproductrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.OrderedProduct
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

interface OrderedProductsRepository {
    fun save(orderedProduct: OrderedProduct)
    fun getAllProductsForOrder(id: Long?): LiveData<List<Product>>
}