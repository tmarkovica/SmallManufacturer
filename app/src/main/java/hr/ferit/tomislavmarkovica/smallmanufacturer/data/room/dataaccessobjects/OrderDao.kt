package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(order: Order): Long

    @Delete
    fun delete(order: Order)

    @Query("SELECT * FROM orders WHERE id = :id")
    fun getOrderById(id: Long): Order?

    @Query("SELECT * FROM orders")
    fun getAllOrders(): LiveData<List<Order>>
}