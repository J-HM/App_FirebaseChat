package com.jhm.android.firebasechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        text_message_id.text = intent.getStringExtra("id")

        recycler_message_message.apply {
            this.layoutManager = LinearLayoutManager(this@MessageActivity)
            // this.adapter = MessageAdapter(chats)
        }
    }
}
