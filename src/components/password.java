/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.JPasswordField;

/**
 *
 * @author mynam
 */
public class password extends JPasswordField{
    private passwordUI passwordUI;
    public password(){
        passwordUI = new passwordUI(this);
//        setUI(passwordUI);
    }
}
