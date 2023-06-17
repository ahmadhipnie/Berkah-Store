package javaswingdev.message;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javaswingdev.swing.Glass;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class AlertPertanyaanHijau extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;

    public AlertPertanyaanHijau(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
    }

    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        StyledDocument doc = txt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        txt.setOpaque(false);
        txt.setBackground(new Color(0, 0, 0, 0));
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
        lbTitle.setText(title);
        txt.setText(message);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }



    public void closeMessage() {
        startAnimator(false);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        cmdCancel = new javaswingdev.swing.ButtonCustom();
        cmdOK = new javaswingdev.swing.ButtonCustom();
        txt = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(384, 370));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(384, 370));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(384, 370));
        jPanel1.setPreferredSize(new java.awt.Dimension(384, 370));
        jPanel1.setLayout(null);

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(70, 204, 89));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Message Title");
        jPanel1.add(lbTitle);
        lbTitle.setBounds(0, 20, 380, 26);

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/tanaja ijo.png"))); // NOI18N
        jPanel1.add(lbIcon);
        lbIcon.setBounds(0, 100, 380, 160);

        cmdCancel.setBackground(new java.awt.Color(255, 255, 255));
        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/batal-hijau.png"))); // NOI18N
        cmdCancel.setColorHover(new java.awt.Color(255, 255, 255));
        cmdCancel.setColorPressed(new java.awt.Color(255, 255, 255));
        cmdCancel.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmdCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdCancelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdCancelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmdCancelMouseReleased(evt);
            }
        });
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });
        jPanel1.add(cmdCancel);
        cmdCancel.setBounds(0, 320, 380, 40);

        cmdOK.setBackground(new java.awt.Color(255, 255, 255));
        cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/ya-hijau.png"))); // NOI18N
        cmdOK.setColorHover(new java.awt.Color(255, 255, 255));
        cmdOK.setColorPressed(new java.awt.Color(255, 255, 255));
        cmdOK.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmdOK.addMouseListener(new java.awt.event.MouseAdapter() {
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

        txt.setEditable(false);
        txt.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        txt.setForeground(new java.awt.Color(76, 76, 76));
        txt.setText("Message Text\nSimple");
        txt.setFocusable(false);
        jPanel1.add(txt);
        txt.setBounds(0, 50, 380, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.OK;
        closeMessage();
    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseEntered
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/batal-hijau-hover.png")));
    }//GEN-LAST:event_cmdCancelMouseEntered

    private void cmdCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseExited
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/batal-hijau.png")));
    }//GEN-LAST:event_cmdCancelMouseExited

    private void cmdCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMousePressed
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/batal-hijau-clicked.png")));
    }//GEN-LAST:event_cmdCancelMousePressed

    private void cmdCancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseReleased
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/batal-hijau.png")));
    }//GEN-LAST:event_cmdCancelMouseReleased

    private void cmdOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseEntered
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/ya-hijau-hover.png")));
    }//GEN-LAST:event_cmdOKMouseEntered

    private void cmdOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseExited
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/ya-hijau.png")));
    }//GEN-LAST:event_cmdOKMouseExited

    private void cmdOKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMousePressed
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/ya-hijau-clicked.png")));
    }//GEN-LAST:event_cmdOKMousePressed

    private void cmdOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseReleased
        // TODO add your handling code here:
cmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/ya-hijau.png")));
    }//GEN-LAST:event_cmdOKMouseReleased

    private void cmdOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdOKKeyPressed
        // TODO add your handling code here:
if (evt.getKeyCode()==KeyEvent.VK_ENTER){
              messageType = AlertPertanyaanHijau.MessageType.OK;
        closeMessage();
}
    }//GEN-LAST:event_cmdOKKeyPressed

    public static enum MessageType {
        CANCEL, OK
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.swing.ButtonCustom cmdCancel;
    public javaswingdev.swing.ButtonCustom cmdOK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}
