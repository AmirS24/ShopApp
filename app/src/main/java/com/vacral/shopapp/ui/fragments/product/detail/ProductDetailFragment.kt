package com.vacral.shopapp.ui.fragments.product.detail

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil3.load
import coil3.request.crossfade
import com.vacral.shopapp.databinding.FragmentDetailBinding
import com.vacral.shopapp.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ProductDetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {


    val args: ProductDetailFragmentArgs by navArgs()

     override val viewModel: DetailViewModel by viewModel()

    override fun onBind(binding: FragmentDetailBinding) {
        if(args.productDetailId != -1){ viewModel.loadProduct(args.productDetailId) }

        binding.tvBack.setOnClickListener { findNavController().popBackStack() }

        viewModel.state.collectUiState (
            onSuccess = { product ->
                with(binding){
                    progressBar.isVisible = false
                    tvTitle.text = product.title
                    tvDesc.text = product.description
                    tvPrice.text = "${product.price} $"
                    ivProduct.load(product.image){
                        crossfade(true)
                    }
                }
            }
        )
    }
}