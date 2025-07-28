package Fisica2;

import java.util.Scanner;

import static Fisica2.mru.*;

public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("ingrese los datos si no tiene alguno ingrese null");
        System.out.println("ingrese el numero con unidades :");

        System.out.println("Ingrese la velocidad con unidad: ");
        velocidad(in);

        System.out.println("Ingrese el tiempo con unidad: ");
        tiempo(in);

        System.out.println("Ingrese la distancia con unidad: ");
        distancia(in);

        mostrarDatos();
    }
}
