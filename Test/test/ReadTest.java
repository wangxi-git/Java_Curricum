package test;
import database.DatabaseConnection;
import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReadTest {

    @Test
    public void testReadData() {
        String selectSQL = "SELECT id, name FROM users WHERE name = 'Alice'";
        try (Connection connection = DatabaseConnection.getConnection(null);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(selectSQL)) {

            assertTrue(rs.next(), "查询未返回结果。");
            int id = rs.getInt("id");
            String name = rs.getString("name");
            assertEquals("Alice", name, "查询的名字应该是 Alice");
        } catch (SQLException e) {
            fail("数据库操作失败: " + e.getMessage());
        }
    }
}
