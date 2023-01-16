package com.smt.kotlinsharedpreferenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.smt.kotlinsharedpreferenceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = this.getSharedPreferences("ProfilBilgisi", MODE_PRIVATE)

        binding.btnKaydet.setOnClickListener{

            val adSoyadi = binding.adText.text.toString()
            val yasi = binding.yasText.text.toString()
            if(adSoyadi.isNotEmpty() && yasi.isNotEmpty()){
                sharedPreferences.edit().putString("adSoyad",adSoyadi).apply()
                sharedPreferences.edit().putString("yas",yasi).apply()
                binding.adText.text.clear()
                binding.yasText.text.clear()

                Toast.makeText(applicationContext, "Kaydetme işlemi başarıyla gerçekleşmiştir", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Doldurulması gereke yerleri boş bırakmayınız", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnYazdir.setOnClickListener {

            val adSoyad = sharedPreferences.getString("adSoyad","")
            val yas = sharedPreferences.getString("yas","")

            binding.textView.setText("Ad ve Soyad: $adSoyad \n Yas: $yas")
        }

        binding.btnSil.setOnClickListener {
            sharedPreferences.edit().clear().apply()
        }
    }
}























































