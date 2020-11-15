package com.tapok.dalimopromotions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tapok.dalimopromotions.databinding.MasterFragmentBinding
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
import com.tapok.dalimopromotions.recyclerview.ItemPromotionAdapter
import javax.inject.Inject


class MasterFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemPromotionAdapter
    private lateinit var viewModel: PromotionViewModel
    private lateinit var masterFragmentBinding: MasterFragmentBinding

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
        bindRecyclerView()
        viewModel = ViewModelProvider(
            activity!!.viewModelStore,
            viewModelFactory
        ).get(PromotionViewModel::class.java)
        viewModel.updateData()
        viewModel.issues.observe(viewLifecycleOwner, { adapter.setData(it) })
        masterFragmentBinding.swipeContainer.setOnRefreshListener {
            viewModel.updateData()
            masterFragmentBinding.swipeContainer.isRefreshing = false
        }
    }

    private fun bindRecyclerView() {
        recyclerView = masterFragmentBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        adapter = ItemPromotionAdapter()
//        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter
    }
}