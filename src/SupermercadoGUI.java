import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SupermercadoGUI extends JFrame {
    private JPanel panel;
    private JButton btnIniciarVenta, btnFinalizarCompra;
    private JTextField txtNombreCliente, txtNombreCajera, txtNombreProducto, txtPrecioProducto, txtCantidadProducto;
    private JTextArea txtInforme;
    private ArrayList<Producto> productos;
    private Cliente cliente;
    private Cajera cajera;
    private DefaultListModel<String> listModel;
    private JList<String> listProductos;

    public SupermercadoGUI() {
        setTitle("Simulador de Supermercado");
        setSize(400, 600);
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Logo (añade tu logo en la raíz del proyecto)
        ImageIcon logo = new ImageIcon("logo.png");
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblLogo);

        // Crear el botón "Iniciar Venta"
        btnIniciarVenta = new JButton("Iniciar Venta");
        btnIniciarVenta.setPreferredSize(new Dimension(150, 40));
        btnIniciarVenta.setAlignmentX(CENTER_ALIGNMENT);  // Centrado del botón
        btnIniciarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioVenta();
            }
        });
        panel.add(btnIniciarVenta);  // Agregar el botón al panel

        // Panel de contenido
        add(panel);
        setVisible(true);
    }

    private void mostrarFormularioVenta() {
        // Limpiar el panel
        panel.removeAll();

        // Crear un nuevo panel centrado para los campos de entrada
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new BoxLayout(formularioPanel, BoxLayout.Y_AXIS));
        formularioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrado de formulario

        // Nombre de Cliente
        JLabel lblNombreCliente = new JLabel("Nombre del Cliente:");
        lblNombreCliente.setAlignmentX(CENTER_ALIGNMENT);
        txtNombreCliente = new JTextField(15);
        txtNombreCliente.setMaximumSize(txtNombreCliente.getPreferredSize());
        formularioPanel.add(lblNombreCliente);
        formularioPanel.add(txtNombreCliente);

        // Nombre de Cajera
        JLabel lblNombreCajera = new JLabel("Nombre de la Cajera:");
        lblNombreCajera.setAlignmentX(CENTER_ALIGNMENT);
        txtNombreCajera = new JTextField(15);
        txtNombreCajera.setMaximumSize(txtNombreCajera.getPreferredSize());
        formularioPanel.add(lblNombreCajera);
        formularioPanel.add(txtNombreCajera);

        // Botón para ingresar productos
        JButton btnIngresarProducto = new JButton("Ingresar Producto");
        btnIngresarProducto.setPreferredSize(new Dimension(150, 40));
        btnIngresarProducto.setAlignmentX(CENTER_ALIGNMENT);  // Centrado del botón
        btnIngresarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarProducto();
            }
        });
        formularioPanel.add(btnIngresarProducto);

        // Lista de productos ingresados
        listModel = new DefaultListModel<>();
        listProductos = new JList<>(listModel);
        listProductos.setPreferredSize(new Dimension(300, 150));
        JScrollPane scrollPane = new JScrollPane(listProductos);
        formularioPanel.add(scrollPane);

        // Botón para finalizar compra
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.setPreferredSize(new Dimension(150, 40));
        btnFinalizarCompra.setAlignmentX(CENTER_ALIGNMENT);  // Centrado del botón
        btnFinalizarCompra.setEnabled(false); // Desactivado hasta ingresar productos
        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecibo();
            }
        });
        formularioPanel.add(btnFinalizarCompra);

        // Agregar el panel de formulario al panel principal
        panel.add(formularioPanel);

        // Redibujar la ventana para aplicar cambios
        panel.revalidate();
        panel.repaint();
    }

    private void ingresarProducto() {
        // Pedir información del producto (Nombre, Precio, Cantidad)
        JTextField txtNombreProducto = new JTextField(15);
        JTextField txtPrecioProducto = new JTextField(15);
        JTextField txtCantidadProducto = new JTextField(15);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Nombre del Producto:"));
        inputPanel.add(txtNombreProducto);
        inputPanel.add(new JLabel("Precio del Producto:"));
        inputPanel.add(txtPrecioProducto);
        inputPanel.add(new JLabel("Cantidad del Producto:"));
        inputPanel.add(txtCantidadProducto);

        int option = JOptionPane.showConfirmDialog(this, inputPanel, "Ingresar Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String nombre = txtNombreProducto.getText();
            double precio = Double.parseDouble(txtPrecioProducto.getText());
            int cantidad = Integer.parseInt(txtCantidadProducto.getText());

            // Crear el producto y agregarlo a la lista de productos
            Producto producto = new Producto(nombre, precio, cantidad);
            if (productos == null) {
                productos = new ArrayList<>();
            }
            productos.add(producto);

            // Actualizar la lista de productos en la interfaz
            listModel.addElement(producto.getNombre() + " - $" + producto.getPrecio() + " x " + producto.getCantidad());

            // Habilitar botón de finalizar compra
            btnFinalizarCompra.setEnabled(true);
        }
    }

    private void mostrarRecibo() {
        StringBuilder recibo = new StringBuilder();
        recibo.append("*********************************\n");
        recibo.append("            Recibo              \n");
        recibo.append("*********************************\n");
        recibo.append("Nombre del Cliente: " + txtNombreCliente.getText() + "\n");
        recibo.append("Nombre de la Cajera: " + txtNombreCajera.getText() + "\n");
        recibo.append("\nProductos Comprados:\n");

        double total = 0;
        for (Producto producto : productos) {
            recibo.append(producto.getNombre() + " - $" + producto.getPrecio() + " x " + producto.getCantidad() +
                    " (Tiempo de procesamiento: " + producto.getTiempoProcesamiento() + " ms)\n");
            total += producto.getCostoTotal();
        }

        recibo.append("\nTotal: $" + total + "\n");
        recibo.append("\n*********************************\n");
        recibo.append("Gracias por su compra!\n");

        // Mostrar recibo en un área de texto
        txtInforme = new JTextArea(20, 40);
        txtInforme.setText(recibo.toString());
        txtInforme.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(txtInforme), "Recibo", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SupermercadoGUI();
            }
        });
    }
}
