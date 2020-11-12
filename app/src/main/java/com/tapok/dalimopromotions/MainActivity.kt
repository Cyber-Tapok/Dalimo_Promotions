package com.tapok.dalimopromotions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.tapok.dalimopromotions.model.ResponceApi
import com.tapok.dalimopromotions.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {

            val orgs: Call<ResponceApi> =
                RetrofitClient.getService().issueCall()

            orgs.enqueue(object : Callback<ResponceApi> {
                override fun onFailure(call: Call<ResponceApi>, t: Throwable) {
                    Log.e("Tets",t.toString())
                }

                override fun onResponse(
                    @NonNull call: Call<ResponceApi>,
                    @NonNull response: Response<ResponceApi>
                ) {
                    Log.e("TEST", response.toString())
                }

            }         )

        } catch (e: Exception) {
            Log.e("Tets","Exc: $e")
        }
    }
}