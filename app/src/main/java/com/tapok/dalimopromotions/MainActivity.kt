package com.tapok.dalimopromotions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tapok.dalimopromotions.model.Promotion
import com.tapok.dalimopromotions.model.PromotionResponce
import com.tapok.dalimopromotions.recyclerview.ItemPromotionAdapter
import com.tapok.dalimopromotions.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var list: List<Promotion>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemPromotionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemPromotionAdapter()
        recyclerView.adapter = adapter

        try {

            val orgs: Call<PromotionResponce> =
                RetrofitClient.getService().issueCall()

            orgs.enqueue(object : Callback<PromotionResponce> {
                override fun onFailure(call: Call<PromotionResponce>, t: Throwable) {
                    Log.e("Tets",t.toString())
                }

                override fun onResponse(
                    @NonNull call: Call<PromotionResponce>,
                    @NonNull response: Response<PromotionResponce>
                ) {
                    list = response.body()?.data ?: emptyList()
                    adapter.setData(list)
                }

            }         )

        } catch (e: Exception) {
            Log.e("Tets","Exc: $e")
        }
    }
}