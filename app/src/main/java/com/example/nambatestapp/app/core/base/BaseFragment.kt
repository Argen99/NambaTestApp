package com.example.nambatestapp.app.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding, VM : ViewModel>(
    @LayoutRes private val layoutRes: Int
) : Fragment(layoutRes) {

    protected abstract val binding: Binding
    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        setupObservers()
    }

    protected open fun initialize() {
        postponeEnterTransition()
        view?.doOnPreDraw { startPostponedEnterTransition() }
    }

    protected open fun setupListeners() {}

    protected open fun setupObservers() {}
}