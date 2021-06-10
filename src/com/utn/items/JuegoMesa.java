package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosJM;

import java.util.Scanner;
import java.util.UUID;

public class JuegoMesa extends itemVenta{

    private GenerosJM genero;

    //region Constructores
    public JuegoMesa(float precio, String nombre, ClasificacionEdad clasificacion, GenerosJM genero) {
        super(precio, nombre, clasificacion);
        this.genero = genero;
    }

    public JuegoMesa() {
    }

    //endregion

    //region GetterYSetters

    public GenerosJM getGenero() {
        return genero;
    }

    public void setGenero(GenerosJM genero) {
        this.genero = genero;
    }

    //endregion

    @Override
    public void CargarItems() {
        //Pide al staff que cargue un item
        Scanner scanner= new Scanner(System.in);
        System.out.println("Ingrese el nombre del juego de mesa: ");
        String nombre = scanner.nextLine(); //no sabemos si es nextline

        System.out.println("Ingrese el precio del juego de mesa: ");
        float numero = scanner.nextFloat();

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

        System.out.println("""
                Ingrese el genero del juego de mesa:\s
                1 - Puzzle.
                2 - Cartas.
                3 - Dados.
                4 - Fiesta.
                5 - Rol.
                """);
        int gen = scanner.nextInt();
        GenerosJM genderJM = null;
        switch (gen) {
            case 1 -> genderJM = GenerosJM.PUZZLE;
            case 2 -> genderJM = GenerosJM.CARTAS;
            case 3 -> genderJM = GenerosJM.DADOS;
            case 4 -> genderJM = GenerosJM.FIESTA;
            case 5 -> genderJM = GenerosJM.ROL;
            default -> System.out.println("Opcion no valida. Reintente");
        }
        JuegoMesa juego=new JuegoMesa(numero,nombre,clasEdad,genderJM);
        
    }

    @Override
    public void MostrarListado() {
        //Mostrar el listado de juegos de mesa

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

    @Override
    public String toString() {
        return  "Juego de mesa (" + super.toString() +
                ", Genero= " + this.genero +
                ")";
    }
}
