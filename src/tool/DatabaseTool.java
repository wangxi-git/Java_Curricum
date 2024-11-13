package tool;

import database.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
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
        try {
            String url = "jdbc:mysql://localhost:3306/db"; // 默认数据库URL
            String user = "root"; // 默认用户名
            String password = "root"; // 默认密码

            // 如果命令行参数存在，则使用提供的数据库连接信息
            if (args.length == 3) {
                url = args[0]; // 第一个参数是数据库URL
                user = args[1]; // 第二个参数是用户名
                password = args[2]; // 第三个参数是密码
            }

            // 尝试连接数据库
            System.out.println("连接数据库...");
            DatabaseConnection.getConnection(new String[] { url, user, password });
            System.out.println("连接成功!");

            // 进入命令行工具交互
            while (true) {
                try {
                    showMenu();
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // 消耗换行符
                    switch (choice) {
                        case 1:
                            System.out.print("请输入 SQL 插入语句: ");
                            String insertSQL = scanner.nextLine();
                            // 捕获执行 SQL 时可能出现的异常
                            CRUDOperations.create(insertSQL); // 这里的 create 方法会处理 SQLException
                            break;
                        case 2:
                            System.out.print("请输入 SQL 查询语句: ");
                            String selectSQL = scanner.nextLine();
                            // 捕获执行 SQL 时可能出现的异常
                            CRUDOperations.read(selectSQL); // 这里的 read 方法会处理 SQLException
                            break;
                        case 3:
                            System.out.print("请输入 SQL 更新语句: ");
                            String updateSQL = scanner.nextLine();
                            // 捕获执行 SQL 时可能出现的异常
                            CRUDOperations.update(updateSQL); // 这里的 update 方法会处理 SQLException
                            break;
                        case 4:
                            System.out.print("请输入 SQL 删除语句: ");
                            String deleteSQL = scanner.nextLine();
                            // 捕获执行 SQL 时可能出现的异常
                            CRUDOperations.delete(deleteSQL); // 这里的 delete 方法会处理 SQLException
                            break;
                        case 5:
                            System.out.println("退出程序...");
                            return; // 退出程序
                        default:
                            System.out.println("无效选项，请重新输入.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("无效输入，请输入数字选项。");
                    scanner.nextLine(); // 清空输入缓存，等待下一次输入
                } catch (Exception e) {
                    System.out.println("程序发生错误: " + e.getMessage());
                    e.printStackTrace(); // 打印堆栈信息，方便调试
                }
            }
        } catch (Exception e) {
            System.out.println("程序发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
