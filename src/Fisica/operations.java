package Fisica;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class operations {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String velocidadTexto = "velocidad";
    public static final String distanciaTexto = "distancia";
    public static final String tiempoTexto = "tiempo";
    public static final String aceleracionTexto = "aceleracion";


    // se deja las entradas en los hashMap
    public static void procesarEntrada(
            String tipo,
            String entrada,
            HashMap<String, Double> valores,
            HashMap<String, String> unidades
    ) throws IllegalArgumentException {
        entrada = entrada.trim().toLowerCase().replaceAll("\\s+", ""); // quitar espacios

        if (entrada.equals("null")) {
            valores.put(tipo, null);
            unidades.put(tipo, "");
            return;
        }

        // Expresión regular: número con unidad (ej: 12.5m/s, 20km, etc.)
        Pattern pattern = Pattern.compile("(-?[0-9]*\\.?[0-9]+)([a-zA-Z/^0-9]+)");
        Matcher matcher = pattern.matcher(entrada);

        String[] unidadesValidasParaV = {"km/h", "km/min", "km/s", "m/h", "m/min", "m/s"};
        String[] unidadesValidasParaA = {"km/h^2", "km/min^2", "km/s^2", "m/h^2", "m/min^2", "m/s^2"};
        String[] unidadesValidasParaD = {"m", "km"};
        String[] unidadesValidasParaT = {"h", "min", "s"};

        if (matcher.matches()) {
            double valor = Double.parseDouble(matcher.group(1));
            String unidad = matcher.group(2);

            boolean unidadValida = false;

            switch (tipo.toLowerCase()) {
                case velocidadTexto -> {
                    for (String item : unidadesValidasParaV) {
                        if (unidad.equals(item)) {
                            unidadValida = true;
                            break;
                        }
                    }
                }
                case aceleracionTexto -> {
                    for (String item : unidadesValidasParaA) {
                        if (unidad.equals(item)) {
                            unidadValida = true;
                            break;
                        }
                    }
                }
                case distanciaTexto -> {
                    for (String item : unidadesValidasParaD) {
                        if (unidad.equals(item)) {
                            unidadValida = true;
                            break;
                        }
                    }
                }
                case tiempoTexto -> {
                    for (String item : unidadesValidasParaT) {
                        if (unidad.equals(item)) {
                            unidadValida = true;
                            break;
                        }
                    }
                }
            }

            if (unidadValida) {
                valores.put(tipo, valor);
                unidades.put(tipo, unidad);
            } else {
                throw new IllegalArgumentException(ANSI_RED + "Unidad inválida para " + tipo + ": " + unidad + ANSI_RESET);
            }
        } else {
            throw new IllegalArgumentException(ANSI_RED + "Formato inválido para " + tipo + ": " + entrada + ANSI_RESET);
        }
    }

    //si es necesario para el cálculo de distancia se convierte el tiempo
    public static Object[] conversionDeTiempo(String unidadDeVelocidad, String unidadDeTiempo, Double valorTiempo) {
        String[] unidad = unidadDeVelocidad.split("/");

        if (!unidad[1].equals(unidadDeTiempo)) {
            switch (unidad[1]) {
                case "h" -> {
                    switch (unidadDeTiempo) {
                        case "min" -> {
                            unidadDeTiempo = "h";
                            valorTiempo = valorTiempo / 60;
                            System.out.println(ANSI_GREEN + "el tiempo convertido es: " + valorTiempo + " " + unidadDeTiempo + ANSI_RESET);
                        }
                        case "s" -> {
                            unidadDeTiempo = "h";
                            valorTiempo = valorTiempo / 3600;
                            System.out.println(ANSI_GREEN + "el tiempo convertido es: " + valorTiempo + " " + unidadDeTiempo + ANSI_RESET);
                        }
                    }
                }
                case "min" -> {
                    switch (unidadDeTiempo) {
                        case "h" -> {
                            unidadDeTiempo = "min";
                            valorTiempo = valorTiempo * 60;
                            System.out.println(ANSI_GREEN + "el tiempo convertido es: " + valorTiempo + " " + unidadDeTiempo + ANSI_RESET);
                        }
                        case "s" -> {
                            unidadDeTiempo = "min";
                            valorTiempo = valorTiempo / 60;
                            System.out.println(ANSI_GREEN + "el tiempo convertido es: " + valorTiempo + " " + unidadDeTiempo + ANSI_RESET);
                        }
                    }
                }
                case "s" -> {
                    switch (unidadDeTiempo) {
                        case "h" -> {
                            unidadDeTiempo = "s";
                            valorTiempo = valorTiempo * 3600;
                            System.out.println(ANSI_GREEN + "el tiempo convertido es: " + valorTiempo + " " + unidadDeTiempo + ANSI_RESET);
                        }
                        case "min" -> {
                            unidadDeTiempo = "s";
                            valorTiempo = valorTiempo * 60;
                            System.out.println(ANSI_GREEN + "el tiempo convertido es: " + valorTiempo + " " + unidadDeTiempo + ANSI_RESET);
                        }
                    }
                }
            }
        }

        return new Object[]{unidadDeTiempo, valorTiempo};
    }

    //si es necesario para el cálculo de tiempo se convierte la distancia
    public static Object[] conversionDeDistancia(String unidadDeVelocidad, String unidadDeDistancia, Double valorDistancia) {
        String[] unidad = unidadDeVelocidad.split("/");

        if (!unidad[0].equals(unidadDeDistancia)) {
            if (unidad[0].equals("km") && unidadDeDistancia.equals("m")) {
                unidadDeDistancia = "km";
                valorDistancia = valorDistancia / 1000;
                System.out.println(ANSI_GREEN + "La distancia convertida es: " + valorDistancia + " " + unidadDeDistancia + ANSI_RESET);
            }
            if (unidad[0].equals("m") && unidadDeDistancia.equals("km")) {
                unidadDeDistancia = "m";
                valorDistancia = valorDistancia * 1000;
                System.out.println(ANSI_GREEN + "La distancia convertida es: " + valorDistancia + " " + unidadDeDistancia + ANSI_RESET);
            }

        }

        return new Object[]{unidadDeDistancia, valorDistancia};
    }

    /*
      M.R.U.
    */
    //se calcula la velocidad
    public static String velocidadMRU(double distancia, double tiempo, String distanciaUnidad, String tiempoUnidad) {
        double resultado = distancia / tiempo;

        return resultado + distanciaUnidad + "/" + tiempoUnidad;
    }

    //se calcula la distancia
    public static String distanciaMRU(double velocidad, double tiempo, String velocidadUnidad) {
        double resultado = velocidad * tiempo;

        // Extraer unidad de distancia desde velocidad (ej. m/s * s → m)
        String distanciaUnidad = velocidadUnidad.split("/")[0];

        return resultado + " " + distanciaUnidad;
    }

    //se calcula el tiempo
    public static String tiempoMRU(double distancia, double velocidad, String velocidadUnidad) {
        double resultado = distancia / velocidad;

        // Extraer unidad de tiempo desde velocidad (ej. m / (m/s) → s)
        String tiempoUnidad = velocidadUnidad.split("/")[1];

        return resultado + " " + tiempoUnidad;
    }

    /*
      M.R.U.V
    */
    public static String velocidadFinalMRUV(
            double velocidadInicial,
            String velocidadUnidades,
            double aceleracion,
            String aceleracionUnidades,
            double tiempo,
            String tiempoUnidades
    ) {
        double resultado = velocidadInicial + (aceleracion * tiempo);

        if (aceleracionUnidades.contains(velocidadUnidades) && velocidadUnidades.contains(tiempoUnidades)) {
            return resultado + velocidadUnidades;
        } else {
            throw new IllegalArgumentException(ANSI_RED + "unidades incompatibles para velocidad final" + ANSI_RESET);
        }

    }
}
