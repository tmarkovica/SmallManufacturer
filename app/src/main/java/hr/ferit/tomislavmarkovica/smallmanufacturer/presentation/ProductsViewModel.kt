package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class ProductsViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    var products: LiveData<List<Product>> = productRepository.getAllProducts()

    fun delete(product: Product) {
        //productRepository.delete(product)
    }

    fun getProductById(id: Long?): Product? {
        var product: Product? = null
        id?.let { product = productRepository.getProductById(id) }
        return product
    }

    fun saveProduct(product: Product) {
        productRepository.save(product)
    }
}