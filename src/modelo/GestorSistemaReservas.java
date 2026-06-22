/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.*;
public class GestorSistemaReservas {
    public static void main (String[] args) {
        File directorioDatos = new File("datos");
        if (!directorioDatos.exists()) {
            System.out.println("El directorio 'datos' no existe.");
            System.out.println("Debes ejecutar primero el programa de reservas para generar los datos.");
            return;
        }


        File f = new File ("datos/reservas.csv");
        if(!f.exists()) {
            System.out.println("El fichero de reservas no existe.");
        } else {
            System.out.println("El fichero de reservas existe.");
        }

        if(f.exists()) {
        if(f.isFile()) {
            System.out.println("El elemento es un fichero.");

        } else if (f.isDirectory()) {
            System.out.println("El elemento es un directorio.");
        }
        }

        if(f.exists()) {
            System.out.println("Nombre del fichero: "+ f.getName());
            System.out.println("Ruta del fichero: "+ f.getPath());
            System.out.println("Tamaño del fichero en bytes: "+ f.length());
        }
        
        System.out.println("Permisos de lectura: "+f.canRead());
        System.out.println("Permisos de escritura: "+f.canWrite());

        System.out.println("Contenido del directorio datos:");
        File[] archivos = directorioDatos.listFiles();
        if(archivos !=null && archivos.length>0) {
        for(File archivo : archivos) {
            if(archivo.isFile()) {
                System.out.println("Archivo: "+ archivo.getName());
            } else if (archivo.isDirectory()) {
                System.out.println("Directorio: "+ archivo.getName());
            }
        }
        } else {
            System.out.println("El directorio está vacío o no se puedo acceder.");
        }
        }
        }




