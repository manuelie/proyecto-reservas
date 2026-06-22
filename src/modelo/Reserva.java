package modelo;
public abstract class Reserva implements Facturable {

    private static final int HORA_MINIMA = 8;
    private static final int HORA_MAXIMA = 22;

    private Socio socio;
    private String tipoClase;
    private String dia;
    private int horaInicio;
    private int plazas;

    public Reserva(Socio socio, String tipoClase, String dia, int horaInicio, int plazas) {

        this.socio = socio;
        this.tipoClase = tipoClase;
        this.dia = dia;

        if (horaInicio >= HORA_MINIMA && horaInicio <= HORA_MAXIMA) {
            this.horaInicio = horaInicio;
        }

        if (plazas > 0) {
            this.plazas = plazas;
        }
    }

    public Socio getSocio() {
        return socio;
    }

    public String getTipoClase() {
        return tipoClase;
    }

    public String getDia() {
        return dia;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getPlazas() {
        return plazas;
    }
    
    public boolean esValida() {

        if (socio == null) {
            return false;
        }

        if (plazas <= 0) {
            return false;
        }

        if (horaInicio < HORA_MINIMA || horaInicio > HORA_MAXIMA) {
            return false;
        }

        return true;
    }

    public Reserva empiezaAntes(Reserva otra) {

        if (this.horaInicio < otra.horaInicio) {
            return this;
        } else {
            return otra;
        }
    }

    public static Reserva empiezaAntes(Reserva r1, Reserva r2) {

        if (r1.horaInicio < r2.horaInicio) {
            return r1;
        } else {
            return r2;
        }
    }

    public abstract double calcularPrecio();

    public String getTipoReserva() {
        return "General";
    }

    public static int getHoraMinima() {
        return HORA_MINIMA;
    }

    public static int getHoraMaxima() {
        return HORA_MAXIMA;
    }

    @Override
    public String toString() {
         return socio.getNombre()
                + " - "
                + tipoClase
                + " - "
                + dia
                + " "
                + horaInicio
                + "h - Tipo: "
                + getTipoReserva();
}
public void mostrarDetalle() {
        System.out.println(this);
    }
}

