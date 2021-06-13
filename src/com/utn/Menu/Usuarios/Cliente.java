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

    private static  void CargarMapaArchivo(HashMap<Integer,Cliente> map){
        try{

         FileOutputStream fileOutputStream = new FileOutputStream("Clientes.json");
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
         objectOutputStream.writeObject(map);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void MostrarArchivo(){
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
                System.out.println("1.Iniciar sesion \n2.Registrarse \n0.Go Back");

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

            if(caso==0){control="N";}

        }while(control.equalsIgnoreCase("S"));
    }

    ///region Funciones de inicio/registro
    private static void IniciarSesion(){

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
                            SubMenu(name);


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

    private static void Registrarse(){

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

    ///Submenu
    private static void SubMenu(String name){
        String control ="s";
        do{
            Scanner scanner = new Scanner(System.in);
            int caso=0;

            try {
                System.out.println("Ingrese una de las siguientes opciones: ");
                System.out.println("1.Buscar Producto \n2.Ver Listado de Productos \n3.Comprar producto/s \n4.Ver saldo \n5.Depositar en cartera \n6.Ver/Modificar Perfil \n0.Go Back");

                caso = scanner.nextInt();
                name=SubOpcion(caso,name);

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

    ///region exceptions

    ///region switch
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

    ///TODO TENGO QUE METER FUNCIONES ACA
    private static String SubOpcion(int caso,String name) throws NumException{

        if((caso>=1 && caso<=6) || caso==0){

            switch (caso) {

                case 1 -> System.out.println("ACA BUSCAR PRODUCTO");
                case 2 -> System.out.println("ACA LISTADO DE PRODUCTOS");
                case 3 -> System.out.println("ACA COMPRAR PRODUCTOS");
                case 4 -> MostrarCartera(name);
                case 5 -> DepositarCartera();
                case 6 -> name=VerModificar(name);
                default -> System.out.println("...");

            }

            return name;

        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }
    }

    private static String perfil(int caso,String name) throws NumException{

        if(caso==1 || caso==2){
            if(caso==1){
                VerPerfil(name);
            }else{
                name=ModificarPerfil(name);
            }
            return name;
        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");
        }

    }

    private static String Modificar(int caso,String name) throws  NumException{

        if((caso>=1 && caso<=4) || caso==0){

            switch (caso) {

                case 1 -> name=CambiarNombre(name);
                case 2 -> CambiarPass(name);
                case 3 -> CambiarDni(name);
                case 4 -> name=CambiarTodo(name);
                default -> System.out.println("...");

            }
            return name;

        }else{
            throw new NumException("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        }

    }

    ///endregion

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
    ///endregion

    ///region Funciones Cartera

    private static void MostrarCartera(String name){
        boolean flag = false;

        HashMap<Integer,Cliente> map= Leer();
        Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

        while(mapIterator.hasNext() && !flag){
            Cliente client = mapIterator.next().getValue();

            if(client.getNombreUsuario().equalsIgnoreCase(name)){
                System.out.println("Cartera : " + client.getCartera());
                flag = true;

            }
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

    ///endregion

    ///region Perfil
    private static String VerModificar(String name){
        int caso;
        Scanner scanner =new Scanner(System.in);

        try{
            System.out.println("Elija una opcion: ");
            System.out.println("1.Ver perfil \n2.Modificar Perfil");

            caso = scanner.nextInt();

            name=perfil(caso,name);



        }catch (InputMismatchException | NumException e){
            System.out.println(e.getMessage());
        }
        return name;

    }

    private static void VerPerfil(String name){
        HashMap<Integer,Cliente> map= Leer();
        boolean flag =false;
        Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

        while(mapIterator.hasNext() && !flag){
            Cliente client = mapIterator.next().getValue();

            if(client.getNombreUsuario().equalsIgnoreCase(name)){
                System.out.println(client);
                System.out.println("-Cartera= " + client.getCartera() +  "\n-Password = " + client.getPassword());
                flag = true;
            }
        }
    }

    private static String ModificarPerfil(String name){
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        System.out.println("Ingrese su dni: ");
        int dni = scanner.nextInt();




        try {

            HashMap<Integer,Cliente> map= Leer();
            Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

            while(mapIterator.hasNext() && !flag){
                Cliente client = mapIterator.next().getValue();

                if(client.getNombreUsuario().equalsIgnoreCase(name)){

                    if(client.getDni()==dni){
                        System.out.println("Digite que es lo que quiere modificar : ");
                        System.out.println("1.Nombre \n2.Dni \n3.Contraseña \n4.Todo\n0.Go Back");

                        int caso = scanner.nextInt();
                        name=Modificar(caso,name);

                    }else{
                        System.out.println("El dni ingresado es incorrecto");
                    }

                    flag = true;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("El valor ingresado es incorrecto...\nVuelva a intentar\n");

        } catch (NumException e){
            System.out.println(e.getMessage());

        }
        return name;
    }

    private static String CambiarNombre(String name){
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nuevo nombre de usuario:");
        String usuario = scanner.next();

        HashMap<Integer,Cliente> map= Leer();
        Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

        while(mapIterator.hasNext() && !flag){
            Cliente client = mapIterator.next().getValue();

            if(client.getNombreUsuario().equalsIgnoreCase(name)){
                client.setNombreUsuario(usuario);
                flag = true;
            }
        }
        CargarMapaArchivo(map);

        return usuario;
    }

    private static void CambiarPass(String name){
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la nueva password de usuario:");
        String pass = scanner.next();

        HashMap<Integer,Cliente> map= Leer();
        Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

        while(mapIterator.hasNext() && !flag){
            Cliente client = mapIterator.next().getValue();

            if(client.getNombreUsuario().equalsIgnoreCase(name)){
                client.setPassword(pass);
                flag = true;
            }
        }
        CargarMapaArchivo(map);

    }

    private static void CambiarDni(String name){
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nuevo Dni de usuario : " );
        int dni = scanner.nextInt();

        HashMap<Integer,Cliente> map= Leer();
        Iterator<Map.Entry<Integer,Cliente>> mapIterator = map.entrySet().iterator();

        while(mapIterator.hasNext() && !flag){
            Cliente client = mapIterator.next().getValue();

            if(client.getNombreUsuario().equalsIgnoreCase(name)){
                client.setDni(dni);
                flag = true;
            }
        }
        CargarMapaArchivo(map);

    }

    private static String CambiarTodo(String name){
        String nuevo=CambiarNombre(name);
        CambiarDni(nuevo);
        CambiarPass(nuevo);

        return nuevo;
    }

    ///endregion

    @Override
    public String toString() {
        return "Cliente: \n-nombre= " + this.nombreUsuario + "\n-dni= " + this.dni + "\n-mail= " + this.mail;
    }
}
