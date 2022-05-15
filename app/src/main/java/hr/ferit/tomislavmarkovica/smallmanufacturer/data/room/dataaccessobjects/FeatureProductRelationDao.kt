package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.FeatureProductRelation

@Dao
interface FeatureProductRelationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(featureProductRelation: FeatureProductRelation)

    @Delete
    fun delete(featureProductRelation: FeatureProductRelation)

//    @Query("SELECT * FROM featureProductRelations WHERE productID = :id")
    @Query("SELECT features.id, features.feature FROM featureProductRelations, features WHERE productID = :id AND features.id = featureProductRelations.featureID")
    fun getAllFeaturesForProduct(id: Long?): LiveData<List<Feature>>

//    @Query("SELECT features.id, features.feature FROM featureProductRelations, features WHERE productID = :id AND features.id = featureProductRelations.featureID")
//    fun getAllFeaturesForProductId(id: Long?): LiveData<List<Feature>>
}