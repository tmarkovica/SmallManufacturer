package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "orderDate")
    val orderDate: String,

    @ColumnInfo(name = "deliveryDate")
    val deliveryDate: String,

    @ColumnInfo(name = "contactId")
    val contactId: String
)