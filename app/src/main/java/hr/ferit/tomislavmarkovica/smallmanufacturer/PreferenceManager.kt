package hr.ferit.tomislavmarkovica.smallmanufacturer

import android.content.Context
import android.widget.Toast

class PreferenceManager(
    private val context: Context
) {
    companion object {
        const val PREFS_FILE = "MyPreferences"
        const val ALLOW_NOTIFICATIONS_TAG = "allow"
    }

    fun allowNotifications(allow: Boolean) {
        val sharedPreferences = context.applicationContext.getSharedPreferences(
            PREFS_FILE, Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putBoolean(ALLOW_NOTIFICATIONS_TAG, allow)
        editor.apply()
        Toast.makeText(context, "Saving to sharedPrefs", Toast.LENGTH_SHORT).show()
    }

    fun retrieveNotificationsAllowance(): Boolean {
        val sharedPreferences = context.applicationContext.getSharedPreferences(
            PREFS_FILE, Context.MODE_PRIVATE
        )
        return sharedPreferences.getBoolean(ALLOW_NOTIFICATIONS_TAG, false)
    }
}