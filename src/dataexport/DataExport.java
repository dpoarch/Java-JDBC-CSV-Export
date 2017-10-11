/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataexport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import com.mysql.jdbc.Driver;
import java.io.FileWriter;

/**
 *
 * @author David
 */
public class DataExport {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/jdbc";
    
    static final String USER = "root";
    static final String PASS = "";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        String filename ="C:\\tmp\\data.csv";
        try {
            FileWriter fw = new FileWriter(filename);
            Class.forName(JDBC_DRIVER).newInstance();
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT * FROM `location` WHERE `timestamp` BETWEEN '2017-05-01 00:00:00' AND '2017-05-01 23:59:59'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            fw.append(rsmd.getColumnName(1));
            fw.append(',');
            fw.append(rsmd.getColumnName(2));
            fw.append(',');
            fw.append(rsmd.getColumnName(3));
            fw.append(',');
            fw.append(rsmd.getColumnName(4));
            fw.append(',');
            fw.append(rsmd.getColumnName(5));
            fw.append('\n');
            
            while (rs.next()) {
                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append('\n');
               }
            fw.flush();
            fw.close();
            conn.close();
            System.out.println("CSV File is created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
