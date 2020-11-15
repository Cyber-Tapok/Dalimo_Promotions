package com.tapok.dalimopromotions

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
        initViewModel()
        initObservers()
        savedInstanceState ?: viewModel.updateData()
    }

    override fun onBackPressed() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT || viewModel.selected.value == null) {
            super.onBackPressed()
        }
        viewModel.selected.value = null
    }

    override fun onDestroy() {
        removeObservers()
        super.onDestroy()
    }

    private fun navigateToDetailsFragment() {
        val navController = findNavController(R.id.nav_host_fragment)
        if (navController.currentDestination?.id == R.id.masterFragment) {
            navController.navigate(R.id.action_masterFragment_to_detailFragment)
        }
    }

    private fun popDetailsFragment() {
        val navController = findNavController(R.id.nav_host_fragment)
        if (navController.currentDestination?.id == R.id.detailFragment) {
            onBackPressed()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PromotionViewModel::class.java)
    }

    private fun initObservers() {
        viewModel.selected.observe(this, { promotion ->
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (promotion != null) {
                    navigateToDetailsFragment()
                } else {
                    popDetailsFragment()
                }
            }
        })
    }

    private fun removeObservers() {
        viewModel.selected.removeObservers(this)
    }
}