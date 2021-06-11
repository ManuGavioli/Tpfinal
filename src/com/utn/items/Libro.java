package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosL;

import java.util.Scanner;
import java.util.UUID;

public class Libro extends itemVenta{
    private GenerosL Genero;
    private String Autor;
    private String Editorial;

    //region Constructor

    public Libro(float precio, String nombre,
                 ClasificacionEdad clasificacion, GenerosL genero,
                 String autor, String editorial) {
        super(precio, nombre, clasificacion);
        Genero = genero;
        Autor = autor;
        Editorial = editorial;
    }

    //endregion

    //region Getters y Setters

    public GenerosL getGenero() {
        return Genero;
    }
    public void setGenero(GenerosL genero) {
        Genero = genero;
    }

    public String getAutor() {
        return Autor;
    }
    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getEditorial() {
        return Editorial;
    }
    public void setEditorial(String editorial) {
        Editorial = editorial;
    }

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
        //Aca se carga un item al listado
        Scanner scanner = new Scanner (System.in);

        System.out.println("Ingrese El nombre del libro: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el nombre de la editorial: ");
        String editorial = scanner.nextLine();

        System.out.println("Ingrese el nombre del/la autor/autora: ");
        String autor = scanner.nextLine();

        System.out.println("Ingrese el precio del producto: ");
        float precio = scanner.nextFloat();

        System.out.println("""
                Ingrese la clasificacion de edad del juego de mesa:\s
                1 - Niños.
                2 - Adolecentes.
                3 - Adultos.
                """);
        int Edad = scanner.nextInt();
        ClasificacionEdad clasEdad = null;
        switch (Edad) {
            case 1 -> clasEdad = ClasificacionEdad.NINIOS;
            case 2 -> clasEdad = ClasificacionEdad.ADOLECENTES;
            case 3 -> clasEdad = ClasificacionEdad.ADULTOS;
            default -> System.out.println("Opcion no valida. Reintente");
        }

        System.out.println("Ingrese el genero del libro: \s" +
                "1 - Accion." +
                "2 - Aventura." +
                "3 - Romance." +
                "4 - Drama." +
                "5 - Poesia." +
                "6 - Medieval." +
                "7 - Ciencia ficcion." +
                "8 - Fantasia." +
                "9 - Policial");
        int gen = scanner.nextInt();
        GenerosL generL = null;
        switch (gen){
            case 1 -> generL = GenerosL.ACCION;
            case 2 -> generL = GenerosL.AVENTURA;
            case 3 -> generL = GenerosL.ROMANCE;
            case 4 -> generL = GenerosL.DRAMA;
            case 5 -> generL = GenerosL.POESIA;
            case 6 -> generL = GenerosL.MEDIEVAL;
            case 7 -> generL = GenerosL.CIENCIA_FICCION;
            case 8 -> generL = GenerosL.FANTASIA;
            case 9 -> generL = GenerosL.POLICIAL;
            default -> System.out.println("Opcion no valida. Reintente");
        }
        Libro libro = new Libro(precio,nombre,clasEdad,generL,autor,editorial);
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
        return "Libro (" + super.toString() + ", Genero= " + this.Genero + ", Autor=" + this.Autor + ", Editorial=" + this.Editorial + ")";
    }
    //endregion
}
