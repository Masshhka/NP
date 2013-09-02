/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mv_pr;

import java.io.File;
import java.sql.SQLException;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 *
 * @author user
 */
public class Launcher {

    public static final String APPLICATION_PATH = "src" + File.separator + "main" + File.separator + "webapp";
    public static final int SERVER_PORT = 8089;                       // на каком порту работает приложение
    public static final String MAPPING = "/";                        //на каком адресе отвечает приложение
    public static final String SERVER_NAME = "localhost";
    private static final String warPath = APPLICATION_PATH + File.separator + "WebApp-1.0-SNAPSHOT.war";

    private static void initializeServer(String appPath, String mapping, int port) throws Exception {
        Server server = new Server();
        Connector connector = new SelectChannelConnector();
        connector.setPort(port);                                          // на каком порту слушаем соединение от клиента
        server.addConnector(connector);                                  //привязываем коннектор к серверу
        WebAppContext root = new WebAppContext();        //создаём контекст нашего приложения, тут указываем путь к файлам и куда мапить в конструкторе (тут тоже пригодится импорт)
        root.setContextPath(MAPPING);
        root.setWar(warPath);
        server.setHandler(root);
        //server.setHandlers(new Handler[]{root});                          //серверу устанавливаются обработчики запросов на адре
        server.start();
        //new Handler[] { root } - тут создаётся массив экземпляров класса Handler с одним элементом - root
    }

    public static void main(String[] args) {
        try {
            initializeServer(APPLICATION_PATH, MAPPING, SERVER_PORT);

         new DB().go(args);

            System.out.println("DB finished");
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
