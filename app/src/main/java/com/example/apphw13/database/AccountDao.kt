package com.example.apphw13.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apphw13.models.Account


@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    fun getAllAccount():List<Account>

    @Query("SELECT * FROM Account WHERE id IN (:accountId)")
    fun getAccount(accountId:Int): Account

    @Insert
    fun insertAll(vararg account: Account)

    @Query("DELETE FROM Account")
    fun deleteAll()
}