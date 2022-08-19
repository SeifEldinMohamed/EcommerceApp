package com.seif.ecommerceapp.di
import android.content.Context
import androidx.room.Room
import com.seif.ecommerceapp.data.local.OrdersDatabase
import com.seif.ecommerceapp.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton // identifies a type that the injector only instantiates once. Not inherited.
    @Provides
    fun provideDatabase(
        @ApplicationContext context:Context
    ) = Room.databaseBuilder(
        context,
        OrdersDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDao(database: OrdersDatabase) = database.orderDao()
}

// If you don’t want to provide migrations and you specifically want
// your database to be cleared when you upgrade the version,
// call fallbackToDestructiveMigration in the database builder