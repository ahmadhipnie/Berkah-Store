/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import javaswingdev.message.AlertBerhasil;
import javaswingdev.message.AlertGagal;
import javaswingdev.message.AlertPertanyaan;
import javaswingdev.message.JSystemFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author mynam
 */
public class Settingkar extends javax.swing.JFrame {
String filename = null; 
private ImageIcon format = null;
    /**
     * Creates new form Dashboard
     */
    public Settingkar() {
        initComponents();
        profile.setVisible(false);
        pw.setVisible(false);
        Cursor();
        about.setVisible(false);
        setFavIcon();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
    }

void panggilAlertHijau(String Title,String message){
            AlertBerhasil obj2 = new AlertBerhasil(this);
            obj2.showMessage(Title, message);
}
void panggilAlertMerah(String Title, String message){
            AlertGagal obj3 = new AlertGagal(this);
            obj3.showMessage(Title, message);
}


private void Cursor(){
transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
gudang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
tambahkar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
upfoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
cancel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
okepw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
editprof.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
gantipw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
tentang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
cancel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
}

void setprofile() {
        try {
            String sql = "SELECT jenis_pengguna, img"
                    + " FROM pengguna"
                    + " WHERE id_pengguna='" + nama.getText() +"' ;" ;
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            jenis.setText(rs.getString(1));
            jenispane.setText(rs.getString(1));
            namapane.setText(nama.getText());
            String imgsql = rs.getString(2);
//admin
            try{
            BufferedImage img = null;
            img = ImageIO.read(new File(imgsql));
            Image img1=img.getScaledInstance(image.getWidth(),image.getHeight(),Image.SCALE_SMOOTH);
            format = new ImageIcon(img1);
            image.setIcon(format);
            imagepane.setIcon(format);
            }catch(Exception e){
            e.printStackTrace();
            }
//} else {
//
//}

        } catch (SQLException e) {
            e.printStackTrace();
        }
}

private void setFavIcon(){
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/main/img/Logoapp.png")));
}

class ImageFilter extends FileFilter {
   public final static String JPEG = "jpeg";
   public final static String JPG = "jpg";
   public final static String GIF = "gif";
   public final static String TIFF = "tiff";
   public final static String TIF = "tif";
   public final static String PNG = "png";
   
   @Override
   public boolean accept(File f) {
      if (f.isDirectory()) {
         return true;
      }

      String extension = getExtension(f);
      if (extension != null) {
         if (extension.equals(TIFF) ||
            extension.equals(TIF) ||
            extension.equals(GIF) ||
            extension.equals(JPEG) ||
            extension.equals(JPG) ||
            extension.equals(PNG)) {
            return true;
         } else {
            return false;
         }
      }
      return false;
   }

   @Override
   public String getDescription() {
      return "Image Only";
   }

   String getExtension(File f) {
      String ext = null;
      String s = f.getName();
      int i = s.lastIndexOf('.');
   
      if (i > 0 &&  i < s.length() - 1) {
         ext = s.substring(i+1).toLowerCase();
      }
      return ext;
   } 
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nama = new javax.swing.JLabel();
        jenis = new javax.swing.JLabel();
        gudang = new javax.swing.JLabel();
        transaksi = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        pengaturan = new javax.swing.JLabel();
        riwayat = new javax.swing.JLabel();
        Menu = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        about = new javax.swing.JPanel();
        cancel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pw = new javax.swing.JPanel();
        txtpwlama = new javax.swing.JPasswordField();
        txtpw = new javax.swing.JPasswordField();
        txtpwbaru = new javax.swing.JPasswordField();
        cancel1 = new javax.swing.JLabel();
        txtid1 = new javax.swing.JLabel();
        okepw = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        profile = new javax.swing.JPanel();
        gambar = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        cancel = new javax.swing.JLabel();
        upfoto = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();
        tambahkar = new javax.swing.JLabel();
        txtnomor = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jenispane = new javax.swing.JLabel();
        namapane = new javax.swing.JLabel();
        tentang = new javax.swing.JLabel();
        editprof = new javax.swing.JLabel();
        gantipw = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        imagepane = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko-Berkah Pengaturan");
        setMinimumSize(new java.awt.Dimension(1280, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(null);

        nama.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        nama.setForeground(new java.awt.Color(48, 56, 65));
        nama.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                namaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(nama);
        nama.setBounds(1030, 30, 240, 30);

        jenis.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jenis.setForeground(new java.awt.Color(48, 56, 65));
        getContentPane().add(jenis);
        jenis.setBounds(1030, 60, 240, 30);

        gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/gudang.png"))); // NOI18N
        gudang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gudangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gudangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gudangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gudangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                gudangMouseReleased(evt);
            }
        });
        getContentPane().add(gudang);
        gudang.setBounds(21, 242, 200, 30);

        transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/transaksi.png"))); // NOI18N
        transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                transaksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                transaksiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                transaksiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                transaksiMouseReleased(evt);
            }
        });
        getContentPane().add(transaksi);
        transaksi.setBounds(21, 201, 200, 30);

        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard.png"))); // NOI18N
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dashboardMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dashboardMouseReleased(evt);
            }
        });
        getContentPane().add(dashboard);
        dashboard.setBounds(21, 162, 200, 30);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/logout.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logoutMouseReleased(evt);
            }
        });
        getContentPane().add(logout);
        logout.setBounds(21, 454, 200, 30);

        pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/pengaturan-clicked.png"))); // NOI18N
        pengaturan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pengaturanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pengaturanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pengaturanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pengaturanMouseReleased(evt);
            }
        });
        getContentPane().add(pengaturan);
        pengaturan.setBounds(21, 415, 200, 30);

        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/riwayat.png"))); // NOI18N
        riwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                riwayatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                riwayatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                riwayatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                riwayatMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                riwayatMouseReleased(evt);
            }
        });
        getContentPane().add(riwayat);
        riwayat.setBounds(21, 280, 200, 30);

        Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/kosong.png"))); // NOI18N
        Menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuMouseClicked(evt);
            }
        });
        getContentPane().add(Menu);
        Menu.setBounds(0, 100, 218, 630);

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/Header.png"))); // NOI18N
        header.setAlignmentY(0.0F);
        getContentPane().add(header);
        header.setBounds(0, 0, 1280, 110);

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/default (1).png"))); // NOI18N
        getContentPane().add(image);
        image.setBounds(938, 10, 75, 90);

        about.setBackground(new java.awt.Color(235, 235, 235));
        about.setLayout(null);

        cancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        cancel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancel2MouseReleased(evt);
            }
        });
        about.add(cancel2);
        cancel2.setBounds(620, 530, 160, 43);

        jLabel9.setBackground(new java.awt.Color(235, 235, 235));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/bgabout.png"))); // NOI18N
        about.add(jLabel9);
        jLabel9.setBounds(0, 0, 1040, 600);

        getContentPane().add(about);
        about.setBounds(240, 100, 1040, 630);

        pw.setBackground(new java.awt.Color(235, 235, 235));
        pw.setLayout(null);

        txtpwlama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtpwlama.setForeground(new java.awt.Color(48, 56, 65));
        txtpwlama.setBorder(null);
        pw.add(txtpwlama);
        txtpwlama.setBounds(270, 227, 500, 25);

        txtpw.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtpw.setForeground(new java.awt.Color(48, 56, 65));
        txtpw.setBorder(null);
        pw.add(txtpw);
        txtpw.setBounds(270, 300, 500, 25);

        txtpwbaru.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtpwbaru.setForeground(new java.awt.Color(48, 56, 65));
        txtpwbaru.setBorder(null);
        pw.add(txtpwbaru);
        txtpwbaru.setBounds(270, 370, 500, 25);

        cancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        cancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancel1MouseReleased(evt);
            }
        });
        pw.add(cancel1);
        cancel1.setBounds(460, 450, 160, 43);

        txtid1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid1.setForeground(new java.awt.Color(48, 56, 65));
        txtid1.setText("   ");
        pw.add(txtid1);
        txtid1.setBounds(270, 190, 500, 27);

        okepw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png"))); // NOI18N
        okepw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okepwMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okepwMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okepwMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okepwMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okepwMouseReleased(evt);
            }
        });
        pw.add(okepw);
        okepw.setBounds(630, 450, 160, 43);

        jLabel8.setBackground(new java.awt.Color(235, 235, 235));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/bgpw.png"))); // NOI18N
        pw.add(jLabel8);
        jLabel8.setBounds(0, 0, 1040, 600);

        getContentPane().add(pw);
        pw.setBounds(240, 100, 1040, 630);

        profile.setBackground(new java.awt.Color(235, 235, 235));
        profile.setLayout(null);
        profile.add(gambar);
        gambar.setBounds(280, 40, 75, 100);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtalamat.setColumns(5);
        txtalamat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtalamat.setLineWrap(true);
        txtalamat.setRows(5);
        jScrollPane3.setViewportView(txtalamat);

        profile.add(jScrollPane3);
        jScrollPane3.setBounds(270, 410, 500, 90);

        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelMouseReleased(evt);
            }
        });
        profile.add(cancel);
        cancel.setBounds(450, 530, 160, 43);

        upfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/uppoto.png"))); // NOI18N
        upfoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                upfotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                upfotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                upfotoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                upfotoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                upfotoMouseReleased(evt);
            }
        });
        profile.add(upfoto);
        upfoto.setBounds(380, 90, 160, 44);

        txtid.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(48, 56, 65));
        txtid.setText("   ");
        profile.add(txtid);
        txtid.setBounds(270, 190, 500, 27);

        tambahkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png"))); // NOI18N
        tambahkar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahkarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahkarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tambahkarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tambahkarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tambahkarMouseReleased(evt);
            }
        });
        profile.add(tambahkar);
        tambahkar.setBounds(620, 530, 160, 43);

        txtnomor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnomor.setForeground(new java.awt.Color(48, 56, 65));
        txtnomor.setBorder(null);
        txtnomor.setPreferredSize(new java.awt.Dimension(0, 20));
        profile.add(txtnomor);
        txtnomor.setBounds(270, 335, 500, 25);

        txtnama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnama.setForeground(new java.awt.Color(48, 56, 65));
        txtnama.setBorder(null);
        txtnama.setPreferredSize(new java.awt.Dimension(0, 20));
        profile.add(txtnama);
        txtnama.setBounds(270, 263, 490, 25);

        jLabel7.setBackground(new java.awt.Color(235, 235, 235));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/bgpop.png"))); // NOI18N
        profile.add(jLabel7);
        jLabel7.setBounds(0, 0, 1040, 600);

        getContentPane().add(profile);
        profile.setBounds(240, 100, 1040, 630);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jenispane.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jenispane.setForeground(new java.awt.Color(48, 56, 65));
        jenispane.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jenispane);
        jenispane.setBounds(300, 300, 930, 30);

        namapane.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        namapane.setForeground(new java.awt.Color(48, 56, 65));
        namapane.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(namapane);
        namapane.setBounds(300, 270, 930, 40);

        tentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-2.png"))); // NOI18N
        tentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tentangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tentangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tentangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tentangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tentangMouseReleased(evt);
            }
        });
        jPanel1.add(tentang);
        tentang.setBounds(290, 560, 940, 43);

        editprof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-1.png"))); // NOI18N
        editprof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editprofMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editprofMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editprofMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editprofMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editprofMouseReleased(evt);
            }
        });
        jPanel1.add(editprof);
        editprof.setBounds(290, 380, 940, 43);

        gantipw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action.png"))); // NOI18N
        gantipw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gantipwMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gantipwMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gantipwMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gantipwMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                gantipwMouseReleased(evt);
            }
        });
        jPanel1.add(gantipw);
        gantipw.setBounds(290, 440, 940, 43);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/bgsett.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(270, 110, 990, 589);

        imagepane.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/default (1).png"))); // NOI18N
        jPanel1.add(imagepane);
        imagepane.setBounds(728, 160, 75, 100);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 750);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1280, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseEntered
   // TODO add your handling code here:
    transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/transaksi-hover.png")));     
    }//GEN-LAST:event_transaksiMouseEntered

    private void transaksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseExited
        // TODO add your handling code here:
transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/transaksi.png")));   
    }//GEN-LAST:event_transaksiMouseExited

    private void transaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMousePressed
        // TODO add your handling code here:
    transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/transaksi-clicked.png"))); 
    }//GEN-LAST:event_transaksiMousePressed

    private void gudangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseEntered
        // TODO add your handling code here:
gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/gudang-hover.png"))); 
    }//GEN-LAST:event_gudangMouseEntered

    private void gudangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseExited
        // TODO add your handling code here:
gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/gudang.png"))); 
    }//GEN-LAST:event_gudangMouseExited

    private void gudangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMousePressed
        // TODO add your handling code here:
    gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/gudang-clicked.png"))); 
    }//GEN-LAST:event_gudangMousePressed

    private void transaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseReleased
        // TODO add your handling code here:
transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/transaksi.png")));   
    }//GEN-LAST:event_transaksiMouseReleased

    private void gudangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseReleased
        // TODO add your handling code here:
gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/gudang.png"))); 
    }//GEN-LAST:event_gudangMouseReleased

    private void riwayatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseEntered
        // TODO add your handling code here:
    riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/riwayat-hover.png")));
    }//GEN-LAST:event_riwayatMouseEntered

    private void riwayatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseExited
        // TODO add your handling code here:
    riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/riwayat.png")));
    }//GEN-LAST:event_riwayatMouseExited

    private void riwayatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMousePressed
        // TODO add your handling code here:
    riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/riwayat-clicked.png")));
    }//GEN-LAST:event_riwayatMousePressed

    private void riwayatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseReleased
        // TODO add your handling code here:
    riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/riwayat.png")));
    }//GEN-LAST:event_riwayatMouseReleased

    private void pengaturanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pengaturanMouseEntered

    private void pengaturanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseExited
        // TODO add your handling code here:
 
    }//GEN-LAST:event_pengaturanMouseExited

    private void pengaturanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pengaturanMousePressed

    private void pengaturanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pengaturanMouseReleased

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        // TODO add your handling code here:
    logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/logout-hover.png")));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        // TODO add your handling code here:
logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/logout.png")));
    }//GEN-LAST:event_logoutMouseExited

    private void logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMousePressed
        // TODO add your handling code here:
    logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/logout-hover.png")));
    }//GEN-LAST:event_logoutMousePressed

    private void logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseReleased
        // TODO add your handling code here:
logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/logout.png")));
    }//GEN-LAST:event_logoutMouseReleased

    private void dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseEntered
        // TODO add your handling code here:
dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard-hover.png")));
    }//GEN-LAST:event_dashboardMouseEntered

    private void dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseExited
        // TODO add your handling code here:
dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard.png")));
    }//GEN-LAST:event_dashboardMouseExited

    private void dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMousePressed
        // TODO add your handling code here:
dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard-clicked.png")));
    }//GEN-LAST:event_dashboardMousePressed

    private void dashboardMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseReleased
        // TODO add your handling code here:
dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard.png")));
    }//GEN-LAST:event_dashboardMouseReleased

    private void transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseClicked
        // TODO add your handling code here:
                    TransaksiJualkar transaksikar = new TransaksiJualkar();
                    transaksikar.nama.setText(nama.getText());
                    transaksikar.setVisible(true);
                    this.dispose();
    }//GEN-LAST:event_transaksiMouseClicked

    private void riwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseClicked
        // TODO add your handling code here:
