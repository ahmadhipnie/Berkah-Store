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
import java.sql.Connection;
import java.sql.SQLException;
import javaswingdev.message.AlertBerhasil;

import javaswingdev.message.AlertGagal;
import javaswingdev.message.AlertPertanyaan;
import javaswingdev.message.JSystemFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author yogan
 */
public class RiwayatBeli extends javax.swing.JFrame {
String date1, date2, rbar, rbar2;
private ImageIcon format = null;
    /**
     * Creates new form Karyawan
     */
    public RiwayatBeli() {
        initComponents();
        settablehjual();
        Cursor();
        datecus();
        setFavIcon();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        belidetail.setVisible(false);
        exportbeli.setVisible(false);
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
        hjexport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjhps.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjdetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjswitch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjexportexport1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjexportkembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjexportexport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hjdetailkembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
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
JSystemFileChooser jFileChooser= new JSystemFileChooser();
jFileChooser.showSaveDialog(this);
File savefile = jFileChooser.getSelectedFile();
if(savefile != null) {
savefile = new File(savefile.toString()+".xls");

        try {
            String sql = "SELECT pembelian.id_pembelian, pembelian.tanggal_transaksi, pembelian.jam_transaksi, pembelian.uang, "
            + "pembelian.id_pengguna, supplier.nama_supplier from pembelian join supplier "
            + "on pembelian.id_supplier = supplier.id_supplier "
                   + "where tanggal_transaksi BETWEEN '" + date1 + "' AND '" + date2 + "' ;";
            Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Report Pembelian");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Kode Transaksi");
            header.createCell(1).setCellValue("Tanggal");
            header.createCell(2).setCellValue("Jam");
            header.createCell(3).setCellValue("Nama Kasir");
            header.createCell(4).setCellValue("Total Pembayaran");
            header.createCell(5).setCellValue("Nama Supplier");

            int index = 1;
            while (res.next()) {
                Row row = sheet.createRow(index);
                row.createCell(0).setCellValue(res.getString(1));
                row.createCell(1).setCellValue(res.getString(2));
                row.createCell(2).setCellValue(res.getString(3));
                row.createCell(3).setCellValue(res.getString(5));
                row.createCell(4).setCellValue(res.getDouble(4));
                row.createCell(5).setCellValue(res.getString(6));
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


void kosong() {
hjtfkdtrans.setText("");
hjtfkasir.setText(null);
hjtfjam.setText(null);
hjtftgl.setText(null);
hjtftotal.setText(null);

}

    void settablehjexport(){
    tablehjexport.fixTable(jScrollPane5);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
    model.addColumn("Total");
    model.addColumn("Supplier");
        try {
            String sql = "SELECT pembelian.id_pembelian, pembelian.tanggal_transaksi, pembelian.jam_transaksi, pembelian.uang, "
            + "pembelian.id_pengguna, supplier.nama_supplier from pembelian join supplier "
            + "on pembelian.id_supplier = supplier.id_supplier ;";  
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(5),
                        res.getInt(4),
                        res.getString(6)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehjexport.setModel(model);
    }

    void settablehjual(){
    tablehjual.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Transaksi");
    model.addColumn("Tanggal");
    model.addColumn("Jam");
    model.addColumn("Karyawan");
    model.addColumn("Total");
    model.addColumn("Supplier");
        try {
            String sql = "SELECT pembelian.id_pembelian, pembelian.tanggal_transaksi, pembelian.jam_transaksi, pembelian.uang, "
            + "pembelian.id_pengguna, supplier.nama_supplier from pembelian join supplier "
            + "on pembelian.id_supplier = supplier.id_supplier "
            + "order by tanggal_transaksi desc ;";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(5),
                        res.getInt(4),
                        res.getString(6)
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
            String sql = "select detail_pembelian.id_barang, barang.nama_barang, " +
                    "detail_pembelian.harga_beli, detail_pembelian.qty, barang.id_satuan, detail_pembelian.harga_total " +
                    "from detail_pembelian join barang on detail_pembelian.id_barang = barang.id_barang"
                    + " where id_pembelian = '" + hjtfkdtrans.getText() + "' ;";
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
        exportbeli = new javax.swing.JPanel();
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
        belidetail = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablehjdetail = new tabledark.TableDark();
        hjdetailgtotal = new javax.swing.JLabel();
        hjdetailjam = new javax.swing.JLabel();
        hjdetailkasir = new javax.swing.JLabel();
        hjdetailkembali = new javax.swing.JLabel();
        hjdetailkdtrans = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        hjtfsup = new javax.swing.JLabel();
        hjswitch = new javax.swing.JLabel();
        hjdetail = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        dateChooser.setForeground(new java.awt.Color(70, 204, 89));
        dateChooser.setTextRefernce(hjexporttgldari);

        dateChooser1.setForeground(new java.awt.Color(70, 204, 89));
        dateChooser1.setTextRefernce(hjexporttglsampai);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko-Berkah Riwayat Beli");
        setMinimumSize(new java.awt.Dimension(1280, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(null);

        jenis.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jenis.setForeground(new java.awt.Color(48, 56, 65));
        jenis.setText("hh");
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

        exportbeli.setBackground(new java.awt.Color(235, 235, 235));
        exportbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exportbeli.setFocusCycleRoot(true);
        exportbeli.setFocusTraversalPolicyProvider(true);
        exportbeli.setMinimumSize(new java.awt.Dimension(1016, 615));
        exportbeli.setLayout(null);

        hjexporttglsampai.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjexporttglsampai.setForeground(new java.awt.Color(48, 56, 65));
        hjexporttglsampai.setBorder(null);
        hjexporttglsampai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hjexporttglsampaiActionPerformed(evt);
            }
        });
        exportbeli.add(hjexporttglsampai);
        hjexporttglsampai.setBounds(650, 120, 130, 25);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/tgl.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        exportbeli.add(jLabel3);
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
        exportbeli.add(hjexportexport1);
        hjexportexport1.setBounds(850, 112, 60, 40);

        hjexporttgldari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjexporttgldari.setForeground(new java.awt.Color(48, 56, 65));
        hjexporttgldari.setBorder(null);
        hjexporttgldari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hjexporttgldariActionPerformed(evt);
            }
        });
        exportbeli.add(hjexporttgldari);
        hjexporttgldari.setBounds(220, 120, 130, 25);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/tgl.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        exportbeli.add(jLabel2);
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

        exportbeli.add(jScrollPane5);
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
        exportbeli.add(hjexportkembali);
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
        exportbeli.add(hjexportexport);
        hjexportexport.setBounds(760, 530, 160, 50);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgexportbeli.png"))); // NOI18N
        exportbeli.add(jLabel8);
        jLabel8.setBounds(0, 10, 1030, 600);

        getContentPane().add(exportbeli);
        exportbeli.setBounds(246, 100, 1040, 620);

        belidetail.setBackground(new java.awt.Color(235, 235, 235));
        belidetail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        belidetail.setFocusCycleRoot(true);
        belidetail.setFocusTraversalPolicyProvider(true);
        belidetail.setMinimumSize(new java.awt.Dimension(1016, 615));
        belidetail.setLayout(null);

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

        belidetail.add(jScrollPane4);
        jScrollPane4.setBounds(110, 70, 800, 370);

        hjdetailgtotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        belidetail.add(hjdetailgtotal);
        hjdetailgtotal.setBounds(360, 535, 200, 25);

        hjdetailjam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        belidetail.add(hjdetailjam);
        hjdetailjam.setBounds(120, 535, 200, 25);

        hjdetailkasir.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        belidetail.add(hjdetailkasir);
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
        belidetail.add(hjdetailkembali);
        hjdetailkembali.setBounds(750, 530, 160, 40);

        hjdetailkdtrans.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        belidetail.add(hjdetailkdtrans);
        hjdetailkdtrans.setBounds(120, 475, 200, 25);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgdtljual.png"))); // NOI18N
        belidetail.add(jLabel7);
        jLabel7.setBounds(0, 10, 1030, 600);

        getContentPane().add(belidetail);
        belidetail.setBounds(246, 100, 1040, 620);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        hjtfsup.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjtfsup.setForeground(new java.awt.Color(48, 56, 65));
        jPanel1.add(hjtfsup);
        hjtfsup.setBounds(870, 480, 360, 27);

        hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-jual.png"))); // NOI18N
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
        hjdetail.setBounds(850, 580, 400, 50);

        hjtfkasir.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hjtfkasir.setForeground(new java.awt.Color(48, 56, 65));
        hjtfkasir.setText("  ");
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/bgbeli.png"))); // NOI18N
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

    private void hjhpsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMouseEntered
        // TODO add your handling code here:
hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang-hover.png")));
    }//GEN-LAST:event_hjhpsMouseEntered

    private void hjhpsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMouseExited
        // TODO add your handling code here:
hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang.png")));
    }//GEN-LAST:event_hjhpsMouseExited

