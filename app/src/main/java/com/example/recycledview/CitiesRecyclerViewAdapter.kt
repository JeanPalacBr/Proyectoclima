package com.example.recycledview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycledview.data.Cities


class CitiesRecyclerViewAdapter(private val mValue: List<Cities>, private val mListener : CitiesRecyclerViewAdapter.onListInteractions):RecyclerView.Adapter<CitiesRecyclerViewAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var va = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)

        return ViewHolder(va)
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(holder: CitiesRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(mValue[position])

        holder.itemView.setOnClickListener{
            mListener?.onListItemInteraction(mValue[position])
        }
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        fun bindItems(data: Cities){
            val nombre:TextView=itemView.findViewById(R.id.textViewUserName)
            val temperatura:TextView=itemView.findViewById(R.id.textViewUserLastName)
            val estado:TextView=itemView.findViewById(R.id.textViewestado)
            val imagen:ImageView=itemView.findViewById(R.id.UserPhoto)
            nombre.text = data.cityname
            temperatura.text = data.temp.toString()+" °c"
            estado.text = data.Description
            //apellido.text = data.temp
            Glide.with(itemView.context).load(data.IconID).into(imagen)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "${data.cityname}",Toast.LENGTH_LONG).show()

            }
        }
    }
    interface  onListInteractions {
        fun onListItemInteraction(item: Cities?)

        fun onListButtonInteraction(item: Cities?)
    }
    public fun updateData() {
        notifyDataSetChanged()
    }
}


