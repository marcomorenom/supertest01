package com.example.test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.utils.*

open class MainActivity : AppCompatActivity()
{

    /** CORE VARIABLES **/
    open var validUtils : Validations? = null
    open var isBusy: Boolean = false
//    LIFE CYCLE ACTIVITIES
    /**START LIFE CYCLE METHODS **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        super.onStart()
        initGlobalVariables()
        initListeners()
    }
    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            validateUser()
        }, 1000)
    }
    override fun onPause() {
        super.onPause()
    }
    override fun onStop() {
        super.onStop()
    }
    /**END LIFE CYCLE METHODS **/


    /**START LOGIC METHODS **/
    open fun initGlobalVariables() {
        Log.d(TAG_MainActivity,"initGlobalVariables")

    }
    open fun validateUser(){
        if(isUserLogged()){
            startApp(case = LOGIN_HOME_CASE)
        }else {
            startApp(case = LOGIN_LOGIN_CASE)
        }
    }

    private fun isUserLogged(): Boolean {
        var result = true
        val settings = applicationContext.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        val token = settings.getString(TOKEN_KEY,"")
        if(token.isNullOrEmpty()){
            result = false
        }
        return result
    }

    private fun isUserRegistered(): Boolean {
        return false
    }
    private fun startApp(case: String) {
        when (case) {
            LOGIN_HOME_CASE -> {
                Log.d(TAG_MainActivity,LOGIN_HOME_CASE)
                startActivity(Intent(applicationContext, Home::class.java))
                finishAffinity()
            }
            LOGIN_LOGIN_CASE -> {
                Log.d(TAG_MainActivity,LOGIN_LOGIN_CASE)
                startActivity(Intent(applicationContext, Login::class.java))
                finishAffinity()
            }
            LOGIN_SIGN_UP_CASE -> {
                Log.d(TAG_MainActivity,LOGIN_SIGN_UP_CASE)
                startActivity(Intent(applicationContext, SignUp::class.java))
                finishAffinity()
            }
            else -> { // Note the block
                //show error, somethings is wrong with credentials
                Log.e(TAG_MainActivity,applicationContext.getString(R.string.start_app_error))
                logOut()
            }
        }
    }
    open fun logOut(){
        val settings = applicationContext.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        settings.edit().clear().apply()
        startActivity(Intent(applicationContext, Login::class.java))
        finishAffinity()

    }
    open fun initListeners() {}
    /** END LOGIC METHODS **/

    /**
     * VIEW METHODS
     */
    open fun showLoader(){
        isBusy = true
    }
    open fun hideLoader(){
        isBusy = false
    }

}