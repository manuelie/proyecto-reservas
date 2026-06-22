/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.*;
public class CargadorReservas {
    public static void main (String[] args) {
        int reservasCargadas = 0;
        double sumaPrecios=0;

        int reservasYoga=0;
        int reservasSpinning=0;
        int reservasPilates=0;
        int reservasFuncional=0;

        int reservasLunes=0;
        int reservasMartes=0;
        int reservasMiercoles=0;
        int reservasJueves=0;
        int reservasViernes=0;
        try {
        FileReader fr = new FileReader("datos/reservas.csv");
        BufferedReader br = new BufferedReader(fr);
        
        String linea;

        while ((linea = br.readLine()) != null) {
            try {
            String[] datos = linea.split(";");

            String id = datos[0];
            String nombre = datos[1];
            String tipoClase = datos[2];
            String dia = datos[3];

        int horaInicio = Integer.parseInt(datos[4]);
        int plazas = Integer.parseInt(datos[5]);

        Socio s1 = new Socio(nombre, id);
        Reserva r1;

if (plazas == 1) {
    r1 = new ReservaIndividual(
            s1,
            tipoClase,
            dia,
            horaInicio);
} else {
    r1 = new ReservaGrupal(
            s1,
            tipoClase,
            dia,
            horaInicio,
            plazas,
            0.0);
}

        double precio = r1.calcularPrecio();

        System.out.println(
            "Reserva: " +
            nombre + " - " +
            tipoClase + " - " +
            dia + " " +
            horaInicio + "h - Precio: " +
            precio
        );
        reservasCargadas++;
        sumaPrecios+=precio;

        if (tipoClase.equalsIgnoreCase("yoga")) {
            reservasYoga++;
        } else if (tipoClase.equalsIgnoreCase("pilates")) {
            reservasPilates++;
        } else if (tipoClase.equalsIgnoreCase("spinning")) {
            reservasSpinning++;
        } else if (tipoClase.equalsIgnoreCase("funcional")) {
            reservasFuncional++;
        }

        if (dia.equalsIgnoreCase("lunes")) {
            reservasLunes++;
        } else if (dia.equalsIgnoreCase("martes")) {
            reservasMartes++;
        } else if (dia.equalsIgnoreCase("miercoles")) {
            reservasMiercoles++;
        } else if (dia.equalsIgnoreCase("jueves")) {
            reservasJueves++;
        } else if (dia.equalsIgnoreCase("viernes")) {
            reservasViernes++;
        }
        } catch (Exception e) {
            System.out.println("Registro incorrecto: "+linea);
        }
        }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        System.out.println("Número total de reservas cargadas: "+reservasCargadas);

        if(reservasCargadas>0) {
            double media = sumaPrecios/reservasCargadas;
            System.out.println("Precio medio de las reservas: "+  media);
        }
        System.out.println("\nReservas por tipo de clase:");
        System.out.println("Yoga: " + reservasYoga);
        System.out.println("Pilates: " + reservasPilates);
        System.out.println("Spinning: " + reservasSpinning);
        System.out.println("Funcional: " + reservasFuncional);

        System.out.println("\nReservas por día:");
        System.out.println("Lunes: " + reservasLunes);
        System.out.println("Martes: " + reservasMartes);
        System.out.println("Miércoles: " + reservasMiercoles);
        System.out.println("Jueves: " + reservasJueves);
        System.out.println("Viernes: " + reservasViernes);

}
}

    
 