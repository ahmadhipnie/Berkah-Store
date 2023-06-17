-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2022 at 03:54 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_berkah`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` char(6) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `id_kategori` char(6) NOT NULL,
  `id_satuan` char(6) NOT NULL,
  `jumlah` double NOT NULL,
  `barcode` varchar(15) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `retur` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `id_kategori`, `id_satuan`, `jumlah`, `barcode`, `harga_beli`, `harga_jual`, `retur`) VALUES
('ATK001', 'Bulpoin Greebel 0.5', 'ATK', 'Pcs', 240, '', 2000, 2300, 'Tidak'),
('ATK002', 'Bulpoin Standard 0.5', 'ATK', 'Bks', 188, '2323232323239', 15000, 15000, 'Iya'),
('ATK003', 'Penghapus Erson', 'ATK', 'Bks', 493, '1231231231239', 5000, 2000, 'Tidak'),
('ATK004', 'Spidolman', 'ATK', 'Pcs', 73, '', 4000, 5000, 'Iya'),
('ATK005', 'alat kantor', 'ATK', 'Dus', 50, '', 0, 5000, 'Iya'),
('ATK006', 'Indomie rendang', 'ATK', 'Bks', 97, '089686910704', 0, 3000, 'Iya'),
('EKT001', 'Baterai ABC ', 'EKT', 'Bks', 0, 'null', 0, 2500, 'Tidak'),
('KMS001', 'plastik', 'KMS', 'Bks', 4971, '', 0, 8000, 'Iya'),
('MRG001', 'Tiara', 'MRG', 'Pcs', 159, '', 400, 460, 'Iya'),
('MSC001', 'Aqua sachet', 'MSC', 'Bks', 154, '', 0, 5000, 'Tidak'),
('MSK001', 'Indomie', 'MSK', 'Bks', 200, '089686010527', 2500, 2875, 'Iya'),
('PRT001', 'Daia 250 g', 'PRT', 'Bks', 787, '', 0, 55555, 'Tidak'),
('SBK001', 'Indomie goreng', 'SBK', 'Bks', 293, '089686140057', 0, 2800, 'Tidak');

-- --------------------------------------------------------

--
-- Table structure for table `cart_pembelian`
--

