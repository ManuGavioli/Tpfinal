package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosL;

import java.util.UUID;

public class Libro extends itemVenta{
    private GenerosL Genero;
    private String Autor;
    private String Editorial;

    //Constructor
    public Libro(UUID ID, float precio, int stock, String nombre, ClasificacionEdad
            clasificacion, GenerosL genero, String autor, String editorial) {

        super(ID, precio, stock, nombre, clasificacion);
        Genero = genero;
        Autor = autor;
        Editorial = editorial;
    }

    //Getters y Setters
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
