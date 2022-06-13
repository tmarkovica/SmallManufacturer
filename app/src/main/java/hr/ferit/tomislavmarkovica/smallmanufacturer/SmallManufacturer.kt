package hr.ferit.tomislavmarkovica.smallmanufacturer

import android.app.Application
import hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection.databaseModule
import hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection.repositoryModule
import hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import android.util.Log
import hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection.notificationController
import org.koin.android.ext.android.getKoin

class SmallManufacturer : Application() {

    private lateinit var notificationContr: NotificationController

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
                notificationController
            )
        }

        notificationContr = application.getKoin().get<NotificationController>()
        notificationContr.allowNotifications()
    }

    companion object {
        lateinit var application: Application
    }
}