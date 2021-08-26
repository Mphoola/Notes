package com.mphoola.notes

import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //NavigationView.OnNavigationItemSelectedListener,
    //    NoteRVAdapter.NoteClickInterface, NoteRVAdapter.NoteClickDeleteInterface
//    lateinit var viewModal: NoteViewModal
//    lateinit var notesRV: RecyclerView
 //   private lateinit var addFAB: FloatingActionButton

    lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

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