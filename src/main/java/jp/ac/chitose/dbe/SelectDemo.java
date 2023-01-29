package jp.ac.chitose.dbe;

import jp.ac.chitose.dbe.PreExam;
import jp.ac.chitose.dbe.PreExamDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SelectDemo {

    public static void main(String[] args) {
        System.out.print("点数を入力してください:");
        Scanner scan = new Scanner(System.in);
        int lessThan = Integer.valueOf(scan.nextLine());

        try {
            PreExamDAO dao = new PreExamDAO();
            List<PreExam> preExams = dao.selectPreExams(lessThan);
            for (PreExam preExam : preExams) {
                preExam.print();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
