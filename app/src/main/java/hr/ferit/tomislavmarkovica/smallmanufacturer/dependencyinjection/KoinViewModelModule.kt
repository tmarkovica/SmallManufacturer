package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.contactrepo.ContactRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo.FeatureProductRelationRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedfeaturerepo.OrderedFeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductsViewModel(get<ProductRepository>()) }
    viewModel {
        FeaturesViewModel(
            get<FeatureRepository>(),
            get<FeatureProductRelationRepository>()
        )
    }
    viewModel {
        FeatureProductRelationViewModel(
            get<FeatureProductRelationRepository>(),
            get<FeatureRepository>()
        )
    }
    viewModel { ContactsViewModel(get<ContactRepository>()) }
    viewModel {
        OrdersViewModel(
            get<OrderRepository>(),
            get<ContactRepository>(),
            get<ProductRepository>()
        )
    }
    viewModel { SaveOrderViewModel(get<OrderRepository>()) }
    viewModel { OrderedFeatureViewModel(get<OrderedFeatureRepository>()) }
//    viewModel { OrderNotificationViewModel(get<NotificationController>(), get<OrderRepository>()) }
}