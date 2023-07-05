package com.example.nambatestapp.app.presentation.fragments

import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nambatestapp.R
import com.example.nambatestapp.app.core.base.BaseFragment
import com.example.nambatestapp.app.presentation.adapter.PicturesPagingAdapter
import com.example.nambatestapp.databinding.FragmentImagesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PicturesFragment :
    BaseFragment<FragmentImagesBinding, PicturesViewModel>(R.layout.fragment_images) {

    override val binding by viewBinding(FragmentImagesBinding::bind)
    override val viewModel by viewModel<PicturesViewModel>()

    private val pagingAdapter: PicturesPagingAdapter by lazy {
        PicturesPagingAdapter(
            requireContext(),
            this::onItemClick
        )
    }

    override fun initialize() {
        super.initialize()
        binding.recycler.apply {
            adapter = pagingAdapter
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        pagingAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun setupListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            pagingAdapter.refresh()
        }

        pagingAdapter.addLoadStateListener { state ->
            if (state.refresh is LoadState.NotLoading)
                binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.etSearch.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.charactersFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun onItemClick(image: String?, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(
            imageView to getString(R.string.detail_image_transition))
        findNavController().navigate(
            PicturesFragmentDirections.actionImagesFragmentToDetailsFragment(image!!),
            extras,
        )
    }
}