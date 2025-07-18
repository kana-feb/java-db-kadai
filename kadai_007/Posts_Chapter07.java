package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Posts_Chapter07 {
    public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;
        
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "dolphin@kick310"
            );
            System.out.println("データベース接続成功");
            
            System.out.println("レコード追加を実行します");
            
            // SQL文の作成
            String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES" +
                         "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13)," +
                         "(1002, '2023-02-08', 'お疲れ様です！', 12)," +
                         "(1003, '2023-02-09', '今日も頑張ります！', 18)," +
                         "(1001, '2023-02-09', '無理は禁物ですよ！', 17)," +
                         "(1002, '2023-02-10', '明日から連休ですね！', 20);";
            
            statement = con.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println(rows + " 行のデータを挿入しました。");

            
            System.out.println("ユーザーIDが1002のレコードを検索しました");
            // SQL文の作成
            String sql2 = "SELECT posted_at, post_content, likes FROM posts WHERE user_id =  1002 ;";
            //　SQLクエリを実行（DBMSに送信）
            ResultSet  result = statement.executeQuery(sql2);

            
            int count = 1;
            
            while(result.next()) {
                Date posted_at = result.getDate("posted_at");
                String post_content = result.getString("post_content");
                int likes = result.getInt("likes");
                System.out.println(count + "件目：" +  "投稿日時=" + posted_at + "／投稿内容=" + post_content + "／いいね数=" + likes);
                
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