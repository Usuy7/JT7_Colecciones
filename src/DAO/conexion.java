package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    
    protected Connection conexion;
    
    // JDBC driver nombre y base de datos URL
    
    private final String JDBC_DRIVER = "com.mysql.jdbc.driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/agenda";
    
    // Credenciales de la base de datos
    
    private final String USER = "root";
    private final String PASS = "";
    
    // Abrir conexion a la base de datos
    
    public void conectar_BDD(){
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar con la BDD");
        }
    }
    
    // Cerrar conexion a la base de datos
    
    public void desconectar_BDD(){
        if (conexion != null){
            if (!conexion.isClosed()){
                conexion.close();
            }
        }
    }
}
