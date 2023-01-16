package com.smt.kotinsqliteapp

class Kullanici {
    var id :Int = 0
    var email: String =""
    var sifre: String =""

    constructor(email : String , sifre : String){
        this.email = email
        this.sifre = sifre
    }

    constructor(){

    }
}