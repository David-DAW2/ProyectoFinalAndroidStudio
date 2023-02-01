package com.example.proyectohealthdiary.ui

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medicina(val nombre:String, val miligramos:String, val foto: String?): Parcelable{

}