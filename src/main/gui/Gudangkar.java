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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class Gudangkar extends javax.swing.JFrame {
String autosup;
private ImageIcon format = null;
    /**
     * Creates new form Karyawan
     */
    public Gudangkar() {
        initComponents();
        table_bar();
        setJmlbar();
        Cursor();
        setFavIcon();
        setJtgl();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        caribar.setForeground(new java.awt.Color(221,221,221));
        caribar.setText("Cari Nama Barang");
    }

private void setFavIcon(){
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/main/img/Logoapp.png")));
}

void panggilAlertHijau(String Title,String message){
            AlertBerhasil obj2 = new AlertBerhasil(this);
            obj2.showMessage(Title, message);
}
void panggilAlertMerah(String Title, String message){
            AlertGagal obj3 = new AlertGagal(this);
            obj3.showMessage(Title, message);
}

    void setJtgl() {
        String mm = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime tanggal = LocalDateTime.now();
        String a = (dtf.format(tanggal));
        DateTimeFormatter dtff = DateTimeFormatter.ofPattern("dd");
        LocalDateTime tanggall = LocalDateTime.now();
        String aa = (dtff.format(tanggall));
        DateTimeFormatter dtfff = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime tanggalll = LocalDateTime.now();
        String yyyy = (dtfff.format(tanggalll));
        int b = Integer.parseInt(a);
        switch (b) {
            case 1:
                mm = ("Januari");
                break;
            case 2:
                mm = ("Februari");
                break;
            case 3:
                mm = ("Maret");
                break;
            case 4:
                mm = ("April");
                break;
            case 5:
                mm = ("Mei");
                break;
            case 6:
                mm = ("Juni");
                break;
            case 7:
                mm = ("Juli");
                break;
            case 8:
                mm = ("Agustus");
                break;
            case 9:
                mm = ("September");
                break;
            case 10:
                mm = ("Oktober");
                break;
            case 11:
                mm = ("November");
                break;
            case 12:
                mm = ("Desember");
                break;
        }
        jtgl.setText(aa + " " + mm + " " + yyyy);
    }

    void Cursor(){
        dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

    void kosongbar() {
        labelkdbar.setText("");
        labelharga.setText(null);
        labelnamabar.setText(null);
        labelqty.setText(null);
        rtr.setText(null);
    }



    private void setJmlbar() {
        try {
            String sql = "SELECT count(id_barang) as total from barang ;";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            jmlbar.setText(rs.getString("total"));

        } catch (SQLException e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
        }
    }

    void table_bar(){
    table_bar.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kategori");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Harga Beli");
    model.addColumn("Harga Jual");
        try {
            String sql = "select barang.id_barang, barang.nama_barang, kategori.jenis, jumlah, satuan.satuan, barang.harga_beli, barang.harga_jual "
                    + "from barang join kategori on barang.id_kategori = kategori.id_kategori "
                    + "join satuan on barang.id_satuan = satuan.id_satuan ;" ;
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getDouble(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                });
                }
            } catch (SQLException ex) {
                    String c = ex.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
            }
