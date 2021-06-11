package com.utn.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inicio implements Menu {

    ///Funcion que se ejecuta en el main
    public static void Inic(){
        Inicio in = new Inicio();
        in.Menu();
    }

    ///Constructor privado
    private Inicio() { }

    ///Funcion de menu implementada y sobrescribida de la interfaz
    @Override
    public void Menu() {


        String control;

        System.out.println("\nBienvenido\n");

        do {
            int caso = 0;
            control = ".";
            Scanner scanner = new Scanner(System.in);

                try {
                    System.out.println("Elija una de las opciones: ");
                    System.out.println("1.Cliente 2.Staff 0.Exit");

                    caso = scanner.nextInt();
                    Numerador(caso);

                } catch (InputMismatchException e) {
                    System.out.println("El valor ingresado es incorrecto...\nVuelva a intentar\n");
                    control = "s";
                } catch (NumException e){
                    System.out.println(e.getMessage());
                    control = "s";
                }

            if(caso!=0) {
                do{

                    try{
                        control = controlar();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                }while(!control.equalsIgnoreCase("S") && !control.equalsIgnoreCase("N"));
            }

            if(control.equalsIgnoreCase("N")){ System.out.println("Gracias por su atencion!!!"); }

        }while(control.equalsIgnoreCase("S"));

    }

    ///El switch principal para acceder a los diferentes submenus y si el valor es incorrecto devuelve una excepcion
    public static void Numerador(int caso) throws NumException{

        if(caso==1 || caso==2 || caso==0){
            switch (caso) {

                case 1 -> System.out.println("ACA EL MENU DE CLIENTE");
                case 2 -> System.out.println("ACA EL MENU DE STAFF");
                default -> System.out.println("Gracias por su atencion!!!");

            }
        }else{
            throw new NumException("El valor ingresado es incorrecto...");
        }
    }

    ///Funcion que controla si escribe si o no y devuelve una excepcion en el caso contrario
    public static String controlar() throws Exception{

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
