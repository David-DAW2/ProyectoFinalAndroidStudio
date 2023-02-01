package com.example.proyectohealthdiary.ui.server

data class Resultado(
    val biosimilar: Boolean,
    val comerc: Boolean,
    val conduc: Boolean,
    val cpresc: String,
    val docs: List<Doc>,
    val dosis: String,
    val ema: Boolean,
    val estado: Estado,
    val formaFarmaceutica: FormaFarmaceutica,
    val formaFarmaceuticaSimplificada: FormaFarmaceuticaSimplificada,
    val fotos: List<Foto>,
    val generico: Boolean,
    val huerfano: Boolean,
    val labtitular: String,
    val materialesInf: Boolean,
    val nombre: String,
    val nosustituible: Nosustituible,
    val notas: Boolean,
    val nregistro: String,
    val psum: Boolean,
    val receta: Boolean,
    val triangulo: Boolean,
    val viasAdministracion: List<ViasAdministracion>,
    val vtm: Vtm
)