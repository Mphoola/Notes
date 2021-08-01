package com.mphoola.notes

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,
    NoteRVAdapter.NoteClickInterface, NoteRVAdapter.NoteClickDeleteInterface {

    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    //toolbar
    lateinit var toolbarSearch: ImageView
    lateinit var toolbarFilter: ImageView
    lateinit var toolbarLayout: ImageView

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar
        setSupportActionBar(toolbar_main)
        title = "All Notes"

        drawerLayout = findViewById(R.id.drawer_layout)
        navView= findViewById(R.id.nav_view)


        //nav date
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        val view: View = layoutInflater.inflate(R.layout.nav_header_layout, false)
        val dateView = view.findViewById<TextView>(R.id.drawer_nav_date)
        dateView?.text = currentDate

        //val view: View = layoutInflater.inflate(R.layout.dialog_dashboard, null)
        //val toDoName = view.findViewById<EditText>(R.id.add_todo_text)



        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar_main, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        toolbarSearch = findViewById(R.id.toolbar_image_search)
        toolbarFilter = findViewById(R.id.toolbar_image_filter)
        toolbarLayout = findViewById(R.id.toolbar_image_layout)

        notesRV = findViewById(R.id.notes_rv)
        addFAB = findViewById(R.id.floatingActionButton)

        // on below line we are initializing our adapter class.
        val noteRVAdapter = NoteRVAdapter(this, this, this)
        notesRV.layoutManager = LinearLayoutManager(this)
        notesRV.adapter = noteRVAdapter

        //initializing view model
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModal::class.java)

        //calling all notes method from view modal clas to observe the changes on list
        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })

        addFAB.setOnClickListener {
            val intent = Intent(this, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteNote(note)
        Toast.makeText(this, "Deleted!", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                Toast.makeText(this, "Home selected", Toast.LENGTH_LONG).show()
            }
            R.id.nav_favourites -> {
                Toast.makeText(this, "Favourites selected", Toast.LENGTH_LONG).show()
            }
            R.id.nav_trash -> {
                Toast.makeText(this, "Trash selected", Toast.LENGTH_LONG).show()
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "settings selected", Toast.LENGTH_LONG).show()
            } R.id.nav_label -> {
                Toast.makeText(this, "Label selected", Toast.LENGTH_LONG).show()
            }
            R.id.nav_share -> {
                Toast.makeText(this, "Share selected", Toast.LENGTH_LONG).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}