package ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Javi
 */
public class gestionAgenda {

    public static void main(String[] args) throws IOException {
        new gestionAgenda();
    }

    static BufferedReader tc = new BufferedReader(new InputStreamReader(System.in));
    static File fichero = new File("Agenda.txt");
    static ArrayList<contacto> agenda = new ArrayList<contacto>();

    public gestionAgenda() throws IOException {
        //recuperar();
        start();
    }

    public void start() throws IOException {
        int opc;
        do {
            System.out.print("\n***GESTOR AGENDA***\n" + "1.Crear Contacto\n" + "2.Mostrar Contactos\n" + "3.Consultar Contactos\n"
                    + "4.Eliminar Contacto\n" + "5.Ordenar Agenda\n" + "6.Buscar Cumpleaños\n" + "7.Salir\n" + "Elige una opción: ");
            opc = Integer.parseInt(tc.readLine());
            validate(opc);
            menu(opc);
        } while (opc != 7);
    }

    public void validate(int opc) throws IOException {
        while (opc < 1 || opc > 7) {
            System.out.println("Opción no valida, introducir de nuevo: ");
            opc = Integer.parseInt(tc.readLine());
            validate(opc);
            menu(opc);
        }
    }

    public void menu(int opc) throws IOException {
        switch (opc) {
            case 1:
                list_contact();
                break;
            case 2:
                add_contact();
                break;
            case 3:
                search_contact();
                break;
            case 4:
                delete_contact();
                break;
            case 5:
                sort_agenda();
            case 6:
                search_birthday();
                break;
            case 7:
                overwrite();
                System.out.println("Bye Bye");
                System.exit(0);
        }
    }

    public void list_contact() {

        System.out.println("\n*****CONTACTOS*****");
        if (agenda.isEmpty()) {
            System.out.println("\nLa agenda está vacía");
        } else {
            for (int i = 0; i < agenda.size(); i++) {
                System.out.println("\nContacto nº: " + (i + 1));
                System.out.print(agenda.get(i));
            }
        }
        System.out.println("\n********************\n");
    }

    public void add_contact() {
        try {
            char aux;
            do {
                System.out.println("\n***Nuevo Contacto***");

                System.out.print("\nID: ");
                String id = tc.readLine();
                while (isNum(id) == false) {
                    System.out.println("Error, introduce números: ");
                    id = tc.readLine();
                }

                System.out.print("Nombre: ");
                String name = tc.readLine();
                isLetra(name);

                System.out.print("Apellido: ");
                String surname = tc.readLine();
                isLetra(surname);

                System.out.print("Dirección: ");
                String street = tc.readLine();

                System.out.print("Móvil: ");
                String phone = tc.readLine();
                while (isNum(phone) == false) {
                    System.out.println("Error, introduce números: ");
                    phone = tc.readLine();
                }

                System.out.print("Fecha de nacimiento en formato dd/MM/yyyy: ");
                String birthdate = tc.readLine();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = sdf.parse(birthdate);

                while (!sdf.format(fecha).equals(birthdate)) {
                    System.out.println("Formato invalido: ");
                    birthdate = tc.readLine();
                    fecha = sdf.parse(birthdate);
                }

                agenda.add(new contacto(id, name, surname, street, phone, fecha));

                System.out.println("\n****Contacto guardado****");

                System.out.print("¿Desea añadir más? (Y/N)");
                aux = tc.readLine().toUpperCase().charAt(0);

            } while (aux != 'N');
        } catch (IOException | ParseException e) {
        }
    }

    public void search_contact() {

    }

    public void delete_contact() {

        try {
            System.out.print("\nIntroduce el ID del contacto que quieres borrar: ");
            String id = tc.readLine();

            for (int i = 0; i < agenda.size(); i++) {
                if (agenda.get(i).getId().equalsIgnoreCase(id)) {
                    System.out.println(agenda.get(i));
                }
            }

        } catch (IOException e) {
        }

    }

