package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;

import java.util.UUID;

public abstract class itemVenta {
    private UUID ID;
    private float precio;
    private int stock;
    private String nombre;
    private ClasificacionEdad clasificacion;

    //region Constructores
    public itemVenta() {

    }

    public itemVenta(float precio, String nombre, ClasificacionEdad clasificacion) {
        this.ID = UUID.randomUUID();
        this.precio = precio;
        this.stock = stock++;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
    }
    //endregion

    //region Setters y getters

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ClasificacionEdad getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionEdad clasificacion) {
        this.clasificacion = clasificacion;
    }

    //endregion

    public abstract void CrearArchivo();

    public abstract Seccion LeerArchivo();

    public abstract void EscribirArchivo(Seccion datoDeSeccion);

    public abstract void CargarItems();

    public abstract void MostrarListado();

    public abstract void BuscarItems();

    public abstract void Venta(UUID ID);

    public abstract void DarDeBaja(UUID ID);

    @Override
    public String toString() {
        return "Item(ID= " + this.ID + ", Nombre= " + this.nombre + ", Stock= " +
                this.stock + ", Clasificacion= " + this.clasificacion + ", Precio= " + this.precio + ")";
    }
}
