package Fisica;

import java.util.HashMap;
import java.util.Scanner;

import static Fisica.operations.*;

public class mruv {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        HashMap<String, Double> valores = new HashMap<>();
        HashMap<String, String> unidades = new HashMap<>();

        String tipoDeCalculo;
        String vF, vI, a, t;

        System.out.println("que el calculo que desea hacer");
        System.out.println("las opciones son :(Velocidad final|Distancia recorrida|Aceleración|tiempo|distancia sin tiempo) ");
        System.out.print("ingrese el calculo : ");
        tipoDeCalculo = in.nextLine();
        tipoDeCalculo = tipoDeCalculo.toLowerCase();

        switch (tipoDeCalculo) {
            case "velocidad final" -> {
                //velocidad inicial
                while (true) {
                    try {
                        System.out.print("Ingrese la velocidad inicial con unidad(0 si no tiene + unidad): ");
                        vI = in.nextLine();
                        procesarEntrada(velocidadTexto, vI, valores, unidades);
                        break;
                    }catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                //aceleracion
                while (true) {
                    try {
                        System.out.print("Ingrese la aceleracion con unidad(0 si no tiene + unidad): ");
                        a = in.nextLine();
                        procesarEntrada(aceleracionTexto, a, valores, unidades);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                //tiempo
                while (true) {
                    try {
                        System.out.print("Ingrese la tiempo: ");
                        t = in.nextLine();
                        procesarEntrada(tiempoTexto, t, valores, unidades);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                //calculo
                try {
                    String velocidadFinal = velocidadFinalMRUV(
                            valores.get(velocidadTexto),
                            unidades.get(velocidadTexto),
                            valores.get(aceleracionTexto),
                            unidades.get(aceleracionTexto),
                            valores.get(tiempoTexto),
                            unidades.get(tiempoTexto)
                    );
                    System.out.println("si acelera o desacelera a " + a + " durante " + t + " su velocidad final es de " + velocidadFinal);
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            case "distancia recorrida" -> {

            }
            case "aceleracion" -> {

            }
            case "tiempo" -> {

            }
            case "distancia sin tiempo" -> {

            }
            default -> {
                System.out.println("las opciones son :(Velocidad final|Distancia recorrida|Aceleración|tiempo|distancia sin tiempo) ");
            }
        }

    }
}
