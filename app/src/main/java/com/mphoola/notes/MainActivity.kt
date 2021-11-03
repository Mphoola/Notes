package com.mphoola.notes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var tvFavouritesCounter: TextView
    private lateinit var tvLabelsCounter: TextView
    private lateinit var tvTrashedCounter: TextView
    private lateinit var tvNotesCounter: TextView
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MainTheme)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        toolbar.setupWithNavController(navController, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(nav_view, navController)

        //drawer nav item counter
        val navView: NavigationView = findViewById(R.id.nav_view)

        tvNotesCounter = navView.menu.findItem(R.id.homeFragment)
                            .actionView.findViewById(R.id.notes_counter_view)

        tvFavouritesCounter = navView.menu.findItem(R.id.favouritesFragment)
                            .actionView.findViewById(R.id.favourites_counter_view)

        tvTrashedCounter = navView.menu.findItem(R.id.trashedFragment)
                            .actionView.findViewById(R.id.trashed_counter_view)

        tvLabelsCounter = navView.menu.findItem(R.id.labelsFragment)
                            .actionView.findViewById(R.id.labels_counter_view)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        println("note count: " + mainActivityViewModel.allNotesCount.toString())

        tvNotesCounter.text = "10"
        tvLabelsCounter.text = "0"
        tvTrashedCounter.text = "1"
        tvFavouritesCounter.text = "3"
    }

//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_action_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.toolbar_menu_orientation -> Toast.makeText(this, "Change card view", Toast.LENGTH_LONG).show()
//            R.id.toolbar_menu_search -> Toast.makeText(this, "time to search", Toast.LENGTH_LONG).show()
//            R.id.toolbar_menu_sort -> Toast.makeText(this, "Order item by...", Toast.LENGTH_LONG).show()
//        }
//        return false
//    }

//    override fun onBackPressed() {
//        val dialog  = androidx.appcompat.app.AlertDialog.Builder(this)
//        dialog.setCancelable(false)
//        dialog.setTitle("Are you sure?")
//        dialog.setMessage("You want to Exit?")
//        dialog.setPositiveButton("Yes"){ _: DialogInterface, _: Int ->
//            super.onBackPressed()
//        }
//        dialog.setNegativeButton("No!"){ _: DialogInterface, _: Int ->
//
//        }
//        dialog.show()
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}