package com.example.moderandomjokes.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moderandomjokes.R
import com.example.moderandomjokes.model.Value
import kotlinx.android.synthetic.main.item_joke.view.*

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.ArtistViewHolder>() {

    private val data = arrayListOf<Value>()

    fun setData(items: List<Value>?) {
        data.clear()
        if (items != null) {
            data.addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArtistViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_joke,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size
    override fun onBindViewHolder(viewHolder: ArtistViewHolder, position: Int) {
        viewHolder.bind(data[position])

    }

    inner class ArtistViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(response: Value) {
            view.tv_joke.text =  Html.fromHtml(response.joke).toString()
        }
    }
}