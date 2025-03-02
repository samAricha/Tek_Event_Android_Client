package teka.android.tekeventandroidclient.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import teka.android.tekeventandroidclient.data.dataStore.DataStoreRepository
import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.TekEventDatabase
import teka.android.tekeventandroidclient.data.room_remote_sync.RemoteDataUpdater
import teka.android.tekeventandroidclient.domain.interfaces.SmsSender
import teka.android.tekeventandroidclient.presentation.splashScreen.SplashViewModel
import teka.android.tekeventandroidclient.repository.Repository
import teka.android.tekeventandroidclient.utils.sms_service.AppSmsSender
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TekEventAndroidModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Provides
    @Singleton
    fun provideSplashViewModel(repository: DataStoreRepository): SplashViewModel {
        return SplashViewModel(repository)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideSmsSender(appSmsSender: AppSmsSender): SmsSender {
        return appSmsSender
    }

    @Singleton
    @Provides
    fun provideRemoteDataUpdater(@ApplicationContext context: Context): RemoteDataUpdater {
        return RemoteDataUpdater(context)
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


    @Provides
    @Singleton
    fun provideRepository(database: TekEventDatabase): Repository {
        return Repository(eventVisitorDao = database.eventVisitorDao())
    }

}