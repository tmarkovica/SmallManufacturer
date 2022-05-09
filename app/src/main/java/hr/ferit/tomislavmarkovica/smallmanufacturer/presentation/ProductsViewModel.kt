package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import android.util.Log
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

    fun getTaskById(id: Long?): Product? {
        var product: Product? = null
        id?.let { product = productRepository.getProductById(id) }
        return product
    }

    fun saveProduct(product: Product) {
        productRepository.save(product)
    }
//
//    fun getAllProductsInString(): String {
//        useLiveData()
//        return "called"
//    }
//
//    private fun useLiveData() {
//        val str = StringBuilder()
//
//        val allProducts: List<Product>? = products.value
//
//        if (allProducts != null) {
//            for (p in allProducts) {
//                str.append(p.name, "\n")
//            }
//        }
//
//        Log.d("TAG", "useLiveData: " + str.toString())
//    }
}