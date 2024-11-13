package database;

import java.sql.*;

public class CRUDOperationsImpl implements CRUDOperations {

    @Override
    public void create(String sql) {
        try (Connection conn = DatabaseConnectionPool.getConnection();
                Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("插入的行数: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
        }
    }

    @Override
    public void read(String sql) {
        try (Connection conn = DatabaseConnectionPool.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println("ID: " + id + ", 姓名: " + name);
            }
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
        }
    }

    @Override
    public void update(String sql) {
        try (Connection conn = DatabaseConnectionPool.getConnection();
                Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("更新的行数: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
        }
    }

    @Override
    public void delete(String sql) {
        try (Connection conn = DatabaseConnectionPool.getConnection();
                Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("删除的行数: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
        }
    }
}
