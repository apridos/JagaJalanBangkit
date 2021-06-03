package com.example.jagajalanbangkit.components.appcomponents

import com.example.jagajalanbangkit.RiwayatActivity
import com.example.jagajalanbangkit.components.appcomponents.scopes.AppScope
import com.example.jagajalanbangkit.components.corecomponents.CoreComponent
import com.example.jagajalanbangkit.daftar.screen.DaftarActivity
import com.example.jagajalanbangkit.home.screen.HomeActivity
import com.example.jagajalanbangkit.lapor.screen.LaporActivity
import com.example.jagajalanbangkit.login.screen.LoginActivity
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

    fun inject(activity: LaporActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: DaftarActivity)

    fun inject(activity: RiwayatActivity)
}