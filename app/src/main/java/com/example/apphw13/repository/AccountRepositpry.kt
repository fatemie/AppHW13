package com.example.apphw13.repository

import android.content.Context
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

}