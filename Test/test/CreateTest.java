package test;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection(args)) {
            String insertSQL = "INSERT INTO users (name, age) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
                statement.setString(1, "Alice");
                statement.setInt(2, 30);
                int rowsAffected = statement.executeUpdate();
                System.out.println(rowsAffected + " 行数据已插入。");
            }
        } catch (SQLException e) {
            System.out.println("插入数据失败: " + e.getMessage());
        }
    }
}
