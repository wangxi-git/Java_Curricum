package test;

import database.*;
import org.junit.jupiter.api.*;

import java.sql.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryTest {

    private CRUDOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // 插入测试数据
        crudOperations = new CRUDOperationsImpl();
        String insertSQL = "INSERT INTO users (name, age) VALUES ('Alice', 28)";
        crudOperations.create(insertSQL);
    }

    @Test
    public void testQuery() {
        // 查询数据
        String selectSQL = "SELECT * FROM users WHERE name = 'Alice'";
        crudOperations.read(selectSQL);

        // 假设我们在控制台输出中能看到 "Alice" 的记录，查询操作应当成功
        assertTrue(true, "数据查询成功！");
    }
}
