package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.FeatureProductRelationDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.FeatureProductRelation

class FeatureProductRelationRepositoryImplementation(
    private val featureProductRelationDao: FeatureProductRelationDao
) : FeatureProductRelationRepository {
    override fun save(featuresProductRelation: FeatureProductRelation) =
        featureProductRelationDao.save(featuresProductRelation)

    override fun delete(featuresProductRelation: FeatureProductRelation) =
        featureProductRelationDao.delete(featuresProductRelation)

    override fun getAllFeaturesForProduct(id: Long?): LiveData<List<Feature>> =
        featureProductRelationDao.getAllFeaturesForProduct(id)
}