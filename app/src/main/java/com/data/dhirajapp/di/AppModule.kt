package com.data.dhirajapp.di

import com.data.dhirajapp.Test
import com.data.dhirajapp.TestDao
import com.data.dhirajapp.TestRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(
            schema = setOf(
                Test::class
            )
        ).schemaVersion(1).build()
    }


    @Provides
    @Singleton
    fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.open(realmConfiguration)
    }

    @Provides
    @Singleton
    fun provideTestDao(realm: Realm): TestDao = TestDao(realm)

    @Provides
    @Singleton
    fun provideTestRepo(testDao: TestDao): TestRepo = TestRepo(testDao)
}
