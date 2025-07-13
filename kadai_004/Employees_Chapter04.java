package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {
    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;

        try {
            // データベースに接続（URL・ユーザー名・パスワード）
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "dolphin@kick310"              );

            System.out.println("データベース接続成功:"+ con);

            // SQLクエリ（CREATE TABLE）を準備
            String sql = """
                CREATE TABLE employees (
                    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(60) NOT NULL,
                    email VARCHAR(255)	NOT NULL,
                    age INT(11),
                    address VARCHAR(255)
                );
            """;

            // SQLクエリを送信（テーブル作成）
            stmt = con.createStatement();
            int rowCnt = stmt.executeUpdate(sql); 
            System.out.println("社員テーブルを作成しました：更新レコード数 =" + rowCnt);

        } catch (SQLException e) {
            System.out.println("エラー：" + e.getMessage());

        } finally {
            // 使ったオブジェクトを解放（nullチェックあり）
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException ignore) {}
        }
    }
}