table_bar.setModel(model);
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
        gudang = new javax.swing.JLabel();
        transaksi = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        pengaturan = new javax.swing.JLabel();
        riwayat = new javax.swing.JLabel();
        Menu = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        caribar = new javax.swing.JTextField();
        jtgl = new javax.swing.JLabel();
        labelnamabar = new javax.swing.JLabel();
        labelharga = new javax.swing.JLabel();
        labelqty = new javax.swing.JLabel();
        txtid10 = new javax.swing.JLabel();
        jmlbar = new javax.swing.JLabel();
        rtr = new javax.swing.JLabel();
        labelkdbar = new javax.swing.JLabel();
        txtid1 = new javax.swing.JLabel();
        txtid2 = new javax.swing.JLabel();
        txtid3 = new javax.swing.JLabel();
        txtid4 = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bar = new tabledark.TableDark();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko-Berkah Gudang");
        setMinimumSize(new java.awt.Dimension(1280, 750));
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

        gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/gudang-clicked.png"))); // NOI18N
        gudang.addMouseListener(new java.awt.event.MouseAdapter() {
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
        getContentPane().add(Menu);
        Menu.setBounds(0, 100, 218, 630);

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/Header.png"))); // NOI18N
        header.setAlignmentY(0.0F);
        getContentPane().add(header);
        header.setBounds(0, 0, 1280, 110);

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/default (1).png"))); // NOI18N
        getContentPane().add(image);
        image.setBounds(938, 10, 75, 90);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        caribar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        caribar.setBorder(null);
        caribar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                caribarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                caribarFocusLost(evt);
            }
        });
        caribar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caribarKeyReleased(evt);
            }
        });
        jPanel1.add(caribar);
        caribar.setBounds(340, 128, 490, 30);

        jtgl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtgl.setForeground(new java.awt.Color(48, 56, 65));
        jtgl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtgl.setText("September");
        jPanel1.add(jtgl);
        jtgl.setBounds(1000, 120, 230, 50);

        labelnamabar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        labelnamabar.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(labelnamabar);
        labelnamabar.setBounds(400, 560, 270, 30);

        labelharga.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        labelharga.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(labelharga);
        labelharga.setBounds(430, 590, 230, 30);

        labelqty.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        labelqty.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(labelqty);
        labelqty.setBounds(400, 620, 250, 30);

        txtid10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid10.setForeground(new java.awt.Color(48, 56, 65));
        txtid10.setText("Rp. ");
        jPanel1.add(txtid10);
        txtid10.setBounds(400, 590, 40, 30);

        jmlbar.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jmlbar.setForeground(new java.awt.Color(48, 56, 65));
        jmlbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jmlbar);
        jmlbar.setBounds(980, 560, 250, 110);

        rtr.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rtr.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(rtr);
        rtr.setBounds(400, 650, 250, 30);

        labelkdbar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        labelkdbar.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(labelkdbar);
        labelkdbar.setBounds(400, 530, 280, 30);

        txtid1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid1.setForeground(new java.awt.Color(48, 56, 65));
        txtid1.setText("Nama Barang  :");
        jPanel1.add(txtid1);
        txtid1.setBounds(290, 560, 100, 30);

        txtid2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid2.setForeground(new java.awt.Color(48, 56, 65));
        txtid2.setText("Harga              :");
        jPanel1.add(txtid2);
        txtid2.setBounds(290, 590, 100, 30);

        txtid3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid3.setForeground(new java.awt.Color(48, 56, 65));
        txtid3.setText("Stok                :");
        jPanel1.add(txtid3);
        txtid3.setBounds(290, 620, 100, 30);

        txtid4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid4.setForeground(new java.awt.Color(48, 56, 65));
        txtid4.setText("Retur              :");
        jPanel1.add(txtid4);
        txtid4.setBounds(290, 650, 100, 30);

        txtid.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(48, 56, 65));
        txtid.setText("Kode Barang   :");
        jPanel1.add(txtid);
        txtid.setBounds(290, 530, 100, 30);

        jScrollPane1.setBorder(null);

        table_bar.setModel(new javax.swing.table.DefaultTableModel(
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
        table_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_barMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_bar);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(290, 200, 940, 280);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/bg.png"))); // NOI18N
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

    private void table_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_barMouseClicked
        // TODO add your handling code here:
        int baris = table_bar.rowAtPoint(evt.getPoint());
        String id = table_bar.getValueAt(baris, 0).toString();
        labelkdbar.setText(id);
        if (table_bar.getValueAt(baris, 1)==null) {
            labelnamabar.setText("");
        } else {
            labelnamabar.setText(table_bar.getValueAt(baris, 1).toString());
        }
        if (table_bar.getValueAt(baris, 3)==null) {
            labelqty.setText("");
        } else {
            labelqty.setText(table_bar.getValueAt(baris, 3).toString()+" "+table_bar.getValueAt(baris, 4).toString());
        }
        if (table_bar.getValueAt(baris, 6)==null) {
            labelharga.setText("");
        } else {
            labelharga.setText(table_bar.getValueAt(baris, 6).toString());
        }
        try {
            String sql = "select retur from barang WHERE id_barang = '"
                    +labelkdbar.getText()+"' ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            rs.next();
            rtr.setText(rs.getString("retur"));
        } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !",c);
        }
    }//GEN-LAST:event_table_barMouseClicked

    private void gudangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_gudangMouseEntered

    private void gudangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_gudangMouseExited

    private void gudangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_gudangMousePressed

    private void gudangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseReleased
        // TODO add your handling code here:

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
TransaksiJualkar transaksijualkar = new TransaksiJualkar();
transaksijualkar.nama.setText(nama.getText());
transaksijualkar.setVisible(true);
this.dispose();

    }//GEN-LAST:event_transaksiMouseClicked

    private void caribarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribarFocusGained
        // TODO add your handling code here:
        if (caribar.getText().equals("Cari Nama Barang")) {
            caribar.setForeground(new java.awt.Color(51,51,51));
            caribar.setText("");
        } else {
        }
    }//GEN-LAST:event_caribarFocusGained

    private void caribarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribarFocusLost
        // TODO add your handling code here:
        if (caribar.getText().equals("")) {
            caribar.setForeground(new java.awt.Color(221,221,221));
            caribar.setText("Cari Nama Barang");
        } else {
        }
    }//GEN-LAST:event_caribarFocusLost

    private void caribarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caribarKeyReleased
        // TODO add your handling code here:
        table_bar.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kategori");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Harga Beli");
    model.addColumn("Harga Jual");
        try {
            String sql = "select barang.id_barang, barang.nama_barang, kategori.jenis, jumlah, satuan.satuan, barang.harga_beli, barang.harga_jual "
                    + "from barang join kategori on barang.id_kategori = kategori.id_kategori "
                    + "join satuan on barang.id_satuan = satuan.id_satuan " 
                    + "where nama_barang like '%" + caribar.getText() + "%' ;" ;
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getDouble(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                });
                }
            } catch (SQLException ex) {
                    String c = ex.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
            }
    table_bar.setModel(model);
    }//GEN-LAST:event_caribarKeyReleased

    private void namaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_namaAncestorAdded
        // TODO add your handling code here:
        setprofile();
    }//GEN-LAST:event_namaAncestorAdded

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        // TODO add your handling code here:
Dashboardkar dashboardkar = new Dashboardkar();
dashboardkar.nama.setText(nama.getText());
dashboardkar.setVisible(true);
this.dispose();

    }//GEN-LAST:event_dashboardMouseClicked

    private void riwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseClicked
        // TODO add your handling code here:
