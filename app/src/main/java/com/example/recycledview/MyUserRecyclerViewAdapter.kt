package com.example.recycledview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycledview.data.User


class MyUserRecyclerViewAdapter(private val mValue: List<User>,private val mListener : MyUserRecyclerViewAdapter.onListInteractions):RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var va = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return ViewHolder(va)
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(holder: MyUserRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(mValue[position])

        holder.itemView.setOnClickListener{
            mListener?.onListItemInteraction(mValue[position])
        }
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        fun bindItems(data: User){
            val nombre:TextView=itemView.findViewById(R.id.textViewUserName)
            val apellido:TextView=itemView.findViewById(R.id.textViewUserLastName)
            val imagen:ImageView=itemView.findViewById(R.id.UserPhoto)
            nombre.text = data.name
            apellido.text = data.lastname
            Glide.with(itemView.context).load(data.imgsrc).into(imagen)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "${data.name}",Toast.LENGTH_LONG).show()
            }
        }
    }
    interface  onListInteractions {
        fun onListItemInteraction(item: User?)

        fun onListButtonInteraction(item: User?)
    }
    public fun updateData() {
        notifyDataSetChanged()
    }
}


