package com.example.test

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : MainActivity() {

    lateinit var recycler: RecyclerView
    lateinit var adapter: com.example.adapters.SimpleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
    override fun initGlobalVariables() {
        super.initGlobalVariables()
        recycler =  findViewById(R.id.recycler)
        adapter =  com.example.adapters.SimpleAdapter(applicationContext, ArrayList())
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
}
