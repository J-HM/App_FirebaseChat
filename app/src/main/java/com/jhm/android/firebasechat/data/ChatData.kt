package com.jhm.android.firebasechat.data

import com.google.firebase.Timestamp


data class ChatData(
    val title: String? = null,
    val password: String? = null,
    val creationTime: Timestamp? = null,
    val numberOfParticipants: Number? = null,
    val id: String? = null
)
