/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.JPasswordField;
import javax.swing.plaf.metal.MetalTextFieldUI;


public class passwordUI extends MetalTextFieldUI{
    private JPasswordField passwordfield;
    public passwordUI(JPasswordField passwordfield){
        this.passwordfield = passwordfield;
    }
}
