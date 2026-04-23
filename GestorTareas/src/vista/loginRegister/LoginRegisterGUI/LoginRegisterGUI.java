package vista.loginRegister.LoginRegisterGUI;

import Gestores.gestorProductos.GestorProductos;
import Gestores.gestorUsuario.GestorUsuario;
import Modelo.usuario.Usuario;
import Utils.EstilosGUI;
import Utils.Validador.Validador;
import java.awt.*;
import javax.swing.*;
import vista.loginRegister.inventarioGUI.InventarioGUI;

public class LoginRegisterGUI extends JFrame {
    private final GestorUsuario gestorUsuario;
    private final GestorProductos gestorProductos;
    private int contadorUsuarios;

    public LoginRegisterGUI(GestorUsuario gestorUsuario, GestorProductos gestorProductos, int contadorUsuarios) {
        this.gestorUsuario = gestorUsuario;
        this.gestorProductos = gestorProductos;
        this.contadorUsuarios = contadorUsuarios;
        initUI();
    }

    private void initUI() {
        setTitle("✨ Gestor de Inventario Premium ✨");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Panel principal con fondo
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(EstilosGUI.COLOR_FONDO);
        
        // Título
        JLabel titleLabel = new JLabel("Bienvenido a tu Inventario", SwingConstants.CENTER);
        titleLabel.setFont(EstilosGUI.FUENTE_TITULO);
        titleLabel.setForeground(EstilosGUI.COLOR_PRIMARIO);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(EstilosGUI.FUENTE_NORMAL);
        tabbedPane.addTab("🔐 Iniciar Sesión", createLoginPanel());
        tabbedPane.addTab("📝 Registrarse", createRegisterPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        EstilosGUI.estilizarPanel(panel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Email
        JLabel emailLabel = new JLabel("📧 Correo Electrónico:");
        emailLabel.setFont(EstilosGUI.FUENTE_NORMAL);
        JTextField emailField = new JTextField(15);
        EstilosGUI.estilizarCampoTexto(emailField);
        
        // Contraseña
        JLabel passLabel = new JLabel("🔒 Contraseña:");
        passLabel.setFont(EstilosGUI.FUENTE_NORMAL);
        JPasswordField passField = new JPasswordField(15);
        EstilosGUI.estilizarCampoTexto(passField);
        
        // Botón
        JButton loginButton = new JButton("Ingresar");
        EstilosGUI.estilizarBoton(loginButton, EstilosGUI.COLOR_PRIMARIO);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String pass = new String(passField.getPassword());
            if (gestorUsuario.login(email, pass)) {
                Usuario usuario = gestorUsuario.buscarPorEmail(email);
                new InventarioGUI(gestorProductos, usuario.getId(), contadorUsuarios).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "❌ Email o contraseña incorrectos", 
                    "Error de autenticación", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        EstilosGUI.estilizarPanel(panel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        JLabel nameLabel = new JLabel("👤 Nombre:");
        nameLabel.setFont(EstilosGUI.FUENTE_NORMAL);
        JTextField nameField = new JTextField(15);
        EstilosGUI.estilizarCampoTexto(nameField);
        
        JLabel emailLabel = new JLabel("📧 Email:");
        emailLabel.setFont(EstilosGUI.FUENTE_NORMAL);
        JTextField emailField = new JTextField(15);
        EstilosGUI.estilizarCampoTexto(emailField);
        
        JLabel passLabel = new JLabel("🔒 Contraseña:");
        passLabel.setFont(EstilosGUI.FUENTE_NORMAL);
        JPasswordField passField = new JPasswordField(15);
        EstilosGUI.estilizarCampoTexto(passField);
        
        JButton registerButton = new JButton("Registrarse");
        EstilosGUI.estilizarBoton(registerButton, EstilosGUI.COLOR_SECUNDARIO);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        registerButton.addActionListener(e -> {
            String nombre = nameField.getText();
            String email = emailField.getText();
            String pass = new String(passField.getPassword());
            
            if (!Validador.textoNoVacio(nombre) || !Validador.emailValido(email) || !Validador.textoNoVacio(pass)) {
                JOptionPane.showMessageDialog(this, "❌ Datos inválidos. Verifica los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Usuario nuevo = new Usuario(contadorUsuarios, nombre, email, pass);
            gestorUsuario.registrarUsuario(nuevo);
            contadorUsuarios++;
            JOptionPane.showMessageDialog(this, "✅ ¡Usuario registrado con éxito! Ahora inicia sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            nameField.setText("");
            emailField.setText("");
            passField.setText("");
        });

        return panel;
    }
}