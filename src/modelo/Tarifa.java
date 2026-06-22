package modelo;
public class Tarifa {

    private static final double PRECIO_YOGA = 8.0;
    private static final double PRECIO_SPINNING = 10.0;
    private static final double PRECIO_BASE = 6.0;

    public static double calcularPrecio(String tipoClase) {

        if (tipoClase.equals("Yoga")) {
            return PRECIO_YOGA;
        }

        if (tipoClase.equals("Spinning")) {
            return PRECIO_SPINNING;
        }

        return PRECIO_BASE;
    }
}

