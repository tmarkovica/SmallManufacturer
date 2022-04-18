package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

interface ProductRepository {
    fun save(product: Product)
    //fun delete(task: Product)
    fun getProductById(id: Long): Product?
    fun getAllProducts(): LiveData<List<Product>>
}