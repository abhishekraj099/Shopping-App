package com.example.shoppingapp.domain.di

import com.example.shoppingapp.data.repo.RepoImpl
import com.example.shoppingapp.domain.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule{

    @Provides
    fun provideRepo(firebaseAuth: FirebaseAuth): Repo{
        return RepoImpl(firebaseAuth)
    }
}