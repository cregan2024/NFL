import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class JerseyDesigner extends JFrame implements ActionListener {
    private Container c;
    private JLabel title;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel numberLabel;
    private JTextField numberField;
    private JLabel teamLabel;
    private JComboBox<String> teamDropdown;
    private JLabel typeLabel;
    private JComboBox<String> typeDropdown;
    private JButton submitButton;
    private JButton readCSVButton;
    private JTextArea resultArea;

    private String[] jerseyTypes = {"Home", "Away", "Alternate"};
    private String[] nflTeams = {"Arizona Cardinals", "Atlanta Falcons", "Baltimore Ravens", "Buffalo Bills", "Carolina Panthers", "Chicago Bears", "Cincinnati Bengals", "Cleveland Browns", "Dallas Cowboys", "Denver Broncos", "Detroit Lions", "Green Bay Packers", "Houston Texans", "Indianapolis Colts", "Jacksonville Jaguars", "Kansas City Chiefs", "Las Vegas Raiders", "Los Angeles Chargers", "Los Angeles Rams", "Miami Dolphins", "Minnesota Vikings", "New England Patriots", "New Orleans Saints", "New York Giants", "New York Jets", "Philadelphia Eagles", "Pittsburgh Steelers", "San Francisco 49ers", "Seattle Seahawks", "Tampa Bay Buccaneers", "Tennessee Titans", "Washington Football Team"};
    private File dataFile;

    public JerseyDesigner() {
        setTitle("Jersey Designer");
        setBounds(300, 90, 600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Design Your NFL Jersey");
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        title.setSize(300, 30);
        title.setLocation(200, 30);
        c.add(title);

        nameLabel = new JLabel("Name on Jersey:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setSize(200, 20);
        nameLabel.setLocation(100, 100);
        c.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 15));
        nameField.setSize(190, 20);
        nameField.setLocation(330, 100);
        c.add(nameField);

        numberLabel = new JLabel("Number:");
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        numberLabel.setSize(100, 20);
        numberLabel.setLocation(100, 150);
        c.add(numberLabel);

        numberField = new JTextField();
        numberField.setFont(new Font("Arial", Font.PLAIN, 15));
        numberField.setSize(150, 20);
        numberField.setLocation(330, 150);
        c.add(numberField);

        teamLabel = new JLabel("Team:");
        teamLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        teamLabel.setSize(100, 20);
        teamLabel.setLocation(100, 200);
        c.add(teamLabel);

        teamDropdown = new JComboBox<>(nflTeams);
        teamDropdown.setFont(new Font("Arial", Font.PLAIN, 15));
        teamDropdown.setSize(200, 20);
        teamDropdown.setLocation(330, 200);
        c.add(teamDropdown);

        typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        typeLabel.setSize(100, 20);
        typeLabel.setLocation(100, 250);
        c.add(typeLabel);

        typeDropdown = new JComboBox<>(jerseyTypes);
        typeDropdown.setFont(new Font("Arial", Font.PLAIN, 15));
        typeDropdown.setSize(120, 20);
        typeDropdown.setLocation(330, 250);
        c.add(typeDropdown);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(150, 300);
        submitButton.addActionListener(this);
        c.add(submitButton);

        readCSVButton = new JButton("Read CSV");
        readCSVButton.setFont(new Font("Arial", Font.PLAIN, 15));
        readCSVButton.setSize(100, 20);
        readCSVButton.setLocation(350, 300);
        readCSVButton.addActionListener(this);
        c.add(readCSVButton);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Arial", Font.PLAIN, 15));
        resultArea.setSize(400, 200);
        resultArea.setLocation(100, 350);
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);
        c.add(resultArea);

        // Create or load data file
        dataFile = new File("jersey_data.csv");

        setVisible(true);
    }
    //making the interface

    // Save entered data to file
    private void saveData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true))) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read data from CSV file and display in the result area
    private void readDataFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            StringBuilder data = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            resultArea.setText(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String number = numberField.getText();
            String team = (String) teamDropdown.getSelectedItem();
            String type = (String) typeDropdown.getSelectedItem();

            String result = name + "," + number + "," + team + "," + type;
            resultArea.setText("Name: " + name + "\nNumber: " + number + "\nTeam: " + team + "\nType: " + type);

            // Save entered data
            saveData(result);
        } else if (e.getSource() == readCSVButton) {
            // Read data from CSV
            readDataFromCSV();
        }
    }
    //all the csv action, prints out data from csv when it hits read csv

    public static void main(String[] args) {
        new JerseyDesigner();
    }
}
