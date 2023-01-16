package com.smt.kotinsqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.smt.kotinsqliteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DataBaseHelper(this)

        binding.btnKaydet.setOnClickListener {
            val email = binding.emailText.text.toString()
            val sifre = binding.sifreText.text.toString()
            if(email.isNotEmpty() && sifre.isNotEmpty()){
                val kullanici = Kullanici(email,sifre)
                db.insertData(kullanici)
            }else{
                Toast.makeText(applicationContext, "Boş olan yerleri doldurmanız lazım!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnYazdR.setOnClickListener {
            val data = db.readData()
            binding.textView.text =""

            for(i in 0 until data.size){
                binding.textView.append(data.get(i).id.toString() + " " + data.get(i).email + " " + data.get(i).sifre +"\n" )
            }
        }
        binding.btnGuncelle.setOnClickListener {
            db.updateData()
            binding.btnYazdR.performClick()
        }
        binding.btnSil.setOnClickListener {
            db.deletedData()
            binding.btnYazdR.performClick()
        }
    }
}