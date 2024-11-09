
package test;
import database.DatabaseConnection;
import tool.DatabaseTool;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() {
        try (Connection connection = DatabaseConnection.getConnection(null)) {
            assertNotNull(connection, "连接应当不为 null");
        } catch (SQLException e) {
            fail("数据库连接失败: " + e.getMessage());
        }
    }
}
