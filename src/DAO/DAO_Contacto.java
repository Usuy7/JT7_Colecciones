package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DAO_Contacto extends conexion{
    
    public void show(){
        try {
            this.conectar_BDD();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM contactos");
            
        } catch (SQLException e) {
        } finally{
            this.desconectar_BDD();
        }
    }
    
    public void add(){
        try {
            this.conectar_BDD();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO contactos (name, surname, street, phone, birthdate) VALUES (?,?,?,?,?)");
        } catch (SQLException e) {
        } finally{
            this.desconectar_BDD();
        }
    }
    
    public void update(){
        
    }
    
    public void search(){
        
    }
    
    public void delete(){
        
    }
}
