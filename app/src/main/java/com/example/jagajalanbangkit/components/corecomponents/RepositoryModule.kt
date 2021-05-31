package com.example.jagajalanbangkit.components.corecomponents

import com.example.data.Repository
import com.example.domain.repository.IRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository : Repository): IRepository

}