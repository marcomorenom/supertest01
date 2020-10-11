package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapters.SimpleAdapter
import com.example.models.UserItem
import com.example.repositories.HomeRepo
import com.example.utils.CONTENT_KEY
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*

class Home : MainActivity(), SimpleAdapter.OnItemClicked {
    lateinit var recycler: RecyclerView
    lateinit var logout: Button
    lateinit var adapter: com.example.adapters.SimpleAdapter
    var userList: ArrayList<UserItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()
        HomeRepo(applicationContext.getString(R.string.SERVER_URL)).getUserList { list ->
            if(!list.isNullOrEmpty()){
                userList = list
                updateRecycler()
            }else {
                Toast.makeText(applicationContext,applicationContext.getText(R.string.default_connection_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRecycler() {
        adapter.updateData(userList)
        adapter.notifyDataSetChanged()
    }

    override fun initGlobalVariables() {
        super.initGlobalVariables()
        recycler =  findViewById(R.id.recycler)
        logout =  findViewById(R.id.logout)
        adapter =  com.example.adapters.SimpleAdapter(applicationContext, ArrayList(), this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
    }
    override fun initListeners() {
        super.initListeners()
        logout.setOnClickListener {
            logOut()
        }
    }
    override fun validateUser() {
        //let's change this for now
//        super.validateUser()
    }

    override fun onItemClicked(item: UserItem?) {
        //validate item first
        item?.let {
            val intent = Intent(applicationContext, Details::class.java)
            intent.putExtra(CONTENT_KEY, Gson().toJson(item))
            startActivity(intent)
        }
    }
}
