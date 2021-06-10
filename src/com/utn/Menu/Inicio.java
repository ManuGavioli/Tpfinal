package com.utn.Menu;

import java.util.HashMap;
import java.util.Scanner;

public class Inicio implements Menu {



    public static void Inicio(){

    }

    @Override
    public void Menu() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido\n");
        System.out.println("Elija una de las opciones: ");
        System.out.println("1.Cliente 2.Staff 0.Exit");

        int caso = scanner.nextInt();
        String control = "N";

        do {


            switch (caso) {
                case 1:
                    System.out.println("ACA EL MENU DE CLIENTE");
                    break;
                case 2:
                    System.out.println("ACA EL MENU DE STAFF");
                    break;
                default:
                    break;
            }

            if(caso!=0){

                    System.out.println("Desea relizar otra operacion??");
                    System.out.println("S/N");
                    control = scanner.next();

                    ///Cambiar comparativa por comparador de strings
                    while(control != "S" && control != "N"){
                        System.out.println("Esa no es una opcion optima");
                    }

            }

        }while(control=="S");




    }
}
