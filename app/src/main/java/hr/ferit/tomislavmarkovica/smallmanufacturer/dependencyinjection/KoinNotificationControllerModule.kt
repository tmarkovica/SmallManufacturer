package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import android.app.Application
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.NotificationController
import org.koin.dsl.module

val notificationController = module {
    fun provideNotificationController(application: Application, repo: OrderRepository): NotificationController {
        return NotificationController.getController(application, repo)
    }
    single<NotificationController> { provideNotificationController(get<Application>(), get<OrderRepository>()) }
}