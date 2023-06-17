/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.gui;

import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javaswingdev.message.JSystemFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author yogan
 */
public class RiwayatJual extends javax.swing.JFrame {
String date1, date2, rbar, rbar2="";
private ImageIcon format = null;
    /**
     * Creates new form Karyawan
     */
    public RiwayatJual() {
        initComponents();
        settablehjual();
        Cursor();
        datecus();
        setFavIcon();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        jualdetail.setVisible(false);
        exportjual.setVisible(false);
        paneretur.setVisible(false);
        popupretur.setVisible(false);
        coretur.setVisible(false);
        hjcari.setForeground(new java.awt.Color(221,221,221));
        hjcari.setText("Cari Kode Transaksi");
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
        karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjswitch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjdetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjprint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjexport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjhps.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjretur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hpsretur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        returback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rcaribar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rcekout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahretur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        h.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rpopback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printretur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
         hjdetailkembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjdetailprint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjexportexport1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjexportkembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjexportexport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rpoptambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

public void datecus() {
        dateChooser.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                date1 = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    dateChooser.hidePopup();
                }
            }
        });
        dateChooser1.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                date2 = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    dateChooser1.hidePopup();
                }
            }
        });
}

public void openFile(String file) {
try {
        File path = new File(file);
        Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
        System.out.println(ioe);
}
}

