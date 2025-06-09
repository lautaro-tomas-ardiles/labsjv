package Fisica2;

public enum tipoMagnitud {

    VELOCIDAD(new String[]{"km/h", "km/min", "km/s", "m/h", "m/min", "m/s"}),
    DISTANCIA(new String[]{"km", "m"}),
    TIEMPO(new String[]{"h", "min", "s"}),
    ACELERACION(new String[]{"km/h^2", "km/min^2", "km/s^2", "m/h^2", "m/min^2", "m/s^2"});

    private final String[] unidades;

    tipoMagnitud(String[] unidades) {
        this.unidades = unidades;
    }

    public String[] getUnidades() {
        return unidades;
    }
}