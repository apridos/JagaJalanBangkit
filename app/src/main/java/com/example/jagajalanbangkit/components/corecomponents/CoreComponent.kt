package com.example.jagajalanbangkit.components.corecomponents

import android.app.Application
import android.content.Context
import com.example.domain.repository.IRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun inject(app: Application)

    fun provideRepository() : IRepository
}