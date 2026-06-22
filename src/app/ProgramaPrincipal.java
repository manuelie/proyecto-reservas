package app;

import modelo.*;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class ProgramaPrincipal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Reserva> listaReservas =
                new ArrayList<>();

        registrarReservas(sc, listaReservas);

        mostrarListadoRegistradas(listaReservas);

        eliminarNoValidas(listaReservas);

        mostrarConteoPorTipoClase(listaReservas);

        double total =
                calcularIngresos(listaReservas);

        double media =
                calcularMedia(listaReservas, total);

        System.out.println(
                "\nIngresos totales de reservas válidas: "
                        + total);

        System.out.println(
                "Precio medio de las reservas válidas: "
                        + media);

        mostrarListadoValidas(listaReservas);

        menuBusquedas(sc, listaReservas);

        ArrayList<Facturable> facturables = new ArrayList<>();

facturables.addAll(listaReservas);
facturables.add(new BonoMensual("Marcos",TipoBono.BASICO,30));
facturables.add(new BonoMensual("Nerea",TipoBono.PREMIUM,50));

System.out.println("--- FACTURACIÓN GLOBAL ---");
double totalSistema = 0;

for (Facturable f : facturables) {

    System.out.println(f.getClass().getSimpleName()+ ": "+ f.calcularPrecio());

    totalSistema +=f.calcularPrecio();
}

double mediaSistema =totalSistema /facturables.size();

System.out.println("Total de ingresos del sistema: " + totalSistema);
System.out.println("Ingreso medio del sistema: " + mediaSistema);

