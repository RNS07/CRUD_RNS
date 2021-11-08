package com.rns.accomium.features.product.productAdd.util

import android.content.Context
import androidx.room.Room
import com.rns.accomium.data.product.data.ProductDao
import com.rns.accomium.data.AppDatabase
import com.rns.accomium.features.product.productAdd.domain.repository.AddProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class AddProductModule {

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context):
//            AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java,"appDatabase").build()

    @Provides
    @Singleton
    fun provideDatadao(productDatabase: AppDatabase): ProductDao =
        productDatabase.ProductDao()

    @Provides
    @Singleton
    fun provideAddProductRepository(  ProductDao: ProductDao): AddProductRepository =
        AddProductRepository(ProductDao)
}