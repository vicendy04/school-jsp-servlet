/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author t
 */
import data_access.SqlServerDataAccess;
import dto.PhieuMuonDTO;
import entity.NguoiDung;
import entity.PhieuMuon;
import entity.PhieuMuonChiTiet;
import entity.PhieuTra;
import entity.Sach;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sachdao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<Sach> getAllSach() {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach";
        try {
            connection = new SqlServerDataAccess().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Sach(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getDouble(7),
                        resultSet.getDate(8),
                        resultSet.getString(9),
                        resultSet.getString(10)));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
        return list;
    }

    public Sach getLast() {
        String sql = "select top 1 * from Sach \n"
                + "order by MaSach  desc";
        try {
            connection = new SqlServerDataAccess().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Sach(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getDouble(7),
                        resultSet.getDate(8),
                        resultSet.getString(9),
                        resultSet.getString(10));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
        return null;
    }

    public List<Sach> getSachByCID(String mals) {
        List<Sach> list = new ArrayList<>();
        String sql = "select * from Sach\n"
                + "where MaLoaiSach= ?";
        try {
            connection = new SqlServerDataAccess().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mals);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Sach(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getDouble(7),
                        resultSet.getDate(8),
                        resultSet.getString(9),
                        resultSet.getString(10)));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
        return list;
    }

    private static final String FOLDER_PATH = "D:\\NetbeanProjects\\JAVA_NC\\CuoiKy\\WebQuanLyThuVien\\web\\images\\sach"; // Thay thế với đường dẫn thực tế

    public List<String> getAllAnh() {
        List<String> listAnh = new ArrayList<>();
        File folder = new File(FOLDER_PATH);

        // Kiểm tra xem thư mục tồn tại hay không
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Lỗi: Thư mục không tồn tại hoặc không phải là thư mục!");
            return listAnh;
        }

        // Lấy tất cả các tệp và thư mục trong folder
        File[] listOfFiles = folder.listFiles();

        // Kiểm tra nếu folder không trống
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    // Thêm tên tệp vào danh sách nếu đó là một tệp
                    listAnh.add(file.getName());
                }
            }
        } else {
            System.err.println("Lỗi: Thư mục rỗng hoặc không có quyền truy cập!");
        }
        return listAnh;
    }

    public NguoiDung login(String user, String pass) {
        String sql = "select * from NguoiDung\n"
                + "where [MaNguoiDung] = ?\n"
                + "and [MatKhau] =?";
        try {
            connection = new SqlServerDataAccess().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new NguoiDung(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            }

        } catch (Exception e) {
        }

        return null;
    }

    public Sach getProductById(String productId) {
        String sql = "SELECT * FROM Sach WHERE MaSach = ?";
        try {
            connection = new SqlServerDataAccess().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Sach(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getDouble(7),
                        resultSet.getDate(8),
                        resultSet.getString(9),
                        resultSet.getString(10));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        } finally {
            close();
        }
        return null;
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi đóng kết nối: " + ex.getMessage());
        }
    }

    public void addPhieuMuon(PhieuMuon phieuMuon) {
        String sql = "INSERT INTO PhieuMuon (NgayMuon, NgayHenTra, TongSoLuongSachMuon, MaNguoiDung,MaPhieuMuon) VALUES (?, ?, ?, ?,?)";
        try {
            connection = new SqlServerDataAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(phieuMuon.getNgayMuon().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(phieuMuon.getNgayHenTra().getTime()));
            preparedStatement.setInt(3, phieuMuon.getTongSoLuongSachMuon());
            preparedStatement.setString(4, phieuMuon.getMaNguoiDung());
            preparedStatement.setInt(5, phieuMuon.getMaPhieuMuon());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }

    public List<PhieuMuonDTO> LSPhieuMuon(String userId) throws SQLException {
        List<PhieuMuonDTO> listPhieuMuonDTO = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = new SqlServerDataAccess().getConnection();
            String sql = "SELECT\n"
                    + "    pm.MaPhieuMuon,\n"
                    + "    pm.NgayMuon,\n"
                    + "    pm.NgayHenTra,\n"
                    + "    ptr.NgayTra AS NgayTra,\n"
                    + "    ctpm.SoLuongSachMuonMoiLoai,\n"
                    + "    s.TieuDe\n"
                    + "FROM\n"
                    + "    PhieuMuon pm\n"
                    + "INNER JOIN ChiTietPhieuMuon ctpm ON pm.MaPhieuMuon = ctpm.MaPhieuMuon\n"
                    + "LEFT JOIN Sach s ON ctpm.MaSach = s.MaSach\n"
                    + "LEFT JOIN PhieuTra ptr ON pm.MaPhieuMuon = ptr.MaPhieuMuon\n"
                    + "WHERE\n"
                    + "    pm.MaNguoiDung = ?\n"
                    + "ORDER BY\n"
                    + "    pm.NgayMuon DESC;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setMaPhieuMuon(resultSet.getInt("MaPhieuMuon"));
                phieuMuon.setNgayMuon(resultSet.getDate("NgayMuon"));
                phieuMuon.setNgayHenTra(resultSet.getDate("NgayHenTra"));
                PhieuTra phieuTra = new PhieuTra();
                phieuTra.setNgayTra(resultSet.getDate("NgayTra"));

                Sach sach = new Sach();
                sach.setTieuDe(resultSet.getString("TieuDe"));

                PhieuMuonChiTiet phieuMuonChiTiet = new PhieuMuonChiTiet();
                phieuMuonChiTiet.setSlSachMuonMoiLoai(resultSet.getInt("SoLuongSachMuonMoiLoai"));

                PhieuMuonDTO phieuMuonDTO = new PhieuMuonDTO(phieuMuon, sach, phieuTra, phieuMuonChiTiet);
                listPhieuMuonDTO.add(phieuMuonDTO);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listPhieuMuonDTO;
    }

    public List<Sach> searchByName(String txtSearch) {
        List<Sach> list = new ArrayList<>();
//        String sql = "SELECT * FROM Sach\n"
//                + " WHERE [TieuDe] LIKE ?";
        String sql = "SELECT * FROM Sach WHERE TieuDe LIKE ?";

        try {
            connection = new SqlServerDataAccess().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + txtSearch + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Sach(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDouble(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        Sachdao sd = new Sachdao();
        System.out.println("Danh sách các tệp ảnh:");
        List<String> listAnh = sd.getAllAnh();
        for (String anh : listAnh) {
            System.out.println(anh);
        }
    }
}
