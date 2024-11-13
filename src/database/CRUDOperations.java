package database;

import java.sql.*;

public class CRUDOperations {

    // 创建记录
    public static void create(String sql) {
        try (Connection conn = DatabaseConnection.getConnection(null);
                Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("插入的行数: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            e.printStackTrace(); // 打印堆栈信息
        }
    }

    // 读取记录
    public static void read(String sql) {
        try (Connection conn = DatabaseConnection.getConnection(null);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (!rs.next()) {
                System.out.println("没有找到符合条件的记录。");
                return; // 如果查询结果为空，提前返回
            }
            do {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println("ID: " + id + ", 姓名: " + name);
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            e.printStackTrace(); // 打印堆栈信息
        }
    }

    // 更新记录
    public static void update(String sql) {
        try (Connection conn = DatabaseConnection.getConnection(null);
                Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("更新的行数: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            e.printStackTrace(); // 打印堆栈信息
        }
    }

    // 删除记录
    public static void delete(String sql) {
        try (Connection conn = DatabaseConnection.getConnection(null);
                Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("删除的行数: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            e.printStackTrace(); // 打印堆栈信息
        }
    }
}
