package com.example.apphw13.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apphw13.R
import com.example.apphw13.models.Account
import com.example.apphw13.repository.AccountRepositpry
import com.example.apphw13.views.ACCOUNTCOUNT

class MainViewModel(app: Application): AndroidViewModel(app) {


    var nextEnabledLiveData = MutableLiveData<Boolean>(true)
    var backEnabledLiveData = MutableLiveData<Boolean>(false)
    var number = 1
    var minId = 1
    var accountLiveData = MutableLiveData<Account>()
    lateinit var accountCountLiveData : LiveData<Int>
    var numberOfAccount = 0

    init{
        AccountRepositpry.initDB(app.applicationContext)
        accountLiveData.value = AccountRepositpry.getMinId()
            ?.let { AccountRepositpry.getAccount(it) }
        accountCountLiveData = AccountRepositpry.getAccountCount()!!
        numberOfAccount = AccountRepositpry.getAccountCount2()!!
        //minId = AccountRepositpry.getMinId()!!
    }


    fun nextClicked() {
        backEnabledLiveData.value = true
        number++
        accountLiveData.value = AccountRepositpry.getAccount(number)

        if(number == numberOfAccount + AccountRepositpry.getMinId()!! -1) {
            nextEnabledLiveData.value = false
        }
    }

    fun backClicked() {
        nextEnabledLiveData.value = true
        number--
        accountLiveData.value = AccountRepositpry.getAccount(number)
        if(number == AccountRepositpry.getMinId()) {
            backEnabledLiveData.value = false
        }
    }

    fun search(numberCard : String){
        accountLiveData.value = AccountRepositpry.getAccountWithNumber(numberCard)
    }

    fun initViews(cardList : List<CardView>, accountCount : Int ){
        for (i in 0 until accountCount){
            cardList[i].isVisible = true
        }
    }

    fun accountFirst(){
        numberOfAccount = AccountRepositpry.getAccountCount2()!!
        if(numberOfAccount != 0){
            minId = AccountRepositpry.getMinId()!!
            accountLiveData.value = AccountRepositpry.getAccount(minId!!)
            number = minId
        }
        else{
        }

    }
}