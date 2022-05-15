package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

@Dao
interface FeatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(feature: Feature): Long

    @Delete
    fun delete(feature: Feature)

    @Query("SELECT * FROM features WHERE id =:id")
    fun getFeatureById(id: Long): Feature?

    @Query("SELECT * FROM features")
    fun getAllFeatures(): LiveData<List<Feature>>
}