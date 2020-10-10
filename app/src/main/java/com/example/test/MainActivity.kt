package com.example.test

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
        validUtils = Validations()
    }
    open fun validateUser(){
        validUtils?.let { validations ->
            if(validations.isUserLogged()){
                startApp(case = LOGIN_HOME_CASE)
            }else {
                if(isUserRegistered()){
                    startApp(case = LOGIN_LOGIN_CASE)
                }else{
                    startApp(case = LOGIN_SIGN_UP_CASE)
                }
            }
        }
        if(validUtils == null){
            //show error, somethings is wrong with credentials
            logOut()
        }
    }
    private fun isUserRegistered(): Boolean {
        return false
    }
    private fun startApp(case: String) {
        when (case) {
            LOGIN_HOME_CASE -> {
                Log.d(TAG_MainActivity,LOGIN_HOME_CASE)
                startActivity(Intent(applicationContext, Home::class.java))
            }
            LOGIN_LOGIN_CASE -> {
                Log.d(TAG_MainActivity,LOGIN_LOGIN_CASE)
                startActivity(Intent(applicationContext, Login::class.java))
            }
            LOGIN_SIGN_UP_CASE -> {
                Log.d(TAG_MainActivity,LOGIN_SIGN_UP_CASE)
                startActivity(Intent(applicationContext, SignUp::class.java))
            }
            else -> { // Note the block
                //show error, somethings is wrong with credentials
                Log.e(TAG_MainActivity,applicationContext.getString(R.string.start_app_error))
                logOut()
            }
        }
    }
    open fun logOut(){

    }
    open fun initListeners() {}
    /** END LOGIC METHODS **/

    /**
     * VIEW METHODS
     */
    open fun showLoader(){}
    open fun hideLoader(){}

}