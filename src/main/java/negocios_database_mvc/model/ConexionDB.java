package negocios_database_mvc.model;

import java.sql.*;

public class ConexionDB {
    private Connection conexion;
    private static String conexionUrl = "jdbc:sqlite:./negocios.db";
    public ConexionDB(){}

    public Connection obtenerConexion() {
        try {
            conexion = DriverManager.getConnection(conexionUrl);
            if(conexion != null) {
                DatabaseMetaData meta = conexion.getMetaData();
                System.out.println("=== Se conecto a la base de datos " + meta.getDriverName() + "  ===");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return conexion;
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
