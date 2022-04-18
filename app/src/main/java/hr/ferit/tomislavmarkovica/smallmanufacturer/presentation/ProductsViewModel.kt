package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class ProductsViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    var products = productRepository.getAllProducts()

    fun delete(product: Product) {
        //productRepository.delete(product)
    }

    fun getTaskById(id: Long?): Product? {
        var product: Product? = null
        id?.let { product = productRepository.getProductById(id) }
        return product
    }

    fun saveSomething() {
        val product = Product(0, "proizvod1", "opis proizvoda 1")
        productRepository.save(product)

        Log.d("TAG", products.value.toString())
        Log.d("TAG", products.value?.size.toString())
    }

    fun getAllProductsInString(): String {
        useLiveData()
        return "called"
    }

    private fun useLiveData() {
        val str = StringBuilder()

        val allProducts: List<Product>? = products.value

        if (allProducts != null) {
            for (p in allProducts) {
                str.append(p.name, "\n")
            }
        }

        Log.d("TAG", "useLiveData: " + str.toString())
    }
}