package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import android.app.Application
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.SmallManufacturerDatabase
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.*
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


    fun provideOrderDao(database: SmallManufacturerDatabase): OrderDao {
        return database.getOrderDao()
    }
    fun provideContactDao(database: SmallManufacturerDatabase): ContactDao {
        return database.getContactDao()
    }
    fun provideOrderedProductsDao(database: SmallManufacturerDatabase): OrderedProductsDao {
        return database.getOrderedProductsDao()
    }
    single<OrderDao> { provideOrderDao(get()) }
    single<ContactDao> { provideContactDao(get()) }
    single<OrderedProductsDao> { provideOrderedProductsDao(get()) }


    fun provideOrderedFeatureDao(database: SmallManufacturerDatabase): OrderedFeatureDao {
        return database.getOrderedFeatureDao()
    }
    single<OrderedFeatureDao> { provideOrderedFeatureDao(get()) }
}