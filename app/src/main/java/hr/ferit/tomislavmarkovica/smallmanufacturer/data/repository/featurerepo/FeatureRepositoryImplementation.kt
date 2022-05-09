package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.FeatureDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

class FeatureRepositoryImplementation (
    private val featureDao: FeatureDao
) : FeatureRepository {

    override fun save(feature: Feature) = featureDao.save(feature)

    //override fun delete(feature: Feature) = productDao.delete(feature)
    override fun getFeatureById(id: Long): Feature? = featureDao.getFeatureById(id)
    override fun getAllFeatures(): LiveData<List<Feature>> = featureDao.getAllFeatures()
}