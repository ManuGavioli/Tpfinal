package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosL;

import java.util.UUID;

public class Libro extends itemVenta{
    private GenerosL Genero;
    private String Autor;
    private String Editorial;

    //Constructor

    public Libro(UUID ID, float precio, int stock, String nombre,
                 ClasificacionEdad clasificacion, GenerosL genero,
                 String autor, String editorial) {
        super(ID, precio, stock, nombre, clasificacion);
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
}
