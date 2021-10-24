package negocios_database_mvc.controller;

import negocios_database_mvc.model.Cliente;
import negocios_database_mvc.model.ClienteDAO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Controller {
    private ClienteDAO db;

    public Controller() {
        db = new ClienteDAO();
    }

    public void mostrarClientes(DefaultTableModel datosTabla) {
        ArrayList<Cliente> clientes = db.obtenerClientes();
        if(clientes.size() > 0) {
            for(Cliente cliente: clientes) {
                datosTabla.addRow(new Object[]{
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getDireccion(),
                        cliente.getTelefono(),
                        cliente.getCorreo()
                });
            }
        }
    }
    private void limpiarTabla(DefaultTableModel datosTabla) {
        datosTabla.setRowCount(0);
    }
    public void agregarClientes(Cliente cliente, DefaultTableModel datosTabla) {
        db.insertarCliente(cliente);
        limpiarTabla(datosTabla);
        mostrarClientes(datosTabla);
    }
    public Cliente obtenerCliente(int id) {
        return db.obtenerCLientePorID(id);
    }

    public void actualizarCliente(Cliente cliente, DefaultTableModel datosTabla) {
        db.actualizarCliente(cliente);
        limpiarTabla(datosTabla);
        mostrarClientes(datosTabla);
    }

    public void eliminarCliente(int id, DefaultTableModel datosTabla) {
        db.eliminarCliente(id);
        limpiarTabla(datosTabla);
        mostrarClientes(datosTabla);
    }
}
