package teka.android.tekeventandroidclient.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.TekEventDatabase
import teka.android.tekeventandroidclient.domain.interfaces.SmsSender
import teka.android.tekeventandroidclient.utils.sms_service.AppSmsSender
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TekEventAndroidModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }


//    @Provides
//    @Singleton
//    fun provideSmsSender(@ApplicationContext context: Context): SmsSender = AppSmsSender(context)

    @Provides
    @Singleton
    fun provideSmsSender(appSmsSender: AppSmsSender): SmsSender {
        return appSmsSender
    }

    @Provides
    @Singleton
    fun provideTekEventDatabase(@ApplicationContext context: Context): TekEventDatabase {
        return TekEventDatabase.getDatabase(context)
    }

    @Provides
    fun provideEventVisitorDao(database: TekEventDatabase): EventVisitorDao {
        return database.eventVisitorDao()
    }

}