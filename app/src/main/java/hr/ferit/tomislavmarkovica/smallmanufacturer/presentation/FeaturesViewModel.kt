package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

class FeaturesViewModel(
    private val featureRepository: FeatureRepository
) : ViewModel() {
    var features: LiveData<List<Feature>> = featureRepository.getAllFeatures()
    
    fun saveFeature(feature: Feature) {
        featureRepository.save(feature)
        Log.d("TAG", feature.toString())
    }
}