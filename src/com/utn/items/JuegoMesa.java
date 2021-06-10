package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosJM;

import java.util.UUID;

public class JuegoMesa extends itemVenta{

    private GenerosJM genero;

    //region Constructores
    public JuegoMesa(float precio, int stock, String nombre, ClasificacionEdad clasificacion, GenerosJM genero) {
        super(precio, stock, nombre, clasificacion);
        this.genero = genero;
    }

    //endregion

    //region GetterYSetters

    public GenerosJM getGenero() {
        return genero;
    }

    public void setGenero(GenerosJM genero) {
        this.genero = genero;
    }

    //endregion

    @Override
    public void CargarItems() {
        //Pide al staff que cargue un item

    }

    @Override
    public void MostrarListado() {
        //Mostrar el listado de juegos de mesa

    }

    @Override
    public void BuscarItems() {


    }

    @Override
    public void Venta() {

    }

    @Override
    public String toString() {
        return  "Juego de mesa (" + super.toString() +
                ", Genero= " + this.genero +
                ")";
    }
}
