package com.jhm.android.firebasechat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhm.android.firebasechat.R
import com.jhm.android.firebasechat.data.MessageData

const val LEFT_CONTENT = 0
const val RIGHT_CONTENT = 1
const val CENTER_CONTENT = 2

class MessageAdapter(private val messages: ArrayList<MessageData>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
    
        return when (viewType) {
            CENTER_CONTENT -> ViewHolder(inflater.inflate(R.layout.row_message_center, parent, false))
            LEFT_CONTENT -> ViewHolder(inflater.inflate(R.layout.row_message_left, parent, false))
            RIGHT_CONTENT -> ViewHolder(inflater.inflate(R.layout.row_message_right, parent, false))
            else -> ViewHolder(inflater.inflate(R.layout.row_message_center, parent, false))
        }
    }
    
    override fun getItemCount(): Int = messages.size
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    
    }
    
}
