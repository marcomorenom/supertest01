package com.example.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.models.UserRegisterModel
import com.example.repositories.LoginRepo
import com.example.utils.ID_KEY
import com.example.utils.PREF_KEY
import com.example.utils.TOKEN_KEY

class Login : MainActivity() {

    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var signBtn: Button
    lateinit var login: Button
    private val TAG = "Login"
    private var passValue = ""
    private var emailValue = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun initGlobalVariables() {
        super.initGlobalVariables()
        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        signBtn = findViewById(R.id.signUp)
        login = findViewById(R.id.login)
    }
    override fun validateUser() {
        // we don't need this for sign up
    }

    override fun initListeners() {
        super.initListeners()
        pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text : String = s.toString().trim()
                passValue = text
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text : String = s.toString().trim()
                emailValue = text
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        signBtn.setOnClickListener {
            startActivity(Intent(applicationContext,SignUp::class.java))
        }
        login.setOnClickListener {
            if(!isBusy){
                showLoader()
                if(validateInfo(showErrors = true)){
                    login { user : UserRegisterModel? ->
                        hideLoader()
                        if(user!=null){
                            Log.d(TAG, "register success!")
                            saveUserOnPref(user)
                            startActivity(Intent(applicationContext,Home::class.java))
                            finishAffinity()
                        }else{
                            // show error!
                            Toast.makeText(applicationContext,applicationContext.getText(R.string.login_error), Toast.LENGTH_SHORT).show()
                            Log.e(TAG, "register failed!")
                        }
                    }
                }else{
                    hideLoader()
                }
            }
        }
    }

    private fun saveUserOnPref(userRegister: UserRegisterModel) {
        val settings = applicationContext.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        settings.edit().putString(ID_KEY, userRegister.id).apply()
        settings.edit().putString(TOKEN_KEY, userRegister.jwt).apply()
    }

    private fun validateInfo(showErrors : Boolean) : Boolean {
        var isValid = true
        if(passValue.isEmpty()){
            if(showErrors){
                pass.error = applicationContext.getText(R.string.default_invalid_field)
            }
            isValid = false
        }
        if(emailValue.isEmpty()){
            if(showErrors){
                email.error = applicationContext.getText(R.string.default_invalid_field)
            }
            isValid = false
        }
        if(!isValid && showErrors){
            Toast.makeText(applicationContext, applicationContext.getText(R.string.default_invalid_field), Toast.LENGTH_SHORT).show()
        }

        return isValid
    }

    private fun login(onRegister: (result: UserRegisterModel?) -> Unit) {
        LoginRepo(applicationContext.getString(R.string.SERVER_URL)).login(
            email = emailValue,
            pass = passValue,
            onRegister)
    }


}