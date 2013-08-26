/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mv_pr;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
   public class For_Frame extends JFrame{
    public For_Frame(){
        setSize(400, 300);
        setTitle("Properties");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // метод getContentPane() принадлежит классу JFrame
        //возвращает область содержимого для объекта JFrame
        //другими словами, возвращает объект, имеющий метод для добавления компонента на фрейм
        //общий вид:
        Container contentPane=super.getContentPane();
        //для добавления панели:
        // класс MyPanel описан далее
        For_Panel panel1=new For_Panel();
        contentPane.add(panel1);
    }
}