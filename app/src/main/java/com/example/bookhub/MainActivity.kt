package com.example.bookhub

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView
    lateinit var frameLayout : FrameLayout
    var previousMenuItem : MenuItem? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)
        frameLayout = findViewById(R.id.frameLayout)
        setUpToolbar()
        openDashboard()
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open_Drawer,R.string.close_Drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            if(previousMenuItem!=null) {
                previousMenuItem?.isCheckable = false
            }
            it.isCheckable=true
            it.isChecked= true
            previousMenuItem=it
            when(it.itemId){
                R.id.dashboard-> {
                 //   Toast.makeText(this@MainActivity, "clicked on dashboard", Toast.LENGTH_LONG).show()
                    openDashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.favourite->{

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,FavouriteFragment())
                       // .addToBackStack("Favourite")
                        .commit()
                    supportActionBar?.title ="Favourite"
                    drawerLayout.closeDrawers()
                }
                R.id.profile->{
                  //  Toast.makeText(this@MainActivity, "clicked on profile ", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,ProfileFragment())
                       // .addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title ="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.about->{
                 //   Toast.makeText(this@MainActivity, "clicked on about", Toast.LENGTH_LONG).show()

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,AboutFragment())
                       // .addToBackStack("About")
                        .commit()
                    supportActionBar?.title ="About"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true }

    }

        fun setUpToolbar(){
            setSupportActionBar(toolbar)
            supportActionBar?.title ="Toolbar title"

            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }


    fun openDashboard(){
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
       // transaction.addToBackStack("Dashboard")
        transaction.commit()
        supportActionBar?.title ="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)

    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag){
            !is DashboardFragment ->openDashboard()
            else ->super.onBackPressed()
        }

    }

    }
