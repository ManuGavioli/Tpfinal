package com.utn.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosJM;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JuegoMesa extends itemVenta{

    private GenerosJM genero;

    //region Constructores
    public JuegoMesa(float precio, String nombre, ClasificacionEdad clasificacion, GenerosJM genero,int stock) {
        super(precio, nombre, clasificacion,stock);
        this.genero = genero;
    }

    public JuegoMesa() {
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
    public void CrearArchivo() {
        try{
            BufferedWriter fSalida = new BufferedWriter(new FileWriter("juegosDeMesa.json"));
            fSalida.close();

        } catch(IOException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public Seccion LeerArchivo() {
        Seccion<JuegoMesa> aux = new Seccion<>(50);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type seccion = new TypeToken<Seccion<JuegoMesa>>() {}.getType();
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader("juegosDeMesa.json"));
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
        Type seccion = new TypeToken<Seccion<JuegoMesa>>(){}.getType();
        BufferedWriter guardar = null;

        try{
            guardar = new BufferedWriter(new FileWriter("juegosDeMesa.json"));

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
        //Pide al staff que cargue un item
        String control = ".";
        Seccion <JuegoMesa> juegosDeMesa = new Seccion<>(50);

        do{
            Scanner scanner= new Scanner(System.in);

            System.out.println("Ingrese el nombre del juego de mesa: ");
            String nombre = scanner.nextLine();

            boolean flag = BuscarItems(nombre);

            if(!flag){
                System.out.println("Ingrese el precio del juego de mesa: ");
                float numero = scanner.nextFloat();

                System.out.println("Ingrese la cantidad de productos en stock: ");
                int stock = scanner.nextInt();

                //region ClasificacionPorEdad
                int Edad;
                ClasificacionEdad clasEdad = null;
                do{
                    System.out.println("""
                Ingrese la clasificacion de edad del juego de mesa:\s
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
                int gen;
                GenerosJM genderJM = null;
                do {
                    System.out.println("""
                Ingrese el genero del juego de mesa:\s
                1 - Puzzle.
                2 - Cartas.
                3 - Dados.
                4 - Fiesta.
                5 - Rol.
                """);
                    gen = scanner.nextInt();
                    switch (gen) {
                        case 1 -> genderJM = GenerosJM.PUZZLE;
                        case 2 -> genderJM = GenerosJM.CARTAS;
                        case 3 -> genderJM = GenerosJM.DADOS;
                        case 4 -> genderJM = GenerosJM.FIESTA;
                        case 5 -> genderJM = GenerosJM.ROL;
                        default -> System.out.println("Opcion no valida. Reintente");
                    }
                }while(gen != 1 && gen != 2 && gen != 3 && gen != 4 && gen != 5);

                //endregion

                JuegoMesa juego = new JuegoMesa(numero,nombre,clasEdad,genderJM,stock);
                if(juegosDeMesa.agregarElemento(juego)){
                    System.out.println("...Se agrego el juego en los elementos de la seccion...");
                }

                System.out.println("\nDesea cargar otro juego? s/n");
                control = scanner.next();
            }else{
                List<JuegoMesa> juegos = LeerArchivo().getElementos();
                Iterator<JuegoMesa> iterator = juegos.iterator();
                System.out.println("El elemento : "+nombre+ "Ya existe, ingrese la cantidad de produtos para añadir al stock: \s");
                int stock = scanner.nextInt();
                while (iterator.hasNext()){
                    if(iterator.next().getNombre().equalsIgnoreCase(nombre)){
                        iterator.next().setStock((this.getStock()+stock));
                    }
                }
            }

        }while(control.equalsIgnoreCase("S"));

        EscribirArchivo(juegosDeMesa);
    }

    @Override
    public void MostrarListado() {
        List <JuegoMesa> juegos = LeerArchivo().getElementos();

        for (var juego:juegos){
            System.out.println(juego);
        }
    }

    @Override
    public boolean BuscarItems(String nombre) {
        boolean flag = false;
        List <JuegoMesa> juegos = LeerArchivo().getElementos();
        Iterator <JuegoMesa> iterator = juegos.iterator();

        while (iterator.hasNext() && !flag){
            if(iterator.next().getNombre().equalsIgnoreCase(nombre)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void Venta(UUID ID) {
        Seccion<JuegoMesa> seccionJM = LeerArchivo();
        List<JuegoMesa> juegos = seccionJM.getElementos();
        int tope = seccionJM.getTope();

        for (int i =0 ; i<tope;i++){
            if (juegos.get(i).getID() == ID){
                setStock((this.getStock()-1));
            }
        }
        seccionJM.setElementos(juegos);
    }

    @Override
    public void DarDeBaja(UUID ID) {
        Seccion<JuegoMesa> seccionJM = LeerArchivo();
        List<JuegoMesa> juegos = seccionJM.getElementos();
        List<JuegoMesa> aux = new ArrayList<>();
        int tope = seccionJM.getTope();
        for (int i = 0;i<tope;i++){
            if (juegos.get(i).getID() != ID){
                aux.add(juegos.get(i));
            }
        }
        seccionJM.setElementos(aux);
    }

    //region toString
    @Override
    public String toString() {
        return  "Juego de mesa = {" + super.toString() +
                ", Genero= " + this.genero +
                "}";
    }
    //endregion
}
