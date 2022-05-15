package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo.FeatureProductRelationRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeatureProductRelationViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeaturesViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductsViewModel(get<ProductRepository>()) }
    viewModel { FeaturesViewModel(get<FeatureRepository>(), get<FeatureProductRelationRepository>()) }
    viewModel { FeatureProductRelationViewModel(get<FeatureProductRelationRepository>(), get<FeatureRepository>()) }
}