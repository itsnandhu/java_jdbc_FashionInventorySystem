package com.list;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.main_l.dbcon;

public class inventory {
    Statement stmt;
    ResultSet rs;
    dbcon db = new dbcon();
    Connection con;

    // Add item to DB, auto-generating p_No
    public void add(dress d) {
        int count = 0;
        try {
            con = db.getDBConnection();
            String query = "INSERT INTO dr (category, colour, price, model) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, d.getCategory());
            ps.setString(2, d.getColour());
            ps.setFloat(3, d.getPrice());
            ps.setString(4, d.getModel());
            count = ps.executeUpdate();

            if (count > 0) {
               
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int p_No = generatedKeys.getInt(1);
                    System.out.println("Added in DB successfully with p_No: " + p_No);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Search by auto-generated p_No
    public dress search(int id) {
        dress d = null;
        try {
            con = db.getDBConnection();
            stmt = con.createStatement();
            String query = "SELECT * FROM dr WHERE p_No=" + id;
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                d = new dress();
                d.setP_No(rs.getInt("p_No"));
                d.setCategory(rs.getString("category"));
                d.setColour(rs.getString("colour"));
                d.setPrice(rs.getFloat("price"));
                d.setModel(rs.getString("model"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }


    public void update(dress d, int o) {
        try {
            con = db.getDBConnection();
            stmt = con.createStatement();
            String query = "";
            switch (o) {
                case 1:
                    query = "UPDATE dr SET category='" + d.getCategory() + "' WHERE p_No=" + d.getP_No();
                    break;
                case 2:
                    query = "UPDATE dr SET colour='" + d.getColour() + "' WHERE p_No=" + d.getP_No();
                    break;
                case 3:
                    query = "UPDATE dr SET price=" + d.getPrice() + " WHERE p_No=" + d.getP_No();
                    break;
                case 4:
                    query = "UPDATE dr SET model='" + d.getModel() + "' WHERE p_No=" + d.getP_No();
                    break;
                default:
                    System.out.println("Invalid option");
                    return;
            }
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Updated successfully");
        show();
    }

  
    public void remove(int id) {
        try {
            con = db.getDBConnection();
            stmt = con.createStatement();
            
            stmt.executeUpdate("DELETE FROM dr WHERE p_No=" + id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Item removed from db");
    }

    
    public void show() {
        try {
            con = db.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM dr");
            while (rs.next()) {
                System.out.println(rs.getInt("p_No") + " " + rs.getString("category") + " " + rs.getString("colour") + " " + rs.getFloat("price") + " " + rs.getString("model"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
