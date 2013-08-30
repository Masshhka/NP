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
        Statement s = null;
        ResultSet rs = null;
        Properties props = new Properties(); // connection properties
        String dbName = "PROPERTIES"; // the name of the database
        conn = DriverManager.getConnection(protocol + dbName + ";create=true", props);
        System.out.println("Connected to and created database " + dbName);
        conn.setAutoCommit(true);
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
        System.out.println("Committed the transaction");
        rs = s.executeQuery("SELECT count(*) FROM location WHERE SERVER_NAME=localhost");
        //+
        if (rs==null) 
        {
            psInsert = conn.prepareStatement("insert into location values (?, ?, ?, ?)");
            statements.add(psInsert);
            psInsert.setInt(1, 1);
            psInsert.setString(2, "SERVER_NAME");
            psInsert.setString(3, Launcher.SERVER_NAME);
            psInsert.setInt(4, 1);
        } else {
            psInsert.executeUpdate();
        }
        psInsert.setInt(1, 2);
        psInsert.setString(2, "SERVER_PORT");
        psInsert.setString(3, String.valueOf(Launcher.SERVER_PORT));
        psInsert.executeUpdate();

        psInsert.setInt(1, 3);
        psInsert.setString(2, "MAPPING");
        psInsert.setString(3, Launcher.MAPPING);
        psInsert.executeUpdate();

        psInsert.setInt(1, 4);
        psInsert.setString(2, "APPLICATION_PATH");
        psInsert.setString(3, Launcher.APPLICATION_PATH);
        psInsert.executeUpdate();

        rs = s.executeQuery("select * from location");
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
