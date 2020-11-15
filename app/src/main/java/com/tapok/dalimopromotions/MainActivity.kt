package com.tapok.dalimopromotions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tapok.dalimopromotions.databinding.ActivityMainBinding
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
import com.tapok.dalimopromotions.model.Promotion
import com.tapok.dalimopromotions.recyclerview.ItemPromotionAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PromotionViewModel
    private lateinit var mainBinding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        (applicationContext as PromotionApplication).databaseComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PromotionViewModel::class.java)
        var fragmentManager = supportFragmentManager
        viewModel.selected.observe(this, {promotion ->
            promotion?.let {
                mainBinding.detailFragment.let {
                    fragmentManager.beginTransaction()
                        .replace(mainBinding.detailFragment.id, DetailFragment()).commit()
                }
            }
        })
        fragmentManager.beginTransaction().replace(mainBinding.masterFragment.id, MasterFragment())
            .commit()
    }

    override fun onDestroy() {
        viewModel.selected.removeObservers(this)
        super.onDestroy()
    }
}