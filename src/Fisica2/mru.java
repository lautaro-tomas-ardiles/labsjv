package Fisica2;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Se debería intentar hacer un mru que facilite el uso del resto
 * es decir tener una clase mru y una main
 */
public class mru {
    static final HashMap<tipoMagnitud, magnitud> variables = new HashMap<>();

    private static boolean isPresent(tipoMagnitud tipo) {
        return variables.containsKey(tipo) && variables.get(tipo).valor() != null;
    }

    public static void velocidad(Scanner entrada) {
        // Velocidad
        while (true) {
            try {
                String input = entrada.nextLine();
                variables.putAll(operations.procesarEntrada(tipoMagnitud.VELOCIDAD, input));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void distancia(Scanner entrada) {
        while (true) {
            try {
                String input = entrada.nextLine();
                variables.putAll(operations.procesarEntrada(tipoMagnitud.DISTANCIA, input));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void tiempo(Scanner entrada) {
        while (true) {
            try {
                String input = entrada.nextLine();
                variables.putAll(operations.procesarEntrada(tipoMagnitud.TIEMPO, input));
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
        boolean noVelocidad = !isPresent(tipoMagnitud.VELOCIDAD)
                && isPresent(tipoMagnitud.DISTANCIA)
                && isPresent(tipoMagnitud.TIEMPO);
        if (noVelocidad) {
            magnitud distancia = variables.get(tipoMagnitud.DISTANCIA);
            magnitud tiempo = variables.get(tipoMagnitud.TIEMPO);

            String velocidadCalculada = operations.velocidadMRU(distancia, tiempo);
            System.out.println("Velocidad : " + velocidadCalculada);
        }

        boolean noDistancia = !isPresent(tipoMagnitud.DISTANCIA)
                && isPresent(tipoMagnitud.VELOCIDAD)
                && isPresent(tipoMagnitud.TIEMPO);
        // si no hay distancia, pero si hay velocidad y tiempo
        if (noDistancia) {
            magnitud oldTiempo = variables.get(tipoMagnitud.TIEMPO);
            magnitud velocidad = variables.get(tipoMagnitud.VELOCIDAD);

            magnitud newTiempo = operations.convertirTiempo(oldTiempo, velocidad);
            variables.replace(tipoMagnitud.TIEMPO, newTiempo);

            String distanciaCalculada = operations.distanciaMRU(velocidad, newTiempo);
            System.out.println("Distancia : " + distanciaCalculada);
        }

        boolean noTiempo = !isPresent(tipoMagnitud.TIEMPO)
                && isPresent(tipoMagnitud.VELOCIDAD)
                && isPresent(tipoMagnitud.DISTANCIA);
        if (noTiempo) {
            magnitud oldDistancia = variables.get(tipoMagnitud.DISTANCIA);
            magnitud velocidad = variables.get(tipoMagnitud.VELOCIDAD);

            magnitud newDistancia = operations.convertirDistancia(oldDistancia, velocidad);
            variables.replace(tipoMagnitud.DISTANCIA, newDistancia);

            String tiempoCalculado = operations.tiempoMRU(velocidad, newDistancia);
            System.out.println("Tiempo : " + tiempoCalculado);
        }
    }
}
