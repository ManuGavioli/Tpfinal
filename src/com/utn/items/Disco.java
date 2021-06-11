package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosD;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Disco extends itemVenta{

    private String Solo_Banda;
    private ArrayList<String> Canciones;
    private GenerosD Genero;
    private LocalDate FechaLanzamiento;

    //region Constructores
    public Disco(float precio,String nombre, ClasificacionEdad clasificacion) {
        super(precio, nombre, clasificacion);
    }


    public Disco(float precio, String nombre, ClasificacionEdad clasificacion, String solo_Banda, ArrayList<String> canciones, GenerosD genero, LocalDate fechaLanzamiento) {
        super(precio, nombre, clasificacion);
        Solo_Banda = solo_Banda;
        Canciones= new ArrayList<String>();
        Genero = genero;
        FechaLanzamiento = fechaLanzamiento;
    }
    //endregion

    //region Getters y Setters
    public String getSolo_Banda() { return Solo_Banda; }
    public void setSolo_Banda(String solo_Banda) { Solo_Banda = solo_Banda; }

    public ArrayList<String> getCanciones() { return Canciones; }
    public void setCanciones(ArrayList<String> canciones) { Canciones = canciones; }

    public GenerosD getGenero() { return Genero; }
    public void setGenero(GenerosD genero) { Genero = genero; }

    public LocalDate getFechaLanzamiento() { return FechaLanzamiento; }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { FechaLanzamiento = fechaLanzamiento; }
    //endregion

    @Override
    public void CrearArchivo() {

    }

    @Override
    public Seccion LeerArchivo() {
        return null;
    }

    @Override
    public void EscribirArchivo(Seccion datoDeSeccion) {

    }

    @Override
    public void CargarItems() {
        //Se carga un nuevo item al programa
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del disco: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del producto: ");
        float precio = scanner.nextFloat();

        System.out.println("Ingrese el nombre de la banda o del solista: ");
        String soloBanda = scanner.nextLine();

        //
        //Aca iria Añadir canciones
        //

        System.out.println("Ingrese la clasificacion de edad del disco: \s" +
                "1 - Niños." +
                "2 - Adolecentes." +
                "3 - Adultos.");
        int edad = scanner.nextInt();
        ClasificacionEdad clasEdad = null;
        switch (edad) {
            case 1 -> clasEdad = ClasificacionEdad.NINIOS;
            case 2 -> clasEdad = ClasificacionEdad.ADOLECENTES;
            case 3 -> clasEdad = ClasificacionEdad.ADULTOS;
            default -> System.out.println("Opcion no valida. Reintente");
        }

        System.out.println("Ingrese el genero del disco: \s" +
                "1 - Rock." +
                "2 - Pop." +
                "3 - Jazz." +
                "4 - Electronica." +
                "5 - Clasica." +
                "6 - Blues." +
                "7 - R&B." +
                "8 - Hip Hop.");
        int gen = scanner.nextInt();
        GenerosD genderD = null;
        switch (gen){
            case 1 -> genderD = GenerosD.ROCK;
            case 2 -> genderD = GenerosD.POP;
            case 3 -> genderD = GenerosD.JAZZ;
            case 4 -> genderD = GenerosD.ELECTRONICA;
            case 5 -> genderD = GenerosD.CLASICA;
            case 6 -> genderD = GenerosD.BLUES;
            case 7 -> genderD = GenerosD.RandB;
            case 8 -> genderD = GenerosD.HIP_HOP;
            default -> System.out.println("Opcion no valida. Reintente");
        }

        //
        //Aca va el local Date
        //

    }

    @Override
    public void MostrarListado() {

    }

    @Override
    public void BuscarItems() {

    }

    @Override
    public void Venta() {

    }

    @Override
    public void DarDeBaja() {

    }

    //region toString
    @Override
    public String toString() {
        return "Disco (" + super.toString() + ", Solista / Banda = " +
                this.Solo_Banda + ", Canciones = " + this.Canciones + ", Genero = " +
                this.Genero + ", Fecha de lanzamiento = " + this.FechaLanzamiento + ")";
    }
    //endregion
}
