package com.utn.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.utn.Menu.Usuarios.Cliente;
import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosJM;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JuegoMesa extends itemVenta{

    private GenerosJM genero;

    //region Constructores
    public JuegoMesa(double precio, String nombre, ClasificacionEdad clasificacion, GenerosJM genero,int stock) {
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
    public void VerificarStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del juego de mesa del cual desea saber el stock");
        String nombre = scanner.nextLine();
        boolean flag = false;

        List <JuegoMesa> juegos = LeerArchivo().getElementos();
        Iterator <JuegoMesa> iterator = juegos.iterator();

        while (iterator.hasNext()){
            JuegoMesa juego = iterator.next();
            if(juego.getNombre().equalsIgnoreCase(nombre)){
                flag = true;
                if(juego.getStock() >= 1){
                    System.out.println("Quedan en deposito " + juego.getStock() + " unidades de este juego de mesa");
                }else{
                    System.out.println("Las unidades de este juego de mesa se encuentran agotadas");
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
        Type seccion = new TypeToken<Seccion<JuegoMesa>>() {}.getType();
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader("juegosDeMesa.json"));
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
        Type seccion = new TypeToken<Seccion<JuegoMesa>>(){}.getType();
        BufferedWriter guardar = null;

        try {
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
                double numero = scanner.nextDouble();

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
            }else{
                List<JuegoMesa> juegos = LeerArchivo().getElementos();
                Iterator<JuegoMesa> iterator = juegos.iterator();
                System.out.println("El elemento : " + nombre + " ya existe, ingrese la cantidad de produtos para añadir al stock: \s");
                int stock = scanner.nextInt();
                while (iterator.hasNext()){
                    JuegoMesa juego = iterator.next();
                    if(juego.getNombre().equalsIgnoreCase(nombre)){
                        stock += juego.getStock();
                        juego.setStock(stock);
                    }
                }
                juegosDeMesa.setElementos(juegos);
            }
            System.out.println("\nDesea cargar otro juego? s/n");
            control = scanner.next();
            EscribirArchivo(juegosDeMesa);
        }while(control.equalsIgnoreCase("S"));
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
            JuegoMesa juego = iterator.next();
            if(juego.getNombre().equalsIgnoreCase(nombre)){
                System.out.println(juego);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void Venta(String name) {

        boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        Seccion<JuegoMesa> juegos = new Seccion<>(50);

        HashMap<Integer, Cliente> map= Cliente.Leer();
        Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

        List<JuegoMesa> seccionJM = LeerArchivo().getElementos();
        Iterator <JuegoMesa> iterator = seccionJM.iterator();

        System.out.println("Escriba el nombre del libro a comprar: ");
        String nombre = scanner.nextLine();

        while(mapIterator.hasNext() && !flag){
            Cliente client = mapIterator.next().getValue();

            if(client.getNombreUsuario().equalsIgnoreCase(name)) {
                while (iterator.hasNext()) {
                    JuegoMesa juego = iterator.next();
                    if (juego.getNombre().equalsIgnoreCase(nombre)) {
                        if (client.getCartera() >= juego.getPrecio()) {
                            if (juego.getStock() >= 1) {
                                int stock = juego.getStock();
                                stock--;
                                juego.setStock(stock);

                                double valor = (client.getCartera() - juego.getPrecio());
                                client.setCartera(valor);
                                System.out.println("Gracias por su compra :) ");
                            } else {
                                System.out.println("No queda stock del producto...");

                            }

                        } else {
                            System.out.println("La cartera no posee el saldo necesario para comprar el producto...");
                        }
                        flag = true;

                    }
                }
            }

        }
        if(!flag){
            System.out.println("El nombre del producto no se encuentra...");
        }

        Cliente.CargarMapaArchivo(map);
        juegos.setElementos(seccionJM);
        EscribirArchivo(juegos);
    }

    @Override
    public void DarDeBaja() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del juego de mesa que desea dar de baja");
        String nombre = scanner.nextLine();

        Seccion<JuegoMesa> seccionJM = LeerArchivo();
        List<JuegoMesa> juegos = seccionJM.getElementos();

        Iterator<JuegoMesa> iterator = juegos.iterator();
        List<JuegoMesa> aux = new ArrayList<>();

        System.out.println("Ingrese la cantidad de stock que desea dar de baja del producto: ");
        int stockABajar = scanner.nextInt();

        boolean flag = false;

        while(iterator.hasNext() && !flag){
            JuegoMesa juego = iterator.next();
            if (juego.getNombre().equalsIgnoreCase(nombre)){
                if(stockABajar >= juego.getStock()){
                    for (int i = 0;i<juegos.size();i++){
                        if (!juegos.get(i).getNombre().equalsIgnoreCase(nombre)){
                            aux.add(juegos.get(i));
                        }
                    }
                    seccionJM.setElementos(aux);
                }else{
                    int stock = juego.getStock();
                    stock -= stockABajar;
                    juego.setStock(stock);
                    seccionJM.setElementos(juegos);
                }
                System.out.println("Se dio de baja exitosamente");
                flag = true;
            }
        }
        if(!flag){
            System.out.println("El nombre del producto no se encuentra en el archivo...");
        }
        EscribirArchivo(seccionJM);
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
