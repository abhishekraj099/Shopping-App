package com.example.shoppingapp.domain.di

import com.example.shoppingapp.data.repo.RepositoryImpl
import com.example.shoppingapp.domain.repo.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): Repository {
        return RepositoryImpl(firebaseAuth, firebaseFirestore)
    }

}