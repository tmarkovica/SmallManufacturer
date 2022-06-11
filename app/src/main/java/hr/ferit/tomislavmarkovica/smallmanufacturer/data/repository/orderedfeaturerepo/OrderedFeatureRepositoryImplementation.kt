package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedfeaturerepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.OrderedFeatureDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.OrderedFeature

class OrderedFeatureRepositoryImplementation(
    private val orderedFeatureDao: OrderedFeatureDao
) : OrderedFeatureRepository {
    override fun save(orderedFeature: OrderedFeature) = orderedFeatureDao.save(orderedFeature)
    override fun getFeaturesOrderedForProduct(id: Long?): LiveData<List<Feature>> =
        orderedFeatureDao.getFeaturesOrderedForProduct(id)
}