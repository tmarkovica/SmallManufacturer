package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.ProductDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class ProductRepositoryImplementation(
    private val productDao: ProductDao
) : ProductRepository {

    override fun save(product: Product): Long = productDao.save(product)

    //override fun delete(product: Product) = productDao.delete(product)
    override fun getProductById(id: Long): Product? = productDao.getProductById(id)
    override fun getAllProducts(): LiveData<List<Product>> = productDao.getAllProducts()
}