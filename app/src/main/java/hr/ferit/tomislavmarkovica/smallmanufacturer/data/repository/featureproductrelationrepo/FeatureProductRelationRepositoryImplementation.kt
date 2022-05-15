package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.FeatureProductRelationDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.FeatureProductRelation

class FeatureProductRelationRepositoryImplementation(
    private val featureProductRelationDao: FeatureProductRelationDao
) : FeatureProductRelationRepository {
    override fun save(featurePoductRelation: FeatureProductRelation) = featureProductRelationDao.save(featurePoductRelation)
    override fun delete(featurePoductRelation: FeatureProductRelation) = featureProductRelationDao.delete(featurePoductRelation)
    override fun getAllFeaturesForProduct(id: Long?): LiveData<List<Feature>> = featureProductRelationDao.getAllFeaturesForProduct(id)
}