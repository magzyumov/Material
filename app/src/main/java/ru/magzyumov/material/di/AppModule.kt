package ru.magzyumov.material.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.magzyumov.material.common.Constants
import ru.magzyumov.material.common.helpers.PreferenceHelper
import ru.magzyumov.material.common.helpers.SnackBarHelper
import ru.magzyumov.material.common.helpers.StorageHelper
import ru.magzyumov.material.common.helpers.ThemeHelper
import ru.magzyumov.material.data.database.ImageDao
import ru.magzyumov.material.data.database.ImageDatabase
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
    @Singleton
    fun provideStorageHelper(application: Application): StorageHelper {
        return StorageHelper(application)
    }

    @Provides
    fun providePreferenceHelper(sharedPreferences: SharedPreferences): PreferenceHelper {
        return PreferenceHelper(sharedPreferences)
    }

    @Provides
    fun provideThemeHelper(preferenceHelper: PreferenceHelper): ThemeHelper {
        return ThemeHelper(preferenceHelper)
    }

    @Singleton
    @Provides
    fun providesImageDao(db: ImageDatabase): ImageDao {
        return db.imageDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): ImageDatabase {
        return Room.databaseBuilder(application, ImageDatabase::class.java, "image_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}