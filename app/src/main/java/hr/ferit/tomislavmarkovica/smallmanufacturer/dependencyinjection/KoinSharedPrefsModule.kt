package hr.ferit.tomislavmarkovica.smallmanufacturer.dependencyinjection

import android.content.Context
import hr.ferit.tomislavmarkovica.smallmanufacturer.PreferenceManager
import org.koin.dsl.module

val sharedPrefers = module {
    single<PreferenceManager>() { PreferenceManager(get<Context>()) }
}



