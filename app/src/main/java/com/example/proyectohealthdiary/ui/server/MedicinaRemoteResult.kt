package com.example.proyectohealthdiary.ui.server

data class MedicinaRemoteResult(
    val pagina: Int,
    val resultados: List<Resultado>,
    val tamanioPagina: Int,
    val totalFilas: Int
)