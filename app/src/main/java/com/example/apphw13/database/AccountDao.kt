package com.example.apphw13.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apphw13.models.Account


@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    fun getAllDoctor():List<Account>

    @Query("SELECT * FROM Account WHERE id IN (:accountId)")
    fun getDoctor(accountId:Int): Account

    @Insert
    fun insertAll(vararg doctor: Account)
}