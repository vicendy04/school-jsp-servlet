/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import data_access.SqlServerDataAccess;
import entity.PhieuMuon;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hoang Tran
 */
public class PhieuMuonDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<PhieuMuon> getAllPhieuMuon() {
        List<PhieuMuon> danhSachPhieuMuon = new ArrayList<>();

        try {
            // 1. Mở kết nối đến cơ sở dữ liệu
            connection = new SqlServerDataAccess().getConnection();

            // 2. Tạo câu truy vấn SQL
            String sql = "SELECT * FROM PhieuMuon";

            // 3. Chuẩn bị và thực thi câu lệnh
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // 4. Duyệt qua kết quả và tạo các đối tượng PhieuMuon
            while (resultSet.next()) {
                int maPhieuMuon = resultSet.getInt("maPhieuMuon");
                Date ngayMuon = resultSet.getDate("ngayMuon");
                Date ngayHenTra = resultSet.getDate("ngayHenTra");
                int tongSoLuongSachMuon = resultSet.getInt("tongSoLuongSachMuon");
                String maNguoiDung = resultSet.getString("maNguoiDung");
                String ghiChu = resultSet.getString("ghiChu");

                PhieuMuon phieuMuon = new PhieuMuon(maPhieuMuon, ngayMuon, ngayHenTra, tongSoLuongSachMuon, maNguoiDung, ghiChu);
                danhSachPhieuMuon.add(phieuMuon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Đóng tất cả các tài nguyên
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return danhSachPhieuMuon;
    }

    public List<PhieuMuon> searchByMaNguoiDung(String maNguoiDung) {
        List<PhieuMuon> danhSachPhieuMuon = new ArrayList<>();

        try {
            // 1. Mở kết nối đến cơ sở dữ liệu
            connection = new SqlServerDataAccess().getConnection();

            // 2. Tạo câu truy vấn SQL với điều kiện tìm kiếm
            String sql = "SELECT * FROM PhieuMuon WHERE maNguoiDung = ?";

            // 3. Chuẩn bị và thực thi câu lệnh
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maNguoiDung);
            resultSet = preparedStatement.executeQuery();

            // 4. Duyệt qua kết quả và tạo các đối tượng PhieuMuon
            while (resultSet.next()) {
                int maPhieuMuon = resultSet.getInt("maPhieuMuon");
                Date ngayMuon = resultSet.getDate("ngayMuon");
                Date ngayHenTra = resultSet.getDate("ngayHenTra");
                int tongSoLuongSachMuon = resultSet.getInt("tongSoLuongSachMuon");
                String ghiChu = resultSet.getString("ghiChu");

                PhieuMuon phieuMuon = new PhieuMuon(maPhieuMuon, ngayMuon, ngayHenTra, tongSoLuongSachMuon, maNguoiDung, ghiChu);
                danhSachPhieuMuon.add(phieuMuon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Đóng tất cả các tài nguyên
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return danhSachPhieuMuon;
    }
}
