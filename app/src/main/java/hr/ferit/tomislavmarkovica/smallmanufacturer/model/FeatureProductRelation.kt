package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "featureProductRelations",
    foreignKeys = [
        // Foreign key to Product
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        ),
        // Foreign key to Feature
        ForeignKey(
            entity = Feature::class,
            parentColumns = ["id"],
            childColumns = ["featureId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["productId", "featureId"],
    // column references a foreign key but it is not part of an index fix:
    indices = [Index("featureId")]
)
data class FeatureProductRelation(

    var productId: Long = 0,

    var featureId: Long = 0

)