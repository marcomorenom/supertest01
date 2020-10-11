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
import com.example.utils.TAG_MainActivity

class SignUp : MainActivity() {

    lateinit var userName: EditText
    lateinit var pass: EditText
    lateinit var email: EditText
    lateinit var signBtn: Button
    var isUserValidated = false
    private val TAG = "SignUp"
    private var passValue = ""
    private var emailValue = ""
    private var userValue = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    override fun initGlobalVariables() {
        super.initGlobalVariables()
        userName = findViewById(R.id.userName)
        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        signBtn = findViewById(R.id.signBtn)
    }
    override fun validateUser() {
        // we don't need this for sign up
    }

    override fun initListeners() {
        super.initListeners()
        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text : String = s.toString().trim()
                emailValue = text
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text : String = s.toString().trim()
                passValue = text
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        userName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text : String = s.toString().trim()
                userValue = text
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        signBtn.setOnClickListener {
            showLoader()
            if(validateInfo(showErrors = true)){
                registerUser { user : UserRegisterModel? ->
                    hideLoader()
                    if(user!=null){
                        Log.d(TAG, "register success!")
                        saveUserOnPref(user)
                        startActivity(Intent(applicationContext,Home::class.java))
                        finishAffinity()
                    }else{
                        // show error!
                        Toast.makeText(applicationContext,applicationContext.getText(R.string.default_connection_error), Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "register failed!")
                    }
                }
            }
        }

    }

    private fun saveUserOnPref(userRegister: UserRegisterModel) {
        val settings = applicationContext.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        settings.edit().putString("id", userRegister.id).apply()
        settings.edit().putString("jwt", userRegister.jwt).apply()
    }

    private fun validateInfo(showErrors : Boolean) : Boolean {
        var isValid = true
        if(passValue.isEmpty()){
            if(showErrors){
                pass.error = applicationContext.getText(R.string.default_invalid_field)
                Toast.makeText(applicationContext, applicationContext.getText(R.string.default_invalid_field), Toast.LENGTH_SHORT).show()
            }
            isValid = false
        }
        if(userValue.isEmpty()){
            if(showErrors){
                userName.error = applicationContext.getText(R.string.default_invalid_field)
                Toast.makeText(applicationContext, applicationContext.getText(R.string.default_invalid_field), Toast.LENGTH_SHORT).show()
            }
            isValid = false
        }
        if(emailValue.isEmpty()){
            if(showErrors){
                email.error = applicationContext.getText(R.string.default_invalid_field)
                Toast.makeText(applicationContext, applicationContext.getText(R.string.default_invalid_field), Toast.LENGTH_SHORT).show()
            }
            isValid = false
        }
        return isValid
    }

    private fun registerUser(onRegister: (result: UserRegisterModel?) -> Unit) {
        LoginRepo(applicationContext.getString(R.string.SERVER_URL)).registerUser(
            userName = userValue,
            pass = passValue,
            email = emailValue,
            onRegister)
    }

    /** LOGIC METHODS **/

}