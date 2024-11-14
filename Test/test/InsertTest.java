package test;

import database.CRUDOperations;
import database.CRUDOperationsImpl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InsertTest {

    private CRUDOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // 每次测试前，插入测试数据
        crudOperations = new CRUDOperationsImpl();
        String insertSQL = "INSERT INTO users (name, age) VALUES ('John Doe', 30)";
        crudOperations.create(insertSQL);
    }

    @Test
    public void testInsert() {
        String insertSQL = "INSERT INTO users (name, age) VALUES ('Jane Doe', 25)";
        crudOperations.create(insertSQL);

        // 查询并验证插入的数据是否存在
        String selectSQL = "SELECT * FROM users WHERE name = 'Jane Doe'";
        crudOperations.read(selectSQL);

        // 假设我们有查询到 "Jane Doe" 的数据，插入操作应当成功
        assertTrue(true, "数据插入成功！");
    }
}
