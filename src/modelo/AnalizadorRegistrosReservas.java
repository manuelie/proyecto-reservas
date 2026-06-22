/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import app.*;
public class AnalizadorRegistrosReservas {

    public static void main(String[] args) {

        // Array con registros de reservas simulando datos recibidos desde un sistema externo
        String[] registros = {
                "S001;Yoga;Lunes;18;1",
                "S002;Spinning;Martes;19;2",
                "S10;Pilates;Miércoles;abc;1"
        };

        // Se recorren todos los registros del array
        for (String registro : registros) {

            // Se separan los datos del registro utilizando el carácter ';'
            String[] datos = registro.split(";");

            // Extracción de los campos que se van a validar
            String id = datos[0];
            String horaTexto = datos[3];

            // Variable que indica si el registro tiene un formato correcto
            boolean registroCorrecto = true;

            // Validación del identificador de socio reutilizando el método ya creado
            // en la clase ProgramaPrincipal
            if (!ProgramaPrincipal.validarIdSocio(id)) {
                registroCorrecto = false;
            }

            // Validación del formato de la hora de inicio
            // Se intenta convertir el texto a número entero
            // Si el texto no representa un número, se produce una excepción
            try {

                Integer.parseInt(horaTexto);

            } catch (NumberFormatException e) {

                // Si ocurre la excepción, el formato del dato es incorrecto
                registroCorrecto = false;
            }

            // Si se detecta algún error en el registro, se muestra la línea completa
            if (!registroCorrecto) {

                System.out.println("Registro incorrecto: " + registro);
            }
        }
    }
}