package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.models.UserItem
import com.example.utils.CONTENT_KEY
import com.google.gson.Gson
import java.lang.Exception

class Details : AppCompatActivity() {
    lateinit var title : TextView
    lateinit var subtitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = findViewById(R.id.title)
        subtitle = findViewById(R.id.subtitle)
        initContent(UserItem())
        getContent()
    }

    private fun getContent() {
        intent.extras?.let { extras ->
            val contentItem = extras.getString(CONTENT_KEY)
            contentItem?.let { itemString ->
                try {
                    val user : UserItem? = Gson().fromJson(itemString,UserItem::class.java)
                    user?.let {_user ->
                        initContent(userContent = _user)
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun initContent(userContent: UserItem) {
        title.text = userContent.username
        subtitle.text = userContent.email
    }

}