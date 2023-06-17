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
import java.sql.Connection;
import java.sql.SQLException;
import javaswingdev.message.AlertBerhasil;

import javaswingdev.message.AlertGagal;
import javaswingdev.message.AlertPertanyaan;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yogan
 */
public class Karyawan extends javax.swing.JFrame {
private ImageIcon format = null;
    /**
     * Creates new form Karyawan
     */
    public Karyawan() {
        initComponents();
        tbl_kar();
        Cursor();
        setFavIcon();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        popupkar.setVisible(false);
        carikar.setForeground(new java.awt.Color(221,221,221));
        carikar.setText("Cari Nama Karyawan");
    }

private void setFavIcon(){
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/main/img/Logoapp.png")));
}

    void Cursor(){
        dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gudang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        retur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tmbhkar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editkar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapuskar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

            String imgsql = rs.getString(2);
//admin
            try{
            BufferedImage img = null;
            img = ImageIO.read(new File(imgsql));
            Image img1=img.getScaledInstance(image.getWidth(),image.getHeight(),Image.SCALE_SMOOTH);
            format = new ImageIcon(img1);
            image.setIcon(format);
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
    private void kosong() {
        txtid.setText("");
        txtnama.setText(null);
        txtalamat.setText(null);
        txtnomor.setText(null);
        txtpass.setText(null);
        comb.setSelectedItem(null);
//        txtcari.setText(null);
    }

    private void pkosong() {
        ptxtid.setText("");
        ptxtnama.setText(null);
        ptxtalamat.setText(null);
        ptxtnomor.setText(null);
        ptxtpass.setText(null);
        pcomb.setSelectedItem(null);
//        txtcari.setText(null);
    }


    void tbl_kar(){
    table_karyawan.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Karyawan");
    model.addColumn("Nama");
    model.addColumn("No. Hp");
    model.addColumn("Alamat");
    model.addColumn("Jenis Akun");
    model.addColumn("Password");
        try {
            String sql = "select * from pengguna ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_pengguna"),
                            res.getString("nama_pengguna"),
                            res.getString("telp_pengguna"),
                            res.getString("alamat_pengguna"),
                            res.getString("jenis_pengguna"),
                            res.getString("password")
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
table_karyawan.setModel(model);
    }

void panggilAlertHijau(String Title,String message){
            AlertBerhasil obj2 = new AlertBerhasil(this);
            obj2.showMessage(Title, message);
}
void panggilAlertMerah(String Title, String message){
            AlertGagal obj3 = new AlertGagal(this);
            obj3.showMessage(Title, message);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jenis = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        karyawan = new javax.swing.JLabel();
        retur = new javax.swing.JLabel();
        gudang = new javax.swing.JLabel();
        transaksi = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        pengaturan = new javax.swing.JLabel();
        riwayat = new javax.swing.JLabel();
        Menu = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        popupkar = new javax.swing.JPanel();
        ptxtid = new javax.swing.JTextField();
        pcomb = new combo_suggestion.ComboBoxSuggestion();
        cancel = new javax.swing.JLabel();
        tambahkar = new javax.swing.JLabel();
        ptxtnomor = new javax.swing.JTextField();
        ptxtalamat = new javax.swing.JTextField();
        ptxtpass = new javax.swing.JTextField();
        ptxtnama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        carikar = new javax.swing.JTextField();
        txtnomor = new javax.swing.JTextField();
        txtid = new javax.swing.JLabel();
        comb = new combo_suggestion.ComboBoxSuggestion();
        txtalamat = new javax.swing.JTextField();
        txtpass = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_karyawan = new tabledark.TableDark();
        tmbhkar = new javax.swing.JLabel();
        hapuskar = new javax.swing.JLabel();
        editkar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko-Berkah Karyawan");
        setMinimumSize(new java.awt.Dimension(1280, 750));
        setPreferredSize(new java.awt.Dimension(1280, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(null);

        jenis.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jenis.setForeground(new java.awt.Color(48, 56, 65));
        getContentPane().add(jenis);
        jenis.setBounds(1030, 60, 240, 30);

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

        karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/karyawan-clicked.png"))); // NOI18N
        karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                karyawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                karyawanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                karyawanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                karyawanMouseReleased(evt);
            }
        });
        getContentPane().add(karyawan);
        karyawan.setBounds(21, 415, 200, 30);

        retur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/retur.png"))); // NOI18N
        retur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                returMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                returMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                returMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                returMouseReleased(evt);
            }
        });
        getContentPane().add(retur);
        retur.setBounds(21, 320, 200, 30);

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
        logout.setBounds(21, 494, 200, 30);

        pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/pengaturan.png"))); // NOI18N
        pengaturan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pengaturanMouseClicked(evt);
            }
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
        pengaturan.setBounds(21, 454, 200, 30);

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
        getContentPane().add(Menu);
        Menu.setBounds(0, 100, 218, 630);

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/Header.png"))); // NOI18N
        header.setAlignmentY(0.0F);
        getContentPane().add(header);
        header.setBounds(0, 0, 1280, 110);

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/default (1).png"))); // NOI18N
        getContentPane().add(image);
        image.setBounds(938, 10, 75, 90);

        popupkar.setBackground(new java.awt.Color(235, 235, 235));
        popupkar.setLayout(null);

        ptxtid.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ptxtid.setForeground(new java.awt.Color(48, 56, 65));
        ptxtid.setBorder(null);
        ptxtid.setPreferredSize(new java.awt.Dimension(0, 20));
        popupkar.add(ptxtid);
        ptxtid.setBounds(270, 112, 490, 25);

        pcomb.setEditable(false);
        pcomb.setForeground(new java.awt.Color(48, 56, 65));
        pcomb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Karyawan" }));
        pcomb.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pcomb.setMinimumSize(new java.awt.Dimension(221, 20));
        pcomb.setPreferredSize(new java.awt.Dimension(221, 20));
        popupkar.add(pcomb);
        pcomb.setBounds(265, 410, 510, 30);

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
        popupkar.add(cancel);
        cancel.setBounds(450, 530, 160, 43);

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
        popupkar.add(tambahkar);
        tambahkar.setBounds(620, 530, 160, 43);

        ptxtnomor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ptxtnomor.setForeground(new java.awt.Color(48, 56, 65));
        ptxtnomor.setBorder(null);
        ptxtnomor.setPreferredSize(new java.awt.Dimension(0, 20));
        popupkar.add(ptxtnomor);
        ptxtnomor.setBounds(270, 343, 490, 25);

        ptxtalamat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ptxtalamat.setForeground(new java.awt.Color(48, 56, 65));
        ptxtalamat.setBorder(null);
        ptxtalamat.setPreferredSize(new java.awt.Dimension(0, 20));
        popupkar.add(ptxtalamat);
        ptxtalamat.setBounds(270, 482, 490, 25);

        ptxtpass.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ptxtpass.setForeground(new java.awt.Color(48, 56, 65));
        ptxtpass.setBorder(null);
        ptxtpass.setPreferredSize(new java.awt.Dimension(0, 20));
        popupkar.add(ptxtpass);
        ptxtpass.setBounds(270, 180, 490, 25);

        ptxtnama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ptxtnama.setForeground(new java.awt.Color(48, 56, 65));
        ptxtnama.setBorder(null);
        ptxtnama.setPreferredSize(new java.awt.Dimension(0, 20));
        popupkar.add(ptxtnama);
        ptxtnama.setBounds(270, 273, 490, 25);

        jLabel7.setBackground(new java.awt.Color(235, 235, 235));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/popup.png"))); // NOI18N
        popupkar.add(jLabel7);
        jLabel7.setBounds(0, 0, 1040, 600);

        getContentPane().add(popupkar);
        popupkar.setBounds(240, 100, 1040, 650);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        carikar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        carikar.setBorder(null);
        carikar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                carikarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                carikarFocusLost(evt);
            }
        });
        carikar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carikarKeyReleased(evt);
            }
        });
        jPanel1.add(carikar);
        carikar.setBounds(340, 128, 490, 30);

        txtnomor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnomor.setForeground(new java.awt.Color(48, 56, 65));
        txtnomor.setBorder(null);
        txtnomor.setPreferredSize(new java.awt.Dimension(0, 20));
        jPanel1.add(txtnomor);
        txtnomor.setBounds(930, 415, 300, 25);

        txtid.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(txtid);
        txtid.setBounds(930, 230, 300, 27);

        comb.setEditable(false);
        comb.setForeground(new java.awt.Color(48, 56, 65));
        comb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Karyawan" }));
        comb.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comb.setMinimumSize(new java.awt.Dimension(221, 20));
        comb.setPreferredSize(new java.awt.Dimension(221, 20));
        jPanel1.add(comb);
        comb.setBounds(925, 480, 315, 30);

        txtalamat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtalamat.setForeground(new java.awt.Color(48, 56, 65));
        txtalamat.setBorder(null);
        txtalamat.setPreferredSize(new java.awt.Dimension(0, 20));
        jPanel1.add(txtalamat);
        txtalamat.setBounds(930, 540, 300, 25);

        txtpass.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtpass.setForeground(new java.awt.Color(48, 56, 65));
        txtpass.setBorder(null);
        txtpass.setPreferredSize(new java.awt.Dimension(0, 20));
        jPanel1.add(txtpass);
        txtpass.setBounds(930, 290, 300, 25);

        txtnama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnama.setForeground(new java.awt.Color(48, 56, 65));
        txtnama.setBorder(null);
        txtnama.setPreferredSize(new java.awt.Dimension(0, 20));
        jPanel1.add(txtnama);
        txtnama.setBounds(930, 353, 300, 25);

        jScrollPane1.setBorder(null);

        table_karyawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_karyawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_karyawan);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(290, 200, 590, 470);

        tmbhkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-tambah.png"))); // NOI18N
        tmbhkar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmbhkarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tmbhkarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tmbhkarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmbhkarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tmbhkarMouseReleased(evt);
            }
        });
        jPanel1.add(tmbhkar);
        tmbhkar.setBounds(1030, 120, 220, 50);

        hapuskar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-hapus.png"))); // NOI18N
        hapuskar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapuskarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapuskarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hapuskarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hapuskarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hapuskarMouseReleased(evt);
            }
        });
        jPanel1.add(hapuskar);
        hapuskar.setBounds(920, 630, 330, 50);

        editkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-edit.png"))); // NOI18N
        editkar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editkarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editkarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editkarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editkarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editkarMouseReleased(evt);
            }
        });
        jPanel1.add(editkar);
        editkar.setBounds(920, 580, 330, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/main.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(270, 110, 990, 589);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 750);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1280, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editkarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editkarMouseClicked
        // TODO add your handling code here:
        if (txtnama.getText().equals("")) {
            panggilAlertMerah("Edit Gagal !","pilih dahulu Karyawan yang ingin Edit");
        } else {
            String hp=txtnomor.getText();
        if (hp.matches("^[0-9]*") && hp.length()==12){
        try {
            String sql ="UPDATE pengguna SET nama_pengguna = '"+txtnama.getText()
                            +"', alamat_pengguna = '"
                            +txtalamat.getText()
                            +"', telp_pengguna = '" +txtnomor.getText()
                            + "', jenis_pengguna = '"+comb.getSelectedItem()
                            +"', password = '"+txtpass.getText()
                            +"' WHERE pengguna.id_pengguna = '"+txtid.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Edit Berhasil !","Data User dengan ID "+txtid.getText()+" berhasil diubah");
        tbl_kar();
        kosong();
        } catch (Exception e) {
            panggilAlertMerah("Edit Gagal !",e.getMessage());
        } 
        } else {
            panggilAlertMerah("Edit Gagal !","Nomor Telepon salah");

        }
        }
    }//GEN-LAST:event_editkarMouseClicked

    private void tmbhkarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhkarMouseEntered
        // TODO add your handling code here:
tmbhkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-tambah-hover.png")));
    }//GEN-LAST:event_tmbhkarMouseEntered

    private void tmbhkarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhkarMouseExited
        // TODO add your handling code here:
tmbhkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-tambah.png")));
    }//GEN-LAST:event_tmbhkarMouseExited

    private void tmbhkarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhkarMousePressed
        // TODO add your handling code here:
tmbhkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-tambah-clicked.png")));
    }//GEN-LAST:event_tmbhkarMousePressed

    private void tmbhkarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhkarMouseReleased
        // TODO add your handling code here:
tmbhkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-tambah.png")));
    }//GEN-LAST:event_tmbhkarMouseReleased

    private void editkarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editkarMouseEntered
        // TODO add your handling code here:
editkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-edit-hover.png")));
    }//GEN-LAST:event_editkarMouseEntered

    private void editkarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editkarMouseExited
        // TODO add your handling code here:
editkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-edit.png")));

    }//GEN-LAST:event_editkarMouseExited

    private void editkarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editkarMousePressed
        // TODO add your handling code here:
editkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-edit-clicked.png")));
    }//GEN-LAST:event_editkarMousePressed

    private void editkarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editkarMouseReleased
        // TODO add your handling code here:
editkar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-edit.png")));
    }//GEN-LAST:event_editkarMouseReleased

    private void hapuskarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuskarMouseEntered
        // TODO add your handling code here:
