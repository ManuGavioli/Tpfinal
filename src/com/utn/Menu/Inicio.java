package com.utn.Menu;

import java.util.Scanner;

public class Inicio implements Menu {

    public Inicio() { }

    @Override
    public void Menu() {

        Scanner scanner = new Scanner(System.in);
        String control;

        System.out.println("\nBienvenido\n");

        do {
            control ="x";
            System.out.println("Elija una de las opciones: ");
            System.out.println("1.Cliente 2.Staff 0.Exit");

            int caso = scanner.nextInt();


            switch (caso) {
                case 1 -> System.out.println("ACA EL MENU DE CLIENTE");
                case 2 -> System.out.println("ACA EL MENU DE STAFF");
                default -> System.out.println("Gracias por su atencion!!!");
            }

            if(caso!=0) {
                do{
                System.out.println("Desea relizar otra operacion??");
                System.out.println("S/N");
                control = scanner.next();

                    if(!control.equalsIgnoreCase("S") && !control.equalsIgnoreCase("N")){ System.out.println("ERROR CARACTER INCORRECTO"); }

                }while(!control.equalsIgnoreCase("S") && !control.equalsIgnoreCase("N"));

            }

            if(control.equalsIgnoreCase("N")){
                System.out.println("Gracias por su atencion!!!");
            }

        }while(control.equalsIgnoreCase("S"));




    }
}