public void exportExcel() {
if (date1 == null || date2 == null ) {
    panggilAlertMerah("Export gagal", "Pilih tanggal terlebih dahulu");
} else {
try {
JSystemFileChooser jFileChooser = new JSystemFileChooser();
jFileChooser.showSaveDialog(this);
File savefile = jFileChooser.getSelectedFile();
if(savefile != null) {
savefile = new File(savefile.toString()+".xls");

        try {
            String sql = "SELECT * from penjualan " +
                    "where tanggal_transaksi BETWEEN '" + date1 + "' AND '" + date2 + "' ;";
            Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Report Penjualan");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Kode Transaksi");
            header.createCell(1).setCellValue("Tanggal");
            header.createCell(2).setCellValue("Jam");
            header.createCell(3).setCellValue("Nama Kasir");
            header.createCell(4).setCellValue("Total Pembayaran");

            int index = 1;
            while (res.next()) {
                Row row = sheet.createRow(index);
                row.createCell(0).setCellValue(res.getString("id_penjualan"));
                row.createCell(1).setCellValue(res.getString("tanggal_transaksi"));
                row.createCell(2).setCellValue(res.getString("jam_transaksi"));
                row.createCell(3).setCellValue(res.getString("id_pengguna"));
                row.createCell(4).setCellValue(res.getDouble("total_bayar"));
                index++;
            }
            FileOutputStream fileout = new FileOutputStream(new File(savefile.toString()));
            workbook.write(fileout);
            workbook.close();
            fileout.close();
            openFile(savefile.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
} else {
//panggilAlertMerah(null, "Error");
}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    void setcotgl() {
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
        cotgl.setText(aa + " " + mm + " " + yyyy);
    }

    void bersihcart() {
                    try {
                        String sqll = "TRUNCATE TABLE cart_retur_cus;";
                        java.sql.Connection connn = (Connection) Config.configDB();
                        java.sql.PreparedStatement pstl = connn.prepareStatement(sqll);
                        pstl.execute();
                    } catch (Exception e) {
                    }
}

    void rcekout() {
        try {
            String sqll = "insert into retur_customer (id_retur_cus, id_penjualan, tanggal, jam, id_karyawan ) " +
                    "values ('" + rkdrtr.getText() + "', '" + rkdtrans.getText() + "', CURRENT_DATE, CURRENT_TIME, '"+nama.getText()+"') ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pstl = conn.prepareStatement(sqll);
            pstl.execute();
            String sql = "insert into detail_retur_cos (`id_retur`, `id_barang`, "
                    + "`qty`, `sub_total`, `keterangan`, `status`, `id_supplier`) "
                    + "SELECT id_retur, id_barang, qty, sub_total, keterangan, status, id_supplier FROM cart_retur_cus ;";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
coretur.setVisible(true);
paneretur.setVisible(false);
            bersihcart();
        } catch (Exception e) {
e.printStackTrace();
            panggilAlertMerah("Gagal ", e.getMessage());
        }

    }

void kosong() {
hjtfkdtrans.setText("");
hjtfkasir.setText(null);
hjtfjam.setText(null);
hjtftgl.setText(null);
hjtftotal.setText(null);

}

void rkosong() {
rbar2 = "";
rnamabar.setText("");
rharga.setText(null);
rqty.setText(null);
rtotal.setText(null);
rtfqty.setText("");
rket.setText(null);
rsup.removeAllItems();
}

    void setrkdrtr() {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy-");
        LocalDateTime tanggal = LocalDateTime.now();
        String a = (dtf.format(tanggal));
        DateTimeFormatter dtff = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime tanggall = LocalDateTime.now();
        String aa = (dtff.format(tanggall));
        try {
            //--> melakukan eksekusi query untuk mengambil data dari tabel
            String sql = "SELECT MAX(id_retur_cus) FROM retur_customer " +
                    "WHERE tanggal = '" + aa + "' ;" ;
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    rkdrtr.setText("RT-" + a + "001");
                } else {
                    rs.last();
                    String auto = rs.getString(1);
                    auto = auto.replace("RT-" + a, "");
                    int auto_id = Integer.parseInt(auto) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 3 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    rkdrtr.setText("RT-" + a + no);
                }
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void settablehjexport(){
    tablehjexport.fixTable(jScrollPane5);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
    model.addColumn("Grand Total");
        try {
            String sql = "SELECT * from penjualan ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_penjualan"),
                        res.getString("tanggal_transaksi"),
                        res.getString("jam_transaksi"),
                        res.getString("id_pengguna"),
                        res.getInt("total_bayar")
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehjexport.setModel(model);
    }

    void settablepopretur(){
    tablepopretur.fixTable(jScrollPane6);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Harga");
    model.addColumn("Retur");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Sub Total");
        try {
            String sql = "SELECT detail_penjualan.id_barang, "
            + "barang.nama_barang, detail_penjualan.harga, detail_penjualan.qty, satuan.satuan, detail_penjualan.harga_total, barang.retur "
            + "from detail_penjualan join barang on detail_penjualan.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_penjualan.id_penjualan = '"+rkdtrans.getText()+"' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getString(7),
                        res.getDouble(4),
                        res.getString(5),
                        res.getInt(6)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablepopretur.setModel(model);
    }

    void settablecoretur(){
    tablecoretur.fixTable(jScrollPane7);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Total");
        try {
            String sql = "SELECT detail_retur_cos.id_barang, barang.nama_barang, detail_retur_cos.qty, detail_retur_cos.sub_total, "
            + "satuan.satuan from detail_retur_cos join barang on detail_retur_cos.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_retur_cos.id_retur = '"+rkdrtr.getText()+"' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getDouble(3),
                        res.getString(5),
                        res.getInt(4)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablecoretur.setModel(model);
    }

    void settablecartretur(){
    tablecartretur.fixTable(jScrollPane2);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Retur");
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Total");
        try {
            String sql = "SELECT cart_retur_cus.id_retur, cart_retur_cus.id_barang, barang.nama_barang, cart_retur_cus.qty, cart_retur_cus.sub_total, "
            + "satuan.satuan from cart_retur_cus join barang on cart_retur_cus.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where cart_retur_cus.id_retur = '"+rkdrtr.getText()+"' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getDouble(4),
                        res.getString(6),
                        res.getInt(5),
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablecartretur.setModel(model);
    }

    void settablehjual(){
    tablehjual.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
    model.addColumn("Grand Total");
        try {
            String sql = "SELECT * from penjualan "
            + "order by tanggal_transaksi desc ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_penjualan"),
                        res.getString("tanggal_transaksi"),
                        res.getString("jam_transaksi"),
                        res.getString("id_pengguna"),
                        res.getInt("total_bayar")
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehjual.setModel(model);
    }

    void setTablehjdetail(){
    tablehjdetail.fixTable(jScrollPane4);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Harga");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Sub Total");
        try {
            String sql = "select detail_penjualan.id_barang, barang.nama_barang, " +
                    "detail_penjualan.harga, detail_penjualan.qty, barang.id_satuan, detail_penjualan.harga_total " +
                    "from detail_penjualan join barang on detail_penjualan.id_barang = barang.id_barang"
                    + " where id_penjualan = '" + hjtfkdtrans.getText() + "' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getDouble(4),
                        res.getString(5),
                        res.getInt(6)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehjdetail.setModel(model);
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
        paneretur = new javax.swing.JPanel();
        hpsretur = new javax.swing.JLabel();
        rsup = new combo_suggestion.ComboBoxSuggestion();
        jScrollPane3 = new javax.swing.JScrollPane();
        rket = new javax.swing.JTextArea();
        rkdtrans = new javax.swing.JLabel();
        rkdrtr = new javax.swing.JLabel();
        returback = new javax.swing.JLabel();
        rnamabar1 = new javax.swing.JLabel();
        rqty = new javax.swing.JLabel();
        rtotal = new javax.swing.JLabel();
        rnamabar = new javax.swing.JLabel();
        rharga = new javax.swing.JLabel();
        rtfqty = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablecartretur = new tabledark.TableDark();
        rcaribar = new javax.swing.JLabel();
        rcekout = new javax.swing.JLabel();
        tambahretur = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        coretur = new javax.swing.JPanel();
        rnamabar2 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        rcokdrtr = new javax.swing.JLabel();
        caribar = new javax.swing.JTextField();
        printretur = new javax.swing.JLabel();
        cototal = new javax.swing.JLabel();
        cotgl = new javax.swing.JLabel();
        cokar = new javax.swing.JLabel();
        txtid10 = new javax.swing.JLabel();
        cojmlbar = new javax.swing.JLabel();
        cokdtrans = new javax.swing.JLabel();
        txtid1 = new javax.swing.JLabel();
        txtid2 = new javax.swing.JLabel();
        txtid3 = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablecoretur = new tabledark.TableDark();
        jLabel6 = new javax.swing.JLabel();
        exportjual = new javax.swing.JPanel();
        hjexporttglsampai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        hjexportexport1 = new javax.swing.JLabel();
        hjexporttgldari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablehjexport = new tabledark.TableDark();
        hjexportkembali = new javax.swing.JLabel();
        hjexportexport = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jualdetail = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablehjdetail = new tabledark.TableDark();
        hjdetailgtotal = new javax.swing.JLabel();
        hjdetailjam = new javax.swing.JLabel();
        hjdetailkasir = new javax.swing.JLabel();
        hjdetailkembali = new javax.swing.JLabel();
        hjdetailprint = new javax.swing.JLabel();
        hjdetailkdtrans = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        popupretur = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablepopretur = new tabledark.TableDark();
        popreturcari = new javax.swing.JTextField();
        h = new javax.swing.JLabel();
        rpopback = new javax.swing.JLabel();
        rpoptambah = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        hjswitch = new javax.swing.JLabel();
        hjdetail = new javax.swing.JLabel();
        hjprint = new javax.swing.JLabel();
        hjtfkasir = new javax.swing.JLabel();
        hjtftotal = new javax.swing.JLabel();
        hjtfkdtrans = new javax.swing.JLabel();
        hjtftgl = new javax.swing.JLabel();
        hjcari = new javax.swing.JTextField();
        hjtfjam = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablehjual = new tabledark.TableDark();
        hjexport = new javax.swing.JLabel();
        hjhps = new javax.swing.JLabel();
        hjretur = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        dateChooser.setForeground(new java.awt.Color(70, 204, 89));
        dateChooser.setTextRefernce(hjexporttgldari);

        dateChooser1.setForeground(new java.awt.Color(70, 204, 89));
        dateChooser1.setTextRefernce(hjexporttglsampai);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko-Berkah Riwayat Jual");
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

        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/riwayat-clicked.png"))); // NOI18N
        riwayat.addMouseListener(new java.awt.event.MouseAdapter() {
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

        paneretur.setBackground(new java.awt.Color(255, 255, 255));
        paneretur.setLayout(null);

        hpsretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus.png"))); // NOI18N
        hpsretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hpsreturMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hpsreturMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hpsreturMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hpsreturMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hpsreturMouseReleased(evt);
            }
        });
        paneretur.add(hpsretur);
        hpsretur.setBounds(965, 590, 140, 50);

        rsup.setEditable(false);
        rsup.setForeground(new java.awt.Color(48, 56, 65));
        rsup.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rsup.setMinimumSize(new java.awt.Dimension(221, 20));
        rsup.setPreferredSize(new java.awt.Dimension(221, 20));
        rsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsupActionPerformed(evt);
            }
        });
        paneretur.add(rsup);
        rsup.setBounds(965, 555, 275, 30);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        rket.setColumns(5);
        rket.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rket.setLineWrap(true);
        rket.setRows(5);
        jScrollPane3.setViewportView(rket);

        paneretur.add(jScrollPane3);
        jScrollPane3.setBounds(967, 470, 270, 60);

        rkdtrans.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rkdtrans.setForeground(new java.awt.Color(48, 56, 65));
        paneretur.add(rkdtrans);
        rkdtrans.setBounds(360, 120, 170, 50);

        rkdrtr.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rkdrtr.setForeground(new java.awt.Color(48, 56, 65));
        paneretur.add(rkdrtr);
        rkdrtr.setBounds(1070, 117, 170, 50);

        returback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png"))); // NOI18N
        returback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returbackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                returbackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                returbackMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                returbackMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                returbackMouseReleased(evt);
            }
        });
        paneretur.add(returback);
        returback.setBounds(300, 120, 40, 50);

        rnamabar1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rnamabar1.setForeground(new java.awt.Color(48, 56, 65));
        rnamabar1.setText("Kode Retur :");
        paneretur.add(rnamabar1);
        rnamabar1.setBounds(980, 117, 90, 50);

        rqty.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rqty.setForeground(new java.awt.Color(48, 56, 65));
        paneretur.add(rqty);
        rqty.setBounds(1125, 290, 110, 27);

        rtotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rtotal.setForeground(new java.awt.Color(48, 56, 65));
        paneretur.add(rtotal);
        rtotal.setBounds(970, 355, 260, 27);

        rnamabar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rnamabar.setForeground(new java.awt.Color(48, 56, 65));
        paneretur.add(rnamabar);
        rnamabar.setBounds(970, 230, 260, 27);

        rharga.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rharga.setForeground(new java.awt.Color(48, 56, 65));
        paneretur.add(rharga);
        rharga.setBounds(970, 290, 110, 27);

        rtfqty.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rtfqty.setBorder(null);
        rtfqty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rtfqtyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                rtfqtyFocusLost(evt);
            }
        });
        rtfqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rtfqtyKeyReleased(evt);
            }
        });
        paneretur.add(rtfqty);
        rtfqty.setBounds(970, 412, 260, 25);

        jScrollPane2.setBorder(null);

        tablecartretur.setModel(new javax.swing.table.DefaultTableModel(
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
        tablecartretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablecartreturMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablecartretur);

        paneretur.add(jScrollPane2);
        jScrollPane2.setBounds(290, 200, 630, 470);

        rcaribar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/cari-barang.png"))); // NOI18N
        rcaribar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rcaribarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rcaribarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rcaribarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rcaribarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rcaribarMouseReleased(evt);
            }
        });
        paneretur.add(rcaribar);
        rcaribar.setBounds(540, 120, 390, 50);

        rcekout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rcekout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/checkout-retur.png"))); // NOI18N
        rcekout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rcekoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rcekoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rcekoutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rcekoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rcekoutMouseReleased(evt);
            }
        });
        paneretur.add(rcekout);
        rcekout.setBounds(955, 640, 300, 50);

        tambahretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur.png"))); // NOI18N
        tambahretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahreturMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahreturMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tambahreturMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tambahreturMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tambahreturMouseReleased(evt);
            }
        });
        paneretur.add(tambahretur);
        tambahretur.setBounds(1110, 590, 140, 50);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgretur.png"))); // NOI18N
        jLabel4.setText("jLabel1");
        paneretur.add(jLabel4);
        jLabel4.setBounds(270, 110, 990, 589);

        getContentPane().add(paneretur);
        paneretur.setBounds(0, 0, 1280, 750);

        coretur.setBackground(new java.awt.Color(255, 255, 255));
        coretur.setLayout(null);

        rnamabar2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rnamabar2.setForeground(new java.awt.Color(48, 56, 65));
        rnamabar2.setText("Kode Retur :");
        coretur.add(rnamabar2);
        rnamabar2.setBounds(980, 117, 90, 50);

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
        coretur.add(back);
        back.setBounds(290, 120, 40, 50);

        rcokdrtr.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rcokdrtr.setForeground(new java.awt.Color(48, 56, 65));
        coretur.add(rcokdrtr);
        rcokdrtr.setBounds(1070, 117, 170, 50);

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
        coretur.add(caribar);
        caribar.setBounds(420, 128, 490, 30);

        printretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/nota.png"))); // NOI18N
        printretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printreturMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printreturMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printreturMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printreturMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                printreturMouseReleased(evt);
            }
        });
        coretur.add(printretur);
        printretur.setBounds(1010, 610, 210, 70);

        cototal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cototal.setForeground(new java.awt.Color(48, 56, 65));
        coretur.add(cototal);
        cototal.setBounds(450, 560, 220, 30);

        cotgl.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cotgl.setForeground(new java.awt.Color(48, 56, 65));
        coretur.add(cotgl);
        cotgl.setBounds(420, 590, 240, 30);

        cokar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cokar.setForeground(new java.awt.Color(48, 56, 65));
        coretur.add(cokar);
        cokar.setBounds(420, 620, 230, 30);

        txtid10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid10.setForeground(new java.awt.Color(48, 56, 65));
        txtid10.setText("Rp. ");
        coretur.add(txtid10);
        txtid10.setBounds(420, 560, 30, 30);

        cojmlbar.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        cojmlbar.setForeground(new java.awt.Color(48, 56, 65));
        cojmlbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coretur.add(cojmlbar);
        cojmlbar.setBounds(980, 560, 250, 50);

        cokdtrans.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cokdtrans.setForeground(new java.awt.Color(48, 56, 65));
        coretur.add(cokdtrans);
        cokdtrans.setBounds(410, 530, 270, 30);

        txtid1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid1.setForeground(new java.awt.Color(48, 56, 65));
        txtid1.setText("Total Uang Retur  :");
        coretur.add(txtid1);
        txtid1.setBounds(290, 560, 140, 30);

        txtid2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid2.setForeground(new java.awt.Color(48, 56, 65));
        txtid2.setText("Tanggal                :");
        coretur.add(txtid2);
        txtid2.setBounds(290, 590, 130, 30);

        txtid3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid3.setForeground(new java.awt.Color(48, 56, 65));
        txtid3.setText("Nama Kasir           :");
        coretur.add(txtid3);
        txtid3.setBounds(290, 620, 120, 30);

        txtid.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(48, 56, 65));
        txtid.setText("Kode Transaksi    :");
        coretur.add(txtid);
        txtid.setBounds(290, 530, 120, 30);

        jScrollPane7.setBorder(null);

        tablecoretur.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tablecoretur);

        coretur.add(jScrollPane7);
        jScrollPane7.setBounds(290, 200, 940, 280);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgcoretur.png"))); // NOI18N
        jLabel6.setText("jLabel1");
        coretur.add(jLabel6);
        jLabel6.setBounds(270, 110, 990, 584);

        getContentPane().add(coretur);
        coretur.setBounds(0, 0, 1280, 750);

        exportjual.setBackground(new java.awt.Color(235, 235, 235));
        exportjual.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exportjual.setFocusCycleRoot(true);
        exportjual.setFocusTraversalPolicyProvider(true);
        exportjual.setMinimumSize(new java.awt.Dimension(1016, 615));
        exportjual.setLayout(null);

        hjexporttglsampai.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjexporttglsampai.setForeground(new java.awt.Color(48, 56, 65));
        hjexporttglsampai.setBorder(null);
        hjexporttglsampai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hjexporttglsampaiActionPerformed(evt);
            }
        });
        exportjual.add(hjexporttglsampai);
        hjexporttglsampai.setBounds(650, 120, 130, 25);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/tgl.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        exportjual.add(jLabel3);
        jLabel3.setBounds(640, 117, 180, 32);

        hjexportexport1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hjexportexport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/set.png"))); // NOI18N
        hjexportexport1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjexportexport1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjexportexport1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjexportexport1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjexportexport1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjexportexport1MouseReleased(evt);
            }
        });
        exportjual.add(hjexportexport1);
        hjexportexport1.setBounds(850, 112, 60, 40);

        hjexporttgldari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjexporttgldari.setForeground(new java.awt.Color(48, 56, 65));
        hjexporttgldari.setBorder(null);
        hjexporttgldari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hjexporttgldariActionPerformed(evt);
            }
        });
        exportjual.add(hjexporttgldari);
        hjexporttgldari.setBounds(220, 120, 130, 25);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/tgl.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        exportjual.add(jLabel2);
        jLabel2.setBounds(210, 117, 180, 32);

        tablehjexport.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tablehjexport);

        exportjual.add(jScrollPane5);
        jScrollPane5.setBounds(110, 160, 800, 360);

        hjexportkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        hjexportkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjexportkembaliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjexportkembaliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjexportkembaliMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjexportkembaliMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjexportkembaliMouseReleased(evt);
            }
        });
        exportjual.add(hjexportkembali);
        hjexportkembali.setBounds(590, 530, 160, 50);

        hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export.png"))); // NOI18N
        hjexportexport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjexportexportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjexportexportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjexportexportMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjexportexportMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjexportexportMouseReleased(evt);
            }
        });
        exportjual.add(hjexportexport);
        hjexportexport.setBounds(760, 530, 160, 50);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgexport.png"))); // NOI18N
        exportjual.add(jLabel8);
        jLabel8.setBounds(0, 10, 1030, 600);

        getContentPane().add(exportjual);
        exportjual.setBounds(246, 100, 1040, 620);

        jualdetail.setBackground(new java.awt.Color(235, 235, 235));
        jualdetail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jualdetail.setFocusCycleRoot(true);
        jualdetail.setFocusTraversalPolicyProvider(true);
        jualdetail.setMinimumSize(new java.awt.Dimension(1016, 615));
        jualdetail.setLayout(null);

        tablehjdetail.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tablehjdetail);

        jualdetail.add(jScrollPane4);
        jScrollPane4.setBounds(110, 70, 800, 370);

        hjdetailgtotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jualdetail.add(hjdetailgtotal);
        hjdetailgtotal.setBounds(360, 535, 200, 25);

        hjdetailjam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jualdetail.add(hjdetailjam);
        hjdetailjam.setBounds(120, 535, 200, 25);

        hjdetailkasir.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jualdetail.add(hjdetailkasir);
        hjdetailkasir.setBounds(360, 475, 200, 25);

        hjdetailkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        hjdetailkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjdetailkembaliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjdetailkembaliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjdetailkembaliMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjdetailkembaliMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjdetailkembaliMouseReleased(evt);
            }
        });
        jualdetail.add(hjdetailkembali);
        hjdetailkembali.setBounds(750, 530, 160, 40);

        hjdetailprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print.png"))); // NOI18N
        hjdetailprint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjdetailprintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjdetailprintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjdetailprintMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjdetailprintMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjdetailprintMouseReleased(evt);
            }
        });
        jualdetail.add(hjdetailprint);
        hjdetailprint.setBounds(750, 460, 160, 50);

        hjdetailkdtrans.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jualdetail.add(hjdetailkdtrans);
        hjdetailkdtrans.setBounds(120, 475, 200, 25);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgdtljual.png"))); // NOI18N
        jualdetail.add(jLabel7);
        jLabel7.setBounds(0, 10, 1030, 600);

        getContentPane().add(jualdetail);
        jualdetail.setBounds(246, 100, 1040, 620);

        popupretur.setBackground(new java.awt.Color(235, 235, 235));
        popupretur.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popupretur.setFocusCycleRoot(true);
        popupretur.setFocusTraversalPolicyProvider(true);
        popupretur.setMinimumSize(new java.awt.Dimension(1016, 615));
        popupretur.setLayout(null);

        tablepopretur.setModel(new javax.swing.table.DefaultTableModel(
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
        tablepopretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablepopreturMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablepopretur);

        popupretur.add(jScrollPane6);
        jScrollPane6.setBounds(160, 90, 690, 430);

        popreturcari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        popreturcari.setBorder(null);
        popreturcari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                popreturcariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                popreturcariFocusLost(evt);
            }
        });
        popreturcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popreturcariActionPerformed(evt);
            }
        });
        popreturcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                popreturcariKeyReleased(evt);
            }
        });
        popupretur.add(popreturcari);
        popreturcari.setBounds(210, 40, 630, 30);

        h.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/Search-bar-long.png"))); // NOI18N
        popupretur.add(h);
        h.setBounds(160, 30, 700, 50);

        rpopback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png"))); // NOI18N
        rpopback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rpopbackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rpopbackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rpopbackMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rpopbackMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rpopbackMouseReleased(evt);
            }
        });
        popupretur.add(rpopback);
        rpopback.setBounds(520, 530, 160, 50);

        rpoptambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/tambah.png"))); // NOI18N
        rpoptambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rpoptambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rpoptambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rpoptambahMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rpoptambahMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rpoptambahMouseReleased(evt);
            }
        });
        popupretur.add(rpoptambah);
        rpoptambah.setBounds(690, 530, 160, 50);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/bgpop.png"))); // NOI18N
        popupretur.add(jLabel5);
        jLabel5.setBounds(110, 10, 810, 600);

        getContentPane().add(popupretur);
        popupretur.setBounds(246, 100, 1040, 620);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-beli.png"))); // NOI18N
        hjswitch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjswitchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjswitchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjswitchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjswitchMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjswitchMouseReleased(evt);
            }
        });
        jPanel1.add(hjswitch);
        hjswitch.setBounds(860, 120, 190, 50);

        hjdetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hjdetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png"))); // NOI18N
        hjdetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjdetailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjdetailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjdetailMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjdetailMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjdetailMouseReleased(evt);
            }
        });
        jPanel1.add(hjdetail);
        hjdetail.setBounds(850, 480, 400, 50);

        hjprint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hjprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/print.png"))); // NOI18N
        hjprint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjprintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjprintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjprintMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjprintMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjprintMouseReleased(evt);
            }
        });
        jPanel1.add(hjprint);
        hjprint.setBounds(850, 530, 400, 50);

        hjtfkasir.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjtfkasir.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(hjtfkasir);
        hjtfkasir.setBounds(870, 355, 360, 27);

        hjtftotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjtftotal.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(hjtftotal);
        hjtftotal.setBounds(870, 418, 360, 27);

        hjtfkdtrans.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjtfkdtrans.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(hjtfkdtrans);
        hjtfkdtrans.setBounds(870, 230, 360, 27);

        hjtftgl.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjtftgl.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(hjtftgl);
        hjtftgl.setBounds(870, 290, 160, 27);

        hjcari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjcari.setBorder(null);
        hjcari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hjcariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hjcariFocusLost(evt);
            }
        });
        hjcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hjcariKeyReleased(evt);
            }
        });
        jPanel1.add(hjcari);
        hjcari.setBounds(340, 128, 480, 30);

        hjtfjam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjtfjam.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(hjtfjam);
        hjtfjam.setBounds(1070, 290, 160, 27);

        jScrollPane1.setBorder(null);

        tablehjual.setModel(new javax.swing.table.DefaultTableModel(
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
        tablehjual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablehjualMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablehjual);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(290, 200, 530, 470);

        hjexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export-xls.png"))); // NOI18N
        hjexport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjexportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjexportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjexportMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjexportMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjexportMouseReleased(evt);
            }
        });
        jPanel1.add(hjexport);
        hjexport.setBounds(1080, 120, 170, 50);

        hjhps.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png"))); // NOI18N
        hjhps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjhpsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjhpsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjhpsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjhpsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjhpsMouseReleased(evt);
            }
        });
        jPanel1.add(hjhps);
        hjhps.setBounds(850, 630, 400, 50);

        hjretur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hjretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur-panjang.png"))); // NOI18N
        hjretur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hjreturMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hjreturMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hjreturMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hjreturMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hjreturMouseReleased(evt);
            }
        });
        jPanel1.add(hjretur);
        hjretur.setBounds(850, 580, 400, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgjual.png"))); // NOI18N
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

    private void hjreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjreturMouseClicked
        // TODO add your handling code here:
        int total = 0;
        if (hjtfkdtrans.getText().equals("")) {
            panggilAlertMerah("Retur Gagal!","Pilih dahulu transaksi yang ingin diretur");
        } else {
        try {
            String sql = "SELECT count(id_retur_cus) as total from retur_customer "
            + "where id_penjualan = '"+hjtfkdtrans.getText()+"' ;" ;
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            total = rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error");
        }
if (total == 0) {
        rkdtrans.setText(hjtfkdtrans.getText());
        bersihcart();
        setrkdrtr();
        settablecartretur();
        rkosong();
        paneretur.setVisible(true);
        jPanel1.setVisible(false);
        } else {
        AlertPertanyaanHijau obj = new AlertPertanyaanHijau(this);
        obj.showMessage("Transaksi sudah pernah retur", "Silakan menuju menu retur untuk melihat riwayat, Lanjutkan Retur?");
        if (obj.getMessageType() == AlertPertanyaanHijau.MessageType.OK) {
        rkdtrans.setText(hjtfkdtrans.getText());
        bersihcart();
        setrkdrtr();
        settablecartretur();
        rkosong();
        paneretur.setVisible(true);
        jPanel1.setVisible(false);
}
}
} 
    }//GEN-LAST:event_hjreturMouseClicked

    private void hjexportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportMouseEntered
        // TODO add your handling code here:
hjexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export-xls-hover.png")));
    }//GEN-LAST:event_hjexportMouseEntered

    private void hjexportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportMouseExited
        // TODO add your handling code here:
hjexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export-xls.png")));
    }//GEN-LAST:event_hjexportMouseExited

    private void hjexportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportMousePressed
        // TODO add your handling code here:
hjexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export-xls-clicked.png")));
    }//GEN-LAST:event_hjexportMousePressed

    private void hjexportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportMouseReleased
        // TODO add your handling code here:
hjexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export-xls.png")));
    }//GEN-LAST:event_hjexportMouseReleased

    private void hjreturMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjreturMouseEntered
        // TODO add your handling code here:
hjretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur-panjang-hover.png")));
    }//GEN-LAST:event_hjreturMouseEntered

    private void hjreturMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjreturMouseExited
        // TODO add your handling code here:
hjretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur-panjang.png")));

    }//GEN-LAST:event_hjreturMouseExited

    private void hjreturMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjreturMousePressed
        // TODO add your handling code here:
hjretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur-panjang-clicked.png")));
    }//GEN-LAST:event_hjreturMousePressed

    private void hjreturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjreturMouseReleased
        // TODO add your handling code here:
hjretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur-panjang.png")));
    }//GEN-LAST:event_hjreturMouseReleased

    private void hjhpsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMouseEntered
        // TODO add your handling code here:
hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hjhpsMouseEntered

    private void hjhpsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMouseExited
        // TODO add your handling code here:
hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hjhpsMouseExited

    private void hjhpsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMousePressed
        // TODO add your handling code here:
hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hjhpsMousePressed

    private void hjhpsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMouseReleased
        // TODO add your handling code here:
hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hjhpsMouseReleased

    private void tablehjualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablehjualMouseClicked
        // TODO add your handling code here:
        int baris = tablehjual.rowAtPoint(evt.getPoint());
        String id = tablehjual.getValueAt(baris, 0).toString();
        hjtfkdtrans.setText(id);
        if (tablehjual.getValueAt(baris, 1)==null) {
            hjtftgl.setText("");
        } else {
            hjtftgl.setText(tablehjual.getValueAt(baris, 1).toString());
        }
        if (tablehjual.getValueAt(baris, 2)==null) {
            hjtfjam.setText("");
        } else {
            hjtfjam.setText(tablehjual.getValueAt(baris, 2).toString());
        }
        if (tablehjual.getValueAt(baris, 3)==null) {
            hjtfkasir.setText("");
        } else {
            hjtfkasir.setText(tablehjual.getValueAt(baris, 3).toString());
        }
        if (tablehjual.getValueAt(baris, 4)==null) {
            hjtftotal.setText("");
        } else {
            hjtftotal.setText(tablehjual.getValueAt(baris, 4).toString());
        }
    }//GEN-LAST:event_tablehjualMouseClicked

    private void hjcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hjcariFocusGained
        // TODO add your handling code here:
if (hjcari.getText().equals("Cari Kode Transaksi")) {
hjcari.setForeground(new java.awt.Color(51,51,51));
hjcari.setText("");
} else {
}
    }//GEN-LAST:event_hjcariFocusGained

    private void hjcariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hjcariFocusLost
        // TODO add your handling code here:
if (hjcari.getText().equals("")) {
hjcari.setForeground(new java.awt.Color(221,221,221));
hjcari.setText("Cari Kode Transaksi");
} else {
}
    }//GEN-LAST:event_hjcariFocusLost

    private void hjcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hjcariKeyReleased
        // TODO add your handling code here:
    tablehjual.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
    model.addColumn("Grand Total");
        try {
            String sql = "SELECT * from penjualan "
            + "where id_penjualan like '%" + hjcari.getText() + "%'";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_penjualan"),
                        res.getString("tanggal_transaksi"),
                        res.getString("jam_transaksi"),
                        res.getString("id_pengguna"),
                        res.getInt("total_bayar")
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehjual.setModel(model);
    }//GEN-LAST:event_hjcariKeyReleased

    private void hjexportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportMouseClicked
        // TODO add your handling code here:
        exportjual.setVisible(true);
        jPanel1.setVisible(false);
        settablehjexport();
    }//GEN-LAST:event_hjexportMouseClicked

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
    }//GEN-LAST:event_riwayatMouseEntered

    private void riwayatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayatMouseExited

    private void riwayatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayatMousePressed

    private void riwayatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayatMouseReleased

    private void transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseClicked
        // TODO add your handling code here:
