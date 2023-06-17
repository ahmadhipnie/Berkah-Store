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
public class Gudang extends javax.swing.JFrame {
String autosup;
private ImageIcon format = null;
    /**
     * Creates new form Karyawan
     */
    public Gudang() {
        initComponents();
        table_bar();
        setJmlbar();
        Cursor();
        setFavIcon();
        combobox();
        comboboxktgr();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        popupb.setVisible(false);
        popupb1.setVisible(false);
        sup.setVisible(false);
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

    void Cursor(){
        dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        retur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lhtsup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hpsbar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditsup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhpssup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntmbhsup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tmbhbar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        peditbar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahbar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        txtidsup.setText("");
        txtnamasup.setText(null);
        txtalamatsup.setText(null);
        txtnomorsup.setText(null);
        btntmbhsup.setVisible(true);
        autoidsup();
    }
    void kosongbar() {
        labelkdbar.setText("");
        labelharga.setText(null);
        labelnamabar.setText(null);
        labelqty.setText(null);
        rtr.setText(null);
    }
    void kosongpop1() {
        pkdbar1.setText("");
        pnamabar1.setText(null);
        pkdbat1.setText("");
        pktgr1.setSelectedItem(null);
        pqty1.setText(null);
        psatuan1.setSelectedItem(null);
        phargabar1.setText(null);
        returcek1.setSelected(false);
    }

    void kosongpop() {
        pkdbar.setText("");
        pnamabar.setText(null);
        pkdbat.setText("");
        pktgr.setSelectedItem(1);
        pqty.setText(null);
        psatuan.setSelectedItem(null);
        phargabar.setText(null);
        returcek.setSelected(false);
    }

private void combobox() {
    String sql = "select * from satuan";
    try {
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while (res.next()) {
        psatuan.addItem(res.getString("satuan"));
        psatuan1.addItem(res.getString("satuan"));
    }
    } catch (Exception e) {
}
}
    private void comboboxktgr() {
    String sql = "select * from kategori";
    try {
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while (res.next()) {
        pktgr.addItem(res.getString("jenis"));
        pktgr1.addItem(res.getString("jenis"));
    }
    } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
}
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

    void autoidsup() {
        try {
            //--> melakukan eksekusi query untuk mengambil data dari tabel
            String sql = "SELECT MAX(id_supplier) FROM supplier" ;
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    txtidsup.setText("S"+"00001");
                } else {
                    rs.last();
                    String auto = rs.getString(1);
                    auto = auto.replace("S","");
                    int auto_id = Integer.parseInt(auto) +1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 5 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    txtidsup.setText("S"+no);
                    autosup = "S"+no;
                }
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
        }
    }

    void setTable_sup(){
    table_sup.fixTable(jScrollPane2);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Supplier");
    model.addColumn("Nama");
    model.addColumn("Nomor Telepon");
    model.addColumn("Alamat");

        try {
            String sql = "select * from supplier ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_supplier"),
                        res.getString("nama_supplier"),
                        res.getString("telp_supplier"),
                        res.getString("alamat_supplier")
                });
                }
            } catch (SQLException ex) {
                    String c = ex.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
            }
