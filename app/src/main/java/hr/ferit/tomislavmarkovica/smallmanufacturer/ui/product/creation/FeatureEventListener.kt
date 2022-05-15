package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.creation

import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

interface FeatureEventListener {
    fun onFeatureClick(feature: Feature)
}