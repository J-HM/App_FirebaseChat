package com.jhm.android.firebasechat.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhm.android.firebasechat.R
import com.jhm.android.firebasechat.adapter.ChatAdapter
import com.jhm.android.firebasechat.data.ChatData
import com.jhm.android.firebasechat.model.ChatModel
import com.jhm.android.firebasechat.model.PassWordModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var chats = ArrayList<ChatData>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val passWordModel = PassWordModel(this)
        recycler_main_chat.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = ChatAdapter(chats) { position ->
    
                passWordModel.confirmPassword(chats[position].password) { isValid ->
                    when (isValid) {
                        true -> startMessageActivity(position)
                        false -> Toast.makeText(this@MainActivity, "비밀번호 ", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    
        ChatModel(chats, recycler_main_chat).linkChatsWithRecycler()
    }
    
    
    private fun startMessageActivity(position: Int) {
        Intent(this@MainActivity, MessageActivity::class.java).let {
            it.putExtra("id", chats[position].id)
            startActivity(it)
        }
    }
    
}
