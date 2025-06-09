package Fisica2;

/*
public final class magnitud {
    private Double valor;
    private String unidad;

    public magnitud(Double valor, String unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }

    public double getValor() {
        return valor;
    }
    public String getUnidad() {
        return unidad;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
*/
public record magnitud(Double valor, String unidad) { }