package com.tapok.dalimopromotions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
import com.tapok.dalimopromotions.model.Promotion
import com.tapok.dalimopromotions.recyclerview.ItemPromotionAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PromotionViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        (applicationContext as PromotionApplication).databaseComponent.inject(this)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(PromotionViewModel::class.java)
        var fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.master_fragment, MasterFragment()).commit()

    }
}