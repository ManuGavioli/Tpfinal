package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;

import java.util.UUID;

public abstract class itemVenta {
    private UUID ID;
    private double precio;
    private int stock = 0;
    private String nombre;
    private ClasificacionEdad clasificacion;

    //region Constructores
    public itemVenta() {

    }

    public itemVenta(double precio, String nombre, ClasificacionEdad clasificacion, int stock) {
        this.ID = UUID.randomUUID();
        this.precio = precio;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.stock = stock;
    }
    //endregion

    //region Setters y getters

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
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

    public abstract void VerificarStock();

    public abstract Seccion LeerArchivo();

    public abstract void EscribirArchivo(Seccion datoDeSeccion);

    public abstract void CargarItems();

    public abstract void MostrarListado();

    public abstract boolean BuscarItems(String nombre);

    public abstract void Venta(String name);

    public abstract void DarDeBaja();

    @Override
    public String toString() {
        return "ID= [" + this.ID + "], Nombre= " + this.nombre + ", Stock= " +
                this.stock + ", Clasificacion= " + this.clasificacion + ", Precio= $" + this.precio;
    }
}
