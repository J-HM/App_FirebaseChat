package com.jhm.android.firebasechat.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.jhm.android.firebasechat.adapter.MessageAdapter
import com.jhm.android.firebasechat.data.MessageData
import com.jhm.android.firebasechat.R
import kotlinx.android.synthetic.main.activity_message.*
import java.util.*
import kotlin.collections.ArrayList

class MessageActivity : AppCompatActivity() {
    
    private var messages = ArrayList<MessageData>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        messages.add(MessageData("haha", Timestamp.now(), UUID.randomUUID()))
        messages.add(MessageData("2", Timestamp.now(), UUID.randomUUID()))
        messages.add(MessageData("입장했습니다.", Timestamp.now()))
        messages.add(MessageData("응?", Timestamp.now(), UUID.randomUUID()))
        messages.add(MessageData("뚜", Timestamp.now(), UUID.randomUUID()))
        messages.add(MessageData("입장했습니다.", Timestamp.now()))
        messages.add(MessageData("dghhkdn", Timestamp.now(), UUID.randomUUID()))
        messages.add(MessageData("뭐라노?", Timestamp.now(), UUID.randomUUID()))
        
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        
        text_message_id.text = intent.getStringExtra("id")

        recycler_message_message.apply {
            this.layoutManager = LinearLayoutManager(this@MessageActivity)
            this.adapter = MessageAdapter(messages)
        }
    }
}
