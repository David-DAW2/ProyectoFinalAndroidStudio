package com.example.proyectohealthdiary.ui.server
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RemoteResult(
    val results: List<Medicina>?



)
@Parcelize
data class Medicina(
    @SerializedName("nombre") val nombre: String?,
                        @SerializedName("cpresc") val receta: Boolean?,
                        @SerializedName("formaFarmaceuticaSimplificada.nombre") val nomFormaFarmaceutica : String?,
                        @SerializedName("labtitular") val nombreLab:String?,
                        @SerializedName("fotos.url") val foto:String?,
    val dosis:String?
):Parcelable