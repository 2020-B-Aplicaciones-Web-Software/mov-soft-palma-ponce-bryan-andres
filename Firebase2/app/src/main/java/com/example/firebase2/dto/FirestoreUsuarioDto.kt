package com.example.firebase2.dto

data class FirestoreUsuarioDto (
    var uid: String = "",
    var email: String = "",
    var roles: ArrayList<String> = arrayListOf()
) {

}