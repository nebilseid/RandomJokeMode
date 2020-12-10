package com.example.moderandomjokes.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moderandomjokes.R
import com.example.moderandomjokes.util.setVisibility
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val jokesViewModel: JokesViewModel by viewModel()
    private val jokeAdapter = JokesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()

        jokesViewModel.loadingLiveData.observe(this, { loading(it) })
        jokesViewModel.errorLiveData.observe(this, { showError(it) })
        jokesViewModel.jokesContentLiveData.observe(this, { jokeAdapter.setData(it) })

        jokesViewModel.fetchJokes()
    }

    private fun initializeView() {
        val mLayoutManager = LinearLayoutManager(this)
        rv_jokes.apply {
            layoutManager = mLayoutManager
            adapter = jokeAdapter
        }
    }

    private fun loading(isInProgress: Boolean) {
        pb_loading.setVisibility(isInProgress)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
