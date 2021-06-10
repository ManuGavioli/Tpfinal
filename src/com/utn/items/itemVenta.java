package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;

import java.util.UUID;

public abstract class itemVenta {
    private UUID ID;
    private float precio;
    private int stock;
    private String nombre;
    private ClasificacionEdad clasificacion;

    public abstract void CargarItems();

    public abstract void MostrarListado();

    public abstract void BuscarItems();

    public abstract void Venta();

}
