package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.contactrepo.ContactRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.contactrepo.ContactRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo.FeatureProductRelationRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelationrepo.FeatureProductRelationRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedproductrepo.OrderedProductsRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderedproductrepo.OrderedProductsRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepositoryImplementation
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImplementation(get()) }
    single<FeatureRepository> { FeatureRepositoryImplementation(get()) }
    single<FeatureProductRelationRepository> { FeatureProductRelationRepositoryImplementation(get()) }

    single<OrderRepository> { OrderRepositoryImplementation(get()) }
    single<ContactRepository> { ContactRepositoryImplementation(get()) }
    single<OrderedProductsRepository> { OrderedProductsRepositoryImplementation(get()) }
}