import java.util.*
import kotlin.collections.ArrayList

fun  main() {
    println("HOLA MUNDO")


    var edadProfesor: Int = 31
    var  sueldoProfesor: Double = 12.1
    // Mutables
    var edadCachorro =0
    edadCachorro=1
    edadCachorro=2
    edadCachorro=3

    //INMUTABLES
    val numeroCedula = 17126381849


    // Tipos de variables(Son los mismos tipo de variables que se tienen en JAVA)
    val nombreAlumno: String = "Edison Quimbiamba"
    val sueldo: Double = 15.5
    val estadoCivil: Char ='S'
    val fechaNacimiento: Date =Date()
    val casado: Boolean = false
    // Cualquier tipo de variable que usemos en java  ya lo podemos usar aki

    //Condicionales//

    when( estadoCivil){
        'C'-> println("huir")
        'S'-> println("Conversar ")
        'N' -> println("Nada")
        else -> {
            println("No tiene estado civil ")
        }
    }
    val sueldoMayorEstablecido  = if(sueldo > 12.2) 500 else 0
    println(sueldoMayorEstablecido)

    //Funciones

    imprimitNombre("Edison Quimbiamba")
    calcularSueldo(100.00, 15.00)
    calcularSueldo(100.00, 14.00)
    calcularSueldo(100.00,14.00,25)

    calcularSueldo(sueldo
    = 15.00,bono=16,tasa=15.0)

    //Arreglos estáticos y dinamicos

    val arregloEstatico:Array<Int> = arrayOf(1,2,3) // nosotros no podemos aumentar y disminuir elemetos

    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)

    println(arregloDinamico)

    arregloDinamico.add(11)
    arregloDinamico.add(12)

    println("Arreglo modificado ${arregloDinamico}")



    //OPERADORES  --> Sirven para los arreglos estaticos y dinámicos

    val respuestaForEach = arregloDinamico.forEach { valorActual -> println("Valor actual ${valorActual}") }

    arregloDinamico.forEach{println("valor actual ${it}")}

    //Cualquier operador tiene su mismo  operador index

    arregloDinamico.forEachIndexed{
            indice, valoractual ->
        println("valor actural ${valoractual}, indice actual ${indice}")
    }


    //Lo que nos permite es mutar el arreglo
    // Mao -> List <....>  nos devuelve  el arrego mutado
    //Enviemos el nuevo valor de la iteración
    // Nos devuelve es un nuevo arreglo con los valores modificados

    val respuestaMap  = arregloDinamico.map {
            valorActual -> return@map valorActual.toDouble() + 100.00
    }

    println(respuestaMap)

    val respuestaFechas = arregloDinamico.map {
            valorActual -> return@map Date()

    }

    println(respuestaFechas) //

    //filter --Nos ayuda a filtrar los elementos del arreglo


    val respuestaFilter = arregloDinamico.filter {
            valorActual->
        val mayoresACinco = valorActual>5
        return@filter mayoresACinco //Bolean
    }

    println(respuestaFilter)


    //or and ANY (aLGUNO CUMPLE )
    //and --> ALL TODOS CUMPLEN


    val respuestaAny = arregloDinamico.any { valorActual ->
        return@any (valorActual > 5) //true
    }
    println(respuestaAny)
    val respuestaAll =arregloDinamico.all {

            valorActual ->
        return@all (valorActual>5) // false
    }
    println(respuestaAll)


    //REDUCE -> tenemos el valor acumulado
    // Valor acumulado =0
    //[1,2,,4,5] --> sumeme todos los valores del arreglo
    // Valor inicial


    val respuestaReduce  = arregloDinamico.reduce{

            acumulado, valorActual ->
        return@reduce (acumulado+valorActual)  // tenemos la logica de negocio
    }

    val arregloInicial = arrayListOf<Int>(12,15,8,10)
    val respuestaReduceFold = arregloInicial.fold(
        100, // acumulado inicial
        {
                acumulado, valorActualIteración ->
            return@fold acumulado- valorActualIteración
        }
    )
    println(respuestaReduceFold)


    val vidaActual = arregloDinamico.
    map {it*2.3 }
        .filter { it>20 }
        .fold(100.00,{
                acc,i-> acc-i })
        .also { println(it) }
    println(vidaActual)

    val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1,null)
    val ejemploCuatro = Suma(null,null)

    println(ejemploUno.sumar())
    println(ejemploDos.sumar())
    println(ejemploTres.sumar())
    println(ejemploCuatro.sumar())





}// FIN MAIN

fun imprimitNombre(nombre:String) {
    println("Nombre ${nombre}") // Template String
}

fun calcularSueldo(
    sueldo: Double, //Parametro requerido los que se usan normalmente
    tasa: Double = 12.00, //opcionales al tener un valor por defecto es opcional
    bono: Int? = null  // variables que pueden ser nulas
):Double {
    if (bono != null) {
        return sueldo * (100 / tasa) + bono

    }else{
        return  sueldo*(100/tasa)
    }
}



abstract  class  NumeroJava{
    protected val numeroUno: Int
    private val numeroDos:Int

    constructor(uno: Int, dos: Int){
        numeroUno= uno
        numeroDos=uno
    }

    //instacias.numero

}

abstract  class  Numeros(
    protected  var numeroUno: Int,
    protected  var numeroDos: Int
){
    init {
        //Bloque inicio del constructor primario
        println("Inicialización")
    }
}

class Suma(
    uno: Int,
    dos: Int
): Numeros(
    uno,dos
){
    init {
        this.numeroUno
        this.numeroDos
    }
    constructor(
        uno: Int?, // parametros
        dos: Int // parametros
    ):this(
        if(uno ==null)0 else uno,dos
    )

    constructor(
        uno: Int, // parametros
        dos: Int? // parametros
    ):this( uno,
        if(dos ==null)0 else dos
    )

    constructor(
        uno: Int?, // parametros
        dos: Int? // parametros
    ):this(
        if(uno ==null) 0 else uno,
        if (dos == null) 0 else  dos,
    )

    fun sumar(): Int {
        val total: Int = numeroUno + numeroDos
        return total
    }

    //SINGLETO

    companion object{
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma : Int){
            this.historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }


}