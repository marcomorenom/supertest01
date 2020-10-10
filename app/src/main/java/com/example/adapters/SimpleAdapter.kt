package com.example.adapters

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class SimpleAdapter(private val context: Context, private var items: ArrayList<String>?): RecyclerView.Adapter<ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setDefaultContent(holder)
        items?.let { _items->
            if(_items.isNotEmpty()){
                _items[position].let { item ->
                    holder.title.text = item
                    holder.subtitle.text = item
                }
            }
        }
    }

    private fun setDefaultContent(holder: ViewHolder) {
        holder.title.text = context.getText(R.string.default_short_text_lorem_ipsum)
        holder.subtitle.text = context.getText(R.string.default_long_text_lorem_ipsum)
    }

    fun updateData(items : ArrayList<String>?) {
        if(items!=null){
            this.items = items
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_home, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        items?.let {
//            return it.count()
            return 5
        }
        return 0
    }}

 class ViewHolder(holder: View) : RecyclerView.ViewHolder(holder) {
     var title: TextView = holder.findViewById(R.id.title)
     var subtitle: TextView = holder.findViewById(R.id.subtitle)
}