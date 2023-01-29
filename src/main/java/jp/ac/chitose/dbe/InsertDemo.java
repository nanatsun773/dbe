package jp.ac.chitose.dbe;

import java.sql.SQLException;
import java.util.Scanner;

public class InsertDemo {
    public static void main(String[] args) {
        System.out.print("中間テストの得点を追加する学生コードは?:");
        Scanner scan = new Scanner(System.in);
        String gakuseiCode = scan.nextLine();
        System.out.print(gakuseiCode + "の得点は?:");
        int point = Integer.valueOf(scan.nextLine());

        try {
            PreExamDAO preExamDAO = new PreExamDAO();
            int n = preExamDAO.insertPreExam(gakuseiCode, point);
            System.out.println(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
