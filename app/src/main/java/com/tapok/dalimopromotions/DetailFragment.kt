package com.tapok.dalimopromotions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
        detailFragmentBinding.toolbar?.setNavigationOnClickListener { requireActivity().onBackPressed() }
        viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            viewModelFactory
        ).get(PromotionViewModel::class.java)
        viewModel.selected.observe(viewLifecycleOwner, { promotion ->
            promotion?.let {
                detailFragmentBinding.promotion = promotion
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.selected.value = null
                requireActivity().supportFragmentManager.beginTransaction().remove(this@DetailFragment).commit()
                this.remove()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(callback)
    }

    override fun onDestroyView() {
        viewModel.selected.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }

}