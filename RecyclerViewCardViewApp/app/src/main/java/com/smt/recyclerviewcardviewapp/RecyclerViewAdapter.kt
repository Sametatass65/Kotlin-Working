package com.smt.recyclerviewcardviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smt.recyclerviewcardviewapp.databinding.ActivityUserListBinding

class RecyclerViewAdapter (val list : ArrayList<User>):RecyclerView.Adapter<RecyclerViewAdapter.UserVH>() {
    class UserVH(itemView : View):RecyclerView.ViewHolder(itemView) {
        val adsoyad : TextView = itemView.findViewById(R.id.ad_soyad_textview)
        val yas : TextView = itemView.findViewById(R.id.yas_textview)
        val hakkinda : TextView = itemView.findViewById(R.id.hakkinizda_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item_list_recycler_row,parent,false)
        return UserVH(view)
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.adsoyad.text = list[position].adiSoyadi
        holder.yas.text = list[position].yas
        holder.hakkinda.text = list[position].hakkinda
    }

    override fun getItemCount(): Int {
        return list.size
    }
}