table_sup.setModel(model);
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
        sup = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtalamatsup = new javax.swing.JTextArea();
        txtidsup = new javax.swing.JLabel();
        carisup = new javax.swing.JTextField();
        txtnamasup = new javax.swing.JTextField();
        txtnomorsup = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_sup = new tabledark.TableDark();
        btntmbhsup = new javax.swing.JLabel();
        clear = new javax.swing.JLabel();
        btnhpssup = new javax.swing.JLabel();
        btneditsup = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        popupb1 = new javax.swing.JPanel();
        returcek1 = new checkbox.JCheckBoxCustom();
        psatuan1 = new combo_suggestion.ComboBoxSuggestion();
        pkdbat1 = new javax.swing.JTextField();
        pkdbar1 = new javax.swing.JLabel();
        pktgr1 = new combo_suggestion.ComboBoxSuggestion();
        cancel1 = new javax.swing.JLabel();
        peditbar = new javax.swing.JLabel();
        pqty1 = new javax.swing.JTextField();
        phargabar1 = new javax.swing.JTextField();
        pnamabar1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        popupb = new javax.swing.JPanel();
        returcek = new checkbox.JCheckBoxCustom();
        psatuan = new combo_suggestion.ComboBoxSuggestion();
        pkdbat = new javax.swing.JTextField();
        pkdbar = new javax.swing.JLabel();
        pktgr = new combo_suggestion.ComboBoxSuggestion();
        cancel = new javax.swing.JLabel();
        tambahbar = new javax.swing.JLabel();
        pqty = new javax.swing.JTextField();
        phargabar = new javax.swing.JTextField();
        pnamabar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        caribar = new javax.swing.JTextField();
        tmbhbar = new javax.swing.JLabel();
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
        lhtsup = new javax.swing.JLabel();
        hpsbar = new javax.swing.JLabel();
        editbar = new javax.swing.JLabel();
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

        karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/karyawan.png"))); // NOI18N
        karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                karyawanMouseClicked(evt);
            }
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

        sup.setBackground(new java.awt.Color(255, 255, 255));
        sup.setLayout(null);

        back.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                backMouseReleased(evt);
            }
        });
        sup.add(back);
        back.setBounds(290, 120, 40, 50);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtalamatsup.setColumns(5);
        txtalamatsup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtalamatsup.setLineWrap(true);
        txtalamatsup.setRows(5);
        jScrollPane3.setViewportView(txtalamatsup);

        sup.add(jScrollPane3);
        jScrollPane3.setBounds(930, 420, 310, 70);

        txtidsup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        sup.add(txtidsup);
        txtidsup.setBounds(930, 230, 300, 25);

        carisup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        carisup.setBorder(null);
        carisup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                carisupFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                carisupFocusLost(evt);
            }
        });
        carisup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carisupKeyReleased(evt);
            }
        });
        sup.add(carisup);
        carisup.setBounds(400, 127, 460, 30);

        txtnamasup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnamasup.setForeground(new java.awt.Color(48, 56, 65));
        txtnamasup.setBorder(null);
        txtnamasup.setPreferredSize(new java.awt.Dimension(0, 20));
        txtnamasup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamasupActionPerformed(evt);
            }
        });
        sup.add(txtnamasup);
        txtnamasup.setBounds(930, 290, 300, 25);

        txtnomorsup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnomorsup.setForeground(new java.awt.Color(48, 56, 65));
        txtnomorsup.setBorder(null);
        txtnomorsup.setPreferredSize(new java.awt.Dimension(0, 20));
        sup.add(txtnomorsup);
        txtnomorsup.setBounds(930, 353, 300, 25);

        jScrollPane2.setBorder(null);

        table_sup.setModel(new javax.swing.table.DefaultTableModel(
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
        table_sup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_supMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_sup);

        sup.add(jScrollPane2);
        jScrollPane2.setBounds(290, 200, 590, 470);

        btntmbhsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-supplier.png"))); // NOI18N
        btntmbhsup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntmbhsupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntmbhsupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntmbhsupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btntmbhsupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btntmbhsupMouseReleased(evt);
            }
        });
        sup.add(btntmbhsup);
        btntmbhsup.setBounds(920, 530, 330, 50);

        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-text.png"))); // NOI18N
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clearMouseReleased(evt);
            }
        });
        sup.add(clear);
        clear.setBounds(920, 630, 160, 50);

        btnhpssup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-supplier.png"))); // NOI18N
        btnhpssup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnhpssupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnhpssupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnhpssupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnhpssupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnhpssupMouseReleased(evt);
            }
        });
        sup.add(btnhpssup);
        btnhpssup.setBounds(1080, 630, 160, 50);

        btneditsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-supplier.png"))); // NOI18N
        btneditsup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneditsupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btneditsupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btneditsupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btneditsupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btneditsupMouseReleased(evt);
            }
        });
        sup.add(btneditsup);
        btneditsup.setBounds(920, 580, 330, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/sup.png"))); // NOI18N
        jLabel2.setText("jLabel1");
        sup.add(jLabel2);
        jLabel2.setBounds(270, 110, 990, 589);

        getContentPane().add(sup);
        sup.setBounds(0, 0, 1280, 750);

        popupb1.setBackground(new java.awt.Color(199, 199, 199));
        popupb1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popupb1.setFocusCycleRoot(true);
        popupb1.setFocusTraversalPolicyProvider(true);
        popupb1.setMinimumSize(new java.awt.Dimension(1016, 615));
        popupb1.setLayout(null);

        returcek1.setBackground(new java.awt.Color(255, 87, 34));
        returcek1.setText("Ya/Tidak");
        returcek1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        popupb1.add(returcek1);
        returcek1.setBounds(530, 400, 230, 27);

        psatuan1.setEditable(false);
        psatuan1.setForeground(new java.awt.Color(48, 56, 65));
        psatuan1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        psatuan1.setMinimumSize(new java.awt.Dimension(221, 20));
        psatuan1.setPreferredSize(new java.awt.Dimension(221, 20));
        popupb1.add(psatuan1);
        psatuan1.setBounds(530, 467, 245, 35);

        pkdbat1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pkdbat1.setForeground(new java.awt.Color(48, 56, 65));
        pkdbat1.setBorder(null);
        pkdbat1.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb1.add(pkdbat1);
        pkdbat1.setBounds(270, 265, 490, 25);

        pkdbar1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pkdbar1.setForeground(new java.awt.Color(48, 56, 65));
        popupb1.add(pkdbar1);
        pkdbar1.setBounds(270, 198, 490, 25);

        pktgr1.setEditable(false);
        pktgr1.setForeground(new java.awt.Color(48, 56, 65));
        pktgr1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pktgr1.setMinimumSize(new java.awt.Dimension(221, 20));
        pktgr1.setPreferredSize(new java.awt.Dimension(221, 20));
        pktgr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pktgr1ActionPerformed(evt);
            }
        });
        popupb1.add(pktgr1);
        pktgr1.setBounds(260, 120, 510, 35);

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
        popupb1.add(cancel1);
        cancel1.setBounds(450, 540, 160, 43);

        peditbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png"))); // NOI18N
        peditbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                peditbarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                peditbarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                peditbarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                peditbarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                peditbarMouseReleased(evt);
            }
        });
        popupb1.add(peditbar);
        peditbar.setBounds(620, 540, 160, 43);

        pqty1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pqty1.setForeground(new java.awt.Color(48, 56, 65));
        pqty1.setBorder(null);
        pqty1.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb1.add(pqty1);
        pqty1.setBounds(270, 473, 220, 25);

        phargabar1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        phargabar1.setForeground(new java.awt.Color(48, 56, 65));
        phargabar1.setBorder(null);
        phargabar1.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb1.add(phargabar1);
        phargabar1.setBounds(270, 401, 220, 25);

        pnamabar1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pnamabar1.setForeground(new java.awt.Color(48, 56, 65));
        pnamabar1.setBorder(null);
        pnamabar1.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb1.add(pnamabar1);
        pnamabar1.setBounds(270, 333, 490, 25);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/popedit.png"))); // NOI18N
        popupb1.add(jLabel8);
        jLabel8.setBounds(110, 10, 810, 600);

        getContentPane().add(popupb1);
        popupb1.setBounds(246, 100, 1040, 620);

        popupb.setBackground(new java.awt.Color(199, 199, 199));
        popupb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popupb.setFocusCycleRoot(true);
        popupb.setFocusTraversalPolicyProvider(true);
        popupb.setMinimumSize(new java.awt.Dimension(1016, 615));
        popupb.setLayout(null);

        returcek.setBackground(new java.awt.Color(255, 87, 34));
        returcek.setText("Ya/Tidak");
        returcek.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        popupb.add(returcek);
        returcek.setBounds(530, 400, 230, 27);

        psatuan.setEditable(false);
        psatuan.setForeground(new java.awt.Color(48, 56, 65));
        psatuan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        psatuan.setMinimumSize(new java.awt.Dimension(221, 20));
        psatuan.setPreferredSize(new java.awt.Dimension(221, 20));
        popupb.add(psatuan);
        psatuan.setBounds(530, 467, 245, 35);

        pkdbat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pkdbat.setForeground(new java.awt.Color(48, 56, 65));
        pkdbat.setBorder(null);
        pkdbat.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb.add(pkdbat);
        pkdbat.setBounds(270, 265, 490, 25);

        pkdbar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pkdbar.setForeground(new java.awt.Color(48, 56, 65));
        popupb.add(pkdbar);
        pkdbar.setBounds(270, 198, 490, 25);

        pktgr.setEditable(false);
        pktgr.setForeground(new java.awt.Color(48, 56, 65));
        pktgr.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pktgr.setMinimumSize(new java.awt.Dimension(221, 20));
        pktgr.setPreferredSize(new java.awt.Dimension(221, 20));
        pktgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pktgrActionPerformed(evt);
            }
        });
        popupb.add(pktgr);
        pktgr.setBounds(260, 120, 510, 35);

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
        popupb.add(cancel);
        cancel.setBounds(450, 540, 160, 43);

        tambahbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png"))); // NOI18N
        tambahbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahbarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahbarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tambahbarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tambahbarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tambahbarMouseReleased(evt);
            }
        });
        popupb.add(tambahbar);
        tambahbar.setBounds(620, 540, 160, 43);

        pqty.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pqty.setForeground(new java.awt.Color(48, 56, 65));
        pqty.setBorder(null);
        pqty.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb.add(pqty);
        pqty.setBounds(270, 473, 220, 25);

        phargabar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        phargabar.setForeground(new java.awt.Color(48, 56, 65));
        phargabar.setBorder(null);
        phargabar.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb.add(phargabar);
        phargabar.setBounds(270, 401, 220, 25);

        pnamabar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pnamabar.setForeground(new java.awt.Color(48, 56, 65));
        pnamabar.setBorder(null);
        pnamabar.setPreferredSize(new java.awt.Dimension(0, 20));
        popupb.add(pnamabar);
        pnamabar.setBounds(270, 333, 490, 25);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/poptambah.png"))); // NOI18N
        popupb.add(jLabel7);
        jLabel7.setBounds(110, 10, 810, 600);

        getContentPane().add(popupb);
        popupb.setBounds(246, 100, 1040, 620);

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

        tmbhbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-barang.png"))); // NOI18N
        tmbhbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmbhbarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tmbhbarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tmbhbarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmbhbarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tmbhbarMouseReleased(evt);
            }
        });
        jPanel1.add(tmbhbar);
        tmbhbar.setBounds(1010, 610, 210, 70);

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
        jmlbar.setBounds(980, 560, 250, 50);

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

        lhtsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/daftar-supplier.png"))); // NOI18N
        lhtsup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lhtsupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lhtsupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lhtsupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lhtsupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lhtsupMouseReleased(evt);
            }
        });
        jPanel1.add(lhtsup);
        lhtsup.setBounds(1030, 120, 220, 50);

        hpsbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-barang.png"))); // NOI18N
        hpsbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hpsbarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hpsbarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hpsbarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hpsbarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hpsbarMouseReleased(evt);
            }
        });
        jPanel1.add(hpsbar);
        hpsbar.setBounds(760, 590, 160, 50);

        editbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-barang.png"))); // NOI18N
        editbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editbarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editbarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editbarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editbarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editbarMouseReleased(evt);
            }
        });
        jPanel1.add(editbar);
        editbar.setBounds(760, 540, 160, 50);

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

    private void editbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbarMouseClicked
        // TODO add your handling code here:
        if (labelkdbar.getText().equals("")){
        panggilAlertMerah("Edit Gagal !","Pilih dahulu barang yang ingin diedit");    
        } else {
pkdbar1.setText(labelkdbar.getText());
pnamabar1.setText(labelnamabar.getText());
phargabar1.setText(labelharga.getText());
String satuan, ktgr;
                if (rtr.getText().equals("Iya")) {
                    returcek1.setSelected(true);
                } else {
                    returcek1.setSelected(false);
                }
try {
                    String sql = "SELECT barang.barcode, satuan.satuan, kategori.jenis, barang.jumlah from barang"
                    + " join satuan on barang.id_satuan = satuan.id_satuan "
                    + "join kategori on barang.id_kategori = kategori.id_kategori "
                            + "where barang.id_barang = '" + labelkdbar.getText()+ "' ;";
                    java.sql.Connection conn = (Connection) Config.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet rs = pst.executeQuery(sql);
                    rs.next();
                    pkdbat1.setText(rs.getString(1));
                    psatuan1.setSelectedItem(rs.getString(2));
                    pktgr1.setSelectedItem(rs.getString(3));
                    pktgr1.setEnabled(false);
                    pqty1.setText(rs.getString(4));
                    popupb1.setVisible(true);
                    jPanel1.setVisible(false);
                } catch (SQLException e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Edit Gagal !",c);
               
        }
}
    }//GEN-LAST:event_editbarMouseClicked

    private void lhtsupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lhtsupMouseEntered
        // TODO add your handling code here:
lhtsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/daftar-supplier-hover.png")));
    }//GEN-LAST:event_lhtsupMouseEntered

    private void lhtsupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lhtsupMouseExited
        // TODO add your handling code here:
lhtsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/daftar-supplier.png")));
    }//GEN-LAST:event_lhtsupMouseExited

    private void lhtsupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lhtsupMousePressed
        // TODO add your handling code here:
lhtsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/daftar-supplier-clicked.png")));
    }//GEN-LAST:event_lhtsupMousePressed

    private void lhtsupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lhtsupMouseReleased
        // TODO add your handling code here:
lhtsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/daftar-supplier.png")));
    }//GEN-LAST:event_lhtsupMouseReleased

    private void editbarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbarMouseEntered
        // TODO add your handling code here:
editbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-barang-hover.png")));
    }//GEN-LAST:event_editbarMouseEntered

    private void editbarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbarMouseExited
        // TODO add your handling code here:
editbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-barang.png")));

    }//GEN-LAST:event_editbarMouseExited

    private void editbarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbarMousePressed
        // TODO add your handling code here:
editbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-barang-clicked.png")));
    }//GEN-LAST:event_editbarMousePressed

    private void editbarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbarMouseReleased
        // TODO add your handling code here:
editbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-barang.png")));
    }//GEN-LAST:event_editbarMouseReleased

    private void hpsbarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsbarMouseEntered
        // TODO add your handling code here:
hpsbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-barang-hover.png")));
    }//GEN-LAST:event_hpsbarMouseEntered

    private void hpsbarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsbarMouseExited
        // TODO add your handling code here:
hpsbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-barang.png")));
    }//GEN-LAST:event_hpsbarMouseExited

    private void hpsbarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsbarMousePressed
        // TODO add your handling code here:
hpsbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-barang-clicked.png")));
    }//GEN-LAST:event_hpsbarMousePressed

    private void hpsbarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsbarMouseReleased
        // TODO add your handling code here:
hpsbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-barang.png")));
    }//GEN-LAST:event_hpsbarMouseReleased

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

    private void tambahbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahbarMouseClicked
        // TODO add your handling code here:
            String cek ="";
            if (returcek.isSelected()) {
                cek = "Iya";
            } else {
                cek = "Tidak" ;
            }
            String kktgr = "", idsat = "";
                try {
                    String sql = "SELECT id_kategori from kategori "
                            + "where jenis = '"+pktgr.getSelectedItem()+"';";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    java.sql.ResultSet rs = pst.executeQuery(sql);
                    rs.next();
                    kktgr = rs.getString("id_kategori");

                } catch (SQLException e) {
                }
                try {
                    String sql = "SELECT id_satuan from satuan "
                            + "where satuan = '"+psatuan.getSelectedItem()+"';";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    java.sql.ResultSet rs = pst.executeQuery(sql);
                    rs.next();
                    idsat = rs.getString("id_satuan");

                } catch (SQLException e) {
                }

try {
int hrgjl = Integer.parseInt(phargabar.getText());
int qtty = Integer.parseInt(pqty.getText());
                try {
                    String sqll = "INSERT INTO barang (id_barang, nama_barang, id_kategori, id_satuan, jumlah, barcode, harga_beli, harga_jual, retur) " +
                            "VALUES ('"+pkdbar.getText()+"','"
                            +pnamabar.getText()+"','"
                            +kktgr+"','"
                            +idsat+"','"
                            +pqty.getText()+"','"
                            +pkdbat.getText()+"','"
                            +"0','"
                            +phargabar.getText()+"','"
                            +cek+"')";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pstl=conn.prepareStatement(sqll);
                    pstl.execute();
                    panggilAlertHijau("Berhasil!", "Barang "+pkdbar.getText()+" Berhasil Ditambah");
                    table_bar();
                    setJmlbar();
                    kosongpop();
                } catch (Exception e) {
                    panggilAlertMerah("Tambah Gagal!", "Barang "+pkdbar.getText()+" Gagal Ditambah ");
                    }
} catch (Exception ex) {
                    panggilAlertMerah("Tambah Gagal!", "Masukkan harga dan kuantitas dengan benar");
}
    }//GEN-LAST:event_tambahbarMouseClicked

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        // TODO add your handling code here:
popupb.setVisible(false);
jPanel1.setVisible(true);
    }//GEN-LAST:event_cancelMouseClicked

    private void lhtsupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lhtsupMouseClicked
        // TODO add your handling code here:
