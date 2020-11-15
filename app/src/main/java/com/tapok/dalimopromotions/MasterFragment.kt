package com.tapok.dalimopromotions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.tapok.dalimopromotions.databinding.MasterFragmentBinding
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
import com.tapok.dalimopromotions.model.Promotion
import com.tapok.dalimopromotions.recyclerview.ItemPromotionAdapter
import javax.inject.Inject

class MasterFragment : Fragment() {
    private lateinit var viewModel: PromotionViewModel
    private lateinit var masterFragmentBinding: MasterFragmentBinding
    private var promotionAdapter: ItemPromotionAdapter = ItemPromotionAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        masterFragmentBinding = MasterFragmentBinding.inflate(inflater, container, false)
        return masterFragmentBinding.root
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as PromotionApplication).databaseComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        initAdapterExtra()
        initSwipeRefreshLayout()
        initObservers()
    }

    override fun onDestroyView() {
        removeObservers()
        super.onDestroyView()

    }

    private fun loadDataInAdapter(data: List<Promotion>) {
        promotionAdapter.apply { if (isEmpty()) setData(data) else updateData(data) }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            viewModelFactory
        ).get(PromotionViewModel::class.java)
    }

    private fun initRecyclerView() {
        masterFragmentBinding.recyclerView.apply {
            setHasFixedSize(true)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            (layoutManager as GridLayoutManager).spanCount = 2
            adapter = promotionAdapter
        }
    }
    private fun initAdapterExtra() {
        promotionAdapter.selectedItemId = viewModel.selectedItemId
        promotionAdapter.clickListener = {
            viewModel.selectedItemId = promotionAdapter.selectedItemId
            viewModel.select(it)
        }
    }

    private fun initSwipeRefreshLayout() {
        masterFragmentBinding.swipeContainer.setOnRefreshListener {
            viewModel.updateData()
            masterFragmentBinding.swipeContainer.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.apply {
            promotion.observe(viewLifecycleOwner, { status ->
                when (status) {
                    DataState.Idle -> {
                    }
                    is DataState.Success -> loadDataInAdapter(status.dataList)
                    is DataState.Failed -> {
                        Toast.makeText(requireContext(), R.string.toast_error, Toast.LENGTH_LONG).show()
                        loadDataInAdapter(status.dataList)
                    }
                    DataState.Empty -> Toast.makeText(
                        requireContext(),
                        R.string.toast_empty,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
            selected.observe(viewLifecycleOwner, { promotion ->
                if (promotion == null) promotionAdapter.resetSelectItem()
            })
        }
    }

    private fun removeObservers() {
        viewModel.apply {
            selected.removeObservers(viewLifecycleOwner)
            promotion.removeObservers(viewLifecycleOwner)
        }
    }
}