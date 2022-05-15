package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

interface FeatureRepository {
    fun save(feature: Feature): Long
    fun getFeatureById(id: Long): Feature?
    fun getAllFeatures(): LiveData<List<Feature>>
}