import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class BookingManagement extends JFrame {

    // ================= COLORS =================
    private static final Color BACKGROUND = new Color(18, 32, 52);
    private static final Color PANEL_COLOR = new Color(25, 45, 68);
    private static final Color FIELD_COLOR = new Color(35, 58, 82);
    private static final Color TABLE_COLOR = new Color(22, 40, 60);
    private static final Color ACCENT = new Color(80, 210, 180);
    private static final Color TEXT_COLOR = new Color(235, 245, 250);
    private static final Color SECONDARY_TEXT = new Color(175, 195, 210);

    // ================= COMPONENTS =================
    private JComboBox<String> customerBox;
    private JComboBox<String> roomBox;
    private JComboBox<String> paymentStatusBox;

    private JTextField checkInField;
    private JTextField checkOutField;

    private JSpinner guestsSpinner;

    private JRadioButton onlineRadio;
    private JRadioButton walkInRadio;

    private JTextArea notesArea;

    private JTable table;
    private DefaultTableModel model;

    private JButton bookButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton checkoutButton;

    // ================= CONSTRUCTOR =================
    public BookingManagement() {

        setTitle("Booking Management");
        setSize(1150, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(BACKGROUND);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 10));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // ================= HEADER =================
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(BACKGROUND);

        JLabel titleLabel = new JLabel("BOOKING MANAGEMENT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        titleLabel.setForeground(ACCENT);

        JLabel subtitleLabel = new JLabel(
                "Grand Horizon Hotel • Manage Room Reservations"
        );
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(SECONDARY_TEXT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(3));
        headerPanel.add(subtitleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ================= BOOKING PANEL =================
        JPanel bookingPanel = new JPanel(new BorderLayout(10, 10));
        bookingPanel.setBackground(BACKGROUND);

        // ================= FORM PANEL =================
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_COLOR);

        formPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(55, 80, 105), 1, true),
                new EmptyBorder(10, 15, 10, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 7, 4, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        customerBox = new JComboBox<>();
        roomBox = new JComboBox<>();

        paymentStatusBox = new JComboBox<>(
                new String[]{
                        "Pending",
                        "Paid",
                        "Partially Paid"
                }
        );

        checkInField = new JTextField();
        checkOutField = new JTextField();

        guestsSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 20, 1)
        );

        onlineRadio = new JRadioButton("Online");
        walkInRadio = new JRadioButton("Walk-In");

        onlineRadio.setSelected(true);

        onlineRadio.setBackground(PANEL_COLOR);
        walkInRadio.setBackground(PANEL_COLOR);

        onlineRadio.setForeground(TEXT_COLOR);
        walkInRadio.setForeground(TEXT_COLOR);

        onlineRadio.setFocusPainted(false);
        walkInRadio.setFocusPainted(false);

        ButtonGroup bookingTypeGroup = new ButtonGroup();
        bookingTypeGroup.add(onlineRadio);
        bookingTypeGroup.add(walkInRadio);

        notesArea = new JTextArea(2, 15);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);

        // ================= SMALLER FIELDS =================
        styleComboBox(customerBox);
        styleComboBox(roomBox);
        styleComboBox(paymentStatusBox);

        styleTextField(checkInField);
        styleTextField(checkOutField);

        Dimension fieldSize = new Dimension(180, 32);

        customerBox.setPreferredSize(fieldSize);
        roomBox.setPreferredSize(fieldSize);
        paymentStatusBox.setPreferredSize(fieldSize);

        checkInField.setPreferredSize(fieldSize);
        checkOutField.setPreferredSize(fieldSize);

        guestsSpinner.setPreferredSize(
                new Dimension(120, 32)
        );

        guestsSpinner.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        guestsSpinner.setBackground(FIELD_COLOR);
        guestsSpinner.setForeground(TEXT_COLOR);

        JComponent spinnerEditor =
                guestsSpinner.getEditor();

        if (spinnerEditor instanceof JSpinner.DefaultEditor) {

            JTextField spinnerField =
                    ((JSpinner.DefaultEditor) spinnerEditor)
                            .getTextField();

            spinnerField.setBackground(FIELD_COLOR);
            spinnerField.setForeground(TEXT_COLOR);
            spinnerField.setCaretColor(TEXT_COLOR);

            spinnerField.setBorder(
                    new EmptyBorder(4, 7, 4, 7)
            );
        }

        notesArea.setBackground(FIELD_COLOR);
        notesArea.setForeground(TEXT_COLOR);
        notesArea.setCaretColor(TEXT_COLOR);

        notesArea.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        notesArea.setBorder(
                new EmptyBorder(5, 7, 5, 7)
        );

        JScrollPane notesScroll =
                new JScrollPane(notesArea);

        notesScroll.setPreferredSize(
                new Dimension(260, 55)
        );

        int row = 0;

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Customer",
                customerBox
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Room",
                roomBox
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Check-In",
                checkInField
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Check-Out",
                checkOutField
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Number of Guests",
                guestsSpinner
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Payment Status",
                paymentStatusBox
        );

        // ================= BOOKING TYPE =================
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;

        formPanel.add(
                createLabel("Booking Type"),
                gbc
        );

        JPanel bookingTypePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                0
                        )
                );

        bookingTypePanel.setBackground(PANEL_COLOR);

        bookingTypePanel.add(onlineRadio);
        bookingTypePanel.add(walkInRadio);

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                bookingTypePanel,
                gbc
        );

        row++;

        // ================= NOTES =================
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        formPanel.add(
                createLabel("Notes"),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                notesScroll,
                gbc
        );

        bookingPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        // ================= TABLE =================
        String[] columns = {
                "Booking ID",
                "Customer",
                "Room",
                "Check-In",
                "Check-Out",
                "Guests",
                "Payment"
        };

        model = new DefaultTableModel(
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

        table = new JTable(model);

        table.setBackground(TABLE_COLOR);
        table.setForeground(TEXT_COLOR);

        table.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        table.setRowHeight(31);

        table.setSelectionBackground(
                new Color(45, 100, 125)
        );

        table.setSelectionForeground(Color.WHITE);

        table.setGridColor(
                new Color(50, 70, 90)
        );

        table.setShowGrid(true);

        table.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        table.getTableHeader().setBackground(
                new Color(30, 65, 90)
        );

        table.getTableHeader().setForeground(
                TEXT_COLOR
        );

        table.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        table.getTableHeader().setPreferredSize(
                new Dimension(0, 35)
        );

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(220);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(90);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(125);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(125);

        table.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(80);

        table.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(130);

        JScrollPane tableScrollPane =
                new JScrollPane(table);

        tableScrollPane.setBorder(
                new LineBorder(
                        new Color(55, 80, 105),
                        1,
                        true
                )
        );

        tableScrollPane.getViewport()
                .setBackground(TABLE_COLOR);

        bookingPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        // ================= BUTTONS =================
        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                5
                        )
                );

        buttonPanel.setBackground(BACKGROUND);

        bookButton = createButton(
                "Book Room",
                new Color(35, 140, 100)
        );

        updateButton = createButton(
                "Update",
                new Color(45, 105, 170)
        );

        deleteButton = createButton(
                "Delete",
                new Color(180, 65, 70)
        );

        clearButton = createButton(
                "Clear",
                new Color(110, 80, 165)
        );

        checkoutButton = createButton(
                "Check Out",
                new Color(205, 115, 45)
        );

        buttonPanel.add(bookButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(checkoutButton);

        bookingPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                bookingPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // ================= LOAD DATA =================
        loadCustomers();
        loadRooms();
        loadBookings();

        // ================= BUTTON ACTIONS =================
        bookButton.addActionListener(
                e -> bookRoom()
        );

        updateButton.addActionListener(
                e -> updateBooking()
        );

        deleteButton.addActionListener(
                e -> deleteBooking()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        checkoutButton.addActionListener(
                e -> checkOut()
        );

        // ================= TABLE SELECTION =================
        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    int selectedRow =
                            table.getSelectedRow();

                    if (selectedRow == -1) {
                        return;
                    }

                    String customerName =
                            table.getValueAt(
                                    selectedRow,
                                    1
                            ).toString();

                    String roomNumber =
                            table.getValueAt(
                                    selectedRow,
                                    2
                            ).toString();

                    String checkIn =
                            table.getValueAt(
                                    selectedRow,
                                    3
                            ).toString();

                    String checkOut =
                            table.getValueAt(
                                    selectedRow,
                                    4
                            ).toString();

                    String guests =
                            table.getValueAt(
                                    selectedRow,
                                    5
                            ).toString();

                    String payment =
                            table.getValueAt(
                                    selectedRow,
                                    6
                            ).toString();

                    selectCustomerByName(
                            customerName
                    );

                    selectRoom(roomNumber);

                    checkInField.setText(checkIn);
                    checkOutField.setText(checkOut);

                    try {

                        guestsSpinner.setValue(
                                Integer.parseInt(guests)
                        );

                    } catch (Exception ex) {

                        guestsSpinner.setValue(1);
                    }

                    paymentStatusBox.setSelectedItem(
                            payment
                    );
                });
    }

    // ================= LABEL =================
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(TEXT_COLOR);

        return label;
    }

    // ================= FORM ROW =================
    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            Component component
    ) {

        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(
                createLabel(labelText),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                component,
                gbc
        );
    }

    // ================= TEXT FIELD STYLE =================
    private void styleTextField(
            JTextField field
    ) {

        field.setBackground(FIELD_COLOR);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(TEXT_COLOR);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        65,
                                        90,
                                        115
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );
    }

    // ================= COMBO STYLE =================
    private void styleComboBox(
            JComboBox<String> combo
    ) {

        combo.setBackground(FIELD_COLOR);
        combo.setForeground(TEXT_COLOR);

        combo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        combo.setBorder(
                new LineBorder(
                        new Color(
                                65,
                                90,
                                115
                        ),
                        1,
                        true
                )
        );
    }

    // ================= BUTTON =================
    private JButton createButton(
            String text,
            Color color
    ) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        125,
                        38
                )
        );

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(
                                        55,
                                        120,
                                        150
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(color);
                    }
                }
        );

        return button;
    }

    // ================= LOAD CUSTOMERS =================
    private void loadCustomers() {

        customerBox.removeAllItems();

        String sql =
                "SELECT customer_id, name " +
                "FROM customers " +
                "ORDER BY name";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                int id =
                        rs.getInt("customer_id");

                String name =
                        rs.getString("name");

                customerBox.addItem(
                        id + " - " + name
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading customers:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= LOAD ROOMS =================
    private void loadRooms() {

        roomBox.removeAllItems();

        String sql =
                "SELECT room_number " +
                "FROM rooms " +
                "WHERE status = 'Available' " +
                "ORDER BY room_number";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                roomBox.addItem(
                        rs.getString(
                                "room_number"
                        )
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading rooms:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= LOAD BOOKINGS =================
    private void loadBookings() {

        model.setRowCount(0);

        String sql =
                "SELECT b.booking_id, " +
                "c.name, " +
                "r.room_number, " +
                "b.check_in, " +
                "b.check_out, " +
                "b.guests, " +
                "b.payment_status " +
                "FROM bookings b " +
                "JOIN customers c " +
                "ON b.customer_id = c.customer_id " +
                "JOIN rooms r " +
                "ON b.room_id = r.room_id " +
                "ORDER BY b.booking_id";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt(
                                        "booking_id"
                                ),

                                rs.getString(
                                        "name"
                                ),

                                rs.getString(
                                        "room_number"
                                ),

                                rs.getString(
                                        "check_in"
                                ),

                                rs.getString(
                                        "check_out"
                                ),

                                rs.getInt(
                                        "guests"
                                ),

                                rs.getString(
                                        "payment_status"
                                )
                        }
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading bookings:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= BOOK ROOM =================
    private void bookRoom() {

        if (
                customerBox.getSelectedItem() == null ||
                roomBox.getSelectedItem() == null ||
                checkInField.getText()
                        .trim()
                        .isEmpty() ||
                checkOutField.getText()
                        .trim()
                        .isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required booking details.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String customerValue =
                customerBox.getSelectedItem()
                        .toString();

        int customerId;

        try {

            customerId =
                    Integer.parseInt(
                            customerValue
                                    .split(" - ")[0]
                    );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid customer selection.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String roomNumber =
                roomBox.getSelectedItem()
                        .toString();

        String checkIn =
                checkInField.getText()
                        .trim();

        String checkOut =
                checkOutField.getText()
                        .trim();

        int guests =
                (Integer) guestsSpinner
                        .getValue();

        String payment =
                paymentStatusBox
                        .getSelectedItem()
                        .toString();

        Connection con = null;

        try {

            con =
                    DatabaseConnection
                            .getConnection();

            con.setAutoCommit(false);

            String insertSql =
                    "INSERT INTO bookings " +
                    "(customer_id, room_id, check_in, check_out, guests, payment_status) " +
                    "SELECT ?, room_id, ?, ?, ?, ? " +
                    "FROM rooms " +
                    "WHERE room_number = ? " +
                    "AND status = 'Available'";

            PreparedStatement ps =
                    con.prepareStatement(
                            insertSql
                    );

            ps.setInt(
                    1,
                    customerId
            );

            ps.setString(
                    2,
                    checkIn
            );

            ps.setString(
                    3,
                    checkOut
            );

            ps.setInt(
                    4,
                    guests
            );

            ps.setString(
                    5,
                    payment
            );

            ps.setString(
                    6,
                    roomNumber
            );

            int inserted =
                    ps.executeUpdate();

            if (inserted == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room is not available.",
                        "Booking Failed",
                        JOptionPane.WARNING_MESSAGE
                );

                con.rollback();

                ps.close();

                return;
            }

            ps.close();

            String updateRoomSql =
                    "UPDATE rooms SET status = 'Booked' " +
                    "WHERE room_number = ?";

            ps =
                    con.prepareStatement(
                            updateRoomSql
                    );

            ps.setString(
                    1,
                    roomNumber
            );

            ps.executeUpdate();

            ps.close();

            con.commit();

            JOptionPane.showMessageDialog(
                    this,
                    "Room booked successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadBookings();
            loadRooms();
            clearFields();

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (SQLException ignored) {
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Booking failed:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            try {

                if (con != null) {

                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (SQLException ignored) {
            }
        }
    }

    // ================= UPDATE BOOKING =================
    private void updateBooking() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a booking to update.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int bookingId =
                Integer.parseInt(
                        table.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );

        String checkIn =
                checkInField.getText()
                        .trim();

        String checkOut =
                checkOutField.getText()
                        .trim();

        int guests =
                (Integer) guestsSpinner
                        .getValue();

        String payment =
                paymentStatusBox
                        .getSelectedItem()
                        .toString();

        if (
                checkIn.isEmpty() ||
                checkOut.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Check-In and Check-Out dates.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "UPDATE bookings SET " +
                "check_in=?, " +
                "check_out=?, " +
                "guests=?, " +
                "payment_status=? " +
                "WHERE booking_id=?";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    checkIn
            );

            ps.setString(
                    2,
                    checkOut
            );

            ps.setInt(
                    3,
                    guests
            );

            ps.setString(
                    4,
                    payment
            );

            ps.setInt(
                    5,
                    bookingId
            );

            int updated =
                    ps.executeUpdate();

            if (updated > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadBookings();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking could not be updated.",
                        "Update Failed",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Update failed:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= DELETE BOOKING =================
    private void deleteBooking() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a booking to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int bookingId =
                Integer.parseInt(
                        table.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );

        String roomNumber =
                table.getValueAt(
                        selectedRow,
                        2
                ).toString();

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this booking?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm !=
                        JOptionPane.YES_OPTION
        ) {
            return;
        }

        Connection con = null;

        try {

            con =
                    DatabaseConnection
                            .getConnection();

            con.setAutoCommit(false);

            String deleteSql =
                    "DELETE FROM bookings " +
                    "WHERE booking_id=?";

            PreparedStatement ps =
                    con.prepareStatement(
                            deleteSql
                    );

            ps.setInt(
                    1,
                    bookingId
            );

            ps.executeUpdate();

            ps.close();

            String roomSql =
                    "UPDATE rooms SET status='Available' " +
                    "WHERE room_number=?";

            ps =
                    con.prepareStatement(
                            roomSql
                    );

            ps.setString(
                    1,
                    roomNumber
            );

            ps.executeUpdate();

            ps.close();

            con.commit();

            JOptionPane.showMessageDialog(
                    this,
                    "Booking deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadBookings();
            loadRooms();
            clearFields();

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (SQLException ignored) {
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Delete failed:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            try {

                if (con != null) {

                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (SQLException ignored) {
            }
        }
    }

    // ================= CHECK OUT =================
    private void checkOut() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a booking to check out.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int bookingId =
                Integer.parseInt(
                        table.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );

        String customerName =
                table.getValueAt(
                        selectedRow,
                        1
                ).toString();

        String roomNumber =
                table.getValueAt(
                        selectedRow,
                        2
                ).toString();

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Check out " +
                                customerName +
                                " from Room " +
                                roomNumber +
                                "?",
                        "Confirm Check Out",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm !=
                        JOptionPane.YES_OPTION
        ) {
            return;
        }

        Connection con = null;

        try {

            con =
                    DatabaseConnection
                            .getConnection();

            con.setAutoCommit(false);

            // =================================================
            // STEP 1: GET ROOM ID
            // =================================================

            int roomId = -1;

            String getRoomIdSql =
                    "SELECT room_id " +
                    "FROM rooms " +
                    "WHERE room_number=?";

            PreparedStatement ps =
                    con.prepareStatement(
                            getRoomIdSql
                    );

            ps.setString(
                    1,
                    roomNumber
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                roomId =
                        rs.getInt("room_id");
            }

            rs.close();
            ps.close();

            if (roomId == -1) {

                con.rollback();

                JOptionPane.showMessageDialog(
                        this,
                        "Room ID could not be found.",
                        "Check Out Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // =================================================
            // STEP 2: UPDATE ROOM STATUS
            // =================================================

            String roomSql =
                    "UPDATE rooms SET status='Available' " +
                    "WHERE room_number=?";

            ps =
                    con.prepareStatement(
                            roomSql
                    );

            ps.setString(
                    1,
                    roomNumber
            );

            int roomUpdated =
                    ps.executeUpdate();

            ps.close();

            if (roomUpdated == 0) {

                con.rollback();

                JOptionPane.showMessageDialog(
                        this,
                        "Room status could not be updated.",
                        "Check Out Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // =================================================
            // STEP 3: DELETE HOTEL SERVICES
            // =================================================

            String serviceSql =
                    "DELETE FROM hotel_services " +
                    "WHERE room_id=?";

            ps =
                    con.prepareStatement(
                            serviceSql
                    );

            ps.setInt(
                    1,
                    roomId
            );

            ps.executeUpdate();

            ps.close();

            // =================================================
            // STEP 4: DELETE BOOKING
            // =================================================

            String deleteSql =
                    "DELETE FROM bookings " +
                    "WHERE booking_id=?";

            ps =
                    con.prepareStatement(
                            deleteSql
                    );

            ps.setInt(
                    1,
                    bookingId
            );

            int bookingDeleted =
                    ps.executeUpdate();

            ps.close();

            if (bookingDeleted == 0) {

                con.rollback();

                JOptionPane.showMessageDialog(
                        this,
                        "Booking could not be removed.",
                        "Check Out Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // =================================================
            // STEP 5: COMMIT EVERYTHING
            // =================================================

            con.commit();

            JOptionPane.showMessageDialog(
                    this,
                    "Check-out completed successfully!\n\n"
                            + "Room "
                            + roomNumber
                            + " is now Available.\n"
                            + "Hotel services for this room have been cleared.\n"
                            + "Customer details remain in Customer Management.",
                    "Check Out Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadBookings();
            loadRooms();
            clearFields();

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (SQLException ignored) {
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Check-out failed:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            try {

                if (con != null) {

                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (SQLException ignored) {
            }
        }
    }

    // ================= CLEAR =================
    private void clearFields() {

        if (customerBox.getItemCount() > 0) {
            customerBox.setSelectedIndex(0);
        }

        if (roomBox.getItemCount() > 0) {
            roomBox.setSelectedIndex(0);
        }

        checkInField.setText("");
        checkOutField.setText("");

        guestsSpinner.setValue(1);

        paymentStatusBox.setSelectedItem(
                "Pending"
        );

        onlineRadio.setSelected(true);
        walkInRadio.setSelected(false);

        notesArea.setText("");

        table.clearSelection();
    }

    // ================= SELECT CUSTOMER =================
    private void selectCustomerByName(
            String name
    ) {

        for (
                int i = 0;
                i < customerBox.getItemCount();
                i++
        ) {

            String item =
                    customerBox.getItemAt(i);

            if (
                    item.contains(
                            " - " + name
                    )
            ) {

                customerBox.setSelectedIndex(i);

                return;
            }
        }
    }

    // ================= SELECT ROOM =================
    private void selectRoom(
            String roomNumber
    ) {

        boolean found = false;

        for (
                int i = 0;
                i < roomBox.getItemCount();
                i++
        ) {

            if (
                    roomBox.getItemAt(i)
                            .equals(roomNumber)
            ) {

                roomBox.setSelectedIndex(i);
                found = true;

                break;
            }
        }

        if (!found) {

            roomBox.addItem(roomNumber);

            roomBox.setSelectedItem(
                    roomNumber
            );
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new BookingManagement()
                    .setVisible(true);

        });
    }
}