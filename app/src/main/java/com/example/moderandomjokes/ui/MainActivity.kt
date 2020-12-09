package com.example.moderandomjokes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moderandomjokes.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val jokesViewModel: JokesViewModel by viewModel()
    private val jokeAdapter = JokesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()

        jokesViewModel.getLoadingObservable().observe(this, {
            loading(it)
        })
        jokesViewModel.getErrorObservable().observe(this, {
            showError(it)
        })

        jokesViewModel.getJokesContentObservable().observe(this, {
            jokeAdapter.setData(it.value)

        })

        jokesViewModel.fetchJokes()
    }

    private fun loading(isInProgress: Boolean) {
        pb_loading.visibility = if (isInProgress) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun initializeView() {
        val mLayoutManager = LinearLayoutManager(this)
        rv_jokes.apply {
            layoutManager = mLayoutManager
            adapter = jokeAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }
}