sup.setVisible(true);
jPanel1.setVisible(false);
        autoidsup();
        setTable_sup();
    }//GEN-LAST:event_lhtsupMouseClicked

    private void karyawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseEntered
        // TODO add your handling code here:
        karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/karyawan-hover.png")));
    }//GEN-LAST:event_karyawanMouseEntered

    private void karyawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseExited
        // TODO add your handling code here:
        karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/karyawan.png")));
    }//GEN-LAST:event_karyawanMouseExited

    private void karyawanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMousePressed
        // TODO add your handling code here:
        karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/karyawan-clicked.png")));
    }//GEN-LAST:event_karyawanMousePressed

    private void karyawanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseReleased
        // TODO add your handling code here:
        karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/karyawan.png")));
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
TransaksiJual transaksijual = new TransaksiJual();
transaksijual.nama.setText(nama.getText());
transaksijual.setVisible(true);
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

    private void tmbhbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhbarMouseClicked
        // TODO add your handling code here:
popupb.setVisible(true);
jPanel1.setVisible(false);
    }//GEN-LAST:event_tmbhbarMouseClicked

    private void tmbhbarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhbarMouseEntered
        // TODO add your handling code here:
tmbhbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-barang-hover.png")));
    }//GEN-LAST:event_tmbhbarMouseEntered

    private void tmbhbarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhbarMouseExited
        // TODO add your handling code here:
tmbhbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-barang.png")));
    }//GEN-LAST:event_tmbhbarMouseExited

    private void tmbhbarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhbarMousePressed
        // TODO add your handling code here:
tmbhbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-barang-clicked.png")));
    }//GEN-LAST:event_tmbhbarMousePressed

    private void tmbhbarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhbarMouseReleased
        // TODO add your handling code here:
tmbhbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-barang.png")));
    }//GEN-LAST:event_tmbhbarMouseReleased

    private void pktgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pktgrActionPerformed
        // TODO add your handling code here:
        String kktgr = "";
        try {
            String sql = "SELECT id_kategori from kategori "
                    + "where jenis = '"+pktgr.getSelectedItem()+"';";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            kktgr = rs.getString("id_kategori");

        } catch (SQLException e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
        }

        try {
            //--> melakukan eksekusi query untuk mengambil data dari tabel
            String sql = "SELECT MAX(id_barang) FROM barang" +
                    " WHERE id_kategori ='"+kktgr+"' ;" ;
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    pkdbar.setText(kktgr+"001");
                } else {
                    rs.last();
                    String auto = rs.getString(1);
                    auto = auto.replace(kktgr,"");
                    int auto_id = Integer.parseInt(auto) +1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 3 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    pkdbar.setText(kktgr+no);
                }
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Gagal !", c);
        }
    }//GEN-LAST:event_pktgrActionPerformed

    private void pktgr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pktgr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pktgr1ActionPerformed

    private void cancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseClicked
        // TODO add your handling code here:
popupb1.setVisible(false);
jPanel1.setVisible(true);
    }//GEN-LAST:event_cancel1MouseClicked

    private void peditbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peditbarMouseClicked
        // TODO add your handling code here:
            String cek ="";
            if (returcek1.isSelected()) {
                cek = "Iya";
            } else {
                cek = "Tidak" ;
            }
            String kktgr = "", idsat = "";
//                try {
//                    String sql = "SELECT id_kategori from kategori "
//                            + "where jenis = '"+pktgr1.getSelectedItem()+"';";
//                    java.sql.Connection conn=(Connection)Config.configDB();
//                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//                    java.sql.ResultSet rs = pst.executeQuery(sql);
//                    rs.next();
//                    kktgr = rs.getString("id_kategori");
//
//                } catch (SQLException e) {
//                }
                try {
                    String sql = "SELECT id_satuan from satuan "
                            + "where satuan = '"+psatuan1.getSelectedItem()+"';";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    java.sql.ResultSet rs = pst.executeQuery(sql);
                    rs.next();
                    idsat = rs.getString("id_satuan");

                } catch (SQLException e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Edit Gagal !", c);
                }
