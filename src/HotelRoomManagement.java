import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class HotelRoomManagement extends JFrame {

    JButton customerButton;
    JButton roomButton;
    JButton bookingButton;
    JButton billingButton;
    JButton staffButton;
    JButton servicesButton;

    JLabel availableCountLabel;
    JLabel bookedCountLabel;
    JLabel maintenanceCountLabel;

    // Main dashboard colors
    private final Color BACKGROUND = new Color(18, 32, 52);
    private final Color BUTTON_COLOR = new Color(40, 75, 105);
    private final Color BUTTON_HOVER = new Color(55, 95, 130);

    // Dashboard refresh timer
    private Timer dashboardTimer;

    public HotelRoomManagement() {

        // =========================
        // WINDOW
        // =========================

        setTitle("Hotel Room Management System");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(
                new BorderLayout(15, 15)
        );

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30
                )
        );

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(
                new GridLayout(2, 1, 3, 3)
        );

        headerPanel.setBackground(BACKGROUND);

        JLabel titleLabel = new JLabel(
                "GRAND HORIZON HOTEL",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel(
                "Hotel Management Dashboard",
                SwingConstants.CENTER
        );

        subtitleLabel.setFont(
                new Font("Arial", Font.PLAIN, 17)
        );

        subtitleLabel.setForeground(
                new Color(190, 210, 225)
        );

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================
        // ROOM STATUS CARDS
        // =========================

        JPanel statusPanel = new JPanel(
                new GridLayout(1, 3, 15, 15)
        );

        statusPanel.setBackground(BACKGROUND);

        JPanel availableCard = createStatusCard(
                "AVAILABLE",
                "Ready for booking",
                new Color(25, 105, 65),
                new Color(80, 220, 130)
        );

        JPanel bookedCard = createStatusCard(
                "BOOKED",
                "Currently occupied",
                new Color(125, 40, 45),
                new Color(255, 100, 100)
        );

        JPanel maintenanceCard = createStatusCard(
                "MAINTENANCE",
                "Temporarily unavailable",
                new Color(145, 75, 25),
                new Color(255, 170, 70)
        );

        statusPanel.add(availableCard);
        statusPanel.add(bookedCard);
        statusPanel.add(maintenanceCard);

        // =========================
        // MANAGEMENT BUTTONS
        // =========================

        JPanel buttonPanel = new JPanel(
                new GridLayout(2, 3, 15, 15)
        );

        buttonPanel.setBackground(BACKGROUND);

        customerButton =
                createManagementButton(
                        "Customer Management"
                );

        roomButton =
                createManagementButton(
                        "Room Management"
                );

        bookingButton =
                createManagementButton(
                        "Booking Management"
                );

        billingButton =
                createManagementButton(
                        "Billing"
                );

        staffButton =
                createManagementButton(
                        "Staff Management"
                );

        servicesButton =
                createManagementButton(
                        "Hotel Services"
                );

        buttonPanel.add(customerButton);
        buttonPanel.add(roomButton);
        buttonPanel.add(bookingButton);
        buttonPanel.add(billingButton);
        buttonPanel.add(staffButton);
        buttonPanel.add(servicesButton);

        // =========================
        // DASHBOARD CENTER
        // =========================

        JPanel dashboardCenter = new JPanel(
                new GridLayout(2, 1, 15, 15)
        );

        dashboardCenter.setBackground(BACKGROUND);

        dashboardCenter.add(statusPanel);
        dashboardCenter.add(buttonPanel);

        mainPanel.add(
                dashboardCenter,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =========================
        // BUTTON ACTIONS
        // =========================

        customerButton.addActionListener(e ->
                new CustomerManagement().setVisible(true)
        );

        roomButton.addActionListener(e ->
                new RoomManagement().setVisible(true)
        );

        bookingButton.addActionListener(e ->
                new BookingManagement().setVisible(true)
        );

        billingButton.addActionListener(e ->
                new Billing().setVisible(true)
        );

        staffButton.addActionListener(e ->
                new StaffManagement().setVisible(true)
        );

        servicesButton.addActionListener(e ->
                new HotelServices().setVisible(true)
        );

        // =========================
        // MENU BAR
        // =========================

        JMenuBar menuBar = new JMenuBar();

        // -------------------------
        // FILE MENU
        // -------------------------

        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem =
                new JMenuItem("Exit");

        exitItem.addActionListener(e ->
                System.exit(0)
        );

        fileMenu.add(exitItem);

        // -------------------------
        // MANAGE MENU
        // -------------------------

        JMenu manageMenu =
                new JMenu("Manage");

        JMenuItem customerItem =
                new JMenuItem(
                        "Customer Management"
                );

        JMenuItem roomItem =
                new JMenuItem(
                        "Room Management"
                );

        JMenuItem bookingItem =
                new JMenuItem(
                        "Booking Management"
                );

        JMenuItem billingItem =
                new JMenuItem("Billing");

        JMenuItem staffItem =
                new JMenuItem(
                        "Staff Management"
                );

        JMenuItem servicesItem =
                new JMenuItem(
                        "Hotel Services"
                );

        customerItem.addActionListener(e ->
                new CustomerManagement()
                        .setVisible(true)
        );

        roomItem.addActionListener(e ->
                new RoomManagement()
                        .setVisible(true)
        );

        bookingItem.addActionListener(e ->
                new BookingManagement()
                        .setVisible(true)
        );

        billingItem.addActionListener(e ->
                new Billing()
                        .setVisible(true)
        );

        staffItem.addActionListener(e ->
                new StaffManagement()
                        .setVisible(true)
        );

        servicesItem.addActionListener(e ->
                new HotelServices()
                        .setVisible(true)
        );

        manageMenu.add(customerItem);
        manageMenu.add(roomItem);
        manageMenu.add(bookingItem);
        manageMenu.add(billingItem);
        manageMenu.add(staffItem);
        manageMenu.add(servicesItem);

        // -------------------------
        // HELP MENU
        // -------------------------

        JMenu helpMenu =
                new JMenu("Help");

        JMenuItem aboutItem =
                new JMenuItem("About");

        aboutItem.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Grand Horizon Hotel\n"
                    + "Hotel Room Management System\n"
                    + "Java Swing + MySQL + JDBC",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });

        helpMenu.add(aboutItem);

        // Add menus
        menuBar.add(fileMenu);
        menuBar.add(manageMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // =========================
        // INITIAL ROOM STATUS
        // =========================

        loadRoomStatus();

        // =========================
        // AUTOMATIC REFRESH
        // =========================

        dashboardTimer = new Timer(
                2000,
                e -> loadRoomStatus()
        );

        dashboardTimer.start();
    }

    // =========================================================
    // CREATE STATUS CARD
    // =========================================================

    private JPanel createStatusCard(
            String title,
            String description,
            Color cardColor,
            Color accentColor
    ) {

        JPanel card = new JPanel(
                new BorderLayout(5, 3)
        );

        card.setBackground(cardColor);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                accentColor,
                                2
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 15, 8, 15
                        )
                )
        );

        // -------------------------
        // TOP
        // -------------------------

        JPanel topPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        6,
                        0
                )
        );

        topPanel.setBackground(cardColor);

        JLabel dotLabel =
                new JLabel("●");

        dotLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        dotLabel.setForeground(accentColor);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        titleLabel.setForeground(Color.WHITE);

        topPanel.add(dotLabel);
        topPanel.add(titleLabel);

        card.add(
                topPanel,
                BorderLayout.NORTH
        );

        // -------------------------
        // COUNT
        // -------------------------

        JLabel countLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        countLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        countLabel.setForeground(Color.WHITE);

        if (title.equals("AVAILABLE")) {

            availableCountLabel =
                    countLabel;

        } else if (title.equals("BOOKED")) {

            bookedCountLabel =
                    countLabel;

        } else if (title.equals("MAINTENANCE")) {

            maintenanceCountLabel =
                    countLabel;
        }

        card.add(
                countLabel,
                BorderLayout.CENTER
        );

        // -------------------------
        // DESCRIPTION
        // -------------------------

        JLabel descriptionLabel =
                new JLabel(
                        description,
                        SwingConstants.CENTER
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                new Color(225, 235, 240)
        );

        card.add(
                descriptionLabel,
                BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // CREATE MANAGEMENT BUTTON
    // =========================================================

    private JButton createManagementButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(BUTTON_COLOR);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(75, 120, 155),
                        1
                )
        );

        // -------------------------
        // HOVER EFFECT
        // -------------------------

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                BUTTON_HOVER
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                BUTTON_COLOR
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // LOAD ROOM STATUS FROM DATABASE
    // =========================================================

    private void loadRoomStatus() {

        int availableRooms = 0;
        int bookedRooms = 0;
        int maintenanceRooms = 0;

        String sql =
                "SELECT status, COUNT(*) AS total "
                + "FROM rooms "
                + "GROUP BY status";

        try (
                Connection conn =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                String status =
                        rs.getString("status");

                int total =
                        rs.getInt("total");

                if (status != null) {

                    if (status.equalsIgnoreCase(
                            "Available")) {

                        availableRooms = total;

                    } else if (
                            status.equalsIgnoreCase(
                                    "Booked")) {

                        bookedRooms = total;

                    } else if (
                            status.equalsIgnoreCase(
                                    "Maintenance")) {

                        maintenanceRooms = total;
                    }
                }
            }

            // Update dashboard labels
            availableCountLabel.setText(
                    String.valueOf(
                            availableRooms
                    )
            );

            bookedCountLabel.setText(
                    String.valueOf(
                            bookedRooms
                    )
            );

            maintenanceCountLabel.setText(
                    String.valueOf(
                            maintenanceRooms
                    )
            );

        } catch (SQLException e) {

            // Do not show popup every 2 seconds.
            System.out.println(
                    "Unable to refresh room status: "
                    + e.getMessage()
            );
        }
    }

    // =========================================================
    // STOP TIMER WHEN WINDOW CLOSES
    // =========================================================

    @Override
    public void dispose() {

        if (dashboardTimer != null) {
            dashboardTimer.stop();
        }

        super.dispose();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HotelRoomManagement hotel =
                    new HotelRoomManagement();

            hotel.setVisible(true);
        });
    }
}