hapuskar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-hapus-hover.png")));
    }//GEN-LAST:event_hapuskarMouseEntered

    private void hapuskarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuskarMouseExited
        // TODO add your handling code here:
hapuskar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-hapus.png")));
    }//GEN-LAST:event_hapuskarMouseExited

    private void hapuskarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuskarMousePressed
        // TODO add your handling code here:
hapuskar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-hapus-clicked.png")));
    }//GEN-LAST:event_hapuskarMousePressed

    private void hapuskarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuskarMouseReleased
        // TODO add your handling code here:
hapuskar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Kar/btn-hapus.png")));
    }//GEN-LAST:event_hapuskarMouseReleased

    private void table_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_karyawanMouseClicked
        // TODO add your handling code here:
        int baris = table_karyawan.rowAtPoint(evt.getPoint());
        String id = table_karyawan.getValueAt(baris, 0).toString();
        txtid.setText(id);
        if (table_karyawan.getValueAt(baris, 1)==null) {
            txtnama.setText("");
        } else {
            txtnama.setText(table_karyawan.getValueAt(baris, 1).toString());
        }
        if (table_karyawan.getValueAt(baris, 3)==null) {
            txtalamat.setText("");
        } else {
            txtalamat.setText(table_karyawan.getValueAt(baris, 3).toString());
        }
        if (table_karyawan.getValueAt(baris, 2)==null) {
            txtnomor.setText("");
        } else {
            txtnomor.setText(table_karyawan.getValueAt(baris, 2).toString());
        }
        if (table_karyawan.getValueAt(baris, 4)==null) {
            comb.setSelectedItem(this);
        } else {
            comb.setSelectedItem(table_karyawan.getValueAt(baris, 4).toString());
        }
        if (table_karyawan.getValueAt(baris, 5)==null) {
            txtpass.setText("");
        } else {
            txtpass.setText(table_karyawan.getValueAt(baris, 5).toString());
        }
    }//GEN-LAST:event_table_karyawanMouseClicked

    private void carikarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carikarFocusGained
        // TODO add your handling code here:
