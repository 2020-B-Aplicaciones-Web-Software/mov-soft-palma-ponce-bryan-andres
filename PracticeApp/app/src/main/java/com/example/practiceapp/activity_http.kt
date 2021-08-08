package com.example.practiceapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class activity_http : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        //http -> METODO HTTP -> URL -> Parametros
        metodoGet()
        metodoPost()
    }

    fun metodoGet() {
        val identificadorPost = 1
        val url = "https://jsonplaceholder.typicode.com/posts/" + identificadorPost.toString()

        url
            .httpGet()
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: $error")
                    }
                    is Result.Success -> {
                        val respuestaString = result.get()
                        Log.i("http-klaxon", respuestaString)

                        //"http://jsonplaceholder.typicode.com/posts/1"   1
                        //"http://jsonplaceholder.typicode.com/posts"   muchos
                        val post = Klaxon()
                            .parse<PostHttp>(respuestaString)
                        //        .parseArray<IPostHttp>(respuestaString)
                        Log.i("http-klaxon", "${post?.body}")


                    }
                }

            }
    }

    fun metodoPost() {
        val parametros: List<Pair<String, *>> = listOf(
            "title" to "Titulo moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )

        val url = "http://jsonplaceholder.typicode.com/posts"
        url
            .httpPost(parametros)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        var error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "Error: ${postString}")

                        val post = Klaxon()
                            .parse<PostHttp>(postString)
                        Log.i("http-klaxon", "${post?.title}")
                    }
                }

            }
    }
}