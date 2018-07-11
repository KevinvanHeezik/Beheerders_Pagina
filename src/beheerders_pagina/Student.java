package beheerders_pagina;

import java.sql.*;

public class Student {
    
    public static String COMMA = "; ";

    String stud_nr;
    String voornaam;
    String tussenvoegsel;
    String achternaam;
    String straat;
    int nummer;
    String toevoeging;
    String postcode;
    String plaats;
    String vooropleiding;
    Date geboortedatum;
    String geslacht;
    String nationaliteit;

    void insert(Connection conn) throws Exception {
        // the mysql insert statement
        String query = " INSERT INTO studenten (Studenten_Nummer, Voornaam, Tussenvoegsel, Achternaam, Straat, Nummer, Toevoeging, Postcode, Plaats, Vooropleiding, Geboortedatum, Geslacht, Nationaliteit)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, stud_nr);
        preparedStmt.setString(2, voornaam);
        preparedStmt.setString(3, tussenvoegsel);
        preparedStmt.setString(4, achternaam);
        preparedStmt.setString(5, straat);
        preparedStmt.setInt(6, nummer);
        preparedStmt.setString(7, toevoeging);
        preparedStmt.setString(8, postcode);
        preparedStmt.setString(9, plaats);
        preparedStmt.setString(10, vooropleiding);
        preparedStmt.setDate(11, geboortedatum);
        preparedStmt.setString(12, geslacht);
        preparedStmt.setString(13, nationaliteit);

        // execute the preparedstatement
        preparedStmt.execute();

    }

    @Override
    public String toString()
    {
      return stud_nr + COMMA + voornaam + COMMA + tussenvoegsel + COMMA + achternaam + COMMA + straat + COMMA + nummer + COMMA + toevoeging + COMMA + postcode + COMMA + plaats + COMMA + vooropleiding + COMMA + geboortedatum + COMMA + geslacht + COMMA + nationaliteit; 
    }
}
