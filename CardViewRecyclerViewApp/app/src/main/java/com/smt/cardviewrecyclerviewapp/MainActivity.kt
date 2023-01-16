package com.smt.cardviewrecyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.smt.cardviewrecyclerviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("profile")

        binding.btnKaydet.setOnClickListener {
            val adisoyadi = binding.adveSoyadTextView.text.toString()
            val yas = binding.yasTextView.text.toString()
            val yorum = binding.yorumTextView.text.toString()

            if (adisoyadi.isEmpty()){
                binding.adveSoyadTextView.error ="Lütfen Adınızı ve Soyadınızı Giriniz."
                return@setOnClickListener
            }else if(yas.isEmpty()){
                binding.yasTextView.error ="Lütfen Yaşınızı Giriniz."
                return@setOnClickListener
            }else if (yorum.isEmpty()){
                binding.yorumTextView.error ="Lütfen Kendinizle ilgili Kısa Bir Bilgi Veriniz."
            }else{
                val id = databaseReference.push()
                id.child("adisoyadi").setValue(adisoyadi)
                id.child("yas").setValue(yas)
                id.child("yorum").setValue(yorum)
                Toast.makeText(this, "Ekleme işlemi başarılı bir şekilde gerçekleşti", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.kayitmenu){
            val intent = Intent(this,UserListActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}



























