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
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javaswingdev.message.AlertBerhasil;

import javaswingdev.message.AlertGagal;
import javaswingdev.message.AlertPertanyaan;
import javaswingdev.message.AlertPertanyaanHijau;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author yogan
 */
public class Retur extends javax.swing.JFrame {
String date1, date2, rbar, rbar2;
private ImageIcon format = null;
    /**
     * Creates new form Karyawan
     */
    public Retur() {
        initComponents();
        Cursor();
        setsup();
        cekcart();
        bersihcart();
        setkdretursup();
        settgl();
        setFavIcon();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        dtlrtrcus.setVisible(false);
        cart.setVisible(false);
        hcustomer.setVisible(false);
        hsupplier.setVisible(false);
        dtlrtrsup.setVisible(false);
        caribar.setForeground(new java.awt.Color(221,221,221));
        caribar.setText("Cari Nama Barang");
        caricus.setForeground(new java.awt.Color(221,221,221));
        caricus.setText("Cari Kode Transaksi");
        carisup.setForeground(new java.awt.Color(221,221,221));
        carisup.setText("Cari Kode Retur");
    }

private void setFavIcon(){
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/main/img/Logoapp.png")));
}

    void Cursor(){
        dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gudang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hcos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lanjutco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dtlcusback1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        detail1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapussup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dtlcusprint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dtlcusback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        detail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backcart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prosesretur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hsup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tmbhcart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

void kosong() {
kdbar.setText("");
qty.setText(null);
sat.setText(null);
namabar.setText(null);

}
void rcuskosong() {
kdrtr.setText("");
kdtrans.setText(null);
tgl.setText(null);
jam.setText(null);
kar.setText(null);
totalbar.setText(null);

}

void rsupkosong() {
kdrtr1.setText("");
namsup.setText(null);
tgl1.setText(null);
jam1.setText(null);
kar1.setText(null);
totalbar1.setText(null);

}

private void setsup() {
    String sql = "select * from supplier";
    try {
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while (res.next()) {
        sup.addItem(res.getString("nama_supplier"));
    }
    } catch (Exception e) {
    }
}

    void settgl() {
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
        tglcart.setText(aa + " " + mm + " " + yyyy);
    }

    void setkdretursup() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy-");
        LocalDateTime tanggal = LocalDateTime.now();
        String a = (dtf.format(tanggal));
        DateTimeFormatter dtff = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime tanggall = LocalDateTime.now();
        String aa = (dtff.format(tanggall));
        try {
            //--> melakukan eksekusi query untuk mengambil data dari tabel
            String sql = "SELECT MAX(id_retur_sup) FROM retur_supplier " +
                    "WHERE tanggal_balik = '" + aa + "' ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    kdreturcart.setText("RS-" + a + "001");
                } else {
                    rs.last();
                    String auto = rs.getString(1);
                    auto = auto.replace("RS-" + a, "");
                    int auto_id = Integer.parseInt(auto) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 3 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    kdreturcart.setText("RS-" + a + no);
                }
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void cekcart() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy-");
        LocalDateTime tanggal = LocalDateTime.now();
        String a = (dtf.format(tanggal));
        try {
            //--> melakukan eksekusi query untuk mengambil data dari tabel
            String sql = "SELECT MAX(id_retur_sup) FROM cart_retur_supplier " +
                    "WHERE id_retur_sup like '%" + a + "%' ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    try {
                        String sqll = "TRUNCATE TABLE cart_retur_supplier ;";
                        java.sql.Connection connn = (Connection) Config.configDB();
                        java.sql.PreparedStatement pstl = connn.prepareStatement(sqll);
                        pstl.execute();
                    } catch (Exception e) {
                    }
                } else {
//                    System.out.println("kosong");
                }
            }
            rs.close();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    void bersihcart() {
                    try {
                        String sqll = "TRUNCATE TABLE cart_retur_supplier ;";
                        java.sql.Connection connn = (Connection) Config.configDB();
                        java.sql.PreparedStatement pstl = connn.prepareStatement(sqll);
                        pstl.execute();
                    } catch (Exception e) {
                    }
}

    void rcekout() {
        String supp = "";
        try {
            String sql = "SELECT id_supplier from supplier "
                    + "where nama_supplier = '" + sup.getSelectedItem() + "';";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            supp = rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sqll = "insert into retur_supplier (id_retur_sup, id_supplier, tanggal_balik, jam, id_pengguna ) " +
                    "values ('" + kdreturcart.getText() + "', '" + supp + "', CURRENT_DATE, CURRENT_TIME, '"+nama.getText()+"') ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pstl = conn.prepareStatement(sqll);
            pstl.execute();
            String sql = "insert into detail_retur_supplier (`id_retur_sup`, `id_barang`, "
                    + "`qty` ) "
                    + "SELECT id_retur_sup, id_barang, qty FROM cart_retur_supplier ;";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            updatestatus();
            bersihcart();
            setTablecart();
            setkdretursup();
            panggilAlertHijau("Berhasil", "Proses Retur Berhasil \nSilakan menuju ke riwayat untuk mengeceknya");
        } catch (Exception e) {
            e.printStackTrace();
            panggilAlertMerah("Gagal ", e.getMessage());
        }

    }

