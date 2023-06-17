/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.swing;


import main.gui.*;
import javax.swing.JPasswordField;
import javaswingdev.swing.PasswordFieldSuggestionUI;

public class PasswordFieldSuggestion extends JPasswordField{

    private PasswordFieldSuggestionUI textUI;

    public PasswordFieldSuggestion() {
        textUI = new PasswordFieldSuggestionUI(this);
//        setUI(textUI);
        
        
    }

    public void addItemSuggestion(String text) {
        textUI.getItems().add(text);
    }

    public void removeItemSuggestion(String text) {
        textUI.getItems().remove(text);
        
    }

    public void clearItemSuggestion() {
        textUI.getItems().clear();
    }

    public void setRound(int round) {
        textUI.setRound(round);
    }

    public int getRound() {
        return textUI.getRound();
    }

}