TransaksiJual transaksijual = new TransaksiJual();
transaksijual.nama.setText(nama.getText());
transaksijual.setVisible(true);
this.dispose();

    }//GEN-LAST:event_transaksiMouseClicked

    private void hjhpsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMouseClicked
        // TODO add your handling code here:
        if (hjtfkdtrans.getText().equals("")) {
            panggilAlertMerah("Hapus Gagal !","pilih dahulu Transaksi yang ingin dihapus");
        } else { 
                    AlertPertanyaan obj = new AlertPertanyaan(this);
        obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Riawayat Transaksi Dengan Kode "+hjtfkdtrans.getText()+"?");
        if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
             try {
            String sql ="DELETE FROM penjualan where id_penjualan ='"+hjtfkdtrans.getText()+"'";
        java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Hapus Berhasil !","Data Riawayat Transaksi Dengan Kode "+hjtfkdtrans.getText()+" berhasil dihapus");
        settablehjual();
        kosong();
        } catch (Exception e) {
            panggilAlertHijau("Hapus Gagal !", e.getMessage());
        } 
         
        }
                
        }
    }//GEN-LAST:event_hjhpsMouseClicked

    private void hjdetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailMouseClicked
        // TODO add your handling code here:
        if (hjtfkdtrans.getText().equals("")) {
            panggilAlertMerah("Lihat Gagal!","Pilih dahulu transaksi yang ingin dilihat");
        } else { 
        hjdetailkdtrans.setText(hjtfkdtrans.getText());
        hjdetailkasir.setText(hjtfkasir.getText());
        hjdetailjam.setText(hjtfjam.getText());
        hjdetailgtotal.setText(hjtftotal.getText());
        setTablehjdetail();
    jualdetail.setVisible(true);
    jPanel1.setVisible(false);
}
    }//GEN-LAST:event_hjdetailMouseClicked

    private void hjdetailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailMouseEntered
        // TODO add your handling code here:
hjdetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail-hover.png")));
    }//GEN-LAST:event_hjdetailMouseEntered

    private void hjdetailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailMouseExited
        // TODO add your handling code here:
hjdetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png")));
    }//GEN-LAST:event_hjdetailMouseExited

    private void hjdetailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailMousePressed
        // TODO add your handling code here:
hjdetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail-clicked.png")));
    }//GEN-LAST:event_hjdetailMousePressed

    private void hjdetailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailMouseReleased
        // TODO add your handling code here:
hjdetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/lihat-detail.png")));
    }//GEN-LAST:event_hjdetailMouseReleased

    private void hjprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjprintMouseClicked
        // TODO add your handling code here:
        if (hjtfkdtrans.getText().equals("")) {
            panggilAlertMerah("Cetak Gagal !","pilih dahulu Transaksi yang ingin dicetak ulang");
        } else { 
                    AlertPertanyaan obj = new AlertPertanyaan(this);
        obj.showMessage("Apakah Anda Yakin ?", "Ingin mencetak ulang struk Dengan Kode \n"+hjtfkdtrans.getText()+"?");
        if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
                     try {
            java.sql.Connection conn=(com.mysql.jdbc.Connection)Config.configDB();
//        String namafile = "src/kasir/report.jrxml";
            InputStream report = getClass().getResourceAsStream("jual.jasper");
            HashMap param = new HashMap();
            param.put("kd_trans", hjtfkdtrans.getText());
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
} else {
}
}
    }//GEN-LAST:event_hjprintMouseClicked

    private void hjprintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjprintMouseEntered
        // TODO add your handling code here:
hjprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/print-hover.png")));
    }//GEN-LAST:event_hjprintMouseEntered

    private void hjprintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjprintMouseExited
        // TODO add your handling code here:
hjprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/print.png")));
    }//GEN-LAST:event_hjprintMouseExited

    private void hjprintMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjprintMousePressed
        // TODO add your handling code here:
hjprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/print-clicked.png")));
    }//GEN-LAST:event_hjprintMousePressed

    private void hjprintMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjprintMouseReleased
        // TODO add your handling code here:
hjprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/print.png")));
    }//GEN-LAST:event_hjprintMouseReleased

    private void hjswitchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseClicked
        // TODO add your handling code here:
                    RiwayatBeli riwayatbeli = new RiwayatBeli();
                    riwayatbeli.nama.setText(nama.getText());
                    riwayatbeli.setVisible(true);
this.dispose();
    }//GEN-LAST:event_hjswitchMouseClicked

    private void hjswitchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseEntered
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-beli-hover.png")));
    }//GEN-LAST:event_hjswitchMouseEntered

    private void hjswitchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseExited
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-beli.png")));
    }//GEN-LAST:event_hjswitchMouseExited

    private void hjswitchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMousePressed
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-beli-clicked.png")));
    }//GEN-LAST:event_hjswitchMousePressed

    private void hjswitchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseReleased
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-beli.png")));
    }//GEN-LAST:event_hjswitchMouseReleased

    private void hjdetailkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailkembaliMouseClicked
        // TODO add your handling code here:
        jualdetail.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_hjdetailkembaliMouseClicked

    private void hjdetailprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailprintMouseClicked
        // TODO add your handling code here:
        if (hjdetailkdtrans.getText().equals("")) {
            panggilAlertMerah("Gagal", "Pilih dahulu barang dari Table");
        } else {
        AlertPertanyaanHijau obj = new AlertPertanyaanHijau(this);
        obj.showMessage("Konfirmasi Cetak", "apakah anda yakin untuk mencetak nota kembali?");
        if (obj.getMessageType() == AlertPertanyaanHijau.MessageType.OK) {
                    try {
            java.sql.Connection conn=(com.mysql.jdbc.Connection)Config.configDB();
//        String namafile = "src/kasir/report.jrxml";
            InputStream report = getClass().getResourceAsStream("jual.jasper");
            HashMap param = new HashMap();
            param.put("kd_trans", hjdetailkdtrans.getText());
//        File file = new File(namafile);
//        JasperDesign jd = JRXmlLoader.load(namafile);
//        JasperReport jr = JasperCompileManager.compileReport(jd);
//            String reportPath = report;
//            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(report,param,conn);
            JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            e.printStackTrace();
            panggilAlertMerah("Gagal", e.getMessage());
        }
            }
        }
    }//GEN-LAST:event_hjdetailprintMouseClicked

    private void hjexportkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportkembaliMouseClicked
        // TODO add your handling code here:
kosong();
exportjual.setVisible(false);
jPanel1.setVisible(true);
    }//GEN-LAST:event_hjexportkembaliMouseClicked

    private void hjexportexportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMouseClicked
        // TODO add your handling code here:
exportExcel();
    }//GEN-LAST:event_hjexportexportMouseClicked

    private void hjexporttgldariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hjexporttgldariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hjexporttgldariActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        dateChooser.showPopup();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void hjexporttglsampaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hjexporttglsampaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hjexporttglsampaiActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        dateChooser1.showPopup();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void hjexportexport1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexport1MouseClicked
        // TODO add your handling code here:

    tablehjexport.fixTable(jScrollPane5);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
    model.addColumn("Grand Total");
        try {
            String sql = "SELECT * from penjualan "
                        +"where tanggal_transaksi BETWEEN '"+date1+"' AND '"+date2+"' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString("id_penjualan"),
                        res.getString("tanggal_transaksi"),
                        res.getString("jam_transaksi"),
                        res.getString("id_pengguna"),
                        res.getInt("total_bayar")
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehjexport.setModel(model);
    }//GEN-LAST:event_hjexportexport1MouseClicked

    private void returbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returbackMouseClicked
        // TODO add your handling code here:
        paneretur.setVisible(false);
        jPanel1.setVisible(true);
        kosong();
    }//GEN-LAST:event_returbackMouseClicked

    private void returbackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returbackMouseEntered
        // TODO add your handling code here:
returback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back-hover.png")));
    }//GEN-LAST:event_returbackMouseEntered

    private void returbackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returbackMouseExited
        // TODO add your handling code here:
