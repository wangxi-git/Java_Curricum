package test;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection(args)) {
            String updateSQL = "UPDATE users SET age = ? WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
                statement.setInt(1, 31); // 更新年龄为 31
                statement.setString(2, "A"); // 更新名称为 A 的用户
                int rowsAffected = statement.executeUpdate();
                System.out.println(rowsAffected + " 行数据已更新。");
            }
        } catch (SQLException e) {
            System.out.println("更新数据失败: " + e.getMessage());
        }
    }
}
