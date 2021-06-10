package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosL;

import java.util.UUID;

public class Libro extends itemVenta{
    private GenerosL Genero;
    private String Autor;
    private String Editorial;

    //Constructor

    public Libro(float precio, int stock, String nombre,
                 ClasificacionEdad clasificacion, GenerosL genero,
                 String autor, String editorial) {
        super(precio, stock, nombre, clasificacion);
        Genero = genero;
        Autor = autor;
        Editorial = editorial;
    }
    //Setters y Getters

    @Override
    public void CargarItems() {

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
    public String toString() {
        return "Libro (" + super.toString() + ", Genero= " + this.Genero + ", Autor=" + this.Autor + ", Editorial=" + this.Editorial + ")";
    }
}
