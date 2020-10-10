package com.example.test

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapters.SimpleAdapter

class Home : MainActivity(), SimpleAdapter.OnItemClicked {

    lateinit var recycler: RecyclerView
    lateinit var adapter: com.example.adapters.SimpleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
    override fun initGlobalVariables() {
        super.initGlobalVariables()
        recycler =  findViewById(R.id.recycler)
        adapter =  com.example.adapters.SimpleAdapter(applicationContext, ArrayList(), this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
    }
    override fun initListeners() {
        super.initListeners()
    }
    override fun validateUser() {
        //let's change this for now
//        super.validateUser()
    }

    override fun onItemClicked(item: Any?) {
        //validate item first
        startActivity(Intent(applicationContext, Details::class.java))
    }
}
