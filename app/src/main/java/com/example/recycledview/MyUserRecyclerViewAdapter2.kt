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


class MyUserRecyclerViewAdapter2(private val mValue: List<User>, private val mListener : MyUserRecyclerViewAdapter2.onListInteractions):RecyclerView.Adapter<MyUserRecyclerViewAdapter2.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var va = LayoutInflater.from(parent.context).inflate(R.layout.row2,parent,false)
        return ViewHolder(va)
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(holder: MyUserRecyclerViewAdapter2.ViewHolder, position: Int) {
        holder.bindItems(mValue[mValue.size-1])

        holder.itemView.setOnClickListener{
            mListener?.onListItemInteraction(mValue[position])
        }
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        fun bindItems(data: User){
            val nombreciudad:TextView=itemView.findViewById(R.id.citynd)
            val tempemin:TextView=itemView.findViewById(R.id.temp_mind)
            val tempemax:TextView=itemView.findViewById(R.id.temp_maxd)
            val tempesens:TextView=itemView.findViewById(R.id.temp_feeld)
            val tempeact:TextView=itemView.findViewById(R.id.tempd)
            val estado:TextView=itemView.findViewById(R.id.climad)
            val dateatime:TextView=itemView.findViewById(R.id.date_timed)
            val humidity:TextView=itemView.findViewById(R.id.humidityd)
            val windspeed:TextView=itemView.findViewById(R.id.windspeedd)
            val pressure:TextView=itemView.findViewById(R.id.pressured)
            val imagen:ImageView=itemView.findViewById(R.id.ImgEstado)

            nombreciudad.text = data.cityname
            tempemin.text = data.temp_min.toString()+" °c"
            tempemax.text = data.temp_max.toString()+" °c"
            tempesens.text = data.Feels_like.toString()+" °c"
            tempeact.text = data.temp.toString()+" °c"
            estado.text = data.Description
            dateatime.text = data.datehour.toString()
            humidity.text = data.Humidity.toString()+ "%"
            windspeed.text = data.WindSpeeed.toString()+" m/s"
            pressure.text = data.Pressure.toString()+ " kpa"
            Glide.with(itemView.context).load(data.IconID).into(imagen)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "${data.cityname}",Toast.LENGTH_LONG).show()
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


