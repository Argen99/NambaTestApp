package com.example.nambatestapp.domain.di

import com.example.nambatestapp.domain.use_cases.GetPicturesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetPicturesUseCase)
}