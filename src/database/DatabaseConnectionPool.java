package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionPool {

    private static HikariDataSource dataSource;

    // 静态块初始化连接池
    static {
        // 配置连接池参数
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db"); // 数据库URL
        config.setUsername("root"); // 数据库用户名
        config.setPassword("root"); // 数据库密码
        config.setMaximumPoolSize(10); // 连接池最大连接数
        config.setMinimumIdle(5); // 最小空闲连接数
        config.setIdleTimeout(300000); // 空闲连接的超时值
        config.setMaxLifetime(600000); // 连接最大生命周期
        config.setConnectionTimeout(30000); // 获取连接的超时

        // 创建连接池
        dataSource = new HikariDataSource(config);
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 关闭连接池
    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
