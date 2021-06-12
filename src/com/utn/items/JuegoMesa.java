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
            BufferedWriter fSalida = new BufferedWriter(new FileWriter(new File("juegosDeMesa.json")));
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
            guardar = new BufferedWriter(new FileWriter(new File("juegosDeMesa.json")));

            gson.toJson(datoDeSeccion, Seccion.class, guardar);
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
        String control = null;

        do{
            Scanner scanner= new Scanner(System.in);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Seccion <JuegoMesa> juegosDeMesa = new Seccion<>(50);

            System.out.println("Ingrese el nombre del juego de mesa: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el precio del juego de mesa: ");
            float numero = scanner.nextFloat();

            //region ClasificacionPorEdad
            System.out.println("""
                Ingrese la clasificacion de edad del juego de mesa:\s
                1 - Niños.
                2 - Adolecentes.
                3 - Adultos.
                """);
            int Edad = scanner.nextInt();
            ClasificacionEdad clasEdad = null;
            switch (Edad) {
                case 1 -> clasEdad = ClasificacionEdad.NINIOS;
                case 2 -> clasEdad = ClasificacionEdad.ADOLECENTES;
                case 3 -> clasEdad = ClasificacionEdad.ADULTOS;
                default -> System.out.println("Opcion no valida. Reintente");
            }
            //endregion

            //region ClasificacionPorGenero
            System.out.println("""
                Ingrese el genero del juego de mesa:\s
                1 - Puzzle.
                2 - Cartas.
                3 - Dados.
                4 - Fiesta.
                5 - Rol.
                """);
            int gen = scanner.nextInt();
            GenerosJM genderJM = null;
            switch (gen) {
                case 1 -> genderJM = GenerosJM.PUZZLE;
                case 2 -> genderJM = GenerosJM.CARTAS;
                case 3 -> genderJM = GenerosJM.DADOS;
                case 4 -> genderJM = GenerosJM.FIESTA;
                case 5 -> genderJM = GenerosJM.ROL;
                default -> System.out.println("Opcion no valida. Reintente");
            }
            //endregion

            JuegoMesa juego = new JuegoMesa(numero,nombre,clasEdad,genderJM);

            juegosDeMesa=LeerArchivo();

            if(juegosDeMesa!=null){
                juegosDeMesa.agregarElemento(juego);
            }
            EscribirArchivo(juegosDeMesa);

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
