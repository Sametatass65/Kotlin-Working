package com.smt.cardviewrecyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.*
import com.smt.cardviewrecyclerviewapp.databinding.ActivityHakkindaBinding

class HakkindaActivity : AppCompatActivity() {
    lateinit var binding : ActivityHakkindaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHakkindaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val hakkinda =  intent.getStringExtra("hakkinda")
        val adsoyad = intent.getStringExtra("adsoyad")
        binding.yorumtextView.text = hakkinda
        binding.adiSoyadTextVEw.text = adsoyad

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.hakkindamenu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.hakindamenu){
            val intent = Intent(this,UserListActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}