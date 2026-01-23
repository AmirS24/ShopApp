package com.shabelnikd.shopapp.ui.fragments.cart

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shabelnikd.shopapp.ui.adapters.CartAdapter
import com.vacral.shopapp.databinding.FragmentCartBinding
import com.vacral.shopapp.ui.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>(
    FragmentCartBinding::inflate
) {
    override val viewModel: CartViewModel by viewModel()

    private val adapter = CartAdapter()

    override fun onBind(binding: FragmentCartBinding) {
        setupRecycler()
        observeData()
        binding.btnCheckout.setOnClickListener {
            viewModel.checkout()
        }

    }

    private fun setupRecycler() {
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter
    }


    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { items ->
                    adapter.submitList(items)
                    binding.btnCheckout.isEnabled = items.isNotEmpty()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.total.collect { sum ->
                    binding.tvTotalValue.text = "$ %.2f".format(sum)
                }
            }
        }
    }

}