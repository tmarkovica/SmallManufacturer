package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import android.app.Application
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.ProductDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.productrepo.ProductRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.SmallManufacturerDatabase
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import android.util.Log
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.featurerepo.FeatureRepositoryImplementation
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.FeatureDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.FeaturesViewModel

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
    single<SmallManufacturerDatabase> { provideDatabase(get()) }
    single<ProductDao> { provideProductDao(get()) }
    single<FeatureDao> { provideFeatureDao(get()) }
}


val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImplementation(get()) }
    single<FeatureRepository> { FeatureRepositoryImplementation(get()) }
}

val viewModelModule = module {
    viewModel { ProductsViewModel(get()) }
    viewModel { FeaturesViewModel(get()) }
}

//val sharedPrefers = module {
//    single<PreferenceManager>() { PreferenceManager(get()) }
//}