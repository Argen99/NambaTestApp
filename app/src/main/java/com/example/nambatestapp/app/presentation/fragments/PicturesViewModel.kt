@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.example.nambatestapp.app.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nambatestapp.domain.model.PicturesModel
import com.example.nambatestapp.domain.use_cases.GetPicturesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class PicturesViewModel(
    private val getPicturesUseCase: GetPicturesUseCase
): ViewModel() {

    val charactersFlow: Flow<PagingData<PicturesModel>>
    private val searchBy = MutableLiveData("")

    init {
        charactersFlow = searchBy.asFlow()
            .debounce(500)
            .flatMapLatest {
            getPicturesUseCase(it)
        }.cachedIn(viewModelScope)
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}