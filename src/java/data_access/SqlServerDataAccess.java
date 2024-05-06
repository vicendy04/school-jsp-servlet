/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hoang Tran
 */
public class SqlServerDataAccess {

    protected Connection connection = null;

    private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String SERVER = "localhost";
    private static final int PORT = 1433;
    private static final String USER = "sa";
    private static final String PASSWORD = "1";
    private static final String DB_NAME = "DB_QuanLyThuVien";
    private static final String CONNECTION_URL = "jdbc:sqlserver://" + SERVER + ":" + PORT + ";databaseName=" + DB_NAME + ";user=" + USER + ";password=" + PASSWORD + ";encrypt=true;trustServerCertificate=true";

    public SqlServerDataAccess() {
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(CONNECTION_URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load SQLServer JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
