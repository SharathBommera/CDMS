import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomePage extends JFrame implements ActionListener {
    public HomePage() {
        setTitle("Home Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel for the heading and buttons
        JPanel panel = new JPanel(null);
        panel.setOpaque(false); // Make panel transparent

        JLabel headingLabel = new JLabel("CROP DATA MANAGEMENT SYSTEM");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBounds(0, 20, 800, 50);
        panel.add(headingLabel);

        // Set up background image
        ImageIcon backgroundImage = new ImageIcon("F:\\Team-06(1602-22-737-165 & 175)\\project\\images\\cFarm.jpg"); // Replace with your image file path
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        
        // Add panel to the background label
        backgroundLabel.add(panel);

        // Set background label as content pane
        setContentPane(backgroundLabel);

        // Add buttons
        JButton[] buttons = new JButton[9];
        String[] buttonLabels = {"FARMERS", "FARM", "PESTICIDES", "PESTICIDES_FARM", "EQUIPMENT", "EQUIPMENT_FARMERS", "CROP", "PROFIT_LOSS", "CONTACTS"};
        String[] filePaths = {
                "C:\\MyWebProjects\\FarmersManagement.java",
                "C:\\MyWebProjects\\FarmManagement.java",
                "C:\\MyWebProjects\\PesticidesManagement.java",
                "C:\\MyWebProjects\\Pesticides_FarmManagement.java",
                "C:\\MyWebProjects\\EquipmentManagement.java",
                "C:\\MyWebProjects\\Equipment_FarmersManagement.java",
                "C:\\MyWebProjects\\CropManagement.java",
                "C:\\MyWebProjects\\Profit_LossManagement.java",
                "C:\\MyWebProjects\\Farmer_F_ContactManagement.java"
        };

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBounds(50 + (i % 3) * 250, 100 + (i / 3) * 150, 200, 100);
            buttons[i].setActionCommand(filePaths[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = e.getActionCommand();
        openFile(filePath);
    }

    private void openFile(String filePath) {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "-cp", ".;C:\\MyWebProjects\\mysql-connector-j-8.4.0\\mysql-connector-j-8.4.0\\mysql-connector-j-8.4.0.jar", filePath);
            Process process = pb.start();
            
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                String className = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
                pb = new ProcessBuilder("java", "-cp", ".;C:\\MyWebProjects\\mysql-connector-j-8.4.0\\mysql-connector-j-8.4.0\\mysql-connector-j-8.4.0.jar", className);
                process = pb.start();
            } else {
                System.out.println("Compilation failed for file: " + filePath);
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage());
    }
}
