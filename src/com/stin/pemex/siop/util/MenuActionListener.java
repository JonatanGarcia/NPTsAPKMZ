/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.pemex.siop.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;

/**
 *
 * @author Marco
 */
public class MenuActionListener implements ActionListener {
    
    private JInternalFrame formu;    
            
    public MenuActionListener(JInternalFrame formu){
        this.formu=formu;
    }     
    public void actionPerformed(ActionEvent e) {
       formu.toFront();
    }
}
