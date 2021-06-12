package com.utn.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.items.enums.ClasificacionEdad;
import com.utn.items.enums.GenerosJM;
import java.io.*;
import java.util.Scanner;

public class JuegoMesa extends itemVenta{

    private GenerosJM genero;

    //region Constructores
    public JuegoMesa(float precio, String nombre, ClasificacionEdad clasificacion, GenerosJM genero) {
        super(precio, nombre, clasificacion);
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
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(new File("juegosDeMesa.json")));
            aux = gson.fromJson(reader,Seccion.class);
        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo correspondiente");
            CrearArchivo();
            System.out.println("Creando el archivo");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        /** try {
            FileInputStream fileInputStream = new FileInputStream("juegosDeMesa.json");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            aux = (Seccion<JuegoMesa>) objectInputStream.readObject();

        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo...");
            return null;

        } catch(IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro la clase...");
        }
*/
        return aux;
    }

    @Override
    public void EscribirArchivo(Seccion datoDeSeccion) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        BufferedWriter guardar = null;

        try{
            guardar = new BufferedWriter(new FileWriter("juegosDeMesa.json"));

            gson.toJson(datoDeSeccion, Seccion.class, guardar);
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



        /**try{
            FileOutputStream fileOutputStream = new FileOutputStream("juegosDeMesa.json");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutputStream);

            objectOutput.writeObject(datoDeSeccion);

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo...");
            System.out.println("Creando el archivo...");
            CrearArchivo();
            System.out.println("Archivo Creado!");
            EscribirArchivo(datoDeSeccion);

        } catch (IOException e) {
            e.printStackTrace();

         */
    }

    @Override
    public void CargarItems() {
        //Pide al staff que cargue un item
        String control;

        do{
            Scanner scanner= new Scanner(System.in);
            Seccion <JuegoMesa> juegosDeMesa = new Seccion<>(50);

            System.out.println("Ingrese el nombre del juego de mesa: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el precio del juego de mesa: ");
            float numero = scanner.nextFloat();

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

            JuegoMesa juego = new JuegoMesa(numero,nombre,clasEdad,genderJM);

            // HACER BIEN GENERICIDAD CON ARCHIVOS EN ESTA PARTE

            System.out.println("Desea cargar otro juego? s/n");
            control = scanner.next();

        }while(control.equalsIgnoreCase("S"));
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

    @Override
    public void DarDeBaja() {

    }

    //region toString
    @Override
    public String toString() {
        return  "Juego de mesa (" + super.toString() +
                ", Genero= " + this.genero +
                ")";
    }
    //endregion
}