    public void sort_agenda() {
        try {
            int op;
            System.out.println("\nCómo quieres ordenar tu agenda?"
                    + "\n1.ID" + "\n2.Name" + "\nElige una opción: ");
            
            op = Integer.parseInt(tc.readLine());
            while (op < 1 || op > 2) {
                System.out.println("Introduce 1 o 2: ");
                op = Integer.parseInt(tc.readLine());
            }
            switch (op) {
                case 1:
                    Collections.sort(agenda, new compare_id());
                    System.out.println("Ordenado por el ID");
                    break;
                case 2:
                    Collections.sort(agenda, new compare_name());
                    System.out.println("Ordenado por el nombre");
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("");
        }
    }

    public void search_birthday() {

    }

    /*
    public void recuperar() {
        try {
            if (fichero.exists()) {
                // RECUPERAMOS el fichero del formato byte.
                System.out.println("Accediendo a fichero...\n");
                ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream(fichero));
                agenda = (ArrayList<contacto>) recuperar.readObject();
                recuperar.close();

            } else {
                // Si NO EXISTE, CREAMOS el fichero
                System.out.println("Creamos fichero");
                FileWriter escritura = new FileWriter(fichero, true);
                BufferedWriter buffer = new BufferedWriter(escritura);
                buffer.close();
            }
        } catch (FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException e) { // qué hacer si no se encuentra el fichero
            System.out.println("No se encuentra una clase con ese nombre de definición");
        } catch (IOException e) { // qué hacer si hay un error en la lectura del fichero
            System.out.println("No se puede leer el fichero ");
        }
    }
     */
    public void overwrite() {
        try {
            System.out.println("\nGuardando los datos en el fichero...");
            ObjectOutputStream serializar = new ObjectOutputStream(new FileOutputStream(fichero, false));
            serializar.writeObject(agenda);
            serializar.close();
            System.out.println("\nTodos los datos se han guardado adecuadamente");
        } catch (FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) { // qué hacer si hay un error en la lectura del fichero
            System.out.println("No se puede leer el fichero ");
        }
    }

    public static boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String isLetra(String cadena) throws IOException {

        boolean val = false;

        do {

            while (cadena.equals("")) {
                System.out.print("Ingresa algún dato: ");
                cadena = tc.readLine();
            }

            for (int i = 0; i < cadena.length(); i++) {

                val = true;

                if (!Character.isLetter(cadena.charAt(i))) {
                    val = false;
                }
            }
            if (!val) {
                System.out.print("Error, ingresa solo carácteres alfabeticos: ");
                cadena = tc.readLine();
            }
        } while (!val);

        return cadena;
    }

    /*
    package Agenda;

import Entidad.Contacto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class MostrarDatos {
    
    BufferedReader tec = new BufferedReader(new InputStreamReader(System.in));

    public MostrarDatos() {
   
    }
    
    public Contacto añadirDatos() throws IOException{
        
         int id = 0;
            String nombre;
            String apellidos;
            String telefono;
            String fNacimiento;            
    
            System.out.println("Introduce el nombre:");
            nombre = tec.readLine();
            System.out.println("Introduce los apellidos:");
            apellidos = tec.readLine();
            System.out.println("Introduce el telefono:");
            telefono = tec.readLine();
            System.out.println("Introduce fecha de nacimiento en formato yyyy-MM-dd:");
            fNacimiento = tec.readLine();
            
            Contacto contacto = new Contacto(id, nombre, apellidos, telefono, fNacimiento);
        
        return contacto;
        
    }

    public void MostrarContactos(ArrayList c) {

        ArrayList<Contacto> con = c;

        int id;
        String nombre, apellidos, telefono, fNacimiento;

        System.out.println("------- CONTACTOS -------");

        for (int i = 0; i < con.size(); i++) {

            id = con.get(i).getId();
            nombre = con.get(i).getNombre();
            apellidos = con.get(i).getApellidos();
            telefono = con.get(i).getTelefono();
            fNacimiento = con.get(i).getfNacimiento();

            System.out.println("-------------------------");
            System.out.println("ID: " + id + "\nNombre: " + nombre + "\nApellidos: " + apellidos + "\nTelefono: " + telefono + "\nF.Nacimiento: " + fNacimiento);

        }
        System.out.println("\nHECHO!\n");
    }
    
     public int buscarContacto(ArrayList c) throws IOException, ClassNotFoundException {
        
        ArrayList<Contacto> contactos = c;
         
        int posicion = 0; //Variable en la que guardo la posición del array que tengo que borrar
        
        String nom = "", aux2=null;
        int opc = 0, aux = 0, cont = 0;
        boolean sw = false;

        System.out.println("Introduce el nombre del contacto que desea buscar.");
        nom = tec.readLine();
        System.out.println();

        for (int i = 0; i < contactos.size(); i++) {

            if (contactos.get(i).getNombre().equalsIgnoreCase(nom)) {
                
                System.out.println(contactos.get(i).toString());
                
                aux = i;
                posicion = contactos.get(i).getId();
                cont += 1;
            } 
                                   
        }    
        
        if(cont == 0) {
                System.out.println("Nombre NO encontrado...");
                buscarContacto(contactos);
            }

        if (cont > 1) {
            System.out.println("Hay " + cont + " contactos con este nombre...\n"
                    + "Elimina el contacto que quieras introduciendo su número de teléfono...");
            aux2 = tec.readLine();

            for (int i = 0; i < contactos.size(); i++) {

                if (contactos.get(i).getTelefono().equalsIgnoreCase(aux2)) {
                    
                    posicion = contactos.get(i).getId();
                    contactos.remove(i);                    
                    System.out.println("Contacto eliminado...\n");
                    sw = true;
                    break;
                }                
            }

            if (sw == false) {
                    System.out.println("ERROR. Teléfono introducido incorrecto...\n");  
                    buscarContacto(contactos);                   
                }
        }
        
        if (cont == 1) {
            System.out.println("Pulse 1 para borrar el contacto\nPulse 2 para volver al menú...");
            opc = Integer.parseInt(tec.readLine());
            while (opc < 1 || opc > 2) {
                System.out.println("Error. Selecciona una opción válida.");
                opc = Integer.parseInt(tec.readLine());
            }
            
            switch (opc) {
                case 1:                      
                    posicion = contactos.get(aux).getId();
                    contactos.remove(aux);
                    System.out.println("Contacto eliminado...\n");
                    break;

                case 2:
                    posicion=0;
                    break;
            }
        }

        return posicion;
    }
     
     
     public int editarContacto(ArrayList c) throws IOException{
         
         ArrayList<Contacto> contact = c;
         
         int idEditar, posicionEditar = 0, posicionDB=0;
         
         String nombre, apellidos, telefono, fnac;
         
         System.out.println("Indica la ID del contacto que quieras editar");
         
         idEditar = Integer.parseInt(tec.readLine());
         
         for (int i = 0; i < contact.size(); i++) {
             
             if (contact.get(i).getId() == idEditar) { 
                 
                posicionEditar = i; //editar en array
                posicionDB = contact.get(i).getId(); //editar en DB
             
            }
         }
         
         System.out.println("\nVas a editar el siguiente contacto: ");
         System.out.println(contact.get(posicionEditar).toString());
         
         int opcion;
            do{
                do{
            System.out.println("¿Qué quieres editar? Elige una opción");
            System.out.println("1 = Nombre");
            System.out.println("2 = Apellidos");
            System.out.println("3 = Telefono");
            System.out.println("4 = FechaNacimiento");
            System.out.println("5 = SALIR");
            
            opcion = Integer.parseInt(tec.readLine());
            
            if(opcion<1 || opcion>5){
                System.out.println("ERROR, introduce una opción válida");
            }   
                }while(opcion<1 || opcion>5);
            
            
               switch (opcion){
               
                   case 1:
                       System.out.println("Nuevo nombre: ");
                       nombre = tec.readLine();
                       contact.get(posicionEditar).setNombre(nombre);
                       break;
                       
                       
                   case 2:
                       System.out.println("Nuevos apellidos: ");
                       apellidos = tec.readLine();
                       contact.get(posicionEditar).setApellidos(apellidos);
                       break;
                       
                   case 3:
                       System.out.println("Nuevo teléfono: ");
                       telefono = tec.readLine();
                       contact.get(posicionEditar).setTelefono(telefono);
                       
                       break;
                       
                   case 4:
                       System.out.println("Nueva F.Nacimiento: ");
                       fnac = tec.readLine();
                       contact.get(posicionEditar).setfNacimiento(fnac);
 
                       break;

               }                
            
            }while(opcion!=5);

         return posicionDB;
     }
}
*/

}

class compare_id implements Comparator<contacto> {
    
    @Override
    public int compare(contacto p1, contacto p2){
        return p1.getId().compareToIgnoreCase(p2.getId());
    }
}

class compare_name implements Comparator<contacto> {

    @Override
    public int compare(contacto p1, contacto p2) {
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}