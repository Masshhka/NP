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
    /* the default framework is embedded*/

    private String framework = "embedded";
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";

    void go(String[] args) throws SQLException {
        parseArguments(args);
        System.out.println("SimpleApp starting in " + framework + " mode");
        Connection conn = null;
        ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        Statement s = null;
        ResultSet rs = null;
        try {
        } catch (Exception e) {
        }
        {
            Properties props = new Properties(); // connection properties
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = "derbyDB"; // the name of the database
            conn = DriverManager.getConnection(protocol + dbName + ";create=true", props);
            System.out.println("Connected to and created database " + dbName);
            conn.setAutoCommit(false);
            s = conn.createStatement();
            statements.add(s);
            s.execute("create table location(num int, addr varchar(40))");
            System.out.println("Created table location");
            conn.commit();
            System.out.println("Committed the transaction");
        }
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }
    }

    private void parseArguments(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void printSQLException(SQLException sqle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
