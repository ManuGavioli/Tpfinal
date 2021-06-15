package com.utn.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosD;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Disco extends itemVenta{

    private String Solo_Banda;
    private ArrayList<String> Canciones;
    private GenerosD Genero;
    private LocalDateTime FechaLanzamiento;

    //region Constructores

    public Disco(String solo_Banda, GenerosD genero, LocalDateTime fechaLanzamiento) {
        Solo_Banda = solo_Banda;
        Genero = genero;
        FechaLanzamiento = fechaLanzamiento;
    }

    public Disco(float precio, String nombre, ClasificacionEdad clasificacion, String solo_Banda, GenerosD genero, LocalDateTime fechaLanzamiento,int stock) {
        super(precio, nombre, clasificacion,stock);
        Solo_Banda = solo_Banda;
        Genero = genero;
        FechaLanzamiento = fechaLanzamiento;
    }

    public Disco(float precio, String nombre, ClasificacionEdad clasificacion, String solo_Banda,
                 ArrayList<String> canciones, GenerosD genero, LocalDateTime fechaLanzamiento,int stock) {
        super(precio, nombre, clasificacion,stock);
        Solo_Banda = solo_Banda;
        Canciones = canciones;
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

    public LocalDateTime getFechaLanzamiento() { return FechaLanzamiento; }
    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) { FechaLanzamiento = fechaLanzamiento; }
    //endregion

    public ArrayList <String> AñadirCanciones(){
        ArrayList <String> canciones = new ArrayList<>();
        String cancion;
        String control;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese una cancion del disco: ");
            cancion = scanner.nextLine();
            canciones.add(cancion);

            System.out.println("Desea cargar otra cancion? s/n");
            control = scanner.next();
        }while(control.equalsIgnoreCase("S"));
        return canciones;
    }

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
    public void VerificarStock(String nombre) {
        List <Disco> discos = LeerArchivo().getElementos();
        Iterator <Disco> iterator = discos.iterator();

        while (iterator.hasNext()){
            if(iterator.next().getNombre().equalsIgnoreCase(nombre)){
                if(iterator.next().getStock()<=0){
                    System.out.println("Las unidades de este produto se encuentran agotadas");
                }else{
                    System.out.println("Quedan en deposito "+iterator.next().getStock()+" unidades de este producto");
                }
            }
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
        } catch (FileNotFoundException e){
            return aux;
        } catch (IOException e){
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
        //Se carga un nuevo item al programa
        String control;
        ArrayList <String> canciones = new ArrayList<>();
        Seccion <Disco> seccionDiscos = new Seccion<>(50);
        do {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del disco: ");
            String nombre = scanner.nextLine();
            boolean flag = BuscarItems(nombre);

            if(!flag){
                System.out.println("Ingrese el nombre de la banda o del solista: ");
                String soloBanda = scanner.nextLine();

                System.out.println("Ingrese el precio del producto: ");
                float precio = scanner.nextFloat();

                System.out.println("Ingrese la cantidad de productos en stock: ");
                int stock = scanner.nextInt();

                canciones = AñadirCanciones();

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

                DateTimeFormatter formatterOfPatterns = DateTimeFormatter.ofPattern("d/M/u , h:m:s a");
                System.out.println("Ingrese la fecha de lanzamiento del disco (Dia/Mes/Año): ");
                String fechaAux = scanner.nextLine();
                LocalDateTime fecha = LocalDateTime.parse(fechaAux,formatterOfPatterns);
                //todo Arreglar localdatetime parse error

                Disco disco = new Disco(precio,nombre,clasEdad,soloBanda,canciones,genderD,fecha,stock);
                if(seccionDiscos.agregarElemento(disco)){
                    System.out.println("...Se agrego el disco en los elementos de la seccion...");
                }
            }else {
                List<Disco> discos = LeerArchivo().getElementos();
                Iterator<Disco> iterator = discos.iterator();
                System.out.println("El elemento : " + nombre + "Ya existe, ingrese la cantidad de produtos para añadir al stock: \s");
                int stock = scanner.nextInt();
                while (iterator.hasNext()) {
                    Disco disco = iterator.next();
                    if (disco.getNombre().equalsIgnoreCase(nombre)) {
                        stock += disco.getStock();
                        disco.setStock(stock);
                    }
                }
                seccionDiscos.setElementos(discos);
            }


            System.out.println("Desea cargar otro disco? s/n");
            control = scanner.next();
            EscribirArchivo(seccionDiscos);
        }while (control.equalsIgnoreCase("S"));


    }

    @Override
    public void MostrarListado() {
        List <Disco> discos = LeerArchivo().getElementos();

        for (var disco:discos){
            System.out.println(disco);
        }
    }

    @Override
    public boolean BuscarItems(String nombre) {
        boolean flag = false;
        List <Disco> discos = LeerArchivo().getElementos();
        Iterator <Disco> iterator = discos.iterator();

        while (iterator.hasNext() && !flag){
            if(iterator.next().getNombre().equalsIgnoreCase(nombre)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void Venta(String nombre) {
        Seccion<Disco> seccionD = new Seccion<>(50);
        List<Disco> discos = LeerArchivo().getElementos();
        Iterator <Disco> iterator = seccionD.iterator();

        while (iterator.hasNext()){
            Disco disco =iterator.next();
            if(disco.getNombre().equalsIgnoreCase(nombre)){
                int stock = disco.getStock();
                stock--;
                disco.setStock(stock);
            }
        }
        seccionD.setElementos(discos);
        EscribirArchivo(seccionD);
    }

    @Override
    public void DarDeBaja(String nombre) {
        Seccion<Disco> seccionD = LeerArchivo();
        List<Disco> discos = seccionD.getElementos();

        Iterator <Disco> iterator = discos.iterator();
        List<Disco> aux = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la cantidad de stock que desea dar de baja del producto: ");
        int stockABajar = scanner.nextInt();

        while (iterator.hasNext()){
            Disco disco = iterator.next();
            if(disco.getNombre().equalsIgnoreCase(nombre)){
                if (stockABajar >= disco.getStock()){
                    for (int i = 0;i<discos.size();i++){
                        if (!discos.get(i).getNombre().equalsIgnoreCase(nombre)){
                            aux.add(discos.get(i));
                        }
                    }
                    seccionD.setElementos(aux);
                }else {
                    int stock = disco.getStock();
                    stock -=stockABajar;
                    disco.setStock(stock);
                    seccionD.setElementos(discos);
                }
            }
        }
        EscribirArchivo(seccionD);
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