if (carikar.getText().equals("Cari Nama Karyawan")) {
carikar.setForeground(new java.awt.Color(51,51,51));
carikar.setText("");
} else {
}
    }//GEN-LAST:event_carikarFocusGained

    private void carikarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carikarFocusLost
        // TODO add your handling code here:
if (carikar.getText().equals("")) {
carikar.setForeground(new java.awt.Color(221,221,221));
carikar.setText("Cari Nama Karyawan");
} else {
}
    }//GEN-LAST:event_carikarFocusLost

    private void carikarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carikarKeyReleased
        // TODO add your handling code here:
    table_karyawan.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Karyawan");
    model.addColumn("Nama");
    model.addColumn("No. Hp");
    model.addColumn("Alamat");
    model.addColumn("Jenis Akun");
    model.addColumn("Password");
        try {
            String sql = "select * from pengguna "
            + "where nama_pengguna like '%" + carikar.getText() + "%'";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_pengguna"),
                            res.getString("nama_pengguna"),
                            res.getString("telp_pengguna"),
                            res.getString("alamat_pengguna"),
                            res.getString("jenis_pengguna"),
                            res.getString("password")
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
table_karyawan.setModel(model);
    }//GEN-LAST:event_carikarKeyReleased

    private void tmbhkarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhkarMouseClicked
        // TODO add your handling code here:
