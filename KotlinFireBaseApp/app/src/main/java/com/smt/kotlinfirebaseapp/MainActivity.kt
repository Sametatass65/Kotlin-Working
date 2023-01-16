package com.smt.kotlinfirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance().reference // FireBasein referansının Instancesini oluşturduk

        btnEkle.setOnClickListener {
            val adsoyad = adText.text.toString()
            val maas = maasText.text.toString().toInt()
            val personelno = personelNoText.text.toString().toInt()

            database.child(personelno.toString()).setValue(Personel(adsoyad, maas))
        }

        val getdata = object : ValueEventListener { // listener databasdeki verileri kontrol ediyor
            override fun onDataChange(snapshot: DataSnapshot) {
                // her veri değişikliğinde çağırılır
                val sb = StringBuilder() // metin  birleştirme sınıfı
                // databasedeki verileri hepsini birleştirip bize getiricek

                for(i in snapshot.children)// snapShot = veri tabanında bulundan verileri kontrol ediyor
                {
                    val adsoyad = i.child("padsoyad").getValue()
                    val maas = i.child("pmaas").getValue()

                    sb.append("${i.key}  ${adsoyad}   ${maas}\n")
                }
                yazdirText.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.addValueEventListener(getdata) // veri eklenirse güncel olarak anında kullanıcı görücek
        database.addListenerForSingleValueEvent(getdata)
    }
}