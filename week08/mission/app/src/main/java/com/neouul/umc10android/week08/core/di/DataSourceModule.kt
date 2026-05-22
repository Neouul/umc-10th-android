package com.neouul.umc10android.week08.core.di

import com.neouul.umc10android.week08.data.data_source.ProductDataSource
import com.neouul.umc10android.week08.data.data_source.ProductDataSourceImpl
import com.neouul.umc10android.week08.data.data_source.UserDataSource
import com.neouul.umc10android.week08.data.data_source.remote.RemoteUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindProductDataSource(
        productDataSourceImpl: ProductDataSourceImpl
    ): ProductDataSource

    @Binds
    @Singleton
    abstract fun bindUserDataSource(
        remoteUserDataSourceImpl: RemoteUserDataSourceImpl
    ): UserDataSource
}
