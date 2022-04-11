package com.example.apphw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.apphw13.repository.AccountRepositpry

class MainViewModel(app: Application): AndroidViewModel(app) {

    //var doctorById= MutableLiveData<Doctor>()
    //val doctorList= MutableLiveData<List<Doctor>>()

    init{
        AccountRepositpry.initDB(app.applicationContext)
    }

    fun getDoctor(id:Int){
     //   doctorById.value=DoctorRepository.getDoctor(id)
    }
}