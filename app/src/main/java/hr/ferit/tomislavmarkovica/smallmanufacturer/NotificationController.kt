package hr.ferit.tomislavmarkovica.smallmanufacturer

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.orderrepo.OrderRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Order
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.activities.MainActivity
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NotificationController(
    private val orderRepository: OrderRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    val orders = orderRepository.getAllOrders()
    private var oldOrders = mutableListOf<Order>()

    private fun observeOrders() {
        viewModelScope.launch {
            orderRepository.getAllOrders().asFlow().collect {
                if (preferenceManager.retrieveNotificationsAllowance())
                    pushNotificationForEachDeliveryThatIsSetForToday(it)
            }
        }
    }

    init {
        observeOrders()
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        return pendingIntent
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // nije trebalo stvarati kanale prije Android Oreo
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            NotificationManagerCompat.from(context).createNotificationChannel(channel)
        }
    }

    private fun createNotification(order: Order): Notification {
        createNotificationChannel()
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Order ${order.id}")
            .setContentText("Is set for today: ${order.deliveryDate}")
            .setSmallIcon(R.drawable.shopping_cart_256)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(createPendingIntent())
            .build()
    }

    private fun pushNotification(notification: Notification) {
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, notification) // NOTIFICATION_ID
        notificationId++
    }

    private fun getCurrentDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/M/yyyy")
        return current.format(formatter)
    }

    private fun filteredOrdersThatDidNotYetPushNotification(orders: List<Order>): List<Order> {
        val tempOrders = orders as MutableList
        tempOrders.removeAll(oldOrders.toSet())
        oldOrders.clear()
        oldOrders.addAll(orders)
        return tempOrders
    }

    private fun pushNotificationForEachDeliveryThatIsSetForToday(orders: List<Order>) {
        val todayDate = getCurrentDate()
        for (order in filteredOrdersThatDidNotYetPushNotification(orders)) {
            if (todayDate == order.deliveryDate) {
                pushNotification(createNotification(order))
            }
        }
    }

    companion object {
        const val CHANNEL_ID = "channelId"
        const val CHANNEL_NAME = "channelName"
        private var notificationId = 0

        private lateinit var context: Context

        @Volatile
        private var INSTANCE: NotificationController? = null

        fun getController(
            context: Context,
            orderRepository: OrderRepository,
            preferenceManager: PreferenceManager
        ): NotificationController {
            Companion.context = context
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = NotificationController(orderRepository, preferenceManager)
                }
            }
            return INSTANCE!!
        }
    }
}