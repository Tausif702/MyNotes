package com.tausif702.mynotes.activitis

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.tausif702.mynotes.R
import com.tausif702.mynotes.databinding.ActivityMainBinding
import com.tausif702.mynotes.databases.NoteDatabase
import com.tausif702.mynotes.repository.NoteRepository
import com.tausif702.mynotes.vm.NoteViewModel
import com.tausif702.mynotes.vm.NoteViewModelFuctory

class MainActivity : AppCompatActivity() {

     lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F57C00")))


        setupViewModel()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment

        if (navHostFragment != null) {
            navController = navHostFragment.navController
            setupActionBarWithNavController(navController)
        } else {
            throw IllegalStateException("NavHostFragment not found. Check your layout XML.")
        }
    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFuctory(application, noteRepository)
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
