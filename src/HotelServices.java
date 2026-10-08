import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class HotelServices extends JFrame {

    private JComboBox<String> roomBox;
    private JComboBox<String> cuisineBox;
    private JComboBox<String> mealBox;
    private JComboBox<String> foodBox;

    private JTextArea orderArea;

    private final Color BACKGROUND = new Color(18, 32, 52);
    private final Color PANEL_COLOR = new Color(27, 46, 68);
    private final Color FIELD_COLOR = new Color(40, 60, 82);
    private final Color BUTTON_COLOR = new Color(40, 100, 135);
    private final Color BUTTON_HOVER = new Color(55, 130, 165);

    public HotelServices() {

        setTitle("Hotel Services");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 2, 2));
        headerPanel.setBackground(BACKGROUND);

        JLabel titleLabel = new JLabel(
                "HOTEL SERVICES",
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel(
                "Grand Horizon Hotel - Food & Dining Services",
                SwingConstants.CENTER
        );

        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitleLabel.setForeground(new Color(190, 210, 225));

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(BACKGROUND);

        // =====================================================
        // FORM PANEL
        // =====================================================

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_COLOR);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(70, 110, 145),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 25, 15, 25
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(7, 12, 7, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // =====================================================
        // ROOM
        // =====================================================

        addLabel(formPanel, "Room Number:", gbc, 0);

        roomBox = new JComboBox<>();
        styleComboBox(roomBox);

        gbc.gridx = 1;
        gbc.gridy = 0;

        formPanel.add(roomBox, gbc);

        // =====================================================
        // CUISINE
        // =====================================================

        addLabel(formPanel, "Cuisine:", gbc, 1);

        cuisineBox = new JComboBox<>(
                new String[]{
                        "South Indian",
                        "North Indian",
                        "Chinese",
                        "Continental"
                }
        );

        styleComboBox(cuisineBox);

        gbc.gridx = 1;
        gbc.gridy = 1;

        formPanel.add(cuisineBox, gbc);

        // =====================================================
        // MEAL
        // =====================================================

        addLabel(formPanel, "Meal:", gbc, 2);

        mealBox = new JComboBox<>(
                new String[]{
                        "Breakfast",
                        "Lunch",
                        "Dinner"
                }
        );

        styleComboBox(mealBox);

        gbc.gridx = 1;
        gbc.gridy = 2;

        formPanel.add(mealBox, gbc);

        // =====================================================
        // FOOD
        // =====================================================

        addLabel(formPanel, "Food Item:", gbc, 3);

        foodBox = new JComboBox<>();
        styleComboBox(foodBox);

        gbc.gridx = 1;
        gbc.gridy = 3;

        formPanel.add(foodBox, gbc);

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton addButton = createButton("Add Service");
        JButton clearButton = createButton("Clear");

        JPanel buttonPanel =
                new JPanel(new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        2
                ));

        buttonPanel.setBackground(PANEL_COLOR);

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        formPanel.add(buttonPanel, gbc);

        centerPanel.add(formPanel, BorderLayout.CENTER);

        // =====================================================
        // SELECTED SERVICES
        // =====================================================

        JPanel orderPanel =
                new JPanel(new BorderLayout(5, 5));

        orderPanel.setBackground(BACKGROUND);

        JLabel orderLabel =
                new JLabel("Selected Hotel Services");

        orderLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        orderLabel.setForeground(Color.WHITE);

        orderPanel.add(
                orderLabel,
                BorderLayout.NORTH
        );

        orderArea = new JTextArea();

        orderArea.setEditable(false);

        orderArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        orderArea.setForeground(Color.WHITE);

        orderArea.setBackground(
                new Color(22, 39, 58)
        );

        orderArea.setLineWrap(true);
        orderArea.setWrapStyleWord(true);

        orderArea.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        JScrollPane orderScroll =
                new JScrollPane(orderArea);

        orderScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(70, 110, 145)
                )
        );

        orderPanel.add(
                orderScroll,
                BorderLayout.CENTER
        );

        orderPanel.setPreferredSize(
                new Dimension(900, 210)
        );

        centerPanel.add(
                orderPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =====================================================
        // LOAD ROOMS
        // =====================================================

        loadRooms();

        // =====================================================
        // INITIAL FOOD LIST
        // =====================================================

        updateFoodItems();

        // =====================================================
        // CUISINE CHANGE
        // =====================================================

        cuisineBox.addActionListener(
                e -> updateFoodItems()
        );

        // =====================================================
        // MEAL CHANGE
        // =====================================================

        mealBox.addActionListener(
                e -> updateFoodItems()
        );

        // =====================================================
        // ROOM SELECTION
        // =====================================================

        roomBox.addActionListener(e -> {

            if (roomBox.getSelectedItem() == null) {
                return;
            }

            String room =
                    roomBox.getSelectedItem().toString();

            loadServicesForRoom(room);

            if (!isRoomBooked(room)) {

                JOptionPane.showMessageDialog(
                        this,
                        "This room is not booked.\n"
                        + "Hotel service can only be added "
                        + "to a booked room.",
                        "Room Not Booked",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // =====================================================
        // ADD SERVICE
        // =====================================================

        addButton.addActionListener(e -> {

            if (roomBox.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a room.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String room =
                    roomBox.getSelectedItem().toString();

            if (!isRoomBooked(room)) {

                JOptionPane.showMessageDialog(
                        this,
                        "This room is not booked.\n"
                        + "Hotel service can only be added "
                        + "to a booked room.",
                        "Room Not Booked",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String cuisine =
                    cuisineBox.getSelectedItem().toString();

            String meal =
                    mealBox.getSelectedItem().toString();

            String food =
                    foodBox.getSelectedItem().toString();

            if (saveService(
                    room,
                    cuisine,
                    meal,
                    food
            )) {

                loadServicesForRoom(room);

                JOptionPane.showMessageDialog(
                        this,
                        "Hotel service added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // =====================================================
        // CLEAR
        // =====================================================

        clearButton.addActionListener(e -> {

            roomBox.setSelectedIndex(-1);

            cuisineBox.setSelectedIndex(0);

            mealBox.setSelectedIndex(0);

            updateFoodItems();

            orderArea.setText("");
        });
    }

    // =========================================================
    // LOAD ROOMS
    // =========================================================

    private void loadRooms() {

        roomBox.removeAllItems();

        String sql =
                "SELECT room_number "
                + "FROM rooms "
                + "ORDER BY room_number";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                roomBox.addItem(
                        rs.getString("room_number")
                );
            }

            roomBox.setSelectedIndex(-1);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load rooms.\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // GET ROOM ID
    // =========================================================

    private int getRoomId(String roomNumber) {

        String sql =
                "SELECT room_id "
                + "FROM rooms "
                + "WHERE room_number = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, roomNumber);

            try (
                    ResultSet rs =
                            stmt.executeQuery()
            ) {

                if (rs.next()) {
                    return rs.getInt("room_id");
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to get room ID.\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return -1;
    }

    // =========================================================
    // CHECK ROOM STATUS
    // =========================================================

    private boolean isRoomBooked(String roomNumber) {

        String sql =
                "SELECT status "
                + "FROM rooms "
                + "WHERE room_number = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, roomNumber);

            try (
                    ResultSet rs =
                            stmt.executeQuery()
            ) {

                if (rs.next()) {

                    String status =
                            rs.getString("status");

                    return status != null
                            && status.equalsIgnoreCase(
                                    "Booked"
                            );
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to check room status.\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return false;
    }

    // =========================================================
    // GET FOOD PRICE
    // =========================================================

    private double getFoodPrice(String food) {

        switch (food) {

            // -----------------------------
            // SOUTH INDIAN
            // -----------------------------

            case "Idli":
                return 80.00;

            case "Dosa":
                return 100.00;

            case "Poori":
                return 100.00;

            case "Steamed Rice with Veg":
                return 180.00;

            case "Steamed Rice with Non-Veg":
                return 250.00;

            case "Biriyani":
                return 280.00;

            case "Idiyapam":
                return 120.00;

            case "Fruit Bowl":
                return 150.00;

            // -----------------------------
            // NORTH INDIAN
            // -----------------------------

            case "Aloo Paratha":
                return 140.00;

            case "Chole Bhature":
                return 160.00;

            case "Paneer Stuffed Kulcha":
                return 180.00;

            case "Butter Chicken":
                return 320.00;

            case "Paneer Makhani":
                return 280.00;

            case "Dal Makhani":
                return 220.00;

            case "Rogan Josh":
                return 350.00;

            case "Murgh Tikka Masala":
                return 330.00;

            case "Shahi Paneer":
                return 290.00;

            // -----------------------------
            // CHINESE
            // -----------------------------

            case "Vegetable Congee":
                return 140.00;

            case "Steamed Vegetable Dumplings":
                return 180.00;

            case "Chicken Bao":
                return 220.00;

            case "Veg Hakka Noodles":
                return 220.00;

            case "Kung Pao Chicken":
                return 320.00;

            case "Fried Rice":
                return 230.00;

            case "Stir Fried Prawns":
                return 380.00;

            case "Mapo Tofu":
                return 280.00;

            case "Szechuan Chicken":
                return 350.00;

            // -----------------------------
            // CONTINENTAL
            // -----------------------------

            case "Classic English Breakfast":
                return 300.00;

            case "Pancakes with Fresh Berries":
                return 250.00;

            case "Croissant & Cheese Platter":
                return 280.00;

            case "Grilled Chicken Breast":
                return 350.00;

            case "Penne Arrabbiata":
                return 280.00;

            case "Vegetable Quiche":
                return 250.00;

            case "Grilled Fish Fillet":
                return 400.00;

            case "Chicken Stroganoff":
                return 380.00;

            case "Mushroom Risotto":
                return 320.00;

            default:
                return 0.00;
        }
    }

    // =========================================================
    // SAVE SERVICE
    // =========================================================

    private boolean saveService(
            String roomNumber,
            String cuisine,
            String meal,
            String food
    ) {

        int roomId =
                getRoomId(roomNumber);

        if (roomId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room ID could not be found.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        double price =
                getFoodPrice(food);

        if (price <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price not found for selected food item.",
                    "Price Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        String sql =
                "INSERT INTO hotel_services "
                + "(room_id, meal_type, cuisine, food_item, "
                + "quantity, price) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, roomId);

            stmt.setString(2, meal);

            stmt.setString(3, cuisine);

            stmt.setString(4, food);

            // Quantity
            stmt.setInt(5, 1);

            // Actual food price
            stmt.setDouble(6, price);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to save hotel service.\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }
    }

    // =========================================================
    // LOAD SERVICES FOR ROOM
    // =========================================================

    private void loadServicesForRoom(
            String roomNumber
    ) {

        orderArea.setText("");

        int roomId =
                getRoomId(roomNumber);

        if (roomId == -1) {
            return;
        }

        String sql =
                "SELECT meal_type, cuisine, food_item, "
                + "quantity, price "
                + "FROM hotel_services "
                + "WHERE room_id = ? "
                + "ORDER BY service_id";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, roomId);

            try (
                    ResultSet rs =
                            stmt.executeQuery()
            ) {

                while (rs.next()) {

                    double price =
                            rs.getDouble("price");

                    int quantity =
                            rs.getInt("quantity");

                    double total =
                            price * quantity;

                    orderArea.append(
                            "Room: " + roomNumber
                            + "    |    "
                            + rs.getString("cuisine")
                            + "    |    "
                            + rs.getString("meal_type")
                            + "    |    "
                            + rs.getString("food_item")
                            + "    |    Qty: "
                            + quantity
                            + "    |    ₹"
                            + String.format("%.2f", total)
                            + "\n"
                    );
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load hotel services.\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE FOOD ITEMS
    // =========================================================

    private void updateFoodItems() {

        foodBox.removeAllItems();

        String cuisine =
                cuisineBox.getSelectedItem() != null
                        ? cuisineBox.getSelectedItem().toString()
                        : "";

        String meal =
                mealBox.getSelectedItem() != null
                        ? mealBox.getSelectedItem().toString()
                        : "";

        // =====================================================
        // SOUTH INDIAN
        // =====================================================

        if (cuisine.equals("South Indian")) {

            if (meal.equals("Breakfast")) {

                addFood("Idli");
                addFood("Dosa");
                addFood("Poori");

            } else if (meal.equals("Lunch")) {

                addFood("Steamed Rice with Veg");
                addFood("Steamed Rice with Non-Veg");
                addFood("Biriyani");

            } else {

                addFood("Dosa");
                addFood("Idiyapam");
                addFood("Fruit Bowl");
            }
        }

        // =====================================================
        // NORTH INDIAN
        // =====================================================

        else if (cuisine.equals("North Indian")) {

            if (meal.equals("Breakfast")) {

                addFood("Aloo Paratha");
                addFood("Chole Bhature");
                addFood("Paneer Stuffed Kulcha");

            } else if (meal.equals("Lunch")) {

                addFood("Butter Chicken");
                addFood("Paneer Makhani");
                addFood("Dal Makhani");

            } else {

                addFood("Rogan Josh");
                addFood("Murgh Tikka Masala");
                addFood("Shahi Paneer");
            }
        }

        // =====================================================
        // CHINESE
        // =====================================================

        else if (cuisine.equals("Chinese")) {

            if (meal.equals("Breakfast")) {

                addFood("Vegetable Congee");
                addFood("Steamed Vegetable Dumplings");
                addFood("Chicken Bao");

            } else if (meal.equals("Lunch")) {

                addFood("Veg Hakka Noodles");
                addFood("Kung Pao Chicken");
                addFood("Fried Rice");

            } else {

                addFood("Stir Fried Prawns");
                addFood("Mapo Tofu");
                addFood("Szechuan Chicken");
            }
        }

        // =====================================================
        // CONTINENTAL
        // =====================================================

        else if (cuisine.equals("Continental")) {

            if (meal.equals("Breakfast")) {

                addFood("Classic English Breakfast");
                addFood("Pancakes with Fresh Berries");
                addFood("Croissant & Cheese Platter");

            } else if (meal.equals("Lunch")) {

                addFood("Grilled Chicken Breast");
                addFood("Penne Arrabbiata");
                addFood("Vegetable Quiche");

            } else {

                addFood("Grilled Fish Fillet");
                addFood("Chicken Stroganoff");
                addFood("Mushroom Risotto");
            }
        }
    }

    // =========================================================
    // ADD FOOD
    // =========================================================

    private void addFood(String food) {

        foodBox.addItem(food);
    }

    // =========================================================
    // ADD LABEL
    // =========================================================

    private void addLabel(
            JPanel panel,
            String text,
            GridBagConstraints gbc,
            int row
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        label.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;

        panel.add(label, gbc);
    }

    // =========================================================
    // STYLE COMBO BOX
    // =========================================================

    private void styleComboBox(
            JComboBox<String> comboBox
    ) {

        comboBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        comboBox.setForeground(Color.WHITE);

        comboBox.setBackground(FIELD_COLOR);

        comboBox.setPreferredSize(
                new Dimension(300, 36)
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(BUTTON_COLOR);

        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(150, 40)
        );

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(80, 140, 175),
                        1
                )
        );

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
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            HotelServices services =
                    new HotelServices();

            services.setVisible(true);
        });
    }
}