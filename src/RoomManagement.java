import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RoomManagement extends JFrame {

    // ================= DATABASE =================
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "priyan21";

    // ================= COLORS =================
    private static final Color BACKGROUND = new Color(18, 32, 52);
    private static final Color PANEL_COLOR = new Color(25, 45, 68);
    private static final Color FIELD_COLOR = new Color(35, 58, 82);
    private static final Color TABLE_COLOR = new Color(22, 40, 60);

    private static final Color ACCENT = new Color(80, 210, 180);
    private static final Color BLUE = new Color(70, 145, 235);
    private static final Color RED = new Color(235, 85, 95);
    private static final Color ORANGE = new Color(240, 165, 70);

    private static final Color TEXT_COLOR = new Color(235, 245, 250);
    private static final Color SECONDARY_TEXT = new Color(175, 195, 210);

    // ================= COMPONENTS =================
    private JTextField roomIdField;
    private JTextField roomNumberField;
    private JComboBox<String> roomTypeBox;
    private JTextField priceField;
    private JComboBox<String> statusBox;
    private JTextArea maintenanceArea;

    private JTable table;
    private DefaultTableModel model;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton refreshButton;

    // ================= CONSTRUCTOR =================
    public RoomManagement() {

        setTitle("Room Management - Grand Horizon Hotel");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(BACKGROUND);
        setLayout(new BorderLayout(10, 10));

        // ================= HEADER =================
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(BACKGROUND);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel titleLabel = new JLabel("ROOM MANAGEMENT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        titleLabel.setForeground(ACCENT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(
                "Grand Horizon Hotel • Manage Rooms and Maintenance"
        );
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(SECONDARY_TEXT);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(3));
        headerPanel.add(subtitleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // ================= FORM PANEL =================
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_COLOR);
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(45, 70, 95)),
                        BorderFactory.createEmptyBorder(12, 15, 12, 15)
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Dimension fieldSize = new Dimension(180, 30);

        // Room ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(createLabel("Room ID"), gbc);

        roomIdField = createTextField();
        roomIdField.setPreferredSize(fieldSize);

        gbc.gridx = 1;
        formPanel.add(roomIdField, gbc);

        // Room Number
        gbc.gridx = 2;
        formPanel.add(createLabel("Room Number"), gbc);

        roomNumberField = createTextField();
        roomNumberField.setPreferredSize(fieldSize);

        gbc.gridx = 3;
        formPanel.add(roomNumberField, gbc);

        // Room Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(createLabel("Room Type"), gbc);

        roomTypeBox = new JComboBox<>(
                new String[]{
                        "Single",
                        "Double",
                        "Deluxe",
                        "Suite"
                }
        );
        styleComboBox(roomTypeBox);
        roomTypeBox.setPreferredSize(fieldSize);

        gbc.gridx = 1;
        formPanel.add(roomTypeBox, gbc);

        // Price
        gbc.gridx = 2;
        formPanel.add(createLabel("Price / Day"), gbc);

        priceField = createTextField();
        priceField.setPreferredSize(fieldSize);

        gbc.gridx = 3;
        formPanel.add(priceField, gbc);

        // Status
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(createLabel("Status"), gbc);

        statusBox = new JComboBox<>(
                new String[]{
                        "Available",
                        "Booked",
                        "Maintenance"
                }
        );
        styleComboBox(statusBox);
        statusBox.setPreferredSize(fieldSize);

        gbc.gridx = 1;
        formPanel.add(statusBox, gbc);

        // Maintenance Comment
        gbc.gridx = 2;
        formPanel.add(createLabel("Maintenance"), gbc);

        maintenanceArea = new JTextArea(2, 15);
        maintenanceArea.setLineWrap(true);
        maintenanceArea.setWrapStyleWord(true);
        maintenanceArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        maintenanceArea.setForeground(TEXT_COLOR);
        maintenanceArea.setBackground(FIELD_COLOR);
        maintenanceArea.setCaretColor(TEXT_COLOR);
        maintenanceArea.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(60, 85, 110)),
                        BorderFactory.createEmptyBorder(4, 6, 4, 6)
                )
        );

        JScrollPane maintenanceScroll = new JScrollPane(maintenanceArea);
        maintenanceScroll.setPreferredSize(new Dimension(180, 48));
        maintenanceScroll.setBorder(null);

        gbc.gridx = 3;
        formPanel.add(maintenanceScroll, gbc);

        // ================= FORM + BUTTON AREA =================
        JPanel topPanel = new JPanel(new BorderLayout(10, 8));
        topPanel.setBackground(BACKGROUND);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 5, 15));

        topPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        buttonPanel.setBackground(PANEL_COLOR);

        addButton = createButton("Add Room", ACCENT);
        updateButton = createButton("Update", BLUE);
        deleteButton = createButton("Delete", RED);
        clearButton = createButton("Clear", ORANGE);
        refreshButton = createButton("Refresh", new Color(130, 110, 220));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.CENTER);

        // ================= TABLE =================
        model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Breakfast and Wi-Fi are NOT included
        model.setColumnIdentifiers(
                new String[]{
                        "Room ID",
                        "Room Number",
                        "Room Type",
                        "Price / Day",
                        "Status",
                        "Maintenance"
                }
        );

        table = new JTable(model);

        table.setBackground(TABLE_COLOR);
        table.setForeground(TEXT_COLOR);
        table.setGridColor(new Color(50, 70, 90));
        table.setSelectionBackground(new Color(55, 95, 120));
        table.setSelectionForeground(Color.WHITE);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);

        table.getTableHeader().setBackground(new Color(35, 60, 85));
        table.getTableHeader().setForeground(ACCENT);
        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));

        // Column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(300);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBackground(BACKGROUND);
        tableScroll.getViewport().setBackground(TABLE_COLOR);
        tableScroll.setBorder(
                BorderFactory.createLineBorder(new Color(45, 70, 95))
        );

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(BACKGROUND);
        tablePanel.setBorder(
                BorderFactory.createEmptyBorder(0, 15, 15, 15)
        );

        tablePanel.add(tableScroll, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.SOUTH);

        // Better layout: put form at top and table in center
        remove(topPanel);
        remove(tablePanel);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        // ================= BUTTON ACTIONS =================
        addButton.addActionListener(e -> addRoom());
        updateButton.addActionListener(e -> updateRoom());
        deleteButton.addActionListener(e -> deleteRoom());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadRooms());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                int row = table.getSelectedRow();

                roomIdField.setText(
                        model.getValueAt(row, 0).toString()
                );

                roomNumberField.setText(
                        model.getValueAt(row, 1).toString()
                );

                roomTypeBox.setSelectedItem(
                        model.getValueAt(row, 2).toString()
                );

                priceField.setText(
                        model.getValueAt(row, 3).toString()
                );

                statusBox.setSelectedItem(
                        model.getValueAt(row, 4).toString()
                );

                Object maintenance =
                        model.getValueAt(row, 5);

                maintenanceArea.setText(
                        maintenance == null
                                ? ""
                                : maintenance.toString()
                );
            }
        });

        loadRooms();
    }

    // ================= LABEL =================
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        label.setForeground(TEXT_COLOR);

        return label;
    }

    // ================= TEXT FIELD =================
    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        field.setForeground(TEXT_COLOR);
        field.setBackground(FIELD_COLOR);
        field.setCaretColor(TEXT_COLOR);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(60, 85, 110)
                        ),
                        BorderFactory.createEmptyBorder(
                                4, 7, 4, 7
                        )
                )
        );

        return field;
    }

    // ================= COMBO BOX =================
    private void styleComboBox(JComboBox<String> combo) {

        combo.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        combo.setForeground(TEXT_COLOR);
        combo.setBackground(FIELD_COLOR);

        combo.setFocusable(false);
    }

    // ================= BUTTON =================
    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setPreferredSize(
                new Dimension(105, 32)
        );

        return button;
    }

    // ================= DATABASE CONNECTION =================
    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    // ================= LOAD ROOMS =================
    private void loadRooms() {

        model.setRowCount(0);

        String sql =
                "SELECT room_id, room_number, room_type, price, status, " +
                "maintenance_comment FROM rooms ORDER BY room_number";

        try (
                Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()
        ) {

            while (rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("room_id"),
                                rs.getString("room_number"),
                                rs.getString("room_type"),
                                rs.getDouble("price"),
                                rs.getString("status"),
                                rs.getString("maintenance_comment")
                        }
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading rooms:\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= ADD ROOM =================
    private void addRoom() {

        if (!validateFields()) {
            return;
        }

        String sql =
                "INSERT INTO rooms " +
                "(room_number, room_type, price, status, maintenance_comment) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    roomNumberField.getText().trim()
            );

            pst.setString(
                    2,
                    roomTypeBox.getSelectedItem().toString()
            );

            pst.setDouble(
                    3,
                    Double.parseDouble(
                            priceField.getText().trim()
                    )
            );

            pst.setString(
                    4,
                    statusBox.getSelectedItem().toString()
            );

            pst.setString(
                    5,
                    maintenanceArea.getText().trim()
            );

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Room added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadRooms();
            clearFields();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error adding room:\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= UPDATE ROOM =================
    private void updateRoom() {

        if (roomIdField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room from the table.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validateFields()) {
            return;
        }

        String sql =
                "UPDATE rooms SET " +
                "room_number=?, room_type=?, price=?, status=?, " +
                "maintenance_comment=? " +
                "WHERE room_id=?";

        try (
                Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    roomNumberField.getText().trim()
            );

            pst.setString(
                    2,
                    roomTypeBox.getSelectedItem().toString()
            );

            pst.setDouble(
                    3,
                    Double.parseDouble(
                            priceField.getText().trim()
                    )
            );

            pst.setString(
                    4,
                    statusBox.getSelectedItem().toString()
            );

            pst.setString(
                    5,
                    maintenanceArea.getText().trim()
            );

            pst.setInt(
                    6,
                    Integer.parseInt(
                            roomIdField.getText().trim()
                    )
            );

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Room updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadRooms();
            clearFields();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error updating room:\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= DELETE ROOM =================
    private void deleteRoom() {

        if (roomIdField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room from the table.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this room?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM rooms WHERE room_id=?";

        try (
                Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(
                    1,
                    Integer.parseInt(
                            roomIdField.getText().trim()
                    )
            );

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Room deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadRooms();
            clearFields();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting room:\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= VALIDATION =================
    private boolean validateFields() {

        if (roomNumberField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Room Number."
            );

            return false;
        }

        if (priceField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Room Price."
            );

            return false;
        }

        try {

            double price =
                    Double.parseDouble(
                            priceField.getText().trim()
                    );

            if (price <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than 0."
                );

                return false;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price."
            );

            return false;
        }

        return true;
    }

    // ================= CLEAR =================
    private void clearFields() {

        roomIdField.setText("");
        roomNumberField.setText("");

        roomTypeBox.setSelectedIndex(0);

        priceField.setText("");

        statusBox.setSelectedIndex(0);

        maintenanceArea.setText("");

        table.clearSelection();
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }

            new RoomManagement().setVisible(true);
        });
    }
}