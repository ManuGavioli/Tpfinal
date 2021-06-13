package com.utn.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Seccion<T> {
    private List<T> elementos = new ArrayList<>();
    private int tope;

    public Seccion(int tope){
        this.tope = tope;
    }

    //region Getters y Setters
    public List<T> getElementos() {
        return elementos;
    }

    public void setElementos(List<T> elementos) {
        this.elementos = elementos;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }
    //endregion

    ///todo Probar sacando tope

    public boolean agregarElemento(T t) {
        if (tope < elementos.size()) {
            elementos.add(t);
            return true;
        }
        return false;
    }

    public Iterator < T > iterator(){
        return elementos.iterator();
    }

}
