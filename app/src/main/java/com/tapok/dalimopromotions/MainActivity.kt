package com.tapok.dalimopromotions

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.tapok.dalimopromotions.databinding.ActivityMainBinding
import com.tapok.dalimopromotions.di.PromotionApplication
import com.tapok.dalimopromotions.di.ViewModelFactory
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
        viewModel.selected.observe(this, { promotion ->
            promotion?.let {
                mainBinding.detailFragment.apply {
                    isVisible = true
                    fragmentManager.beginTransaction()
                        .replace(mainBinding.detailFragment.id, DetailFragment()).commit()
                }
            } ?: run { mainBinding.detailFragment.isVisible = false }
        })
        fragmentManager.beginTransaction().replace(mainBinding.masterFragment.id, MasterFragment())
            .commit()
    }

    override fun onDestroy() {
        viewModel.selected.removeObservers(this)
        super.onDestroy()
    }
}