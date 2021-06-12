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

    @Serial
    private static final long serialVersionUID = 13221L;


    ///region Constructores
    public Cliente() { }

    public Cliente(int dni, String nombreUsuario, String password, String mail) {
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.mail = mail;
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

    ///region Funciones archivo

    ///Carga un mapa en el archivo
    private static  void CargarMapaArchivo(HashMap<Integer,Cliente> map){
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

    private static HashMap Leer(){

        HashMap<Integer,Cliente> map = new HashMap<>();
        try{
            FileInputStream fileInputStream =new FileInputStream("Clientes.json");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            map = (HashMap<Integer,Cliente>) objectInputStream.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return map;
    }
    ///endregion

    ///Menu Principal
    @Override
    public void Menu() {
        String control ="s";
        do{
            Scanner scanner = new Scanner(System.in);
            int caso=0;

            try {
                System.out.println("Ingrese una de las siguientes opciones: ");
                System.out.println("1.Iniciar sesion 2.Registrarse 0.Go Back");

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

        }while(control.equalsIgnoreCase("S"));
    }

    ///region Funciones de inicio/registro
    // /CREAR LOOP DE CONTRASEÑA INCORRECTA SI EXISTE EL USUARIO PERO NO ES LA CONTRASEÑA
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
                            SubMenu();


                      }else{
                          System.out.println("La contraseña es incorrecta...");
                      }
                      flag=true;
                  }
              }

              if(!flag){ System.out.println("El usuario no se encuentra registrado"); }

            } catch (FileNotFoundException e){
                System.out.println("El archivo no existe...");

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();

            }

    }

    public static void Registrarse(){

        Scanner scanner = new Scanner(System.in);
        boolean flag=false;

        System.out.println("Ingrese nombre de usuario: ");
        String name = scanner.next();
        System.out.println("Ingrese la contraseña: ");
        String password = scanner.next();
        System.out.println("Ingrese el DNI: ");
        int dni = scanner.nextInt();
        System.out.println("Ingrese la direccion de correo electronico: ");
        String mail = scanner.next();

        Cliente ingresado = new Cliente(dni,name,password,mail);

        try{
            FileInputStream fileInputStream =new FileInputStream("Clientes.json");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashMap<Integer,Cliente> map = (HashMap<Integer,Cliente>) objectInputStream.readObject();

            Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

            while(mapIterator.hasNext() && !flag){

                Cliente client = mapIterator.next().getValue();

                if(name.equalsIgnoreCase(client.getNombreUsuario())){
                    System.out.println("-Ese nombre de usuario ya se encuentra registrado-");
                    flag=true;

                }
                if( dni == client.getDni() && !flag){
                    System.out.println("-Ese Dni ya se encuentra registrado-");
                    flag=true;

                }

                if(mail.equalsIgnoreCase(client.getMail()) && !flag ){
                    System.out.println("-El mail ya se encuentra registrado-");
                    flag=true;

                }

                if(flag){
                    System.out.println("Intente iniciando sesion");
                }
            }

            if(!flag){
                map.put(dni,ingresado);
                CargarMapaArchivo(map);
            }

        } catch (FileNotFoundException e){
            System.out.println("Creando archivo.... ");
            HashMap<Integer,Cliente> map = new HashMap<>();

            map.put(dni,ingresado);
            CargarMapaArchivo(map);

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();

        }

    }
    ///endregion

    private static void SubMenu(){
        String control ="s";
        do{
            Scanner scanner = new Scanner(System.in);
            int caso=0;

            try {
                System.out.println("Ingrese una de las siguientes opciones: ");
                System.out.println("1.Buscar Producto 2.Ver Listado de Productos 3.Comprar producto/s 4.Ver saldo 5.Depositar en cartera 0.Go Back");

                caso = scanner.nextInt();
                SubOpcion(caso);

            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado es incorrecto...\nVuelva a intentar\n");

            } catch (NumException e){
                System.out.println(e.getMessage());

            }

            if ((caso>=1 && caso<=6) || caso==0){
                do{

                    try{
                        control = controlar();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                }while(!control.equalsIgnoreCase("S") && !control.equalsIgnoreCase("N"));
            }

        }while(control.equalsIgnoreCase("S"));
    }

    ///TODO TENGO QUE METER FUNCIONES ACA
    private static void SubOpcion(int caso) throws NumException{

        if(caso==1 || caso==2 || caso==3 || caso==4 || caso==5 ||caso==6 || caso==0){

            switch (caso) {

                case 1 -> System.out.println("ACA BUSCAR PRODUCTO");
                case 2 -> System.out.println("ACA LISTADO DE PRODUCTOS");
                case 3 -> System.out.println("ACA COMPRAR PRODUCTOS");
                case 4 -> MostrarCartera();
                case 5 -> DepositarCartera();
                case 6 -> VerModificar();
                default -> System.out.println("...");

            }

        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }
    }

    private static void Opcion(int caso) throws NumException{

        if(caso==1 || caso==2 || caso==0){

            switch (caso) {

                case 1 -> Cliente.IniciarSesion();
                case 2 -> Cliente.Registrarse();
                default -> System.out.println("...");

            }

        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

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

    private static void VerModificar(){
        int caso;
        Scanner scanner =new Scanner(System.in);

        try{
            System.out.println("Elija una opcion: ");
            System.out.println("1.Ver perfil \n 2.Modificar Perfil");

            caso = scanner.nextInt();

           perfil(caso);

        }catch (InputMismatchException | NumException e){
            System.out.println(e.getMessage());
        }

    }

    ///todo VER O MODIFICAR PERFIL
    ///TODO TENGO QUE METER FUNCIONES ACA
    private static void perfil(int caso) throws NumException{

        if(caso==1 || caso==2){
            if(caso==1){
                System.out.println("ACA VER PERFIL");
            }else{
                System.out.println("ACA MODIFICAR PERFIL");
            }
        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");
        }

    }

    private static void DepositarCartera(){
        Scanner scanner = new Scanner(System.in);
        boolean flag =false;

        System.out.println("Ingrese el dni para continuar:");
        int dni = scanner.nextInt();

            HashMap<Integer,Cliente> map= Leer();
            Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

            while(mapIterator.hasNext() && !flag){
                Cliente client = mapIterator.next().getValue();

                if(client.getDni()==dni){
                    ///dasdasdasdas
                    System.out.println("Ingrese el monto que desea depositar:");
                    double monto = scanner.nextDouble();
                    double cartera = client.getCartera();
                    cartera += monto;
                    client.setCartera(cartera);
                    flag = true;

                }
            }

            if(!flag){
                System.out.println("El dni no se encuentra registrado...");

            }else{
                CargarMapaArchivo(map);

            }

    }

    private static void MostrarCartera(){

        Scanner scanner = new Scanner(System.in);
        boolean flag =false;

        System.out.println("Ingrese el dni para continuar:");
        int dni = scanner.nextInt();

        HashMap<Integer,Cliente> map= Leer();
        Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

        while(mapIterator.hasNext() && !flag){
            Cliente client = mapIterator.next().getValue();

            if(client.getDni()==dni){
                System.out.println("Cartera : " + client.getCartera());
                flag = true;

            }
        }

        if(!flag){
            System.out.println("El dni no se encuentra registrado...");

        }

    }

    ///todo implementar pasaje de nombre por parametro para verificar datos ej depositar cartera verifica que sepa el dni del usuario antes pasado por parametro

    ///todo  posible historial de compras

    @Override
    public String toString() {
        return "Cliente: \n-nombre= " + this.nombreUsuario + "\n-dni= " + this.dni + "\n-mail= " + this.mail;
    }
}
