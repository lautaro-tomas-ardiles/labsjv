package Fisica2;

import java.util.HashMap;
import java.util.regex.Pattern;

public class operations {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    //codigo inicial de como se procesaba la entrada
    /*
    public static HashMap<tipoMagnitud, magnitud> procesarEntrada(
            tipoMagnitud tipo,
            String entrada
    ) throws IllegalArgumentException {

        var resultado = new HashMap<tipoMagnitud, magnitud>();
        entrada = entrada.toLowerCase().replaceAll(" ", "");

        if (entrada.equals("null")) {
            resultado.put(tipo, new magnitud(null, ""));
        }
        // Expresión regular: número con unidad (ej: 12.5m/s, 20km, etc.)
        var pattern = Pattern.compile("(-?[0-9]*\\.?[0-9]+)([a-zA-Z/^0-9]+)");
        var matcher = pattern.matcher(entrada);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ANSI_RED + "Formato inválido para " + tipo + ": " + entrada + ANSI_RESET);
        }

        double valor = Double.parseDouble(matcher.group(1));
        String unidad = matcher.group(2);

        switch (tipo) {
            case VELOCIDAD -> {
                for (String item : tipoMagnitud.VELOCIDAD.getUnidades()) {
                    if (unidad.equals(item)) {
                        resultado.put(tipo, new magnitud(valor, unidad));
                        return resultado;
                    }
                }
            }
            case DISTANCIA -> {
                for (String item : tipoMagnitud.DISTANCIA.getUnidades()) {
                    if (unidad.equals(item)) {
                        resultado.put(tipo, new magnitud(valor, unidad));
                        return resultado;
                    }
                }
            }
            case TIEMPO -> {
                for (String item : tipoMagnitud.TIEMPO.getUnidades()) {
                    if (unidad.equals(item)) {
                        resultado.put(tipo, new magnitud(valor, unidad));
                        return resultado;
                    }
                }
            }
            case ACELERACION -> {
                for (String item : tipoMagnitud.ACELERACION.getUnidades()) {
                    if (unidad.equals(item)) {
                        resultado.put(tipo, new magnitud(valor, unidad));
                        return resultado;
                    }
                }
            }
            default -> throw new IllegalArgumentException(ANSI_RED + "Unidad inválida para " + tipo + ": " + unidad + ANSI_RESET);
        }


        return resultado;
    }
     */

    private static boolean esUnidadValida(tipoMagnitud tipo, String unidad) {
        for (String item : tipo.getUnidades()) {
            if (item.equals(unidad)) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<tipoMagnitud, magnitud> procesarEntrada(
            tipoMagnitud tipo,
            String entrada
    ) throws IllegalArgumentException {

        var resultado = new HashMap<tipoMagnitud, magnitud>();
        entrada = entrada.toLowerCase().replaceAll(" ", "");

        if ("null".equals(entrada)) {
            resultado.put(tipo, new magnitud(null, ""));
            return resultado;
        }

        var pattern = Pattern.compile("(-?[0-9]*\\.?[0-9]+)([a-zA-Z/^0-9]+)");
        var matcher = pattern.matcher(entrada);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ANSI_RED + "Formato inválido para " + tipo + ": " + entrada + ANSI_RESET);
        }

        double valor = Double.parseDouble(matcher.group(1));
        String unidad = matcher.group(2);

        if (esUnidadValida(tipo, unidad)) {
            resultado.put(tipo, new magnitud(valor, unidad));
            return resultado;
        } else {
            throw new IllegalArgumentException(ANSI_RED + "Unidad inválida para " + tipo + ": " + unidad + ANSI_RESET);
        }
    }

