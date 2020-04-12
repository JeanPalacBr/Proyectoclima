package com.example.recycledview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycledview.data.Forecasts
import com.example.recycledview.databinding.Row2Binding


class ForecastsRecyclerViewAdapter(private val mValue: MutableList<Forecasts>, private val mListener : ForecastsRecyclerViewAdapter.onListInteractions):RecyclerView.Adapter<ForecastsRecyclerViewAdapter.ViewHolder>(){
    private var opened: Int = 0
    private var openeda: Int = mValue.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row2,parent, false))
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.Row2Binding.forecs = mValue[position]
            holder.bindItems(mValue[position])
        if(openeda!=mValue.size){
            opened = mValue.size-40
            openeda = mValue.size

        }
        if(mValue.size<=40){
            holder.bindItems(mValue[position])
            holder.Row2Binding.forecs = mValue[position]
            opened = mValue.size
        }else {
            if (mValue.size > 40 && opened != mValue.size-1) {
                holder.bindItems(mValue[opened])
                holder.Row2Binding.forecs = mValue[opened]
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


    inner class ViewHolder(val Row2Binding: Row2Binding):RecyclerView.ViewHolder(Row2Binding.root){

        fun bindItems(data: Forecasts){

            Glide.with(itemView.context).load(data.IconID).into(itemView.findViewById(R.id.ImgEstado))

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


