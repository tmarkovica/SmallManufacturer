package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.OrderedFeature

@Dao
interface OrderedFeatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(orderedFeature: OrderedFeature)

    @Query(
        "SELECT features.id, features.feature " +
                "FROM features, orders, orderedFeatures WHERE orders.id = :id AND orders.id = orderedFeatures.orderId" +
                " AND features.id = orderedFeatures.featureId AND orders.productId = orderedFeatures.productId"
    )
    fun getFeaturesOrderedForProduct(id: Long?): LiveData<List<Feature>>
}