package edu.msudenver.cs3013.project03

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import edu.msudenver.cs3013.project03.data.NameViewModel
import edu.msudenver.cs3013.project03.data.ViewModelFactory

class MainActivity2 : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var nameToDisplay = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val model = ViewModelProvider(this, ViewModelFactory.getInstance())[NameViewModel::class.java]

        //get first and last name from LoginActivity
        val firstName = model.getFirstName()
        val lastName = model.getLastName()

        //connect name
        nameToDisplay = firstName.plus(" ").plus(lastName)

        //toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //drawer and navView
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        //set first and last name in nav_header_main.xml
        val headerView = navView.getHeaderView(0)
        val navName = headerView.findViewById<TextView>(R.id.nav_name)
        navName?.text = nameToDisplay

        //set email in nav_header_main.xml
        val email = headerView.findViewById<TextView>(R.id.nav_email)
        email?.text = model.getEmail()

        //nav controller
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val bottomNavView: BottomNavigationView = findViewById(R.id.navigation)

        //bottom nav controller
        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_home)
                }

                R.id.navigation_profile -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_profile)
                }

                R.id.navigation_settings -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_settings)
                }
            }
            false
        }
    }
    //override onSupportNavigateUp to navigate to previous fragment
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}