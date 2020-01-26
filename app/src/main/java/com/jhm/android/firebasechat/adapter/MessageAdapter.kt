package com.jhm.android.firebasechat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhm.android.firebasechat.R
import com.jhm.android.firebasechat.data.MessageData
import kotlinx.android.synthetic.main.row_message_center.view.*
import kotlinx.android.synthetic.main.row_message_left.view.*
import kotlinx.android.synthetic.main.row_message_right.view.*
import java.util.*
import kotlin.collections.ArrayList

class MessageAdapter(private val messages: ArrayList<MessageData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    companion object {
        private const val LEFT_CONTENT = 0
        private const val RIGHT_CONTENT = 1
        private const val CENTER_CONTENT = 2
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        
        return when (viewType) {
            LEFT_CONTENT -> ViewHolderLeft(inflater.inflate(R.layout.row_message_left, parent, false))
            RIGHT_CONTENT -> ViewHolderRight(inflater.inflate(R.layout.row_message_right, parent, false))
            CENTER_CONTENT -> ViewHolderCenter(inflater.inflate(R.layout.row_message_center, parent, false))
            else -> throw IllegalArgumentException()
        }
    }
    
    override fun getItemCount(): Int = messages.size
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderLeft -> {
                holder.itemView.text_message_left_content.text = messages[position].content
            }
            is ViewHolderRight -> {
                holder.rightView.text_message_right_content.text = messages[position].content
            }
            is ViewHolderCenter -> {
                holder.centerView.text_message_center_content.text = messages[position].content
            }
            else -> Log.d("jhmlog", "error")
        }
    }
    
    override fun getItemViewType(position: Int): Int {
        return when (messages[position].sender) {
            UUID.randomUUID() -> RIGHT_CONTENT
            null -> CENTER_CONTENT
            else -> LEFT_CONTENT
        }
    }
    
    class ViewHolderLeft(view: View) : RecyclerView.ViewHolder(view)
    class ViewHolderRight(val rightView: View) : RecyclerView.ViewHolder(rightView)
    class ViewHolderCenter(val centerView: View) : RecyclerView.ViewHolder(centerView)
    
}
