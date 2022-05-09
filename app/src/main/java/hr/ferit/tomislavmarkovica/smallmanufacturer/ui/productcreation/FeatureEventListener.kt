package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.productcreation

import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

interface FeatureEventListener {
    fun onFeatureClick(feature: Feature)
}