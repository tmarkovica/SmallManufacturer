package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedproductrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.OrderedProductsDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.OrderedProduct
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class OrderedProductsRepositoryImplementation (
    private val orderedProductsDao: OrderedProductsDao
) : OrderedProductsRepository{

    override fun save(orderedProduct: OrderedProduct)  = orderedProductsDao.save(orderedProduct)
    override fun getAllProductsForOrder(id: Long?): LiveData<List<Product>> = orderedProductsDao.getAllProductsForOrder(id)
}