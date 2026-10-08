import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CustomerManagement extends JFrame {

    JTextField idField, nameField, phoneField, emailField, searchField;
    JTextArea addressArea;
    JRadioButton maleRadio, femaleRadio, otherRadio;
    JCheckBox idProofCheck, termsCheck;

    JTable table;
    DefaultTableModel model;

    // =========================
    // COLORS
    // =========================

    private final Color BACKGROUND = new Color(18, 32, 52);
    private final Color PANEL_COLOR = new Color(25, 45, 68);
    private final Color FIELD_COLOR = new Color(35, 58, 82);
    private final Color BUTTON_COLOR = new Color(40, 95, 125);
    private final Color BUTTON_HOVER = new Color(55, 120, 150);
    private final Color ACCENT = new Color(80, 210, 180);
    private final Color TEXT_COLOR = new Color(235, 245, 250);
    private final Color SECONDARY_TEXT = new Color(175, 195, 210);

    public CustomerManagement() {

        setTitle("Customer Management - Grand Horizon Hotel");
        setSize(1050, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(15, 15, 15, 15)
        );

        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel("CUSTOMER MANAGEMENT");

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        title.setForeground(ACCENT);

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        JLabel subtitle =
                new JLabel(
                        "Grand Horizon Hotel  •  Manage Guest Information"
                );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        subtitle.setForeground(SECONDARY_TEXT);

        subtitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        JPanel headerPanel =
                new JPanel(
                        new GridLayout(2, 1, 0, 3)
                );

        headerPanel.setBackground(BACKGROUND);

        headerPanel.add(title);
        headerPanel.add(subtitle);

        // =========================
        // TABBED PANE
        // =========================

        JTabbedPane tabbedPane =
                new JTabbedPane();

        tabbedPane.setBackground(PANEL_COLOR);
        tabbedPane.setForeground(TEXT_COLOR);

        tabbedPane.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        // =========================
        // CUSTOMER DETAILS
        // =========================

        JPanel detailsPanel =
                new JPanel(new GridBagLayout());

        detailsPanel.setBackground(PANEL_COLOR);

        detailsPanel.setBorder(
                new EmptyBorder(15, 25, 15, 25)
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 10, 8, 10);

        gbc.anchor =
                GridBagConstraints.WEST;

        // =========================
        // CUSTOMER ID
        // =========================

        JLabel idLabel =
                createLabel("Customer ID");

        idField =
                createTextField();

        idField.setEditable(false);

        idField.setBackground(
                new Color(45, 65, 82)
        );

        addRow(
                detailsPanel,
                gbc,
                0,
                idLabel,
                idField
        );

        // =========================
        // NAME
        // =========================

        JLabel nameLabel =
                createLabel("Name");

        nameField =
                createTextField();

        addRow(
                detailsPanel,
                gbc,
                1,
                nameLabel,
                nameField
        );

        // =========================
        // PHONE
        // =========================

        JLabel phoneLabel =
                createLabel("Phone");

        phoneField =
                createTextField();

        addRow(
                detailsPanel,
                gbc,
                2,
                phoneLabel,
                phoneField
        );

        // =========================
        // EMAIL
        // =========================

        JLabel emailLabel =
                createLabel("Email");

        emailField =
                createTextField();

        addRow(
                detailsPanel,
                gbc,
                3,
                emailLabel,
                emailField
        );

        // =========================
        // GENDER
        // =========================

        JLabel genderLabel =
                createLabel("Gender");

        maleRadio =
                createRadioButton("Male");

        femaleRadio =
                createRadioButton("Female");

        otherRadio =
                createRadioButton("Other");

        ButtonGroup genderGroup =
                new ButtonGroup();

        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        maleRadio.setSelected(true);

        JPanel genderPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                0
                        )
                );

        genderPanel.setBackground(PANEL_COLOR);

        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);

        addRow(
                detailsPanel,
                gbc,
                4,
                genderLabel,
                genderPanel
        );

        // =========================
        // ADDRESS
        // =========================

        JLabel addressLabel =
                createLabel("Address");

        addressArea =
                new JTextArea(3, 25);

        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);

        addressArea.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        addressArea.setForeground(TEXT_COLOR);
        addressArea.setBackground(FIELD_COLOR);
        addressArea.setCaretColor(TEXT_COLOR);

        addressArea.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(65, 90, 110)
                        ),
                        new EmptyBorder(
                                5, 7, 5, 7
                        )
                )
        );

        JScrollPane addressScroll =
                new JScrollPane(addressArea);

        addressScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(65, 90, 110)
                )
        );

        addressScroll.setPreferredSize(
                new Dimension(280, 75)
        );

        addRow(
                detailsPanel,
                gbc,
                5,
                addressLabel,
                addressScroll
        );

        // =========================
        // CHECKBOXES
        // =========================

        JLabel optionLabel =
                createLabel("Verification");

        idProofCheck =
                createCheckBox(
                        "ID Proof Submitted"
                );

        termsCheck =
                createCheckBox(
                        "Terms Accepted"
                );

        JPanel checkPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                0
                        )
                );

        checkPanel.setBackground(PANEL_COLOR);

        checkPanel.add(idProofCheck);
        checkPanel.add(termsCheck);

        addRow(
                detailsPanel,
                gbc,
                6,
                optionLabel,
                checkPanel
        );

        tabbedPane.addTab(
                "  Customer Details  ",
                detailsPanel
        );

        // =========================
        // BUTTONS
        // =========================

        JButton addButton =
                createButton(
                        "Add",
                        new Color(35, 130, 90)
                );

        JButton updateButton =
                createButton(
                        "Update",
                        new Color(45, 105, 155)
                );

        JButton deleteButton =
                createButton(
                        "Delete",
                        new Color(155, 55, 65)
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        new Color(100, 80, 120)
                );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                8
                        )
                );

        buttonPanel.setBackground(BACKGROUND);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // =========================
        // SEARCH
        // =========================

        searchField =
                createTextField();

        searchField.setPreferredSize(
                new Dimension(180, 32)
        );

        JButton searchButton =
                createButton(
                        "Search",
                        BUTTON_COLOR
                );

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                8
                        )
                );

        searchPanel.setBackground(
                new Color(22, 40, 61)
        );

        JLabel searchLabel =
                new JLabel("Search Customer:");

        searchLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        searchLabel.setForeground(TEXT_COLOR);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // =========================
        // TABLE
        // =========================

        model =
                new DefaultTableModel();

        model.addColumn("Customer ID");
        model.addColumn("Name");
        model.addColumn("Phone");
        model.addColumn("Email");
        model.addColumn("Address");

        table =
                new JTable(model);

        table.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        table.setRowHeight(30);

        table.setBackground(
                new Color(28, 48, 70)
        );

        table.setForeground(TEXT_COLOR);

        table.setSelectionBackground(
                new Color(40, 105, 115)
        );

        table.setSelectionForeground(Color.WHITE);

        table.setGridColor(
                new Color(60, 80, 100)
        );

        table.setShowGrid(true);
        table.setFillsViewportHeight(true);

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        table.getTableHeader().setBackground(
                new Color(35, 75, 95)
        );

        table.getTableHeader().setForeground(
                Color.WHITE
        );

        table.getTableHeader().setPreferredSize(
                new Dimension(0, 35)
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(55, 80, 100)
                )
        );

        scrollPane.getViewport().setBackground(
                new Color(28, 48, 70)
        );

        // =========================
        // TOP PANEL
        // =========================

        JPanel topPanel =
                new JPanel(new BorderLayout(0, 8));

        topPanel.setBackground(BACKGROUND);

        topPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        topPanel.add(
                tabbedPane,
                BorderLayout.CENTER
        );

        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // MAIN FRAME
        // =========================

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                searchPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // =========================
        // LOAD CUSTOMERS
        // =========================

        loadCustomers();

        // =========================
        // BUTTON ACTIONS
        // =========================

        addButton.addActionListener(
                e -> addCustomer()
        );

        updateButton.addActionListener(
                e -> updateCustomer()
        );

        deleteButton.addActionListener(
                e -> deleteCustomer()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        searchButton.addActionListener(
                e -> searchCustomer()
        );

        // =========================
        // TABLE ROW CLICK
        // =========================

        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    int row =
                            table.getSelectedRow();

                    if (row >= 0) {

                        idField.setText(
                                model.getValueAt(
                                        row, 0
                                ).toString()
                        );

                        nameField.setText(
                                model.getValueAt(
                                        row, 1
                                ).toString()
                        );

                        phoneField.setText(
                                model.getValueAt(
                                        row, 2
                                ).toString()
                        );

                        emailField.setText(
                                model.getValueAt(
                                        row, 3
                                ).toString()
                        );

                        addressArea.setText(
                                model.getValueAt(
                                        row, 4
                                ).toString()
                        );
                    }
                });
    }

    // =========================
    // LABEL
    // =========================

    JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        label.setForeground(TEXT_COLOR);

        return label;
    }

    // =========================
    // TEXT FIELD
    // =========================

    JTextField createTextField() {

        JTextField field =
                new JTextField(18);

        field.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        field.setForeground(TEXT_COLOR);
        field.setBackground(FIELD_COLOR);
        field.setCaretColor(TEXT_COLOR);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(65, 90, 110)
                        ),
                        new EmptyBorder(
                                5, 8, 5, 8
                        )
                )
        );

        return field;
    }

    // =========================
    // RADIO BUTTON
    // =========================

    JRadioButton createRadioButton(
            String text) {

        JRadioButton radio =
                new JRadioButton(text);

        radio.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        radio.setForeground(TEXT_COLOR);
        radio.setBackground(PANEL_COLOR);

        return radio;
    }

    // =========================
    // CHECKBOX
    // =========================

    JCheckBox createCheckBox(
            String text) {

        JCheckBox check =
                new JCheckBox(text);

        check.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        check.setForeground(TEXT_COLOR);
        check.setBackground(PANEL_COLOR);

        return check;
    }

    // =========================
    // BUTTON
    // =========================

    JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setPreferredSize(
                new Dimension(110, 35)
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                BUTTON_HOVER
                        );
                    }

                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(color);
                    }
                }
        );

        return button;
    }

    // =========================
    // ADD FORM ROW
    // =========================

    void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            Component component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(component, gbc);
    }

    // =========================
    // CHECKBOX VALIDATION
    // =========================

    boolean verifyCustomer() {

        if (!idProofCheck.isSelected()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please tick 'ID Proof Submitted' before updating customer details.",
                    "Verification Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (!termsCheck.isSelected()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please tick 'Terms Accepted' before updating customer details.",
                    "Terms Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =========================
    // LOAD CUSTOMERS
    // =========================

    void loadCustomers() {

        model.setRowCount(0);

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String sql =
                    "SELECT * FROM customers";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("customer_id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("email"),
                                rs.getString("address")
                        }
                );
            }

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // ADD CUSTOMER
    // =========================

    void addCustomer() {

        try {

            if (nameField.getText()
                    .trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter customer name.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // CHECKBOX VALIDATION
            if (!verifyCustomer()) {
                return;
            }

            Connection con =
                    DatabaseConnection.getConnection();

            String sql =
                    "INSERT INTO customers " +
                    "(name, phone, email, address) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(
                    1,
                    nameField.getText().trim()
            );

            pst.setString(
                    2,
                    phoneField.getText().trim()
            );

            pst.setString(
                    3,
                    emailField.getText().trim()
            );

            pst.setString(
                    4,
                    addressArea.getText().trim()
            );

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Customer Added Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            con.close();

            loadCustomers();
            clearFields();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // UPDATE CUSTOMER
    // =========================

    void updateCustomer() {

        try {

            if (idField.getText()
                    .trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a customer from the table first.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // CHECKBOX VALIDATION
            if (!verifyCustomer()) {
                return;
            }

            Connection con =
                    DatabaseConnection.getConnection();

            String sql =
                    "UPDATE customers SET " +
                    "name=?, phone=?, email=?, address=? " +
                    "WHERE customer_id=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(
                    1,
                    nameField.getText().trim()
            );

            pst.setString(
                    2,
                    phoneField.getText().trim()
            );

            pst.setString(
                    3,
                    emailField.getText().trim()
            );

            pst.setString(
                    4,
                    addressArea.getText().trim()
            );

            pst.setInt(
                    5,
                    Integer.parseInt(
                            idField.getText().trim()
                    )
            );

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Customer Updated Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            con.close();

            loadCustomers();
            clearFields();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // DELETE CUSTOMER
    // =========================

    void deleteCustomer() {

        try {

            if (idField.getText()
                    .trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a customer first.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this customer?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            Connection con =
                    DatabaseConnection.getConnection();

            String sql =
                    "DELETE FROM customers " +
                    "WHERE customer_id=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(
                    1,
                    Integer.parseInt(
                            idField.getText().trim()
                    )
            );

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Customer Deleted Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            con.close();

            loadCustomers();
            clearFields();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // SEARCH CUSTOMER
    // =========================

    void searchCustomer() {

        model.setRowCount(0);

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            String sql =
                    "SELECT * FROM customers " +
                    "WHERE name LIKE ? " +
                    "OR phone LIKE ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            String search =
                    "%" +
                    searchField.getText().trim() +
                    "%";

            pst.setString(1, search);
            pst.setString(2, search);

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("customer_id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("email"),
                                rs.getString("address")
                        }
                );
            }

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // CLEAR
    // =========================

    void clearFields() {

        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        searchField.setText("");

        maleRadio.setSelected(true);

        idProofCheck.setSelected(false);
        termsCheck.setSelected(false);

        table.clearSelection();
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new CustomerManagement().setVisible(true);
        });
    }
}