void updatestatus() {
        String supp = "";
        try {
            String sql = "SELECT id_supplier from supplier "
                    + "where nama_supplier = '" + sup.getSelectedItem() + "';";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            supp = rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    tablecart.fixTable(jScrollPane2);
    DefaultTableModel model = (DefaultTableModel) tablecart.getModel();
    int row = model.getRowCount();
    for(int i = 0; i < row; i++)
    {
        String id = tablecart.getValueAt(i, 0).toString();
        try {
        String sqll = "UPDATE detail_retur_cos "
                                + "SET status = 'sudah' "
                                + "WHERE id_barang = '" + id + "' and id_supplier = '"+ supp + "' ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pstl = conn.prepareStatement(sqll);
            pstl.execute();
      System.out.println(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    void settablemain(){
        String supp = "";
        try {
            String sql = "SELECT id_supplier from supplier "
                    + "where nama_supplier = '" + sup.getSelectedItem() + "';";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            supp = rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    tablemainretur.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
        try {
            String sql = "SELECT detail_retur_cos.id_barang, barang.nama_barang, sum(detail_retur_cos.qty), satuan.satuan "
            + "from detail_retur_cos join retur_customer on detail_retur_cos.id_retur = retur_customer.id_retur_cus "
            + "join barang on detail_retur_cos.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_retur_cos.id_supplier = '"+supp+"' and detail_retur_cos.status = 'belum' "
            + "group by detail_retur_cos.id_barang ;";  
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getDouble(3),
                        res.getString(4)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablemainretur.setModel(model);
    }

    void setTablecart(){
    tablecart.fixTable(jScrollPane2);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
        try {
            String sql = "select cart_retur_supplier.id_barang, barang.nama_barang, cart_retur_supplier.qty, satuan.satuan " +
                    "from cart_retur_supplier join barang on cart_retur_supplier.id_barang = barang.id_barang "
                    + "join satuan on barang.id_satuan = satuan.id_satuan "
                    + " where id_retur_sup = '" + kdreturcart.getText() + "' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getDouble(3),
                        res.getString(4)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablecart.setModel(model);
    }

    void setTablehcus(){
    tablehcus.fixTable(jScrollPane3);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Retur");
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
        try {
            String sql = "select * from retur_customer "
            + "order by tanggal desc ; ";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehcus.setModel(model);
    }

    void setTablehsup(){
    tablehsup.fixTable(jScrollPane6);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Retur");
    model.addColumn("Supplier");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
        try {
            String sql = "select retur_supplier.id_retur_sup, supplier.nama_supplier, "
            + "retur_supplier.tanggal_balik, retur_supplier.jam, retur_supplier.id_pengguna from retur_supplier "
            + "join supplier on retur_supplier.id_supplier = supplier.id_supplier "
            + "order by tanggal_balik desc ; ";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehsup.setModel(model);
    }

    void setTabledtlcus(){
    tabledtlcus.fixTable(jScrollPane4);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Harga");
    model.addColumn("Kuantitas");
    model.addColumn("Total");
    model.addColumn("Supplier");
        try {
            String sql = "select detail_retur_cos.id_barang, barang.nama_barang, detail_penjualan.harga, "
            + "detail_retur_cos.qty, detail_retur_cos.sub_total, supplier.nama_supplier from detail_retur_cos "
            + "join retur_customer on detail_retur_cos.id_retur = retur_customer.id_retur_cus "
            + "join detail_penjualan on retur_customer.id_penjualan = detail_penjualan.id_penjualan and detail_retur_cos.id_barang = detail_penjualan.id_barang "
            + "join barang on detail_retur_cos.id_barang = barang.id_barang "
            + "join supplier on detail_retur_cos.id_supplier = supplier.id_supplier "
            + "where detail_retur_cos.id_retur = '"+dtlkdrtr.getText()+"' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getDouble(4),
                        res.getInt(5),
                        res.getString(6)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tabledtlcus.setModel(model);
    }

    void setTabledtlsup(){
    tabledtlsup.fixTable(jScrollPane7);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kuantitas");
        try {
            String sql = "select detail_retur_supplier.id_barang, barang.nama_barang, detail_retur_supplier.qty "
            + "from detail_retur_supplier join barang on detail_retur_supplier.id_barang = barang.id_barang "
            + "where detail_retur_supplier.id_retur_sup = '"+dtlkdrtr1.getText()+"' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getInt(3)

                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tabledtlsup.setModel(model);
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

        dateChooser = new com.raven.datechooser.DateChooser();
        dateChooser1 = new com.raven.datechooser.DateChooser();
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
        hcustomer = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        kar = new javax.swing.JLabel();
        detail = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        jam = new javax.swing.JLabel();
        kdrtr = new javax.swing.JLabel();
        kdtrans = new javax.swing.JLabel();
        caricus = new javax.swing.JTextField();
        totalbar = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablehcus = new tabledark.TableDark();
        hapuscus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtlrtrsup = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabledtlsup = new tabledark.TableDark();
        dtlsup = new javax.swing.JLabel();
        dtlkdrtr1 = new javax.swing.JLabel();
        dtlcusback1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hsupplier = new javax.swing.JPanel();
        back1 = new javax.swing.JLabel();
        kar1 = new javax.swing.JLabel();
        detail1 = new javax.swing.JLabel();
        tgl1 = new javax.swing.JLabel();
        jam1 = new javax.swing.JLabel();
        kdrtr1 = new javax.swing.JLabel();
        namsup = new javax.swing.JLabel();
        carisup = new javax.swing.JTextField();
        totalbar1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablehsup = new tabledark.TableDark();
        hapussup = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dtlrtrcus = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabledtlcus = new tabledark.TableDark();
        jScrollPane5 = new javax.swing.JScrollPane();
        dtlket = new javax.swing.JTextArea();
        dtlcusprint = new javax.swing.JLabel();
        dtlgtot = new javax.swing.JLabel();
        dtljam = new javax.swing.JLabel();
        dtlkdtrans = new javax.swing.JLabel();
        dtlcusback = new javax.swing.JLabel();
        dtlkdrtr = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cart = new javax.swing.JPanel();
        tglcart = new javax.swing.JLabel();
        backcart = new javax.swing.JLabel();
        totalcart = new javax.swing.JLabel();
        kdreturcart = new javax.swing.JLabel();
        supcart = new javax.swing.JLabel();
        caribarcart = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablecart = new tabledark.TableDark();
        prosesretur = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        reset = new javax.swing.JLabel();
        tmbhcart = new javax.swing.JLabel();
        sup = new combo_suggestion.ComboBoxSuggestion();
        qty = new javax.swing.JLabel();
        hsup = new javax.swing.JLabel();
        kdbar = new javax.swing.JLabel();
        namabar = new javax.swing.JLabel();
        caribar = new javax.swing.JTextField();
        sat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablemainretur = new tabledark.TableDark();
        hcos = new javax.swing.JLabel();
        lanjutco = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        dateChooser.setForeground(new java.awt.Color(70, 204, 89));

        dateChooser1.setForeground(new java.awt.Color(70, 204, 89));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko-Berkah Retur");
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

        retur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/retur-clicked.png"))); // NOI18N
        retur.addMouseListener(new java.awt.event.MouseAdapter() {
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

        hcustomer.setBackground(new java.awt.Color(255, 255, 255));
        hcustomer.setLayout(null);

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
        hcustomer.add(back);
        back.setBounds(290, 120, 40, 50);

        kar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        kar.setForeground(new java.awt.Color(48, 56, 65));
        hcustomer.add(kar);
        kar.setBounds(870, 480, 360, 27);

        detail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png"))); // NOI18N
        detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                detailMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                detailMouseReleased(evt);
            }
        });
        hcustomer.add(detail);
        detail.setBounds(850, 580, 400, 50);

        tgl.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tgl.setForeground(new java.awt.Color(48, 56, 65));
        tgl.setText("  ");
        hcustomer.add(tgl);
        tgl.setBounds(870, 355, 360, 27);

        jam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jam.setForeground(new java.awt.Color(48, 56, 65));
        hcustomer.add(jam);
        jam.setBounds(870, 418, 360, 27);

        kdrtr.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        kdrtr.setForeground(new java.awt.Color(48, 56, 65));
        hcustomer.add(kdrtr);
        kdrtr.setBounds(870, 230, 360, 27);

        kdtrans.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        kdtrans.setForeground(new java.awt.Color(48, 56, 65));
        hcustomer.add(kdtrans);
        kdtrans.setBounds(870, 290, 360, 27);

        caricus.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        caricus.setBorder(null);
        caricus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                caricusFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                caricusFocusLost(evt);
            }
        });
        caricus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caricusKeyReleased(evt);
            }
        });
        hcustomer.add(caricus);
        caricus.setBounds(400, 128, 420, 30);

        totalbar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        totalbar.setForeground(new java.awt.Color(48, 56, 65));
        hcustomer.add(totalbar);
        totalbar.setBounds(870, 543, 360, 27);

        jScrollPane3.setBorder(null);

        tablehcus.setModel(new javax.swing.table.DefaultTableModel(
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
        tablehcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablehcusMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablehcus);

        hcustomer.add(jScrollPane3);
        jScrollPane3.setBounds(290, 200, 530, 470);

        hapuscus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hapuscus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png"))); // NOI18N
        hapuscus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapuscusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapuscusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hapuscusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hapuscusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hapuscusMouseReleased(evt);
            }
        });
        hcustomer.add(hapuscus);
        hapuscus.setBounds(850, 630, 400, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/bghcustomer.png"))); // NOI18N
        jLabel3.setText("jLabel1");
        hcustomer.add(jLabel3);
        jLabel3.setBounds(270, 110, 990, 589);

        getContentPane().add(hcustomer);
        hcustomer.setBounds(0, 0, 1280, 750);

        dtlrtrsup.setBackground(new java.awt.Color(235, 235, 235));
        dtlrtrsup.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dtlrtrsup.setFocusCycleRoot(true);
        dtlrtrsup.setFocusTraversalPolicyProvider(true);
        dtlrtrsup.setMinimumSize(new java.awt.Dimension(1016, 615));
        dtlrtrsup.setLayout(null);

        tabledtlsup.setModel(new javax.swing.table.DefaultTableModel(
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
        tabledtlsup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabledtlsupMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabledtlsup);

        dtlrtrsup.add(jScrollPane7);
        jScrollPane7.setBounds(110, 70, 800, 430);

        dtlsup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dtlrtrsup.add(dtlsup);
        dtlsup.setBounds(340, 535, 180, 25);

        dtlkdrtr1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dtlrtrsup.add(dtlkdrtr1);
        dtlkdrtr1.setBounds(120, 535, 190, 25);

        dtlcusback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        dtlcusback1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtlcusback1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dtlcusback1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dtlcusback1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dtlcusback1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dtlcusback1MouseReleased(evt);
            }
        });
        dtlrtrsup.add(dtlcusback1);
        dtlcusback1.setBounds(750, 525, 160, 50);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/bgdtlretursup.png"))); // NOI18N
        dtlrtrsup.add(jLabel8);
        jLabel8.setBounds(0, 10, 1030, 600);

        getContentPane().add(dtlrtrsup);
        dtlrtrsup.setBounds(246, 100, 1040, 620);

        hsupplier.setBackground(new java.awt.Color(255, 255, 255));
        hsupplier.setLayout(null);

        back1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png"))); // NOI18N
        back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                back1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                back1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                back1MouseReleased(evt);
            }
        });
        hsupplier.add(back1);
        back1.setBounds(290, 120, 40, 50);

        kar1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        kar1.setForeground(new java.awt.Color(48, 56, 65));
        hsupplier.add(kar1);
        kar1.setBounds(870, 480, 360, 27);

        detail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png"))); // NOI18N
        detail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detail1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detail1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detail1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                detail1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                detail1MouseReleased(evt);
            }
        });
        hsupplier.add(detail1);
        detail1.setBounds(850, 580, 400, 50);

        tgl1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tgl1.setForeground(new java.awt.Color(48, 56, 65));
        tgl1.setText("  ");
        hsupplier.add(tgl1);
        tgl1.setBounds(870, 355, 360, 27);

        jam1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jam1.setForeground(new java.awt.Color(48, 56, 65));
        hsupplier.add(jam1);
        jam1.setBounds(870, 418, 360, 27);

        kdrtr1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        kdrtr1.setForeground(new java.awt.Color(48, 56, 65));
        hsupplier.add(kdrtr1);
        kdrtr1.setBounds(870, 230, 360, 27);

        namsup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        namsup.setForeground(new java.awt.Color(48, 56, 65));
        hsupplier.add(namsup);
        namsup.setBounds(870, 290, 360, 27);

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
        hsupplier.add(carisup);
        carisup.setBounds(400, 128, 420, 30);

        totalbar1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        totalbar1.setForeground(new java.awt.Color(48, 56, 65));
        hsupplier.add(totalbar1);
        totalbar1.setBounds(870, 543, 360, 27);

        jScrollPane6.setBorder(null);

        tablehsup.setModel(new javax.swing.table.DefaultTableModel(
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
        tablehsup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablehsupMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablehsup);

        hsupplier.add(jScrollPane6);
        jScrollPane6.setBounds(290, 200, 530, 470);

        hapussup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hapussup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png"))); // NOI18N
        hapussup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapussupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapussupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hapussupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hapussupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hapussupMouseReleased(evt);
            }
        });
        hsupplier.add(hapussup);
        hapussup.setBounds(850, 630, 400, 50);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/bghsup.png"))); // NOI18N
        jLabel4.setText("jLabel1");
        hsupplier.add(jLabel4);
        jLabel4.setBounds(270, 110, 990, 589);

        getContentPane().add(hsupplier);
        hsupplier.setBounds(0, 0, 1280, 750);

        dtlrtrcus.setBackground(new java.awt.Color(235, 235, 235));
        dtlrtrcus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dtlrtrcus.setFocusCycleRoot(true);
        dtlrtrcus.setFocusTraversalPolicyProvider(true);
        dtlrtrcus.setMinimumSize(new java.awt.Dimension(1016, 615));
        dtlrtrcus.setLayout(null);

        tabledtlcus.setModel(new javax.swing.table.DefaultTableModel(
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
        tabledtlcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabledtlcusMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabledtlcus);

        dtlrtrcus.add(jScrollPane4);
        jScrollPane4.setBounds(110, 70, 800, 370);

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        dtlket.setEditable(false);
        dtlket.setColumns(10);
        dtlket.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dtlket.setForeground(new java.awt.Color(48, 56, 65));
        dtlket.setLineWrap(true);
        dtlket.setRows(5);
        jScrollPane5.setViewportView(dtlket);

        dtlrtrcus.add(jScrollPane5);
        jScrollPane5.setBounds(550, 475, 195, 85);

        dtlcusprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print.png"))); // NOI18N
        dtlcusprint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtlcusprintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dtlcusprintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dtlcusprintMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dtlcusprintMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dtlcusprintMouseReleased(evt);
            }
        });
        dtlrtrcus.add(dtlcusprint);
        dtlcusprint.setBounds(760, 460, 160, 50);

        dtlgtot.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dtlgtot.setForeground(new java.awt.Color(48, 56, 65));
        dtlrtrcus.add(dtlgtot);
        dtlgtot.setBounds(340, 535, 180, 25);

        dtljam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dtljam.setForeground(new java.awt.Color(48, 56, 65));
        dtlrtrcus.add(dtljam);
        dtljam.setBounds(120, 535, 190, 25);

        dtlkdtrans.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dtlkdtrans.setForeground(new java.awt.Color(48, 56, 65));
        dtlrtrcus.add(dtlkdtrans);
        dtlkdtrans.setBounds(340, 475, 180, 25);

        dtlcusback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        dtlcusback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtlcusbackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dtlcusbackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dtlcusbackMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dtlcusbackMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dtlcusbackMouseReleased(evt);
            }
        });
        dtlrtrcus.add(dtlcusback);
        dtlcusback.setBounds(760, 520, 160, 50);

        dtlkdrtr.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dtlkdrtr.setForeground(new java.awt.Color(48, 56, 65));
        dtlrtrcus.add(dtlkdrtr);
        dtlkdrtr.setBounds(120, 475, 190, 25);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/bgdtlreturcus.png"))); // NOI18N
        dtlrtrcus.add(jLabel7);
        jLabel7.setBounds(0, 10, 1030, 600);

        getContentPane().add(dtlrtrcus);
        dtlrtrcus.setBounds(246, 100, 1040, 620);

        cart.setBackground(new java.awt.Color(255, 255, 255));
        cart.setLayout(null);

        tglcart.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tglcart.setForeground(new java.awt.Color(48, 56, 65));
        tglcart.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cart.add(tglcart);
        tglcart.setBounds(990, 117, 250, 50);

        backcart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/kembali.png"))); // NOI18N
        backcart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backcartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backcartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backcartMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backcartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                backcartMouseReleased(evt);
            }
        });
        cart.add(backcart);
        backcart.setBounds(960, 580, 290, 50);

        totalcart.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        totalcart.setForeground(new java.awt.Color(48, 56, 65));
        totalcart.setText("  ");
        cart.add(totalcart);
        totalcart.setBounds(980, 342, 250, 27);

        kdreturcart.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        kdreturcart.setForeground(new java.awt.Color(48, 56, 65));
        cart.add(kdreturcart);
        kdreturcart.setBounds(980, 230, 250, 27);

        supcart.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        supcart.setForeground(new java.awt.Color(48, 56, 65));
        cart.add(supcart);
        supcart.setBounds(980, 288, 250, 27);

        caribarcart.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        caribarcart.setBorder(null);
        caribarcart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                caribarcartFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                caribarcartFocusLost(evt);
            }
        });
        caribarcart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caribarcartKeyReleased(evt);
            }
        });
        cart.add(caribarcart);
        caribarcart.setBounds(375, 128, 480, 30);

        jScrollPane2.setBorder(null);

        tablecart.setModel(new javax.swing.table.DefaultTableModel(
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
        tablecart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablecartMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablecart);

        cart.add(jScrollPane2);
        jScrollPane2.setBounds(290, 200, 630, 470);

        prosesretur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prosesretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/prosesretur.png"))); // NOI18N
        prosesretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prosesreturMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prosesreturMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prosesreturMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                prosesreturMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                prosesreturMouseReleased(evt);
            }
        });
        cart.add(prosesretur);
        prosesretur.setBounds(960, 630, 290, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/bgcart (2).png"))); // NOI18N
        jLabel2.setText("jLabel1");
        cart.add(jLabel2);
        jLabel2.setBounds(270, 110, 990, 589);

        getContentPane().add(cart);
        cart.setBounds(0, 0, 1280, 750);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        reset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-alert.png"))); // NOI18N
        reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resetMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                resetMouseReleased(evt);
            }
        });
        jPanel1.add(reset);
        reset.setBounds(850, 580, 190, 50);

        tmbhcart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tmbhcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-1.png"))); // NOI18N
        tmbhcart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmbhcartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tmbhcartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tmbhcartMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmbhcartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tmbhcartMouseReleased(evt);
            }
        });
        jPanel1.add(tmbhcart);
        tmbhcart.setBounds(1060, 580, 190, 50);

        sup.setEditable(false);
        sup.setForeground(new java.awt.Color(48, 56, 65));
        sup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        sup.setMinimumSize(new java.awt.Dimension(221, 20));
        sup.setPreferredSize(new java.awt.Dimension(221, 20));
        sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supActionPerformed(evt);
            }
        });
        jPanel1.add(sup);
        sup.setBounds(290, 230, 530, 35);

        qty.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        qty.setForeground(new java.awt.Color(48, 56, 65));
        qty.setText("  ");
        jPanel1.add(qty);
        qty.setBounds(870, 355, 150, 27);

        hsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/Rectangle 24.png"))); // NOI18N
        hsup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hsupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hsupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hsupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hsupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hsupMouseReleased(evt);
            }
        });
        jPanel1.add(hsup);
        hsup.setBounds(760, 120, 230, 50);

        kdbar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        kdbar.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(kdbar);
        kdbar.setBounds(870, 230, 360, 27);

        namabar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        namabar.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(namabar);
        namabar.setBounds(870, 290, 360, 27);

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
        caribar.setBounds(340, 128, 350, 30);

        sat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        sat.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(sat);
        sat.setBounds(1080, 355, 150, 27);

        jScrollPane1.setBorder(null);

        tablemainretur.setModel(new javax.swing.table.DefaultTableModel(
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
        tablemainretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablemainreturMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablemainretur);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(290, 280, 530, 390);

        hcos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-2.png"))); // NOI18N
        hcos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hcosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hcosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hcosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hcosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hcosMouseReleased(evt);
            }
        });
        jPanel1.add(hcos);
        hcos.setBounds(1000, 120, 250, 50);

        lanjutco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lanjutco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action.png"))); // NOI18N
        lanjutco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lanjutcoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lanjutcoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lanjutcoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lanjutcoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lanjutcoMouseReleased(evt);
            }
        });
        jPanel1.add(lanjutco);
        lanjutco.setBounds(850, 630, 400, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/bg main.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(270, 110, 990, 589);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 750);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hcosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcosMouseEntered
        // TODO add your handling code here:
