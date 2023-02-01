package com.example.proyectohealthdiary.ui.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET ("https://cima.aemps.es/cima/rest/medicamentos?*")
    suspend fun getMed(@Query("api_key")apiKey: String): MedicinaRemoteResult
}