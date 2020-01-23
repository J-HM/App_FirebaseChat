package com.jhm.android.firebasechat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        holder.view.text_chat_title.text = chats[position].title

        holder.view.setOnClickListener {
            onclick(position)
        }
    }
}
