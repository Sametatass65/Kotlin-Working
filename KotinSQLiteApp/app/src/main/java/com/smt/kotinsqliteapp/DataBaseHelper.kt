package com.smt.kotinsqliteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception
import java.time.MonthDay

val database_name = "Veritabanim"
val table_name = "Kullanici"
val col_email = "email"
val col_sifre = "sifre"
val col_id = "id"
// veritabanının taslağı


class DataBaseHelper(var context : Context):SQLiteOpenHelper(context, database_name,null,1){
    override fun onCreate(p0: SQLiteDatabase?) {

        try {
            //val db = context.openOrCreateDatabase(database_name, Context.MODE_PRIVATE,null)

            p0?.let {
                p0.execSQL(" Create table if not exists $table_name(" +
                        "$col_id integer primary key autoincrement," +
                        "$col_email VARCHAR(256)," +
                        "$col_sifre VARCHAR(256))") // veri tabanının tablolarını oluşturduk
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //veri tabanını yükseltmek için kullanılır
    }

    fun insertData(kullanici:Kullanici){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col_email,kullanici.email)// col_emaile kullanici emmaili koy
        cv.put(col_sifre,kullanici.sifre)// col_sifre kullanici.sifredeki veriyi koy
        val sonuc = db.insert(table_name,null,cv) // işlemleri yapıp veritabanını dolduruyor

        if(sonuc == (-1).toLong()) //veri tabanı bir kayıt yapmadı başarısız
        {
            Toast.makeText(context, "Database kayıt yapılamamıştır", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Databasee kayıt işlemi başarılıdır", Toast.LENGTH_SHORT).show()
        }
    } // veri tabanına veri ekliyor
    fun readData():MutableList<Kullanici> { // veri tabanındaki verileri okumak için
        val liste: MutableList<Kullanici> = ArrayList()
        val db = this.readableDatabase
        val sorgu = "select * from " + table_name
        val sonuc = db.rawQuery(sorgu, null)

        val idColumnIndext = sonuc.getColumnIndex(col_id)
        val emailColumnIndext = sonuc.getColumnIndex(col_email)
        val sifreColumnIndext = sonuc.getColumnIndex(col_sifre)
        if (sonuc.moveToFirst()) { // imleci başlangıca al
            do {
                val kullanici = Kullanici()
                kullanici.id = sonuc.getInt(idColumnIndext)
                kullanici.email = sonuc.getString(emailColumnIndext)
                kullanici.sifre = sonuc.getString(sifreColumnIndext)
                liste.add(kullanici)
            } while (sonuc.moveToNext()) // imleci ilerlet
        }
        sonuc.close()
        return liste
    } // veri tabanındaki verileri okuyacak

    fun updateData(){
        val db = this.readableDatabase
        var cv = ContentValues()  // değişkenleri tutabileceğimiz bir değişkenimiz
        val sorgu = "select * from $table_name"
        val sonuc = db.rawQuery(sorgu,null)

        val emailColumnIndext = sonuc.getColumnIndex(col_email)

        if(sonuc.moveToFirst()){
            do{
                cv.put(col_email,sonuc.getString(emailColumnIndext)+ " " + "Güncel" )
                db.update(table_name,cv,"$col_email=?", arrayOf(sonuc.getString(emailColumnIndext)))
            }while(sonuc.moveToNext())
            db.close()
            sonuc.close()
        }
    }

    fun deletedData(){
        val db = this.writableDatabase
        db.delete(table_name,null,null)
    }

}

