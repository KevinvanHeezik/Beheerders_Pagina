package beheerders_pagina;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class Beheerders_Pagina {

    Connection conn;

    public static void main(String[] args) {

        Beheerders_Pagina formulier = new Beheerders_Pagina();

        formulier.getConnection();
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

            System.out.println("Id; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Geslacht; Nationaliteit");

            ArrayList<Student> studenten = new ArrayList<Student>();
            while (rs.next()) {
                Student student = new Student();

                student.stud_nr = rs.getString("Studenten_Nummer");
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

                studenten.add(student);

                System.out.println(student);
            }

            //System.out.println("lijstje");
            //System.out.println(studenten);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
