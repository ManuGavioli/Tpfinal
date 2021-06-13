package com.utn.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosD;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public class Disco extends itemVenta{

    private String Solo_Banda;
    private ArrayList<String> Canciones;
    private GenerosD Genero;
    private LocalDate FechaLanzamiento;

    //region Constructores

    public Disco(float precio,String nombre, ClasificacionEdad clasificacion) {
        super(precio, nombre, clasificacion);
    }

    public Disco(float precio, String nombre, ClasificacionEdad clasificacion, String solo_Banda, ArrayList<String> canciones, GenerosD genero, LocalDate fechaLanzamiento) {
        super(precio, nombre, clasificacion);
        Solo_Banda = solo_Banda;
        Canciones= new ArrayList<String>();
        Genero = genero;
        FechaLanzamiento = fechaLanzamiento;
    }

    public Disco(){

    }
    //endregion

    //region Getters y Setters
    public String getSolo_Banda() { return Solo_Banda; }
    public void setSolo_Banda(String solo_Banda) { Solo_Banda = solo_Banda; }

    public ArrayList<String> getCanciones() { return Canciones; }
    public void setCanciones(ArrayList<String> canciones) { Canciones = canciones; }

    public GenerosD getGenero() { return Genero; }
    public void setGenero(GenerosD genero) { Genero = genero; }

    public LocalDate getFechaLanzamiento() { return FechaLanzamiento; }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { FechaLanzamiento = fechaLanzamiento; }
    //endregion

    @Override
    public void CrearArchivo() {
        try{
            BufferedWriter fSalida = new BufferedWriter(new FileWriter(new File("discos.json")));
            fSalida.close();

        } catch(IOException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public Seccion LeerArchivo() {
        Seccion<Disco> aux = new Seccion<>(50);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type seccion = new TypeToken<Seccion<Disco>>() {}.getType();
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(new File("discos.json")));
            aux = gson.fromJson(reader,seccion);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return aux;
    }

    @Override
    public void EscribirArchivo(Seccion datoDeSeccion) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type seccion = new TypeToken<Seccion<Disco>>(){}.getType();
        BufferedWriter guardar = null;

        try{
            guardar = new BufferedWriter(new FileWriter(new File("discos.json")));

            gson.toJson(datoDeSeccion, seccion, guardar);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
        //Se carga un nuevo item al programa
        String control;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del disco: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el precio del producto: ");
            float precio = scanner.nextFloat();

            System.out.println("Ingrese el nombre de la banda o del solista: ");
            String soloBanda = scanner.nextLine();

            //
            //Aca iria Añadir canciones
            //

            //region ClasificacionPorEdad
            int Edad;
            ClasificacionEdad clasEdad = null;
            do{
                System.out.println("""
                Ingrese la clasificacion de edad del disco:\s
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
            GenerosD genderD = null;
            int gen;
            do {
                System.out.println("""
                        Ingrese el genero del disco: \s
                        1 - Rock.
                        2 - Pop.
                        3 - Jazz.
                        4 - Electronica.
                        5 - Clasica.
                        6 - Blues.
                        7 - R&B.
                        8 - Hip Hop.""");
                gen = scanner.nextInt();
                switch (gen){
                    case 1 -> genderD = GenerosD.ROCK;
                    case 2 -> genderD = GenerosD.POP;
                    case 3 -> genderD = GenerosD.JAZZ;
                    case 4 -> genderD = GenerosD.ELECTRONICA;
                    case 5 -> genderD = GenerosD.CLASICA;
                    case 6 -> genderD = GenerosD.BLUES;
                    case 7 -> genderD = GenerosD.RandB;
                    case 8 -> genderD = GenerosD.HIP_HOP;
                    default -> System.out.println("Opcion no valida. Reintente");
                }
            }while(gen != 1 && gen != 2 && gen != 3 && gen != 4 && gen != 5 && gen != 6 && gen != 7 && gen != 8);
            //endregion

            //
            //Aca va el local Date
            //

            //Disco disco = new Disco(CON LOS PARAMETROS ACA PRIMERO HACER LOCALDATE Y AÑADIR CANCIONES);

            System.out.println("Desea cargar otro libro? s/n");
            control = scanner.next();

        }while (control.equalsIgnoreCase("S"));

    }

    @Override
    public void MostrarListado() {
        List<Disco> discos = LeerArchivo().getElementos();

        Iterator<Disco> iterator = discos.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Override
    public void BuscarItems() {
        boolean flag = false;
        List <Disco> discos = LeerArchivo().getElementos();
        Iterator <Disco> iterator = discos.iterator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del libro, autor o editorial de este: \s");
        String dato = scanner.nextLine();

        while (iterator.hasNext()){
            if(iterator.next().getNombre() == dato || iterator.next().getSolo_Banda() == dato){
                System.out.println(iterator.next());
            }
        }
    }

    @Override
    public void Venta(UUID ID) {
        Seccion<Disco> seccionD = LeerArchivo();
        List<Disco> discos = seccionD.getElementos();

        for (var disco : discos){
            if (disco.getID() == ID){
                setStock(-1);
            }
        }
        seccionD.setElementos(discos);


    }

    @Override
    public void DarDeBaja(UUID ID) {
        Seccion<Disco> seccionD = LeerArchivo();
        List<Disco> discos = seccionD.getElementos();
        List<Disco> aux = new ArrayList<Disco>();
        for (var disco : discos){
            if (disco.getID() != ID){
                aux.add(disco);
            }
        }
        seccionD.setElementos(aux);
    }

    //region toString
    @Override
    public String toString() {
        return "Disco (" + super.toString() + ", Solista / Banda = " +
                this.Solo_Banda + ", Canciones = " + this.Canciones + ", Genero = " +
                this.Genero + ", Fecha de lanzamiento = " + this.FechaLanzamiento + ")";
    }
    //endregion
}
