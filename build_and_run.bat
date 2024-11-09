@echo off
set SRC_DIR=src
set TEST_DIR=Test
set LIB_DIR=lib
set BIN_DIR=bin

set JDBC_DRIVER=%LIB_DIR%\mysql-connector-java-8.0.30.jar

:: 编译源代码
javac -cp %JDBC_DRIVER% -d %BIN_DIR% %SRC_DIR%\*.java

:: 编译测试代码
javac -cp %JDBC_DRIVER%;%BIN_DIR% -d %BIN_DIR% %TEST_DIR%\*.java

:: 运行测试
java -cp %JDBC_DRIVER%;%BIN_DIR% Test.DatabaseConnectionTest
java -cp %JDBC_DRIVER%;%BIN_DIR% Test.CreateTest
java -cp %JDBC_DRIVER%;%BIN_DIR% Test.ReadTest
java -cp %JDBC_DRIVER%;%BIN_DIR% Test.UpdateTest
java -cp %JDBC_DRIVER%;%BIN_DIR% Test.DeleteTest
