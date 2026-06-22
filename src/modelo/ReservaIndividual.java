/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class ReservaIndividual extends Reserva {
// Una reserva individual es una reserva sin características adicionales.
// La única diferencia es que siempre tiene 1 plaza.
 public ReservaIndividual(Socio socio, String tipoClase, String dia, int horaInicio) {

   //Se llama al constructor de la clase base (Reserva) 
   //fijando automáticamente el número de plazas a 1.
   super(socio, tipoClase, dia, horaInicio, 1);
 }
 
 @Override
    public String getTipoReserva() {
        return "Individual";
    }
    
  @Override
public double calcularPrecio() {
    return Tarifa.calcularPrecio(getTipoClase())* getPlazas();
}
}