package com.example.apphw13.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.apphw13.database.AccountDao
import com.example.apphw13.database.AppDatabase
import com.example.apphw13.models.Account

object AccountRepositpry {

    var db: AppDatabase?=null
    var dao: AccountDao?=null

    fun initDB(context: Context){
        db= AppDatabase.getAppDatabase(context)
        dao=db?.accountDao()
    }

    fun insertAccount( account : Account){
        dao?.insertAll(account)
    }
    fun getAccount(id:Int): Account?{
        return dao?.getAccount(id)
    }

    fun getAccountWithNumber(number : String) : Account?{
        return dao?.getAccountWithCardNumber(number)
    }

    fun deleteAll(){
        dao?.deleteAll()
    }

    fun getAccountCount(): LiveData<Int>? {
        return dao?.getAccountCount()
    }

    fun getAccountCount2(): Int? {
        return dao?.getAccountCount2()
    }

    fun getMinId(): Int? {
        return dao?.getMinId()
    }


}