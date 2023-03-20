package com.yuvesh.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),INotesRvAdapter {

    lateinit var viewModel: NoteViewModel
  private lateinit var recyclerView: RecyclerView
    lateinit var input:EditText
    lateinit var btnsubmit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)

        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NoteRvAdapter(this,this)
        recyclerView.adapter=adapter

        input=findViewById(R.id.input)
        btnsubmit=findViewById(R.id.btnSubmit)



        viewModel= ViewModelProvider(this@MainActivity,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)
            }

        })



        }

    override fun onItemClicked(note: Note) {
              viewModel.deleteNote(note)
              Toast.makeText(this@MainActivity,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if(noteText.isNotEmpty())
        {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this@MainActivity,"$noteText added",Toast.LENGTH_LONG).show()
        }
    }
}
