/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class ReservaGrupal extends Reserva {
 private double descuento;

 //Una reserva grupal permite varias plazas y aplica un descuento.

 public ReservaGrupal(Socio socio, String tipoClase, String dia, int horaInicio, int plazas, double descuento) {

   //Primero se inicializa la parte heredada (Reserva)
 
   super(socio, tipoClase, dia, horaInicio, plazas);

   // Después se inicializa la parte propia de la subclase
   this.descuento = descuento;
 }

@Override
   public double calcularPrecio() {

        double precioBase =
                Tarifa.calcularPrecio(
                        getTipoClase())
                        * getPlazas();

        return precioBase - descuento;
    }
 

@Override
    public String getTipoReserva() {
        return "Grupal";
    }

 public double getDescuento() {
        return descuento;
    }

  @Override
    public String toString() {
        return super.toString() + "\nDescuento aplicado: " + descuento;
    }
@Override
    public void mostrarDetalle() {
        System.out.println(this);
    }
}