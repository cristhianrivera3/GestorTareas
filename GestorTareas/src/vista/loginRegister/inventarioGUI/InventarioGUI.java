package vista.loginRegister.inventarioGUI;

import Gestores.gestorProductos.GestorProductos;
import Modelo.Producto;
import Utils.EstilosGUI;
import Utils.Validador.Validador;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import vista.loginRegister.LoginRegisterGUI.LoginRegisterGUI;

public class InventarioGUI extends JFrame {
    private final GestorProductos gestorProductos;
    private final int usuarioId;
    private int contadorProductos;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel totalProductosLabel;
    private JLabel valorTotalLabel;

    public InventarioGUI(GestorProductos gestorProductos, int usuarioId, int contadorProductos) {
        this.gestorProductos = gestorProductos;
        this.usuarioId = usuarioId;
        this.contadorProductos = contadorProductos;
        initUI();
        cargarTabla();
    }

    private void initUI() {
        setTitle("📦 Mi Inventario Personal");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        EstilosGUI.estilizarPanel(mainPanel);
        
        // Panel superior con título y resumen
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("📊 Panel de Control de Inventario", SwingConstants.CENTER);
        titleLabel.setFont(EstilosGUI.FUENTE_TITULO);
        titleLabel.setForeground(EstilosGUI.COLOR_PRIMARIO);
        
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalProductosLabel = new JLabel("Productos: 0");
        valorTotalLabel = new JLabel("Valor total: $0");
        totalProductosLabel.setFont(EstilosGUI.FUENTE_NORMAL);
        valorTotalLabel.setFont(EstilosGUI.FUENTE_NORMAL);
        summaryPanel.add(totalProductosLabel);
        summaryPanel.add(Box.createHorizontalStrut(15));
        summaryPanel.add(valorTotalLabel);
        
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(summaryPanel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        // Tabla
        tableModel = new DefaultTableModel(new String[]{"ID", "📦 Producto", "📊 Stock", "💰 Precio"}, 0);
        table = new JTable(tableModel);
        EstilosGUI.estilizarTabla(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton addButton = new JButton("➕ Agregar Producto");
        JButton editButton = new JButton("✏️ Editar Producto");
        JButton deleteButton = new JButton("🗑️ Eliminar Producto");
        JButton logoutButton = new JButton("🚪 Cerrar Sesión");
        
        EstilosGUI.estilizarBoton(addButton, EstilosGUI.COLOR_SECUNDARIO);
        EstilosGUI.estilizarBoton(editButton, EstilosGUI.COLOR_PRIMARIO);
        EstilosGUI.estilizarBoton(deleteButton, EstilosGUI.COLOR_PELIGRO);
        EstilosGUI.estilizarBoton(logoutButton, new Color(107, 114, 128));
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);
        
        // Ensamblar
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Acciones
        addButton.addActionListener(e -> agregarProducto());
        editButton.addActionListener(e -> editarProducto());
        deleteButton.addActionListener(e -> eliminarProducto());
        logoutButton.addActionListener(e -> {
            new LoginRegisterGUI(new Gestores.gestorUsuario.GestorUsuario(), gestorProductos, contadorProductos).setVisible(true);
            dispose();
        });
    }

    private void cargarTabla() {
        tableModel.setRowCount(0);
        ArrayList<Producto> productos = gestorProductos.obtenerProductosPorUsuario(usuarioId);
        int totalProductos = 0;
        double valorTotal = 0;
        
        for (Producto p : productos) {
            tableModel.addRow(new Object[]{p.getId(), p.getNombre(), p.getStock(), String.format("$%.2f", p.getPrecio())});
            totalProductos++;
            valorTotal += p.getStock() * p.getPrecio();
        }
        
        totalProductosLabel.setText("📦 Productos: " + totalProductos);
        valorTotalLabel.setText(String.format("💰 Valor total: $%.2f", valorTotal));
    }

    private void agregarProducto() {
        JTextField nombreField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField precioField = new JTextField();
        
        EstilosGUI.estilizarCampoTexto(nombreField);
        EstilosGUI.estilizarCampoTexto(stockField);
        EstilosGUI.estilizarCampoTexto(precioField);
        
        Object[] message = {"Nombre:", nombreField, "Stock:", stockField, "Precio:", precioField};
        int option = JOptionPane.showConfirmDialog(this, message, "Nuevo Producto", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText();
                int stock = Integer.parseInt(stockField.getText());
                double precio = Double.parseDouble(precioField.getText());
                
                if (!Validador.textoNoVacio(nombre) || !Validador.esEnteroPositivo(stock) || !Validador.precioValido(precio)) {
                    JOptionPane.showMessageDialog(this, "❌ Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Producto nuevo = new Producto(contadorProductos++, nombre, stock, precio, usuarioId);
                gestorProductos.agregarProducto(nuevo);
                cargarTabla();
                JOptionPane.showMessageDialog(this, "✅ Producto agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Stock y precio deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarProducto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona un producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nombreActual = (String) tableModel.getValueAt(selectedRow, 1);
        int stockActual = (int) tableModel.getValueAt(selectedRow, 2);
        String precioActualStr = (String) tableModel.getValueAt(selectedRow, 3);
        double precioActual = Double.parseDouble(precioActualStr.replace("$", ""));
        
        JTextField nombreField = new JTextField(nombreActual);
        JTextField stockField = new JTextField(String.valueOf(stockActual));
        JTextField precioField = new JTextField(String.valueOf(precioActual));
        
        EstilosGUI.estilizarCampoTexto(nombreField);
        EstilosGUI.estilizarCampoTexto(stockField);
        EstilosGUI.estilizarCampoTexto(precioField);
        
        Object[] message = {"Nombre:", nombreField, "Stock:", stockField, "Precio:", precioField};
        int option = JOptionPane.showConfirmDialog(this, message, "Editar Producto", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nuevoNombre = nombreField.getText();
                int nuevoStock = Integer.parseInt(stockField.getText());
                double nuevoPrecio = Double.parseDouble(precioField.getText());
                
                gestorProductos.actualizarProducto(id, nuevoNombre, nuevoStock, nuevoPrecio);
                cargarTabla();
                JOptionPane.showMessageDialog(this, "✅ Producto actualizado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Stock y precio deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void eliminarProducto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona un producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nombre = (String) tableModel.getValueAt(selectedRow, 1);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar '" + nombre + "'?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            gestorProductos.eliminarProducto(id);
            cargarTabla();
            JOptionPane.showMessageDialog(this, "✅ Producto eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}