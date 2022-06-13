package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedfeaturerepo.OrderedFeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.OrderedFeature
import kotlinx.coroutines.launch

class OrderedFeatureViewModel(
    private val orderedFeatureRepository: OrderedFeatureRepository
) : ViewModel() {

    private var _features = MutableLiveData<List<Feature>>(null)
    var features: LiveData<List<Feature>> = _features

    private fun save(orderedFeature: OrderedFeature) {
        orderedFeatureRepository.save(orderedFeature = orderedFeature)
    }

    fun saveCheckedFeatures(productId: Long, featureIds: List<Long>, orderId: Long) {
        for (id: Long in featureIds) {
            save(OrderedFeature(productId, id, orderId))
        }
    }

    private fun postFeatures(featuresToPost: List<Feature>) {
        _features.value = featuresToPost
    }

    fun findFeaturesForProductInOrderWithId(orderId: Long) {
        viewModelScope.launch {
            orderedFeatureRepository.getFeaturesOrderedForProduct(orderId).asFlow().collect {
                postFeatures(it)
            }
        }
    }
}