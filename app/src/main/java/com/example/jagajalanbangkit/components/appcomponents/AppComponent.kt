package com.example.jagajalanbangkit.components.appcomponents

import com.example.jagajalanbangkit.components.appcomponents.scopes.AppScope
import com.example.jagajalanbangkit.components.corecomponents.CoreComponent
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules =[AppModule::class, ViewModelModule::class]
)
class AppComponent {
}