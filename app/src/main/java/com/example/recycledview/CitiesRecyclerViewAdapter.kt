package com.example.recycledview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycledview.data.Cities
import com.example.recycledview.databinding.RowBinding


class CitiesRecyclerViewAdapter(private val mValue: List<Cities>, private val mListener : CitiesRecyclerViewAdapter.onListInteractions):RecyclerView.Adapter<CitiesRecyclerViewAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.row,parent, false))
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(holder: CitiesRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.RowBinding.citiees = mValue[position]
        holder.bindItems(mValue[position])

        holder.itemView.setOnClickListener{
            mListener?.onListItemInteraction(mValue[position])
        }
    }

    inner class ViewHolder(val RowBinding: RowBinding):RecyclerView.ViewHolder(RowBinding.root){

        fun bindItems(data: Cities){

            Glide.with(itemView.context).load(data.IconID).into(itemView.findViewById(R.id.ActualWePhoto))

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


