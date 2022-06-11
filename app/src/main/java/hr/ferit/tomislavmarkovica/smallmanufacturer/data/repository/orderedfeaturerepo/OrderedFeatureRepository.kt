package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedfeaturerepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.OrderedFeature

interface OrderedFeatureRepository {
    fun save(orderedFeature: OrderedFeature)
    fun getFeaturesOrderedForProduct(id: Long?): LiveData<List<Feature>>
}