popupkar.setVisible(true);
jPanel1.setVisible(false);
    }//GEN-LAST:event_tmbhkarMouseClicked

    private void karyawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseEntered
        // TODO add your handling code here:
      
    }//GEN-LAST:event_karyawanMouseEntered

    private void karyawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_karyawanMouseExited

    private void karyawanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_karyawanMousePressed

    private void karyawanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_karyawanMouseReleased

    private void returMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseEntered
        // TODO add your handling code here:
        retur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/retur-hover.png")));
    }//GEN-LAST:event_returMouseEntered

    private void returMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseExited
        // TODO add your handling code here:
        retur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/retur.png")));
    }//GEN-LAST:event_returMouseExited

    private void returMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMousePressed
        // TODO add your handling code here:
        retur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/retur-clicked.png")));
    }//GEN-LAST:event_returMousePressed

    private void returMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseReleased
        // TODO add your handling code here:
        retur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/retur.png")));
    }//GEN-LAST:event_returMouseReleased

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

    private void gudangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseReleased
        // TODO add your handling code here:
        gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/gudang.png")));
    }//GEN-LAST:event_gudangMouseReleased

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

    private void transaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseReleased
        // TODO add your handling code here:
        transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/transaksi.png")));
    }//GEN-LAST:event_transaksiMouseReleased

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

    private void pengaturanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseEntered
        // TODO add your handling code here:
        pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/pengaturan-hover.png")));
    }//GEN-LAST:event_pengaturanMouseEntered

    private void pengaturanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseExited
        // TODO add your handling code here:
        pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/pengaturan.png")));
    }//GEN-LAST:event_pengaturanMouseExited

    private void pengaturanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMousePressed
        // TODO add your handling code here:
        pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/pengaturan-clicked.png")));
    }//GEN-LAST:event_pengaturanMousePressed

    private void pengaturanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseReleased
        // TODO add your handling code here:
        pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/pengaturan.png")));
    }//GEN-LAST:event_pengaturanMouseReleased

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

    private void transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseClicked
        // TODO add your handling code here:
