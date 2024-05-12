package com.example.measuremate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.measuremate.databinding.MeasureLayoutBinding
import com.example.measuremate.fragments.HomeFragmentDirections
import com.example.measuremate.model.Measure

class MeasureAdapter : RecyclerView.Adapter<MeasureAdapter.NoteViewHolder>(){

    class NoteViewHolder(val itemBinding:MeasureLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Measure>(){
        override fun areItemsTheSame(oldItem: Measure, newItem: Measure): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle

        }

        override fun areContentsTheSame(oldItem: Measure, newItem: Measure): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            MeasureLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)


            it.findNavController().navigate(direction)
        }
    }
}