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
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
import com.tapok.dalimopromotions.recyclerview.ItemPromotionAdapter
import javax.inject.Inject


class MasterFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemPromotionAdapter
    private lateinit var viewModel: PromotionViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.master_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as PromotionApplication).databaseComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        bindRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory).get(PromotionViewModel::class.java)
        viewModel.updateData()
        viewModel.issues.observe(viewLifecycleOwner, { adapter.setData(it) })
    }

    private fun bindRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        adapter = ItemPromotionAdapter()
        recyclerView.adapter = adapter
    }
}