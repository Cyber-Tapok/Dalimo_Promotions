package com.tapok.dalimopromotions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
import com.tapok.dalimopromotions.model.Promotion
import com.tapok.dalimopromotions.recyclerview.ItemPromotionAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var list: List<Promotion>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemPromotionAdapter
    private lateinit var viewModel: PromotionViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as PromotionApplication).databaseComponent.inject(this)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemPromotionAdapter()
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory).get(PromotionViewModel::class.java)
        viewModel.updateData()
        viewModel.issues.observe(this, { adapter.setData(it) })
//                    adapter.setData(list)
//        try {
//
//            val orgs: Call<PromotionResponse> =
//                RetrofitClient.getService().getPromotionFromApi()
//
//            orgs.enqueue(object : Callback<PromotionResponse> {
//                override fun onFailure(call: Call<PromotionResponse>, t: Throwable) {
//                    Log.e("Tets",t.toString())
//                }
//
//                override fun onResponse(
//                    @NonNull call: Call<PromotionResponse>,
//                    @NonNull response: Response<PromotionResponse>
//                ) {
//                    list = response.body()?.data ?: emptyList()
//                    adapter.setData(list)
//                }
//
//            }         )
//
//        } catch (e: Exception) {
//            Log.e("Tets","Exc: $e")
//        }
    }
}