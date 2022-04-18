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

val databaseModule = module {
    Log.d("TAG", "databaseModule")
    fun provideDatabase(application: Application): SmallManufacturerDatabase {
        Log.d("TAG", "provideDatabase")
        return SmallManufacturerDatabase.getDatabase(application)
    }
    fun provideProductDao(database: SmallManufacturerDatabase): ProductDao {
        return database.getProductDao()
    }
    single<SmallManufacturerDatabase> { provideDatabase(get()) }
    single<ProductDao> { provideProductDao(get()) }
}


val repositoryModule = module {
    Log.d("TAG", "repositoryModule")
    single<ProductRepository> { ProductRepositoryImplementation(get()) }
}

val viewModelModule = module {
    Log.d("TAG", "viewModelModule")
    viewModel { ProductsViewModel(get()) }
}

//val sharedPrefers = module {
//    single<PreferenceManager>() { PreferenceManager(get()) }
//}