package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.FeatureProductRelation

interface FeatureProductRelationRepository {
    fun save(featuresProductRelation: FeatureProductRelation)
    fun delete(featuresProductRelation: FeatureProductRelation)
    fun getAllFeaturesForProduct(id: Long?): LiveData<List<Feature>>
}