    public static magnitud convertirVelocidad(magnitud velocidad, magnitud aceleracion) {
        // Extraer la unidad de velocidad objetivo de la aceleración (ej.: m/s^2 -> m/s)
        String unidadObjetivo = aceleracion.unidad().replace("^2", "");

        double valorConvertido = velocidad.valor();
        String unidadConvertida = "";

        if (unidadObjetivo.equals(velocidad.unidad())) {
            return velocidad;
        }

        switch (unidadObjetivo){
            case "km/h" -> {
                unidadConvertida = "km/h";
                valorConvertido = switch (velocidad.unidad()) {
                    case "km/min" -> velocidad.valor() * 60;
                    case "km/s" -> velocidad.valor() * 3_600;
                    case "m/h" -> velocidad.valor() / 1_000;
                    case "m/min" -> velocidad.valor() * 0.06;
                    case "m/s" -> velocidad.valor() * 3.6;
                    default -> valorConvertido;
                };
            }
            case "km/min" -> {
                unidadConvertida = "km/min";
                valorConvertido = switch (velocidad.unidad()) {
                    case "km/h" -> velocidad.valor() / 60;
                    case "km/s" -> velocidad.valor() * 60;
                    case "m/h" -> velocidad.valor() / 60_000;
                    case "m/min" -> velocidad.valor() / 1_000;
                    case "m/s" -> velocidad.valor() * 0.06;
                    default -> valorConvertido;
                };
            }
            case "km/s" -> {
                unidadConvertida = "km/s";
                valorConvertido = switch (velocidad.unidad()) {
                    case "km/h" -> velocidad.valor() / 3_600;
                    case "km/min" -> velocidad.valor() / 60;
                    case "m/h" -> velocidad.valor() / 1_000 / 3_600;
                    case "m/min" -> velocidad.valor() / 1_000 / 60;
                    case "m/s" -> velocidad.valor() / 1_000;
                    default -> valorConvertido;
                };
            }
            case "m/h" -> {
                unidadConvertida = "m/h";
                valorConvertido = switch (velocidad.unidad()) {
                    case "km/h" -> velocidad.valor() * 1_000;
                    case "km/min" -> velocidad.valor() * 60_000;
                    case "km/s" -> velocidad.valor() * 1_000 * 60 * 60;
                    case "m/min" -> velocidad.valor() * 60;
                    case "m/s" -> velocidad.valor() * 3_600;
                    default -> valorConvertido;
                };
            }
            case "m/min" -> {
                unidadConvertida = "m/min";
                valorConvertido = switch (velocidad.unidad()) {
                    case "km/h" -> velocidad.valor() * 1_000 / 60;
                    case "km/min" -> velocidad.valor() * 1_000;
                    case "km/s" -> velocidad.valor() * 60_000;
                    case "m/h" -> velocidad.valor() / 60;
                    case "m/s" -> velocidad.valor() * 60;
                    default -> valorConvertido;
                };
            }
            case "m/s" -> {
                unidadConvertida = "m/s";
                valorConvertido = switch (velocidad.unidad()) {
                    case "km/h" -> velocidad.valor() * 1_000 / 3_600;
                    case "km/min" -> velocidad.valor() * 1_000 / 60;
                    case "km/s" -> velocidad.valor() * 1_000;
                    case "m/h" -> velocidad.valor() / 3_600;
                    case "m/min" -> velocidad.valor() / 60;
                    default -> valorConvertido;
                };
            }
        }

        System.out.println(ANSI_GREEN + "La velocidad convertida es: " + valorConvertido + " " + unidadConvertida + ANSI_RESET);

        return new magnitud(valorConvertido, unidadObjetivo);
    }

    public static magnitud convertirTiempo(magnitud tiempo, magnitud velocidad) {
        // Extraer la unidad de tiempo de la velocidad (pasa de m/s a m -> s)
        String unidadObjetivo = velocidad.unidad().split("/")[1];

        double valorConvertido = 0;
        String unidadConvertida = "";

        if (unidadObjetivo.equals(tiempo.unidad())) {
            return tiempo;
        }

        switch (unidadObjetivo) {
            case "h" -> {
                unidadConvertida = "h";
                if ("s".equals(tiempo.unidad())) {
                    valorConvertido = tiempo.valor() / 3600;

                } else if ("min".equals(tiempo.unidad())) {
                    valorConvertido = tiempo.valor() / 60;
                }
            }
            case "min" -> {
                unidadConvertida = "min";
                if ("h".equals(tiempo.unidad())) {
                    valorConvertido = tiempo.valor() * 60;
                } else if ("s".equals(tiempo.unidad())) {
                    valorConvertido = tiempo.valor() / 60;
                }
            }
            case "s" -> {
                unidadConvertida = "s";
                if ("h".equals(tiempo.unidad())) {
                    valorConvertido = tiempo.valor() * 3600;
                } else if ("min".equals(tiempo.unidad())) {
                    valorConvertido = tiempo.valor() * 60;
                }
            }
        }

        System.out.println(ANSI_GREEN + "el tiempo convertido es: " + valorConvertido + " " + unidadConvertida + ANSI_RESET);
        return new magnitud(valorConvertido, unidadConvertida);
    }

    public static magnitud convertirDistancia(magnitud distancia, magnitud velocidad) {
        // Extraer la unidad de distancia de la velocidad (pasa de m/s a m -> s)
        String unidadObjetivo = velocidad.unidad().split("/")[0];

        double valorConvertido = 0;
        String unidadConvertida = "";

        if (unidadObjetivo.equals(distancia.unidad())) {
            return distancia;
        }

        switch (unidadObjetivo) {
            case "km" -> {
                unidadConvertida = "km";
                valorConvertido = distancia.valor() / 1000;
            }
            case "m" -> {
                unidadConvertida = "m";
                valorConvertido = distancia.valor() * 1000;
            }
        }

        System.out.println(ANSI_GREEN + "la distancia convertida es: " + valorConvertido + " " + unidadConvertida + ANSI_RESET);
        return new magnitud(valorConvertido, unidadConvertida);
    }

    public static String velocidadMRU(magnitud distancia, magnitud tiempo) {
        double resultado = distancia.valor() / tiempo.valor();

        return resultado + distancia.unidad() + "/" + tiempo.unidad();
    }

    public static String distanciaMRU(magnitud velocidad, magnitud tiempo) {
        double resultado = velocidad.valor() * tiempo.valor();

        // Extraer unidad de distancia desde velocidad (ej. m/s | s → m)
        String distanciaUnidad = velocidad.unidad().split("/")[0];

        return resultado + " " + distanciaUnidad;
    }

    public static String tiempoMRU(magnitud distancia, magnitud velocidad) {
        double resultado = distancia.valor() / velocidad.valor();

        // Extraer unidad de tiempo desde velocidad (ej. m/s | s → m)
        String tiempoUnidad = velocidad.unidad().split("/")[1];

        return resultado + " " + tiempoUnidad;
    }

}


