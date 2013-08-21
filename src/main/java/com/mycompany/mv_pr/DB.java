/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mv_pr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author user
 */
public class DB {
    /* the default framework is embedded */

    private String framework = "embedded";
    private String protocol = "jdbc:derby:";
    private static final String ERROR_TABLE_EXISTS_CODE = "X0Y32";

    void go(String[] args) throws SQLException {
        System.out.println("DB starting in " + framework + " mode");
        System.out.println("SimpleApp starting in " + framework + " mode");
        Connection conn = null;
        ArrayList<Statement> statements = new ArrayList<Statement>(); // list of
        PreparedStatement psInsert = null;
        // PreparedStatement psUpdate = null;
        Statement s = null;
        ResultSet rs = null;
        Properties props = new Properties(); // connection properties
        String dbName = "PROPERTIES"; // the name of the database
        conn = DriverManager.getConnection(protocol + dbName + ";create=true", props);
        System.out.println("Connected to and created database " + dbName);
        //conn.setAutoCommit(false);
        s = conn.createStatement();
        statements.add(s);
        try {
            s.execute("create table location(id int, param_name varchar(40), param_value varchar(40), app_id int)");
        } catch (SQLException sqlException) {
            if (!sqlException.getSQLState().equals(ERROR_TABLE_EXISTS_CODE)) {
                throw sqlException;
            }
        }
        System.out.println("Created table location");
        //conn.commit();
        System.out.println("Committed the transaction");

        psInsert = conn.prepareStatement("insert into location values (?, ?, ?, ?)");
        statements.add(psInsert);
        psInsert.setInt(1, 1);
        psInsert.setString(2, "SERVER_NAME");
        psInsert.setString(3, Launcher.SERVER_NAME);
        psInsert.setInt(4, 1);
        psInsert.executeUpdate();
        rs = s.executeQuery("select * from PROPERTIES");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("param_name") + " " + rs.getString("param_value") + " " + rs.getInt("app_id"));
        }
        //conn.commit();
        psInsert.close();
        s.close();

        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    // change
    // Templates.
}
