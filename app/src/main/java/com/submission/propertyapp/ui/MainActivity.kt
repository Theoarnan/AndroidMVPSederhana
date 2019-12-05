package com.submission.propertyapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.submission.propertyapp.R
import com.submission.propertyapp.ui.dashboard.DashboardFragment
import com.submission.propertyapp.ui.property.PropertyFragment
import com.submission.propertyapp.util.Preferences
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.submission.propertyapp.ui.about.AboutFragment
import com.submission.propertyapp.ui.favorite.FavoriteContainerFragment
import com.submission.propertyapp.ui.property.AddPropertyActivity


class MainActivity : AppCompatActivity() {

    private lateinit var dl: DrawerLayout
    private lateinit var t: ActionBarDrawerToggle
    private lateinit var nv: NavigationView
    private lateinit var bv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Untuk DrawerLayout
        dl = findViewById(R.id.drawer_layout)
        t = ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close)
        dl.addDrawerListener(t)
        t.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nv = findViewById(R.id.nv)
        nv.setNavigationItemSelectedListener { item ->
            val id = item.itemId
            dl.closeDrawer(Gravity.LEFT)
            when (id) {
//                R.id.home -> {
//                    val fragment = DashboardFragment.newInstance()
//                    addFragment(fragment)
//                }
                R.id.nav_property -> {
                    val fragment = DashboardFragment.newInstance()
                    addFragment(fragment)
                }
                R.id.nav_about -> {
                    val fragment = AboutFragment.newInstance()
                    addFragment(fragment)
                }
                R.id.nav_logout -> {
                    Preferences.saveToken("", this)
                    Preferences.saveUsername("", this)
                    Preferences.savePassword("", this)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_setting -> {
                    startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                }
            }
            true
        }

        val defaultfragment = DashboardFragment.newInstance()
        addFragment(defaultfragment)

        //Untuk BottomNavigationLayout
        val mHandler = Handler()
        val mPendingRunnable = Runnable {
            bv = findViewById(R.id.nav_view)
            bv.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)
        }
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable)
        }
    }

    //Fungsi BottomNavigationView
    private val onBottomNavItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_property -> {
                    val fragment = PropertyFragment.newInstance()
                    addBottomFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_add_property -> {
                    val intent = Intent(applicationContext, AddPropertyActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.search_kategori_property -> {
                    val fragment = AboutFragment.newInstance()
                    addBottomFragment(fragment)
                    return@OnNavigationItemSelectedListener true
//                    val fragment = AddPropertyFragment.newInstance()
//                    addBottomFragment(fragment)
//                return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    //Add Fragment untuk DrawerLayout
    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    //Add Fragment untuk Bottom Navigation
    fun addBottomFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (t.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}

