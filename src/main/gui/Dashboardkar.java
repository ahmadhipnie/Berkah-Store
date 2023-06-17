/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.gui;

import com.raven.chart.ModelChart;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javaswingdev.message.AlertPertanyaan;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mynam
 */
public class Dashboardkar extends javax.swing.JFrame {
private ImageIcon format = null;
    /**
     * Creates new form Dashboard
     */
    public Dashboardkar() {
        initComponents();
        Trans();
        PendapatanChart();
        PengeluaranChart();
        Pendapatan();
        Pengeluaran();
        stokbarang();
        tabletrans();
        hidePade();
        Cursor();
        setFavIcon();
        Toolkit toolkit = getToolkit ();
        Dimension size = toolkit.getScreenSize();
        setLocation (size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
    }
private void setFavIcon(){
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/main/img/Logoapp.png")));
}

private void Cursor(){
transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
gudang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
btnStokbrg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
btndtlPendapatan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
btndtlTrans.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
btndtlpengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
private void Trans() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime tanggal = LocalDateTime.now();
        String a = (dtf.format(tanggal));
        try {
            String sql = "SELECT count(id_penjualan) as total from penjualan "
                    + "where tanggal_transaksi='" + a + "' ;";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            lblTrans.setText(rs.getString("total"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Pendapatan() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime tanggal = LocalDateTime.now();
        String a = (dtf.format(tanggal));
        DateTimeFormatter dtff = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDateTime tanggall = LocalDateTime.now();
        String aa = (dtff.format(tanggall));
        int b = Integer.parseInt(a);
        switch (b) {
            case 1:
                blnpenda.setText("Januari");
                blnpenge.setText("Januari");
                break;
            case 2:
                blnpenda.setText("Februari");
                blnpenge.setText("Februari");
                break;
            case 3:
                blnpenda.setText("Maret");
                blnpenge.setText("Maret");
                break;
            case 4:
                blnpenda.setText("April");
                blnpenge.setText("April");
                break;
            case 5:
                blnpenda.setText("Mei");
                blnpenge.setText("Mei");
                break;
            case 6:
                blnpenda.setText("Juni");
                blnpenge.setText("Juni");
                break;
            case 7:
                blnpenda.setText("Juli");
                blnpenge.setText("Juli");
                break;
            case 8:
                blnpenda.setText("Agustus");
                blnpenge.setText("Agustus");
                break;
            case 9:
                blnpenda.setText("September");
                blnpenge.setText("September");
                break;
            case 10:
                blnpenda.setText("Oktober");
                blnpenge.setText("Oktober");
                break;
            case 11:
                blnpenda.setText("November");
                blnpenge.setText("November");
                break;
            case 12:
                blnpenda.setText("Desember");
                blnpenge.setText("Desember");
                break;
        }
        blnpendachart.setText(blnpenda.getText());
        blnpengechart.setText(blnpenda.getText());
        try {
            String sql = "SELECT sum(total_bayar) as total from penjualan "
                    + "where tanggal_transaksi between '"+aa+"-01' and '"+aa+"-31'";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            RP rupiah = new RP();
            lblPendapatan.setText(rupiah.formatRupiah((int) rs.getInt("total")).replace(",00", ""));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Pengeluaran() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDateTime tanggal = LocalDateTime.now();
        String a = (dtf.format(tanggal));
        try {
            String sql = "SELECT sum(uang) as total from pembelian "
                    + "where tanggal_transaksi between '"+a+"-01' and '"+a+"-31'";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            RP rupiah = new RP();
            lblPengeluaran.setText(rupiah.formatRupiah((int) rs.getInt("total")).replace(",00", ""));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stokbarang() {
        try {
            String sql = "SELECT count(id_barang) as total from barang "
                    + "where jumlah<=5 ;";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            lblStokbrg.setText(rs.getString("total"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void PendapatanChart() {
        areapendapatan.addLegend("Pendapatan", new Color(12, 84, 175), new Color(0, 108, 247));
        for (int i = 1; i <=31; i++) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
            LocalDateTime tanggal = LocalDateTime.now();
            String a = (dtf.format(tanggal));
            int j = 0;
            try {
                String sql = "SELECT sum(total_bayar) as total from penjualan "
                        + "where tanggal_transaksi between '"+a+"-"+i+"' and '"+a+"-"+i+"'";
                java.sql.Connection conn = (Connection) Config.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                j = rs.getInt("total");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            areapendapatan.addData(new ModelChart(""+i+"", new double[]{j}));
        }
        areapendapatan.start();
    }

    public void PengeluaranChart() {
        areapengeluaran.addLegend("Pengeluaran", new Color(186, 37, 37), new Color(241, 100, 120));
        for (int i = 1; i <=31; i++) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
            LocalDateTime tanggal = LocalDateTime.now();
            String a = (dtf.format(tanggal));
            int j = 0;
            try {
                String sql = "SELECT sum(uang) as total from pembelian "
                        + "where tanggal_transaksi between '"+a+"-"+i+"' and '"+a+"-"+i+"'";
                java.sql.Connection conn = (Connection) Config.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                j = rs.getInt("total");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            areapengeluaran.addData(new ModelChart(""+i+"", new double[]{j}));
        }
        areapengeluaran.start();
        
    }

    void tabletrans() {
//        JTableHeader style = tablejual.getTableHeader();
//        tablejual.setRowHeight(25);
//        TableColumn col=tablejual.getColumnModel().getColumn(0);
//        col.setPreferredWidth(40);
//        TableColumn col1=tablejual.getColumnModel().getColumn(1);
//        col1.setPreferredWidth(100);
//        TableColumn col2=tablejual.getColumnModel().getColumn(2);
//        col2.setPreferredWidth(200);
//        TableColumn col3=tablejual.getColumnModel().getColumn(3);
//        col3.setPreferredWidth(74);
//        TableColumn col4=tablejual.getColumnModel().getColumn(4);
//        col4.setPreferredWidth(50);
//        TableColumn col5=tablejual.getColumnModel().getColumn(5);
//        col5.setPreferredWidth(150);
    table_dbtrans.fixTable(jScrollPane3);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("No");
    model.addColumn("Kode Transaksi");
    model.addColumn("Jam");
    model.addColumn("Total Bayar");
    model.addColumn("Nama Kasir");
    
        int no=1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime tgl = LocalDateTime.now();
        String a = (dtf.format(tgl));
        try {
            String sql = "SELECT * from penjualan "
                    + "where tanggal_transaksi='"+a+"' ;";
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while(res.next()){
         model.addRow (new Object[] {no++, res.getString("id_penjualan"),
                        res.getString("jam_transaksi"),
                        res.getInt("total_bayar"),
                        res.getString("id_pengguna")
            });
        }
        table_dbtrans.setModel(model);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    void tablebar() {
//        JTableHeader style = tablejual.getTableHeader();
//        tablejual.setRowHeight(25);
//        TableColumn col=tablejual.getColumnModel().getColumn(0);
//        col.setPreferredWidth(40);
//        TableColumn col1=tablejual.getColumnModel().getColumn(1);
//        col1.setPreferredWidth(100);
//        TableColumn col2=tablejual.getColumnModel().getColumn(2);
//        col2.setPreferredWidth(200);
//        TableColumn col3=tablejual.getColumnModel().getColumn(3);
//        col3.setPreferredWidth(74);
//        TableColumn col4=tablejual.getColumnModel().getColumn(4);
//        col4.setPreferredWidth(50);
//        TableColumn col5=tablejual.getColumnModel().getColumn(5);
//        col5.setPreferredWidth(150);
    table_dbtrans.fixTable(jScrollPane1);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("No");
    model.addColumn("Kode Barang");
    model.addColumn("Nama Barang");
    model.addColumn("Kategori");
    model.addColumn("Kuantitas");
    model.addColumn("Satuan");
    
        int no=1;
        try {
            String sql = "select barang.id_barang, barang.nama_barang, kategori.jenis, jumlah, satuan.satuan "
                    + "from barang join kategori on barang.id_kategori = kategori.id_kategori "
                    + "join satuan on barang.id_satuan = satuan.id_satuan "
                    + "where jumlah<=5 ;";
        java.sql.Connection conn=(Connection)Config.configDB();
        java.sql.Statement stm=conn.createStatement();
        java.sql.ResultSet res=stm.executeQuery(sql);
        while(res.next()){
         model.addRow (new Object[] {no++, res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getDouble(4),
                        res.getString(5)
            });
        }
        table_dbbarang.setModel(model);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void hidePade() {
        panetrans.setVisible(true);
        panebarang.setVisible(false);
        panePendapatan.setVisible(false);
        panePengeluaran.setVisible(false);
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
        jPanel1 = new javax.swing.JPanel();
        panePendapatan = new javax.swing.JPanel();
        blnpendachart = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        areapendapatan = new com.raven.chart.CurveChart();
        panePengeluaran = new javax.swing.JPanel();
        blnpengechart = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        areapengeluaran = new com.raven.chart.CurveChart();
        panebarang = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_dbbarang = new tabledark.TableDark();
        panetrans = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_dbtrans = new tabledark.TableDark();
        lblStokbrg = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnStokbrg = new javax.swing.JLabel();
        lblPengeluaran = new javax.swing.JLabel();
        btndtlpengeluaran = new javax.swing.JLabel();
        btndtlPendapatan = new javax.swing.JLabel();
        lblPendapatan = new javax.swing.JLabel();
        btndtlTrans = new javax.swing.JLabel();
        blnpenge = new javax.swing.JLabel();
        blnpenda = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTrans = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko-Berkah Dashboard");
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

        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/sideBar/dashboard-clicked.png"))); // NOI18N
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
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

        panePendapatan.setBackground(new java.awt.Color(255, 255, 255));
        panePendapatan.setLayout(null);

        blnpendachart.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        blnpendachart.setForeground(new java.awt.Color(48, 56, 65));
        panePendapatan.add(blnpendachart);
        blnpendachart.setBounds(190, 0, 200, 30);

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(48, 56, 65));
        jLabel9.setText("Pendapatan Bulan");
        panePendapatan.add(jLabel9);
        jLabel9.setBounds(10, 0, 180, 30);
        panePendapatan.add(areapendapatan);
        areapendapatan.setBounds(10, 40, 940, 280);

        jPanel1.add(panePendapatan);
        panePendapatan.setBounds(260, 370, 960, 320);

        panePengeluaran.setBackground(new java.awt.Color(255, 255, 255));
        panePengeluaran.setLayout(null);

        blnpengechart.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        blnpengechart.setForeground(new java.awt.Color(48, 56, 65));
        panePengeluaran.add(blnpengechart);
        blnpengechart.setBounds(190, 0, 200, 30);

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(48, 56, 65));
        jLabel11.setText("Pengeluaran Bulan");
        panePengeluaran.add(jLabel11);
        jLabel11.setBounds(10, 0, 180, 30);
        panePengeluaran.add(areapengeluaran);
        areapengeluaran.setBounds(10, 40, 940, 280);

        jPanel1.add(panePengeluaran);
        panePengeluaran.setBounds(260, 370, 960, 320);

        panebarang.setBackground(new java.awt.Color(255, 255, 255));
        panebarang.setLayout(null);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(48, 56, 65));
        jLabel10.setText("Stok Barang Menipis");
        panebarang.add(jLabel10);
        jLabel10.setBounds(10, 0, 250, 30);

        table_dbbarang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_dbbarang);

        panebarang.add(jScrollPane1);
        jScrollPane1.setBounds(20, 40, 920, 260);

        jPanel1.add(panebarang);
        panebarang.setBounds(260, 370, 960, 320);

        panetrans.setBackground(new java.awt.Color(255, 255, 255));
        panetrans.setLayout(null);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(48, 56, 65));
        jLabel8.setText("Transaksi Hari ini");
        panetrans.add(jLabel8);
        jLabel8.setBounds(10, 0, 190, 30);

        table_dbtrans.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(table_dbtrans);

        panetrans.add(jScrollPane3);
        jScrollPane3.setBounds(20, 40, 920, 260);

        jPanel1.add(panetrans);
        panetrans.setBounds(260, 370, 960, 320);

        lblStokbrg.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        lblStokbrg.setForeground(new java.awt.Color(238, 48, 48));
        lblStokbrg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStokbrg.setText("hhh");
        jPanel1.add(lblStokbrg);
        lblStokbrg.setBounds(1020, 200, 200, 30);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(238, 48, 48));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Barang");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(1020, 230, 200, 30);

        btnStokbrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/db/dtlmerah.png"))); // NOI18N
        btnStokbrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStokbrgMouseClicked(evt);
            }
        });
        jPanel1.add(btnStokbrg);
        btnStokbrg.setBounds(1030, 280, 180, 40);

        lblPengeluaran.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblPengeluaran.setForeground(new java.awt.Color(255, 154, 81));
        lblPengeluaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPengeluaran.setText("hhh");
        jPanel1.add(lblPengeluaran);
        lblPengeluaran.setBounds(770, 200, 200, 50);

        btndtlpengeluaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/db/dtloren.png"))); // NOI18N
        btndtlpengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndtlpengeluaranMouseClicked(evt);
            }
        });
        jPanel1.add(btndtlpengeluaran);
        btndtlpengeluaran.setBounds(780, 280, 180, 40);

        btndtlPendapatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/db/dtlhijau2.png"))); // NOI18N
        btndtlPendapatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndtlPendapatanMouseClicked(evt);
            }
        });
        jPanel1.add(btndtlPendapatan);
        btndtlPendapatan.setBounds(530, 280, 180, 40);

        lblPendapatan.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblPendapatan.setForeground(new java.awt.Color(70, 204, 89));
        lblPendapatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPendapatan.setText("hhh");
        jPanel1.add(lblPendapatan);
        lblPendapatan.setBounds(520, 200, 200, 50);

        btndtlTrans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/db/dtlhijau.png"))); // NOI18N
        btndtlTrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndtlTransMouseClicked(evt);
            }
        });
        jPanel1.add(btndtlTrans);
        btndtlTrans.setBounds(280, 280, 180, 40);

        blnpenge.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        blnpenge.setForeground(new java.awt.Color(255, 154, 81));
        blnpenge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(blnpenge);
        blnpenge.setBounds(770, 170, 200, 30);

        blnpenda.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        blnpenda.setForeground(new java.awt.Color(70, 204, 89));
        blnpenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(blnpenda);
        blnpenda.setBounds(520, 170, 200, 30);

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(70, 204, 89));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Transaksi");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(270, 230, 200, 30);

        lblTrans.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        lblTrans.setForeground(new java.awt.Color(70, 204, 89));
        lblTrans.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrans.setText("hhh");
        jPanel1.add(lblTrans);
        lblTrans.setBounds(270, 200, 200, 30);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 154, 81));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pengeluaran Bulan");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(770, 140, 200, 30);

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(238, 48, 48));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Stok Barang Menipis");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(1020, 140, 200, 30);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(70, 204, 89));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pendapatan Bulan");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(520, 140, 200, 30);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(70, 204, 89));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Transaksi Hari ini");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(270, 140, 200, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/img/db/bg.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(250, 120, 990, 589);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndtlTransMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndtlTransMouseClicked
        // TODO add your handling code here:
        tabletrans();
        panetrans.setVisible(true);
        panebarang.setVisible(false);
        panePendapatan.setVisible(false);
        panePengeluaran.setVisible(false);
    }//GEN-LAST:event_btndtlTransMouseClicked

    private void btndtlPendapatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndtlPendapatanMouseClicked
        // TODO add your handling code here:
        panetrans.setVisible(false);
        panebarang.setVisible(false);
        panePendapatan.setVisible(true);
        panePengeluaran.setVisible(false);
    }//GEN-LAST:event_btndtlPendapatanMouseClicked

    private void btndtlpengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndtlpengeluaranMouseClicked
        // TODO add your handling code here:
        panetrans.setVisible(false);
        panebarang.setVisible(false);
        panePendapatan.setVisible(false);
        panePengeluaran.setVisible(true);
    }//GEN-LAST:event_btndtlpengeluaranMouseClicked

    private void btnStokbrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStokbrgMouseClicked
        // TODO add your handling code here:
        tablebar();
        panetrans.setVisible(false);
        panebarang.setVisible(true);
        panePendapatan.setVisible(false);
        panePengeluaran.setVisible(false);
    }//GEN-LAST:event_btnStokbrgMouseClicked

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
    }//GEN-LAST:event_dashboardMouseEntered

    private void dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboardMouseExited

    private void dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboardMousePressed

    private void dashboardMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseReleased
        // TODO add your handling code here:
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
                    RiwayatJualkar riwayatkar = new RiwayatJualkar();
                    riwayatkar.nama.setText(nama.getText());
                    riwayatkar.setVisible(true);
                    this.dispose();
    }//GEN-LAST:event_riwayatMouseClicked

    private void namaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_namaAncestorAdded
        // TODO add your handling code here:
