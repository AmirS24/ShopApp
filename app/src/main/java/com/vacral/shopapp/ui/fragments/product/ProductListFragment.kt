package com.vacral.shopapp.ui.fragments.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vacral.shopapp.databinding.FragmentDetailBinding
import com.vacral.shopapp.databinding.FragmentDetailBinding.inflate
import com.vacral.shopapp.databinding.FragmentListBinding
import com.vacral.shopapp.ui.adapters.ProductAdapter
import com.vacral.shopapp.ui.base.BaseFragment
import com.vacral.shopapp.ui.fragments.product.detail.DetailViewModel
import com.vacral.shopapp.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : BaseFragment<FragmentListBinding, ListViewModel>(
    FragmentListBinding::inflate
) {

    override val viewModel: ListViewModel by viewModel()

    private val adapter = ProductAdapter { product ->
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment()
                .setProductDetailId(product.id)
        findNavController().navigate(action)
    }

    override fun onBind(binding: FragmentListBinding) {
        setupRecycler()
        observeState()
    }

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun observeState() {
        viewModel.state.collectUiState(
            onLoading = {
                binding.progressBar.isVisible = true
                binding.recyclerView.isVisible = false
            },
            onSuccess = { data ->
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = true
                adapter.submitList(data)
            },
            onError = { message ->
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = false
            }
        )
    }
}