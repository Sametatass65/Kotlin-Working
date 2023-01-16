package com.smt.recyclerviewcardviewapp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.smt.recyclerviewcardviewapp.databinding.ActivityMainBinding
import com.smt.recyclerviewcardviewapp.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserListBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var List : ArrayList<User>
    private lateinit var recylerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recylerView= binding.userList
        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.setHasFixedSize(true)
        List = arrayListOf<User>()
        getData()

        binding.btnEkle.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        databaseReference = FirebaseDatabase.getInstance().reference.child("profile")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(User::class.java)
                        List.add(user!!)
                    }
                    recylerView.adapter = RecyclerViewAdapter(List)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}