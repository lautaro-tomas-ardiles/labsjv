import java.util.ArrayList;
import java.util.Scanner;

public class Collatz {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static boolean esPar(int n) {
        int r = n % 2;

        return r == 0;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int step = 1;

        System.out.println("ingrese el numero inicial :");
        int xn = in.nextInt();

        ArrayList<Integer> valores = new ArrayList<>();
        valores.add(xn);

        while (xn != 1) {
            if (xn == Integer.MAX_VALUE || xn < 0) {
                System.out.println(ANSI_RED + "ERROR :" + ANSI_RESET + " el numero supero: 2147483647");
                break;
            }
            step++;

            if (esPar(xn)) { //si xn es par
                xn /= 2;// se lo divide por 2
            } else { //si no
                xn = (xn * 3) + 1; // se multiplica por 3 y se añade 1
            }
            System.out.println(ANSI_GREEN + "El número: " + xn + " en el paso: " + step + ANSI_RESET);
            valores.add(xn);
        }

        /*
        // Mostrar gráfico simple en consola
        System.out.println("\nGráfico de Collatz (barra proporcional a cada valor calculado con log):");
        int max = valores.stream().max(Integer::compareTo).get(); // encontrar el valor máximo para escalar

        for (int i = 0; i < valores.size(); i++) {
            int val = valores.get(i);

            double logMax = Math.log(max);
            double logVal = Math.log(val);

            int barras = (int) ((50.0 * logVal) / logMax);
            barras = barras == 0 ? 1 : barras;// si el valor es 0 se cambia a 1

            String paso = "Paso " + String.format("%4d", i); //se le da formato para que toda la barras empiecen en el mismo lugar
            String valor = String.format("%4d",val);
            String barra = "#".repeat(barras);

            System.out.println(paso + ":" + valor + "|" + barra);
        }

         */

        System.out.println("\nGráfico de Collatz en forma VERTICAL:");

        int max = valores.stream().max(Integer::compareTo).get();
        int pasos = valores.size();
        int alturaMaxima = 20;
        int anchoColumna = 6;

        int[] alturas = new int[pasos];

        // Calcular alturas logarítmicas escaladas
        for (int i = 0; i < pasos; i++) {

            double logVal = Math.log(valores.get(i));
            double logMax = Math.log(max);

            int altura = (int) ((alturaMaxima * logVal) / logMax);
            alturas[i] = altura == 0 ? 1 : altura;// si es 0 se cambia a 1 sino se ingresa altura
        }

        // Imprimir gráfico desde la parte superior
        for (int nivel = alturaMaxima; nivel >= 1; nivel--) {
            for (int i = 0; i < pasos; i++) {
                if (alturas[i] >= nivel) {
                    System.out.printf("%" + anchoColumna + "s", "#" + "|");
                } else {
                    System.out.printf("%" + anchoColumna + "s", " " + "|");
                }
            }
            System.out.println();
        }

        // Línea de etiquetas: Paso
        for (int i = 0; i < pasos; i++) {
            System.out.printf("%" + anchoColumna + "s", "P" + i + "|");
        }
        System.out.println();

        // Línea de valores
        for (int i = 0; i < pasos; i++) {
            System.out.printf("%" + anchoColumna + "d", valores.get(i) );
        }
        System.out.println();


    }
}
