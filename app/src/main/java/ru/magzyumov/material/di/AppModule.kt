package ru.magzyumov.material.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.magzyumov.material.common.Constants
import ru.magzyumov.material.common.helpers.PreferenceHelper
import ru.magzyumov.material.common.helpers.SnackBarHelper
import ru.magzyumov.material.common.helpers.ThemeHelper
import ru.magzyumov.material.di.services.BaseServices
import javax.inject.Singleton


//Provide all the app level dependency here
@Module
class AppModule {


    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideSnackBarHelper(): SnackBarHelper {
        return SnackBarHelper()
    }

    @Provides
    fun providePreferenceHelper(sharedPreferences: SharedPreferences): PreferenceHelper {
        return PreferenceHelper(sharedPreferences)
    }

    @Provides
    fun provideThemeHelper(preferenceHelper: PreferenceHelper): ThemeHelper {
        return ThemeHelper(preferenceHelper)
    }

    @Provides
    fun provideBaseServices(
        preferenceHelper: PreferenceHelper,
        snackBarHelper: SnackBarHelper,
        themeHelper: ThemeHelper
    ): BaseServices {
        return BaseServices(preferenceHelper, snackBarHelper, themeHelper)
    }
}