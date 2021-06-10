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

    //region Constructores
    public Disco(float precio,String nombre, ClasificacionEdad clasificacion) {
        super(precio, nombre, clasificacion);
    }

    public Disco(float precio, String nombre, ClasificacionEdad clasificacion, String solo_Banda, ArrayList<String> canciones, GenerosD genero, LocalDate fechaLanzamiento) {
        super(precio, nombre, clasificacion);
        Solo_Banda = solo_Banda;
        Canciones = canciones;
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
        return "Disco (" + super.toString() + ", Solista / Banda = " +
                this.Solo_Banda + ", Canciones = " + this.Canciones + ", Genero = " +
                this.Genero + ", Fecha de lanzamiento = " + this.FechaLanzamiento + ")";
    }
}
