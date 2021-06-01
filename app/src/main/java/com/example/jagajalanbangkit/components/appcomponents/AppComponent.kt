package com.example.jagajalanbangkit.components.appcomponents

import com.example.jagajalanbangkit.components.appcomponents.scopes.AppScope
import com.example.jagajalanbangkit.components.corecomponents.CoreComponent
import com.example.jagajalanbangkit.home.screen.HomeActivity
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules =[AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: HomeActivity)
}