package com.smt.recyclerviewcardviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.smt.recyclerviewcardviewapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var database : FirebaseDatabase ?= null
    var dataBaseReference :DatabaseReference ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database =FirebaseDatabase.getInstance()
        dataBaseReference = database?.reference!!.child("profile")
        dataBaseReference?.let { dataBaseReference->

            binding.btnKaydet.setOnClickListener {
                val ad_soyad = binding.adveSoyadTextView.text.toString().trim()
                val yas = binding.yasTextView.text.toString().trim()
                val yorum = binding.yorumTextView.text.toString().trim()

                if(ad_soyad.isEmpty()){
                    binding.adveSoyadTextView.error ="Lütfen Ad ve Soyadınızı giriniz"
                    return@setOnClickListener
                }else if(yas.isEmpty()){
                    binding.yasTextView.error ="Lütfen Yaşınızı Giriniz"
                    return@setOnClickListener
                }else if (yorum.isEmpty()){
                    binding.yorumTextView.error ="Lütfen Kendiniz Hakkında Bilgi Veriniz"
                }

                val id = dataBaseReference.push()
                id.child("id").setValue(id.key.toString())
                id.child("adisoyadi").setValue(ad_soyad)
                id.child("yas").setValue(yas)
                id.child("yorum").setValue(yorum)
                Toast.makeText(this, "Kayit Başarılı", Toast.LENGTH_SHORT).show()

            }
            binding.btnKayitlariGoster.setOnClickListener {
                val intent = Intent(this,UserListActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.mainmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.kayitliKullanicilar){
            val intent = Intent(this,UserListActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }*/
}











































