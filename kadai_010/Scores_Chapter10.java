package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {
    public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;
        
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "dolphin@kick310"
            );
            System.out.println("データベース接続成功:"+ con);
         
            // ステートメント生成
            statement = con.createStatement();
                        
            // SQL文の作成1
            String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5";
            
            // SQLクエリを実行
            System.out.println("レコード更新を実行します");
            int update = statement.executeUpdate(sql);
            System.out.println( update + "件のレコードが更新されました");   
            
            // SQL文の作成2
            String sql2 = "SELECT id,name,score_english,score_math FROM scores ORDER BY score_math DESC,score_english DESC";
            
            // SQLクエリを実行2
            ResultSet result = statement.executeQuery(sql2);
            System.out.println("数学・英語の点数が高い順に並べ替えました");
           
            int count = 1;
            
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int score_math = result.getInt("score_math");
                int score_english = result.getInt("score_english");
                System.out.println(count + "件目：" +  "生徒ID=" + id + "／氏名=" + name + "／数学" + score_math +"／英語="+ score_english);
                
                count++;
            }

            		
                       
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // リソースを解放
            try {
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}