returback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png")));
    }//GEN-LAST:event_returbackMouseExited

    private void returbackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returbackMousePressed
        // TODO add your handling code here:
returback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back-clicked.png")));
    }//GEN-LAST:event_returbackMousePressed

    private void returbackMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returbackMouseReleased
        // TODO add your handling code here:
returback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/bar/back.png")));
    }//GEN-LAST:event_returbackMouseReleased

    private void rtfqtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rtfqtyFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_rtfqtyFocusGained

    private void rtfqtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rtfqtyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_rtfqtyFocusLost

    private void rtfqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rtfqtyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rtfqtyKeyReleased

    private void tablecartreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablecartreturMouseClicked
        // TODO add your handling code here:
        rkosong();
        int baris = tablecartretur.rowAtPoint(evt.getPoint());
        rbar = tablecartretur.getValueAt(baris, 1).toString();
        rbar2 = tablecartretur.getValueAt(baris, 1).toString();
           try {
                String sql = "SELECT detail_penjualan.id_barang, "
            + "barang.nama_barang, detail_penjualan.harga, detail_penjualan.qty, satuan.satuan, detail_penjualan.harga_total "
            + "from detail_penjualan join barang on detail_penjualan.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_penjualan.id_penjualan = '"+rkdtrans.getText()+"' and detail_penjualan.id_barang = '"+rbar+"' ;";
                java.sql.Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while (res.next()) {
                    rnamabar.setText(res.getString(2));
                    rharga.setText(res.getString(3));
                    rqty.setText(res.getString(4));
                    rtotal.setText(res.getString(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    try {
String sql = "SELECT qty, keterangan from cart_retur_cus "
            + "where id_retur = '"+rkdrtr.getText()+"' and id_barang = '"+rbar+"' ;";
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while (res.next()) {
        rtfqty.setText(res.getString(1));
        rket.setText(res.getString(2));
    }
    } catch (Exception e) {
    }

    try {
    String sql = "select distinct supplier.nama_supplier from pembelian "
                + "join supplier on pembelian.id_supplier = supplier.id_supplier "
                + "join detail_pembelian on pembelian.id_pembelian = detail_pembelian.id_pembelian "
                + "where detail_pembelian.id_barang = '"+rbar+"' ;";
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while (res.next()) {
        rsup.addItem(res.getString(1));
    }

    } catch (Exception e) {
    }
    }//GEN-LAST:event_tablecartreturMouseClicked

    private void rcaribarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcaribarMouseClicked
        // TODO add your handling code here:
settablepopretur();
popupretur.setVisible(true);
paneretur.setVisible(false);
    }//GEN-LAST:event_rcaribarMouseClicked

    private void rcaribarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcaribarMouseEntered
        // TODO add your handling code here:
rcaribar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/cari-barang-hover.png")));
    }//GEN-LAST:event_rcaribarMouseEntered

    private void rcaribarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcaribarMouseExited
        // TODO add your handling code here:
rcaribar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/cari-barang.png")));
    }//GEN-LAST:event_rcaribarMouseExited

    private void rcaribarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcaribarMousePressed
        // TODO add your handling code here:
rcaribar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/cari-barang-clicked.png")));
    }//GEN-LAST:event_rcaribarMousePressed

    private void rcaribarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcaribarMouseReleased
        // TODO add your handling code here:
rcaribar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/Trans/cari-barang.png")));
    }//GEN-LAST:event_rcaribarMouseReleased

    private void rcekoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcekoutMouseClicked
        // TODO add your handling code here:
        DefaultTableModel cartmodel = (DefaultTableModel) tablecartretur.getModel();
        if (cartmodel.getRowCount ()==0) {
            panggilAlertMerah("Gagal!", "Keranjang masih kosong");
        } else {
                    AlertPertanyaanHijau obj = new AlertPertanyaanHijau(this);
        obj.showMessage("Konfirmasi Retur", "apakah anda yakin untuk checkout Retur?");
        if (obj.getMessageType() == AlertPertanyaanHijau.MessageType.OK) {
            rcekout();
            rkosong();
            settablecartretur();
            settablecoretur();
            setcotgl();
            rcokdrtr.setText(rkdrtr.getText());
            cokdtrans.setText(rkdtrans.getText());

            int j;
            try {
                String sql = "SELECT sum(sub_total) as total from detail_retur_cos "
                        + "where id_retur = '"+rcokdrtr.getText()+"' ;" ;
                java.sql.Connection conn = (Connection) Config.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                j = rs.getInt("total");
                cototal.setText(String.valueOf(j));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                String sql = "SELECT id_karyawan from retur_customer "
                        + "where id_retur_cus = '"+rcokdrtr.getText()+"' ;" ;
                java.sql.Connection conn = (Connection) Config.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                cokar.setText(rs.getString(1));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                String sql = "SELECT count(id_barang) as total from detail_retur_cos "
                        + "where id_retur = '"+rcokdrtr.getText()+"' ;" ;
                java.sql.Connection conn = (Connection) Config.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                cojmlbar.setText(rs.getString(1));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }//GEN-LAST:event_rcekoutMouseClicked

    private void rcekoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcekoutMouseEntered
        // TODO add your handling code here:
rcekout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/checkout-retur-hover.png")));
    }//GEN-LAST:event_rcekoutMouseEntered

    private void rcekoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcekoutMouseExited
        // TODO add your handling code here:
rcekout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/checkout-retur.png")));
    }//GEN-LAST:event_rcekoutMouseExited

    private void rcekoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcekoutMousePressed
        // TODO add your handling code here:
rcekout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/checkout-retur-clicked.png")));
    }//GEN-LAST:event_rcekoutMousePressed

    private void rcekoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rcekoutMouseReleased
        // TODO add your handling code here:
rcekout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/checkout-retur.png")));
    }//GEN-LAST:event_rcekoutMouseReleased

    private void tambahreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahreturMouseClicked
        // TODO add your handling code here:
        if (rnamabar.getText().equals("")) {
            panggilAlertMerah("Gagal", "Pilih barang dahulu");
        } else {
if (rtfqty.getText().equals("")){
                panggilAlertMerah("Gagal", "Kuantitas retur kosong");
} else {
            String bar = "";
            Double hrg, reqty = Double.parseDouble(rtfqty.getText()), bqty = Double.parseDouble(rqty.getText()), harga = Double.parseDouble(rharga.getText());
//            try {
//                String sqll = "SELECT id_barang, qty FROM cart_retur_cus where id_barang='" + rbar + "' ;";
//                java.sql.Connection conn = (Connection) Config.configDB();
//                java.sql.Statement stm = conn.createStatement();
//                java.sql.ResultSet res = stm.executeQuery(sqll);
//                res.next();
//                bar = res.getString(1);
//                qty = res.getDouble(2);
//            } catch (Exception e) {
//            }
String sup = "";
        try {
            String sql = "SELECT id_supplier from supplier "
                    + "where nama_supplier = '" + rsup.getSelectedItem() + "';";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            sup = rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        hrg = reqty*harga;
                if (reqty > bqty ) {
                    panggilAlertMerah("Gagal", "Kuantitas retur lebih besar dari kuantitas beli"); 
                    } else {

            if (rbar.equals(bar)) {
                     try {
                        String sqll = "UPDATE cart_retur_cus "
                                + "SET qty = " + rtfqty.getText()
                                + ", sub_total = '" + hrg
                                + ", keterangan = '" + rket.getText()
                                + "', id_supplier = '" + sup
                                + "' WHERE cart_retur_cus.id_barang = '" + rbar + "'";
                        java.sql.Connection conn = (Connection) Config.configDB();
                        java.sql.PreparedStatement pstl = conn.prepareStatement(sqll);
                        pstl.execute();
                    rkosong();
                    settablecartretur();
                    } catch (Exception e) {
                        panggilAlertMerah("Gagal", e.getMessage());
                    }
                } else {
                try {
                    String sqll = "INSERT INTO `cart_retur_cus` (`id_retur`, `id_barang`, `qty`, `sub_total`, `keterangan`, `status`, `id_supplier`)" +
                            " VALUES ('" + rkdrtr.getText() + "', '"
                            + rbar + "', '"
                            + rtfqty.getText() + "', '"
                            + hrg + "', '"
                            + rket.getText() + "', 'belum', '"
                            + sup + "') ; ";
                    java.sql.Connection conn = (Connection) Config.configDB();
                    java.sql.PreparedStatement pstl = conn.prepareStatement(sqll);
                    pstl.execute();
                    rkosong();
                    settablecartretur();
                } catch (Exception e) {
                        panggilAlertMerah("Gagal", e.getMessage());
                }
        }
    }
}
}
    }//GEN-LAST:event_tambahreturMouseClicked

    private void tambahreturMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahreturMouseEntered
        // TODO add your handling code here:
tambahretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur-hover.png")));
    }//GEN-LAST:event_tambahreturMouseEntered

    private void tambahreturMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahreturMouseExited
        // TODO add your handling code here:
tambahretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur.png")));
    }//GEN-LAST:event_tambahreturMouseExited

    private void tambahreturMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahreturMousePressed
        // TODO add your handling code here:
tambahretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur-clicked.png")));
    }//GEN-LAST:event_tambahreturMousePressed

    private void tambahreturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahreturMouseReleased
        // TODO add your handling code here:
tambahretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/retur.png")));
    }//GEN-LAST:event_tambahreturMouseReleased

    private void rsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rsupActionPerformed

    private void tablepopreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablepopreturMouseClicked
        // TODO add your handling code here:
        int baris = tablepopretur.rowAtPoint(evt.getPoint());
        rbar = tablepopretur.getValueAt(baris, 0).toString();
        if (tablepopretur.getValueAt(baris, 3)==null) {
            rpoptambah.setVisible(true);
        } else {
        String cek = tablepopretur.getValueAt(baris, 3).toString();
        if (cek.equals("Iya")) {
            rpoptambah.setVisible(true);
        } else {
            rpoptambah.setVisible(false);
}
}
    }//GEN-LAST:event_tablepopreturMouseClicked

    private void popreturcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_popreturcariFocusGained
        // TODO add your handling code here:
        if (popreturcari.getText().equals("Cari Nama Barang")) {
            popreturcari.setForeground(new java.awt.Color(51,51,51));
            popreturcari.setText("");
        } else {
        }
    }//GEN-LAST:event_popreturcariFocusGained

    private void popreturcariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_popreturcariFocusLost
        // TODO add your handling code here:
        if (popreturcari.getText().equals("")) {
            popreturcari.setForeground(new java.awt.Color(221,221,221));
            popreturcari.setText("Cari Nama Barang");
        } else {
        }
    }//GEN-LAST:event_popreturcariFocusLost

    private void popreturcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popreturcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_popreturcariActionPerformed

    private void popreturcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_popreturcariKeyReleased
        // TODO add your handling code here:
    tablepopretur.fixTable(jScrollPane6);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Harga");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Sub Total");
        try {
            String sql = "SELECT detail_penjualan.id_barang, "
            + "barang.nama_barang, detail_penjualan.harga, detail_penjualan.qty, satuan.satuan, detail_penjualan.harga_total "
            + "from detail_penjualan join barang on detail_penjualan.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_penjualan.id_penjualan = '"+rkdtrans.getText()+"' "
            + "and barang.nama_barang like '%" + popreturcari.getText() + "%' ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getDouble(4),
                        res.getString(5),
                        res.getInt(6)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablepopretur.setModel(model);
    }//GEN-LAST:event_popreturcariKeyReleased

    private void rpopbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpopbackMouseClicked
        // TODO add your handling code here:
        popupretur.setVisible(false);
        popupretur.requestFocus();
        paneretur.setVisible(true);
    }//GEN-LAST:event_rpopbackMouseClicked

    private void rpoptambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpoptambahMouseClicked
        // TODO add your handling code here:
        rkosong();
        if (rbar.equals("")) {
            panggilAlertMerah("Gagal", "Pilih dahulu barang dari Table");
        } else {
            try {
                String sql = "SELECT detail_penjualan.id_barang, "
            + "barang.nama_barang, detail_penjualan.harga, detail_penjualan.qty, satuan.satuan, detail_penjualan.harga_total "
            + "from detail_penjualan join barang on detail_penjualan.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_penjualan.id_penjualan = '"+rkdtrans.getText()+"' and detail_penjualan.id_barang = '"+rbar+"' ;";
                java.sql.Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while (res.next()) {
                    rnamabar.setText(res.getString(2));
                    rharga.setText(res.getString(3));
                    rqty.setText(res.getString(4));
                    rtotal.setText(res.getString(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    try {
    String sql = "select distinct supplier.nama_supplier from pembelian "
                + "join supplier on pembelian.id_supplier = supplier.id_supplier "
                + "join detail_pembelian on pembelian.id_pembelian = detail_pembelian.id_pembelian "
                + "where detail_pembelian.id_barang = '"+rbar+"' ;";
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while (res.next()) {
        rsup.addItem(res.getString(1));
    }
    } catch (Exception e) {
    }
        popupretur.setVisible(false);
        paneretur.setVisible(true);
}
    }//GEN-LAST:event_rpoptambahMouseClicked

    private void hpsreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsreturMouseClicked
        // TODO add your handling code here:
        if (rbar2.equals("") || rbar2.equals(null)) {
            panggilAlertMerah("Hapus Gagal !","pilih dahulu Barang yang ingin dihapus");
        } else { 
                    AlertPertanyaan obj = new AlertPertanyaan(this);
        obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Barang Dengan Kode \n"+rbar2+"?");
        if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
             try {
            String sql ="DELETE FROM cart_retur_cus where id_barang='"+rbar2+"'";
        java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Hapus Berhasil !","Data Retur dengan Kode "+rbar2+" berhasil dihapus");
                    rkosong();
                    settablecartretur();
        } catch (Exception e) {
            String c = e.getMessage().substring(0, 36);
                panggilAlertMerah("Hapus Gagal !", c);
        } 
        
        }
     
        }
    }//GEN-LAST:event_hpsreturMouseClicked

    private void hpsreturMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsreturMouseEntered
        // TODO add your handling code here:
hpsretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-hover.png")));
    }//GEN-LAST:event_hpsreturMouseEntered

    private void hpsreturMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsreturMouseExited
        // TODO add your handling code here:
hpsretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus.png")));
    }//GEN-LAST:event_hpsreturMouseExited

    private void hpsreturMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsreturMousePressed
        // TODO add your handling code here:
hpsretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-clicked.png")));
    }//GEN-LAST:event_hpsreturMousePressed

    private void hpsreturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hpsreturMouseReleased
        // TODO add your handling code here:
hpsretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus.png")));
    }//GEN-LAST:event_hpsreturMouseReleased

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
    tablecoretur.fixTable(jScrollPane7);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Retur");
    model.addColumn("Kode Barang");
    model.addColumn("Nama");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    model.addColumn("Total");
        try {
            String sql = "SELECT detail_retur_cos.id_retur, detail_retur_cos.id_barang, barang.nama_barang, detail_retur_cos.qty, detail_retur_cos.sub_total, "
            + "satuan.satuan from detail_retur_cos join barang on detail_retur_cos.id_barang = barang.id_barang "
            + "join satuan on barang.id_satuan = satuan.id_satuan "
            + "where detail_retur_cos.id_retur = '"+rkdrtr.getText()+"' "
            + "and barang.nama_barang like '%" + caribar.getText() + "%' ;" ;
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getDouble(4),
                        res.getString(6),
                        res.getInt(5),
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablecoretur.setModel(model);
    }//GEN-LAST:event_caribarKeyReleased

    private void printreturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreturMouseClicked
        // TODO add your handling code here:
                     try {
            java.sql.Connection conn=(com.mysql.jdbc.Connection)Config.configDB();
//        String namafile = "src/kasir/report.jrxml";
            InputStream report = getClass().getResourceAsStream("retur.jasper");
            HashMap param = new HashMap();
            param.put("kd_retur", rcokdrtr.getText());
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
    }//GEN-LAST:event_printreturMouseClicked

    private void printreturMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreturMouseEntered
        // TODO add your handling code here:
        printretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/nota-hover.png")));
    }//GEN-LAST:event_printreturMouseEntered

    private void printreturMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreturMouseExited
        // TODO add your handling code here:
        printretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/nota.png")));
    }//GEN-LAST:event_printreturMouseExited

    private void printreturMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreturMousePressed
        // TODO add your handling code here:
        printretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/nota-clicked.png")));
    }//GEN-LAST:event_printreturMousePressed

    private void printreturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printreturMouseReleased
        // TODO add your handling code here:
        printretur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/nota.png")));
    }//GEN-LAST:event_printreturMouseReleased

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
            rkosong();
            settablecartretur();
        coretur.setVisible(false);
        paneretur.setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void hjexportexport1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexport1MouseEntered
        // TODO add your handling code here:
hjexportexport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/set-hover.png")));
    }//GEN-LAST:event_hjexportexport1MouseEntered

    private void hjexportexport1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexport1MouseExited
        // TODO add your handling code here:
