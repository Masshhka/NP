/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mv_pr;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class For_Panel extends JPanel {

    public For_Panel() {
        // указать менеджер компоновки
        //setLayout(new Менеджер_компоновки(парметры));
        // добавление компонентов на панель
        //Тип_компонента имя_компонента=new Тип_компонента(параметры);
        //add(имя_компонента);
        GridLayout g1 = new GridLayout(5, 1);
        setLayout(g1);
        
        JPanel pn1 = new JPanel();
        JLabel l1 = new JLabel("APPLICATION_PATH");
        pn1.add(l1);
        JTextField t1 = new JTextField(15);
        pn1.add(t1);
        add(pn1);

        JPanel pn2 = new JPanel();
        JLabel l2 = new JLabel("SERVER_NAME");
        pn2.add(l2);
        JTextField t2 = new JTextField(15);
        pn2.add(t2);
        add(pn2);
        
        JPanel pn3 = new JPanel();
        JLabel l3 = new JLabel("SERVER_PORT");
        pn3.add(l3);
        JTextField t3 = new JTextField(15);
        pn2.add(t3);
        add(pn3);
        
        JPanel pn4 = new JPanel();
        JLabel l4 = new JLabel("MAPPING");
        pn4.add(l4);
        JTextField t4 = new JTextField(15);
        pn4.add(t4);
        add(pn4);

        JPanel pn5 = new JPanel();
        JButton b1 = new JButton("Выполнить");
        pn5.add(b1);
        add(pn5);
    }
}
