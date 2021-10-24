package negocios_database_mvc.model;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {
    private ConexionDB conexion;

    public ClienteDAO() {
        conexion = new ConexionDB();
        clienteTable();
    }

    private void clienteTable() {
        try(Connection accesoDB = conexion.obtenerConexion();
            Statement stmt = accesoDB.createStatement()) {
            String sintaxisSql = "CREATE TABLE IF NOT EXISTS clientes(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre VARCHAR(255) NOT NULL," +
                    "direccion VARCHAR(255) NOT NULL," +
                    "telefono VARCHAR(255) NOT NULL," +
                    "correo VARCHAR(255) NOT NULL"+
                    ")";
            stmt.execute(sintaxisSql);
            conexion.cerrarConexion();
        }catch (SQLException | RuntimeException sqlex) {
            System.out.println("Cliente table: " + sqlex);
        }
    }

    public Cliente obtenerCLientePorID(int id) {
        String sintaxisSql = "SELECT * FROM clientes where id=" + id;
        Cliente cliente = null;
        try(Connection accesoDB = conexion.obtenerConexion();
            PreparedStatement pstmt = accesoDB.prepareStatement(sintaxisSql);
            ResultSet rs = pstmt.executeQuery();
            ){
            while (rs.next()) {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
            conexion.cerrarConexion();
        }catch (SQLException | RuntimeException sqlex) {
            System.out.println("Insertar cliente: " + sqlex);
        }
        return cliente;
    }

    public void insertarCliente(Cliente cliente) {
        String sintaxisSql = "INSERT INTO clientes (nombre, direccion, telefono, correo) " +
                "VALUES (?,?,?,?)";
        try(Connection accesoDB = conexion.obtenerConexion();
            PreparedStatement pstmt = accesoDB.prepareStatement(sintaxisSql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.executeUpdate();
            conexion.cerrarConexion();
        }catch (SQLException | RuntimeException sqlex) {
            System.out.println("Insertar cliente: " + sqlex);
        }
    }

    public void actualizarCliente(Cliente cliente) {
        String sintaxisSql = "UPDATE clientes SET nombre=?, direccion=?, telefono=?, correo=? " +
                "WHERE id=?";
        try(Connection accesoDB = conexion.obtenerConexion();
            PreparedStatement pstmt = accesoDB.prepareStatement(sintaxisSql)) {
            pstmt.setString(1,cliente.getNombre());
            pstmt.setString(2,cliente.getDireccion());
            pstmt.setString(3,cliente.getTelefono());
            pstmt.setString(4,cliente.getCorreo());
            pstmt.setInt(5,cliente.getId());
            pstmt.executeUpdate();
            conexion.cerrarConexion();
        }catch (SQLException | RuntimeException sqlex) {
            System.out.println("Actualizar cliente: " + sqlex);
        }
    }

    public void eliminarCliente(int id) {
        String sintaxisSql = "DELETE FROM clientes WHERE id=?";
        try(Connection accesoDB = conexion.obtenerConexion();
            PreparedStatement pstmt = accesoDB.prepareStatement(sintaxisSql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conexion.cerrarConexion();
        }catch (SQLException | RuntimeException sqlex) {
            System.out.println("Eliminar cliente: " + sqlex);
        }
    }

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente;

        try(Connection accesoDB = conexion.obtenerConexion();
            PreparedStatement pstmt = accesoDB.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = pstmt.executeQuery();
            ) {
            while (rs.next()) {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                clientes.add(cliente);
            }
        }catch (SQLException | RuntimeException sqlex) {
            System.out.println("Obtener clientes: " + sqlex);
        }

        if(clientes.size() == 0) {
            System.out.println("No existen datos.");
        }
            return clientes;
    }
}
