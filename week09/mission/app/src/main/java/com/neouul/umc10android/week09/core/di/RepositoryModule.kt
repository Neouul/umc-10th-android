package com.neouul.umc10android.week09.core.di

import com.neouul.umc10android.week09.data.repository.ProductRepositoryImpl
import com.neouul.umc10android.week09.data.repository.UserRepositoryImpl
import com.neouul.umc10android.week09.data.repository.WishRepositoryImpl
import com.neouul.umc10android.week09.domain.repository.ProductRepository
import com.neouul.umc10android.week09.domain.repository.UserRepository
import com.neouul.umc10android.week09.domain.repository.WishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindWishRepository(
        wishRepositoryImpl: WishRepositoryImpl
    ): WishRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
