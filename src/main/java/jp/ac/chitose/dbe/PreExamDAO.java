package jp.ac.chitose.dbe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreExamDAO {
    // ↓学籍番号(XXXXXXXX)は、データベース初期登録時に設定したもの
    private static final String URL = "jdbc:h2:~/h2db/abe;AUTO_SERVER=TRUE;MODE=PostgreSQL";
    private static final String USER_NAME = "b2201580";
    private static final String USER_PASS = "b2201580";

    public List<PreExam> selectPreExams(int lessThan) throws SQLException {
        List<PreExam> returning = new ArrayList<PreExam>();
        String sql = "select * from 中間テスト where 得点 < ?";

        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lessThan);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                var col1 = results.getString("学生コード");
                var col2 = results.getInt("得点");
                PreExam preExam = new PreExam(col1, col2);
                returning.add(preExam);
            }
        }
        return returning;
    }

    public int deletePreExam(String gakuseiCode) throws SQLException {
        String sql = "delete from 中間テスト where 学生コード = ?";
        int n = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gakuseiCode);
            n = stmt.executeUpdate();
        }
        return n;
    }

    public int insertPreExam(String gakuseiCode, int point) throws SQLException {
        String sql = "insert into 中間テスト values (?, ?)";
        int n = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gakuseiCode);
            stmt.setInt(2, point);
            n = stmt.executeUpdate();
        }
        return n;
    }
}