TransaksiJual transaksijual = new TransaksiJual();
transaksijual.nama.setText(nama.getText());
transaksijual.setVisible(true);
this.dispose();

    }//GEN-LAST:event_transaksiMouseClicked

    private void hapuskarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuskarMouseClicked
        // TODO add your handling code here:
        if (txtid.getText().equals("")) {
            panggilAlertMerah("Hapus Gagal !","pilih dahulu Karyawan yang ingin dihapus");
        } else { 
            if (txtid.getText().equals(nama.getText())) {
                panggilAlertMerah("Hapus Gagal !","Tidak dapat dihapus! \nAnda sedang login dengan user tersebut");
            } else {
                    AlertPertanyaan obj = new AlertPertanyaan(this);
        obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Karyawan Dengan ID \n"+txtid.getText()+"?");
        if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
             try {
            String sql ="DELETE FROM pengguna where id_pengguna='"+txtid.getText()+"'";
        java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Hapus Berhasil !","Data Karyawan dengan ID "+txtid.getText()+" berhasil dihapus");
        tbl_kar();
        kosong();
        } catch (Exception e) {
            panggilAlertHijau("Hapus Gagal !", e.getMessage());
        } 
       
            
        }

                
        }
        }
    }//GEN-LAST:event_hapuskarMouseClicked

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        // TODO add your handling code here:
popupkar.setVisible(false);
jPanel1.setVisible(true);
    }//GEN-LAST:event_cancelMouseClicked

    private void tambahkarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahkarMouseClicked
        // TODO add your handling code here:
        if (ptxtnama.getText().equals("")) {
            panggilAlertMerah("Tambah Gagal !","Id Karyawan Masih Kosong");
        } else {
            String idpengguna = "";
            try {
                String sql = "select * from pengguna " +
                "where id_pengguna = '"+ptxtid.getText()+"';";
                Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                res.next();
                idpengguna = res.getString("id_pengguna");
            } catch (SQLException ex) {
            }

            if (ptxtid.getText().equals(idpengguna)) {
                panggilAlertMerah("Tambah Gagal !","ID karyawan "+ptxtid.getText()+" sudah ada");

            } else {
                String pwc = txtpass.getText();
                if (pwc.length() > 8) {
            panggilAlertMerah("Gagal!","Password lebih dari 8 karakter");
            } else {
                String hp=ptxtnomor.getText();
                if (hp.matches("^[0-9]*") && hp.length()==12){
                    try {
                        String sql = "INSERT INTO pengguna (id_pengguna, nama_pengguna, alamat_pengguna, telp_pengguna, jenis_pengguna, password) "
                        + "Values ('" + ptxtid.getText() + "','"
                        + ptxtnama.getText() + "','" + ptxtalamat.getText() + "','" +
                        ptxtnomor.getText() + "','" + pcomb.getSelectedItem() + "','" + ptxtpass.getText() + "')";
                        java.sql.Connection conn=(Connection)Config.configDB();
                        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                        pst.execute();
                        panggilAlertHijau("Berhasil !","Data User dengan ID "+ptxtid.getText()+" berhasil ditambah");
                        tbl_kar();
                        pkosong();
                    } catch (Exception e) {
                        panggilAlertMerah("Tambah Gagal !","Penambahan Data gagal ");
                    }
                } else {
                    panggilAlertMerah("Tambah Gagal !","Nomor Telepon salah");
                }
            }
            }
        }
    }//GEN-LAST:event_tambahkarMouseClicked

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

    private void namaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_namaAncestorAdded
        // TODO add your handling code here:
        setprofile();
    }//GEN-LAST:event_namaAncestorAdded

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        // TODO add your handling code here:
Dashboard dashboard = new Dashboard();
dashboard.nama.setText(nama.getText());
dashboard.setVisible(true);
this.dispose();

    }//GEN-LAST:event_dashboardMouseClicked

    private void gudangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseClicked
        // TODO add your handling code here:
                    Gudang gudang = new Gudang();
                    gudang.nama.setText(nama.getText());
                    gudang.setVisible(true);
                    this.dispose();


    }//GEN-LAST:event_gudangMouseClicked

    private void riwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseClicked
        // TODO add your handling code here:
RiwayatJual riwayatjual = new RiwayatJual();
riwayatjual.nama.setText(nama.getText());
riwayatjual.setVisible(true);
this.dispose();

    }//GEN-LAST:event_riwayatMouseClicked

    private void returMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseClicked
        // TODO add your handling code here:
                    Retur retur = new Retur();
                    retur.nama.setText(nama.getText());
                    retur.setVisible(true);
                    this.dispose();

    }//GEN-LAST:event_returMouseClicked

    private void pengaturanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseClicked
        // TODO add your handling code here:
                    Setting setting = new Setting();
                    setting.nama.setText(nama.getText());
                    setting.setVisible(true);
                    this.dispose();

    }//GEN-LAST:event_pengaturanMouseClicked

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
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JLabel cancel;
    private javax.swing.JTextField carikar;
    private combo_suggestion.ComboBoxSuggestion comb;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel editkar;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel hapuskar;
    private javax.swing.JLabel header;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jenis;
    private javax.swing.JLabel karyawan;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private combo_suggestion.ComboBoxSuggestion pcomb;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JPanel popupkar;
    private javax.swing.JTextField ptxtalamat;
    private javax.swing.JTextField ptxtid;
    private javax.swing.JTextField ptxtnama;
    private javax.swing.JTextField ptxtnomor;
    private javax.swing.JTextField ptxtpass;
    private javax.swing.JLabel retur;
    private javax.swing.JLabel riwayat;
    public static tabledark.TableDark table_karyawan;
    private javax.swing.JLabel tambahkar;
    private javax.swing.JLabel tmbhkar;
    private javax.swing.JLabel transaksi;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JLabel txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnomor;
    private javax.swing.JTextField txtpass;
    // End of variables declaration//GEN-END:variables
}
