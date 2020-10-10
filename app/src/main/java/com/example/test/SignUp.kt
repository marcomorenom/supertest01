package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignUp : MainActivity() {

    lateinit var userName: EditText
    lateinit var pass: EditText
    lateinit var signBtn: Button
    var isUserValidated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    override fun initGlobalVariables() {
        super.initGlobalVariables()
        userName = findViewById(R.id.userName)
        pass = findViewById(R.id.pass)
        signBtn = findViewById(R.id.signBtn)
    }
    override fun validateUser() {
        // we don't need this for sign up
    }

    override fun initListeners() {
        super.initListeners()
        signBtn.setOnClickListener {
            showLoader()
            registerUser { registerSuccess ->
                hideLoader()
                if(registerSuccess){
                    startActivity(Intent(applicationContext,Home::class.java))
                }else{
                    // show error!
                }
            }
        }

    }

    private fun registerUser(onRegister: (result: Boolean) -> Unit) {
        onRegister.invoke(true)
    }

    /** LOGIC METHODS **/

}