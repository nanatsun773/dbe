package jp.ac.chitose.dbe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionalDAO {

    private static final String URL = "jdbc:h2:~/h2db/abe;AUTO_SERVER=TRUE;MODE=PostgreSQL";
    private static final String USER_NAME = "b2201580";
    private static final String USER_PASS = "b2201580";

    // トランザクションを体験するための実践的なソースコード
    public void insertUserAndExam() throws SQLException {

        String gakuseiCode = "A110";
        String shi = "タナカ";
        String mei = "イチロウ";
        int gakunen = 1;
        int tokuten = 50;

        String sql1 = "insert into 学生情報 values (?, ?, ?, ?)";
        String sql2 = "insert into 中間テスト values (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, USER_PASS)) {

            // この行のデータベースへの処理をトランザクションにすることをDBMSに命令する
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql1)) {
                stmt.setString(1, gakuseiCode);
                stmt.setString(2, shi);
                stmt.setString(3, mei);
                stmt.setInt(4, gakunen);
                stmt.executeUpdate();
            }

            System.out.println("A110の学生情報を追加");

            // 意図的に処理を中断するための処理
            System.out.println("中断するときは0を入れてください");
            Scanner scanner = new Scanner(System.in);
            int key = scanner.nextInt();
            if (key == 0) {
                throw new RuntimeException("中断");
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
                stmt.setString(1, gakuseiCode);
                stmt.setInt(2, tokuten);
                stmt.executeUpdate();
            }

            // この行までのトランザクションをデータベースに反映することをDBMSに命令する
            conn.commit();

            System.out.println("A110の中間テストを追加");
        }
    }
}
