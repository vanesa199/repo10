/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal1;

/**
 *
 * @author Almacen S75
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRUDApp extends JFrame {
    private JTextField idField, nameField, emailField;
    private JButton createButton, readButton, updateButton, deleteButton;
    private UserDAO userDAO;

    public CRUDApp() {
        userDAO = new UserDAO();
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });
        add(createButton);

        readButton = new JButton("Read");
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readUser();
            }
        });
        add(readButton);

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
        add(deleteButton);

        setTitle("CRUD Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        userDAO.createUser(name, email);
        JOptionPane.showMessageDialog(this, "User created successfully!");
    }

    private void readUser() {
        int id = Integer.parseInt(idField.getText());
        User user = userDAO.readUser(id);
        if (user != null) {
            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
        } else {
            JOptionPane.showMessageDialog(this, "User not found!");
        }
    }

    private void updateUser() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String email = emailField.getText();
        userDAO.updateUser(id, name, email);
        JOptionPane.showMessageDialog(this, "User updated successfully!");
    }

    private void deleteUser() {
        int id = Integer.parseInt(idField.getText());
        userDAO.deleteUser(id);
        JOptionPane.showMessageDialog(this, "User deleted successfully!");
    }

    public static void main(String[] args) {
        // Ejecuta la aplicación en el hilo de despacho de eventos para asegurarse de que
        // todas las operaciones de GUI se realicen en el hilo correcto.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CRUDApp();
            }
        });
    }
}
