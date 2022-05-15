package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "orderedProducts",
    foreignKeys = [
        // Foreign key to Product
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productID"],
            onDelete = ForeignKey.CASCADE
        ),
        // Foreign key to Order
        ForeignKey(
            entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["orderID"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["productID", "orderID"],
    // column references a foreign key but it is not part of an index fix:
    indices = [Index("orderID")]
)
data class OrderedProduct (
    var productID: Long = 0,

    var orderID: Long = 0
)