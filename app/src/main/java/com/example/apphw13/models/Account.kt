package com.example.apphw13.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(@PrimaryKey(autoGenerate = true) val id : Int, val type : String, val number : String, val balance : String)