sc.close();
    }


    // REGISTRO DE RESERVAS

    public static void registrarReservas(
            Scanner sc,
            ArrayList<Reserva> listaReservas) {

        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {

            System.out.print(
                    "Nombre del socio: ");

            String nombre = sc.nextLine();

            System.out.print(
                    "Identificador del socio: ");

            String id = sc.nextLine();

            System.out.print(
                    "Tipo de clase: ");

            String tipoClase = sc.nextLine();

            System.out.print(
                    "Día de la reserva: ");

            String dia = sc.nextLine();

            System.out.print(
                    "Hora de inicio: ");

            int hora =
                    Integer.parseInt(sc.nextLine());

            System.out.println(
                    "Tipo de reserva:");

            System.out.println(
                    "1. Individual");

            System.out.println(
                    "2. Grupal");

            System.out.print(
                    "Selecciona una opción: ");

            int tipoReserva =
                    Integer.parseInt(sc.nextLine());

            Socio socio =
                    new Socio(nombre, id);

            Reserva reserva;

            int plazas = 1;
            double descuento = 0;

            if (tipoReserva == 1) {

                reserva =
                        new ReservaIndividual(
                                socio,
                                tipoClase,
                                dia,
                                hora);

            } else {

                System.out.print(
                        "Número de plazas: ");

                plazas =
                        Integer.parseInt(
                                sc.nextLine());

                System.out.print(
                        "Descuento: ");

                descuento =
                        Double.parseDouble(
                                sc.nextLine());

                reserva =
                        new ReservaGrupal(
                                socio,
                                tipoClase,
                                dia,
                                hora,
                                plazas,
                                descuento);
            }

            System.out.println(
                    "¿Reserva válida? "
                            + reserva.esValida());

            System.out.println(
                    "Precio total: "
                            + reserva.calcularPrecio());

            if (reserva instanceof ReservaGrupal
                    && reserva.esValida()) {

                ReservaGrupal rg =
                        (ReservaGrupal) reserva;

                System.out.println(
                        "Descuento aplicado: "
                                + rg.getDescuento());
            }

            listaReservas.add(reserva);

            guardarReservaEnFichero(reserva);

            System.out.print(
                    "¿Deseas registrar otra reserva? (s/n): ");

            continuar = sc.nextLine();
        }
    }

    // PERSISTENCIA

    public static void guardarReservaEnFichero(
            Reserva reserva) {

        if (!reserva.esValida()) {
            return;
        }

        File directorio =
                new File("datos");

        if (!directorio.exists()) {
            directorio.mkdir();
        }

        PrintWriter pw = null;

        try {

            pw = new PrintWriter(
                    new FileWriter(
                            "datos/reservas.csv",
                            true));

            String tipo = "I";
            double descuento = 0;

            if (reserva instanceof ReservaGrupal) {

                tipo = "G";

                ReservaGrupal rg =
                        (ReservaGrupal) reserva;

                descuento =
                        rg.getDescuento();
            }

            String linea =
                    reserva.getSocio().getIdSocio()
                            + ";"
                            + reserva.getSocio().getNombre()
                            + ";"
                            + reserva.getTipoClase()
                            + ";"
                            + reserva.getDia()
                            + ";"
                            + reserva.getHoraInicio()
                            + ";"
                            + reserva.getPlazas()
                            + ";"
                            + tipo
                            + ";"
                            + descuento;

            pw.println(linea);

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar la reserva.");

        } finally {

            if (pw != null) {
                pw.close();
            }
        }
    }

    // LISTADOS

    public static void mostrarListadoRegistradas(
            ArrayList<Reserva> listaReservas) {

        System.out.println(
                "\nListado de reservas registradas:");

        for (Reserva r : listaReservas) {
        r.mostrarDetalle();
                }
        }

    public static void mostrarListadoValidas(
            ArrayList<Reserva> listaReservas) {

        System.out.println(
                "\nListado de reservas válidas:");

        for (Reserva r : listaReservas) {
        r.mostrarDetalle();
    }
    }

    public static void imprimirReserva(Reserva r) {

        System.out.println(

                r.getSocio().getNombre()
                        + " - "
                        + r.getTipoClase()
                        + " - "
                        + r.getDia()
                        + " "
                        + r.getHoraInicio()
                        + "h"
        );
    }

    public static void imprimirInformacionEspecifica(
            Reserva r) {

        System.out.println(
                "Tipo de reserva: "
                        + r.getTipoReserva());

        System.out.println(
                "Precio: "
                        + r.calcularPrecio());

        if (r instanceof ReservaGrupal) {

            ReservaGrupal rg =
                    (ReservaGrupal) r;

            System.out.println(
                    "Descuento aplicado: "
                            + rg.getDescuento());
        }
    }

    // ELIMINACIÓN

    public static void eliminarNoValidas(
            ArrayList<Reserva> listaReservas) {

        System.out.println(
                "\nEliminación de reservas no válidas:");

        for (int i = listaReservas.size() - 1;
             i >= 0;
             i--) {

            if (!listaReservas.get(i).esValida()) {

                System.out.println(
                        "Eliminada: "
                                + listaReservas.get(i)
                                .getSocio()
                                .getNombre());

                listaReservas.remove(i);
            }
        }
    }

    // CONTEO

    public static void mostrarConteoPorTipoClase(
            ArrayList<Reserva> listaReservas) {

        int yoga = 0;
        int spinning = 0;
        int pilates = 0;
        int funcional = 0;

        for (Reserva r : listaReservas) {

            switch (r.getTipoClase()) {

                case "Yoga":
                    yoga++;
                    break;

                case "Spinning":
                    spinning++;
                    break;

                case "Pilates":
                    pilates++;
                    break;

                case "Funcional":
                    funcional++;
                    break;
            }
        }

        System.out.println(
                "\nTotal de reservas por tipo de clase:");

        System.out.println(
                "Yoga: " + yoga);

        System.out.println(
                "Spinning: " + spinning);

        System.out.println(
                "Pilates: " + pilates);

        System.out.println(
                "Funcional: " + funcional);
    }

    // CÁLCULOS

    public static double calcularIngresos(
            ArrayList<Reserva> listaReservas) {

        double total = 0;

        for (Reserva r : listaReservas) {

            total += r.calcularPrecio();
        }

        return total;
    }

    public static double calcularMedia(
            ArrayList<Reserva> listaReservas,
            double total) {

        if (listaReservas.size() == 0) {
            return 0;
        }

        return total / listaReservas.size();
    }


    // MENÚ

    public static void menuBusquedas(
            Scanner sc,
            ArrayList<Reserva> listaReservas) {

        int opcion;

        do {

            System.out.println(
                    "\n--- MENÚ DE BÚSQUEDAS ---");

            System.out.println(
                    "1. Buscar por identificador");

            System.out.println(
                    "2. Buscar por tipo de clase");

            System.out.println(
                    "3. Buscar por día");

            System.out.println(
                    "4. Búsqueda combinada");

            System.out.println(
                    "5. Mostrar reservas individuales");

            System.out.println(
                    "6. Mostrar reservas grupales");

            System.out.println(
                    "0. Salir");

            opcion =
                    Integer.parseInt(
                            sc.nextLine());

            switch (opcion) {

                case 1:
                    buscarPorId(sc, listaReservas);
                    break;

                case 2:
                    buscarPorTipoClase(sc, listaReservas);
                    break;

                case 3:
                    buscarPorDia(sc, listaReservas);
                    break;

                case 4:
                    busquedaCombinada(sc, listaReservas);
                    break;

                case 5:
                    mostrarReservasIndividuales(
                            listaReservas);
                    break;

                case 6:
                    mostrarReservasGrupales(
                            listaReservas);
                    break;
            }

        } while (opcion != 0);
    }

    // NUEVAS BÚSQUEDAS

    public static void mostrarReservasIndividuales(
            ArrayList<Reserva> listaReservas) {

        System.out.println(
                "\nReservas individuales:");

        for (Reserva r : listaReservas) {

            if (r instanceof ReservaIndividual) {
                imprimirReserva(r);
            }
        }
    }

    public static void mostrarReservasGrupales(
            ArrayList<Reserva> listaReservas) {

        System.out.println(
                "\nReservas grupales:");

        for (Reserva r : listaReservas) {

            if (r instanceof ReservaGrupal) {

                imprimirReserva(r);

                imprimirInformacionEspecifica(r);
            }
        }
    }

    // BÚSQUEDAS

    public static void buscarPorId(
            Scanner sc,
            ArrayList<Reserva> listaReservas) {

        System.out.print("ID: ");

        String id = sc.nextLine();

        for (Reserva r : listaReservas) {

            if (r.getSocio()
                    .getIdSocio()
                    .equals(id)) {

                imprimirReserva(r);
            }
        }
    }

    public static void buscarPorTipoClase(
            Scanner sc,
            ArrayList<Reserva> listaReservas) {

        System.out.print("Tipo: ");

        String tipo = sc.nextLine();

        for (Reserva r : listaReservas) {

            if (r.getTipoClase().equals(tipo)) {

                imprimirReserva(r);
            }
        }
    }

    public static void buscarPorDia(
            Scanner sc,
            ArrayList<Reserva> listaReservas) {

        System.out.print("Día: ");

        String dia = sc.nextLine();

        for (Reserva r : listaReservas) {

            if (r.getDia().equals(dia)) {

                imprimirReserva(r);
            }
        }
    }

    public static void busquedaCombinada(
            Scanner sc,
            ArrayList<Reserva> listaReservas) {

        System.out.println(
                "1. Tipo + Día");

        System.out.println(
                "2. ID + Día");

        int opcion =
                Integer.parseInt(
                        sc.nextLine());

        if (opcion == 1) {

            System.out.print("Tipo: ");

            String tipo =
                    sc.nextLine();

            System.out.print("Día: ");

            String dia =
                    sc.nextLine();

            for (Reserva r : listaReservas) {

                if (r.getTipoClase().equals(tipo)
                        && r.getDia().equals(dia)) {

                    imprimirReserva(r);
                }
            }

        } else {

            System.out.print("ID: ");

            String id =
                    sc.nextLine();

            System.out.print("Día: ");

            String dia =
                    sc.nextLine();

            for (Reserva r : listaReservas) {

                if (r.getSocio()
                        .getIdSocio()
                        .equals(id)
                        && r.getDia().equals(dia)) {

                    imprimirReserva(r);
                }
            }
        }
    }

    public static boolean validarIdSocio(String id) {

    return id != null && !id.isEmpty();
}
}