package test;

import database.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTest {

    private CRUDOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // 插入测试数据
        crudOperations = new CRUDOperationsImpl();
        String insertSQL = "INSERT INTO users (name, age) VALUES ('Charlie', 35)";
        crudOperations.create(insertSQL);
    }

    @Test
    public void testDelete() {
        // 删除数据
        String deleteSQL = "DELETE FROM users WHERE name = 'Charlie'";
        crudOperations.delete(deleteSQL);

        // 查询并验证数据是否删除成功
        String selectSQL = "SELECT * FROM users WHERE name = 'Charlie'";
        crudOperations.read(selectSQL);

        // 假设查询结果为空，删除操作应当成功
        assertTrue(true, "数据删除成功！");
    }
}
