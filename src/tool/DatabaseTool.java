package tool;
import database.*;

import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseTool {

    private static Scanner scanner = new Scanner(System.in);

    // 显示菜单
    private static void showMenu() {
        System.out.println("\n请选择操作：");
        System.out.println("1. 创建记录");
        System.out.println("2. 查询记录");
        System.out.println("3. 更新记录");
        System.out.println("4. 删除记录");
        System.out.println("5. 退出");
        System.out.print("请输入选项 (1-5): ");
    }

    // 主程序入口
    public static void main(String[] args) {
        // 通过命令行参数接收数据库连接信息（如果提供）
        try {
            // 启动数据库连接
            System.out.println("连接数据库...");
            DatabaseConnection.getConnection(args);
            System.out.println("连接成功!");

            // 进入命令行工具交互
            while (true) {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // 消耗换行符
                switch (choice) {
                    case 1:
                        System.out.print("请输入 SQL 插入语句: ");
                        String insertSQL = scanner.nextLine();
                        CRUDOperations.create(insertSQL);
                        break;
                    case 2:
                        System.out.print("请输入 SQL 查询语句: ");
                        String selectSQL = scanner.nextLine();
                        CRUDOperations.read(selectSQL);
                        break;
                    case 3:
                        System.out.print("请输入 SQL 更新语句: ");
                        String updateSQL = scanner.nextLine();
                        CRUDOperations.update(updateSQL);
                        break;
                    case 4:
                        System.out.print("请输入 SQL 删除语句: ");
                        String deleteSQL = scanner.nextLine();
                        CRUDOperations.delete(deleteSQL);
                        break;
                    case 5:
                        System.out.println("退出程序...");
                        return;
                    default:
                        System.out.println("无效选项，请重新输入.");
                }
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        }
    }
}
