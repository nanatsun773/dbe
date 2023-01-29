package jp.ac.chitose.dbe;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteDemo {
    public static void main(String[] args) {
        System.out.print("中間テストの点数を削除する学生コードは?:");
        Scanner scan = new Scanner(System.in);
        String gakuseiCode = scan.nextLine();

        try {
            PreExamDAO dao = new PreExamDAO();
            var n = dao.deletePreExam(gakuseiCode);
            System.out.println(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