hcos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-2-hover.png")));
    }//GEN-LAST:event_hcosMouseEntered

    private void hcosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcosMouseExited
        // TODO add your handling code here:
hcos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-2.png")));
    }//GEN-LAST:event_hcosMouseExited

    private void hcosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcosMousePressed
        // TODO add your handling code here:
hcos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-2-clicked.png")));
    }//GEN-LAST:event_hcosMousePressed

    private void hcosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcosMouseReleased
        // TODO add your handling code here:
hcos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-2.png")));
    }//GEN-LAST:event_hcosMouseReleased

    private void lanjutcoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lanjutcoMouseEntered
        // TODO add your handling code here:
lanjutco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-hover.png")));
    }//GEN-LAST:event_lanjutcoMouseEntered

    private void lanjutcoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lanjutcoMouseExited
        // TODO add your handling code here;
lanjutco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action.png")));
    }//GEN-LAST:event_lanjutcoMouseExited

    private void lanjutcoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lanjutcoMousePressed
        // TODO add your handling code here:
lanjutco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-clicked.png")));
    }//GEN-LAST:event_lanjutcoMousePressed

    private void lanjutcoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lanjutcoMouseReleased
        // TODO add your handling code here:
lanjutco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action.png")));
    }//GEN-LAST:event_lanjutcoMouseReleased

    private void tablemainreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablemainreturMouseClicked
        // TODO add your handling code here:
        int baris = tablemainretur.rowAtPoint(evt.getPoint());
        String id = tablemainretur.getValueAt(baris, 0).toString(); 
        kdbar.setText(id);
        if (tablemainretur.getValueAt(baris, 1)==null) {
            namabar.setText("");
        } else {
            namabar.setText(tablemainretur.getValueAt(baris, 1).toString());
        }
        if (tablemainretur.getValueAt(baris, 2)==null) {
            qty.setText("");
        } else {
            qty.setText(tablemainretur.getValueAt(baris, 2).toString());
        }
        if (tablemainretur.getValueAt(baris, 3)==null) {
            sat.setText("");
        } else {
            sat.setText(tablemainretur.getValueAt(baris, 3).toString());
        }
    }//GEN-LAST:event_tablemainreturMouseClicked

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
        String supp = "";
        try {
            String sql = "SELECT id_supplier from supplier "
                    + "where nama_supplier = '" + sup.getSelectedItem() + "';";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            supp = rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    tablemainretur.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
        try {
            String sql = "SELECT detail_retur_cos.id_barang, barang.nama_barang, sum(detail_retur_cos.qty), satuan.satuan "
            + "from detail_retur_cos join retur_customer on detail_retur_cos.id_retur = retur_customer.id_retur_cus "
            + "join barang on detail_retur_cos.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_retur_cos.id_supplier = '"+supp+"' and detail_retur_cos.status = 'belum' and "
            + "barang.nama_barang like '%" + caribar.getText() + "%' "
            + "group by detail_retur_cos.id_barang ;";  
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getDouble(3),
                        res.getString(4)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablemainretur.setModel(model);
    }//GEN-LAST:event_caribarKeyReleased

    private void hcosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hcosMouseClicked
        // TODO add your handling code here:
        hcustomer.setVisible(true);
        jPanel1.setVisible(false);
        setTablehcus();
rcuskosong();
    }//GEN-LAST:event_hcosMouseClicked

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
 
    }//GEN-LAST:event_returMouseEntered

    private void returMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_returMouseExited

    private void returMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_returMousePressed

    private void returMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseReleased
        // TODO add your handling code here:
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
dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard.png")));
    }//GEN-LAST:event_dashboardMouseEntered

    private void dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseExited
        // TODO add your handling code here:
dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard.png")));
    }//GEN-LAST:event_dashboardMouseExited

    private void dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMousePressed
        // TODO add your handling code here:
dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard.png")));
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

    private void lanjutcoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lanjutcoMouseClicked
        // TODO add your handling code here:
        try {
            String sql = "SELECT COUNT(id_barang) as totbar from cart_retur_supplier" +
                    " WHERE id_retur_sup = '" + kdreturcart.getText() + "' ;";
            Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            res.next();
            totalcart.setText(res.getString("totbar"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setTablecart();
        setkdretursup();
        cart.setVisible(true);
        jPanel1.setVisible(false);
    }//GEN-LAST:event_lanjutcoMouseClicked

    private void tmbhcartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhcartMouseClicked
        // TODO add your handling code here:
        if (kdbar.getText().equals("")) {
            panggilAlertMerah("Gagal!","Pilih dahulu barang yang ingin diretur");
        } else { 
            try {
                    String sqll = "INSERT INTO `cart_retur_supplier` (`id_retur_sup`, `id_barang`, `qty`)" +
                            " VALUES ('" + kdreturcart.getText() + "', '"
                            + kdbar.getText() + "', '"
                            + qty.getText() + "') ; ";
                    java.sql.Connection conn = (Connection) Config.configDB();
                    java.sql.PreparedStatement pstl = conn.prepareStatement(sqll);
                    pstl.execute();
                    kosong();
                    setTablecart();
                    tablemainretur.fixTable(jScrollPane1);
                    DefaultTableModel model = (DefaultTableModel) tablemainretur.getModel();
                    if(tablemainretur.getSelectedRow() != -1) {
               // remove selected row from the model
               model.removeRow(tablemainretur.getSelectedRow());
//               panggilAlertHijau(null, "Selected row deleted successfully");
            }
                } catch (Exception e) {
                e.printStackTrace();
                        panggilAlertMerah("Gagal", e.getMessage());
                }

//    cart.setVisible(true);
//    jPanel1.setVisible(false);
}
    }//GEN-LAST:event_tmbhcartMouseClicked

    private void tmbhcartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhcartMouseEntered
        // TODO add your handling code here:
tmbhcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-1-hover.png")));
    }//GEN-LAST:event_tmbhcartMouseEntered

    private void tmbhcartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhcartMouseExited
        // TODO add your handling code here:
tmbhcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-1.png")));
    }//GEN-LAST:event_tmbhcartMouseExited

    private void tmbhcartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhcartMousePressed
        // TODO add your handling code here:
tmbhcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-1-clicked.png")));
    }//GEN-LAST:event_tmbhcartMousePressed

    private void tmbhcartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhcartMouseReleased
        // TODO add your handling code here:
tmbhcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-action-1.png")));
    }//GEN-LAST:event_tmbhcartMouseReleased

    private void hsupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hsupMouseClicked
        // TODO add your handling code here:
hsupplier.setVisible(true);
jPanel1.setVisible(false);
setTablehsup();
rsupkosong();
    }//GEN-LAST:event_hsupMouseClicked

    private void hsupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hsupMouseEntered
        // TODO add your handling code here:
hsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/Rectangle 24-hover.png")));
    }//GEN-LAST:event_hsupMouseEntered

    private void hsupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hsupMouseExited
        // TODO add your handling code here:
hsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/Rectangle 24.png")));
    }//GEN-LAST:event_hsupMouseExited

    private void hsupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hsupMousePressed
        // TODO add your handling code here:
hsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/Rectangle 24-clicked.png")));
    }//GEN-LAST:event_hsupMousePressed

    private void hsupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hsupMouseReleased
        // TODO add your handling code here:
hsup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/Rectangle 24.png")));
    }//GEN-LAST:event_hsupMouseReleased

    private void resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMouseClicked
        // TODO add your handling code here:
AlertPertanyaanHijau obj = new AlertPertanyaanHijau(this);
        obj.showMessage("Konfirmasi Reset", "apakah anda yakin untuk menghapus semua keranjang retur?");
        if (obj.getMessageType() == AlertPertanyaanHijau.MessageType.OK) {
bersihcart();
}
    }//GEN-LAST:event_resetMouseClicked

    private void resetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMouseEntered
        // TODO add your handling code here:
reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-alert-hover.png")));
    }//GEN-LAST:event_resetMouseEntered

    private void resetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMouseExited
        // TODO add your handling code here:
reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-alert.png")));
    }//GEN-LAST:event_resetMouseExited

    private void resetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMousePressed
        // TODO add your handling code here:
reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-alert-clicked.png")));
    }//GEN-LAST:event_resetMousePressed

    private void resetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMouseReleased
        // TODO add your handling code here:
reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/btn-alert.png")));
    }//GEN-LAST:event_resetMouseReleased

    private void supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supActionPerformed
        // TODO add your handling code here:
