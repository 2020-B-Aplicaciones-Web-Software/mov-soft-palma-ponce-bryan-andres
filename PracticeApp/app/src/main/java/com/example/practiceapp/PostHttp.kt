package com.example.practiceapp

class PostHttp(
    val id: Int,
    var userId: Any,
    val title: String,
    var body:String
) {
    init{
        if(userId is String){
            userId=(userId as String).toInt()
        }
        if (userId is Int){
            userId=userId
        }
    }

}