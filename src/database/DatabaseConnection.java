package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 默认的数据库连接信息
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/db";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "root";

    // 根据命令行参数连接数据库
    public static Connection getConnection(String[] args) throws SQLException {
        String url = DEFAULT_URL;
        String user = DEFAULT_USER;
        String password = DEFAULT_PASSWORD;

        // 如果命令行参数存在，解析数据库连接信息
        if (args.length == 3) {
            url = args[0]; // 第一个参数是数据库URL
            user = args[1]; // 第二个参数是用户名
            password = args[2]; // 第三个参数是密码
        }

        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取数据库连接
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("未找到 MySQL 驱动程序", e);
        }
    }
}
