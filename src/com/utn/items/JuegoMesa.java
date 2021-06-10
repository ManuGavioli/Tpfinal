package com.utn.items;

import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosJM;

import java.util.UUID;

public class JuegoMesa extends itemVenta{

    private GenerosJM genero;

    public JuegoMesa(UUID ID, float precio, int stock, String nombre, ClasificacionEdad clasificacion, GenerosJM genero) {
        super(ID, precio, stock, nombre, clasificacion);
        this.genero = genero;
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
