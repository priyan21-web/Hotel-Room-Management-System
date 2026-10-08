import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Billing extends JFrame {

    JComboBox<String> customerBox;
    JComboBox<String> roomBox;
    JComboBox<String> paymentStatusBox;
    JComboBox<String> paymentMethodBox;

    JCheckBox gstCheck;
    JCheckBox serviceCheck;

    JSpinner discountSpinner;

    JTextField priceField;
    JTextArea notesArea;

    JTable bookingTable;
    DefaultTableModel tableModel;

    // =========================
    // DARK GRAND HORIZON COLORS
    // =========================

    private final Color BACKGROUND = new Color(18, 32, 52);
    private final Color PANEL_COLOR = new Color(25, 45, 68);
    private final Color FIELD_COLOR = new Color(35, 58, 82);
    private final Color TABLE_COLOR = new Color(22, 40, 60);

    private final Color ACCENT = new Color(80, 210, 180);
    private final Color BLUE = new Color(70, 145, 235);
    private final Color GREEN = new Color(65, 190, 120);
    private final Color RED = new Color(225, 75, 85);
    private final Color ORANGE = new Color(235, 155, 65);
    private final Color PURPLE = new Color(130, 105, 220);

    private final Color TEXT_COLOR = new Color(235, 245, 250);
    private final Color SECONDARY_TEXT = new Color(175, 195, 210);
    private final Color BORDER_COLOR = new Color(55, 80, 105);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Billing() {

        setTitle("Billing - Grand Horizon Hotel");

        setSize(1100, 720);

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
                        "BILLING"
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
                        "Grand Horizon Hotel • Manage Guest Bills and Payments"
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
        // MAIN CONTENT
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
        // BILLING FORM
        // =================================================

        JPanel billingPanel =
                new JPanel(
                        new GridBagLayout()
                );

        billingPanel.setBackground(
                PANEL_COLOR
        );

        billingPanel.setBorder(
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

        // Smaller fields
        Dimension fieldSize =
                new Dimension(
                        180,
                        30
                );

        // =================================================
        // CUSTOMER
        // =================================================

        addFormRow(
                billingPanel,
                gbc,
                0,
                "Customer",
                customerBox =
                        createComboBox(fieldSize)
        );

        // =================================================
        // ROOM
        // =================================================

        addFormRow(
                billingPanel,
                gbc,
                1,
                "Room Number",
                roomBox =
                        createComboBox(fieldSize)
        );

        // =================================================
        // PRICE
        // =================================================

        priceField =
                createTextField();

        priceField.setPreferredSize(
                fieldSize
        );

        priceField.setEditable(
                false
        );

        priceField.setBackground(
                new Color(30, 50, 72)
        );

        addFormRow(
                billingPanel,
                gbc,
                2,
                "Price / Day",
                priceField
        );

        // =================================================
        // PAYMENT STATUS
        // =================================================

        paymentStatusBox =
                new JComboBox<>(
                        new String[]{
                                "Pending",
                                "Paid",
                                "Partially Paid"
                        }
                );

        styleComboBox(
                paymentStatusBox,
                fieldSize
        );

        addFormRow(
                billingPanel,
                gbc,
                3,
                "Payment Status",
                paymentStatusBox
        );

        // =================================================
        // PAYMENT METHOD
        // =================================================

        paymentMethodBox =
                new JComboBox<>(
                        new String[]{
                                "Cash",
                                "Card",
                                "UPI"
                        }
                );

        styleComboBox(
                paymentMethodBox,
                fieldSize
        );

        addFormRow(
                billingPanel,
                gbc,
                4,
                "Payment Method",
                paymentMethodBox
        );

        // =================================================
        // ADDITIONAL CHARGES
        // =================================================

        gstCheck =
                new JCheckBox(
                        "GST 18%"
                );

        serviceCheck =
                new JCheckBox(
                        "Service Charge 5%"
                );

        styleCheckBox(
                gstCheck
        );

        styleCheckBox(
                serviceCheck
        );

        JPanel chargePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                0
                        )
                );

        chargePanel.setBackground(
                PANEL_COLOR
        );

        chargePanel.add(
                gstCheck
        );

        chargePanel.add(
                serviceCheck
        );

        addFormRow(
                billingPanel,
                gbc,
                5,
                "Additional Charges",
                chargePanel
        );

        // =================================================
        // DISCOUNT
        // =================================================

        discountSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                0,
                                0,
                                100,
                                1
                        )
                );

        discountSpinner.setPreferredSize(
                new Dimension(
                        80,
                        30
                )
        );

        discountSpinner.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        addFormRow(
                billingPanel,
                gbc,
                6,
                "Discount %",
                discountSpinner
        );

        // =================================================
        // NOTES
        // =================================================

        notesArea =
                new JTextArea(
                        2,
                        20
                );

        notesArea.setLineWrap(
                true
        );

        notesArea.setWrapStyleWord(
                true
        );

        notesArea.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        notesArea.setForeground(
                TEXT_COLOR
        );

        notesArea.setBackground(
                FIELD_COLOR
        );

        notesArea.setCaretColor(
                TEXT_COLOR
        );

        notesArea.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                4,
                                6,
                                4,
                                6
                        )
                )
        );

        JScrollPane notesScroll =
                new JScrollPane(
                        notesArea
                );

        notesScroll.setPreferredSize(
                new Dimension(
                        180,
                        50
                )
        );

        notesScroll.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );

        addFormRow(
                billingPanel,
                gbc,
                7,
                "Notes",
                notesScroll
        );

        // =================================================
        // BUTTONS
        // =================================================

        JButton refreshButton =
                createButton(
                        "Refresh",
                        BLUE
                );

        JButton showBillButton =
                createButton(
                        "Show Bill",
                        GREEN
                );

        JButton paidButton =
                createButton(
                        "Paid",
                        ORANGE
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        RED
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

        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                showBillButton
        );

        buttonPanel.add(
                paidButton
        );

        buttonPanel.add(
                clearButton
        );

        GridBagConstraints buttonGbc =
                new GridBagConstraints();

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 8;

        buttonGbc.gridwidth = 2;

        buttonGbc.insets =
                new Insets(
                        7,
                        5,
                        3,
                        5
                );

        buttonGbc.anchor =
                GridBagConstraints.CENTER;

        billingPanel.add(
                buttonPanel,
                buttonGbc
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
                billingPanel,
                BorderLayout.CENTER
        );

        contentPanel.add(
                formWrapper,
                BorderLayout.NORTH
        );

        // =================================================
        // BOOKING TABLE
        // =================================================

        String[] columns = {
                "Booking ID",
                "Customer",
                "Room",
                "Check-In",
                "Check-Out",
                "Days",
                "Price/Day",
                "Payment"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        bookingTable =
                new JTable(
                        tableModel
                );

        bookingTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        bookingTable.setRowHeight(
                30
        );

        bookingTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        bookingTable.setForeground(
                TEXT_COLOR
        );

        bookingTable.setBackground(
                TABLE_COLOR
        );

        bookingTable.setSelectionBackground(
                new Color(
                        55,
                        95,
                        120
                )
        );

        bookingTable.setSelectionForeground(
                Color.WHITE
        );

        bookingTable.setGridColor(
                new Color(
                        50,
                        70,
                        90
                )
        );

        JTableHeader header =
                bookingTable.getTableHeader();

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

        bookingTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(85);

        bookingTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(190);

        bookingTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(90);

        bookingTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(110);

        bookingTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(110);

        bookingTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(65);

        bookingTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(100);

        bookingTable
                .getColumnModel()
                .getColumn(7)
                .setPreferredWidth(120);

        JScrollPane tableScroll =
                new JScrollPane(
                        bookingTable
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
                        "BOOKING DETAILS"
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

        // =================================================
        // ADD CONTENT
        // =================================================

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // LOAD DATA
        // =================================================

        loadCustomers();

        loadRooms();

        loadBookings();

        // =================================================
        // ROOM CHANGE
        // =================================================

        roomBox.addActionListener(
                e -> {

                    if (
                            roomBox.getSelectedItem()
                                    != null
                    ) {

                        loadRoomPrice(
                                roomBox
                                        .getSelectedItem()
                                        .toString()
                        );
                    }
                }
        );

        // =================================================
        // TABLE SELECTION
        // =================================================

        bookingTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (
                                    e.getValueIsAdjusting()
                            ) {
                                return;
                            }

                            int row =
                                    bookingTable
                                            .getSelectedRow();

                            if (row != -1) {

                                String customer =
                                        tableModel
                                                .getValueAt(
                                                        row,
                                                        1
                                                )
                                                .toString();

                                String room =
                                        tableModel
                                                .getValueAt(
                                                        row,
                                                        2
                                                )
                                                .toString();

                                customerBox
                                        .setSelectedItem(
                                                customer
                                        );

                                roomBox
                                        .setSelectedItem(
                                                room
                                        );

                                priceField
                                        .setText(
                                                tableModel
                                                        .getValueAt(
                                                                row,
                                                                6
                                                        )
                                                        .toString()
                                        );

                                paymentStatusBox
                                        .setSelectedItem(
                                                tableModel
                                                        .getValueAt(
                                                                row,
                                                                7
                                                        )
                                        );
                            }
                        }
                );

        // =================================================
        // REFRESH
        // =================================================

        refreshButton.addActionListener(
                e -> {

                    loadCustomers();

                    loadRooms();

                    loadBookings();

                    JOptionPane.showMessageDialog(
                            this,
                            "Billing data refreshed!",
                            "Refresh",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
        );

        // =================================================
        // SHOW BILL
        // =================================================

        showBillButton.addActionListener(
                e -> showBill()
        );

        // =================================================
        // PAID
        // =================================================

        paidButton.addActionListener(
                e -> markAsPaid()
        );

        // =================================================
        // CLEAR
        // =================================================

        clearButton.addActionListener(
                e -> clearFields()
        );
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

        JLabel label =
                new JLabel(
                        labelText
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

        panel.add(
                label,
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
    // COMBO BOX
    // =====================================================

    private JComboBox<String> createComboBox(
            Dimension size) {

        JComboBox<String> box =
                new JComboBox<>();

        styleComboBox(
                box,
                size
        );

        return box;
    }

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
    // TEXT FIELD
    // =====================================================

    private JTextField createTextField() {

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
    // CHECKBOX
    // =====================================================

    private void styleCheckBox(
            JCheckBox checkBox) {

        checkBox.setBackground(
                PANEL_COLOR
        );

        checkBox.setForeground(
                TEXT_COLOR
        );

        checkBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
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
                        105,
                        32
                )
        );

        return button;
    }

    // =====================================================
    // MARK AS PAID
    // =====================================================

    private void markAsPaid() {

        int row =
                bookingTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a booking first.",
                    "Payment",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int bookingId;

        try {

            bookingId =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            row,
                                            0
                                    )
                                    .toString()
                    );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid booking selected.",
                    "Payment Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String currentStatus =
                tableModel
                        .getValueAt(
                                row,
                                7
                        )
                        .toString();

        if (
                currentStatus.equalsIgnoreCase(
                        "Paid"
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This booking is already marked as Paid.",
                    "Payment",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Mark this booking as Paid?",
                        "Confirm Payment",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (
                confirmation
                        != JOptionPane.YES_OPTION
        ) {
            return;
        }

        String sql =
                "UPDATE bookings "
                        + "SET payment_status = 'Paid' "
                        + "WHERE booking_id = ?";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    bookingId
            );

            int updated =
                    ps.executeUpdate();

            if (updated > 0) {

                paymentStatusBox
                        .setSelectedItem(
                                "Paid"
                        );

                loadBookings();

                JOptionPane.showMessageDialog(
                        this,
                        "Payment status updated to Paid.",
                        "Payment Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment status could not be updated.",
                        "Payment Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update payment status:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SHOW BILL
    // =====================================================

    private void showBill() {

        int row =
                bookingTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a booking from the table.",
                    "Billing",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            String customer =
                    tableModel
                            .getValueAt(
                                    row,
                                    1
                            )
                            .toString();

            String room =
                    tableModel
                            .getValueAt(
                                    row,
                                    2
                            )
                            .toString();

            String checkIn =
                    tableModel
                            .getValueAt(
                                    row,
                                    3
                            )
                            .toString();

            String checkOut =
                    tableModel
                            .getValueAt(
                                    row,
                                    4
                            )
                            .toString();

            int days =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            row,
                                            5
                                    )
                                    .toString()
                    );

            double price =
                    Double.parseDouble(
                            tableModel
                                    .getValueAt(
                                            row,
                                            6
                                    )
                                    .toString()
                    );

            String payment =
                    tableModel
                            .getValueAt(
                                    row,
                                    7
                            )
                            .toString();

            // =========================
            // ROOM CHARGE
            // =========================

            double roomCharge =
                    days * price;

            // =========================
            // DISCOUNT
            // =========================

            double discountPercent =
                    (int) discountSpinner
                            .getValue();

            double discountAmount =
                    roomCharge
                            * discountPercent
                            / 100;

            // =========================
            // FOOD CHARGES
            // =========================

            double foodCharges =
                    getFoodCharges(
                            room
                    );

            double amount =
                    roomCharge
                            - discountAmount
                            + foodCharges;

            // =========================
            // GST
            // =========================

            double gstAmount = 0;

            if (
                    gstCheck.isSelected()
            ) {

                gstAmount =
                        amount * 18 / 100;
            }

            // =========================
            // SERVICE CHARGE
            // =========================

            double serviceAmount = 0;

            if (
                    serviceCheck.isSelected()
            ) {

                serviceAmount =
                        amount * 5 / 100;
            }

            // =========================
            // FINAL TOTAL
            // =========================

            double finalTotal =
                    amount
                            + gstAmount
                            + serviceAmount;

            // =========================
            // BILL
            // =========================

            StringBuilder bill =
                    new StringBuilder();

            bill.append(
                    "             GRAND HORIZON HOTEL\n"
            );

            bill.append(
                    "                  HOTEL BILL\n"
            );

            bill.append(
                    "========================================\n"
            );

            bill.append(
                    "Customer       : "
            ).append(
                    customer
            ).append(
                    "\n"
            );

            bill.append(
                    "Room Number    : "
            ).append(
                    room
            ).append(
                    "\n"
            );

            bill.append(
                    "Check-In       : "
            ).append(
                    checkIn
            ).append(
                    "\n"
            );

            bill.append(
                    "Check-Out      : "
            ).append(
                    checkOut
            ).append(
                    "\n"
            );

            bill.append(
                    "Number of Days : "
            ).append(
                    days
            ).append(
                    "\n"
            );

            bill.append(
                    "Price Per Day  : ₹"
            ).append(
                    String.format(
                            "%.2f",
                            price
                    )
            ).append(
                    "\n"
            );

            bill.append(
                    "----------------------------------------\n"
            );

            bill.append(
                    "Room Charge    : ₹"
            ).append(
                    String.format(
                            "%.2f",
                            roomCharge
                    )
            ).append(
                    "\n"
            );

            bill.append(
                    "Discount ("
            ).append(
                    (int) discountPercent
            ).append(
                    "%)       : -₹"
            ).append(
                    String.format(
                            "%.2f",
                            discountAmount
                    )
            ).append(
                    "\n"
            );

            bill.append(
                    "Food Charges   : ₹"
            ).append(
                    String.format(
                            "%.2f",
                            foodCharges
                    )
            ).append(
                    "\n"
            );

            if (
                    gstCheck.isSelected()
            ) {

                bill.append(
                        "GST (18%)      : ₹"
                ).append(
                        String.format(
                                "%.2f",
                                gstAmount
                        )
                ).append(
                        "\n"
                );
            }

            if (
                    serviceCheck.isSelected()
            ) {

                bill.append(
                        "Service Charge : ₹"
                ).append(
                        String.format(
                                "%.2f",
                                serviceAmount
                        )
                ).append(
                        "\n"
                );
            }

            bill.append(
                    "========================================\n"
            );

            bill.append(
                    "FINAL TOTAL    : ₹"
            ).append(
                    String.format(
                            "%.2f",
                            finalTotal
                    )
            ).append(
                    "\n"
            );

            bill.append(
                    "----------------------------------------\n"
            );

            bill.append(
                    "Payment Status : "
            ).append(
                    payment
            ).append(
                    "\n"
            );

            bill.append(
                    "Payment Method : "
            ).append(
                    paymentMethodBox
                            .getSelectedItem()
            ).append(
                    "\n"
            );

            if (
                    !notesArea
                            .getText()
                            .trim()
                            .isEmpty()
            ) {

                bill.append(
                        "Notes          : "
                ).append(
                        notesArea
                                .getText()
                ).append(
                        "\n"
                );
            }

            bill.append(
                    "========================================\n"
            );

            bill.append(
                    "          Thank you for staying with us!\n"
            );

            JTextArea billArea =
                    new JTextArea(
                            bill.toString()
                    );

            billArea.setFont(
                    new Font(
                            "Monospaced",
                            Font.PLAIN,
                            14
                    )
            );

            billArea.setForeground(
                    new Color(
                            35,
                            45,
                            55
                    )
            );

            billArea.setBackground(
                    new Color(
                            245,
                            248,
                            250
                    )
            );

            billArea.setEditable(
                    false
            );

            billArea.setBorder(
                    new EmptyBorder(
                            12,
                            12,
                            12,
                            12
                    )
            );

            JScrollPane billScroll =
                    new JScrollPane(
                            billArea
                    );

            billScroll.setPreferredSize(
                    new Dimension(
                            520,
                            450
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    billScroll,
                    "Grand Horizon Hotel - Bill",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to generate bill:\n"
                            + e.getMessage(),
                    "Billing Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD CUSTOMERS
    // =====================================================

    void loadCustomers() {

        customerBox.removeAllItems();

        String sql =
                "SELECT name FROM customers "
                        + "ORDER BY name";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (
                    rs.next()
            ) {

                customerBox.addItem(
                        rs.getString(
                                "name"
                        )
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load customers:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD ROOMS
    // =====================================================

    void loadRooms() {

        roomBox.removeAllItems();

        String sql =
                "SELECT room_number "
                        + "FROM rooms "
                        + "ORDER BY room_number";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (
                    rs.next()
            ) {

                roomBox.addItem(
                        rs.getString(
                                "room_number"
                        )
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load rooms:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD ROOM PRICE
    // =====================================================

    void loadRoomPrice(
            String roomNumber) {

        String sql =
                "SELECT price FROM rooms "
                        + "WHERE room_number = ?";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    roomNumber
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (
                        rs.next()
                ) {

                    priceField.setText(
                            rs.getString(
                                    "price"
                            )
                    );
                }
            }

        } catch (Exception e) {

            priceField.setText("");
        }
    }

    // =====================================================
    // LOAD BOOKINGS
    // =====================================================

    void loadBookings() {

        tableModel.setRowCount(
                0
        );

        String sql =
                "SELECT b.booking_id, "
                        + "c.name, "
                        + "r.room_number, "
                        + "b.check_in, "
                        + "b.check_out, "
                        + "b.payment_status, "
                        + "r.price "
                        + "FROM bookings b "
                        + "JOIN customers c "
                        + "ON b.customer_id = c.customer_id "
                        + "JOIN rooms r "
                        + "ON b.room_id = r.room_id "
                        + "ORDER BY b.booking_id";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (
                    rs.next()
            ) {

                LocalDate checkIn =
                        rs.getDate(
                                "check_in"
                        ).toLocalDate();

                LocalDate checkOut =
                        rs.getDate(
                                "check_out"
                        ).toLocalDate();

                long days =
                        ChronoUnit.DAYS.between(
                                checkIn,
                                checkOut
                        );

                if (
                        days <= 0
                ) {

                    days = 1;
                }

                tableModel.addRow(
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

                                rs.getDate(
                                        "check_in"
                                ),

                                rs.getDate(
                                        "check_out"
                                ),

                                days,

                                rs.getDouble(
                                        "price"
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
                    "Unable to load bookings:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // FOOD CHARGES
    // =====================================================

    double getFoodCharges(
            String roomNumber) {

        double foodCharges = 0;

        String sql =
                "SELECT COALESCE(SUM(hs.price), 0) "
                        + "FROM hotel_services hs "
                        + "JOIN rooms r "
                        + "ON hs.room_id = r.room_id "
                        + "WHERE r.room_number = ?";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    roomNumber
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (
                        rs.next()
                ) {

                    foodCharges =
                            rs.getDouble(1);
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to calculate food charges:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return foodCharges;
    }

    // =====================================================
    // CLEAR
    // =====================================================

    void clearFields() {

        if (
                customerBox.getItemCount()
                        > 0
        ) {

            customerBox.setSelectedIndex(
                    0
            );
        }

        if (
                roomBox.getItemCount()
                        > 0
        ) {

            roomBox.setSelectedIndex(
                    0
            );
        }

        paymentStatusBox.setSelectedIndex(
                0
        );

        paymentMethodBox.setSelectedIndex(
                0
        );

        priceField.setText(
                ""
        );

        discountSpinner.setValue(
                0
        );

        gstCheck.setSelected(
                false
        );

        serviceCheck.setSelected(
                false
        );

        notesArea.setText(
                ""
        );

        bookingTable.clearSelection();
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    Billing billing =
                            new Billing();

                    billing.setVisible(
                            true
                    );
                }
        );
    }
}