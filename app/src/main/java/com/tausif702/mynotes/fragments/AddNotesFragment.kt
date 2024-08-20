package com.tausif702.mynotes.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.tausif702.mynotes.R
import com.tausif702.mynotes.activitis.MainActivity
import com.tausif702.mynotes.databinding.FragmentAddNotesBinding
import com.tausif702.mynotes.models.Note
import com.tausif702.mynotes.vm.NoteViewModel

class AddNotesFragment : Fragment(R.layout.fragment_add_notes),MenuProvider {
private var addNotesBinding: FragmentAddNotesBinding? = null
    private val binding get()=addNotesBinding!!

    private lateinit var addNoteView: View
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        addNotesBinding=FragmentAddNotesBinding.inflate(inflater,container,false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Add Note"
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel = (activity as MainActivity).noteViewModel
        addNoteView=view
    }


    private fun saveNote(view: View){
        val noteTitle=binding.addNoteTitle.text.trim().trim()
        val noteDesc=binding.addNoteDesc.text.trim().trim()

        if (noteTitle.isNotEmpty()){
            val note= Note(0,noteTitle.toString(),noteDesc.toString())
            noteViewModel.addNotes(note)
            Toast.makeText(requireContext(), "com.tausif702.mynotes.model.Note Saved", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)
            }else{
            Toast.makeText(requireContext(), "Please Enter com.tausif702.mynotes.model.Note Title", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

        return  when(menuItem.itemId){
            R.id.saveMenu->{
                saveNote(addNoteView)
                true
            }
            else-> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNotesBinding=null
    }

}