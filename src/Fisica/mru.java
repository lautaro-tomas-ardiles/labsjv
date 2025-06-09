package Fisica;

import java.util.Scanner;
import java.util.HashMap;

import static Fisica.operations.*;

public class mru {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        HashMap<String, Double> valores = new HashMap<>();
        HashMap<String, String> unidades = new HashMap<>();

        String velocidad, tiempo, distancia;

        System.out.println("ingrese los datos si no tiene alguno ingrese null");
        System.out.println("ingrese el numero con unidades :");

        // Velocidad
        while (true) {
            try {
                System.out.print("\nIngrese la velocidad con unidad: ");
                velocidad = in.nextLine();
                procesarEntrada(velocidadTexto, velocidad, valores, unidades);
                break;
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        // Tiempo
        while (true) {
            try {
                System.out.print("Ingrese el tiempo con unidad: ");
                tiempo = in.nextLine();
                procesarEntrada(tiempoTexto, tiempo, valores, unidades);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Distancia
        while (true) {
            try {
                System.out.print("Ingrese la distancia con unidad: ");
                distancia = in.nextLine();
                procesarEntrada(distanciaTexto, distancia, valores, unidades);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        in.close();
        System.out.println("\nDatos procesados:");

        for (String clave : valores.keySet()) {
            if (valores.get(clave) != null) {
                System.out.println(clave + ": " + valores.get(clave) + " " + unidades.get(clave));
            }
        }

        //calculo velocidad
        boolean calculoDeVelocidad = valores.get(velocidadTexto) == null && valores.get(distanciaTexto) != null && valores.get(tiempoTexto) != null;
        if (calculoDeVelocidad) {
            String velocidadResultante = velocidadMRU(
                    valores.get(distanciaTexto),
                    valores.get(tiempoTexto),
                    unidades.get(distanciaTexto),
                    unidades.get(tiempoTexto)
            );

            System.out.println("la velocidad era : " + velocidadResultante);
        }

        //calculo distancia
        boolean calculoDeDistancia = valores.get(distanciaTexto) == null && valores.get(velocidadTexto) != null && valores.get(tiempoTexto) != null;
        if (calculoDeDistancia) {
            //sí se convierte el tiempo en caso de ser necesitado
            Object[] tiempoConvertido = conversionDeTiempo(
                    unidades.get(velocidadTexto),
                    unidades.get(tiempoTexto),
                    valores.get(tiempoTexto)
            );

            unidades.put(tiempoTexto, (String) tiempoConvertido[0]);
            valores.put(tiempoTexto, (Double) tiempoConvertido[1]);

            String distanciaResultante = distanciaMRU(
                    valores.get(velocidadTexto),
                    valores.get(tiempoTexto),
                    unidades.get(velocidadTexto)
            );

            System.out.println("la distancia era : " + distanciaResultante);
        }

        //calculo tiempo
        boolean calculoDeTiempo = valores.get(tiempoTexto) == null && valores.get(distanciaTexto) != null && valores.get(velocidadTexto) != null;
        if (calculoDeTiempo) {
            Object[] distanciaConvertido = conversionDeDistancia(
                    unidades.get(velocidadTexto),
                    unidades.get(distanciaTexto),
                    valores.get(distanciaTexto)
            );

            unidades.put(distanciaTexto, (String) distanciaConvertido[0]);
            valores.put(distanciaTexto, (Double) distanciaConvertido[1]);

            String tiempoResultante = tiempoMRU(
                    valores.get(distanciaTexto),
                    valores.get(velocidadTexto),
                    unidades.get(velocidadTexto)
            );

            System.out.println("el tiempo era : " + tiempoResultante);
        }
    }
}
