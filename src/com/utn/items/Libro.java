package com.utn.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosL;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Libro extends itemVenta{
    private GenerosL Genero;
    private String Autor;
    private String Editorial;

    //region Constructor

    public Libro(float precio, String nombre, ClasificacionEdad clasificacion, GenerosL genero, String autor, String editorial,int stock) {
        super(precio, nombre, clasificacion,stock);
        Genero = genero;
        Autor = autor;
        Editorial = editorial;
    }

    public Libro(){
    }

    //endregion

    //region Getters y Setters

    public GenerosL getGenero() {
        return Genero;
    }
    public void setGenero(GenerosL genero) {
        Genero = genero;
    }

    public String getAutor() {
        return Autor;
    }
    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getEditorial() {
        return Editorial;
    }
    public void setEditorial(String editorial) {
        Editorial = editorial;
    }

    //endregion

    @Override
    public void CrearArchivo() {
        try{
            BufferedWriter fSalida = new BufferedWriter(new FileWriter(new File("libros.json")));
            fSalida.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void VerificarStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del libro del cual desea saber el stock");
        String nombre = scanner.nextLine();
        boolean flag = false;

        List <Libro> libros = LeerArchivo().getElementos();
        Iterator <Libro> iterator = libros.iterator();

        while (iterator.hasNext()){
            Libro libro = iterator.next();
            if(libro.getNombre().equalsIgnoreCase(nombre)){
                flag = true;
                if(libro.getStock() >= 1){
                    System.out.println("Quedan en deposito "+libro.getStock()+" unidades de este producto");
                }else{
                    System.out.println("Las unidades de este produto se encuentran agotadas");
                }
            }
        }
        if(!flag){
            System.out.println("No se encontro el nombre en el archivo");
        }
    }

    @Override
    public Seccion LeerArchivo() {
        Seccion<JuegoMesa> aux = new Seccion<>(50);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type seccion = new TypeToken<Seccion<Libro>>() {}.getType();
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(new File("libros.json")));
            aux = gson.fromJson(reader,seccion);
        } catch (FileNotFoundException e){
            return aux;
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return aux;
    }

    @Override
    public void EscribirArchivo(Seccion datoDeSeccion) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type seccion = new TypeToken<Seccion<Libro>>(){}.getType();
        BufferedWriter guardar = null;

        try{
            guardar = new BufferedWriter(new FileWriter(new File("libros.json")));

            gson.toJson(datoDeSeccion, seccion, guardar);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(guardar != null){
                try {
                    guardar.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void CargarItems() {
        //Aca se carga un item al listado
        String control;
        Seccion <Libro> seccionLibros = new Seccion<>(50);

        do {
            Scanner scanner = new Scanner (System.in);
            System.out.println("Ingrese el nombre del libro: ");
            String nombre = scanner.nextLine();
            boolean flag = BuscarItems(nombre);

            if(!flag){
                System.out.println("Ingrese el nombre de la editorial: ");
                String editorial = scanner.nextLine();

                System.out.println("Ingrese el nombre del/la autor/autora: ");
                String autor = scanner.nextLine();

                System.out.println("Ingrese el precio del producto: ");
                float precio = scanner.nextFloat();

                System.out.println("Ingrese la cantidad de productos en stock");
                int stock = scanner.nextInt();

                //region ClasificacionPorEdad
                int Edad;
                ClasificacionEdad clasEdad = null;
                do{
                    System.out.println("""
                    Ingrese la clasificacion de edad del libro:\s
                    1 - Niños.
                    2 - Adolecentes.
                    3 - Adultos.
                    """);
                    Edad = scanner.nextInt();
                    switch (Edad) {
                        case 1 -> clasEdad = ClasificacionEdad.NINIOS;
                        case 2 -> clasEdad = ClasificacionEdad.ADOLECENTES;
                        case 3 -> clasEdad = ClasificacionEdad.ADULTOS;
                        default -> System.out.println("Opcion no valida. Reintente");
                    }
                }while(Edad != 1 && Edad != 2 && Edad != 3);
                //endregion

                //region ClasificacionPorGenero
                GenerosL generL = null;
                int gen;
                do {
                    System.out.println("""
                        Ingrese el genero del libro: \s
                        1 - Accion.
                        2 - Aventura.
                        3 - Romance.
                        4 - Drama.
                        5 - Poesia.
                        6 - Medieval.
                        7 - Ciencia ficcion.
                        8 - Fantasia.
                        9 - Policial
                        """);
                    gen = scanner.nextInt();
                    switch (gen){
                        case 1 -> generL = GenerosL.ACCION;
                        case 2 -> generL = GenerosL.AVENTURA;
                        case 3 -> generL = GenerosL.ROMANCE;
                        case 4 -> generL = GenerosL.DRAMA;
                        case 5 -> generL = GenerosL.POESIA;
                        case 6 -> generL = GenerosL.MEDIEVAL;
                        case 7 -> generL = GenerosL.CIENCIA_FICCION;
                        case 8 -> generL = GenerosL.FANTASIA;
                        case 9 -> generL = GenerosL.POLICIAL;
                        default -> System.out.println("Opcion no valida. Reintente");
                    }

                }while(gen != 1 && gen != 2 && gen != 3 && gen != 4 && gen != 5 && gen != 6 && gen != 7 && gen != 8 && gen != 9);
                //endregion

                Libro libro = new Libro(precio,nombre,clasEdad,generL,autor,editorial,stock);

                if(seccionLibros.agregarElemento(libro)){
                    System.out.println("...Se agrego el libro en los elementos de la seccion...");
                }
            }else {
                List<Libro> libros = LeerArchivo().getElementos();
                Iterator<Libro> iterator = libros.iterator();
                System.out.println("El elemento : "+nombre+ "Ya existe, ingrese la cantidad de produtos para añadir al stock: \s");
                int stock = scanner.nextInt();

                while (iterator.hasNext()){
                    Libro libro = iterator.next();
                    if(libro.getNombre().equalsIgnoreCase(nombre)){
                        stock += libro.getStock();
                        libro.setStock(stock);
                    }
                }
                seccionLibros.setElementos(libros);
            }
            System.out.println("Desea cargar otro libro? s/n");
            control = scanner.next();
            EscribirArchivo(seccionLibros);
        }while(control.equalsIgnoreCase("S"));
    }

    @Override
    public void MostrarListado() {
        List <Libro> libros = LeerArchivo().getElementos();

        for (var libro:libros){
            System.out.println(libro);
        }
    }

    @Override
    public boolean BuscarItems(String nombre) {
        boolean flag = false;
        List <Libro> libros = LeerArchivo().getElementos();
        Iterator <Libro> iterator = libros.iterator();

        while (iterator.hasNext() && !flag){
            if(iterator.next().getNombre().equalsIgnoreCase(nombre)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void Venta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el nombre del libro a comprar");
        String nombre = scanner.nextLine();

        Seccion<Libro> seccionL = LeerArchivo();
        List<Libro> libros = seccionL.getElementos();
        Iterator<Libro> iterator = seccionL.iterator();

        while (iterator.hasNext()){
            Libro libro = iterator.next();
            if (libro.getNombre().equalsIgnoreCase(nombre)){
                int stock = libro.getStock();
                stock--;
                libro.setStock(stock);
            }
        }
        seccionL.setElementos(libros);
        EscribirArchivo(seccionL);
    }

    @Override
    public void DarDeBaja() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el nombre del libro que desea dar de baja");
        String nombre = scanner.nextLine();

        Seccion<Libro> seccionL = LeerArchivo();
        List<Libro> libros = seccionL.getElementos();
        List<Libro> aux = new ArrayList<>();
        Iterator<Libro> iterator = libros.iterator();;

        System.out.println("Ingrese la cantidad de stock que desea dar de baja del producto:");
        int stockABajar = scanner.nextInt();

        while (iterator.hasNext()){
            Libro libro = iterator.next();
            if (libro.getNombre().equalsIgnoreCase(nombre)){
                if(stockABajar >= libro.getStock()){
                    for (int i = 0; i < libros.size();i++){
                        if(!libros.get(i).getNombre().equalsIgnoreCase(nombre)){
                            aux.add(libros.get(i));
                        }
                    }
                    seccionL.setElementos(aux);
                }else {
                    int stock = libro.getStock();
                    stock -= stockABajar;
                    libro.setStock(stock);
                    seccionL.setElementos(libros);
                }
            }
        }
        EscribirArchivo(seccionL);
    }



    //region toString
    @Override
    public String toString() {
        return "Libro (" + super.toString() + ", Genero= " + this.Genero + ", Autor=" + this.Autor + ", Editorial=" + this.Editorial + ")";
    }
    //endregion
}
