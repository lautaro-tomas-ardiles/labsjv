package Fisica2;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Se debería intentar hacer un mru que facilite el uso del resto
 * es decir tener una clase mru y una main
 */
public class mru {
    static final HashMap<tipoMagnitud, magnitud> variables = new HashMap<>();

    public static void velocidad(String entrada) {
        // Velocidad
        while (true) {
            try {
                variables.putAll(operations.procesarEntrada(tipoMagnitud.VELOCIDAD, entrada));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void distancia(String entrada) {
        while (true) {
            try {
                variables.putAll(operations.procesarEntrada(tipoMagnitud.DISTANCIA, entrada));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void tiempo(String entrada) {
        while (true) {
            try {
                variables.putAll(operations.procesarEntrada(tipoMagnitud.TIEMPO, entrada));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mostrarDatos() {
        System.out.println("\nDatos procesados:");
        //datos dados
        for (tipoMagnitud clave : variables.keySet()) {
            magnitud valor = variables.get(clave);
            if (valor.valor() != null) {
                System.out.println(clave.toString().toLowerCase() + ": " + valor.valor() + " " + valor.unidad());
            }
        }
        // datos calculados
        boolean noVelocidad = variables.get(tipoMagnitud.VELOCIDAD).valor() == null
                && variables.get(tipoMagnitud.DISTANCIA).valor() != null
                && variables.get(tipoMagnitud.TIEMPO).valor() != null;
        if (noVelocidad) {
            String velocidadCalculada = operations.velocidadMRU(variables.get(tipoMagnitud.DISTANCIA), variables.get(tipoMagnitud.TIEMPO));
            System.out.println("Velocidad : " + velocidadCalculada);
        }

        boolean noDistancia = variables.get(tipoMagnitud.DISTANCIA).valor() == null
                && variables.get(tipoMagnitud.VELOCIDAD).valor() != null
                && variables.get(tipoMagnitud.TIEMPO).valor() != null;
        if (noDistancia) {
            magnitud distanciaConvertida = operations.convertirTiempo(variables.get(tipoMagnitud.TIEMPO), variables.get(tipoMagnitud.VELOCIDAD));
            variables.replace(tipoMagnitud.TIEMPO, distanciaConvertida);
            String distanciaCalculada = operations.distanciaMRU(variables.get(tipoMagnitud.VELOCIDAD), variables.get(tipoMagnitud.TIEMPO));
            System.out.println("Distancia : " + distanciaCalculada);
        }

        boolean noTiempo = variables.get(tipoMagnitud.TIEMPO).valor() == null
                && variables.get(tipoMagnitud.VELOCIDAD).valor() != null
                && variables.get(tipoMagnitud.DISTANCIA).valor() != null;
        if (noTiempo) {
            magnitud tiempoConvertido = operations.convertirDistancia(variables.get(tipoMagnitud.DISTANCIA), variables.get(tipoMagnitud.VELOCIDAD));
            variables.replace(tipoMagnitud.DISTANCIA, tiempoConvertido);
            String tiempoCalculado = operations.tiempoMRU(variables.get(tipoMagnitud.VELOCIDAD), variables.get(tipoMagnitud.DISTANCIA));
            System.out.println("Tiempo : " + tiempoCalculado);
        }
    }

    public static void main(String[] args) {

        String velocidadEntrada, tiempoEntrada, distanciaEntrada;

        Scanner in = new Scanner(System.in);

        System.out.println("ingrese los datos si no tiene alguno ingrese null");
        System.out.println("ingrese el numero con unidades :");

        System.out.print("\nIngrese la velocidad con unidad: ");
        velocidadEntrada = in.nextLine();

        velocidad(velocidadEntrada);

        System.out.print("\nIngrese el tiempo con unidad: ");
        tiempoEntrada = in.nextLine();

        tiempo(tiempoEntrada);

        System.out.print("\nIngrese la distancia con unidad: ");
        distanciaEntrada = in.nextLine();

        distancia(distanciaEntrada);

        mostrarDatos();
    }
}
