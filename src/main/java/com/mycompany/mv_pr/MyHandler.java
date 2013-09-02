/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mv_pr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

/**
 *
 * @author user
 */
 public class MyHandler extends AbstractHandler{

//    public void handle(String target, Request baseRequest,HttpServletRequest request, HttpServletResponse response)
//                throws IOException, ServletException {
 @Overridee
     public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
        throws IOException, ServletException{
            response.setContentType("text/html;charset=utf-8");

            response.setStatus(HttpServletResponse.SC_OK);

          //  baseRequest.setHandled(true);

            response.getWriter().println("Hello World");
        }

         }
