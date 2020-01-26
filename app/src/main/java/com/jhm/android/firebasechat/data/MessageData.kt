package com.jhm.android.firebasechat.data

import com.google.firebase.Timestamp

data class MessageData(
    val content: String? = null,
    val sendTime: Timestamp? = null
)
