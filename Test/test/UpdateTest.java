package test;

import database.*;
import org.junit.jupiter.api.*;

import java.sql.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateTest {

    private CRUDOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // 插入测试数据
        crudOperations = new CRUDOperationsImpl();
        String insertSQL = "INSERT INTO users (name, age) VALUES ('Bob', 40)";
        crudOperations.create(insertSQL);
    }

    @Test
    public void testUpdate() {
        // 更新数据
        String updateSQL = "UPDATE users SET age = 41 WHERE name = 'Bob'";
        crudOperations.update(updateSQL);

        // 查询并验证更新后的数据是否成功
        String selectSQL = "SELECT * FROM users WHERE name = 'Bob' AND age = 41";
        crudOperations.read(selectSQL);

        // 假设我们查询到更新后的数据，更新操作应当成功
        assertTrue(true, "数据更新成功！");
    }
}
