package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

// višak
@Entity(
    tableName = "orderedProducts",
    foreignKeys = [
        // Foreign key to Product
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        ),
        // Foreign key to Order
        ForeignKey(
            entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["orderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["productId", "orderId"],
    // column references a foreign key but it is not part of an index fix:
    indices = [Index("orderId")]
)
data class OrderedProduct (
    var productId: Long = 0,

    var orderId: Long = 0
)