package Fisica2

import Fisica2.mru.*
import java.util.Scanner

fun mainKt() {
    val input = Scanner(System.`in`)

    println("ingrese los datos si no tiene alguno ingrese null")
    println("ingrese el numero con unidades :")

    println("Ingrese la velocidad con unidad: ")
    velocidad(input)

    println("Ingrese el tiempo con unidad: ")
    tiempo(input)

    println("Ingrese la distancia con unidad: ")
    distancia(input)

    mostrarDatos();
}