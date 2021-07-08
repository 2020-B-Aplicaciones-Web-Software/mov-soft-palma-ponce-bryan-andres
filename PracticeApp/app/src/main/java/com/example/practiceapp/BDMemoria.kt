package com.example.practiceapp

class BDMemoria {
    companion object {
        val arregloEntrenador= arrayListOf<BEntrenador>()

        init{
            arregloEntrenador
                .add(
                    BEntrenador("Adrian","aaaa",null)
                )
            arregloEntrenador
                .add(
                    BEntrenador("Vicente","ccc",null)
                )
            arregloEntrenador
                .add(
                    BEntrenador("Andres","bbb",null)
                )
        }
    }
}