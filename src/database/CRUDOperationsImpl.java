package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CRUDOperationsImpl implements CRUDOperations {

    // 创建一个线程池，处理并发任务
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10); // 设置线程池大小为 10

    @Override
    public void create(String sql) {
        executorService.submit(() -> {
            try (Connection conn = DatabaseConnectionPool.getConnection();
                    Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(sql);
                System.out.println("插入的行数: " + rowsAffected);
                if (rowsAffected > 0) {
                    System.out.println("插入数据成功！");
                } else {
                    System.out.println("没有数据被插入！");
                }
            } catch (SQLException e) {
                System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            }
        });
    }

    @Override
    public void read(String sql) {
        executorService.submit(() -> {
            try (Connection conn = DatabaseConnectionPool.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                // 获取结果集的元数据，包含列信息
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount(); // 获取列数

                boolean found = false; // 标记是否有数据

                // 打印列名
                System.out.println("查询结果:");

                // 遍历结果集
                while (rs.next()) {
                    // 遍历每一列，动态获取列的数据
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i); // 获取列名
                        String columnType = metaData.getColumnTypeName(i); // 获取列的数据类型

                        // 根据列的数据类型获取相应的值
                        if (columnType.equals("VARCHAR") || columnType.equals("CHAR") || columnType.equals("TEXT")) {
                            // 对于字符串类型的列，使用 getString()
                            row.append(columnName).append(": ").append(rs.getString(i)).append(" | ");
                        } else if (columnType.equals("INT") || columnType.equals("BIGINT")) {
                            // 对于整数类型的列，使用 getInt()
                            row.append(columnName).append(": ").append(rs.getInt(i)).append(" | ");
                        } else if (columnType.equals("DECIMAL") || columnType.equals("FLOAT")
                                || columnType.equals("DOUBLE")) {
                            // 对于浮动类型的列，使用 getDouble()
                            row.append(columnName).append(": ").append(rs.getDouble(i)).append(" | ");
                        } else if (columnType.equals("DATE") || columnType.equals("DATETIME")
                                || columnType.equals("TIMESTAMP")) {
                            // 对于日期类型的列，使用 getDate()
                            row.append(columnName).append(": ").append(rs.getDate(i)).append(" | ");
                        } else {
                            // 对于其他类型的列，使用 getObject()
                            row.append(columnName).append(": ").append(rs.getObject(i)).append(" | ");
                        }
                    }
                    System.out.println(row.toString()); // 打印每一行的数据
                    found = true;
                }

                // 如果没有结果，提示没有数据
                if (!found) {
                    System.out.println("没有找到相关数据！");
                }
            } catch (SQLException e) {
                System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            }
        });
    }

    @Override
    public void update(String sql) {
        executorService.submit(() -> {
            try (Connection conn = DatabaseConnectionPool.getConnection();
                    Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(sql);
                if (rowsAffected > 0) {
                    System.out.println("更新数据成功！更新的行数: " + rowsAffected);
                } else {
                    System.out.println("没有数据被更新！");
                }
            } catch (SQLException e) {
                System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            }
        });
    }

    @Override
    public void delete(String sql) {
        executorService.submit(() -> {
            try (Connection conn = DatabaseConnectionPool.getConnection();
                    Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(sql);
                if (rowsAffected > 0) {
                    System.out.println("删除数据成功！删除的行数: " + rowsAffected);
                } else {
                    System.out.println("没有数据被删除！");
                }
            } catch (SQLException e) {
                System.out.println("执行 SQL 时发生错误: " + e.getMessage());
            }
        });
    }

    // 关闭线程池
    public static void shutdown() {
        executorService.shutdown();
    }
}
