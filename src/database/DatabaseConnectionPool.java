package database;

import java.sql.*;
import java.util.concurrent.*;
import java.util.*;

public class DatabaseConnectionPool {
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 50;

    private static final Queue<Connection> connectionPool = new LinkedList<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5); // 为多线程操作设置线程池

    static {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection() {
        synchronized (connectionPool) {
            if (connectionPool.isEmpty()) {
                return createConnection(); // 若连接池为空，则创建一个新的连接
            } else {
                return connectionPool.poll();
            }
        }
    }

    public static void releaseConnection(Connection connection) {
        synchronized (connectionPool) {
            if (connection != null) {
                connectionPool.offer(connection); // 将连接放回池中
            }
        }
    }

    public static void close() {
        executorService.shutdown();
        // 在程序结束时，可以选择关闭所有连接池中的连接
        for (Connection conn : connectionPool) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
