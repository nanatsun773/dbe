package jp.ac.chitose.dbe;

import java.sql.SQLException;

public class TransactionalDemo {

    public static void main(String[] args) {
        try {
            TransactionalDAO transDAO = new TransactionalDAO();
            transDAO.insertUserAndExam();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
