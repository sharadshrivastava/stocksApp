package com.test.app.ui.stocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.app.R
import com.test.app.data.network.Resource.Status.*
import com.test.app.databinding.FragmentStocksBinding
import com.test.app.ui.common.ItemClickListener
import com.test.app.ui.common.showErrorBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StocksFragment : Fragment(), ItemClickListener {

    private val stocksViewModel: StocksViewModel by viewModels()
    private lateinit var binding: FragmentStocksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentStocksBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@StocksFragment
            vm = this@StocksFragment.stocksViewModel
            clickListener = this@StocksFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDivider()
        observeStocks()
        observeViewState()
    }

    private fun setupDivider() {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) }
        binding.stocksList.addItemDecoration(itemDecorator)
    }

    private fun observeStocks() {
        stocksViewModel.stocks.observe(viewLifecycleOwner) {
            stocksViewModel.manageViewStates(it)
        }
    }

    private fun observeViewState() {
        stocksViewModel.viewState.observe(viewLifecycleOwner) {
            binding.isLoading = it.loading
            binding.isEmpty = it.empty
            if (it.error) {
                showErrorBar(it.errorMsg)
            }
        }
    }

    override fun onItemClick(item: Any?) {
        //handle list clicks.Added for future.
    }
}