CREATE TABLE `cart_pembelian` (
  `id_pembelian` char(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `qty` double NOT NULL,
  `harga_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cart_penjualan`
--

CREATE TABLE `cart_penjualan` (
  `id_penjualan` char(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `harga` int(11) NOT NULL,
  `qty` double NOT NULL,
  `harga_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cart_retur_cus`
--

CREATE TABLE `cart_retur_cus` (
  `id_retur` varchar(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `qty` double NOT NULL,
  `sub_total` double NOT NULL,
  `keterangan` varchar(255) NOT NULL,
  `status` varchar(5) NOT NULL,
  `id_supplier` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cart_retur_supplier`
--

CREATE TABLE `cart_retur_supplier` (
  `id_retur_sup` varchar(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `qty` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembelian`
--

CREATE TABLE `detail_pembelian` (
  `id_pembelian` char(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `qty` double NOT NULL,
  `harga_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `detail_pembelian`
--
DELIMITER $$
CREATE TRIGGER `SETHARGJUAL` AFTER INSERT ON `detail_pembelian` FOR EACH ROW BEGIN
UPDATE barang SET harga_jual = NEW.harga_beli+(NEW.harga_beli*20/100)
WHERE id_barang=NEW.id_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Tambah Stok` AFTER INSERT ON `detail_pembelian` FOR EACH ROW BEGIN
UPDATE barang SET jumlah = jumlah+NEW.qty
WHERE id_barang=NEW.id_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Tambah uang` AFTER INSERT ON `detail_pembelian` FOR EACH ROW BEGIN
UPDATE pembelian SET uang = uang+NEW.harga_total
WHERE id_pembelian=NEW.id_pembelian;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Update Harga Beli` AFTER INSERT ON `detail_pembelian` FOR EACH ROW BEGIN
UPDATE barang SET harga_beli = NEW.harga_beli
WHERE id_barang=NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id_penjualan` char(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `harga` int(11) NOT NULL,
  `qty` double NOT NULL,
  `harga_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id_penjualan`, `id_barang`, `harga`, `qty`, `harga_total`) VALUES
('TR-11062022-001', 'ATK002', 15000, 2, 30000),
('TR-12062022-001', 'MSK001', 2875, 20, 57500),
('TR-12062022-002', 'KMS001', 8000, 3, 24000),
('TR-12062022-002', 'MSC001', 5000, 5, 25000),
('TR-12062022-002', 'MRG001', 460, 10, 4600),
('TR-12062022-002', 'EKT001', 2500, 2, 5000),
('TR-12062022-003', 'ATK006', 3000, 3, 9000),
('TR-12062022-003', 'ATK002', 15000, 1, 15000);

--
-- Triggers `detail_penjualan`
--
DELIMITER $$
CREATE TRIGGER `Kurang STOK` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
 UPDATE barang SET jumlah=jumlah-NEW.qty
 WHERE id_barang=NEW.id_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `UANG Penjualan` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
UPDATE penjualan SET total_bayar = total_bayar+NEW.harga_total
WHERE id_penjualan=NEW.id_penjualan;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_retur_cos`
--

CREATE TABLE `detail_retur_cos` (
  `id_retur` varchar(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `qty` double NOT NULL,
  `sub_total` double NOT NULL,
  `keterangan` varchar(255) NOT NULL,
  `status` varchar(5) NOT NULL,
  `id_supplier` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detail_retur_supplier`
--

CREATE TABLE `detail_retur_supplier` (
  `id_retur_sup` varchar(20) NOT NULL,
  `id_barang` char(6) NOT NULL,
  `qty` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` char(6) NOT NULL,
  `jenis` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `jenis`) VALUES
('ATK', 'Alat Tulis Komputer'),
('BLL', 'Barang Lain'),
('EKT', 'Barang Elektronik'),
('KBY', 'Keperluan Bayi'),
('KMS', 'Kemasan'),
('MKM', 'Minuman Kemasan'),
('MRG', 'Makanan Ringan'),
('MSC', 'Minuman Sachet'),
('MSK', 'Bahan Memasak'),
('OBT', 'Obat obatan'),
('PMM', 'Perlengkapan Mencuci'),
('PRT', 'Perlengkapan Rumah'),
('SBK', 'Bahan Sembako');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `id_pembelian` char(20) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `jam_transaksi` time NOT NULL,
  `uang` double NOT NULL,
  `id_pengguna` char(6) NOT NULL,
  `id_supplier` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` char(6) NOT NULL,
  `nama_pengguna` varchar(30) NOT NULL,
  `alamat_pengguna` varchar(80) NOT NULL,
  `telp_pengguna` char(12) NOT NULL,
  `jenis_pengguna` char(8) NOT NULL,
  `password` varchar(8) NOT NULL,
  `img` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `alamat_pengguna`, `telp_pengguna`, `jenis_pengguna`, `password`, `img`) VALUES
('admin', 'Admin', 'dbdbdbdbdb', '081444555666', 'Admin', 'admin', 'C:\\toko-berkah\\admin.jpg'),
('agim', 'Gymnastiar', 'Madiun', '081999888777', 'Karyawan', 'agim', 'C:\\toko-berkah\\agim.jpg'),
('aku', 'aku', 'yoaku', '081234567890', 'Admin', 'aku', NULL),
('Arthur', 'Arthurnya nilla', 'Wringinanom', '089504395516', 'Karyawan', 'sayanil', NULL),
('kiki', 'kiki', 'sds', '888888888888', 'Admin', 'koko', NULL),
('pozaka', 'popo zakaria', 'mmdmd', '999888777666', 'Karyawan', 'popo', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` char(20) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `jam_transaksi` time NOT NULL,
  `total_bayar` double NOT NULL,
  `uang` double NOT NULL,
  `kembalian` double NOT NULL,
  `id_pengguna` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `tanggal_transaksi`, `jam_transaksi`, `total_bayar`, `uang`, `kembalian`, `id_pengguna`) VALUES
('TR-11062022-001', '2022-06-11', '20:26:59', 30000, 30000, 0, 'agim'),
('TR-12062022-001', '2022-06-12', '07:45:06', 57500, 60000, 2500, 'agim'),
('TR-12062022-002', '2022-06-12', '07:48:15', 58600, 60000, 1400, 'admin'),
('TR-12062022-003', '2022-06-12', '16:13:47', 24000, 30000, 6000, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `retur_customer`
--

CREATE TABLE `retur_customer` (
  `id_retur_cus` varchar(20) NOT NULL,
  `id_penjualan` varchar(20) NOT NULL,
  `tanggal` date NOT NULL,
  `jam` time NOT NULL,
  `id_karyawan` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `retur_supplier`
--

CREATE TABLE `retur_supplier` (
  `id_retur_sup` varchar(20) NOT NULL,
  `id_supplier` char(6) NOT NULL,
  `tanggal_balik` date NOT NULL,
  `jam` time NOT NULL,
  `id_pengguna` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `satuan`
--

CREATE TABLE `satuan` (
  `id_satuan` char(6) NOT NULL,
  `satuan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `satuan`
--

INSERT INTO `satuan` (`id_satuan`, `satuan`) VALUES
('Bks', 'Bungkus'),
('Btl', 'Botol'),
('Cm', 'Centi Meter'),
('Dus', 'Dus'),
('Gr', 'Gram'),
('Kg', 'Kilo Gram'),
('Kodi', 'Kodi'),
('l', 'Liter'),
('Lbr', 'Lembar'),
('M', 'Meter'),
('Ml', 'Mili Liter'),
('Ons', 'Ons'),
('Pack', 'Pack'),
('Pcs', 'Pieces'),
('Rtg', 'Renteng'),
('Tbl', 'Tablet');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` char(6) NOT NULL,
  `nama_supplier` char(30) NOT NULL,
  `alamat_supplier` varchar(80) NOT NULL,
  `telp_supplier` char(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `alamat_supplier`, `telp_supplier`) VALUES
('S00001', 'Popo', 'Bubulan,Bojonegoro', '081217634111'),
('S00002', 'Wishal', 'Mangli,Jember', '082232937743'),
('S00003', 'Karen', 'Jagalan,Solo', '083864672711'),
('S00004', 'Erik', 'Kepatihan,Jember', '082228455809'),
('S00005', 'Putra', 'Bungatan.Situbondo', '082333564179'),
('S00006', 'Satria', 'Situbondo, Situbondo', '082332991987'),
('S00007', 'Nilla putri', 'Wringinanom,Gresik', '089504395516'),
('S00008', 'Andru', 'Pasanggaran,Banyuwangi', '85232398005'),
('S00009', 'Ammar', 'Sampusari,Jember', '085335362801'),
('S00010', 'Daffa', 'Banyuanyar,Probolinggo', '089522687919'),
('S00011', 'Doni', 'Sidoarjo', '081222333444');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_kategori` (`id_kategori`,`id_satuan`),
  ADD KEY `id_satuan` (`id_satuan`);

--
-- Indexes for table `cart_pembelian`
--
ALTER TABLE `cart_pembelian`
  ADD KEY `id_pembelian` (`id_pembelian`,`id_barang`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `cart_penjualan`
--
ALTER TABLE `cart_penjualan`
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_tranksaksi` (`id_penjualan`);

--
-- Indexes for table `cart_retur_cus`
--
ALTER TABLE `cart_retur_cus`
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_retur` (`id_retur`);

--
-- Indexes for table `cart_retur_supplier`
--
ALTER TABLE `cart_retur_supplier`
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_pembelian` (`id_pembelian`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD KEY `id_tranksaksi` (`id_penjualan`,`id_barang`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `detail_retur_cos`
--
ALTER TABLE `detail_retur_cos`
  ADD PRIMARY KEY (`id_retur`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `detail_retur_supplier`
--
ALTER TABLE `detail_retur_supplier`
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_retur_sup` (`id_retur_sup`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`id_pembelian`),
  ADD KEY `id_anggota` (`id_pengguna`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`),
  ADD KEY `id_pengguna` (`id_pengguna`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_tranksaksi` (`id_penjualan`),
  ADD KEY `id_anggota` (`id_pengguna`);

--
-- Indexes for table `retur_customer`
--
ALTER TABLE `retur_customer`
  ADD PRIMARY KEY (`id_retur_cus`),
  ADD KEY `id_pennjualan` (`id_penjualan`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indexes for table `retur_supplier`
--
ALTER TABLE `retur_supplier`
  ADD PRIMARY KEY (`id_retur_sup`),
  ADD KEY `id_supplier` (`id_supplier`),
  ADD KEY `id_pengguna` (`id_pengguna`);

--
-- Indexes for table `satuan`
--
ALTER TABLE `satuan`
  ADD PRIMARY KEY (`id_satuan`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_2` FOREIGN KEY (`id_satuan`) REFERENCES `satuan` (`id_satuan`),
  ADD CONSTRAINT `barang_ibfk_3` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`);

--
-- Constraints for table `cart_pembelian`
--
ALTER TABLE `cart_pembelian`
  ADD CONSTRAINT `cart_pembelian_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `cart_penjualan`
--
ALTER TABLE `cart_penjualan`
  ADD CONSTRAINT `cart_penjualan_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD CONSTRAINT `detail_pembelian_ibfk_1` FOREIGN KEY (`id_pembelian`) REFERENCES `pembelian` (`id_pembelian`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_pembelian_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id_penjualan`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `detail_retur_cos`
--
ALTER TABLE `detail_retur_cos`
  ADD CONSTRAINT `detail_retur_cos_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `detail_retur_cos_ibfk_3` FOREIGN KEY (`id_retur`) REFERENCES `retur_customer` (`id_retur_cus`) ON DELETE CASCADE;

--
-- Constraints for table `detail_retur_supplier`
--
ALTER TABLE `detail_retur_supplier`
  ADD CONSTRAINT `detail_retur_supplier_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `detail_retur_supplier_ibfk_3` FOREIGN KEY (`id_retur_sup`) REFERENCES `retur_supplier` (`id_retur_sup`) ON DELETE CASCADE;

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`),
  ADD CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `retur_customer`
--
ALTER TABLE `retur_customer`
  ADD CONSTRAINT `retur_customer_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `pengguna` (`id_pengguna`),
  ADD CONSTRAINT `retur_customer_ibfk_2` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id_penjualan`) ON DELETE CASCADE;

--
-- Constraints for table `retur_supplier`
--
ALTER TABLE `retur_supplier`
  ADD CONSTRAINT `retur_supplier_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`),
  ADD CONSTRAINT `retur_supplier_ibfk_2` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
