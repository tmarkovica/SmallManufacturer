package hr.ferit.tomislavmarkovica.smallmanufacturer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import android.util.Log
import hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection.*
import org.koin.android.ext.android.getKoin

class SmallManufacturer : Application() {

    private lateinit var notificationControl: NotificationController

    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
            androidContext(this@SmallManufacturer)
            Log.d("TAG", "Koin started")
            modules(
                databaseModule,
                repositoryModule,
                viewModelModule,
                notificationController,
                sharedPrefers
            )
        }

        notificationControl = application.getKoin().get()
    }

    companion object {
        lateinit var application: Application
    }
}