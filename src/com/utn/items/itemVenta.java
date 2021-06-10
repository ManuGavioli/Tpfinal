package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;

import java.util.UUID;

public abstract class itemVenta {
    private UUID ID;
    private float precio;
    private int stock;
    private String nombre;
    private ClasificacionEdad clasificacion;

    public itemVenta(UUID ID, float precio, int stock, String nombre, ClasificacionEdad clasificacion) {
        this.ID = ID;
        this.precio = precio;
        this.stock = stock;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
    }

    public abstract void CargarItems();

    public abstract void MostrarListado();

    public abstract void BuscarItems();

    public abstract void Venta();

}
