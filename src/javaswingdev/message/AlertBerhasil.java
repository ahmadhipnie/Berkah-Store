package javaswingdev.message;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javaswingdev.message.AlertGagal.lbTitle;
import static javaswingdev.message.AlertGagal.txt;
import javaswingdev.swing.Glass;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class AlertBerhasil extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private AlertBerhasil.MessageType messageType = AlertBerhasil.MessageType.OK;

    public AlertBerhasil(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
    }

    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        StyledDocument doc = txt1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        txt1.setOpaque(false);
        txt1.setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeMessage();
            }
        });
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0.3f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if (show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.1f);
        animator.setDeceleration(.1f);
        setOpacity(0f);
        glass = new Glass();
    }

    private void startAnimator(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }

    public void showMessage(String title, String message) {
        fram.setGlassPane(glass);
        glass.setVisible(true);
        lbTitle1.setText(title);
        txt1.setText(message);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }

    public void tampilkan(String title, String message) {   
       
        lbTitle.setText(title);
        txt.setText(message);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
        
    }
public void glass(){
fram.setGlassPane(glass);
        glass.setVisible(true);
setLocationRelativeTo(fram);
}

    public void closeMessage() {
        startAnimator(false);
    }

    public AlertBerhasil.MessageType getMessageType() {
        return messageType;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitle1 = new javax.swing.JLabel();
        lbIcon1 = new javax.swing.JLabel();
        cmdOK = new javaswingdev.swing.ButtonCustom();
        txt1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(384, 370));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(384, 370));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(384, 370));
        jPanel1.setPreferredSize(new java.awt.Dimension(384, 332));
        jPanel1.setLayout(null);

        lbTitle1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lbTitle1.setForeground(new java.awt.Color(70, 204, 89));
        lbTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle1.setText("Message Title");
        jPanel1.add(lbTitle1);
        lbTitle1.setBounds(0, 20, 390, 40);

        lbIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/bunder-hijau.png"))); // NOI18N
        jPanel1.add(lbIcon1);
        lbIcon1.setBounds(0, 120, 380, 135);

        cmdOK.setBackground(new java.awt.Color(255, 255, 255));
        cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert-4.png"))); // NOI18N
        cmdOK.setColorHover(new java.awt.Color(255, 255, 255));
        cmdOK.setColorPressed(new java.awt.Color(255, 255, 255));
        cmdOK.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmdOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdOKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdOKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdOKMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdOKMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmdOKMouseReleased(evt);
            }
        });
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });
        cmdOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdOKKeyPressed(evt);
            }
        });
        jPanel1.add(cmdOK);
        cmdOK.setBounds(0, 270, 380, 40);

        txt1.setEditable(false);
        txt1.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        txt1.setForeground(new java.awt.Color(76, 76, 76));
        txt1.setText("Message Text\nSimple");
        txt1.setFocusable(false);
        jPanel1.add(txt1);
        txt1.setBounds(0, 70, 380, 48);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.OK;
        closeMessage();

    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseEntered
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert-4-hover.png")));
    }//GEN-LAST:event_cmdOKMouseEntered

    private void cmdOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseExited
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert-4.png")));
    }//GEN-LAST:event_cmdOKMouseExited

    private void cmdOKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMousePressed
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert-4-clicked.png")));
    }//GEN-LAST:event_cmdOKMousePressed

    private void cmdOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseReleased
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert-4.png")));
    }//GEN-LAST:event_cmdOKMouseReleased

    private void cmdOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdOKMouseClicked

    private void cmdOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdOKKeyPressed
        // TODO add your handling code here:
    if (evt.getKeyCode()==KeyEvent.VK_ENTER){
              messageType = AlertBerhasil.MessageType.OK;
        closeMessage();
}
    }//GEN-LAST:event_cmdOKKeyPressed

    public static enum MessageType {
        CANCEL, OK
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javaswingdev.swing.ButtonCustom cmdOK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIcon1;
    private javax.swing.JLabel lbTitle1;
    private javax.swing.JTextPane txt1;
    // End of variables declaration//GEN-END:variables
}
