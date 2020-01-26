package com.jhm.android.firebasechat.data

import com.google.firebase.Timestamp
import java.util.*

data class MessageData(
    val content: String? = null,
    val sendTime: Timestamp? = null,
    val sender: UUID? = null
)
