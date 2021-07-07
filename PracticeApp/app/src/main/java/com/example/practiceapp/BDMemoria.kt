package com.example.practiceapp

class BDMemoria {
    companion object {
        val arregloEntrenador= arrayListOf<BEntrenador>()

        init{
            arregloEntrenador
                .add(
                    BEntrenador("Adrian","aaaa")
                )
            arregloEntrenador
                .add(
                    BEntrenador("Vicente","ccc")
                )
            arregloEntrenador
                .add(
                    BEntrenador("Andres","bbb")
                )
        }
    }
}