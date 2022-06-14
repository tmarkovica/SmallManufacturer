package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import android.app.Application
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.NotificationController
import hr.ferit.tomislavmarkovica.smallmanufacturer.PreferenceManager
import org.koin.dsl.module

val notificationController = module {
    fun provideNotificationController(application: Application, repo: OrderRepository, manager: PreferenceManager): NotificationController {
        return NotificationController.getController(application, repo, manager)
    }
    single<NotificationController> { provideNotificationController(get<Application>(), get<OrderRepository>(), get<PreferenceManager>()) }
}