    private void hjhpsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjhpsMousePressed
        // TODO add your handling code here:
hjhps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/hapus-panjang-clicked.png")));
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
        if (tablehjual.getValueAt(baris, 5)==null) {
            hjtfsup.setText("");
        } else {
            hjtfsup.setText(tablehjual.getValueAt(baris, 5).toString());
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
    model.addColumn("Total");
    model.addColumn("Supplier");
        try {
            String sql = "SELECT pembelian.id_pembelian, pembelian.tanggal_transaksi, pembelian.jam_transaksi, pembelian.uang, "
            + "pembelian.id_pengguna, supplier.nama_supplier from pembelian join supplier "
            + "on pembelian.id_supplier = supplier.id_supplier "
            + "where id_pembelian like '%" + hjcari.getText() + "%'";
            Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    model.addRow (new Object[] {res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(5),
                        res.getInt(4),
                        res.getString(6)
                });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
tablehjual.setModel(model);
    }//GEN-LAST:event_hjcariKeyReleased

    private void hjexportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportMouseClicked
        // TODO add your handling code here:
        exportbeli.setVisible(true);
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
            panggilAlertMerah("Hapus Gagal!","pilih dulu Kode Transaksi yang ingin dihapus");
        } else {
                    AlertPertanyaan obj = new AlertPertanyaan(this);
        obj.showMessage("Apakah Anda Yakin ?", "Ingin Menghapus Riawayat Transaksi Dengan Kode "+hjtfkdtrans.getText()+"?");
        if (obj.getMessageType() == AlertPertanyaan.MessageType.OK) {
             try {
            String sql ="DELETE FROM pembelian where id_pembelian ='"+hjtfkdtrans.getText()+"'";
        java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            panggilAlertHijau("Hapus Berhasil !","Data Riwayat Transaksi Dengan Kode "+hjtfkdtrans.getText()+" berhasil dihapus");
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
    belidetail.setVisible(true);
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

    private void hjswitchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseClicked
        // TODO add your handling code here:
                    RiwayatJual riwayatjual = new RiwayatJual();
riwayatjual.nama.setText(nama.getText());
riwayatjual.setVisible(true);
this.dispose();
    }//GEN-LAST:event_hjswitchMouseClicked

    private void hjswitchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseEntered
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-jual-hover.png")));
    }//GEN-LAST:event_hjswitchMouseEntered

    private void hjswitchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseExited
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-jual.png")));
    }//GEN-LAST:event_hjswitchMouseExited

