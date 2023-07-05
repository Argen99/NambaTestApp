package com.example.nambatestapp.app.di

import com.example.nambatestapp.app.presentation.fragments.PicturesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::PicturesViewModel)
}