package hr.ferit.tomislavmarkovica.smallmanufacturer.product.creation

import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

interface FeatureEventListener {
    fun onFeatureClick(feature: Feature)
}