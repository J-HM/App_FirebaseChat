package com.jhm.android.firebasechat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhm.android.firebasechat.R
import com.jhm.android.firebasechat.model.TimeAgo
import com.jhm.android.firebasechat.data.ChatData
import kotlinx.android.synthetic.main.row_chat.view.*

class ChatAdapter(private val chats: ArrayList<ChatData>, private val onclick: (Int) -> Unit) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_chat, parent, false)
        )
    }
    
    override fun getItemCount(): Int = chats.size
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.run {
            text_chat_title.text = chats[position].title
            text_chat_numberOfParticipants.text = chats[position].numberOfParticipants.toString()
            text_chat_creationTime.text = TimeAgo.getTimeAgo(chats[position].creationTime)
            
            if (chats[position].password.isNullOrBlank()) image_chat_locked.visibility = View.GONE
            else image_chat_locked.visibility = View.VISIBLE
            
            setOnClickListener {
                onclick(position)
            }
        }
        
    }
    
}