try {
int hrgjl = Integer.parseInt(phargabar1.getText());
int qtty = Integer.parseInt(pqty1.getText());
                try {
                    String sqll = "UPDATE barang "
                            + "SET nama_barang = '" + pnamabar1.getText()
                            + "', jumlah = '" + pqty1.getText()
                            + "', id_satuan = '" + idsat
                            + "', harga_jual = '" + phargabar1.getText()
                            + "', retur = '" + cek
                            + "', barcode = '" + pkdbat1.getText()
                            + "' WHERE barang.id_barang = '" + pkdbar1.getText() + "' ;";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pstl=conn.prepareStatement(sqll);
                    pstl.execute();
                    panggilAlertHijau("Edit Berhasil !", "Barang "+pkdbar1.getText()+" Berhasil Diubah");
                    table_bar();
                    setJmlbar();
                    kosongpop1();
                    kosongbar();
                    popupb1.setVisible(false);
                    jPanel1.setVisible(true);
                } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Edit Gagal !", "Barang "+pkdbar1.getText()+" Gagal Diubah "+c);
                    }
} catch (Exception ex) {
                    panggilAlertMerah("Edit Gagal!", "Masukkan harga dan kuantitas dengan benar");
}
    }//GEN-LAST:event_peditbarMouseClicked

    private void carisupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carisupFocusGained
        // TODO add your handling code here:
        if (carisup.getText().equals("Cari Nama Supplier")) {
            carisup.setForeground(new java.awt.Color(51,51,51));
            carisup.setText("");
        } else {
        }
    }//GEN-LAST:event_carisupFocusGained

    private void carisupFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carisupFocusLost
        // TODO add your handling code here:
        if (carisup.getText().equals("")) {
            carisup.setForeground(new java.awt.Color(221,221,221));
            carisup.setText("Cari Nama Supplier");
        } else {
        }
    }//GEN-LAST:event_carisupFocusLost

    private void carisupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carisupKeyReleased
        // TODO add your handling code here:
    table_sup.fixTable(jScrollPane2);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Supplier");
    model.addColumn("Nama");
    model.addColumn("Nomor Telepon");
    model.addColumn("Alamat");

        try {
            String sql = "select * from supplier "
                    + "where nama_supplier like '%" + carisup.getText() + "%'";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_supplier"),
                        res.getString("nama_supplier"),
                        res.getString("telp_supplier"),
                        res.getString("alamat_supplier")
                });
                }
            } catch (SQLException ex) {
                String c = ex.getMessage().substring(0, 36);
                    panggilAlertMerah("Hapus Gagal !", c);
            }
table_sup.setModel(model);
    }//GEN-LAST:event_carisupKeyReleased

    private void table_supMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_supMouseClicked
        // TODO add your handling code here:
        int baris = table_sup.rowAtPoint(evt.getPoint());
        String id = table_sup.getValueAt(baris, 0).toString();
        txtidsup.setText(id);
        if (table_sup.getValueAt(baris, 1)==null) {
            txtnamasup.setText("");
        } else {
            txtnamasup.setText(table_sup.getValueAt(baris, 1).toString());
        }
        if (table_sup.getValueAt(baris, 3)==null) {
            txtalamatsup.setText("");
        } else {
            txtalamatsup.setText(table_sup.getValueAt(baris, 3).toString());
        }
        if (table_sup.getValueAt(baris, 2)==null) {
            txtnomorsup.setText("");
        } else {
            txtnomorsup.setText(table_sup.getValueAt(baris, 2).toString());
        }
btntmbhsup.setVisible(false);
    }//GEN-LAST:event_table_supMouseClicked

    private void btnhpssupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhpssupMouseClicked
        // TODO add your handling code here:
        if (txtidsup.getText().equals(autosup)) {
            panggilAlertMerah("Hapus Gagal !","pilih dahulu Supplier yang ingin dihapus");
        } else {
                AlertPertanyaan obj = new AlertPertanyaan(this);
               obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Supplier Dengan ID \n"+txtidsup.getText()+"?");
               if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
                    try {
                        String sql ="DELETE FROM supplier where id_supplier='"+txtidsup.getText()+"'";
                        java.sql.Connection conn=(Connection)Config.configDB();
                        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                        pst.execute();
                        panggilAlertHijau("Hapus Berhasil", "Data Supplier dengan ID "+txtidsup.getText()+" berhasil dihapus");
                        setTable_sup();
                        kosong();
                    } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Hapus Gagal !", c);
                    }
            }
    }//GEN-LAST:event_btnhpssupMouseClicked
}
    private void btnhpssupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhpssupMouseEntered
        // TODO add your handling code here:
btnhpssup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-supplier-hover.png")));
    }//GEN-LAST:event_btnhpssupMouseEntered

    private void btnhpssupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhpssupMouseExited
        // TODO add your handling code here:
btnhpssup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-supplier.png")));
    }//GEN-LAST:event_btnhpssupMouseExited

    private void btnhpssupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhpssupMousePressed
        // TODO add your handling code here:
btnhpssup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-supplier-clicked.png")));
    }//GEN-LAST:event_btnhpssupMousePressed

    private void btnhpssupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhpssupMouseReleased
        // TODO add your handling code here:
btnhpssup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-supplier.png")));
    }//GEN-LAST:event_btnhpssupMouseReleased

    private void btneditsupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditsupMouseReleased
        // TODO add your handling code here:
btneditsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-supplier.png")));
    }//GEN-LAST:event_btneditsupMouseReleased

    private void btneditsupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditsupMousePressed
        // TODO add your handling code here:
btneditsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-supplier-clicked.png")));
    }//GEN-LAST:event_btneditsupMousePressed

    private void btneditsupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditsupMouseExited
        // TODO add your handling code here:
btneditsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-supplier.png")));
    }//GEN-LAST:event_btneditsupMouseExited

    private void btneditsupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditsupMouseEntered
        // TODO add your handling code here:
btneditsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/edit-supplier-hover.png")));
    }//GEN-LAST:event_btneditsupMouseEntered

    private void btneditsupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditsupMouseClicked
        // TODO add your handling code here:
        if (txtnamasup.getText().equals("")) {
            panggilAlertMerah("Edit Gagal !","pilih dahulu Data Supplier yang ingin diubah");
        } else {
            String hp=txtnomorsup.getText();
            if (hp.matches("^[0-9]*") && hp.length()==12){
                try {
                    String sql ="UPDATE supplier "
                            + "SET nama_supplier = '"+txtnamasup.getText()
                            +"', alamat_supplier = '" +txtalamatsup.getText()
                            +"', telp_supplier = '" +txtnomorsup.getText()
                            +"' WHERE supplier.id_supplier = '"+txtidsup.getText()+"'";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                panggilAlertHijau("Berhasil diubah!", "Data Supplier dengan ID "+txtidsup.getText()+" berhasil diubah");
                setTable_sup();
                kosong();
                } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Perubahan Data Gagal !", c);
                }
            } else {
                panggilAlertMerah("Edit Gagal !","Nomor Telepon salah");
            }
        }
    }//GEN-LAST:event_btneditsupMouseClicked

    private void btntmbhsupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntmbhsupMouseClicked
        // TODO add your handling code here:
        if (txtnamasup.getText().equals("")) {
            panggilAlertMerah("Tambah Gagal !","Nama belum diisi");
        } else {
            String hp=txtnomorsup.getText();
            if (hp.matches("^[0-9]*") && hp.length()==12){
                try {
                    String sql = "INSERT INTO supplier VALUES ('"+txtidsup.getText()+"','"
                            +txtnamasup.getText()+"','"+txtalamatsup.getText()+"','"+
                            txtnomorsup.getText()+"')";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                panggilAlertHijau("Berhasil ditambah!", "Data Supplier dengan ID "+txtidsup.getText()+" berhasil ditambah");
                setTable_sup();
                kosong();
                } catch (Exception e) {
                    String c = e.getMessage().substring(0, 36);
                    panggilAlertMerah("Tambah Data Gagal  !", c);

                }
            } else {
                panggilAlertMerah("Tambah Gagal !","Nomor Telepon salah");
            }
        }
    }//GEN-LAST:event_btntmbhsupMouseClicked

    private void btntmbhsupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntmbhsupMouseEntered
        // TODO add your handling code here:
btntmbhsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-supplier-hover.png")));
    }//GEN-LAST:event_btntmbhsupMouseEntered

    private void btntmbhsupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntmbhsupMouseExited
        // TODO add your handling code here:
btntmbhsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-supplier.png")));
    }//GEN-LAST:event_btntmbhsupMouseExited

    private void btntmbhsupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntmbhsupMousePressed
        // TODO add your handling code here:
btntmbhsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-supplier-clicked.png")));
    }//GEN-LAST:event_btntmbhsupMousePressed

    private void btntmbhsupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntmbhsupMouseReleased
        // TODO add your handling code here:
btntmbhsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/tambah-supplier.png")));
    }//GEN-LAST:event_btntmbhsupMouseReleased

    private void clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseClicked
        // TODO add your handling code here:
kosong();
    }//GEN-LAST:event_clearMouseClicked

    private void clearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseEntered
        // TODO add your handling code here:
clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-text-hover.png")));
    }//GEN-LAST:event_clearMouseEntered

    private void clearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseExited
        // TODO add your handling code here:
clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-text.png")));
    }//GEN-LAST:event_clearMouseExited

    private void clearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMousePressed
        // TODO add your handling code here:
clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-text-clicked.png")));
    }//GEN-LAST:event_clearMousePressed

    private void clearMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseReleased
        // TODO add your handling code here:
clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/hapus-text.png")));
    }//GEN-LAST:event_clearMouseReleased

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
sup.setVisible(false);
jPanel1.setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void hpsbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsbarMouseClicked
        // TODO add your handling code here:
        if (labelkdbar.getText().equals("")) {
            panggilAlertMerah("Hapus Gagal !","pilih dahulu Barang yang ingin dihapus");
        } else { 
                    AlertPertanyaan obj = new AlertPertanyaan(this);
        obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Barang Dengan Kode \n"+labelkdbar.getText()+"?");
        if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
             try {
            String sql ="DELETE FROM barang where id_barang='"+labelkdbar.getText()+"'";
        java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Hapus Berhasil !","Data Barang dengan Kode "+labelkdbar.getText()+" berhasil dihapus");
                table_bar();
                kosongbar();
        } catch (Exception e) {
            String c = e.getMessage().substring(0, 36);
                panggilAlertMerah("Hapus Gagal !", c);
        } 
        
        }
     
        }
    }//GEN-LAST:event_hpsbarMouseClicked

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

    private void peditbarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peditbarMouseEntered
        // TODO add your handling code here:
peditbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-hover.png")));
    }//GEN-LAST:event_peditbarMouseEntered

    private void peditbarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peditbarMouseExited
        // TODO add your handling code here:
peditbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_peditbarMouseExited

    private void peditbarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peditbarMousePressed
        // TODO add your handling code here:
peditbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-clicked.png")));
    }//GEN-LAST:event_peditbarMousePressed

    private void peditbarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peditbarMouseReleased
        // TODO add your handling code here:
peditbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_peditbarMouseReleased

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

    private void tambahbarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahbarMouseEntered
        // TODO add your handling code here:
tambahbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-hover.png")));
    }//GEN-LAST:event_tambahbarMouseEntered

    private void tambahbarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahbarMouseExited
        // TODO add your handling code here:
tambahbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_tambahbarMouseExited

    private void tambahbarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahbarMousePressed
        // TODO add your handling code here:
tambahbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai-clicked.png")));
    }//GEN-LAST:event_tambahbarMousePressed

    private void tambahbarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahbarMouseReleased
        // TODO add your handling code here:
tambahbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/selesai.png")));
    }//GEN-LAST:event_tambahbarMouseReleased

    private void txtnamasupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamasupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamasupActionPerformed

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
        // TODO add your handling code here:
back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back-hover.png")));
    }//GEN-LAST:event_backMouseEntered

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited
        // TODO add your handling code here:
back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png")));
    }//GEN-LAST:event_backMouseExited

    private void backMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMousePressed
        // TODO add your handling code here:
back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back-clicked.png")));
    }//GEN-LAST:event_backMousePressed

    private void backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseReleased
        // TODO add your handling code here:
back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png")));
    }//GEN-LAST:event_backMouseReleased

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

    private void riwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseClicked
        // TODO add your handling code here:
RiwayatJual riwayatjual = new RiwayatJual();
riwayatjual.nama.setText(nama.getText());
riwayatjual.setVisible(true);
this.dispose();

    }//GEN-LAST:event_riwayatMouseClicked

    private void karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseClicked
        // TODO add your handling code here
Karyawan karyawan = new Karyawan();
karyawan.nama.setText(nama.getText());
karyawan.setVisible(true);
this.dispose();

    }//GEN-LAST:event_karyawanMouseClicked

    private void pengaturanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseClicked
        // TODO add your handling code here:
                    Setting setting = new Setting();
                    setting.nama.setText(nama.getText());
                    setting.setVisible(true);
                    this.dispose();

    }//GEN-LAST:event_pengaturanMouseClicked

    private void returMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseClicked
        // TODO add your handling code here:
                    Retur retur = new Retur();
                    retur.nama.setText(nama.getText());
                    retur.setVisible(true);
                    this.dispose();

    }//GEN-LAST:event_returMouseClicked

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
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gudang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JLabel back;
    private javax.swing.JLabel btneditsup;
    private javax.swing.JLabel btnhpssup;
    private javax.swing.JLabel btntmbhsup;
    private javax.swing.JLabel cancel;
    private javax.swing.JLabel cancel1;
    private javax.swing.JTextField caribar;
    private javax.swing.JTextField carisup;
    private javax.swing.JLabel clear;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel editbar;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel header;
    private javax.swing.JLabel hpsbar;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jenis;
    private javax.swing.JLabel jmlbar;
    private javax.swing.JLabel karyawan;
    private javax.swing.JLabel labelharga;
    private javax.swing.JLabel labelkdbar;
    private javax.swing.JLabel labelnamabar;
    private javax.swing.JLabel labelqty;
    private javax.swing.JLabel lhtsup;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private javax.swing.JLabel peditbar;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JTextField phargabar;
    private javax.swing.JTextField phargabar1;
    private javax.swing.JLabel pkdbar;
    private javax.swing.JLabel pkdbar1;
    private javax.swing.JTextField pkdbat;
    private javax.swing.JTextField pkdbat1;
    private combo_suggestion.ComboBoxSuggestion pktgr;
    private combo_suggestion.ComboBoxSuggestion pktgr1;
    private javax.swing.JTextField pnamabar;
    private javax.swing.JTextField pnamabar1;
    private javax.swing.JPanel popupb;
    private javax.swing.JPanel popupb1;
    private javax.swing.JTextField pqty;
    private javax.swing.JTextField pqty1;
    private combo_suggestion.ComboBoxSuggestion psatuan;
    private combo_suggestion.ComboBoxSuggestion psatuan1;
    private javax.swing.JLabel retur;
    private checkbox.JCheckBoxCustom returcek;
    private checkbox.JCheckBoxCustom returcek1;
    private javax.swing.JLabel riwayat;
    private javax.swing.JLabel rtr;
    private javax.swing.JPanel sup;
    private tabledark.TableDark table_bar;
    public static tabledark.TableDark table_sup;
    private javax.swing.JLabel tambahbar;
    private javax.swing.JLabel tmbhbar;
    private javax.swing.JLabel transaksi;
    private javax.swing.JTextArea txtalamatsup;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtid1;
    private javax.swing.JLabel txtid10;
    private javax.swing.JLabel txtid2;
    private javax.swing.JLabel txtid3;
    private javax.swing.JLabel txtid4;
    private javax.swing.JLabel txtidsup;
    private javax.swing.JTextField txtnamasup;
    private javax.swing.JTextField txtnomorsup;
    // End of variables declaration//GEN-END:variables
}
