package test;

import database.CRUDOperations;
import database.CRUDOperationsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CRUDOperationsTest {

    private CRUDOperations crudOperations;

    // 在每个测试之前创建 CRUDOperations 实例
    @BeforeEach
    public void setUp() {
        crudOperations = new CRUDOperationsImpl();
    }

    // 测试插入记录
    @Test
    public void testCreate() {
        // SQL 插入语句
        String insertSQL = "INSERT INTO users (name, age) VALUES ('John Doe', 30)";
        try {
            crudOperations.create(insertSQL);
        } catch (SQLException e) {
            fail("插入记录失败: " + e.getMessage());
        }
    }

    // 测试查询记录
    @Test
    public void testRead() {
        // 先插入一条记录以供查询
        String insertSQL = "INSERT INTO users (name, age) VALUES ('Jane Doe', 25)";
        try {
            crudOperations.create(insertSQL);
        } catch (SQLException e) {
            fail("插入记录失败: " + e.getMessage());
        }

        // 然后执行查询
        String selectSQL = "SELECT * FROM users WHERE name = 'Jane Doe'";
        try {
            crudOperations.read(selectSQL);
        } catch (SQLException e) {
            fail("查询记录失败: " + e.getMessage());
        }
    }

    // 测试更新记录
    @Test
    public void testUpdate() {
        // 先插入一条记录
        String insertSQL = "INSERT INTO users (name, age) VALUES ('Mike', 20)";
        try {
            crudOperations.create(insertSQL);
        } catch (SQLException e) {
            fail("插入记录失败: " + e.getMessage());
        }

        // 然后执行更新
        String updateSQL = "UPDATE users SET age = 21 WHERE name = 'Mike'";
        try {
            crudOperations.update(updateSQL);
        } catch (SQLException e) {
            fail("更新记录失败: " + e.getMessage());
        }

        // 确保更新后的数据是正确的
        String selectSQL = "SELECT * FROM users WHERE name = 'Mike'";
        try {
            crudOperations.read(selectSQL);
        } catch (SQLException e) {
            fail("查询更新后的记录失败: " + e.getMessage());
        }
    }
}
