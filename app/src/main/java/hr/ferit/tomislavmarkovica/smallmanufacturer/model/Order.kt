package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "orders",
    foreignKeys = [
        // Foreign key to Product
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        ),
        // Foreign key to Contact
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["id"],
            childColumns = ["contactId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Order(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "orderDate")
    val orderDate: String,

    @ColumnInfo(name = "deliveryDate")
    val deliveryDate: String,

    @ColumnInfo(name = "contactId")
    val contactId: Long,

    @ColumnInfo(name = "productId")
    val productId: Long
)