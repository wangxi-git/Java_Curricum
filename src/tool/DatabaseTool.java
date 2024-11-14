package tool;

import database.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DatabaseTool {

    private static Scanner scanner = new Scanner(System.in);
    private static ExecutorService executorService = Executors.newFixedThreadPool(5); // 使用 5 个线程的线程池
    private static CRUDOperations crudOperations = new CRUDOperationsImpl(); // 通过接口调用实现类

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

    // 执行数据库操作的同步方法
    private static void executeOperation(String sql, String operationType) {
        try {
            switch (operationType) {
                case "CREATE":
                    crudOperations.create(sql);
                    System.out.println("插入数据成功！");
                    break;
                case "READ":
                    crudOperations.read(sql);
                    System.out.println("查询完成！");
                    break;
                case "UPDATE":
                    crudOperations.update(sql);
                    System.out.println("更新数据成功！");
                    break;
                case "DELETE":
                    crudOperations.delete(sql);
                    System.out.println("删除数据成功！");
                    break;
                default:
                    System.out.println("无效操作");
            }
        } catch (Exception e) {
            System.out.println("操作失败: " + e.getMessage());
        }
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

            // 连接数据库
            DatabaseConnectionPool.getConnection();
            System.out.println("数据库连接成功!");

            // 进入命令行工具交互
            while (true) {
                try {
                    showMenu();
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // 消耗换行符

                    // 根据选择执行相应操作
                    switch (choice) {
                        case 1:
                            System.out.print("请输入 SQL 插入语句: ");
                            String insertSQL = scanner.nextLine();
                            executeOperation(insertSQL, "CREATE"); // 执行插入操作并等待操作完成
                            break;
                        case 2:
                            System.out.print("请输入 SQL 查询语句: ");
                            String selectSQL = scanner.nextLine();
                            executeOperation(selectSQL, "READ"); // 执行查询操作并等待操作完成
                            break;
                        case 3:
                            System.out.print("请输入 SQL 更新语句: ");
                            String updateSQL = scanner.nextLine();
                            executeOperation(updateSQL, "UPDATE"); // 执行更新操作并等待操作完成
                            break;
                        case 4:
                            System.out.print("请输入 SQL 删除语句: ");
                            String deleteSQL = scanner.nextLine();
                            executeOperation(deleteSQL, "DELETE"); // 执行删除操作并等待操作完成
                            break;
                        case 5:
                            System.out.println("退出程序...");
                            executorService.shutdown(); // 关闭线程池
                            DatabaseConnectionPool.close(); // 关闭连接池
                            return; // 退出程序
                        default:
                            System.out.println("无效选项，请重新输入.");
                            break;
                    }

                    // 等待用户输入后显示菜单
                    TimeUnit.SECONDS.sleep(1); // 确保输出操作提示后再显示菜单

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
