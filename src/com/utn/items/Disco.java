package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosD;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Disco extends itemVenta{

    private String Solo_Banda;
    private ArrayList<String> Canciones;
    private GenerosD Genero;
    private LocalDate FechaLanzamiento;


    ///Construcctores
    public Disco(float precio, int stock, String nombre, ClasificacionEdad clasificacion) {
        super(precio, stock, nombre, clasificacion);
    }

    public Disco(float precio, int stock, String nombre, ClasificacionEdad clasificacion, String solo_Banda, ArrayList<String> canciones, GenerosD genero, LocalDate fechaLanzamiento) {
        super(precio, stock, nombre, clasificacion);
        Solo_Banda = solo_Banda;
        Canciones = canciones;
        Genero = genero;
        FechaLanzamiento = fechaLanzamiento;
    }

    ///Getters y Setters
    public String getSolo_Banda() { return Solo_Banda; }
    public void setSolo_Banda(String solo_Banda) { Solo_Banda = solo_Banda; }

    public ArrayList<String> getCanciones() { return Canciones; }
    public void setCanciones(ArrayList<String> canciones) { Canciones = canciones; }

    public GenerosD getGenero() { return Genero; }
    public void setGenero(GenerosD genero) { Genero = genero; }

    public LocalDate getFechaLanzamiento() { return FechaLanzamiento; }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { FechaLanzamiento = fechaLanzamiento; }

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
        return super.toString();
    }
}
