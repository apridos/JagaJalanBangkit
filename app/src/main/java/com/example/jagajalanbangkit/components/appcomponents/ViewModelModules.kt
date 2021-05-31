package com.example.jagajalanbangkit.components.appcomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jagajalanbangkit.viewmodels.UserViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindHomeViewModel(viewModel: UserViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}