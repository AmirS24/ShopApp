package com.vacral.shopapp.ui.fragments.product

import com.vacral.shopapp.R
import com.vacral.shopapp.databinding.FragmentListBinding
import com.vacral.shopapp.ui.adapters.ProductAdapter
import com.vacral.shopapp.ui.base.BaseFragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : BaseFragment<FragmentListBinding, ListViewModel>(
    FragmentListBinding::inflate
) {
    override val viewModel: ListViewModel by viewModel()

    override fun onBind(binding: FragmentListBinding) {
        setupRecycler()

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu , menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_cart -> {
                        findNavController().navigate(R.id.action_productListFragment_to_cartFragment)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        with(binding) {
            viewModel.state.collectUiState(
                onLoading = {
                    progressBar.isVisible = true
                    recyclerView.isVisible = false
                },
                onError = {
                    progressBar.isVisible = false
                    recyclerView.isVisible = false
                },
                onSuccess = { data ->
                    progressBar.isVisible = false
                    recyclerView.isVisible = true
                    adapter.submitList(data)
                }
            )
        }
    }

    private val adapter = ProductAdapter(
        onClick = { product ->
            val action =
                ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment()
                    .setProductDetailId(product.id)
            findNavController().navigate(action)
        },
        onBuyClick = { product ->
            viewModel.addToCart(product)
            Toast.makeText(context, "Добавлено!", Toast.LENGTH_SHORT).show()
        }
    )

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}