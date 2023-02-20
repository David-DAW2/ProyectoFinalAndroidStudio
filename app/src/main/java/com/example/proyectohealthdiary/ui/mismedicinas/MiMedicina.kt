package com.example.proyectohealthdiary.ui.mismedicinas

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
class MiMedicina (val Nombre:String?, val Formato:String?, val Laboratorio:String?, val foto: String?, var stock: String = "Stock" ):
    Serializable , Parcelable{



    constructor() : this(null, null, "", null)

}