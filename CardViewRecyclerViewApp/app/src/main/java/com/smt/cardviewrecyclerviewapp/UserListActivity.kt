package com.smt.cardviewrecyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.smt.cardviewrecyclerviewapp.databinding.ActivityMainBinding
import com.smt.cardviewrecyclerviewapp.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserListBinding
    private lateinit var list : ArrayList<User>
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = arrayListOf<User>()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("profile")

        recyclerViewAdapter = RecyclerViewAdapter(list,this)
        binding.userList.layoutManager = LinearLayoutManager(this)
        binding.userList.adapter = recyclerViewAdapter

        getData()

        binding.btnEkle.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun getData() {
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(User::class.java)
                        list.add(user!!)
                    }
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}























