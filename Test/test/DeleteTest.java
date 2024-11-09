package test;
import database.*;
import tool.DatabaseTool;
import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTest {

    @Test
    public void testDeleteData() {
        String deleteSQL = "DELETE FROM users WHERE name = 'Alice'";
        try (Connection connection = DatabaseConnection.getConnection(null);
             Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(deleteSQL);
            assertEquals(1, rowsAffected, "删除失败，应该删除一行数据。");

            // 验证是否删除成功
            String selectSQL = "SELECT * FROM users WHERE name = 'Alice'";
            try (ResultSet rs = statement.executeQuery(selectSQL)) {
                assertFalse(rs.next(), "删除失败，Alice 仍然存在。");
            }
        } catch (SQLException e) {
            fail("数据库操作失败: " + e.getMessage());
        }
    }
}
