package teka.android.tekeventandroidclient.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.TekEventDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TekEventAndroidModule {

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