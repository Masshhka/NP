/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mv_pr;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;




/**
 *
 * @author user
 */
public class Launcher 
{
private static final String APPLICATION_PATH = "src\\main\\webapp";//путь к папке с приложением было src/main/webapp
private static final int SERVER_PORT = 8080;// на каком порту работает приложение
private static final String MAPPING = "/";//на каком адресе отвечает приложение
private static void initializeServer(String appPath, String mapping, int port) throws Exception 
    {
        Server server = new Server();
        Connector connector = new SelectChannelConnector();
        connector.setPort(port); // на каком порту слушаем соединение от клиента
        server.addConnector(connector); //привязываем коннектор к серверу
        WebAppContext root = new WebAppContext(appPath, mapping); //создаём контекст нашего приложения, тут указываем путь к файлам и куда мапить в конструкторе (тут тоже пригодится импорт)
        server.setHandlers(new Handler[] { root });//серверу устанавливаются обработчики запросов на адре
        server.start();
       
        //new Handler[] { root } - тут создаётся массив экземпляров класса Handler с одним элементом - root
    }


    public static void main(String[] args)
    {
      try 
      {
        initializeServer(APPLICATION_PATH, MAPPING, SERVER_PORT);
      } 
      catch (Exception exception) 
      {
         exception.printStackTrace();
      }
      
        new DB().go(args);
        System.out.println("SimpleApp finished");
    
    }
    
}
