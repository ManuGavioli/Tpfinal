package com.utn.items;

import java.util.ArrayList;
import java.util.List;

public class Seccion<T> {
    private List<T> elementos = new ArrayList<>();
    private int tope;

    private Seccion(int tope){
        this.tope = tope;
    }

    public boolean agregarElemento(T t) {
        if (tope < elementos.size()) {
            elementos.add(t);
            return true;
        }
        return false;
    }
}
