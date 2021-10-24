package negocios_database_mvc.views;

import negocios_database_mvc.controller.Controller;
import negocios_database_mvc.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClientesView extends JFrame{
    private Controller con;
    private Cliente cliente;
    private DefaultTableModel datos;
    private int idClienteSeleccionado = 0;

    private Dimension dimensiones;
    private JLabel titulo;
    private JPanel panelPrincipal;
    private JTextField inputNombre;
    private JTextField inputDireccion;
    private JTextField inputTelefono;
    private JTextField inputCorreo;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JButton btnActualizar;
    private JTable clienteTabla;
    private JButton btnEliminar;

    public ClientesView() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e);
        }

        initialComponents();
        listenerButtons();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientesView().setVisible(true);
            }
        });
    }

    private void initialComponents() {
        con = new Controller();

        dimensiones = new Dimension(800,600);
        this.setTitle("Negocios");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelPrincipal);
        this.setMaximumSize(dimensiones);
        this.setMinimumSize(dimensiones);
        this.setSize(dimensiones);

        btnEditar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);

        titulo.setFont(new Font("Verdana", Font.PLAIN, 30));
        String[] titulo = new String[] {"ID", "Nombre", "Dirección", "Telefono", "Correo"};
        datos = new DefaultTableModel(null, titulo) {
            @Override
            public  boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        clienteTabla.setModel(datos);
        con.mostrarClientes(datos);

        this.setLocationRelativeTo(null);
        this.pack();
    }
    private void listenerButtons() {
        btnAgregar.addActionListener(e -> agregarDatos());
        clienteTabla.getSelectionModel().addListSelectionListener(e -> datoSeleccionado());
        btnEditar.addActionListener(e -> obtenerDatoSeleccionado());
        btnActualizar.addActionListener(e -> actualizarDatoSeleccionado());
        btnEliminar.addActionListener(e -> eliminarDatoSeleccionado());
        btnCancelar.addActionListener(e -> cancelarDatoSeleccionado());
    }

    private void agregarDatos() {
        Cliente cliente = obtenerDatos();
        if(cliente != null) {
            con.agregarClientes(cliente, datos);
        }
    }
    private void datoSeleccionado() {
        btnAgregar.setEnabled(false);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    private void obtenerDatoSeleccionado() {
        int indexSeleccionado = clienteTabla.getSelectedRow();
        int id = (int) datos.getDataVector().get(indexSeleccionado).get(0);
        Cliente datosDelClienteSeleccionado = con.obtenerCliente(id);

        idClienteSeleccionado = datosDelClienteSeleccionado.getId();
        inputNombre.setText(datosDelClienteSeleccionado.getNombre());
        inputDireccion.setText(datosDelClienteSeleccionado.getDireccion());
        inputTelefono.setText(datosDelClienteSeleccionado.getTelefono());
        inputCorreo.setText(datosDelClienteSeleccionado.getCorreo());

        btnActualizar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    private void actualizarDatoSeleccionado() {
        Cliente cliente = obtenerDatos();
        if(cliente != null) {
            con.actualizarCliente(cliente, datos);
            btnAgregar.setEnabled(true);
            btnActualizar.setEnabled(false);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnCancelar.setEnabled(false);
        }
    }
    private void eliminarDatoSeleccionado() {
        int confirmado = JOptionPane.showConfirmDialog(this,
                "¿Estas seguro que quieres eliminarlo?");
        if(confirmado == JOptionPane.OK_OPTION) {
            int indexSeleccionado = clienteTabla.getSelectedRow();
            int id = (int) datos.getDataVector().get(indexSeleccionado).get(0);
            con.eliminarCliente(id, datos);

            btnAgregar.setEnabled(true);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnCancelar.setEnabled(false);
        }
    }
    private void cancelarDatoSeleccionado() {
        clienteTabla.clearSelection();
        limpiarDatos();
        idClienteSeleccionado = 0;
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    private Cliente obtenerDatos() {
        String nombre = inputNombre.getText();
        String direccion = inputDireccion.getText();
        String telefono = inputTelefono.getText();
        String correo = inputCorreo.getText();

        if(nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(idClienteSeleccionado != 0) {
            cliente = new Cliente(idClienteSeleccionado, nombre, direccion, telefono, correo);
        }else {
            cliente = new Cliente(nombre, direccion, telefono, correo);
        }
        limpiarDatos();
        return cliente;
    }
    private void limpiarDatos() {
        inputNombre.setText(null);
        inputDireccion.setText(null);
        inputTelefono.setText(null);
        inputCorreo.setText(null);
    }
}
