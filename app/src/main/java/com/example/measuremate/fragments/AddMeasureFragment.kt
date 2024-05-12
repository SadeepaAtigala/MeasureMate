package com.example.measuremate.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.measuremate.MainActivity
import com.example.measuremate.R
import com.example.measuremate.databinding.FragmentAddMeasureBinding
import com.example.measuremate.model.Measure
import com.example.measuremate.viewmodel.MeasureViewModel


class AddMeasureFragment : Fragment(R.layout.fragment_add_measure), MenuProvider {

    private var addNoteBinding: FragmentAddMeasureBinding? = null
    private val binding get() = addNoteBinding!!

    private lateinit var notesViewModel: MeasureViewModel
    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding = FragmentAddMeasureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel=(activity as MainActivity).measureViewModel
        addNoteView= view
    }

    private fun saveNotes(view: View){
        val noteTile = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if (noteTile.isNotEmpty()){
            val measure = Measure(0,noteTile,noteDesc)
            notesViewModel.addNote(measure)

            Toast.makeText(addNoteView.context,"Measure Saved", Toast.LENGTH_SHORT)
            view.findNavController().popBackStack(R.id.homeFragment,false)

        }else{
            Toast.makeText(addNoteView.context,"Please enter Name", Toast.LENGTH_SHORT)

        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_measure, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu -> {
                saveNotes(addNoteView)
                true
            }

            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }
}