setprofile();
    }//GEN-LAST:event_namaAncestorAdded

    private void gudangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gudangMouseClicked
        // TODO add your handling code here:
                    Gudangkar gudangkar = new Gudangkar();
                    gudangkar.nama.setText(nama.getText());
                    gudangkar.setVisible(true);
                    this.dispose();
    }//GEN-LAST:event_gudangMouseClicked

    private void pengaturanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengaturanMouseClicked
        // TODO add your handling code here:
                    Settingkar settingkar= new Settingkar();
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
            java.util.logging.Logger.getLogger(Dashboardkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboardkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboardkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboardkar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboardkar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private com.raven.chart.CurveChart areapendapatan;
    private com.raven.chart.CurveChart areapengeluaran;
    private javax.swing.JLabel blnpenda;
    private javax.swing.JLabel blnpendachart;
    private javax.swing.JLabel blnpenge;
    private javax.swing.JLabel blnpengechart;
    private javax.swing.JLabel btnStokbrg;
    private javax.swing.JLabel btndtlPendapatan;
    private javax.swing.JLabel btndtlTrans;
    private javax.swing.JLabel btndtlpengeluaran;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel gudang;
    private javax.swing.JLabel header;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jenis;
    private javax.swing.JLabel lblPendapatan;
    private javax.swing.JLabel lblPengeluaran;
    private javax.swing.JLabel lblStokbrg;
    private javax.swing.JLabel lblTrans;
    private javax.swing.JLabel logout;
    public javax.swing.JLabel nama;
    private javax.swing.JPanel panePendapatan;
    private javax.swing.JPanel panePengeluaran;
    private javax.swing.JPanel panebarang;
    private javax.swing.JPanel panetrans;
    private javax.swing.JLabel pengaturan;
    private javax.swing.JLabel riwayat;
    private tabledark.TableDark table_dbbarang;
    private tabledark.TableDark table_dbtrans;
    private javax.swing.JLabel transaksi;
    // End of variables declaration//GEN-END:variables
}
