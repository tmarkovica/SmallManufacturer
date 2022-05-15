package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelation.FeatureProductRelationRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.FeatureProductRelation
import kotlinx.coroutines.launch

class FeatureProductRelationViewModel (
    private val featureProductRelationRepository: FeatureProductRelationRepository,
    private val featureRepository: FeatureRepository
) : ViewModel() {

    private var productId: Long = 0

    private val _features : MutableLiveData<MutableList<Feature>> = MutableLiveData(mutableListOf()) //Feature(0,"default")
    var features: LiveData<MutableList<Feature>> = _features

    private fun postFeatures(featuresToPost: List<Feature>) {
        _features.value?.clear()
        _features.postValue(featuresToPost.toMutableList())
    }

    private fun getFeatures() {
        viewModelScope.launch {
            featureProductRelationRepository.getAllFeaturesForProduct(productId).asFlow().collect {
                postFeatures(it)
            }
        }
    }

    fun setProductId(id: Long) {
        productId = id
        getFeatures()
    }

    fun saveFeature(feature: Feature) {
        val featureID = featureRepository.save(feature)
        featureProductRelationRepository.save(FeatureProductRelation(productId, featureID))
    }
}