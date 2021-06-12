package com.utn.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosL;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class Libro extends itemVenta{
    private GenerosL Genero;
    private String Autor;
    private String Editorial;

    //region Constructor

    public Libro(float precio, String nombre, ClasificacionEdad clasificacion, GenerosL genero, String autor, String editorial) {
        super(precio, nombre, clasificacion);
        Genero = genero;
        Autor = autor;
        Editorial = editorial;
    }

    public Libro(){
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
        try{
            BufferedWriter fSalida = new BufferedWriter(new FileWriter(new File("libros.json")));
            fSalida.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Seccion LeerArchivo() {
        Seccion<JuegoMesa> aux = new Seccion<>(50);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(new File("libros.json")));
            aux = gson.fromJson(reader,Seccion.class);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return aux;
    }

    @Override
    public void EscribirArchivo(Seccion datoDeSeccion) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        BufferedWriter guardar = null;

        try{
            guardar = new BufferedWriter(new FileWriter(new File("libros.json")));

            gson.toJson(datoDeSeccion, Seccion.class, guardar);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(guardar != null){
                try {
                    guardar.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void CargarItems() {
        //Aca se carga un item al listado
        String control;
        do {
            Scanner scanner = new Scanner (System.in);

            System.out.println("Ingrese El nombre del libro: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el nombre de la editorial: ");
            String editorial = scanner.nextLine();

            System.out.println("Ingrese el nombre del/la autor/autora: ");
            String autor = scanner.nextLine();

            System.out.println("Ingrese el precio del producto: ");
            float precio = scanner.nextFloat();

            //region ClasificacionPorEdad
            int Edad;
            ClasificacionEdad clasEdad = null;
            do{
                System.out.println("""
                    Ingrese la clasificacion de edad del libro:\s
                    1 - Niños.
                    2 - Adolecentes.
                    3 - Adultos.
                    """);
                Edad = scanner.nextInt();
                switch (Edad) {
                    case 1 -> clasEdad = ClasificacionEdad.NINIOS;
                    case 2 -> clasEdad = ClasificacionEdad.ADOLECENTES;
                    case 3 -> clasEdad = ClasificacionEdad.ADULTOS;
                    default -> System.out.println("Opcion no valida. Reintente");
                }
            }while(Edad != 1 && Edad != 2 && Edad != 3);
            //endregion

            //region ClasificacionPorGenero
            GenerosL generL = null;
            int gen;
            do {
                System.out.println("""
                        Ingrese el genero del libro: \s
                        1 - Accion.
                        2 - Aventura.
                        3 - Romance.
                        4 - Drama.
                        5 - Poesia.
                        6 - Medieval.
                        7 - Ciencia ficcion.
                        8 - Fantasia.
                        9 - Policial
                        """);
                gen = scanner.nextInt();
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

            }while(gen != 1 && gen != 2 && gen != 3 && gen != 4 && gen != 5 && gen != 6 && gen != 7 && gen != 8 && gen != 9);
            //endregion

            Libro libro = new Libro(precio,nombre,clasEdad,generL,autor,editorial);

            System.out.println("Desea cargar otro libro? s/n");
            control = scanner.next();

        }while(control.equalsIgnoreCase("S"));
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
