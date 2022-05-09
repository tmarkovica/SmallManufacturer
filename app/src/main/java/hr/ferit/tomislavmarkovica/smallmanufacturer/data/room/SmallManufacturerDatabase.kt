package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

@Database(
    entities = [Product::class, Feature::class],
    version = 2,
    exportSchema = false
)
abstract class SmallManufacturerDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getFeatureDao(): FeatureDao

    companion object {

        private const val databaseName = "smallmanufacturerDb"

        @Volatile
        private var INSTANCE: SmallManufacturerDatabase? = null

        fun getDatabase(context: Context): SmallManufacturerDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)

                    if (INSTANCE != null)
                        Log.d("TAG", "Database built successfully.")
                    else
                        Log.d("TAG", "Database not built.")
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): SmallManufacturerDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SmallManufacturerDatabase::class.java,
                databaseName
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration() // destroys database when upgrading version
                .build()
        }
    }
}