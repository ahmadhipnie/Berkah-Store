package javaswingdev.message;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
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
import javax.swing.border.EmptyBorder;
import main.gui.Karyawan;

public class AlertGagal extends javax.swing.JDialog {

    public final JFrame fram;
    private Animator animator;
    Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;


    public AlertGagal(JFrame fram) {
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
                glass.setAlpha(f - f * 0.4f);
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

    public MessageType getMessageType() {
        return messageType;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmdCancel = new javaswingdev.swing.ButtonCustom();
        lbIcon = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        txt = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cmdCancel.setBackground(new java.awt.Color(255, 255, 255));
        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert2.png"))); // NOI18N
        cmdCancel.setColorHover(new Color(255,255,255,0));
        cmdCancel.setColorPressed(new Color(255,255,255,0));
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
        cmdCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdCancelKeyPressed(evt);
            }
        });

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/bunder-merah.png"))); // NOI18N

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(245, 71, 71));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Message Title");

        txt.setEditable(false);
        txt.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        txt.setForeground(new java.awt.Color(76, 76, 76));
        txt.setText("Message Text\nSimple");
        txt.setFocusable(false);
        txt.setMaximumSize(new java.awt.Dimension(111, 48));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdCancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdCancelKeyPressed
        // TODO add your handling code here:
 
    if (evt.getKeyCode()==KeyEvent.VK_ENTER){
              messageType = MessageType.CANCEL;
        closeMessage();
    }
    }//GEN-LAST:event_cmdCancelKeyPressed

    private void cmdCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseEntered
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert2.png")));
    }//GEN-LAST:event_cmdCancelMouseEntered

    private void cmdCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseExited
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert-hover2.png")));
    }//GEN-LAST:event_cmdCancelMouseExited

    private void cmdCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMousePressed
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert-clicked2.png")));
    }//GEN-LAST:event_cmdCancelMousePressed

    private void cmdCancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseReleased
        // TODO add your handling code here:
cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Alert/btn-alert2.png")));
    }//GEN-LAST:event_cmdCancelMouseReleased

    public static enum MessageType {
        CANCEL, OK
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javaswingdev.swing.ButtonCustom cmdCancel;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel lbIcon;
    public static javax.swing.JLabel lbTitle;
    public static javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}
