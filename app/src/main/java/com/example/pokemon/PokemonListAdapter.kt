package com.example.pokemon

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.data.PokemonDetailsData

class PokemonListAdapter(private var context: Context,
                         private var mPokemonNameList: ArrayList<String>,
                         private var mPokemonDetailsDataList: ArrayList<PokemonDetailsData>,
                         private var listener: OnItemClickListener
)
: RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    var pokemonDetailsDataList: List<PokemonDetailsData> = emptyList()

    companion object {
        val TAG: String = PokemonListAdapter::class.java.simpleName
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPokemonName: TextView = itemView.findViewById(R.id.tv_pokemon_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "item count is : ${this.mPokemonNameList.size}")
        return this.mPokemonNameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder -> data : {${this.mPokemonNameList[position]}")
        Log.d(TAG, "Name of pokemon is: ${this.mPokemonNameList[position]}")
        holder.tvPokemonName.text = this.mPokemonNameList[position]
        holder.itemView.setOnClickListener {
            listener.onItemClick(mPokemonDetailsDataList[position])
        }


    }

    // method for refreshing recyclerview items.
    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(filterList: ArrayList<String>) {
        this.mPokemonNameList = filterList
        notifyDataSetChanged()
    }
}

interface OnItemClickListener {
    fun onItemClick(item:PokemonDetailsData)
}