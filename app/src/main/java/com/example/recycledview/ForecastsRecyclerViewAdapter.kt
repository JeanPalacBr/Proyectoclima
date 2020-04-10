package com.example.recycledview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycledview.data.Forecasts


class ForecastsRecyclerViewAdapter(private val mValue: MutableList<Forecasts>, private val mListener : ForecastsRecyclerViewAdapter.onListInteractions):RecyclerView.Adapter<ForecastsRecyclerViewAdapter.ViewHolder>(){
    private val lastItem = mutableListOf<Int>()
    private var opened: Int = 0
    private var openeda: Int = mValue.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var va = LayoutInflater.from(parent.context).inflate(R.layout.row2,parent,false)
        return ViewHolder(va)
    }

    override fun getItemCount(): Int {
        return mValue.size
    }
    fun lastItem():MutableList<Int>{
        lastItem.add(mValue.size)
        return lastItem
    }

    override fun onBindViewHolder(holder: ForecastsRecyclerViewAdapter.ViewHolder, position: Int) {
        if(openeda!=mValue.size){
            opened = mValue.size-40
            openeda = mValue.size

        }
        if(mValue.size<=40){
            holder.bindItems(mValue[position])
            opened = mValue.size
        }else {
            if (mValue.size > 40 && opened != mValue.size-1) {
                holder.bindItems(mValue[opened])
                opened++
                println("opened"+opened+" - - "+mValue.size)
            }else{
                if(opened != mValue.size-1){

                }
            }
        }



        holder.itemView.setOnClickListener{
            mListener?.onListItemInteraction(mValue[position])

        }
    }


    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        fun bindItems(data: Forecasts){
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
        fun onListItemInteraction(item: Forecasts?)

        fun onListButtonInteraction(item: Forecasts?)
    }
    public fun updateData() {

        notifyDataSetChanged()
    }
}


