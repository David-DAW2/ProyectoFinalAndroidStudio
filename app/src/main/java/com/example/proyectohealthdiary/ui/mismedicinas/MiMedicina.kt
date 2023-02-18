package com.example.proyectohealthdiary.ui.mismedicinas

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
class MiMedicina (val nombre:String?, val Formato:String?, val Laboratorio:String? ,val foto: String?):
    Serializable , Parcelable{
    constructor() : this(null, null, "", null)

}