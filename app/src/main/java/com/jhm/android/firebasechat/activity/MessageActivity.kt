package com.jhm.android.firebasechat.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhm.android.firebasechat.adapter.MessageAdapter
import com.jhm.android.firebasechat.data.MessageData
import com.jhm.android.firebasechat.R
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {

    private var messages = ArrayList<MessageData>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        
        text_message_id.text = intent.getStringExtra("id")

        recycler_message_message.apply {
            this.layoutManager = LinearLayoutManager(this@MessageActivity)
            this.adapter = MessageAdapter(messages)
        }
    }
}