RiwayatJualkar riwayatjuakar = new RiwayatJualkar();
riwayatjuakar.nama.setText(nama.getText());
riwayatjuakar.setVisible(true);
this.dispose();
    }//GEN-LAST:event_riwayatMouseClicked

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        // TODO add your handling code here:
        profile.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
        // TODO add your handling code here:
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
        // TODO add your handling code here:
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_cancelMouseExited

    private void cancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMousePressed
        // TODO add your handling code here:
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_cancelMousePressed

    private void cancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseReleased
        // TODO add your handling code here:
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_cancelMouseReleased

    private void tambahkarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahkarMouseClicked
        // TODO add your handling code here:
            String hp=txtnomor.getText();
        if (hp.matches("^[0-9]*") && hp.length()==12){
try {
String newPath = "C:\\toko-berkah\\";
File directory = new File(newPath);
if(!directory.exists()){
    directory.mkdirs();
}

File sourceFile = null;
File destinationFile=null;
String extension = filename.substring(filename.lastIndexOf(".") + 1);
sourceFile = new File(filename);
destinationFile = new File(newPath+nama.getText()+"."+extension);
Files.deleteIfExists(destinationFile.toPath());
Files.copy(sourceFile.toPath(),destinationFile.toPath());
System.out.println(destinationFile);
        try {
            String sql ="UPDATE pengguna SET nama_pengguna = '"+txtnama.getText()
                            +"', alamat_pengguna = '"+txtalamat.getText()
                            +"', telp_pengguna = '" +txtnomor.getText()
                            +"', img = 'C:\\\\toko-berkah\\\\" +nama.getText()+"."+extension
                            +"' WHERE pengguna.id_pengguna = '"+txtid.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Berhasil !","Profile berhasil diubah");
            setprofile();
        } catch (Exception e) {
            panggilAlertMerah("Edit Gagal !",e.getMessage());
        } 
    }catch (IOException ex){
    ex.printStackTrace();
    }
        } else {
            panggilAlertMerah("Edit Gagal !","Nomor Telepon salah");
        }
    }//GEN-LAST:event_tambahkarMouseClicked

    private void tambahkarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahkarMouseEntered
        // TODO add your handling code here:
        tambahkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-hover.png")));
    }//GEN-LAST:event_tambahkarMouseEntered

    private void tambahkarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahkarMouseExited
        // TODO add your handling code here:
        tambahkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_tambahkarMouseExited

    private void tambahkarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahkarMousePressed
        // TODO add your handling code here:
        tambahkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-clicked.png")));
    }//GEN-LAST:event_tambahkarMousePressed

    private void tambahkarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahkarMouseReleased
        // TODO add your handling code here:
        tambahkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_tambahkarMouseReleased

    private void upfotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upfotoMouseClicked
        // TODO add your handling code here:
            JSystemFileChooser chooser= new JSystemFileChooser();
            chooser.addChoosableFileFilter(new Settingkar.ImageFilter());
            chooser.setAcceptAllFileFilterUsed(false);
chooser.showOpenDialog(null);
File f = chooser.getSelectedFile();
//jLabel1.setIcon(new ImageIcon(f.toString()));
filename = f.getAbsolutePath();
try{
      BufferedImage img = null;
      img = ImageIO.read(new File(f.getAbsolutePath()));
      Image img1=img.getScaledInstance(gambar.getWidth(),gambar.getHeight(),Image.SCALE_AREA_AVERAGING);
      format = new ImageIcon(img1);
      gambar.setIcon(format);
}catch(Exception e){
}
    }//GEN-LAST:event_upfotoMouseClicked

    private void upfotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upfotoMouseEntered
        // TODO add your handling code here:
upfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/uppoto-hover.png")));
    }//GEN-LAST:event_upfotoMouseEntered

    private void upfotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upfotoMouseExited
        // TODO add your handling code here:
upfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/uppoto.png")));
    }//GEN-LAST:event_upfotoMouseExited

    private void upfotoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upfotoMousePressed
        // TODO add your handling code here:
upfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/uppoto-clicked.png")));
    }//GEN-LAST:event_upfotoMousePressed

    private void upfotoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upfotoMouseReleased
        // TODO add your handling code here:
upfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/uppoto.png")));
    }//GEN-LAST:event_upfotoMouseReleased

    private void namaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_namaAncestorAdded
        // TODO add your handling code here:
        setprofile();
    }//GEN-LAST:event_namaAncestorAdded

    private void cancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseClicked
        // TODO add your handling code here:
pw.setVisible(false);
jPanel1.setVisible(true);
    }//GEN-LAST:event_cancel1MouseClicked

    private void cancel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseEntered
        // TODO add your handling code here:
cancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_cancel1MouseEntered

    private void cancel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseExited
        // TODO add your handling code here:
cancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_cancel1MouseExited

    private void cancel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MousePressed
        // TODO add your handling code here:
cancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_cancel1MousePressed

    private void cancel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseReleased
        // TODO add your handling code here:
cancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_cancel1MouseReleased

    private void okepwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okepwMouseClicked
        // TODO add your handling code here:
String pwq = "";
 try {
            String sql = "SELECT password"
                    + " FROM pengguna"
                    + " WHERE id_pengguna='" + nama.getText() +"' ;" ;
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            pwq = rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
if (txtpwlama.getText().equals(pwq)) {
String pwc = txtpw.getText();
if (pwc.length() > 8) {
            panggilAlertMerah("Gagal!","Password lebih dari 8 karakter");
} else {
if (!txtpwbaru.getText().equals(txtpw.getText())) {
    panggilAlertMerah("Gagal!", "Password baru tidak sama");
} else {
        try {
            String sql ="UPDATE pengguna SET password = '"+txtpw.getText()
                            +"' WHERE pengguna.id_pengguna = '"+nama.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Berhasil!","Password berhasil diubah");
txtpwlama.setText("");
txtpw.setText("");
txtpw.setText("");
pw.setVisible(false);
jPanel1.setVisible(true);
        } catch (Exception e) {
            panggilAlertMerah("Gagal!",e.getMessage());
        }
}
}
} else  {
            panggilAlertMerah("Gagal!","Password lama salah");
}
    }//GEN-LAST:event_okepwMouseClicked

    private void okepwMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okepwMouseEntered
        // TODO add your handling code here:
okepw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-hover.png")));
    }//GEN-LAST:event_okepwMouseEntered

    private void okepwMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okepwMouseExited
        // TODO add your handling code here:
okepw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_okepwMouseExited

    private void okepwMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okepwMousePressed
        // TODO add your handling code here:
okepw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-clicked.png")));
    }//GEN-LAST:event_okepwMousePressed

    private void okepwMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okepwMouseReleased
        // TODO add your handling code here:
okepw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_okepwMouseReleased

    private void editprofMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editprofMouseEntered
        // TODO add your handling code here:
editprof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-1-hover.png")));
    }//GEN-LAST:event_editprofMouseEntered

    private void editprofMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editprofMouseExited
        // TODO add your handling code here:
editprof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-1.png")));
    }//GEN-LAST:event_editprofMouseExited

    private void editprofMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editprofMousePressed
        // TODO add your handling code here:
editprof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-1-clicked.png")));
    }//GEN-LAST:event_editprofMousePressed

    private void editprofMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editprofMouseReleased
        // TODO add your handling code here:
editprof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-1.png")));
    }//GEN-LAST:event_editprofMouseReleased

    private void gantipwMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gantipwMouseEntered
        // TODO add your handling code here:
gantipw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-hover.png")));
    }//GEN-LAST:event_gantipwMouseEntered

    private void gantipwMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gantipwMouseExited
        // TODO add your handling code here:
gantipw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action.png")));
    }//GEN-LAST:event_gantipwMouseExited

    private void gantipwMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gantipwMousePressed
        // TODO add your handling code here:
gantipw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-clicked.png")));
    }//GEN-LAST:event_gantipwMousePressed

    private void gantipwMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gantipwMouseReleased
        // TODO add your handling code here:
gantipw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action.png")));
    }//GEN-LAST:event_gantipwMouseReleased

    private void tentangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tentangMouseEntered
        // TODO add your handling code here:
tentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-2-hover.png")));
    }//GEN-LAST:event_tentangMouseEntered

    private void tentangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tentangMouseExited
        // TODO add your handling code here:
tentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-2.png")));
    }//GEN-LAST:event_tentangMouseExited

    private void tentangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tentangMousePressed
        // TODO add your handling code here:
tentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-2-clicked.png")));
    }//GEN-LAST:event_tentangMousePressed

    private void tentangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tentangMouseReleased
        // TODO add your handling code here:
tentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/setting/btn-action-2.png")));
    }//GEN-LAST:event_tentangMouseReleased

    private void editprofMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editprofMouseClicked
        // TODO add your handling code here:
txtid.setText(nama.getText());
gambar.setIcon(image.getIcon());
            try {
            String sql = "SELECT nama_pengguna, telp_pengguna, alamat_pengguna"
                    + " FROM pengguna"
                    + " WHERE id_pengguna='" + nama.getText() +"' ;" ;
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            txtnama.setText(rs.getString(1));
            txtnomor.setText(rs.getString(2));
            txtalamat.setText(rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
profile.setVisible(true);
jPanel1.setVisible(false);
    }//GEN-LAST:event_editprofMouseClicked

    private void gantipwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gantipwMouseClicked
        // TODO add your handling code here:
pw.setVisible(true);
jPanel1.setVisible(false);
    }//GEN-LAST:event_gantipwMouseClicked

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        // TODO add your handling code here:
Dashboardkar dashboardkar = new Dashboardkar();
dashboardkar.nama.setText(nama.getText());
dashboardkar.setVisible(true);
this.dispose();
    }//GEN-LAST:event_dashboardMouseClicked

    private void gudangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseClicked
        // TODO add your handling code here:
                    Gudangkar gudangkar = new Gudangkar();
                    gudangkar.nama.setText(nama.getText());
                    gudangkar.setVisible(true);
                    this.dispose();
    }//GEN-LAST:event_gudangMouseClicked

    private void MenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
        AlertPertanyaan obj = new AlertPertanyaan(this);
        obj.showMessage("Logout !", "Apakah Anda Ingin Logout ?");
        if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
            Login login = new Login();
login.setVisible(true);
this.dispose();
        } else {
            
        }
    }//GEN-LAST:event_logoutMouseClicked

    private void cancel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel2MouseClicked
        // TODO add your handling code here:
        about.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_cancel2MouseClicked

    private void cancel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel2MouseEntered
        // TODO add your handling code here:
cancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_cancel2MouseEntered

    private void cancel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel2MouseExited
        // TODO add your handling code here:
cancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_cancel2MouseExited

    private void cancel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel2MousePressed
        // TODO add your handling code here:
cancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_cancel2MousePressed

    private void cancel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel2MouseReleased
        // TODO add your handling code here:
cancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_cancel2MouseReleased

    private void tentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tentangMouseClicked
        // TODO add your handling code here:
        jPanel1.setVisible(false);
        about.setVisible(true);
    }//GEN-LAST:event_tentangMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Settingkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settingkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settingkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settingkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settingkar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JPanel about;
    private javax.swing.JLabel cancel;
    private javax.swing.JLabel cancel1;
    private javax.swing.JLabel cancel2;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel editprof;
    private javax.swing.JLabel gambar;
    private javax.swing.JLabel gantipw;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel header;
    private javax.swing.JLabel image;
    private javax.swing.JLabel imagepane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jenis;
    private javax.swing.JLabel jenispane;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private javax.swing.JLabel namapane;
    private javax.swing.JLabel okepw;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JPanel profile;
    private javax.swing.JPanel pw;
    private javax.swing.JLabel riwayat;
    private javax.swing.JLabel tambahkar;
    private javax.swing.JLabel tentang;
    private javax.swing.JLabel transaksi;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtid1;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnomor;
    private javax.swing.JPasswordField txtpw;
    private javax.swing.JPasswordField txtpwbaru;
    private javax.swing.JPasswordField txtpwlama;
    private javax.swing.JLabel upfoto;
    // End of variables declaration//GEN-END:variables
}