RiwayatJualkar riwayatjualkar = new RiwayatJualkar();
riwayatjualkar.nama.setText(nama.getText());
riwayatjualkar.setVisible(true);
this.dispose();

    }//GEN-LAST:event_riwayatMouseClicked

    private void pengaturanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseClicked
        // TODO add your handling code here:
                    Settingkar settingkar = new Settingkar();
                    settingkar.nama.setText(nama.getText());
                    settingkar.setVisible(true);
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
            java.util.logging.Logger.getLogger(Gudangkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gudangkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gudangkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gudangkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gudangkar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JTextField caribar;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel header;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jenis;
    private javax.swing.JLabel jmlbar;
    private javax.swing.JLabel jtgl;
    private javax.swing.JLabel labelharga;
    private javax.swing.JLabel labelkdbar;
    private javax.swing.JLabel labelnamabar;
    private javax.swing.JLabel labelqty;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JLabel riwayat;
    private javax.swing.JLabel rtr;
    private tabledark.TableDark table_bar;
    private javax.swing.JLabel transaksi;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtid1;
    private javax.swing.JLabel txtid10;
    private javax.swing.JLabel txtid2;
    private javax.swing.JLabel txtid3;
    private javax.swing.JLabel txtid4;
    // End of variables declaration//GEN-END:variables
}
