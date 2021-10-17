package agregar_datos_persona;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AgregaPersona02 extends JFrame{
    private Persona persona;
    private DefaultTableModel datosTabla;
    private int datoSeleccionado;

    private JPanel panelPrincipal;
    private JTextField inputNombre;
    private JTextField inputApellido;
    private JTextField inputEdad;
    private JComboBox comboSexo;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JTable datosPersonaTabla;
    private JButton actualizarButton;
    private JButton cancelarButton;

    public AgregaPersona02() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e);
        }

        initComponents();
        listenersButtons();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgregaPersona02().setVisible(true);
            }
        });
    }

    private void initComponents() {
        this.setTitle("Agregar personas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelPrincipal);
        this.setSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);

        String[] titulos = new String[] {"Nombres", "Apellidos", "Edad", "Sexo"};
        datosTabla = new DefaultTableModel(null, titulos);
        datosPersonaTabla.setModel(datosTabla);

        comboSexo.addItem("Femenino");
        comboSexo.addItem("Masculino");
        comboSexo.addItem("Otros");

        this.pack();
    }
    private void listenersButtons() {
        agregarButton.addActionListener(e -> agregarDatosDeLaPersona());
        editarButton.addActionListener(e -> editarPersonaSeleccionada());
        actualizarButton.addActionListener(e -> actualizarDatosDeLaPersona());
        eliminarButton.addActionListener(e -> eliminarPersonaSeleccionada());
        cancelarButton.addActionListener(e -> cancelarSeleccion());
        datosPersonaTabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editarButton.setEnabled(true);
                eliminarButton.setEnabled(true);
                datoSeleccionado = datosPersonaTabla.getSelectionModel().getAnchorSelectionIndex();
            }
        });
    }

    private void agregarDatosDeLaPersona() {
        Persona tempPersona = obtenerDatosDeLaPersona();
        if(tempPersona != null) {
            mostrarDatos(tempPersona);
        }
    }
    private void editarPersonaSeleccionada() {
        agregarButton.setEnabled(false);
        actualizarButton.setEnabled(true);
        cancelarButton.setEnabled(true);
        datosPersonaTabla.clearSelection();


        Vector datoTemp = personaSeleccionada();
        inputNombre.setText(datoTemp.get(0).toString());
        inputApellido.setText(datoTemp.get(1).toString());
        inputEdad.setText(datoTemp.get(2).toString());
        comboSexo.setSelectedItem(datoTemp.get(3).toString());
        editarButton.setEnabled(false);
        eliminarButton.setEnabled(false);
    }
    private void actualizarDatosDeLaPersona() {
        Persona tempPersona = obtenerDatosDeLaPersona();
        eliminarPersonaSeleccionada();
        mostrarDatos(tempPersona);
        cancelarSeleccion();
    }
    private void eliminarPersonaSeleccionada() {
        datosTabla.removeRow(this.datoSeleccionado);
    }

    private void cancelarSeleccion() {
        agregarButton.setEnabled(true);
        actualizarButton.setEnabled(false);
        eliminarButton.setEnabled(false);
        cancelarButton.setEnabled(false);
        datosPersonaTabla.clearSelection();
        editarButton.setEnabled(false);
        reiniciarCampos();
    }

    private void mostrarDatos(Persona persona) {
        datosTabla.addRow(new Object[] {persona.getNombre(),persona.getApellido(),persona.getEdad(),persona.getSexo()});
    }
    private Persona obtenerDatosDeLaPersona() {
        String nombre = inputNombre.getText();
        String apellido = inputApellido.getText();
        if(nombre.isEmpty() || apellido.isEmpty()) {
            notificacion("Error", "Unos de los campos esta vacio, no se pudo agregar.", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int edad = 0;
        try {
            edad = Integer.parseInt(inputEdad.getText());
        }catch (Exception e) {
            notificacion("Error", "El dato de edad debe ser un numero entero", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String sexo = comboSexo.getSelectedItem().toString();
        reiniciarCampos();
        return new Persona(nombre,apellido,edad,sexo);
    }
    private void reiniciarCampos() {
        inputNombre.setText("");
        inputApellido.setText("");
        inputEdad.setText("");
        comboSexo.setSelectedIndex(0);
    }
    private Vector personaSeleccionada() {
        Vector datoTemp = datosTabla.getDataVector().get(this.datoSeleccionado);
        return datoTemp;
    }

    private void notificacion(String titulo, String mensaje, int iconoMensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, iconoMensaje);
    }

    // TODO: Falta botones de editar y eliminar

}
