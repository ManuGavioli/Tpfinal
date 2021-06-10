package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import org.w3c.dom.ls.LSOutput;

import java.util.UUID;

public abstract class itemVenta {
    private UUID ID;
    private float precio;
    private int stock;
    private String nombre;
    private ClasificacionEdad clasificacion;

    public itemVenta(float precio, int stock, String nombre, ClasificacionEdad clasificacion) {
        this.ID = UUID.randomUUID();
        this.precio = precio;
        this.stock = stock;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
    }

    public abstract void CargarItems();

    public abstract void MostrarListado();

    public abstract void BuscarItems();

    public abstract void Venta();

    @Override
    public String toString() {
        return "Item(ID= " + this.ID + ", Nombre= " + this.nombre + ", Stock= " +
                this.stock + ", Clasificacion= " + this.clasificacion + ", Precio= " + this.precio + ")";
    }
}
