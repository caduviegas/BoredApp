package com.innaval.boredapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.innaval.boredapp.R
import com.innaval.boredapp.databinding.ActivityMainBinding
import com.innaval.boredapp.util.Resource
import com.innaval.boredapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeToObserver()
        binding.btnClick.setOnClickListener {

            viewModel.getAnActivity()
        }
    }

    private fun subscribeToObserver() {
        viewModel.activityResult.observe(this, { activity ->
            when (activity) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false

                    binding.textViewActivityValue.text = activity.data?.activity
                    binding.textViewTypeValue.text = activity.data?.type
                    binding.textViewParticipantsValue.text = activity.data?.participants.toString()
                    binding.textViewPriceValue.text = activity.data?.price.toString()
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Resource.Failure -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, activity.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}