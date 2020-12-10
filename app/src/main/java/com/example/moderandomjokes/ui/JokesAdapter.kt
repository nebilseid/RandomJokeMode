package com.example.moderandomjokes.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moderandomjokes.R
import com.example.moderandomjokes.model.Value
import com.example.moderandomjokes.util.inflate
import kotlinx.android.synthetic.main.item_joke.view.*

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    private val data = arrayListOf<Value>()

    fun setData(items: List<Value>?) {
        data.clear()
        if (items != null) {
            data.addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        JokesViewHolder(parent.inflate(R.layout.item_joke))
    
    
    override fun getItemCount() = data.size
    override fun onBindViewHolder(viewHolder: JokesViewHolder, position: Int) {
        viewHolder.bind(data[position])

    }

    inner class JokesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Value) {
            view.tv_joke.text = Html.fromHtml(item.joke, Html.FROM_HTML_MODE_LEGACY).toString()
            view.tv_joke_categories.text =
                view.context.getString(R.string.categories, item.categoriesFlattened)
        }
    }
}
