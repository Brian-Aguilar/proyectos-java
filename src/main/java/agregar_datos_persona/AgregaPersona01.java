package agregar_datos_persona;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class AgregaPersona01 extends JFrame {
    private Vector<String> personas = new Vector<String>();

    private Dimension dimensiones;
    private JTextField inputNombre;
    private JPanel PrincipalPanel;
    private JList<String> listPersonas;
    private JButton agregarAlFinalButton;
    private JButton agregarAlIncioButton;
    private JButton quitarSeleccionadoButton;
    private JButton obtieneElPrimeroButton;
    private JButton obtieneElÚltimoButton;
    private JButton empiezaConButton;
    private JButton vaciarListaButton;
    private JLabel txtTotalDePersonas;

    public AgregaPersona01() {
        try{
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e);
        }

        initComponents();
        listenersBotones();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgregaPersona01().setVisible(true);
            }
        });
    }

    private void initComponents() {
        dimensiones = new Dimension(400,400);
        this.setTitle("Agregar personas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PrincipalPanel);

        this.setMaximumSize(dimensiones);
        this.setMinimumSize(dimensiones);
        this.setSize(dimensiones);
        this.setLocationRelativeTo(null);

        this.pack();
    }

    private void listenersBotones() {
        agregarAlFinalButton.addActionListener(e -> this.agregarDatoAlFinal());
        agregarAlIncioButton.addActionListener(e -> this.agregarDatoAlInicio());
        quitarSeleccionadoButton.addActionListener(e -> this.quitarDatoSeleccionado());
        vaciarListaButton.addActionListener(e -> this.eliminarDatosPersonas());
        obtieneElPrimeroButton.addActionListener(e -> this.obtenerLaPrimeraPersona());
        obtieneElÚltimoButton.addActionListener(e -> this.obtenerLaUltimaPersona());
        empiezaConButton.addActionListener(e -> this.empiezaElDatoSeleccionadoCon());
    }

    private String obtenerDatoDelInput() {
        Persona persona = new Persona(inputNombre.getText());
        inputNombre.setText("");
        return persona.getNombre();
    }
    private void agregarDatoAlFinal() {
        String nombre = this.obtenerDatoDelInput();
        if(!nombre.isEmpty() || nombre.length() > 0) {
            personas.addElement(nombre);
            mostrarDatos();
            return;
        }
        notificacion("Error", "No hay ningún dato ingresado.", JOptionPane.ERROR_MESSAGE);
    }
    private void agregarDatoAlInicio() {
        String nombre = this.obtenerDatoDelInput();
        if(!nombre.isEmpty() || nombre.length() > 0) {
            personas.add(0,nombre);
            mostrarDatos();
            return;
        }
        notificacion("Error", "No hay ningún dato ingresado.", JOptionPane.ERROR_MESSAGE);
    }
    private void quitarDatoSeleccionado() {
        String nombre = listPersonas.getSelectedValue();
       if(personas.contains(nombre)) {
           personas.remove(nombre);
           mostrarDatos();
           return;
       }
        notificacion("Error", "No hay ningún dato seleccionado.", JOptionPane.ERROR_MESSAGE);

    }
    private void eliminarDatosPersonas() {
        if(!personas.isEmpty()) {
            personas.clear();
            mostrarDatos();
        }
    }
    private void obtenerLaPrimeraPersona() {
        if(personas.size() > 0) {
            String primeraPersona = personas.firstElement().toString();
            notificacion("Obtener primera persona",
                    "La primera persona de la lista es: " + primeraPersona,
                    JOptionPane.NO_OPTION);
            return;
        }
        notificacion("Obtener primera persona",
                "No hay ningún dato.",
                JOptionPane.ERROR_MESSAGE);
    }
    private void obtenerLaUltimaPersona() {
        if(personas.size() > 0) {
            String ultimaPersona = personas.lastElement().toString();
            notificacion("Obtener ultima persona",
                    "La primera persona de la lista es: " + ultimaPersona,
                    JOptionPane.NO_OPTION);
            return;
        }
        notificacion("Obtener ultima persona",
                "No hay ningún dato.",
                JOptionPane.ERROR_MESSAGE);
    }
    private void empiezaElDatoSeleccionadoCon() {
        String nombre = this.obtenerDatoDelInput();
        String persona = "";
        if(!nombre.isEmpty() || nombre.length() != 0) {
            for (int i = 0; i < personas.size(); i++) {
                persona = personas.elementAt(i).toString();
                if(persona.startsWith(nombre)) {
                    notificacion("Persona encontrado", persona, JOptionPane.INFORMATION_MESSAGE);
                    inputNombre.setText(persona);
                    break;
                }
            }
            return;
        }
    }
    private void mostrarDatos() {
        listPersonas.setListData(personas);
        txtTotalDePersonas.setText(Integer.toString(personas.size()) );
        inputNombre.grabFocus();
    }
    private void notificacion(String titulo, String mensaje, int iconoMensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, iconoMensaje);
    }
}
