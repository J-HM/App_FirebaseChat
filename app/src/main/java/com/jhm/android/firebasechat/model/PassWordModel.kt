package com.jhm.android.firebasechat.model

import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.jhm.android.firebasechat.activity.MainActivity

class PassWordModel(private val mainActivity: MainActivity) {

    fun confirmPassword(passWord: String?, isValid: (Boolean) -> Unit) {
        if (passWord.isNullOrBlank()) {
            isValid(true)
        } else {
            getPasswordFromInput {
                if (it == passWord) isValid(true)
                else isValid(false)
            }
        }
    }
    
    private fun getPasswordFromInput(passWord: (String) -> Unit) {
        var editTextPassword: EditText
        
        AlertDialog.Builder(mainActivity).run {
            editTextPassword = EditText(context)
            editTextPassword.hint = "password"
            setView(editTextPassword)
            
            setTitle("비밀번호")
            setPositiveButton("확인") { _, _ ->
                passWord(editTextPassword.text.toString())
            }
            setNegativeButton("취소") { _, _ -> }
            show()
        }
    }
    
}