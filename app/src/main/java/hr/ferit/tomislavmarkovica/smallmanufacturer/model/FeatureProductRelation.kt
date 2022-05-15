package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "featureProductRelations",
    foreignKeys = [
        // Foreign key to Product
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productID"],
            onDelete = ForeignKey.CASCADE
        ),
        // Foreign key to Feature
        ForeignKey(
            entity = Feature::class,
            parentColumns = ["id"],
            childColumns = ["featureID"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["productID", "featureID"]
)
data class FeatureProductRelation(

    var productID: Long = 0,

    var featureID: Long = 0

)