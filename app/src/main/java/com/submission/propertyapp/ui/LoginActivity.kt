package com.submission.propertyapp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.submission.propertyapp.R
import com.submission.propertyapp.model.LoginResponse
import com.submission.propertyapp.presenter.AllPresenter
import com.submission.propertyapp.util.Preferences
import com.submission.propertyapp.view.CommonView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), CommonView {

    private lateinit var presenter : AllPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = AllPresenter(this, this)
        settingTombolLogin()
        checkAndRequestPermissions()
    }

    private fun settingTombolLogin(){
        btn_login.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            if (cb_remember.isChecked){
                Preferences.saveUsername(username, this)
                Preferences.savePassword(password, this)
            }
            val strAuth = username+":"+password
            val auth = "Basic "+ Base64.encodeToString(strAuth.toByteArray(), Base64.NO_WRAP)
            presenter.getToken(auth)
        }
    }

    override fun showLoading() {

    }

    override fun error(error: Throwable) {
        Toast.makeText(this, "Username dan Password tidak Ada", Toast.LENGTH_LONG).show()
    }

    override fun success(anyResponse: Any) {
        //Ketika Username dan Password benar
        val loginResponse = anyResponse as LoginResponse
        val token = loginResponse.token
        Preferences.saveToken(token, this)
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun hideLoading() {

    }

    //Cek Permisssion
    private fun checkAndRequestPermissions(): Boolean {

//        val locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val readExternalPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val writeExternalPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val listPermissionsNeeded = arrayListOf<String>()

//        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
//        }

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (readExternalPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (writeExternalPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 400)
            return false
        }
        return true
    }
}
