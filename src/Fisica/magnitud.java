package Fisica;

/*
public class magnitud {
    private final double valor;
    private final String unidad;

    public magnitud(double valor, String unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }
    public double getValor() {
        return valor;
    }
    public String getUnidad() {
        return unidad;
    }
}
*/

public record magnitud(double valor, String unidad) { }

