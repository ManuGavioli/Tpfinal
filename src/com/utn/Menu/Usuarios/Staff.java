package com.utn.Menu.Usuarios;

import com.utn.Menu.Menu;
import com.utn.Menu.NumException;
import com.utn.items.Disco;
import com.utn.items.JuegoMesa;
import com.utn.items.Libro;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.utn.Menu.Usuarios.Cliente.MostrarArchivo;

public class Staff implements Menu {

private static final int contra = 678_862_341;


    public Staff() {

    }

    public static void InicicStaff(){
        Staff st = new Staff();
        st.Menu();
    }

    @Override
    public void Menu() {
        String control ="s";
        Scanner scn = new Scanner(System.in);

        System.out.println("Ingrese la contraseña de staff: ");
        int con = scn.nextInt();

        if(con == contra){
            System.out.println("Bienvenido al staff: \n\n");

            do{
                Scanner scanner = new Scanner(System.in);
                int caso=0;

                try {
                    System.out.println("Ingrese una de las siguientes opciones: ");
                    System.out.println("1.Listado de usuarios \n2.Cargar producto/s \n3.Dar de baja producto/s \n4.Ver stock producto \n0.Go Back");

                    caso = scanner.nextInt();
                    Opcion(caso);

                } catch (InputMismatchException e) {
                    System.out.println("El valor ingresado es incorrecto...\nVuelva a intentar\n");

                } catch (NumException e){
                    System.out.println(e.getMessage());

                }

                if (caso>=1 && caso<=6){
                    do{

                        try{
                            control = controlar();
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }

                    }while(!control.equalsIgnoreCase("S") && !control.equalsIgnoreCase("N"));
                }else{
                    control = "n";
                }

            }while(control.equalsIgnoreCase("S"));
        }else{
            System.out.println("La contraseña es incorrecta...");

        }


    }

    //region switch
    private static void Opcion(int caso) throws NumException{

        if((caso>=1 && caso<=4) || caso==0){

            switch (caso) {
                case 1 -> MostrarArchivo();
                case 2 -> CargarItem();
                case 3 -> DarBaja();
                case 4 -> Verif();
                default -> System.out.println("...");

            }


        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }
    }

    private static void Cargar(int caso) throws  NumException{
        Libro libro = new Libro();
        JuegoMesa juego = new JuegoMesa();
        Disco ds = new Disco();


        if((caso>=1 && caso<=3) || caso==0){

            switch (caso) {

                case 1 -> libro.CargarItems();
                case 2 -> juego.CargarItems();
                case 3 -> ds.CargarItems();
                default -> System.out.println("...");

            }
        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }

    }

    private static void Baja(int caso) throws  NumException{
        Libro libro = new Libro();
        JuegoMesa juego = new JuegoMesa();
        Disco ds = new Disco();


        if((caso>=1 && caso<=3) || caso==0){

            switch (caso) {
                ///FALTA CORROBORAR SI SEGUN EL PRECIO Y LA CARTERA SE PUEDE COMPRAR EL PRODUCTO...
                case 1 -> libro.DarDeBaja();
                case 2 -> juego.DarDeBaja();
                case 3 -> ds.DarDeBaja();
                default -> System.out.println("...");

            }
        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }

    }

    private static void Stock(int caso) throws  NumException{
        Libro libro = new Libro();
        JuegoMesa juego = new JuegoMesa();
        Disco ds = new Disco();

        if((caso>=1 && caso<=3) || caso==0){

            switch (caso) {
                case 1 -> libro.VerificarStock();
                case 2 -> juego.VerificarStock();
                case 3 -> ds.VerificarStock();
                default -> System.out.println("...");

            }
        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }

    }
    //endregion

    private static void CargarItem(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tipo de producto que desea cargar: ");
        System.out.println("1.Libro\n2.Juego de Mesa\n3.Disco");
        int caso = scanner.nextInt();

        try {
            Cargar(caso);

        } catch (NumException | InputMismatchException e) {
            e.printStackTrace();

        }

    }

    private static void DarBaja(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tipo de producto que desea dar de baja: ");
        System.out.println("1.Libro\n2.Juego de Mesa\n3.Disco");
        int caso = scanner.nextInt();

        try {
            Baja(caso);

        } catch (NumException | InputMismatchException e) {
            e.printStackTrace();

        }

    }

    private static void Verif(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tipo de producto que desea verificar: ");
        System.out.println("1.Libro\n2.Juego de Mesa\n3.Disco");
        int caso = scanner.nextInt();

        try {
            Stock(caso);

        } catch (NumException | InputMismatchException e) {
            e.printStackTrace();

        }

    }


    private static String controlar() throws Exception{

        String control;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Desea relizar otra operacion??");
        System.out.println("S/N");
        control = scanner.next();

        if(control.equalsIgnoreCase("S") || control.equalsIgnoreCase("N")){
            System.out.println("--------------------------");
            return control;
        }

        throw new Exception("El valor ingresado es incorrecto...\nVuelva a intentar\n");
    }

}
