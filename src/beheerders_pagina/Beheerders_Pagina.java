package beheerders_pagina;

import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import javax.swing.JFileChooser;

public class Beheerders_Pagina {

    Connection conn;

    public static void main(String[] args) {

        Beheerders_Pagina formulier = new Beheerders_Pagina();

        //formulier.getConnection();
        formulier.CSVReader();
    }

    public void CSVReader() {

        //final JFileChooser fc = new JFileChooser();
        String csvFile = "C:\\Users\\kevin\\Documents\\Klassenindeling\\Test2.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/klassenindeling";
            String username = "root";
            String password = "root";
            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] stud_data = line.split(cvsSplitBy);
                
                //spatie verwijderen
                for (int i = 0; i < stud_data.length; i++) {
                    stud_data[i] = stud_data[i].trim();
                }

                Student student = new Student();

                student.id = stud_data[0];
                student.voornaam = stud_data[1];
                student.tussenvoegsel = stud_data[2];
                student.achternaam = stud_data[3];
                student.straat = stud_data[4];
                student.nummer = Integer.parseInt(stud_data[5]);
                student.toevoeging = stud_data[6];
                student.postcode = stud_data[7];
                student.plaats = stud_data[8];
                student.vooropleiding = stud_data[9];
                java.util.Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(stud_data[10]);
                student.geboortedatum = new java.sql.Date(datum.getTime());
                student.geslacht = stud_data[11];
                student.nationaliteit = stud_data[12];
                student.medestudent = stud_data[13];
                student.overig = stud_data[14];

                student.insert(conn);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getConnection() {

        try {

            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/klassenindeling";
            String username = "root";
            String password = "root";
            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");

            PrintStream printstream = new PrintStream(new BufferedOutputStream(new FileOutputStream("Lijst.csv")), true);
            System.setOut(printstream);
            String query = "SELECT * FROM studenten";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("Id; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Geslacht; Nationaliteit; Medestudent; Overig");

            ArrayList<Student> studenten = new ArrayList<Student>();
            while (rs.next()) {
                Student student = new Student();

                student.id = rs.getString("Studenten_Nummer");
                student.voornaam = rs.getString("Voornaam");
                student.tussenvoegsel = rs.getString("Tussenvoegsel");
                student.achternaam = rs.getString("Achternaam");
                student.straat = rs.getString("Straat");
                student.nummer = rs.getInt("Nummer");
                student.toevoeging = rs.getString("Toevoeging");
                student.postcode = rs.getString("Postcode");
                student.plaats = rs.getString("Plaats");
                student.vooropleiding = rs.getString("Vooropleiding");
                student.geboortedatum = rs.getDate("Geboortedatum");
                student.geslacht = rs.getString("Geslacht");
                student.nationaliteit = rs.getString("Nationaliteit");
                student.medestudent = rs.getString("Medestudent");
                student.overig = rs.getString("Overig");

                studenten.add(student);

                System.out.println(student);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
