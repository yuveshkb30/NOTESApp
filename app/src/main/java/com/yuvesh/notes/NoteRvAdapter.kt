package com.yuvesh.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class NoteRvAdapter(private val context: Context, private val listener:INotesRvAdapter):RecyclerView.Adapter<NoteRvAdapter.NoteViewHolder>() {


    private val allNotes=ArrayList<Note>()
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val textView=itemView.findViewById<TextView>(R.id.txttext)
        val buttondelete=itemView.findViewById<ImageView>(R.id.btndelete)
    }
fun updateList(newList: List<Note>)
{
    allNotes.clear()
    allNotes.addAll(newList)
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder= NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.buttondelete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
                  val current_note=allNotes[position]
                  holder.textView.text=current_note.text
    }

    override fun getItemCount(): Int {
          return allNotes.size
    }
}

interface INotesRvAdapter
{


    fun onItemClicked(note: Note)

}