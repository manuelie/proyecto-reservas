package modelo;
public class BonoMensual implements Facturable {

    private String nombreSocio;
    private TipoBono tipoBono;
    private double precioMensual;

    public BonoMensual(
            String nombreSocio,
            TipoBono tipoBono,
            double precioMensual) {

        this.nombreSocio = nombreSocio;
        this.tipoBono = tipoBono;
        this.precioMensual = precioMensual;
    }

    @Override
    public double calcularPrecio() {
        return precioMensual;
    }

    @Override
    public String toString() {
        return nombreSocio
                + " - "
                + tipoBono
                + " - "
                + precioMensual
                + "€";
    }
}
