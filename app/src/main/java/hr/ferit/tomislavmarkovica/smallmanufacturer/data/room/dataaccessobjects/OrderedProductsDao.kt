package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.OrderedProduct
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

@Dao
interface OrderedProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(orderedProduct: OrderedProduct)

    @Query("SELECT products.id, products.name, products.description, products.photo " +
            "FROM orderedProducts, products WHERE orderId = :id AND products.id = orderedProducts.productID")
    fun getAllProductsForOrder(id: Long?): LiveData<List<Product>>
}