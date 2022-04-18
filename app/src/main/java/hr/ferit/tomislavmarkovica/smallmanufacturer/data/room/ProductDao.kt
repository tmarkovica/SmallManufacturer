package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM products WHERE id =:id")
    fun getProductById(id: Long): Product?

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>
}