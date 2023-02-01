package com.example.proyectohealthdiary.ui.server

data class Doc(
    val fecha: Long,
    val secc: Boolean,
    val tipo: Int,
    val url: String,
    val urlHtml: String
)