import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


var activo = true
val gestorAutor = AutorArchivo()
val gestorLibro = LibroArchivo()
val scanner = Scanner(System.`in`)
val headerLibro="N°\tTítulo\t\t\t\t\t\tAutor\t\t\tPrecio\t\tN° de Pags\t\tDisponibilidad\n"
val headerAutor="N°\tNombre\t\tApellido\tPais\t\tFecha de nacimiento\n"
fun main() {
    var opcion = "0"
    println(
        "Seleccione el archivo:\n" +
                "1. Libro\n" +
                "2. Autor\n" +
                "3. Salir"
    )
    opcion = scanner.nextLine()
    while (activo) {
        when (opcion) {

            ("1") -> {
                menuLibro()
                println(
                    "Seleccione el archivo:\n" +
                            "1. Libro\n" +
                            "2. Autor\n" +
                            "3. Salir"
                )
                opcion = scanner.nextLine()
            }
            ("2") -> {
                menuAutor()
                println(
                    "Seleccione el archivo:\n" +
                            "1. Libro\n" +
                            "2. Autor\n" +
                            "3. Salir"
                )
                opcion = scanner.nextLine()
            }

            else -> {
                activo = false
            }

        }

    }


}

fun menuLibro() {
    var opcion: String
    var activo = true
    var nombre_libro: String
    var num_pags: Int
    var precio: Double
    var disponible = true
    var aux: Int
    var autor: Autor
    var id: Int
    var libro: Libro
    print(headerLibro)
    gestorLibro.verLibros().forEach { print(it) }
    println(
        "Seleccione la accion a realizar:\n" +
                "1. Agregar\n" +
                "2. Actualizar\n" +
                "3. Borrar\n" +
                "4. Salir"
    )
    opcion = scanner.nextLine()
    while (activo) {
        when (opcion) {
            ("1") -> {
                print("Ingrese el Titulo del libro: ")
                nombre_libro = scanner.nextLine()
                println("Seleccione el autor del libro: ")
                gestorAutor.verAutores().forEach {
                    println("${it.getID()} - ${it.getNombre()} ${it.getApellido()}")
                }
                autor = gestorAutor.bucarAutorPorID(scanner.nextInt())
                println("Ingrese el número de paginas: ")
                num_pags = scanner.nextInt()
                println("Ingrese el precio: ")
                precio = scanner.nextDouble()
                println("¿Está disponible?\n1.Si\n2.No")
                aux = scanner.nextInt()
                if (aux != 1) {
                    disponible = false
                }
                if (gestorLibro.crearLibro(
                        Libro(
                            gestorLibro.verLibros().last().getID()?.plus(1),
                            nombre_libro,
                            autor,
                            precio,
                            num_pags,
                            disponible
                        )
                    )
                ) {
                    println("Libro añadido")
                } else {
                    println("Error al añadir")
                }
                print(headerLibro)
                gestorLibro.verLibros().forEach { print(it) }
                "Seleccione la accion a realizar:\n" +
                        "1. Agregar\n" +
                        "2. Actualizar\n" +
                        "3. Borrar\n" +
                        "4. Salir"

                opcion = scanner.nextLine()
            }
            ("2") -> {
                println("Seleccione el libro a actualizar (presione 0 para cancelar): ")
                print(headerLibro)
                gestorLibro.verLibros().forEach { print(it) }
                id = scanner.nextInt()
                if (id != 0) {
                    libro = gestorLibro.bucarLibroPorID(id)
                    print("Ingrese el nuevo Titulo del libro: ")
                    nombre_libro = scanner.nextLine()
                    nombre_libro = scanner.nextLine()
                    println("Seleccione el  nuevo autor del libro: ")
                    gestorAutor.verAutores().forEach {
                        println("${it.getID()} - ${it.getNombre()} ${it.getApellido()}")
                    }
                    autor = gestorAutor.bucarAutorPorID(scanner.nextInt())
                    println("Ingrese el nuevo número de paginas: ")
                    num_pags = scanner.nextInt()
                    println("Ingrese el nuevo precio: ")
                    precio = scanner.nextDouble()
                    println("¿Está disponible?\n1.Si\n2.No")
                    aux = scanner.nextInt()
                    if (aux != 1) {
                        disponible = false
                    }
                    if (gestorLibro.actualizarLibro(
                            id, Libro(
                                id,
                                nombre_libro,
                                autor,
                                precio,
                                num_pags,
                                disponible
                            )
                        )
                    ) {
                        println("Libro Actualizado")
                    } else {
                        println("Error al añadir")
                    }
                    print(headerLibro)
                    gestorLibro.verLibros().forEach { print(it) }
                }

                println(
                    "Seleccione la accion a realizar:\n" +
                            "1. Agregar\n" +
                            "2. Actualizar\n" +
                            "3. Borrar\n" +
                            "4. Salir"
                )
                opcion = scanner.nextLine()
            }
            ("3") -> {
                println("Seleccione el libro a eliminar")
                print(headerLibro)
                gestorLibro.verLibros().forEach { print(it) }
                id = scanner.nextInt()
                if (gestorLibro.eliminarLibro(id)) {
                    println("Libro eliminado")
                } else {
                    println("Error al eliminar")
                }
                print(headerLibro)
                gestorLibro.verLibros().forEach { print(it) }
                println(
                    "Seleccione la accion a realizar:\n" +
                            "1. Agregar\n" +
                            "2. Actualizar\n" +
                            "3. Borrar\n" +
                            "4. Salir"
                )
                opcion = scanner.nextLine()
            }
            else -> {
                activo = false
            }

        }
    }
}

