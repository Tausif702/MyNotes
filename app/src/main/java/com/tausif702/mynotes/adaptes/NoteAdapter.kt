package com.tausif702.mynotes.adaptes

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import com.tausif702.mynotes.models.Note
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tausif702.mynotes.databinding.NoteLayoutBinding
import com.tausif702.mynotes.fragments.HomeFragmentDirections


class NoteAdapter(private val onDeleteClickListener: (Note) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = differ.currentList[position]
        holder.itemBinding.noteTitle.text = currentNote.NoteTitle
        holder.itemBinding.noteDesc.text = currentNote.NoteDesc

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(currentNote)
            it.findNavController().navigate(direction)
        }
        holder.itemBinding.deleteBtn.setOnClickListener {
            onDeleteClickListener(currentNote)
            Toast.makeText(holder.itemView.context,"Note Deleted",Toast.LENGTH_SHORT).show()
        }
        holder.itemBinding.shareBtn.setOnClickListener {
            //code for sharing
            val shareIntent=Intent(Intent.ACTION_SEND).apply {
                type="text/plain"
                putExtra(Intent.EXTRA_SUBJECT,"Note Title: ${currentNote.NoteTitle}")
                putExtra(Intent.EXTRA_TEXT,"Mag: ${currentNote.NoteDesc}")
            }
            it.context.startActivity(shareIntent)
        }
        holder.itemBinding.copyBtn.setOnClickListener {
            //code for copying
            val clipboard= holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip=android.content.ClipData.newPlainText("Note",currentNote.NoteDesc)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(holder.itemView.context,"Copied",Toast.LENGTH_SHORT).show()
        }
    }


    class NoteViewHolder(val itemBinding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem.id == newItem.id && oldItem.NoteDesc ==
                    newItem.NoteDesc && oldItem.NoteTitle == newItem.NoteTitle

        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id && oldItem.NoteDesc ==
                    newItem.NoteDesc && oldItem.NoteTitle == newItem.NoteTitle
        }

    }
    val differ = AsyncListDiffer(this@NoteAdapter, diffCallback)

}