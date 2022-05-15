package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import android.app.Application
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelation.FeatureProductRelationRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featureproductrelation.FeatureProductRelationRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.FeatureDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.FeatureProductRelationDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.ProductDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.SmallManufacturerDatabase
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeatureProductRelationViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeaturesViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): SmallManufacturerDatabase {
        return SmallManufacturerDatabase.getDatabase(application)
    }
    fun provideProductDao(database: SmallManufacturerDatabase): ProductDao {
        return database.getProductDao()
    }
    fun provideFeatureDao(database: SmallManufacturerDatabase): FeatureDao {
        return database.getFeatureDao()
    }
    fun provideFeatureProductRelationDao(database: SmallManufacturerDatabase) : FeatureProductRelationDao {
        return database.getFeatureProductRelationDao()
    }
    single<SmallManufacturerDatabase> { provideDatabase(get()) }
    single<ProductDao> { provideProductDao(get()) }
    single<FeatureDao> { provideFeatureDao(get()) }
    single<FeatureProductRelationDao> { provideFeatureProductRelationDao(get()) }
}


val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImplementation(get()) }
    single<FeatureRepository> { FeatureRepositoryImplementation(get()) }
    single<FeatureProductRelationRepository> { FeatureProductRelationRepositoryImplementation(get()) }
}

val viewModelModule = module {
    viewModel { ProductsViewModel(get<ProductRepository>()) }
    viewModel { FeaturesViewModel(get<FeatureRepository>(), get<FeatureProductRelationRepository>()) }
    viewModel { FeatureProductRelationViewModel(get<FeatureProductRelationRepository>(), get<FeatureRepository>()) }
}

//val sharedPrefers = module {
//    single<PreferenceManager>() { PreferenceManager(get()) }
//}