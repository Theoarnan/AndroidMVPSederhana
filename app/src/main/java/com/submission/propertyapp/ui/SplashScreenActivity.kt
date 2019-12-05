package com.submission.propertyapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import com.submission.propertyapp.R
import com.submission.propertyapp.model.LoginResponse
import com.submission.propertyapp.presenter.AllPresenter
import com.submission.propertyapp.util.Preferences
import com.submission.propertyapp.view.CommonView

class SplashScreenActivity : AppCompatActivity(), CommonView{

    private lateinit var presenter : AllPresenter
    private var belumAutoLogin = true

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 4000 //3 seconds
    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter = AllPresenter(this, this)
        presenter.getAllProperty()

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun showLoading() {

    }

    override fun error(error: Throwable) {
        if (belumAutoLogin){
            belumAutoLogin = false
            val username = Preferences.getUsername(this)
            val password = Preferences.getPassword(this)
            val strAuth = username+":"+password
            val auth = "Basic "+ Base64.encodeToString(strAuth.toByteArray(), Base64.NO_WRAP)
            presenter.getToken(auth)
            return
        }
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun success(anyResponse: Any) {
        if (!belumAutoLogin){
            val loginResponse = anyResponse as LoginResponse
            val token = loginResponse.token
            Preferences.saveToken(token, this)
        }
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun hideLoading() {

    }
}
