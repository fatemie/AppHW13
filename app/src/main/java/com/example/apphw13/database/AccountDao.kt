package com.example.apphw13.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.apphw13.models.Account


@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    fun getAllAccount():List<Account>

    @Query("SELECT * FROM Account WHERE id IN (:accountId)")
    fun getAccount(accountId:Int): Account?

    @Insert
    fun insertAll(vararg account: Account)

    @Query("DELETE FROM Account")
    fun deleteAll()

    @Delete
    fun delete(vararg account: Account)


    @Query("SELECT * FROM Account WHERE number IN (:cardNumber)")
    fun getAccountWithCardNumber(cardNumber :String): Account

    @Query("SELECT COUNT (*) FROM Account")
    fun getAccountCount(): LiveData<Int>

    @Query("SELECT COUNT (*) FROM Account")
    fun getAccountCount2(): Int

    @Query("SELECT MIN(id) FROM Account" )
    fun getMinId():Int?
}