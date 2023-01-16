package com.smt.cardviewrecyclerviewapp

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.contracts.contract
import kotlin.coroutines.coroutineContext

class RecyclerViewAdapter(val list : ArrayList<User> , val context: Context):
    RecyclerView.Adapter<RecyclerViewAdapter.UserVh>() {
    class UserVh(itemView: View):RecyclerView.ViewHolder(itemView) {
        val adsoyad : TextView = itemView.findViewById(R.id.ad_soyad_textview)
        val yas : TextView = itemView.findViewById(R.id.yas_textview)
        val cardView : CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVh {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item_list_recycler_row,parent,false)
        return UserVh(view)
    }

    override fun onBindViewHolder(holder: UserVh, position: Int) {
        holder.adsoyad.text = list[position].adisoyadi
        holder.yas.text = list[position].yas

        val user = list[position]

        val hakkida  = user.yorum
        val adsoyad = user.adisoyadi

        holder.cardView.setOnClickListener {
            val intent = Intent(context ,HakkindaActivity::class.java)
            intent.putExtra("hakkinda", hakkida)
            intent.putExtra("adsoyad",adsoyad)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}