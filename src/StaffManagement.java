import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class StaffManagement extends JFrame {

    JTextField staffIdField, nameField, phoneField, emailField;
    JTextField salaryField;

    JComboBox<String> roleBox, shiftBox;

    JRadioButton maleRadio, femaleRadio, otherRadio;
    JRadioButton aadharRadio, drivingRadio, panRadio;

    JCheckBox idProofCheck;

    JTable staffTable;
    DefaultTableModel tableModel;

    // =========================
    // COLORS
    // =========================

    private final Color BACKGROUND =
            new Color(18, 32, 52);

    private final Color PANEL_COLOR =
            new Color(25, 45, 68);

    private final Color FIELD_COLOR =
            new Color(35, 58, 82);

    private final Color TABLE_COLOR =
            new Color(22, 40, 60);

    private final Color ACCENT =
            new Color(80, 210, 180);

    private final Color BLUE =
            new Color(70, 145, 235);

    private final Color GREEN =
            new Color(65, 190, 120);

    private final Color RED =
            new Color(225, 75, 85);

    private final Color ORANGE =
            new Color(235, 155, 65);

    private final Color TEXT_COLOR =
            new Color(235, 245, 250);

    private final Color SECONDARY_TEXT =
            new Color(175, 195, 210);

    private final Color BORDER_COLOR =
            new Color(55, 80, 105);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public StaffManagement() {

        setTitle("Staff Management - Grand Horizon Hotel");

        setSize(1100, 700);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                BACKGROUND
        );

        setLayout(
                new BorderLayout()
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setBackground(
                BACKGROUND
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        8,
                        20
                )
        );

        JLabel title =
                new JLabel(
                        "STAFF MANAGEMENT"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                ACCENT
        );

        JLabel subtitle =
                new JLabel(
                        "Grand Horizon Hotel • Manage Hotel Staff"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        headerPanel.add(title);

        headerPanel.add(
                Box.createVerticalStrut(3)
        );

        headerPanel.add(subtitle);

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // CONTENT
        // =================================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        0,
                        15,
                        15,
                        15
                )
        );

        // =================================================
        // STAFF FORM
        // =================================================

        JPanel staffPanel =
                new JPanel(
                        new GridBagLayout()
                );

        staffPanel.setBackground(
                PANEL_COLOR
        );

        staffPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        4,
                        7,
                        4,
                        7
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        Dimension fieldSize =
                new Dimension(
                        180,
                        30
                );

        // =================================================
        // STAFF ID
        // =================================================

        addFormRow(
                staffPanel,
                gbc,
                0,
                "Staff ID",
                staffIdField =
                        createTextField(fieldSize)
        );

        // =================================================
        // NAME
        // =================================================

        addFormRow(
                staffPanel,
                gbc,
                1,
                "Name",
                nameField =
                        createTextField(fieldSize)
        );

        // =================================================
        // GENDER
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 2;

        staffPanel.add(
                createLabel("Gender"),
                gbc
        );

        JPanel genderPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                4,
                                0
                        )
                );

        genderPanel.setBackground(
                PANEL_COLOR
        );

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

        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);

        gbc.gridx = 1;

        staffPanel.add(
                genderPanel,
                gbc
        );

        // =================================================
        // PHONE
        // =================================================

        addFormRow(
                staffPanel,
                gbc,
                3,
                "Phone",
                phoneField =
                        createTextField(fieldSize)
        );

        // =================================================
        // EMAIL
        // =================================================

        addFormRow(
                staffPanel,
                gbc,
                4,
                "Email",
                emailField =
                        createTextField(fieldSize)
        );

        // =================================================
        // ROLE
        // =================================================

        roleBox =
                new JComboBox<>(
                        new String[]{
                                "Manager",
                                "Receptionist",
                                "Housekeeping",
                                "Spa Therapist",
                                "Security Guard"
                        }
                );

        styleComboBox(
                roleBox,
                fieldSize
        );

        addFormRow(
                staffPanel,
                gbc,
                5,
                "Role",
                roleBox
        );

        // =================================================
        // SHIFT
        // =================================================

        shiftBox =
                new JComboBox<>(
                        new String[]{
                                "Morning Shift (6 AM - 6 PM)",
                                "Night Shift (6 PM - 6 AM)"
                        }
                );

        styleComboBox(
                shiftBox,
                fieldSize
        );

        addFormRow(
                staffPanel,
                gbc,
                6,
                "Shift",
                shiftBox
        );

        // =================================================
        // SALARY
        // =================================================

        addFormRow(
                staffPanel,
                gbc,
                7,
                "Salary",
                salaryField =
                        createTextField(fieldSize)
        );

        // =================================================
        // ID PROOF CHECKBOX
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 8;

        staffPanel.add(
                createLabel("Verification"),
                gbc
        );

        idProofCheck =
                new JCheckBox(
                        "ID Proof Submitted *"
                );

        styleCheckBox(
                idProofCheck
        );

        gbc.gridx = 1;

        staffPanel.add(
                idProofCheck,
                gbc
        );

        // =================================================
        // ID PROOF TYPE
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 9;

        staffPanel.add(
                createLabel("ID Proof Type *"),
                gbc
        );

        JPanel idPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                4,
                                0
                        )
                );

        idPanel.setBackground(
                PANEL_COLOR
        );

        aadharRadio =
                createRadioButton(
                        "Aadhar Card"
                );

        drivingRadio =
                createRadioButton(
                        "Driving License"
                );

        panRadio =
                createRadioButton(
                        "PAN Card"
                );

        ButtonGroup idGroup =
                new ButtonGroup();

        idGroup.add(aadharRadio);
        idGroup.add(drivingRadio);
        idGroup.add(panRadio);

        idPanel.add(aadharRadio);
        idPanel.add(drivingRadio);
        idPanel.add(panRadio);

        gbc.gridx = 1;

        staffPanel.add(
                idPanel,
                gbc
        );

        // =================================================
        // BUTTONS
        // =================================================

        JButton addButton =
                createButton(
                        "Add",
                        GREEN
                );

        JButton updateButton =
                createButton(
                        "Update",
                        BLUE
                );

        JButton deleteButton =
                createButton(
                        "Delete",
                        RED
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        ORANGE
                );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                3
                        )
                );

        buttonPanel.setBackground(
                PANEL_COLOR
        );

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 10;

        gbc.gridwidth = 2;

        gbc.anchor =
                GridBagConstraints.CENTER;

        staffPanel.add(
                buttonPanel,
                gbc
        );

        // =================================================
        // FORM WRAPPER
        // =================================================

        JPanel formWrapper =
                new JPanel(
                        new BorderLayout()
                );

        formWrapper.setBackground(
                BACKGROUND
        );

        formWrapper.add(
                staffPanel,
                BorderLayout.CENTER
        );

        contentPanel.add(
                formWrapper,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {
                "Staff ID",
                "Name",
                "Gender",
                "Role",
                "Phone",
                "Shift",
                "Salary"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        staffTable =
                new JTable(
                        tableModel
                );

        staffTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        staffTable.setRowHeight(30);

        staffTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        staffTable.setForeground(
                TEXT_COLOR
        );

        staffTable.setBackground(
                TABLE_COLOR
        );

        staffTable.setGridColor(
                new Color(
                        50,
                        70,
                        90
                )
        );

        staffTable.setSelectionBackground(
                new Color(
                        55,
                        95,
                        120
                )
        );

        staffTable.setSelectionForeground(
                Color.WHITE
        );

        // =================================================
        // TABLE HEADER
        // =================================================

        JTableHeader header =
                staffTable.getTableHeader();

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        header.setBackground(
                new Color(
                        35,
                        60,
                        85
                )
        );

        header.setForeground(
                ACCENT
        );

        header.setPreferredSize(
                new Dimension(
                        100,
                        35
                )
        );

        // =================================================
        // COLUMN WIDTHS
        // =================================================

        staffTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        staffTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(180);

        staffTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(100);

        staffTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(160);

        staffTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(130);

        staffTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(190);

        staffTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(110);

        JScrollPane tableScroll =
                new JScrollPane(
                        staffTable
                );

        tableScroll.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );

        tableScroll.getViewport()
                .setBackground(
                        TABLE_COLOR
                );

        // =================================================
        // TABLE PANEL
        // =================================================

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                5
                        )
                );

        tablePanel.setBackground(
                PANEL_COLOR
        );

        tablePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                8,
                                10,
                                10,
                                10
                        )
                )
        );

        JLabel tableTitle =
                new JLabel(
                        "STAFF DETAILS"
                );

        tableTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        tableTitle.setForeground(
                ACCENT
        );

        tablePanel.add(
                tableTitle,
                BorderLayout.NORTH
        );

        tablePanel.add(
                tableScroll,
                BorderLayout.CENTER
        );

        contentPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // LOAD DATABASE DATA
        // =================================================

        loadStaffData();

        // =================================================
        // ADD
        // =================================================

        addButton.addActionListener(
                e -> addStaff()
        );

        // =================================================
        // UPDATE
        // =================================================

        updateButton.addActionListener(
                e -> updateStaff()
        );

        // =================================================
        // DELETE
        // =================================================

        deleteButton.addActionListener(
                e -> deleteStaff()
        );

        // =================================================
        // TABLE CLICK
        // =================================================

        staffTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (e.getValueIsAdjusting()) {
                                return;
                            }

                            int row =
                                    staffTable.getSelectedRow();

                            if (row == -1) {
                                return;
                            }

                            staffIdField.setText(
                                    getTableValue(row, 0)
                            );

                            nameField.setText(
                                    getTableValue(row, 1)
                            );

                            phoneField.setText(
                                    getTableValue(row, 4)
                            );

                            salaryField.setText(
                                    getTableValue(row, 6)
                            );

                            String gender =
                                    getTableValue(row, 2);

                            if (gender.equals("Male")) {

                                maleRadio.setSelected(true);

                            } else if (gender.equals("Female")) {

                                femaleRadio.setSelected(true);

                            } else if (gender.equals("Other")) {

                                otherRadio.setSelected(true);
                            }

                            roleBox.setSelectedItem(
                                    getTableValue(row, 3)
                            );

                            shiftBox.setSelectedItem(
                                    getTableValue(row, 5)
                            );

                            // Load hidden database details
                            loadExtraStaffDetails(
                                    getTableValue(row, 0)
                            );
                        }
                );

        // =================================================
        // CLEAR
        // =================================================

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =====================================================
    // ADD STAFF
    // =====================================================

    private void addStaff() {

        if (!validateStaff()) {
            return;
        }

        String staffId =
                staffIdField.getText().trim();

        String name =
                nameField.getText().trim();

        String gender =
                getGender();

        String role =
                roleBox.getSelectedItem().toString();

        String phone =
                phoneField.getText().trim();

        String email =
                emailField.getText().trim();

        String shift =
                shiftBox.getSelectedItem().toString();

        String salary =
                salaryField.getText().trim();

        String idProofType =
                getIdProofType();

        String sql =
                "INSERT INTO staff " +
                "(staff_id, name, gender, phone, email, role, shift, salary, " +
                "id_proof_submitted, id_proof_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, staffId);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, role);
            ps.setString(7, shift);

            setSalary(ps, 8, salary);

            ps.setBoolean(
                    9,
                    idProofCheck.isSelected()
            );

            ps.setString(
                    10,
                    idProofType
            );

            ps.executeUpdate();

            tableModel.addRow(
                    new Object[]{
                            staffId,
                            name,
                            gender,
                            role,
                            phone,
                            shift,
                            salary
                    }
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Staff added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (SQLIntegrityConstraintViolationException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff ID already exists.",
                    "Duplicate Staff ID",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n" +
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE STAFF
    // =====================================================

    private void updateStaff() {

        int row =
                staffTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a staff member from the table.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validateStaff()) {
            return;
        }

        // Original Staff ID from database
        String oldStaffId =
                getTableValue(row, 0);

        String newStaffId =
                staffIdField.getText().trim();

        String name =
                nameField.getText().trim();

        String gender =
                getGender();

        String role =
                roleBox.getSelectedItem().toString();

        String phone =
                phoneField.getText().trim();

        String email =
                emailField.getText().trim();

        String shift =
                shiftBox.getSelectedItem().toString();

        String salary =
                salaryField.getText().trim();

        String idProofType =
                getIdProofType();

        String sql =
                "UPDATE staff SET " +
                "staff_id=?, name=?, gender=?, phone=?, email=?, role=?, " +
                "shift=?, salary=?, id_proof_submitted=?, id_proof_type=? " +
                "WHERE staff_id=?";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, newStaffId);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, role);
            ps.setString(7, shift);

            setSalary(ps, 8, salary);

            ps.setBoolean(
                    9,
                    idProofCheck.isSelected()
            );

            ps.setString(
                    10,
                    idProofType
            );

            ps.setString(
                    11,
                    oldStaffId
            );

            int result =
                    ps.executeUpdate();

            if (result == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Staff record was not found in database.",
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            tableModel.setValueAt(
                    newStaffId,
                    row,
                    0
            );

            tableModel.setValueAt(
                    name,
                    row,
                    1
            );

            tableModel.setValueAt(
                    gender,
                    row,
                    2
            );

            tableModel.setValueAt(
                    role,
                    row,
                    3
            );

            tableModel.setValueAt(
                    phone,
                    row,
                    4
            );

            tableModel.setValueAt(
                    shift,
                    row,
                    5
            );

            tableModel.setValueAt(
                    salary,
                    row,
                    6
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Staff details updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (SQLIntegrityConstraintViolationException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "The new Staff ID already exists.",
                    "Duplicate Staff ID",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n" +
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE STAFF
    // =====================================================

    private void deleteStaff() {

        int row =
                staffTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a staff member from the table.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this staff member?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm !=
                JOptionPane.YES_OPTION
        ) {
            return;
        }

        String staffId =
                getTableValue(row, 0);

        String sql =
                "DELETE FROM staff WHERE staff_id=?";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    staffId
            );

            int result =
                    ps.executeUpdate();

            if (result == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Staff record was not found in database.",
                        "Delete Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            tableModel.removeRow(row);

            JOptionPane.showMessageDialog(
                    this,
                    "Staff deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n" +
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD STAFF DATA
    // =====================================================

    private void loadStaffData() {

        String sql =
                "SELECT staff_id, name, gender, role, phone, shift, salary " +
                "FROM staff ORDER BY staff_id";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                String salary =
                        rs.getString("salary");

                if (salary == null) {
                    salary = "";
                }

                tableModel.addRow(
                        new Object[]{
                                rs.getString("staff_id"),
                                rs.getString("name"),
                                rs.getString("gender"),
                                rs.getString("role"),
                                rs.getString("phone"),
                                rs.getString("shift"),
                                salary
                        }
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load staff data.\n\n" +
                    "Database error:\n" +
                    ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD EMAIL + ID PROOF DETAILS
    // =====================================================

    private void loadExtraStaffDetails(
            String staffId) {

        String sql =
                "SELECT email, id_proof_submitted, id_proof_type " +
                "FROM staff WHERE staff_id=?";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    staffId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    String email =
                            rs.getString("email");

                    if (email == null) {
                        email = "";
                    }

                    emailField.setText(email);

                    idProofCheck.setSelected(
                            rs.getBoolean(
                                    "id_proof_submitted"
                            )
                    );

                    String proofType =
                            rs.getString(
                                    "id_proof_type"
                            );

                    aadharRadio.setSelected(false);
                    drivingRadio.setSelected(false);
                    panRadio.setSelected(false);

                    if (
                            "Aadhar Card".equals(
                                    proofType
                            )
                    ) {

                        aadharRadio.setSelected(true);

                    } else if (
                            "Driving License".equals(
                                    proofType
                            )
                    ) {

                        drivingRadio.setSelected(true);

                    } else if (
                            "PAN Card".equals(
                                    proofType
                            )
                    ) {

                        panRadio.setSelected(true);
                    }
                }
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load staff verification details.\n" +
                    ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // VALIDATE STAFF
    // =====================================================

    private boolean validateStaff() {

        if (
                staffIdField.getText()
                        .trim()
                        .isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Staff ID.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (
                nameField.getText()
                        .trim()
                        .isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Staff Name.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        // ID proof checkbox compulsory
        if (
                !idProofCheck.isSelected()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select 'ID Proof Submitted'.",
                    "Verification Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        // ID proof type compulsory
        if (
                !isIdProofTypeSelected()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an ID Proof Type.",
                    "Verification Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        // Validate salary if entered
        String salary =
                salaryField.getText().trim();

        if (!salary.isEmpty()) {

            try {

                Double.parseDouble(salary);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid salary.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }
        }

        return true;
    }

    // =====================================================
    // SET SALARY
    // =====================================================

    private void setSalary(
            PreparedStatement ps,
            int index,
            String salary)
            throws SQLException {

        if (
                salary == null ||
                salary.trim().isEmpty()
        ) {

            ps.setNull(
                    index,
                    Types.DECIMAL
            );

        } else {

            ps.setBigDecimal(
                    index,
                    new java.math.BigDecimal(
                            salary.trim()
                    )
            );
        }
    }

    // =====================================================
    // GET TABLE VALUE
    // =====================================================

    private String getTableValue(
            int row,
            int column) {

        Object value =
                tableModel.getValueAt(
                        row,
                        column
                );

        return value == null
                ? ""
                : value.toString();
    }

    // =====================================================
    // GET GENDER
    // =====================================================

    private String getGender() {

        if (
                maleRadio.isSelected()
        ) {
            return "Male";
        }

        if (
                femaleRadio.isSelected()
        ) {
            return "Female";
        }

        if (
                otherRadio.isSelected()
        ) {
            return "Other";
        }

        return "";
    }

    // =====================================================
    // CHECK ID PROOF TYPE
    // =====================================================

    private boolean isIdProofTypeSelected() {

        return aadharRadio.isSelected()
                || drivingRadio.isSelected()
                || panRadio.isSelected();
    }

    // =====================================================
    // GET ID PROOF TYPE
    // =====================================================

    private String getIdProofType() {

        if (
                aadharRadio.isSelected()
        ) {
            return "Aadhar Card";
        }

        if (
                drivingRadio.isSelected()
        ) {
            return "Driving License";
        }

        if (
                panRadio.isSelected()
        ) {
            return "PAN Card";
        }

        return "";
    }

    // =====================================================
    // LABEL
    // =====================================================

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        return label;
    }

    // =====================================================
    // FORM ROW
    // =====================================================

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            Component component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill =
                GridBagConstraints.NONE;

        panel.add(
                createLabel(labelText),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        panel.add(
                component,
                gbc
        );
    }

    // =====================================================
    // TEXT FIELD
    // =====================================================

    private JTextField createTextField(
            Dimension size) {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setForeground(
                TEXT_COLOR
        );

        field.setBackground(
                FIELD_COLOR
        );

        field.setCaretColor(
                TEXT_COLOR
        );

        field.setPreferredSize(
                size
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        BorderFactory.createEmptyBorder(
                                4,
                                7,
                                4,
                                7
                        )
                )
        );

        return field;
    }

    // =====================================================
    // COMBO BOX
    // =====================================================

    private void styleComboBox(
            JComboBox<String> box,
            Dimension size) {

        box.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        box.setForeground(
                TEXT_COLOR
        );

        box.setBackground(
                FIELD_COLOR
        );

        box.setPreferredSize(
                size
        );

        box.setFocusable(
                false
        );
    }

    // =====================================================
    // RADIO BUTTON
    // =====================================================

    private JRadioButton createRadioButton(
            String text) {

        JRadioButton radio =
                new JRadioButton(
                        text
                );

        radio.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        radio.setForeground(
                TEXT_COLOR
        );

        radio.setBackground(
                PANEL_COLOR
        );

        radio.setFocusPainted(
                false
        );

        return radio;
    }

    // =====================================================
    // CHECKBOX
    // =====================================================

    private void styleCheckBox(
            JCheckBox checkBox) {

        checkBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        checkBox.setForeground(
                TEXT_COLOR
        );

        checkBox.setBackground(
                PANEL_COLOR
        );

        checkBox.setFocusPainted(
                false
        );
    }

    // =====================================================
    // BUTTON
    // =====================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setPreferredSize(
                new Dimension(
                        100,
                        32
                )
        );

        return button;
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        staffIdField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        salaryField.setText("");

        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        otherRadio.setSelected(false);

        aadharRadio.setSelected(false);
        drivingRadio.setSelected(false);
        panRadio.setSelected(false);

        idProofCheck.setSelected(false);

        roleBox.setSelectedIndex(0);
        shiftBox.setSelectedIndex(0);

        staffTable.clearSelection();
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    new StaffManagement()
                            .setVisible(true);
                }
        );
    }
}