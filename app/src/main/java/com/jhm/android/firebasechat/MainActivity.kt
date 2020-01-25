package com.jhm.android.firebasechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    private var chats = ArrayList<ChatData>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        recycler_main_chat.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = ChatAdapter(chats) { position ->
                Intent(this@MainActivity, MessageActivity::class.java).let {
                    it.putExtra("id", chats[position].id)
                    startActivity(it)
                }
            }
        }
        
        getChat(
            onSuccess = { updateRecycler(it) },
            onFailure = { handleException(it) }
        )
    }
    
    private fun getChat(onSuccess: (QuerySnapshot) -> Unit, onFailure: (FirebaseFirestoreException.Code) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        
        db.collection("CHAT")
            .orderBy("creationTime")
            .addSnapshotListener { snapshot, exception ->
                exception?.let {
                    onFailure(it.code)
                    return@addSnapshotListener
                }
                snapshot?.let {
                    onSuccess(it)
                    return@addSnapshotListener
                }
            }
    }
    
    private fun handleException(exceptionCode: FirebaseFirestoreException.Code) {
        Log.w("jhmlog", "GET POST FAILURE: $exceptionCode")
    }
    
    private fun updateRecycler(snapshot: QuerySnapshot) {
        Log.d("jhmlog", "GET POST SUCCESS: ${snapshot.size()}")
        for (document in snapshot.documentChanges) {
            document.run {
                val updatedChat = extractChatDataFrom(this)
                when (type) {
                    DocumentChange.Type.ADDED -> addChat(updatedChat, newIndex)
                    DocumentChange.Type.MODIFIED -> {
                        if (oldIndex != newIndex) {        // When index of the modified data has changed
                            removeChat(oldIndex)           // Remove the data was in the original index,
                            addChat(updatedChat, newIndex) // Add data to the new index
                        } else modifyChat(updatedChat, newIndex)
                    }
                    DocumentChange.Type.REMOVED -> removeChat(oldIndex)
                }
            }
        }
    }
    
    private fun extractChatDataFrom(document: DocumentChange): ChatData {
        val title: String? = document.document.data["title"] as String?
        val password: String? = document.document.data["password"] as String?
        val creationTime: Timestamp? = document.document.data["creationTime"] as Timestamp?
        val numberOfParticipants: Number? = document.document.data["numberOfParticipants"] as Number?
        val id: String = document.document.id
        
        return ChatData(title, password, creationTime, numberOfParticipants, id)
    }
    
    private fun addChat(updatedChat: ChatData, IndexToBeAdd: Int) {
        chats.add(IndexToBeAdd, updatedChat)
        recycler_main_chat.adapter?.notifyItemInserted(IndexToBeAdd)
    }
    
    private fun modifyChat(updatedChat: ChatData, IndexToBeModify: Int) {
        chats[IndexToBeModify] = updatedChat
        recycler_main_chat.adapter?.notifyItemChanged(IndexToBeModify)
    }
    
    private fun removeChat(IndexToBeRemove: Int) {
        chats.removeAt(IndexToBeRemove)
        recycler_main_chat.adapter?.run {
            notifyItemRemoved(IndexToBeRemove)
            notifyItemRangeChanged(IndexToBeRemove, chats.size - IndexToBeRemove)
        }
    }
    
}