settablemain();
supcart.setText((String) sup.getSelectedItem());
    }//GEN-LAST:event_supActionPerformed

    private void caribarcartFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribarcartFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_caribarcartFocusGained

    private void caribarcartFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribarcartFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_caribarcartFocusLost

    private void caribarcartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caribarcartKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_caribarcartKeyReleased

    private void tablecartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablecartMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablecartMouseClicked

    private void prosesreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesreturMouseClicked
        // TODO add your handling code here:
        DefaultTableModel cartmodel = (DefaultTableModel) tablecart.getModel();
        if (cartmodel.getRowCount ()==0) {
            panggilAlertMerah("Gagal!", "Table masih kosong");
        } else {
                    AlertPertanyaanHijau obj = new AlertPertanyaanHijau(this);
        obj.showMessage("Konfirmasi Retur", "apakah anda yakin untuk checkout Retur Ke Supplier?");
        if (obj.getMessageType() == AlertPertanyaanHijau.MessageType.OK) {
            rcekout();
        }
    }
    }//GEN-LAST:event_prosesreturMouseClicked

    private void prosesreturMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesreturMouseEntered
        // TODO add your handling code here:
prosesretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/prosesretur-hover.png")));
    }//GEN-LAST:event_prosesreturMouseEntered

    private void prosesreturMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesreturMouseExited
        // TODO add your handling code here:
prosesretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/prosesretur.png")));
    }//GEN-LAST:event_prosesreturMouseExited

    private void prosesreturMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesreturMousePressed
        // TODO add your handling code here:
prosesretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/prosesretur-clicked.png")));
    }//GEN-LAST:event_prosesreturMousePressed

    private void prosesreturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesreturMouseReleased
        // TODO add your handling code here:
prosesretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/prosesretur.png")));
    }//GEN-LAST:event_prosesreturMouseReleased

    private void backcartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backcartMouseClicked
        // TODO add your handling code here:
cart.setVisible(false);
jPanel1.setVisible(true);
    }//GEN-LAST:event_backcartMouseClicked

    private void backcartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backcartMouseEntered
        // TODO add your handling code here:
backcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/kembali-hover.png")));
    }//GEN-LAST:event_backcartMouseEntered

    private void backcartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backcartMouseExited
        // TODO add your handling code here:
backcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/kembali.png")));
    }//GEN-LAST:event_backcartMouseExited

    private void backcartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backcartMousePressed
        // TODO add your handling code here:
backcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/kembali-clicked.png")));
    }//GEN-LAST:event_backcartMousePressed

    private void backcartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backcartMouseReleased
        // TODO add your handling code here:
backcart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/retur/kembali.png")));
    }//GEN-LAST:event_backcartMouseReleased

    private void dtlcusbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusbackMouseClicked
        // TODO add your handling code here:
        dtlrtrcus.setVisible(false);
        hcustomer.setVisible(true);
        rcuskosong();
    }//GEN-LAST:event_dtlcusbackMouseClicked

    private void dtlcusbackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusbackMouseEntered
        // TODO add your handling code here:
        dtlcusback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_dtlcusbackMouseEntered

    private void dtlcusbackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusbackMouseExited
        // TODO add your handling code here:
        dtlcusback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_dtlcusbackMouseExited

    private void dtlcusbackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusbackMousePressed
        // TODO add your handling code here:
        dtlcusback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_dtlcusbackMousePressed

    private void dtlcusbackMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusbackMouseReleased
        // TODO add your handling code here:
        dtlcusback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_dtlcusbackMouseReleased

    private void detailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMouseClicked
        // TODO add your handling code here:
        if (kdrtr.getText().equals("")) {
            panggilAlertMerah("Lihat Gagal!","Pilih dahulu Retur yang ingin dilihat");
        } else {
            dtlkdrtr.setText(kdrtr.getText());
            dtlkdtrans.setText(kdtrans.getText());
            dtljam.setText(jam.getText());
            setTabledtlcus();
            try {
            String sql = "SELECT sum(sub_total) as total from detail_retur_cos "
                    + "where id_retur = '"+kdrtr.getText()+"' ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            RP rupiah = new RP();
            dtlgtot.setText(rupiah.formatRupiah((int) rs.getInt("total")).replace(",00", ""));

            } catch (SQLException e) {
            e.printStackTrace();
            }
            dtlrtrcus.setVisible(true);
            hcustomer.setVisible(false);
        }
    }//GEN-LAST:event_detailMouseClicked

    private void detailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMouseEntered
        // TODO add your handling code here:
        detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail-hover.png")));
    }//GEN-LAST:event_detailMouseEntered

    private void detailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMouseExited
        // TODO add your handling code here:
        detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png")));
    }//GEN-LAST:event_detailMouseExited

    private void detailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMousePressed
        // TODO add your handling code here:
        detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail-clicked.png")));
    }//GEN-LAST:event_detailMousePressed

    private void detailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMouseReleased
        // TODO add your handling code here:
        detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png")));
    }//GEN-LAST:event_detailMouseReleased

    private void caricusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caricusFocusGained
        // TODO add your handling code here:
        if (caricus.getText().equals("Cari Kode Transaksi")) {
            caricus.setForeground(new java.awt.Color(51,51,51));
            caricus.setText("");
        } else {
        }
    }//GEN-LAST:event_caricusFocusGained

    private void caricusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caricusFocusLost
        // TODO add your handling code here:
        if (caricus.getText().equals("")) {
            caricus.setForeground(new java.awt.Color(221,221,221));
            caricus.setText("Cari Kode Transaksi");
        } else {
        }
    }//GEN-LAST:event_caricusFocusLost

    private void caricusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caricusKeyReleased
        // TODO add your handling code here:
    tablehcus.fixTable(jScrollPane3);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Retur");
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
        try {
            String sql = "select * from retur_customer "
            + "where id_penjualan like '%" + caricus.getText() + "%'"
            + "order by tanggal desc ; ";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    tablehcus.setModel(model);
    }//GEN-LAST:event_caricusKeyReleased

    private void tablehcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablehcusMouseClicked
        // TODO add your handling code here:
        int baris = tablehcus.rowAtPoint(evt.getPoint());
        String id = tablehcus.getValueAt(baris, 0).toString();
        kdrtr.setText(id);
        if (tablehcus.getValueAt(baris, 1)==null) {
            kdtrans.setText("");
        } else {
            kdtrans.setText(tablehcus.getValueAt(baris, 1).toString());
        }
        if (tablehcus.getValueAt(baris, 2)==null) {
            tgl.setText("");
        } else {
            tgl.setText(tablehcus.getValueAt(baris, 2).toString());
        }
        if (tablehcus.getValueAt(baris, 3)==null) {
            jam.setText("");
        } else {
            jam.setText(tablehcus.getValueAt(baris, 3).toString());
        }
        if (tablehcus.getValueAt(baris, 4)==null) {
            kar.setText("");
        } else {
            kar.setText(tablehcus.getValueAt(baris, 4).toString());
        }
            try {
                String sql = "SELECT count(id_barang) as total from detail_retur_cos "
                        + "where id_retur = '"+kdrtr.getText()+"' ;" ;
                java.sql.Connection conn = (Connection) Config.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                totalbar.setText(rs.getString(1));

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_tablehcusMouseClicked

    private void hapuscusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuscusMouseClicked
        // TODO add your handling code here:
        if (kdrtr.getText().equals("")) {
            panggilAlertMerah("Hapus Gagal !","pilih dahulu Kode Retur yang ingin dihapus");
        } else {
            AlertPertanyaan obj = new AlertPertanyaan(this);
            obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Riawayat Retur Dengan Kode \n"+kdrtr.getText()+"?");
            if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
                try {
                    String sql ="DELETE FROM retur_customer where id_retur_cus ='"+kdrtr.getText()+"'";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    panggilAlertHijau("Hapus Berhasil !","Data Riwayat Retur Dengan Kode "+kdrtr.getText()+" berhasil dihapus");
                    kosong();
                } catch (Exception e) {
                    panggilAlertHijau("Hapus Gagal !", e.getMessage());
                }

            }

        }
    }//GEN-LAST:event_hapuscusMouseClicked

    private void hapuscusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuscusMouseEntered
        // TODO add your handling code here:
        hapuscus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang-hover.png")));
    }//GEN-LAST:event_hapuscusMouseEntered

    private void hapuscusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuscusMouseExited
        // TODO add your handling code here:
        hapuscus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hapuscusMouseExited

    private void hapuscusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuscusMousePressed
        // TODO add your handling code here:
        hapuscus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang-clicked.png")));
    }//GEN-LAST:event_hapuscusMousePressed

    private void hapuscusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapuscusMouseReleased
        // TODO add your handling code here:
        hapuscus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hapuscusMouseReleased

    private void tabledtlcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabledtlcusMouseClicked
        // TODO add your handling code here:
        int baris = tabledtlcus.rowAtPoint(evt.getPoint());
        String id = tabledtlcus.getValueAt(baris, 0).toString();
            try {
            String sql = "SELECT keterangan from detail_retur_cos "
                    + "where id_retur = '"+dtlkdrtr.getText()+"' and id_barang = '"+id+"' ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            RP rupiah = new RP();
            dtlket.setText(rs.getString(1));

            } catch (SQLException e) {
            e.printStackTrace();
            }
    }//GEN-LAST:event_tabledtlcusMouseClicked

    private void dtlcusprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusprintMouseClicked
        // TODO add your handling code here:
        AlertPertanyaanHijau obj = new AlertPertanyaanHijau(this);
        obj.showMessage("Konfirmasi Cetak", "apakah anda yakin untuk mencetak Retur kembali?");
        if (obj.getMessageType() == AlertPertanyaanHijau.MessageType.OK) {
                     try {
            java.sql.Connection conn=(com.mysql.jdbc.Connection)Config.configDB();
//        String namafile = "src/kasir/report.jrxml";
            InputStream report = getClass().getResourceAsStream("retur.jasper");
            HashMap param = new HashMap();
            param.put("kd_retur", dtlkdrtr.getText());
//        File file = new File(namafile);
//        JasperDesign jd = JRXmlLoader.load(namafile);
//        JasperReport jr = JasperCompileManager.compileReport(jd);
//            String reportPath = report;
//            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(report,param,conn);
            JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            e.printStackTrace();
            panggilAlertMerah("Gagal!", e.getMessage());
        }
    }
    }//GEN-LAST:event_dtlcusprintMouseClicked

    private void dtlcusprintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusprintMouseEntered
        // TODO add your handling code here:
dtlcusprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print-hover.png")));
    }//GEN-LAST:event_dtlcusprintMouseEntered

    private void dtlcusprintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusprintMouseExited
        // TODO add your handling code here:
dtlcusprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print.png")));
    }//GEN-LAST:event_dtlcusprintMouseExited

    private void dtlcusprintMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusprintMousePressed
        // TODO add your handling code here:
dtlcusprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print-clicked.png")));
    }//GEN-LAST:event_dtlcusprintMousePressed

    private void dtlcusprintMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusprintMouseReleased
        // TODO add your handling code here:
dtlcusprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print.png")));
    }//GEN-LAST:event_dtlcusprintMouseReleased

    private void detail1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detail1MouseClicked
        // TODO add your handling code here:
        if (kdrtr1.getText().equals("")) {
            panggilAlertMerah("Lihat Gagal!","Pilih dahulu Retur yang ingin dilihat");
        } else {
            dtlkdrtr1.setText(kdrtr1.getText());
            dtlsup.setText(namsup.getText());
            setTabledtlsup();
            dtlrtrsup.setVisible(true);
            hsupplier.setVisible(false);
        }
    }//GEN-LAST:event_detail1MouseClicked

    private void detail1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detail1MouseEntered
        // TODO add your handling code here:
detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail-hover.png")));
    }//GEN-LAST:event_detail1MouseEntered

    private void detail1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detail1MouseExited
        // TODO add your handling code here:
detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png")));
    }//GEN-LAST:event_detail1MouseExited

    private void detail1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detail1MousePressed
        // TODO add your handling code here:
detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail-clicked.png")));
    }//GEN-LAST:event_detail1MousePressed

    private void detail1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detail1MouseReleased
        // TODO add your handling code here:
detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png")));
    }//GEN-LAST:event_detail1MouseReleased

    private void carisupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carisupFocusGained
        // TODO add your handling code here:
        if (carisup.getText().equals("Cari Kode Retur")) {
            carisup.setForeground(new java.awt.Color(51,51,51));
            carisup.setText("");
        } else {
        }
    }//GEN-LAST:event_carisupFocusGained

    private void carisupFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carisupFocusLost
        // TODO add your handling code here:
        if (carisup.getText().equals("")) {
            carisup.setForeground(new java.awt.Color(221,221,221));
            carisup.setText("Cari Kode Retur");
        } else {
        }
    }//GEN-LAST:event_carisupFocusLost

    private void carisupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carisupKeyReleased
        // TODO add your handling code here:
    tablehsup.fixTable(jScrollPane6);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Retur");
    model.addColumn("Supplier");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
        try {
            String sql = "select retur_supplier.id_retur_sup, supplier.nama_supplier, "
            + "retur_supplier.tanggal_balik, retur_supplier.jam, retur_supplier.id_pengguna from retur_supplier "
            + "join supplier on retur_supplier.id_supplier = supplier.id_supplier "
 + "where retur_supplier.id_retur_sup like '%" + carisup.getText() + "%'"
            + "order by tanggal_balik desc ; ";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehsup.setModel(model);
    }//GEN-LAST:event_carisupKeyReleased

    private void tablehsupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablehsupMouseClicked
        // TODO add your handling code here:
        int baris = tablehsup.rowAtPoint(evt.getPoint());
        String id = tablehsup.getValueAt(baris, 0).toString();
        kdrtr1.setText(id);
        if (tablehsup.getValueAt(baris, 1)==null) {
            namsup.setText("");
        } else {
            namsup.setText(tablehsup.getValueAt(baris, 1).toString());
        }
        if (tablehsup.getValueAt(baris, 2)==null) {
            tgl1.setText("");
        } else {
            tgl1.setText(tablehsup.getValueAt(baris, 2).toString());
        }
        if (tablehsup.getValueAt(baris, 3)==null) {
            jam1.setText("");
        } else {
            jam1.setText(tablehsup.getValueAt(baris, 3).toString());
        }
        if (tablehsup.getValueAt(baris, 4)==null) {
            kar1.setText("");
        } else {
            kar1.setText(tablehsup.getValueAt(baris, 4).toString());
        }
            try {
                String sql = "SELECT count(id_barang) as total from detail_retur_supplier "
                        + "where id_retur_sup = '"+kdrtr1.getText()+"' ;" ;
                java.sql.Connection conn = (Connection) Config.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                totalbar1.setText(rs.getString(1));

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_tablehsupMouseClicked

    private void hapussupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapussupMouseClicked
        // TODO add your handling code here:
        if (kdrtr1.getText().equals("")) {
            panggilAlertMerah("Hapus Gagal !","pilih dahulu Kode Retur yang ingin dihapus");
        } else {
            AlertPertanyaan obj = new AlertPertanyaan(this);
            obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Riawayat Retur Dengan Kode \n"+kdrtr1.getText()+"?");
            if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
                try {
                    String sql ="DELETE FROM retur_supplier where id_retur_sup ='"+kdrtr1.getText()+"'";
                    java.sql.Connection conn=(Connection)Config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    panggilAlertHijau("Hapus Berhasil !","Data Riwayat Retur Dengan Kode "+kdrtr1.getText()+" berhasil dihapus");
                    kosong();
                } catch (Exception e) {
                    panggilAlertHijau("Hapus Gagal !", e.getMessage());
                }

            }

        }
    }//GEN-LAST:event_hapussupMouseClicked

    private void hapussupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapussupMouseEntered
        // TODO add your handling code here:
hapussup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang-hover.png")));
    }//GEN-LAST:event_hapussupMouseEntered

    private void hapussupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapussupMouseExited
        // TODO add your handling code here:
hapussup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hapussupMouseExited

    private void hapussupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapussupMousePressed
        // TODO add your handling code here:
hapussup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang-clicked.png")));
    }//GEN-LAST:event_hapussupMousePressed

    private void hapussupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapussupMouseReleased
        // TODO add your handling code here:
hapussup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hapussupMouseReleased

    private void tabledtlsupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabledtlsupMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabledtlsupMouseClicked

    private void dtlcusback1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusback1MouseClicked
        // TODO add your handling code here:
            dtlrtrsup.setVisible(false);
            hsupplier.setVisible(true);
            rsupkosong();
    }//GEN-LAST:event_dtlcusback1MouseClicked

    private void dtlcusback1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusback1MouseEntered
        // TODO add your handling code here:
dtlcusback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_dtlcusback1MouseEntered

    private void dtlcusback1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusback1MouseExited
        // TODO add your handling code here:
dtlcusback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_dtlcusback1MouseExited

    private void dtlcusback1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusback1MousePressed
        // TODO add your handling code here:
dtlcusback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_dtlcusback1MousePressed

    private void dtlcusback1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlcusback1MouseReleased
        // TODO add your handling code here:
dtlcusback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_dtlcusback1MouseReleased

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        hcustomer.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_backMouseClicked

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

    private void back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseClicked
        // TODO add your handling code here:
        hsupplier.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_back1MouseClicked

    private void back1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseEntered
        // TODO add your handling code here:
back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back-hover.png")));
    }//GEN-LAST:event_back1MouseEntered

    private void back1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseExited
        // TODO add your handling code here:
back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png")));
    }//GEN-LAST:event_back1MouseExited

    private void back1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MousePressed
        // TODO add your handling code here:
back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back-clicked.png")));
    }//GEN-LAST:event_back1MousePressed

    private void back1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseReleased
        // TODO add your handling code here:
back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png")));
    }//GEN-LAST:event_back1MouseReleased

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

    private void karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseClicked
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Retur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Retur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Retur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Retur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Retur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JLabel back;
    private javax.swing.JLabel back1;
    private javax.swing.JLabel backcart;
    private javax.swing.JTextField caribar;
    private javax.swing.JTextField caribarcart;
    private javax.swing.JTextField caricus;
    private javax.swing.JTextField carisup;
    private javax.swing.JPanel cart;
    private javax.swing.JLabel dashboard;
    private com.raven.datechooser.DateChooser dateChooser;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel detail;
    private javax.swing.JLabel detail1;
    private javax.swing.JLabel dtlcusback;
    private javax.swing.JLabel dtlcusback1;
    private javax.swing.JLabel dtlcusprint;
    private javax.swing.JLabel dtlgtot;
    private javax.swing.JLabel dtljam;
    private javax.swing.JLabel dtlkdrtr;
    private javax.swing.JLabel dtlkdrtr1;
    private javax.swing.JLabel dtlkdtrans;
    private javax.swing.JTextArea dtlket;
    private javax.swing.JPanel dtlrtrcus;
    private javax.swing.JPanel dtlrtrsup;
    private javax.swing.JLabel dtlsup;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel hapuscus;
    private javax.swing.JLabel hapussup;
    private javax.swing.JLabel hcos;
    private javax.swing.JPanel hcustomer;
    private javax.swing.JLabel header;
    private javax.swing.JLabel hsup;
    private javax.swing.JPanel hsupplier;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel jam;
    private javax.swing.JLabel jam1;
    private javax.swing.JLabel jenis;
    private javax.swing.JLabel kar;
    private javax.swing.JLabel kar1;
    private javax.swing.JLabel karyawan;
    private javax.swing.JLabel kdbar;
    private javax.swing.JLabel kdreturcart;
    private javax.swing.JLabel kdrtr;
    private javax.swing.JLabel kdrtr1;
    private javax.swing.JLabel kdtrans;
    private javax.swing.JLabel lanjutco;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private javax.swing.JLabel namabar;
    private javax.swing.JLabel namsup;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JLabel prosesretur;
    private javax.swing.JLabel qty;
    private javax.swing.JLabel reset;
    private javax.swing.JLabel retur;
    private javax.swing.JLabel riwayat;
    private javax.swing.JLabel sat;
    private combo_suggestion.ComboBoxSuggestion sup;
    private javax.swing.JLabel supcart;
    public static tabledark.TableDark tablecart;
    private tabledark.TableDark tabledtlcus;
    private tabledark.TableDark tabledtlsup;
    public static tabledark.TableDark tablehcus;
    public static tabledark.TableDark tablehsup;
    public static tabledark.TableDark tablemainretur;
    private javax.swing.JLabel tgl;
    private javax.swing.JLabel tgl1;
    private javax.swing.JLabel tglcart;
    private javax.swing.JLabel tmbhcart;
    private javax.swing.JLabel totalbar;
    private javax.swing.JLabel totalbar1;
    private javax.swing.JLabel totalcart;
    private javax.swing.JLabel transaksi;
    // End of variables declaration//GEN-END:variables
}
