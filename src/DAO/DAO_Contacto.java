package DAO;

import java.sql.SQLException;


public class DAO_Contacto extends conexion{
    
    public void show(){
        try {
            this.conectar_BDD();
            this.conexion.prepareStatement("SELECT * FROM contactos");
        } catch (SQLException e) {
        } finally{
            
        }
    }
    
    public void add(){
        try {
            this.conectar_BDD();
            this.conexion.prepareStatement("INSERT INTO contactos (name, surname, street, phone, birthdate) VALUES (?,?,?,?,?) ");
        } catch (SQLException e) {
        } finally{
            
        }
    }
    
    public void update(){
        
    }
    
    public void search(){
        
    }
    
    public void delete(){
        
    }
}