hjexportexport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/set.png")));
    }//GEN-LAST:event_hjexportexport1MouseExited

    private void hjexportexport1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexport1MousePressed
        // TODO add your handling code here:
hjexportexport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/set-clicked.png")));
    }//GEN-LAST:event_hjexportexport1MousePressed

    private void hjexportexport1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexport1MouseReleased
        // TODO add your handling code here:
hjexportexport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/set.png")));
    }//GEN-LAST:event_hjexportexport1MouseReleased

    private void hjexportkembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportkembaliMouseEntered
        // TODO add your handling code here:
hjexportkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_hjexportkembaliMouseEntered

    private void hjexportkembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportkembaliMouseExited
        // TODO add your handling code here:
hjexportkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_hjexportkembaliMouseExited

    private void hjexportkembaliMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportkembaliMousePressed
        // TODO add your handling code here:
hjexportkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_hjexportkembaliMousePressed

    private void hjexportkembaliMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportkembaliMouseReleased
        // TODO add your handling code here:
hjexportkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_hjexportkembaliMouseReleased

    private void hjexportexportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMouseEntered
        // TODO add your handling code here:
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export-hover.png")));
    }//GEN-LAST:event_hjexportexportMouseEntered

    private void hjexportexportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMouseExited
        // TODO add your handling code here:
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export.png")));
    }//GEN-LAST:event_hjexportexportMouseExited

    private void hjexportexportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMousePressed
        // TODO add your handling code here:
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export-clicked.png")));
    }//GEN-LAST:event_hjexportexportMousePressed

    private void hjexportexportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMouseReleased
        // TODO add your handling code here:
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export.png")));
    }//GEN-LAST:event_hjexportexportMouseReleased

    private void hjdetailprintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailprintMouseEntered
        // TODO add your handling code here:
hjdetailprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print-hover.png")));
    }//GEN-LAST:event_hjdetailprintMouseEntered

    private void hjdetailprintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailprintMouseExited
        // TODO add your handling code here:
hjdetailprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print.png")));
    }//GEN-LAST:event_hjdetailprintMouseExited

    private void hjdetailprintMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailprintMousePressed
        // TODO add your handling code here:
hjdetailprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print-clicked.png")));
    }//GEN-LAST:event_hjdetailprintMousePressed

    private void hjdetailprintMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailprintMouseReleased
        // TODO add your handling code here:
hjdetailprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/print.png")));
    }//GEN-LAST:event_hjdetailprintMouseReleased

    private void hjdetailkembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailkembaliMouseEntered
        // TODO add your handling code here:
hjdetailkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_hjdetailkembaliMouseEntered

    private void hjdetailkembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailkembaliMouseExited
        // TODO add your handling code here:
hjdetailkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_hjdetailkembaliMouseExited

    private void hjdetailkembaliMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailkembaliMousePressed
        // TODO add your handling code here:
hjdetailkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_hjdetailkembaliMousePressed

    private void hjdetailkembaliMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailkembaliMouseReleased
        // TODO add your handling code here:
hjdetailkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_hjdetailkembaliMouseReleased

    private void rpopbackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpopbackMouseEntered
        // TODO add your handling code here:
rpopback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-hover.png")));
    }//GEN-LAST:event_rpopbackMouseEntered

    private void rpopbackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpopbackMouseExited
        // TODO add your handling code here:
rpopback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_rpopbackMouseExited

    private void rpopbackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpopbackMousePressed
        // TODO add your handling code here:
rpopback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali-clicked.png")));
    }//GEN-LAST:event_rpopbackMousePressed

    private void rpopbackMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpopbackMouseReleased
        // TODO add your handling code here:
rpopback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/kembali.png")));
    }//GEN-LAST:event_rpopbackMouseReleased

    private void rpoptambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpoptambahMouseEntered
        // TODO add your handling code here:
rpoptambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/tambah-hover.png")));
    }//GEN-LAST:event_rpoptambahMouseEntered

    private void rpoptambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpoptambahMouseExited
        // TODO add your handling code here:
rpoptambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/tambah.png")));
    }//GEN-LAST:event_rpoptambahMouseExited

    private void rpoptambahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpoptambahMousePressed
        // TODO add your handling code here:
rpoptambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/tambah-clicked.png")));
    }//GEN-LAST:event_rpoptambahMousePressed

    private void rpoptambahMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rpoptambahMouseReleased
        // TODO add your handling code here:
rpoptambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/popup/tambah.png")));
    }//GEN-LAST:event_rpoptambahMouseReleased

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

    private void gudangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseClicked
        // TODO add your handling code here:
                    Gudang gudang = new Gudang();
                    gudang.nama.setText(nama.getText());
                    gudang.setVisible(true);
                    this.dispose();

    }//GEN-LAST:event_gudangMouseClicked

    private void returMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseClicked
        // TODO add your handling code here:
                    Retur retur = new Retur();
                    retur.nama.setText(nama.getText());
                    retur.setVisible(true);
                    this.dispose();

    }//GEN-LAST:event_returMouseClicked

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
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiwayatJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiwayatJual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JLabel back;
    private javax.swing.JTextField caribar;
    private javax.swing.JLabel cojmlbar;
    private javax.swing.JLabel cokar;
    private javax.swing.JLabel cokdtrans;
    private javax.swing.JPanel coretur;
    private javax.swing.JLabel cotgl;
    private javax.swing.JLabel cototal;
    private javax.swing.JLabel dashboard;
    private com.raven.datechooser.DateChooser dateChooser;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JPanel exportjual;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel h;
    private javax.swing.JLabel header;
    private javax.swing.JTextField hjcari;
    private javax.swing.JLabel hjdetail;
    private javax.swing.JLabel hjdetailgtotal;
    private javax.swing.JLabel hjdetailjam;
    private javax.swing.JLabel hjdetailkasir;
    private javax.swing.JLabel hjdetailkdtrans;
    private javax.swing.JLabel hjdetailkembali;
    private javax.swing.JLabel hjdetailprint;
    private javax.swing.JLabel hjexport;
    private javax.swing.JLabel hjexportexport;
    private javax.swing.JLabel hjexportexport1;
    private javax.swing.JLabel hjexportkembali;
    private javax.swing.JTextField hjexporttgldari;
    private javax.swing.JTextField hjexporttglsampai;
    private javax.swing.JLabel hjhps;
    private javax.swing.JLabel hjprint;
    private javax.swing.JLabel hjretur;
    private javax.swing.JLabel hjswitch;
    private javax.swing.JLabel hjtfjam;
    private javax.swing.JLabel hjtfkasir;
    private javax.swing.JLabel hjtfkdtrans;
    private javax.swing.JLabel hjtftgl;
    private javax.swing.JLabel hjtftotal;
    private javax.swing.JLabel hpsretur;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel jenis;
    private javax.swing.JPanel jualdetail;
    private javax.swing.JLabel karyawan;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private javax.swing.JPanel paneretur;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JTextField popreturcari;
    private javax.swing.JPanel popupretur;
    private javax.swing.JLabel printretur;
    private javax.swing.JLabel rcaribar;
    private javax.swing.JLabel rcekout;
    private javax.swing.JLabel rcokdrtr;
    private javax.swing.JLabel retur;
    private javax.swing.JLabel returback;
    private javax.swing.JLabel rharga;
    private javax.swing.JLabel riwayat;
    private javax.swing.JLabel rkdrtr;
    private javax.swing.JLabel rkdtrans;
    private javax.swing.JTextArea rket;
    private javax.swing.JLabel rnamabar;
    private javax.swing.JLabel rnamabar1;
    private javax.swing.JLabel rnamabar2;
    private javax.swing.JLabel rpopback;
    private javax.swing.JLabel rpoptambah;
    private javax.swing.JLabel rqty;
    private combo_suggestion.ComboBoxSuggestion rsup;
    private javax.swing.JTextField rtfqty;
    private javax.swing.JLabel rtotal;
    public static tabledark.TableDark tablecartretur;
    private tabledark.TableDark tablecoretur;
    private tabledark.TableDark tablehjdetail;
    private tabledark.TableDark tablehjexport;
    public static tabledark.TableDark tablehjual;
    private tabledark.TableDark tablepopretur;
    private javax.swing.JLabel tambahretur;
    private javax.swing.JLabel transaksi;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtid1;
    private javax.swing.JLabel txtid10;
    private javax.swing.JLabel txtid2;
    private javax.swing.JLabel txtid3;
    // End of variables declaration//GEN-END:variables
}
