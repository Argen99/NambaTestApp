package com.example.nambatestapp.app.presentation.fragments

import android.transition.TransitionInflater
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nambatestapp.R
import com.example.nambatestapp.app.core.base.BaseFragment
import com.example.nambatestapp.app.core.extensions.loadImage
import com.example.nambatestapp.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding, ViewModel>(R.layout.fragment_details) {

    override val binding by viewBinding(FragmentDetailsBinding::bind)
    override val viewModel by viewModel<PicturesViewModel>()

    private val args by navArgs<DetailsFragmentArgs>()

    override fun initialize() {
        binding.detailImage.loadImage(args.image)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }
}