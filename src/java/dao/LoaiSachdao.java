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
import entity.LoaiSach;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LoaiSachdao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public List<LoaiSach> getALLLoaiSach()
    {
        List<LoaiSach> list=new ArrayList<>();
        String sql="Select *from LoaiSach";
        try {
            connection = new SqlServerDataAccess().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
              list.add(new LoaiSach(resultSet.getString(1),
                        resultSet.getString(2)));
            }
        } catch (Exception e) {
             System.out.println("Lỗi: " + e.getMessage());
        }
        return list;
    }
    
    
     public static void main(String[] args) {
        LoaiSachdao lsd=new LoaiSachdao();
        List<LoaiSach> list = lsd.getALLLoaiSach();
        list.forEach((LoaiSach t) -> {
            System.out.println(t);
        });
     }
}
