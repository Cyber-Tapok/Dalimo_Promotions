package com.tapok.dalimopromotions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tapok.dalimopromotions.databinding.DetailFragmentBinding
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
import javax.inject.Inject

class DetailFragment : Fragment() {
    private lateinit var detailFragmentBinding: DetailFragmentBinding
    private lateinit var viewModel: PromotionViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailFragmentBinding = DetailFragmentBinding.inflate(inflater, container, false)
        return detailFragmentBinding.root
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as PromotionApplication).databaseComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        initViewModel()
        initObservers()
    }

    override fun onDestroyView() {
        removeObservers()
        super.onDestroyView()
    }

    private fun initToolBar() {
        detailFragmentBinding.toolbar?.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            viewModelFactory
        ).get(PromotionViewModel::class.java)
    }

    private fun initObservers() {
        viewModel.selected.observe(viewLifecycleOwner, { promotion ->
            promotion?.let {
                detailFragmentBinding.promotion = promotion
            }
        })
    }

    private fun removeObservers() {
        viewModel.selected.removeObservers(viewLifecycleOwner)
    }

}