/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class MyPassword extends JPasswordField {

    
//    public Icon getPrefixIcon() {
//        return prefixIcon;
//    }
//
//    public void setPrefixIcon(Icon prefixIcon) {
//        this.prefixIcon = prefixIcon;
//        initBorder();
//    }
//
//    public Icon getSuffixIcon() {
//        return suffixIcon;
//    }
//
//    public void setSuffixIcon(Icon suffixIcon) {
//        this.suffixIcon = suffixIcon;
//        initBorder();
//    }
//
//    private Icon prefixIcon;
//    private Icon suffixIcon;

    public MyPassword() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 5, 7, 5));
    }

    @Override
    protected void paintComponent(Graphics g)  {
        super.paintComponent(g);
//        paintIcon(g);
        //  paint border
        
        
        if (isFocusOwner()) {
            g.setColor(new Color(255, 87, 34));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        } else {
            g.setColor(new Color(221, 221, 221));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        }
    }
 private class Border extends EmptyBorder {

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
        }

        public Color getFocusColor() {
            return focusColor;
        }

        public void setFocusColor(Color focusColor) {
            this.focusColor = focusColor;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        private Color focusColor = new Color(255, 87, 34);
        private Color color = new Color(221, 221, 221);
        private int round;

        public Border(int border) {
            super(border, border, border, border);
        }

        public Border() {
            this(5);
        }

        @Override
        public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (cmpnt.isFocusOwner()) {
                g2.setColor(focusColor);
            } else {
                g2.setColor(color);
            }
            g2.drawRoundRect(x, y, width - 1, height - 1, round, round);
            g2.dispose();
        }
    }
//    private void paintIcon(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        if (prefixIcon != null) {
//            Image prefix = ((ImageIcon) prefixIcon).getImage();
//            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
//            g2.drawImage(prefix, 3, y, this);
//        }
//        if (suffixIcon != null) {
//            Image suffix = ((ImageIcon) suffixIcon).getImage();
//            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
//            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 3, y, this);
//        }
//    }
//
//    private void initBorder() {
//        int left = 5;
//        int right = 5;
//        //  5 is default
//        if (prefixIcon != null) {
//            //  prefix is left
//            left = prefixIcon.getIconWidth() + 5;
//        }
//        if (suffixIcon != null) {
//            //  suffix is right
//            right = suffixIcon.getIconWidth() + 5;
//        }
//        setBorder(javax.swing.BorderFactory.createEmptyBorder(7, left, 7, right));
//    }
}