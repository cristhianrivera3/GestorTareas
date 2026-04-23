package Utils;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EstilosGUI {
    
    // Colores modernos (paleta pastel/neón)
    public static final Color COLOR_PRIMARIO = new Color(79, 70, 229);    // Índigo
    public static final Color COLOR_SECUNDARIO = new Color(16, 185, 129); // Verde menta
    public static final Color COLOR_PELIGRO = new Color(239, 68, 68);     // Rojo
    public static final Color COLOR_FONDO = new Color(249, 250, 251);     // Gris muy claro
    public static final Color COLOR_TEXTO = new Color(31, 41, 55);        // Gris oscuro
    
    // Fuentes
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 13);
    
    // Método para estilizar botones
    public static void estilizarBoton(JButton boton, Color color) {
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(FUENTE_BOTON);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });
    }
    
    // Método para estilizar paneles
    public static void estilizarPanel(JPanel panel) {
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
    }
    
    // Método para estilizar campos de texto
    public static void estilizarCampoTexto(JTextField campo) {
        campo.setFont(FUENTE_NORMAL);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }
    
    // Método para estilizar la tabla
    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_NORMAL);
        tabla.setRowHeight(30);
        tabla.setIntercellSpacing(new Dimension(10, 5));
        tabla.setShowGrid(false);
        tabla.setBackground(Color.WHITE);
        
        // Encabezado de tabla
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(COLOR_PRIMARIO);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setPreferredSize(new Dimension(100, 35));
        
        // Colores alternados en filas
        tabla.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(243, 244, 246));
                }
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        });
    }
}