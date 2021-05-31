package com.example.jagajalanbangkit.components.appcomponents

import com.example.domain.usecase.Interactor
import com.example.domain.usecase.UseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideUseCase(interactor : Interactor) : UseCase
}