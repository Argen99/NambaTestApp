package com.example.nambatestapp.domain.use_cases

import com.example.nambatestapp.domain.repository.PicturesRepository

class GetPicturesUseCase(
    private val repository: PicturesRepository
) {
    operator fun invoke(search: String) = repository.getPictures(search)
}