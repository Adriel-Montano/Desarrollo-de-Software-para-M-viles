data class Producto(val nombre: String, var cantidad: Int, var precio: Double)

class Inventario {
    private val productos = mutableListOf<Producto>()

    fun agregarProducto(nombre: String, cantidad: Int, precio: Double) {
        val producto = Producto(nombre, cantidad, precio)
        productos.add(producto)
        println("Producto '$nombre' agregado exitosamente.")
    }

    fun mostrarInventario() {
        if (productos.isEmpty()) {
            println("El inventario está vacío.")
            return
        }

        println("\n=== Inventario Actual ===")
        productos.forEachIndexed { index, producto ->
            println("${index + 1}. Nombre: ${producto.nombre}, Cantidad: ${producto.cantidad}, Precio: $${producto.precio}")
        }
    }

    fun buscarProducto(nombre: String) {
        val resultados = productos.filter { it.nombre.contains(nombre, ignoreCase = true) }

        if (resultados.isEmpty()) {
            println("No se encontraron productos con el nombre '$nombre'.")
            return
        }

        println("\n=== Productos Encontrados ===")
        resultados.forEachIndexed { index, producto ->
            println("${index + 1}. Nombre: ${producto.nombre}, Cantidad: ${producto.cantidad}, Precio: $${producto.precio}")
        }
    }
}

fun main() {
    val inventario = Inventario()

    while (true) {
        println("\n=== Sistema de Gestión de Inventario ===")
        println("1. Agregar producto")
        println("2. Mostrar inventario")
        println("3. Buscar producto")
        println("4. Salir")
        print("Seleccione una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Ingrese el nombre del producto: ")
                val nombre = readLine() ?: ""
                print("Ingrese la cantidad: ")
                val cantidad = readLine()?.toIntOrNull() ?: 0
                print("Ingrese el precio: ")
                val precio = readLine()?.toDoubleOrNull() ?: 0.0

                if (nombre.isNotBlank() && cantidad >= 0 && precio >= 0.0) {
                    inventario.agregarProducto(nombre, cantidad, precio)
                } else {
                    println("Datos inválidos. Intente nuevamente.")
                }
            }
            2 -> inventario.mostrarInventario()
            3 -> {
                print("Ingrese el nombre a buscar: ")
                val nombre = readLine() ?: ""
                inventario.buscarProducto(nombre)
            }
            4 -> {
                println("¡Gracias por usar el sistema!")
                break
            }
            else -> println("Opción inválida. Intente nuevamente.")
        }
    }
}