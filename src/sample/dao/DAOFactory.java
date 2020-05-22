package sample.dao;

import sample.dao.Admin.AdminDAO;
import sample.dao.Admin.AdminDAOImpl;
import sample.dao.alternatives.AlternativesDAO;
import sample.dao.alternatives.AlternativesDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
public class DAOFactory {

    public static final DAOFactory instance = new DAOFactory();
    public static synchronized DAOFactory getInstance(){return instance; }
    private DAOFactory(){}
    /////////////////////////////////////
    private static final String url = "jdbc:mysql://localhost:3306/kurs?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String username = "root";
    private static final String password = "15022001Alina";

    private static Connection conn;

    public   Connection createConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database!!");

        } catch (Exception ex) {
            System.out.println(" Connection failed...");
            System.out.println(ex);
        }
        return conn;
    }


    public AlternativesDAO getAlternativeDAO(){
        return   AlternativesDAOImpl.getInstance();
    }



    public AdminDAO getAdminDAO() { return AdminDAOImpl.getInstance();}

}
