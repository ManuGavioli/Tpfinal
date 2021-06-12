package com.utn.Menu.Usuarios;

import com.utn.Menu.Menu;
import com.utn.Menu.NumException;



import java.io.*;
import java.util.*;

public class Cliente implements Menu , Serializable {

    private int dni;
    private String nombreUsuario;
    private String password;
    private String mail;
    private double cartera = 0;

    private static final long serialVersionUID = 13221L;


    ///region Constructores
    public Cliente() { }

    public Cliente(int dni, String nombreUsuario, String password, String mail, double cartera) {
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.mail = mail;
        this.cartera = cartera;
    }
    ///endregion

    ///region Getters y Setters
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public double getCartera() { return cartera; }
    public void setCartera(double cartera) { this.cartera = cartera; }
    ///endregion


    public static void MenuInic(){
        Cliente cl = new Cliente();
        cl.Menu();
    }

    ///Carga un mapa en el archivo
    private static  void CargarArchivo(HashMap<Integer,Cliente> map){
        try{

         FileOutputStream fileOutputStream = new FileOutputStream("Clientes.json");
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
         objectOutputStream.writeObject(map);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    ///Abre el archivo y muestra tod0 el contenido
    private static void MostrarArchivo(){
        try{
            FileInputStream fileInputStream =new FileInputStream("Clientes.json");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashMap<Integer,Cliente> map = (HashMap<Integer, Cliente>) objectInputStream.readObject();

            map.forEach((k,v)-> System.out.println(v.toString()));

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    /// POR AHORA SOLO LEE
    private static void LeerArchivo(){
        try{
            FileInputStream fileInputStream =new FileInputStream("Clientes.json");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashMap<Integer,Cliente> map = (HashMap<Integer, Cliente>) objectInputStream.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void Menu() {
        String control ="s";
        do{
            Scanner scanner = new Scanner(System.in);
            int caso=0;



            try {
                System.out.println("Ingrese una de las siguientes opciones: ");
                System.out.println("1.Iniciar sesion 2.Registrarse 0.Exit");

                 caso = scanner.nextInt();
                 Opcion(caso);

            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado es incorrecto...\nVuelva a intentar\n");
            } catch (NumException e){
                System.out.println(e.getMessage());
            }


            if (caso==1 || caso==2){
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


    public static void IniciarSesion(){

            Scanner scanner = new Scanner(System.in);
            boolean flag=false;

            System.out.println("Ingrese nombre de usuario: ");
            String name = scanner.next();
            System.out.println("Ingrese la contraseña: ");
            String password = scanner.next();

            try{
              FileInputStream fileInputStream =new FileInputStream("Clientes.json");
              ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

              HashMap<Integer,Cliente> map = (HashMap<Integer, Cliente>) objectInputStream.readObject();

              Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

              while(mapIterator.hasNext() && !flag){

                  Cliente client = mapIterator.next().getValue();

                  if(name.equalsIgnoreCase(client.getNombreUsuario())){

                      if(password.equalsIgnoreCase(client.getPassword())){

                            System.out.println("Ingreso correctamente: ");
                            System.out.println("ACA VA EL SUBMENU");

                            flag=true;
                      }
                  }
              }

              if(!flag){ System.out.println("El usuario o la contraseña son incorrectos..."); }

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();

            }

    }

    public static void Registrarse(){

    }

    public static void Opcion(int caso) throws NumException{

        if(caso==1 || caso==2 || caso==0){

            switch (caso) {

                case 1 -> Cliente.IniciarSesion();
                case 2 -> System.out.println("ACA REGISTRARSE");
                default -> System.out.println("Gracias por su atencion!!!");

            }

        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }
    }

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


    @Override
    public String toString() {
        return "Cliente: \n-nombre= " + this.nombreUsuario + "\n-dni= " + this.dni + "\n-mail= " + this.mail;
    }
}
