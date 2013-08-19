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
    //В коде не используешь, зачем тогда объявлять?)
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    private static final String ERROR_TABLE_EXISTS_CODE = "X0Y32";
    //у тебя метод выбрасывает SQLException, обрабатывать его внутри не нужно. Его лучше обработать там, где вызываешь, в launcher.
    void go(String[] args) throws SQLException {
//      parseArguments(args); //не надо вызывать методы с заглушкой, которые бросают исключения. У тебя код дальше не выполнялся.
        System.out.println("DB starting in " + framework + " mode");
        Connection conn = null;
        ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        Statement s = null;
        ResultSet rs = null;
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
            //таблицу нужно создавать только при первом запуске, когда она не существует. При последующих - она уже есть.
            try {
            	s.execute("create table location(num int, addr varchar(40))");
            } catch (SQLException sqlException) {
            	//Проверяем, если код состояния НЕ "таблица уже существует", то бросаем исключение. Иначе всё в порядке."
            	if (!sqlException.getSQLState().equals(ERROR_TABLE_EXISTS_CODE)) {
            		throw sqlException;
            	}
            }
            System.out.println("Created table location");
            conn.commit();
            System.out.println("Committed the transaction");
   //!!!!!!         
            //заполняем строки
            psInsert = conn.prepareStatement(
                        "insert into location values (?, ?)");
            statements.add(psInsert);

            psInsert.setInt(1, 1);  //не поняла по какому принципу ставятся цифры)) .. что вообще значит первая единичка?
            psInsert.setString(1, "SERVER_PORT");
            psInsert.setString(1, "localhost:8080");//вот тут я хочу взять значение хоста текущей открытой страницы
            //пытаюсь написать get, вылезает куча ошибок
            
            
            psInsert.executeUpdate();
            System.out.println("1  SERVER_PORT   localhost:8080");
//!!!!!!
            ///
            ///
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