fun menuAutor() {
    var opcion = "0"
    var activo = true
    var id = 0
    var nombre: String
    var apellido: String
    var pais: String
    var fecha: LocalDate
    print(headerAutor)
    gestorAutor.verAutores().forEach { print(it) }
    println(
        "Seleccione la accion a realizar:\n" +
                "1. Añadir un autor\n" +
                "2. Actualizar un autor\n" +
                "3. Eliminar un autor\n" +
                "4. Ver los libros de un autor\n" +
                "5. Salir\n"
    )
    opcion = scanner.nextLine()
    while (activo) {
        when (opcion) {
            ("1") -> {
                println("Ingrese el nombre del autor: ")
                nombre = scanner.nextLine()
                println("Ingrese el apellido del autor: ")
                apellido = scanner.nextLine()
                println("Ingrese el pais del autor: ")
                pais = scanner.nextLine()
                println("Ingrese el fecha de nacimiento del autor (yyyy-MM-dd): ")
                fecha = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_DATE)
                if (gestorAutor.crearAutor(
                        Autor(
                            gestorAutor.verAutores().last().getID()?.plus(1),
                            nombre,
                            apellido,
                            pais,
                            fecha
                        )
                    )
                ) {

                    println("Autor actualizado exitosamente")
                    print(headerAutor)
                    gestorAutor.verAutores().forEach { print(it) }
                } else {
                    println("Error al actualizar")
                }

                println(
                    "Seleccione la accion a realizar:\n" +
                            "1. Añadir un autor\n" +
                            "2. Actualizar un autor\n" +
                            "3. Eliminar un autor\n" +
                            "4. Ver los libros de un autor\n" +
                            "5. Salir\n"
                )
                opcion = scanner.nextLine()
            }
            ("2") -> {
                println("Seleccione el autor a actualizar (presione 0 para cancelar): ")
                print(headerAutor)
                gestorAutor.verAutores().forEach { print(it) }
                id = scanner.nextInt()
                if (id != 0) {
                    println("Ingrese el nuevo nombre del autor: ")
                    nombre = scanner.nextLine()
                    nombre = scanner.nextLine()
                    println("Ingrese el nuevo apellido del autor: ")
                    apellido = scanner.nextLine()
                    println("Ingrese el nuevo pais del autor: ")
                    pais = scanner.nextLine()
                    println("Ingrese la fecha de nacimiento actualizada del autor (yyyy-MM-dd): ")
                    fecha = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_DATE)
                    if (gestorAutor.actualizarAutor(
                            id,
                            Autor(
                                id,
                                nombre,
                                apellido,
                                pais,
                                fecha
                            )
                        )
                    ) {
                        println("Autor actualizado exitosamente\n")
                        print(headerAutor)
                        gestorAutor.verAutores().forEach { print(it) }
                    } else {
                        println("Error al actualizar")
                    }
                }
                print(headerAutor)
                gestorAutor.verAutores().forEach { print(it) }

                println(
                    "Seleccione la accion a realizar:\n" +
                            "1. Añadir un autor\n" +
                            "2. Actualizar un autor\n" +
                            "3. Eliminar un autor\n" +
                            "4. Ver los libros de un autor\n" +
                            "5. Salir\n"
                )
                opcion = scanner.nextLine()
            }
            ("3") -> {
                println("Seleccione el autor a eliminar")
                print(headerAutor)
                gestorAutor.verAutores().forEach { print(it) }
                id = scanner.nextInt()
                if (gestorAutor.eliminarAutor(id)) {
                    println("Autor eliminado")
                } else {
                    println("Error al eliminar")
                }
                print(headerAutor)
                gestorAutor.verAutores().forEach { print(it) }
                println(
                    "Seleccione la accion a realizar:\n" +
                            "1. Añadir un autor\n" +
                            "2. Actualizar un autor\n" +
                            "3. Eliminar un autor\n" +
                            "4. Ver los libros de un autor\n" +
                            "5. Salir\n"
                )
                opcion = scanner.nextLine()
            }
            ("4") -> {
                println("Seleccione el autor a visualizar")
                print(headerAutor)
                gestorAutor.verAutores().forEach { print(it) }
                id = scanner.nextInt()
                gestorLibro.bucarLibrosPorAutor(id).forEach {
                    print(it)
                }
                println(
                    "Seleccione la accion a realizar:\n" +
                            "1. Añadir un autor\n" +
                            "2. Actualizar un autor\n" +
                            "3. Eliminar un autor\n" +
                            "4. Ver los libros de un autor\n" +
                            "5. Salir\n"
                )
                opcion = scanner.nextLine()
            }
            else -> {
                activo = false
            }

        }
    }
}
