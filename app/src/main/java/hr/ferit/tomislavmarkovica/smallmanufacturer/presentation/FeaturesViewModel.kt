package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo.FeatureProductRelationRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.FeatureProductRelation

class FeaturesViewModel(
    private val featureRepository: FeatureRepository,
    private val featureProductRelationRepository: FeatureProductRelationRepository
) : ViewModel() {

    private var _features = MutableLiveData<MutableList<Feature>>()

    var features: LiveData<MutableList<Feature>> = _features

    fun addFeature(feature: Feature) {
        _features.addNewItem(feature)
    }

    private fun saveFeatureAndGetFeatureId(feature: Feature): Long {
        return featureRepository.save(feature)
    }

    private fun saveFeatureToDatabaseAndRelateItToProduct(productId: Long, feature: Feature) {
        featureProductRelationRepository.save(FeatureProductRelation(productId, saveFeatureAndGetFeatureId(feature)))
    }

    fun save(productId: Long) {
        features.value?.forEach {
            saveFeatureToDatabaseAndRelateItToProduct(productId, it)
        }
    }
}

fun <T> MutableLiveData<MutableList<T>>.addNewItem(item: T) {
    val oldValue = this.value ?: mutableListOf()
    oldValue.add(item)
    this.value = oldValue
}