    private void hjswitchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMousePressed
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-jual-clicked.png")));
    }//GEN-LAST:event_hjswitchMousePressed

    private void hjswitchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjswitchMouseReleased
        // TODO add your handling code here:
hjswitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/pindah-jual.png")));
    }//GEN-LAST:event_hjswitchMouseReleased

    private void hjdetailkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjdetailkembaliMouseClicked
        // TODO add your handling code here:
        belidetail.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_hjdetailkembaliMouseClicked

    private void hjexportkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportkembaliMouseClicked
        // TODO add your handling code here:
kosong();
exportbeli.setVisible(false);
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
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export.png")));
    }//GEN-LAST:event_hjexportexportMouseEntered

    private void hjexportexportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMouseExited
        // TODO add your handling code here:
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export.png")));
    }//GEN-LAST:event_hjexportexportMouseExited

    private void hjexportexportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMousePressed
        // TODO add your handling code here:
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export.png")));
    }//GEN-LAST:event_hjexportexportMousePressed

    private void hjexportexportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hjexportexportMouseReleased
        // TODO add your handling code here:
hjexportexport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/history/export.png")));
    }//GEN-LAST:event_hjexportexportMouseReleased

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
            java.util.logging.Logger.getLogger(RiwayatBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiwayatBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiwayatBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiwayatBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiwayatBeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JPanel belidetail;
    private javax.swing.JLabel dashboard;
    private com.raven.datechooser.DateChooser dateChooser;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JPanel exportbeli;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel header;
    private javax.swing.JTextField hjcari;
    private javax.swing.JLabel hjdetail;
    private javax.swing.JLabel hjdetailgtotal;
    private javax.swing.JLabel hjdetailjam;
    private javax.swing.JLabel hjdetailkasir;
    private javax.swing.JLabel hjdetailkdtrans;
    private javax.swing.JLabel hjdetailkembali;
    private javax.swing.JLabel hjexport;
    private javax.swing.JLabel hjexportexport;
    private javax.swing.JLabel hjexportexport1;
    private javax.swing.JLabel hjexportkembali;
    private javax.swing.JTextField hjexporttgldari;
    private javax.swing.JTextField hjexporttglsampai;
    private javax.swing.JLabel hjhps;
    private javax.swing.JLabel hjswitch;
    private javax.swing.JLabel hjtfjam;
    private javax.swing.JLabel hjtfkasir;
    private javax.swing.JLabel hjtfkdtrans;
    private javax.swing.JLabel hjtfsup;
    private javax.swing.JLabel hjtftgl;
    private javax.swing.JLabel hjtftotal;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jenis;
    private javax.swing.JLabel karyawan;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JLabel retur;
    private javax.swing.JLabel riwayat;
    private tabledark.TableDark tablehjdetail;
    private tabledark.TableDark tablehjexport;
    public static tabledark.TableDark tablehjual;
    private javax.swing.JLabel transaksi;
    // End of variables declaration//GEN-END:variables
}
