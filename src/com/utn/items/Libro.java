package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosL;

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
    public void DarDeBaja() {

    }

    @Override
    public String toString() {
        return "Libro (" + super.toString() + ", Genero= " + this.Genero + ", Autor=" + this.Autor + ", Editorial=" + this.Editorial + ")";
    }
}
